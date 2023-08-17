package com.ahr.serkomkpu.presentation.entry_electorate

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@ExperimentalFoundationApi
@Destination
@ExperimentalMaterial3Api
@Composable
fun EntryElectorateScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    entryElectorateViewModel: EntryElectorateViewModel = viewModel()
) {

    val scrollState = rememberScrollState()
    val focusRequester = remember(::FocusRequester)
//    val context = LocalContext.current
//    val contentResolver = context.contentResolver

    val entryElectorateScreenUiState by entryElectorateViewModel.entryElectorateScreenUiState.collectAsState()

    val calendarDialogState = rememberUseCaseState()

    var selectedImage by remember { mutableStateOf<Uri?>(null) }

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

//    var imageBitmap by remember {
//        mutableStateOf<Bitmap?>(null)
//    }

    val openGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
//        if (uri != null) {
//            val base64String = uriToBase64(contentResolver, uri)
//            val bitmapDecoded = decodeStringBase64ToBitMap(base64String)
//            imageBitmap = bitmapDecoded
//        }
        selectedImage = uri
    }

    val openGallery: () -> Unit = {
        openGalleryLauncher.launch("image/*")
    }
    val openCalendarDialog: () -> Unit = {
        calendarDialogState.show()
    }

    CalendarDialog(
        state = calendarDialogState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date { newDate ->
            entryElectorateViewModel.updateDateCollectionDate(newDate)
            focusRequester.freeFocus()
        },

    )

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
                text = entryElectorateScreenUiState.nik,
                onTextChanged = entryElectorateViewModel::updateNik,
                placeholder = "Masukan NIK disini"
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuTextField(
                label = "Nama Lengkap",
                text = entryElectorateScreenUiState.name,
                onTextChanged = entryElectorateViewModel::updateName,
                placeholder = "Masukan nama lengkap disini"
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuTextField(
                label = "Nomor Telepon",
                text = entryElectorateScreenUiState.phone,
                onTextChanged = entryElectorateViewModel::updatePhone,
                placeholder = "Masukan no telepon disini"
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuRadioButton(
                label = "Jenis Kelamin",
                selectedOption = entryElectorateScreenUiState.gender,
                options = options,
                onOptionChanged = entryElectorateViewModel::updateGender
            )
            KpuDatePicker(
                label = "Tanggal Pendataan",
                text = entryElectorateScreenUiState.dateCollectionDate.toString(),
                onTextChanged = {},
                placeholder = "Masukan tanggal pendataan",
                onClicked = openCalendarDialog,
                focusRequester = focusRequester
            )
            Spacer(modifier = Modifier.height(12.dp))
            KpuAddressTextField(
                label = "Alamat Rumah",
                text = entryElectorateScreenUiState.address,
                placeholder = "Masukan alamat rumah",
                onTextChanged = entryElectorateViewModel::updateAddress,
                onChooseLocationClicked = navigateToChooseLocationScreen
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuButton(
                text = "Upload Image",
                onButtonClicked = openGallery,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF333538),
                    contentColor = Color(0xFFFFFFFF)
                )
            )
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(
//                    text = "image.jpg",
//                    style = TextStyle(
//                        fontSize = 12.sp,
//                        fontFamily = poppinsFontFamily,
//                        fontWeight = FontWeight.Normal),
//                        color = Color(0xFFD32B28),
//                )
//            }
            if (selectedImage != null) {
                AsyncImage(model = selectedImage, contentDescription = null, modifier = Modifier.fillMaxWidth())
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
