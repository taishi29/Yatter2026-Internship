package com.dmm.bootcamp.yatter.ui.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter.ui.post.bindingmodel.PostBindingModel
import com.dmm.bootcamp.yatter.ui.theme.YatterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTemplate(
  postBindingModel: PostBindingModel,
  isLoading: Boolean,
  canPost: Boolean,
  onYweetTextChanged: (String) -> Unit,
  onClickPost: () -> Unit,
  onClickNavIcon: () -> Unit,
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = "投稿")
        },
        navigationIcon = {
          IconButton(
            onClick = onClickNavIcon,
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "戻る",
            )
          }
        },
      )
    },
  ) { paddingValues ->
    Box(
      modifier =
        Modifier
          .fillMaxSize()
          .padding(paddingValues),
      contentAlignment = Alignment.Center,
    ) {
      Row(
        modifier =
          Modifier
            .fillMaxSize()
            .padding(16.dp),
      ) {
        AsyncImage(
          modifier =
            Modifier
              .size(64.dp),
          model = postBindingModel.avatarUrl,
          contentDescription = "アバター画像",
          contentScale = ContentScale.Crop,
        )

        Column(
          modifier =
            Modifier
              .weight(1f)
              .fillMaxSize(),
          horizontalAlignment = Alignment.End,
        ) {
          TextField(
            modifier =
              Modifier
                .fillMaxWidth()
                .weight(1f),
            value = postBindingModel.yweetText,
            onValueChange = onYweetTextChanged,
            placeholder = {
              Text(text = "今何してる？")
            },
            colors =
              TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
              ),
          )

          Button(
            modifier = Modifier.padding(16.dp),
            onClick = onClickPost,
            enabled = canPost && !isLoading,
          ) {
            Text(text = "ツイート")
          }
        }
      }

      if (isLoading) {
        CircularProgressIndicator()
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PostTemplatePreview() {
  YatterTheme {
    Surface {
      PostTemplate(
        postBindingModel =
          PostBindingModel(
            avatarUrl = null,
            yweetText = "",
            attachmentImageUris = emptyList(),
          ),
        isLoading = false,
        canPost = false,
        onYweetTextChanged = {},
        onClickPost = {},
        onClickNavIcon = {},
      )
    }
  }
}
