package com.filkom.mycv2.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Login(
    onLogin: (String, String) -> Unit,
    onDaftar: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(text = "LOGIN")

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        )

        OutlinedTextField(
            value = passwd,
            onValueChange = { passwd = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        )

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp),
            onClick = { onLogin(email, passwd) }
        ) {
            Text("LOGIN")
        }

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onDaftar
        ) {
            Text("DAFTAR")
        }
    }
}

@Preview
@Composable
fun loginPreview() {
    Login({ _, _ -> }, {})
}
