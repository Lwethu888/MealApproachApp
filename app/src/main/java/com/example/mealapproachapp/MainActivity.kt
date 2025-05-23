package com.example.mealapproachapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Reference to UI elements
        val editTextTime = findViewById<EditText>(R.id.editTextTime)
        val buttonDisplayMeal = findViewById<Button>(R.id.buttonDisplayMeal)
        val buttonReset = findViewById<Button>(R.id.buttonReset)
        val textViewMealSuggestion = findViewById<TextView>(R.id.textViewMealSuggestion)

        // Code Reference: https://tms-outsource.com/blog/posts/how-to-build-android-apps-with-kotlin/
        // Button click event for meal suggestion
        buttonDisplayMeal.setOnClickListener {
            val timeOfDay = editTextTime.text.toString().trim().lowercase()

            if (timeOfDay.isNotEmpty()) {
                val meal = getMealSuggestion(timeOfDay)
                textViewMealSuggestion.text = "Suggested Meal: $meal"
                Log.d("MealSuggestion", "User entered: $timeOfDay, Suggested meal: $meal")

            } else {
                textViewMealSuggestion.text = "Please enter a valid time of day."
                Toast.makeText(this, "Invalid input. Try: Morning, Afternoon.", Toast.LENGTH_SHORT)
                    .show()
                Log.e("MealSuggestion", "Invalid input: $timeOfDay")
            }
        }
        // Button click event for resetting input
        buttonReset.setOnClickListener {
            editTextTime.text.clear()
            textViewMealSuggestion.text = "Meal will appear here"
            Log.d("MealSuggestion", "Input and suggestion reset")
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Code Reference: https://stackoverflow.com/questions/45108239/how-to-create-a-button-in-kotlin-that-opens-a-new-activity-android-studio

    // Function to determine meal suggestion based on time of day
   private fun getMealSuggestion(timeOfDay: String): String {
        return when (timeOfDay) {
            "morning" -> "Eggs and bacon (Breakfast)"
            "mid-morning" -> "Fruit Salad (Mid-Morning Snack)"
            "afternoon" -> "Chicken wrap (Lunch)"
            "afternoon snack", "mid-afternoon" -> "Chips (Afternoon Snack)"
            "dinner" -> "Pap and braai meat (Main Course)"
            "after dinner", "dessert" -> "Cake (Dessert)"
            else -> "Invalid time of day! Please try: Morning, Mid-Morning, Afternoon, Dinner, Dessert."
        }
    }

}