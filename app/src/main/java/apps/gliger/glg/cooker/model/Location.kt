package apps.gliger.glg.cooker.model


import androidx.room.Embedded
import com.squareup.moshi.Json

data class Location(
    @Json(name = "city")
    val city: String,
    @Json(name = "coordinates")
    @Embedded
    val coordinates: Coordinates,
    @Json(name = "postcode")
    val postcode: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "street")
    val street: String,
    @Json(name = "timezone")
    @Embedded
    val timezone: Timezone
)