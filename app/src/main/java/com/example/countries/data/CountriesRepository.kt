package com.example.countries.data

import com.example.countries.di.DaggerServiceComponent
import com.example.countries.models.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.coroutines.CoroutineContext

/**

 * @author Сырова Мария
 */
class CountriesRepository(
    private val ioContext: CoroutineContext = Dispatchers.IO
) {
    private val api: WebApi = DaggerServiceComponent.builder().build().getWebApi()
    private lateinit var countryList: List<Country>

    suspend fun getCountryNames() = withContext(ioContext) {
        getData()
        countryList.map { it.name }
    }

    fun getCountryDetails(name: String): Country? {
        val item = countryList.find { it.name == name }
        return item
    }

    private fun getData() {
        val response = api.getCountries().execute()
        if (!response.isSuccessful) throw IOException("Request failed")
        countryList = response.body() ?: emptyList()
    }
}