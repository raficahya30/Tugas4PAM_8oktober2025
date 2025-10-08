package com.filkom.mycv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filkom.mycv2.screen.Login
import com.filkom.mycv2.screen.detail
import com.filkom.mycv2.screen.daftar
import com.filkom.mycv2.ui.theme.MyCV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCV2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 👇 di sini parameter 'it' dipakai
                    NavigationApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavigationApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier // 👈 ini menyalurkan padding dari Scaffold
    ) {
        composable("login") {
            Login(
                onLogin = { navController.navigate("detail") },
                onDaftar = { navController.navigate("daftar") }
            )
        }
        composable("detail") {
            detail(
                onDaftar = { navController.navigate("daftar") }
            )
        }
        composable("daftar") {
            daftar(
                onSimpan = { navController.navigate("detail") }
            )
        }
    }
}
