package br.com.fiap.projetoapicpf.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun CadastroScreen(navController: NavController) {
    var nome by remember {
        mutableStateOf("")
    }
    var dtNasc by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var cpf by remember {
        mutableStateOf("")
    }

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

        Spacer(modifier = Modifier.height(200.dp))

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
                }) {
                Text(
                    text = "Cadastro",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.blue)
                )
                Text(
                    text = "Insira os dados abaixo",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.blue)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nome,
                    onValueChange = {
                        nome = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White), // Definindo o fundo como branco
                    label = {
                        Text(text = "Digite seu nome")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = dtNasc,
                    onValueChange = {
                        dtNasc = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White), // Definindo o fundo como branco
                    label = {
                        Text(text = "Digite seu CPF")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.AccountBox, contentDescription = "")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White), // Definindo o fundo como branco
                    label = {
                        Text(text = "Digite seu Email")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = cpf,
                    onValueChange = {
                        cpf = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White), // Definindo o fundo como branco
                    label = {
                        Text(text = "Digite sua senha")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "")
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        navController.navigate(route = "login")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_200))
                ) {
                    Text(
                        text = "Entrar",
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }

                Button(
                    onClick = {
                        navController.navigate(route = "login")
                    },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue))
                ) {
                    Text(
                        text = "CADASTRAR",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
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