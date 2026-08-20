-keepnames class remote.models.Login200Response
-if class remote.models.Login200Response
-keep class remote.models.Login200ResponseJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
-if class remote.models.Login200Response
-keepnames class kotlin.jvm.internal.DefaultConstructorMarker
-keepclassmembers class remote.models.Login200Response {
    public synthetic <init>(java.lang.String,int,kotlin.jvm.internal.DefaultConstructorMarker);
}
