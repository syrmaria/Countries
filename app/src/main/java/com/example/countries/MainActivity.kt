package com.example.countries

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.countries.view.CountryDataView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CountriesViewModel
    private lateinit var searchView: AutoCompleteTextView
    private lateinit var dataView: CountryDataView
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.search_view)
        dataView = findViewById(R.id.data_view)
        progress = findViewById(R.id.loading)

        dataView.visibility = View.INVISIBLE

        viewModel = ViewModelProvider(this, CountriesViewModel.factory()).get(CountriesViewModel::class.java)
        viewModel.countries.observe(this) {
            setSearchView(it)
        }
        viewModel.loading.observe(this) {
            progress.isVisible = it
        }
        viewModel.error.observe(this) {
            it?.let { Snackbar.make(searchView, it, Snackbar.LENGTH_SHORT).show() }
        }

        if (savedInstanceState == null) {
            viewModel.getCountryNames()
        }
    }

    private fun setSearchView(countryList: List<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            countryList.toMutableList())

        searchView.setAdapter(adapter)
        searchView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            adapter.getItem(position)?.let { setCountryData(it) }
        }
    }

    private fun setCountryData(name: String) = viewModel.getDetails(name)?.let {
        dataView.visibility = View.VISIBLE
        dataView.setData(it)
    }
}