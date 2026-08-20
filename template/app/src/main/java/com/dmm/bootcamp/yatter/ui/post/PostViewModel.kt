package com.dmm.bootcamp.yatter.ui.post

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter.domain.service.GetLoginUserService
import com.dmm.bootcamp.yatter.usecase.post.PostYweetUseCase
import com.dmm.bootcamp.yatter.usecase.post.PostYweetUseCaseResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface PostNavigationEvent {
  data object Posted : PostNavigationEvent

  data object Back : PostNavigationEvent
}

class PostViewModel(
  private val postYweetUseCase: PostYweetUseCase,
  private val getLoginUserService: GetLoginUserService,
) : ViewModel() {
  private val _uiState: MutableStateFlow<PostUiState> =
    MutableStateFlow(PostUiState.empty())

  val uiState: StateFlow<PostUiState> =
    _uiState.asStateFlow()

  private val _navigationEvent =
    Channel<PostNavigationEvent>(Channel.BUFFERED)

  val navigationEvent: Flow<PostNavigationEvent> =
    _navigationEvent.receiveAsFlow()

  fun onCreate() {
    viewModelScope.launch {
      Log.d("PostViewModel", "onCreate START")

      _uiState.update {
        it.copy(isLoading = true)
      }

      Log.d(
        "PostViewModel",
        "getLoginUserService.execute() 前",
      )

      val loginUser = getLoginUserService.execute()

      Log.d(
        "PostViewModel",
        "getLoginUserService.execute() 後: $loginUser",
      )

      val currentBindingModel =
        uiState.value.bindingModel

      _uiState.update {
        it.copy(
          bindingModel =
            currentBindingModel.copy(
              avatarUrl = loginUser?.avatar?.toString(),
            ),
          isLoading = false,
        )
      }

      Log.d("PostViewModel", "onCreate END")
    }
  }

  fun onChangedYweetText(yweetText: String) {
    val currentBindingModel =
      uiState.value.bindingModel

    _uiState.update {
      it.copy(
        bindingModel =
          currentBindingModel.copy(
            yweetText = yweetText,
          ),
      )
    }
  }

  fun onClickPost(context: Context) {
    viewModelScope.launch {
      Log.d("PostViewModel", "onClickPost START")

      _uiState.update {
        it.copy(isLoading = true)
      }

      val currentBindingModel =
        uiState.value.bindingModel

      Log.d(
        "PostViewModel",
        "投稿内容: ${currentBindingModel.yweetText}",
      )

      val result =
        postYweetUseCase.execute(
          content = currentBindingModel.yweetText,
          attachmentList = emptyList(),
        )

      Log.d(
        "PostViewModel",
        "投稿結果: $result",
      )

      when (result) {
        PostYweetUseCaseResult.Success -> {
          Log.d(
            "PostViewModel",
            "投稿成功",
          )

          _navigationEvent.send(
            PostNavigationEvent.Posted,
          )
        }

        is PostYweetUseCaseResult.Failure -> {
          Log.e(
            "PostViewModel",
            "投稿失敗: $result",
          )
        }
      }

      _uiState.update {
        it.copy(isLoading = false)
      }

      Log.d("PostViewModel", "onClickPost END")
    }
  }

  fun onClickNavIcon() {
    viewModelScope.launch {
      Log.d(
        "PostViewModel",
        "戻るボタン押下",
      )

      _navigationEvent.send(
        PostNavigationEvent.Back,
      )
    }
  }
}
