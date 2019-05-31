package apps.gliger.glg.cooker.network

import apps.gliger.glg.cooker.model.PeopleResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NetworkService {
    @GET("/api/")
    fun getPeople():Deferred<PeopleResponse>
}