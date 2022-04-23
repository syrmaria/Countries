package com.example.countries.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.countries.R
import com.example.countries.models.Country

/**

 * @author Сырова Мария
 */
class CountryDataView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val flag: ImageView
    private val region: TextView
    private val capital: TextView

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.country_view, this, true)

        flag = findViewById(R.id.flag)
        region = findViewById(R.id.region)
        capital = findViewById(R.id.capital)
    }

    fun setData(country: Country) {
        region.text = country.region
        country.capital?.let { capital.text = it }
        Glide.with(flag.context).load(country.flag.pngUri).centerCrop().into(flag)
    }

}