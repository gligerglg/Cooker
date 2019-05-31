package apps.gliger.glg.cooker.model


import com.squareup.moshi.Json

data class Picture(
    @Json(name = "large")
    val large: String,
    @Json(name = "medium")
    val medium: String,
    @Json(name = "thumbnail")
    val thumbnail: String
)