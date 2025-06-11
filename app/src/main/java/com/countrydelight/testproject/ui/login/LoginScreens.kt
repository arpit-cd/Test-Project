package com.countrydelight.testproject.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import com.countrydelight.testproject.R
import kotlinx.coroutines.delay

@Composable
fun LoginFlow() {
    var showInput by remember { mutableStateOf(false) }
    val logoOffset by animateDpAsState(
        targetValue = if (showInput) (-200).dp else 0.dp,
        animationSpec = tween(durationMillis = 600)
    )
    LaunchedEffect(Unit) {
        delay(1000)
        showInput = true
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.Center)
                .offset(y = logoOffset)
        )
        AnimatedVisibility(
            visible = showInput,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            MobileInputBox()
        }
    }
}

@Composable
fun MobileInputBox() {
    var number by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = number,
            onValueChange = { input ->
                val digits = input.filter { it.isDigit() }
                if (digits.length <= 10) number = digits
            },
            label = { Text("Mobile Number") },
            leadingIcon = { Text("+91") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text("Privacy Policy")
            Text(" | ")
            Text("Terms & Conditions")
        }
    }
}
