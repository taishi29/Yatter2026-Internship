-keepnames class remote.models.Yweet
-if class remote.models.Yweet
-keep class remote.models.YweetJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
