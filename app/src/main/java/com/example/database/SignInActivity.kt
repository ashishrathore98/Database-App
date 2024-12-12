package com.example.database

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1 = "package com.example.database.SignInActivity.mail"
        const val KEY2 = "package com.example.database.SignInActivity.name"
        const val KEY3 = "package com.example.database.SignInActivity.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.userNameEditText)

        signInButton.setOnClickListener {

            val uniqueId = userName.text.toString()
            if (uniqueId.isNotEmpty()){
                 readData(uniqueId)
            }
            else
            {
               Toast.makeText(this,"Please Enter the UserName", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(uniqueId: String) {
      databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {

            //if user exists or not
            if(it.exists()){

                //welcome user in your APP, with intent and also pass
                val email = it.child("mail").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value

                //now pass intent for new welcome screen
                val intentWelcome = Intent(this,WelcomeActivity::class.java)
                intentWelcome.putExtra(KEY1,email.toString())
                intentWelcome.putExtra(KEY2,name.toString())
                intentWelcome.putExtra(KEY3,userId.toString())
                startActivity(intentWelcome)




            }else{
                Toast.makeText(this,"User does not exists", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
        }
    }
