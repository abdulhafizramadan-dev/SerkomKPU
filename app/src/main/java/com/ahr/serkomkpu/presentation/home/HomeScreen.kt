package com.ahr.serkomkpu.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahr.serkomkpu.presentation.destinations.EntryElectorateScreenDestination
import com.ahr.serkomkpu.presentation.destinations.HomeScreenDestination
import com.ahr.serkomkpu.presentation.destinations.InformationScreenDestination
import com.ahr.serkomkpu.presentation.destinations.ListElectorateScreenDestination
import com.ahr.serkomkpu.presentation.destinations.LoginScreenDestination
import com.ahr.serkomkpu.ui.component.KpuMenu
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalFoundationApi
@Destination
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val logoutDialogState = rememberUseCaseState()

    val homeScreenUiState by homeViewModel.homeScreenUiState.collectAsState()
    val electorateCount = homeScreenUiState.electorateCount

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getElectorateCount()
    }

    val navigateInformationKpuScreen: () -> Unit = {
        navigator.navigate(InformationScreenDestination())
    }
    val navigateEntryElectorateScreen: () -> Unit = {
        navigator.navigate(EntryElectorateScreenDestination())
    }
    val navigateListElectorateScreen: () -> Unit = {
        navigator.navigate(ListElectorateScreenDestination())
    }
    val navigateToLoginScreen: () -> Unit = {
        navigator.navigate(LoginScreenDestination()) {
            popUpTo(HomeScreenDestination.route) {
                inclusive = true
            }
        }
    }

    CoreDialog(
        state = logoutDialogState,
        selection = CoreSelection(
            withButtonView = true,
            negativeButton = SelectionButton(
                text = "Batal"
            ),
            positiveButton = SelectionButton(
                "Logout"
            ),
            onPositiveClick = navigateToLoginScreen,
            onNegativeClick = { logoutDialogState.finish() }
        ),
        body = {
            Text(text = "Ketika anda melakukan logout,anda harus login kembali melalui halaman login!")
        },
        header = Header.Default(
            title = "Apakah anda yakin ingin logout?",
            icon = IconSource(imageVector = Icons.Default.Warning)
        ),
    )

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(paddingValues)
        ) {
            HomeHeader(
                electorateCount = electorateCount,
                onLogoutButtonClicked = { logoutDialogState.show() }
            )
            Spacer(modifier = Modifier.height(42.dp))
            KpuMenu(
                text = "Informasi KPU",
                onClicked = navigateInformationKpuScreen,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuMenu(
                text = "Entry Data Pemilih",
                onClicked = navigateEntryElectorateScreen,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuMenu(
                text = "Daftar Data Pemilih",
                onClicked = navigateListElectorateScreen,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@FlowPreview
@ExperimentalFoundationApi
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
