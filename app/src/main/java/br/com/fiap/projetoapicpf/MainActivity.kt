package br.com.fiap.projetoapicpf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.projetoapicpf.screen.CadastroScreen
import br.com.fiap.projetoapicpf.screen.CpfScreen
import br.com.fiap.projetoapicpf.screen.LoginScreen
import br.com.fiap.projetoapicpf.ui.theme.ProjetoAPICPFTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetoAPICPFTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login"){
                        composable(route = "login"){
                            LoginScreen(navController)
                        }
                        composable(route = "cadastro"){
                            CadastroScreen(navController)
                        }
                        composable(route = "cpf"){
                            CpfScreen(navController)
                        }
                    }
                    //CpfScreen()
                    //LoginScreen()
                    //CadastroScreen()
                }
            }
        }
    }
}

