-keepnames class remote.models.AttachmentRequest
-if class remote.models.AttachmentRequest
-keep class remote.models.AttachmentRequestJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
