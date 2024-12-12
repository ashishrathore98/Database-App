package com.example.database

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        //now here we accept all that passed value so we use get here
        val mail = intent.getStringExtra(SignInActivity.KEY1)
        val name = intent.getStringExtra(SignInActivity.KEY2)
        val userId = intent.getStringExtra(SignInActivity.KEY3)


        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val mailText = findViewById<TextView>(R.id.tvMail)
        val idText = findViewById<TextView>(R.id.tvUnique)


        welcomeText.text = "Welcome $name"
        mailText.text = "Mail : $mail"
        idText.text = "UserId : $userId"

    }
}