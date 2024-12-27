package com.btc.expensemanage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.btc.expensetracker.Expense
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Define the Expense class
data class Expense(
    val name: String,
    val description: String,
    val amount: Double
)

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var closeButton: ImageView
    private lateinit var amountInput: EditText
    private lateinit var expenseNameInput: EditText
    private lateinit var expenseDescriptionInput: EditText
    private lateinit var saveButton: Button

    // Define a list to store expenses
    private val expensesList = mutableListOf<Expense>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        // Initialize UI components
        closeButton = findViewById(R.id.closeButton)
        amountInput = findViewById(R.id.amountInput)
        expenseNameInput = findViewById(R.id.expenseNameInput)
        expenseDescriptionInput = findViewById(R.id.expenseDescriptionInput)
        saveButton = findViewById(R.id.saveButton)

        // Load existing expenses
        loadExpenses()

        // Set close button click listener
        closeButton.setOnClickListener {
            finish()  // Close the activity
        }

        // Set save button click listener
        saveButton.setOnClickListener {
            saveExpense()
        }
    }

    private fun saveExpense() {
        val amount = amountInput.text.toString().trim()
        val expenseName = expenseNameInput.text.toString().trim()
        val expenseDescription = expenseDescriptionInput.text.toString().trim()

        // Validate inputs
        if (amount.isEmpty() || expenseName.isEmpty() || expenseDescription.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Validate that the amount is a valid number
        val amountValue = try {
            amount.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a new Expense object
        val expense = Expense(expenseName, expenseDescription, amountValue)

        // Add the expense to the list
        expensesList.add(expense)

        // Save the updated expenses list to SharedPreferences
        saveExpenses()

        // Show a success message
        Toast.makeText(this, "Expense saved successfully", Toast.LENGTH_SHORT).show()

        // Clear the input fields after saving
        amountInput.text.clear()
        expenseNameInput.text.clear()
        expenseDescriptionInput.text.clear()
    }

    private fun saveExpenses() {
        val sharedPreferences = getSharedPreferences("ExpenseTracker", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Convert the list to a JSON string
        val gson = Gson()
        val json = gson.toJson(expensesList)

        // Save the JSON string to SharedPreferences
        editor.putString("expenses", json)
        editor.apply()
        println("Saved Expenses: $json")
    }

    private fun loadExpenses() {
        val sharedPreferences = getSharedPreferences("ExpenseTracker", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("expenses", null)

        // Check if there's existing data and load it
        if (json != null) {
            val type = object : TypeToken<MutableList<Expense>>() {}.type
            expensesList.clear()
            expensesList.addAll(gson.fromJson(json, type))
        }
    }
}
