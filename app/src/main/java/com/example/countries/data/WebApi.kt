package com.example.countries.data

import com.example.countries.models.Country
import retrofit2.Call
import retrofit2.http.GET

/**

 * @author Сырова Мария
 */
interface WebApi {
    @GET("v2/all?fields=name,capital,region,flags")
    fun getCountries(): Call<List<Country>>
}