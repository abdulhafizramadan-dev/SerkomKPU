package com.ahr.serkomkpu.presentation.detail_electorate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahr.serkomkpu.ui.component.KpuTopAppBar
import com.ahr.serkomkpu.ui.component.KpuTopAppBarType
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.poppinsFontFamily
import com.ahr.serkomkpu.util.decodeStringBase64ToBitMap
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@ExperimentalMaterial3Api
@Composable
fun DetailElectorateScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    detailElectorateViewModel: DetailElectorateViewModel = hiltViewModel(),
    id: Int
) {
    val scrollState = rememberScrollState()
    val detailElectorateScreenUiState by detailElectorateViewModel.detailElectorateScreenUiState.collectAsState()

    val electorate = detailElectorateScreenUiState.electorate

    LaunchedEffect(key1 = Unit) {
        detailElectorateViewModel.getElectorate(id)
    }

    val navigateUp: () -> Unit = { navigator.navigateUp() }

    Scaffold(
        topBar = {
            KpuTopAppBar(
                title = "Detail Data Pemilih",
                onNavIconClicked = navigateUp,
                type = KpuTopAppBarType.Detail
            )
        }
    ) { paddingValues ->
        if (electorate != null) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
                    .padding(20.dp),
            ) {
                Image(
                    bitmap = electorate.image.decodeStringBase64ToBitMap(),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(8.dp))
                        .width(152.dp)
                        .height(170.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(42.dp))
                Text(
                    text = "Informasi RIncian Data Pemilih",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF333538),
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Nama Lengkap",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(1.5f)
                    )
                    Text(
                        text = ": ${electorate.name}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(2f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "NIK",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(1.5f)
                    )
                    Text(
                        text = ": ${electorate.nik}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(2f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "No Telepon",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(1.5f)
                    )
                    Text(
                        text = ": ${electorate.phone}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(2f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Jenis Kelamin",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(1.5f)
                    )
                    Text(
                        text = ": ${electorate.gender}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(2f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Tanggal Pendataan",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(1.5f)
                    )
                    Text(
                        text = ": ${electorate.dateCollectionDate}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(2f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Alamat",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(1.5f)
                    )
                    Text(
                        text = ": ${electorate.address}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333538),
                        ),
                        modifier = Modifier.weight(2f)
                    )
                }

            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDetailElectorate() {
    SerkomKPUTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DetailElectorateScreen(
                id = 1
            )
        }
    }
}
