package com.ahr.serkomkpu.presentation.entry_electorate

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.serkomkpu.presentation.destinations.ChooseLocationScreenDestination
import com.ahr.serkomkpu.presentation.destinations.HomeScreenDestination
import com.ahr.serkomkpu.ui.component.KpuAddressTextField
import com.ahr.serkomkpu.ui.component.KpuButton
import com.ahr.serkomkpu.ui.component.KpuDatePicker
import com.ahr.serkomkpu.ui.component.KpuRadioButton
import com.ahr.serkomkpu.ui.component.KpuTextField
import com.ahr.serkomkpu.ui.component.KpuTopAppBar
import com.ahr.serkomkpu.ui.component.KpuTopAppBarType
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.poppinsFontFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@ExperimentalFoundationApi
@Destination
@ExperimentalMaterial3Api
@Composable
fun EntryElectorateScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val navigateUp: () -> Unit = { navigator.navigateUp() }
    val navigateToChooseLocationScreen: () -> Unit = {
        navigator.navigate(ChooseLocationScreenDestination())
    }
    val navigateToHomeScreen: () -> Unit = {
        navigator.navigate(HomeScreenDestination()) {
            popUpTo(HomeScreenDestination.route) {
                inclusive = true
            }
        }
    }

    val options = remember { listOf("Laki-Laki", "Perempuan") }
    var selectedOption by remember { mutableStateOf(options[0]) }

    Scaffold(
        topBar = {
            KpuTopAppBar(
                title = "Entry Data Pemilih",
                onNavIconClicked = navigateUp,
                type = KpuTopAppBarType.Detail
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(28.dp))
            KpuTextField(
                label = "NIK",
                text = "",
                onTextChanged = {},
                placeholder = "Masukan NIK disini"
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuTextField(
                label = "Nama Lengkap",
                text = "",
                onTextChanged = {},
                placeholder = "Masukan nama lengkap disini"
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuTextField(
                label = "Nomor Telepon",
                text = "",
                onTextChanged = {},
                placeholder = "Masukan no telepon disini"
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuRadioButton(
                label = "Jenis Kelamin",
                selectedOption = selectedOption,
                options = options,
                onOptionChanged = { selectedOption = it }
            )
            KpuDatePicker(
                label = "Tanggal Pendataan",
                text = "",
                onTextChanged = {},
                placeholder = "Masukan tanggal pendataan",
                onClicked = {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            KpuAddressTextField(
                label = "Alamat Rumah",
                text = "",
                placeholder = "Masukan alamat rumah",
                onTextChanged = {},
                onChooseLocationClicked = navigateToChooseLocationScreen
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                KpuButton(
                    text = "Upload Image",
                    onButtonClicked = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF333538),
                        contentColor = Color(0xFFFFFFFF)
                    )
                )
                Spacer(modifier = Modifier.width(4
                    .dp))
                Text(
                    text = "image.jpg",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal),
                        color = Color(0xFFD32B28),
                )
            }
            Spacer(modifier = Modifier.height(34.dp))
            KpuButton(
                text = "Submit",
                onButtonClicked = navigateToHomeScreen,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEntryElectorateScreen() {
    SerkomKPUTheme {
        Surface {
            EntryElectorateScreen()
        }
    }
}
