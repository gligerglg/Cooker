package apps.gliger.glg.cooker.model


import com.squareup.moshi.Json

data class Registered(
    @Json(name = "age")
    val age: Int,
    @Json(name = "date")
    val date: String
)