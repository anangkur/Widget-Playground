package com.anangkur.widgetplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.widgetplayground.databinding.ActivityMainBinding
import com.anangkur.widgetplayground.features.edittext.EditTextActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEditText.setOnClickListener {
            EditTextActivity.startActivity(this)
        }
    }
}