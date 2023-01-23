package com.example.animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animaciones.ui.theme.AnimacionesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Login()
        }
    }
}

@Composable
@Preview
fun Login() {
    Screen {
        var user by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }
        var validationMessage by remember { mutableStateOf("") }
        var passVisible by remember { mutableStateOf(false) }

        val loginEnabled = user.isNotEmpty() && pass.isNotEmpty()
        val isError = validationMessage.isNotEmpty()
        val login = { validationMessage = validateLogin(user, pass) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            TextField(
                value = user,
                onValueChange = { user = it },
                isError = isError,
                label = { Text(text = "User") },
                placeholder = { Text(text = "Debe ser email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            TextField(
                value = pass,
                onValueChange = { pass = it },
                isError = isError,
                label = { Text(text = "Pass") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions { login() },
                visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconToggleButton(
                        checked = passVisible,
                        onCheckedChange = { passVisible = it }) {
                        Icon(
                            imageVector = if (passVisible) Default.VisibilityOff else Default.Visibility,
                            contentDescription = null
                        )
                    }
                }
            )
            if (validationMessage.isNotEmpty()) {
                Text(text = validationMessage, color = MaterialTheme.colors.error)
            }
            Button(
                onClick = login,
                enabled = loginEnabled
            ) {
                Text(text = "LOGIN")
            }
        }
    }
}

fun validateLogin(user: String, pass: String): String = when {
    !user.contains('@') -> "Usuario no valido"
    pass.length < 5 -> "Contraseña debe tener al menos 5 caracteres"
    else -> "Success"
}


@Composable
fun Screen(content: @Composable () -> Unit) {
    AnimacionesTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
            content = content
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimacionesTheme {
        Greeting("Android")
    }
}