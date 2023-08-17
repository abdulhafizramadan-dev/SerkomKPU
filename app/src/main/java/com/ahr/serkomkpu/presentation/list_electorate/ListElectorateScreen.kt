package com.ahr.serkomkpu.presentation.list_electorate

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.serkomkpu.domain.model.Electorate
import com.ahr.serkomkpu.presentation.destinations.DetailElectorateDestination
import com.ahr.serkomkpu.ui.component.KpuOutlinedTextField
import com.ahr.serkomkpu.ui.component.KpuTopAppBar
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.greyTextFill
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ListElectorateScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {

    var text by remember { mutableStateOf("") }

    val navigateToDetailElectorate: (Electorate) -> Unit = { electorate ->
        navigator.navigate(DetailElectorateDestination(electorate))
    }

    val electorates = (1..10).map {
        Electorate(
            nik = "201004000242002",
            name = "Ki Hadjar Dewantara",
            gender = "Pria",
            address = "Purwokerto Selatan",
            dateCollectionDate = "13-12-2023",
            phone = "08123456789",
            id = it
        )
    }

    Scaffold(
        topBar = {
            KpuTopAppBar(title = "Daftar Data Pemilih")
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                KpuOutlinedTextField(
                    text = text,
                    onTextChanged = { text = it },
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
            }
            items(items = electorates, key = { it.id }) { electorate ->
                KpuElectorateItem(
                    electorate = electorate,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 16.dp),
                    onCardClicked = navigateToDetailElectorate
                )
            }
        }
    }
}

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
