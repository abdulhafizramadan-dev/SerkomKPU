package com.ahr.serkomkpu.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.serkomkpu.ui.component.KpuMenu
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {

    val scrollState = rememberScrollState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(paddingValues)
        ) {
            HomeHeader(electorateCount = 1)
            Spacer(modifier = Modifier.height(42.dp))
            KpuMenu(
                text = "Informasi KPU",
                onClicked = {
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuMenu(
                text = "Entry Data Pemilih",
                onClicked = {
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuMenu(
                text = "Informasi KPU",
                onClicked = {
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewHomeScreen() {
    SerkomKPUTheme {
        Surface {
            HomeScreen()
        }
    }
}
