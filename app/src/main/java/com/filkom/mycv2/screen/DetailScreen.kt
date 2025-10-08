package com.filkom.mycv2.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun detail(
    nim: String,
    nama: String,
    email: String,
    alamat: String,
    onDaftar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "DETAIL",
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(text = "NIM: $nim", fontSize = 14.sp, modifier = Modifier.padding(vertical = 10.dp))
        Text(text = "Nama: $nama", fontSize = 14.sp, modifier = Modifier.padding(vertical = 10.dp))
        Text(text = "Email: $email", fontSize = 14.sp, modifier = Modifier.padding(vertical = 10.dp))
        Text(text = "Alamat: $alamat", fontSize = 14.sp, modifier = Modifier.padding(vertical = 10.dp))

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
fun detailPreview() {
    detail(
        nim = "235150401111036",
        nama = "Rafi Cahya Prastawa",
        email = "raficahya@student.ub.ac.id",
        alamat = "Araya",
        onDaftar = {}
    )
}
