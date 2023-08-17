package com.ahr.serkomkpu.presentation.information

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.serkomkpu.R
import com.ahr.serkomkpu.ui.component.KpuTopAppBar
import com.ahr.serkomkpu.ui.component.KpuTopAppBarType
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.poppinsFontFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@ExperimentalMaterial3Api
@Composable
fun InformationScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {

    val scrollState = rememberScrollState()

    val navigateUp: () -> Unit = { navigator.navigateUp() }

    val generalElectionStages = remember {
        listOf(
            "Penyusunan Peraturan KPU",
            "Pemutakhiran data pemilih dan penyusunan pemilih",
            "Pendaftaran dan verifikasi peserta pemilu",
            "Penetapan peserta pemilu",
            "Masa kampanye pemilu",
            "Presiden dan wakil presiden",
            "Anggota DPR, DPRD Provinsi dan DPRD Kabupaten",
            "Anggota DPD",
            "Penetapan jumlah kursi dan penetapan dapil",
            "Masa tenggang",
            "Pemungutan Suara",
            "Perhitungan Suara",
            "Rekapitulasi hasil perhitungan suara"
        )
    }

    Scaffold(
        topBar = {
            KpuTopAppBar(
                title = "Informasi KPU",
                onNavIconClicked = navigateUp,
                type = KpuTopAppBarType.Detail
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(20.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_tahapan_pemilu),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(335.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Tahapan Pemilihan Umum",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333538),
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            generalElectionStages.forEachIndexed { index, stage ->
                Text(
                    text = "${index + 1}. $stage",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF333538),
                    )
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewInformationScreen() {
    SerkomKPUTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            InformationScreen()
        }
    }
}
