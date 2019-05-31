package apps.gliger.glg.cooker.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object RetrofitFactry {
    const val base_url = "https://randomuser.me"

    fun getService(): NetworkService {
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}