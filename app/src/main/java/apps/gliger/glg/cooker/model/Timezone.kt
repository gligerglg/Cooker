package apps.gliger.glg.cooker.model


import com.squareup.moshi.Json

data class Timezone(
    @Json(name = "description")
    val description: String,
    @Json(name = "offset")
    val offset: String
)