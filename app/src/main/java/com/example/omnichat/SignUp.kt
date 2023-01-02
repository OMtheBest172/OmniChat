package com.example.omnichat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import android.app.Activity
import android.app.ProgressDialog

import android.net.Uri
import android.util.Log
import android.view.View
import android.view.View.OnClickListener

import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUp : AppCompatActivity() {

     lateinit var firebaseAuth: FirebaseAuth
    private lateinit var btnSignUp : Button
    private lateinit var edtName : EditText
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        database = Firebase.database.reference
        firebaseAuth = FirebaseAuth.getInstance()
        edtName = findViewById(R.id.edt_name)
        btnSignUp = findViewById(R.id.btn_signup)


        btn_signup.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser() {
        val email = edt_email.text.toString()
        val password = edt_password.text.toString()
        val name = edt_name.text.toString()
        data class User(val username: String? = null, val email: String? = null)


        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email and Password Can't be blank", Toast.LENGTH_SHORT).show()
            return
        }



        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    addUserToDatabase(name, email, firebaseAuth.currentUser?.uid!!)
                    Toast.makeText(this, "SignUp Sucessfull", Toast.LENGTH_SHORT).show()
                    btnSignUp.setOnClickListener {
                        val intent = Intent( this,MainActivity :: class.java)
                        finish()
                        startActivity(intent)
                    }


                    }
                     else {
                        Toast.makeText(this, "Error creating a User", Toast.LENGTH_SHORT).show()


                 }
                }


            }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        data class User(val username: String? = null, val email: String? = null)
        database = FirebaseDatabase.getInstance().getReference()
        database.child("user").child(uid).setValue(User(name,email,uid))

    }



}
