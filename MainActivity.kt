package com.btc.expensemanage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.btc.expensetracker.HomeActivity
import com.btc.expensetracker.SignUpActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var emailInput: TextInputLayout
    private lateinit var passwordInput: TextInputLayout
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var createAccountButton: Button
    private lateinit var forgotPasswordText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Make sure this matches your XML file name

        // Initialize UI components
        emailInput = findViewById(R.id.textInputEmail)
        passwordInput = findViewById(R.id.textInputPassword)
        editTextEmail = findViewById(R.id.editTextTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextTextPassword)
        loginButton = findViewById(R.id.button)
        createAccountButton = findViewById(R.id.button3)
        forgotPasswordText = findViewById(R.id.textView5)

        // Set click listeners
        loginButton.setOnClickListener {
            handleLogin()
        }

        createAccountButton.setOnClickListener {
            handleCreateAccount()
        }

        forgotPasswordText.setOnClickListener {
            handleForgotPassword()
        }
    }

    private fun handleLogin() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Implement login logic, like API calls or authentication checks
        navigateToHomeActivity()
    }
    private fun navigateToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun handleCreateAccount() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun handleForgotPassword() {
        // Navigate to the Forgot Password Activity or show a dialog
        // Example: Toast message or opening another activity
    }
}