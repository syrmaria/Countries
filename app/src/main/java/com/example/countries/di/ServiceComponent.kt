package com.example.countries.di

import com.example.countries.data.WebApi
import dagger.Component

/**

 * @author Сырова Мария
 */
@Component(modules = [ServiceModule::class])
open interface ServiceComponent {
    fun getWebApi(): WebApi
}