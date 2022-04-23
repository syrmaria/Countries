package com.example.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.countries.data.CountriesRepository
import com.example.countries.models.Country
import kotlinx.coroutines.launch
import java.io.IOException

/**

 * @author Сырова Мария
 */
class CountriesViewModel(private val repo: CountriesRepository) : ViewModel() {
    val countries = MutableLiveData<List<String>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()

    fun getCountryNames() = viewModelScope.launch {
        loading.value = true
        try {
            countries.value = repo.getCountryNames()
        } catch (e: IOException) {
            error.value = e.localizedMessage
            error.value = null
        }
        loading.value = false
    }

    fun getDetails(countryName: String): Country? {
        return repo.getCountryDetails(countryName)
    }

    companion object {
        fun factory() = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CountriesViewModel(CountriesRepository()) as T
            }
        }
    }
}