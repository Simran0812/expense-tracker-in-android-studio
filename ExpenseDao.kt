@file:Suppress("AndroidUnresolvedRoomSqlReference")

package com.btc.expensetracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.btc.expensemanage.Expense

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY id DESC")
    suspend fun getAllExpenses():List<Expense>
}