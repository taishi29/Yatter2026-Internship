-keepnames class remote.models.User
-if class remote.models.User
-keep class remote.models.UserJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
-if class remote.models.User
-keepnames class kotlin.jvm.internal.DefaultConstructorMarker
-keepclassmembers class remote.models.User {
    public synthetic <init>(int,java.lang.String,java.time.OffsetDateTime,int,int,java.lang.String,java.lang.String,java.lang.String,java.lang.String,int,kotlin.jvm.internal.DefaultConstructorMarker);
}
