-keepnames class remote.models.Image
-if class remote.models.Image
-keep class remote.models.ImageJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
