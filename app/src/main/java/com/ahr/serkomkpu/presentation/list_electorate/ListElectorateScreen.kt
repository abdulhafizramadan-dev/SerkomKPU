package com.ahr.serkomkpu.presentation.list_electorate

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahr.serkomkpu.presentation.destinations.DetailElectorateScreenDestination
import com.ahr.serkomkpu.ui.component.KpuOutlinedTextField
import com.ahr.serkomkpu.ui.component.KpuTopAppBar
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.StatusBarUiController
import com.ahr.serkomkpu.ui.theme.greyTextFill
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

@FlowPreview
@Destination
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ListElectorateScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    listElectorateViewModel: ListElectorateViewModel = hiltViewModel()
) {

    StatusBarUiController(
        color = MaterialTheme.colorScheme.background,
        useDarkIcons = true
    )

    val navigateToDetailElectorate: (Int) -> Unit = {
        navigator.navigate(DetailElectorateScreenDestination(id = it))
    }

    val listElectorateScreenUiState by listElectorateViewModel.listElectorateScreenUiState.collectAsState()

    val searchQuery = listElectorateScreenUiState.searchQuery
    val electorates = listElectorateScreenUiState.electorates

    LaunchedEffect(key1 = Unit) {
        listElectorateViewModel.getAllElectorate()
    }

    LaunchedEffect(key1 = searchQuery) {
        snapshotFlow { searchQuery }
            .debounce(100)
            .distinctUntilChanged()
            .collectLatest { query ->
                if (query.isNotEmpty()) {
                    listElectorateViewModel.searchElectorate(query)
                } else {
                    listElectorateViewModel.getAllElectorate()
                }
            }
    }

    Scaffold(
        topBar = {
            KpuTopAppBar(title = "Daftar Data Pemilih")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            KpuOutlinedTextField(
                text = searchQuery,
                onTextChanged = listElectorateViewModel::updateSearchQuery,
                placeholder = "Cari data penduduk",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = greyTextFill
                    )
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .padding(top = 26.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(all = 4.dp),
            ) {
                items(items = electorates, key = { it.id }) { electorate ->
                    KpuElectorateItem(
                        electorate = electorate,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 16.dp)
                            .animateItemPlacement(),
                        onCardClicked = navigateToDetailElectorate
                    )
                }
            }
        }
    }
}

@FlowPreview
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewListElectorateScreen() {
    SerkomKPUTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ListElectorateScreen()
        }
    }
}
