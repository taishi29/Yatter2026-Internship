-keepnames class remote.models.LoginRequest
-if class remote.models.LoginRequest
-keep class remote.models.LoginRequestJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
-if class remote.models.LoginRequest
-keepnames class kotlin.jvm.internal.DefaultConstructorMarker
-keepclassmembers class remote.models.LoginRequest {
    public synthetic <init>(java.lang.String,java.lang.String,int,kotlin.jvm.internal.DefaultConstructorMarker);
}
