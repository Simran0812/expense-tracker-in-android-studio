package com.btc.expensemanage

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.btc.expensemanage.Expense
import com.btc.expensemanage.ExpenseAdapter
import com.btc.expensemanage.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyExpensesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var expenseAdapter: ExpenseAdapter
    private lateinit var expenseList: MutableList<Expense>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_expenses)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Load the expenses from SharedPreferences
        expenseList = loadExpenses()

        expenseAdapter = ExpenseAdapter(expenseList)
        recyclerView.adapter = expenseAdapter
    }

    private fun loadExpenses(): MutableList<Expense> {
        val sharedPreferences = getSharedPreferences("ExpenseTracker", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("expenses", null)

        // Check if there's existing data and load it
        return if (json != null) {
            val type = object : TypeToken<MutableList<Expense>>() {}.type
            gson.fromJson(json, type) ?: mutableListOf()
        } else {
            mutableListOf() // Return an empty list if no data found
        }
    }
}