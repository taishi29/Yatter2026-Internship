-keepnames class remote.models.Attachment
-if class remote.models.Attachment
-keep class remote.models.AttachmentJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
-if class remote.models.Attachment
-keepnames class kotlin.jvm.internal.DefaultConstructorMarker
-keepclassmembers class remote.models.Attachment {
    public synthetic <init>(int,java.lang.String,java.lang.String,java.lang.String,int,kotlin.jvm.internal.DefaultConstructorMarker);
}
