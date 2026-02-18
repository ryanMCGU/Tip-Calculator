package com.example.pizzaparty3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pizzaparty3.ui.PizzaPartyScreen
import com.example.pizzaparty3.ui.theme.PizzaParty3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzaParty3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PizzaPartyScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}