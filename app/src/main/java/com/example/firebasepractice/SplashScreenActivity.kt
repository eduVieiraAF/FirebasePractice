package com.example.firebasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashFire = findViewById<ImageView>(R.id.IM_fire)
        val splashHosting = findViewById<ImageView>(R.id.IM_database)

        splashFire.alpha = 0f
        splashFire.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        splashHosting.alpha = 0f
        splashHosting.animate().setDuration(1500).alpha(1f).withEndAction {

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}