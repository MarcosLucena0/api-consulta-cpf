package br.com.fiap.projetoapicpf.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.projetoapicpf.R
import br.com.fiap.projetoapicpf.model.ComprovanteSituacaoCpf
import br.com.fiap.projetoapicpf.model.ResponseData
import br.com.fiap.projetoapicpf.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CpfScreen(navController: NavController) {

    var cpf by remember{
        mutableStateOf("")
    }
    var dataNasc by remember{
        mutableStateOf("")
    }

    var listaCpfState by remember {
        mutableStateOf(listOf<ComprovanteSituacaoCpf>())
    }

    // Estado para armazenar os dados recebidos da API com novo model
    val responseDataState =
        remember { mutableStateOf<ResponseData?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            //--------Header------------
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .background(colorResource(id = R.color.blue))
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.receita_federal),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(150.dp)
                    )
                    Button(
                        onClick = {navController.navigate(route = "login")},
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue)),
                    ) {
                        Text(
                            text = "",
                            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))

                //--------Formulario-----------
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(370.dp)
                            .background(colorResource(id = R.color.teal_200))
                    ) {
                        Column(
                            modifier = Modifier
                                .run {
                                    fillMaxWidth()
                                        .background(colorResource(id = R.color.teal_200))
                                        .padding(20.dp)
                            }
                        ) {
                            Text(
                                text = "Consultar CPF",
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.blue)
                            )
                            Text(
                                text = "Por favor, informe seus dados",
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.blue)
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            OutlinedTextField(
                                value = cpf,
                                onValueChange = {cpf = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White),
                                placeholder = {
                                    Text(text = "CPF")
                                },
                                label = {
                                    Text(text = "CPF")
                                },
                                shape = RoundedCornerShape(16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = dataNasc,
                                onValueChange = {dataNasc = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White),
                                placeholder = {
                                    Text(
                                        text = "Data de Nascimento"
                                    )
                                },
                                label = {
                                    Text(text = "Data de Nascimento")
                                },
                                shape = RoundedCornerShape(16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                            )
                            Text(text = "Digite a data no formato ISO: YYYY-MM-DD",
                                color = Color.Black,
                                modifier = Modifier.padding(10.dp),
                                fontSize = 13.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    val call = RetrofitFactory().getComprovanteSituacaoCpfService().getComprovanteByCpf(
                                        //mock de dados para validação
                                        cpf = cpf,
                                        dataNascimento = dataNasc
                                    )
                                    call.enqueue(object : Callback<ResponseData> {
                                        override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                                            if (response.isSuccessful) {
                                                val responseData = response.body()
                                                responseDataState.value = responseData // Atualiza o estado com os dados recebidos
                                                Log.i("FIAP", "onResponse: $responseData")
                                            } else {
                                                Log.e("FIAP", "onResponse: Error: ${response.code()}")
                                            }
                                        }

                                        override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                                            Log.e("FIAP", "onFailure: ${t.message}", t)
                                        }
                                    })
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue))
                            ) {
                                Text(
                                    text = "Consultar",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Comprovante situação cadastral CPF",
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    //--------Resultado----------
                    LazyColumn(){
                        items(1){
                            // Exibe os dados dentro do compositor CardConsulta se estiverem disponíveis
                            responseDataState.value?.let { responseData ->
                                CardConsulta(
                                    cpf = responseData.data[0].cpf,
                                    nome = responseData.data[0].nome,
                                    dataNascimento = responseData.data[0].dataNascimento,
                                    situacaoCadastral = responseData.data[0].situacaoCadastral,
                                    dataInscricao = responseData.data[0].dataInscricao,
                                    digitoVerificador = responseData.data[0].consultaDigitoVerificador
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Text(text: String, icon: Unit) {

}


@Composable
fun CardConsulta(
    cpf: String,
    nome: String,
    dataNascimento: String,
    situacaoCadastral: String,
    dataInscricao: String,
    digitoVerificador: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(31, 59, 111))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "N do CPF: $cpf",
                color = Color.White
            )
            Text(
                text = "Nome: $nome",
                color = Color.White
            )
            Text(
                text = "Data de Nascimento: $dataNascimento",
                color = Color.White
            )
            Text(
                text = "Situação Cadastral: $situacaoCadastral",
                color = Color.White
            )
            Text(
                text = "Data de Inscrição: $dataInscricao",
                color = Color.White
            )
            Text(
                text = "Digito Verificador: $digitoVerificador",
                color = Color.White
            )
        }
    }
}