package com.example.pizzaparty3.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pizzaparty3.HungerLevel
import com.example.pizzaparty3.PizzaCalculator

class PizzaPartyViewModel : ViewModel() {
    var numPeopleInput by mutableStateOf("")

    var hungerLevel by mutableStateOf(HungerLevel.MEDIUM)

    var totalPizzas by mutableIntStateOf(0)
        private set

    fun calculateNumPizzas() {
        val calc = PizzaCalculator(numPeopleInput.toIntOrNull() ?: 0, hungerLevel)
        totalPizzas = calc.totalPizzas
    }
}