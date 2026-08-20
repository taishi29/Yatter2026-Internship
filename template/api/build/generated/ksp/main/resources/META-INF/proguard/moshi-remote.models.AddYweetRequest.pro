-keepnames class remote.models.AddYweetRequest
-if class remote.models.AddYweetRequest
-keep class remote.models.AddYweetRequestJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
-if class remote.models.AddYweetRequest
-keepnames class kotlin.jvm.internal.DefaultConstructorMarker
-keepclassmembers class remote.models.AddYweetRequest {
    public synthetic <init>(java.lang.String,java.util.List,int,kotlin.jvm.internal.DefaultConstructorMarker);
}
