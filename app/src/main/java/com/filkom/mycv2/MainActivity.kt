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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.filkom.mycv2.screen.Login
import com.filkom.mycv2.screen.daftar
import com.filkom.mycv2.screen.detail
import com.filkom.mycv2.ui.theme.MyCV2Theme
import com.filkom.mycv2.viewmodel.MainViewModel
import com.filkom.mycv2.viewmodel.MainViewModelFactory
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(this))
            MyCV2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationApp(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationApp(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            Login(
                onLogin = { email, password ->
                    runBlocking {
                        val success = viewModel.login(email, password)
                        if (success) {
                            val user = viewModel.userData.value
                            navController.navigate("detail/${user.nim}/${user.nama}/${user.email}/${user.alamat}")
                        }
                    }
                },
                onDaftar = { navController.navigate("daftar") }
            )
        }

        composable("daftar") {
            daftar(
                onSimpan = { nim, nama, email, alamat, password ->
                    viewModel.registerUser(nim, nama, email, alamat, password)
                    navController.navigate("detail/$nim/$nama/$email/$alamat")
                }
            )
        }

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
    }
}
