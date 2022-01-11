package com.example.firebasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasepractice.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java).apply{}
            startActivity(intent)
        }

        binding.btnReadData.setOnClickListener {

            val intent = Intent(this, ReadData::class.java).apply {}
            startActivity(intent)
        }

        binding.btnUpdate2.setOnClickListener {

            val intent = Intent(this, UpdateData::class.java).apply {}
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {

            val intent = Intent(this, Delete::class.java).apply {}
            startActivity(intent)
        }
    }
}