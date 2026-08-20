-keepnames class remote.models.Relationship
-if class remote.models.Relationship
-keep class remote.models.RelationshipJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
