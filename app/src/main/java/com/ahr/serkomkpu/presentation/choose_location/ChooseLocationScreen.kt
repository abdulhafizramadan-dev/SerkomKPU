package com.ahr.serkomkpu.presentation.choose_location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ahr.serkomkpu.ui.component.KpuTopAppBar
import com.ahr.serkomkpu.ui.component.KpuTopAppBarType
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.poppinsFontFamily
import com.ahr.serkomkpu.ui.theme.redKpu

@ExperimentalMaterial3Api
@Composable
fun ChooseLocationScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            KpuTopAppBar(
                title = "Pilih Lokasi",
                type = KpuTopAppBarType.Detail,
            )
        },
        modifier = modifier,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { },
                containerColor = redKpu
            ) {
                Text(
                    text = "Current Location",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Dummy Maps",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewChooseLocationScreen() {
    SerkomKPUTheme {
        Surface {
            ChooseLocationScreen()
        }
    }
}
