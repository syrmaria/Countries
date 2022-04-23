package com.example.countries.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**

 * @author Сырова Мария
 */
@Serializable
data class Country(
    @SerialName("flags")
    val flag: Flag,
    @SerialName("name")
    val name: String,
    @SerialName("capital")
    val capital: String? = null,
    @SerialName("region")
    val region: String,
    @SerialName("independent")
    val isIndependent: Boolean
)

@Serializable
data class Flag(
    @SerialName("svg")
    val svgUri: String,
    @SerialName("png")
    val pngUri: String
)
