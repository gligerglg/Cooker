package apps.gliger.glg.cooker.model


import com.squareup.moshi.Json

data class PeopleResponse(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Result>
)