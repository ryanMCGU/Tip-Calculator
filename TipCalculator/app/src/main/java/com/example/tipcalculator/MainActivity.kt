package com.example.tipcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.round

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("TipCalculatorV3", maxLines = 1) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Blue,
                                titleContentColor = Color.White
                            )
                        )
                    },modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalcMain(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalcMain(modifier: Modifier = Modifier) {
    var bill by remember { mutableDoubleStateOf(0.0) }
    var tip by remember { mutableIntStateOf(0) }
    var totalTip by remember { mutableDoubleStateOf(0.0) }
    var total by remember { mutableDoubleStateOf(0.0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Text(
                text = "Bill",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = modifier.background(color = Color.LightGray).padding(10.dp, 10.dp,48.dp, 10.dp)
            )
            TextField(
                value = "$bill",
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 32.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { bill = it.toDouble() },
                modifier = modifier
            )
        }
        Row() {
            Text(
                text = "Tip (%)",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = modifier.background(color = Color.LightGray).padding(10.dp, 10.dp,48.dp, 10.dp)
            )
            TextField(
                value = "$tip",
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 32.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { tip = it.toInt() },
                modifier = modifier
            )
        }
        HorizontalDivider(thickness = 8.dp, color = Color.Red)
        Row() {
            Text(
                text = "Tip ($) ",
                fontSize = 32.sp,
                modifier = Modifier.background(color = Color.LightGray).padding(10.dp, 10.dp,48.dp, 10.dp)
            )
            Text(
                text = "$$totalTip",
                fontSize = 32.sp,
                modifier = Modifier.background(color = Color.Green).padding(48.dp, 10.dp,48.dp, 10.dp)
            )
        }
        Row() {
            Text(
                text = "Total ",
                fontSize = 32.sp,
                modifier = Modifier.background(color = Color.LightGray).padding(10.dp, 10.dp,48.dp, 10.dp)
            )
            Text(
                text = "$$total",
                fontSize = 32.sp,
                modifier = Modifier.background(color = Color.Green).padding(48.dp, 10.dp,48.dp, 10.dp)
            )
        }
        val context = LocalContext.current
        Button(
            onClick = {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                totalTip = BigDecimal((bill * (tip.toDouble() / 100))).setScale(2, RoundingMode.HALF_UP).toDouble()
                total = BigDecimal((bill + totalTip)).setScale(2, RoundingMode.HALF_UP).toDouble()
            },
            colors = ButtonColors(
                containerColor = Color.Green,
                contentColor = Color.Black,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.DarkGray
            )
        ) {
            Text(text = "CALCULATE", fontSize = 32.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        CalcMain()
    }
}