package com.example.omnichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.annotations.PublicApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var btnLogin : Button
    private lateinit var btnSignUp : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //supportActionBar?.hide()

        btnLogin = findViewById(R.id.btn_login)
        btnSignUp = findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val intent = Intent( this,SignUp :: class.java)
            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        btn_login.setOnClickListener {
            login()
            //this is the login button
            //omthebest172
        }


        }

    private fun login() {
        val email = edt_email.text.toString()
        val password = edt_password.text.toString()

        if (email.isBlank() || password.isBlank() ) {
            Toast.makeText(this, "Email and Password Can't be blank", Toast.LENGTH_SHORT ).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
                    Toast.makeText(this, "Login Sucessfull", Toast.LENGTH_SHORT).show()
                    btnLogin.setOnClickListener {
                        val intent = Intent( this,MainActivity :: class.java)
                        finish()
                        startActivity(intent)
                    }
                }
                else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT ).show()
                }
            }
    }
}

