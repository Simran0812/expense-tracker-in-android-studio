package com.btc.expensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.btc.expensemanage.AddExpenseActivity
import com.btc.expensemanage.MyExpensesActivity
import com.btc.expensemanage.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val myExpenseButton = findViewById<Button>(R.id.myExpenseButton)
        myExpenseButton.setOnClickListener {
            // Create an intent to start MyExpensesActivity
            val intent = Intent(this, MyExpensesActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for the Add Expense button
        val addExpenseButton = findViewById<Button>(R.id.addExpenseButton)
        addExpenseButton.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for the My Expenses button
//        val myExpenseButton = findViewById<Button>(R.id.myExpenseButton)
//        myExpenseButton.setOnClickListener {
//            val intent = Intent(this, ExpensesActivity::class.java)
//            startActivity(intent)
//        }
    }
}