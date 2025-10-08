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
fun detail(onDaftar: () -> Unit) {
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
        Text(text = "NIM: 22000123", fontSize = 14.sp, modifier = Modifier.padding(vertical = 10.dp))
        Text(text = "Nama: Budi Setiawan", fontSize = 14.sp, modifier = Modifier.padding(vertical = 10.dp))
        Text(text = "Email: budi@gmail.com", fontSize = 14.sp, modifier = Modifier.padding(vertical = 10.dp))
        Text(text = "Alamat: Malang", fontSize = 14.sp, modifier = Modifier.padding(vertical = 10.dp))

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
    detail({})
}
