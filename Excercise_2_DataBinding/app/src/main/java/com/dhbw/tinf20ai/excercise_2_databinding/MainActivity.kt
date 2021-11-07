package com.dhbw.tinf20ai.excercise_2_databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.ObservableField
import com.dhbw.tinf20ai.excercise_2_databinding.databinding.ActivityMainBinding
import kotlin.properties.ObservableProperty

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.viewModel = ViewModel(ObservableField(false), ObservableField(getMemeButtonText(false)))
        setContentView(binding.root)

        binding.btnShowMeme.setOnClickListener {
            val newShowButtonValue = !(binding.viewModel?.showMeme?.get() ?: false)

            binding.viewModel?.showMeme?.set(newShowButtonValue)
            binding.viewModel?.btnText?.set(getMemeButtonText(newShowButtonValue))

            // instead of using ObservableFields for the ViewModel; we could also call
            // binding.invalidateAll()
            // to refresh the bindings
        }
    }

    private fun getMemeButtonText(show: Boolean): String {
        return if (show) resources.getString(R.string.btn_hide_meme_text)
        else resources.getString(R.string.btn_show_meme_text)
    }

}



