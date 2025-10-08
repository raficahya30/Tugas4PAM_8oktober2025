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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        modifier = modifier
    ) {
        composable("login") {
            Login(
                onLogin = { navController.navigate("detail/12345/Rafi/test@mail.com/Malang") },
                onDaftar = { navController.navigate("daftar") }
            )
        }

        // ✅ Tambahkan argumen pada route detail
        composable(
            route = "detail/{nim}/{nama}/{email}/{alamat}",
            arguments = listOf(
                navArgument("nim") { type = NavType.StringType },
                navArgument("nama") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("alamat") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") ?: ""
            val nama = backStackEntry.arguments?.getString("nama") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val alamat = backStackEntry.arguments?.getString("alamat") ?: ""

            detail(
                nim = nim,
                nama = nama,
                email = email,
                alamat = alamat,
                onDaftar = { navController.navigate("daftar") }
            )
        }

        composable("daftar") {
            daftar(
                onSimpan = { nim, nama, email, alamat ->
                    // Kirim data ke halaman detail
                    navController.navigate("detail/$nim/$nama/$email/$alamat")
                }
            )
        }
    }
}
