package com.btc.expensetracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.btc.expensemanage.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var signUpButton: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        nameInput = findViewById(R.id.editTextName)
        emailInput = findViewById(R.id.editTextEmail)
        passwordInput = findViewById(R.id.editTextPassword)
        signUpButton = findViewById(R.id.buttonSignUp)

        databaseHelper = DatabaseHelper(this)

        signUpButton.setOnClickListener {
            handleSignUp()
        }
    }

    private fun handleSignUp() {
        val name = nameInput.text.toString()
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val result = databaseHelper.signUpUser(name, email, password)

        if (result != -1L) {
            Toast.makeText(this, "Sign-up successful", Toast.LENGTH_SHORT).show()
            finish() // Close the signup activity
        } else {
            Toast.makeText(this, "Sign-up failed, email might already be used", Toast.LENGTH_SHORT).show()
        }
    }
}