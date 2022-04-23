package com.example.countries.di

import com.example.countries.data.WebApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

/**

 * @author Сырова Мария
 */
@Module
class ServiceModule {
    @Provides
    fun webApi(): WebApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build().create(WebApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://restcountries.com/"
    }
}