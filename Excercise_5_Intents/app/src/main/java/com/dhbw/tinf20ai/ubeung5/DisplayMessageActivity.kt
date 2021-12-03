package com.dhbw.tinf20ai.ubeung5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.dhbw.tinf20ai.ubeung5.databinding.ActivityDisplayMessageActitivtyBinding

class DisplayMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayMessageActitivtyBinding

    companion object {
        const val MESSAGE_KEY = "messageKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDisplayMessageActitivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = findViewById<TextView>(R.id.tv_display_text)
        textView.text = intent.getStringExtra(MESSAGE_KEY)
    }
}