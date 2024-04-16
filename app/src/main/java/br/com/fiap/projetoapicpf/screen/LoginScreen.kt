package br.com.fiap.projetoapicpf.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.projetoapicpf.R
import java.util.Timer
import java.util.TimerTask


@Composable
fun LoginScreen(navController: NavController) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var emailError by remember {
        mutableStateOf(false)
    }
    var showError by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager
        .current
    val tamanhoMaximo = 8

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagemreceita),
                contentDescription = "logo",
                modifier = Modifier
                    .size(180.dp)
                    .padding(top = 50.dp)
            )
        }

        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Spacer(modifier = Modifier.height(250.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.teal_200))
            ) {
                Column(
                    horizontalAlignment = Alignment
                        .CenterHorizontally,
                    modifier = Modifier
                        .run {
                            fillMaxWidth()
                                .background(colorResource(id = R.color.teal_200))
                                .padding(20.dp)
                        }
                ) {
                    Text(
                        text = "Login",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.blue)
                    )
                    Text(
                        text = "Por favor entre com seus dados",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.blue)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            showError =
                                it.isEmpty() || !it.contains("@") // Verifica se o campo está vazio ou não contém "@"
                            showError = it.isEmpty() || !it.contains(".com")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White), // fundo branco
                        label = {
                            Text(text = "Digite o seu e-mail")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.email),
                                contentDescription = "E-mail Icon",
                                modifier = Modifier.size(24.dp) // Define o tamanho do ícone
                            )
                        }
                    )
                    // Mostrar texto de erro apenas se o showError for verdadeiro
                    if (showError) {
                        DisposableEffect(Unit) {
                            val timer = Timer()
                            timer.schedule(object : TimerTask() {
                                override fun run() {
                                    // Define showError como false após 10000 milissegundos (10 segundos)
                                    showError = false
                                    timer.cancel() // Cancela o timer após a execução
                                }
                            }, 10000)

                            onDispose {
                                timer.cancel()
                            }
                        }
                        Text(
                            text = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp),
                            color = Color.Red,
                            textAlign = TextAlign.End
                        )
                        Text(
                            text = "E-mail é obrigatório!",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp),
                            color = Color.Red,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            if (it.length <= tamanhoMaximo) {
                                password = it
                                if (it.length == 8) {
                                    // Se o comprimento da senha for 8, oculta o teclado
                                    focusManager.clearFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White), // Definindo o fundo como branco
                        label = {
                            Text(text = "Digite a sua senha")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.cadeado),
                                contentDescription = "Password Icon",
                                modifier = Modifier.size(24.dp) // Define o tamanho do ícone
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(onClick = {
                        if (email.isEmpty()) {
                            emailError = true
                        }
                        navController.navigate(route = "cpf")
                    },
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue))
                    ) {
                        Text(
                            text = "ENTRAR",
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        verticalAlignment = Alignment
                            .CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(text = "Não tem uma conta?")
                        Button(
                            onClick = {
                                navController.navigate(route = "cadastro")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_200))
                        ) {
                            Text(
                                text = "Inscreva-se",
                                textAlign = TextAlign.Center,
                                color = Color.Blue
                            )
                        }
                    }
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.Bottom, // Alinhamento no rodapé
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.brasao),
                contentDescription = "logo",
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 15.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.Bottom, // Alinhamento no rodapé
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {

            Text(
                text = "Todos os direitos reservados 2024",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Ministério da Fazenda",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Secretaria da Receita Federal do Brasil",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
}
