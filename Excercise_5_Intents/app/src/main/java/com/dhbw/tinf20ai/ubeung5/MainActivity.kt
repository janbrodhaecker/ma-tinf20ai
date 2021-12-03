package com.dhbw.tinf20ai.ubeung5

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhbw.tinf20ai.ubeung5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExplicit.setOnClickListener {
            val explicitIntent = Intent(this, DisplayMessageActivity::class.java).apply {
                putExtra(DisplayMessageActivity.MESSAGE_KEY, binding.etInput.text.toString())
            }
            startActivity(explicitIntent)
        }

        binding.btnImplicit.setOnClickListener {
            val implicitIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(DisplayMessageActivity.MESSAGE_KEY, binding.etInput.text.toString())
            }

            try {
                startActivity(implicitIntent)
            } catch (ex: ActivityNotFoundException) {
                // TODO: handle exception
            }
        }
    }
}