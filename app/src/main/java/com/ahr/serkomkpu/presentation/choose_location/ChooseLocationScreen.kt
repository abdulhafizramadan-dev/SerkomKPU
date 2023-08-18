package com.ahr.serkomkpu.presentation.choose_location

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.ahr.serkomkpu.ui.theme.redKpu
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@SuppressLint("MissingPermission")
@ExperimentalPermissionsApi
@Destination
@ExperimentalMaterial3Api
@Composable
fun ChooseLocationScreen(
    modifier: Modifier = Modifier,
    resultNavigator: ResultBackNavigator<String>,
    chooseLocationScreenViewModel: ChooseLocationScreenViewModel = hiltViewModel()
) {

    val cameraPositionState = rememberCameraPositionState()

    val chooseLocationScreenUiState by chooseLocationScreenViewModel.chooseLocationScreenUiState.collectAsState()
    val btnSaveLocationEnabled by chooseLocationScreenViewModel.btnSaveEnabled.collectAsState(initial = false)

    val currentLocation = chooseLocationScreenUiState.currentLocation
    val currentLocationCity = chooseLocationScreenUiState.locationCityName

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(key1 = currentLocationCity) {
        if (currentLocationCity != null) {
            Toast.makeText(context, "$currentLocationCity", Toast.LENGTH_SHORT).show()
        }
    }

    val mapsUiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = false,
            myLocationButtonEnabled = false
        )
    }
    val fusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val navigateUp: () -> Unit = { resultNavigator.navigateBack() }
    val saveLocationAndSendDataToPreviousScreen: () -> Unit = {
        resultNavigator.navigateBack(result = currentLocationCity ?: "")
    }
    val moveCameraToCurrentLocation: () -> Unit = {
        if (locationPermissionState.allPermissionsGranted) {
            if (currentLocation != null) {
                scope.launch {
                    delay(1000L)
                    cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(currentLocation, 20f))
                }
            }
        } else {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }

    LaunchedEffect(key1 = locationPermissionState.allPermissionsGranted) {
        if (locationPermissionState.allPermissionsGranted) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                val location = LatLng(it.latitude, it.longitude)
                chooseLocationScreenViewModel.updateCurrentLocation(location)

                scope.launch {
                    delay(1000L)
                    cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(location, 20f))
                }

                val geocoder = Geocoder(context, Locale.getDefault()).getFromLocation(it.latitude, it.longitude, 1)
                if (geocoder?.isNotEmpty() == true) {
                    val address = geocoder[0]
                    val city = address.locality ?: "-"
                    chooseLocationScreenViewModel.updateLocationCityName(city)
                }
            }
        } else {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            KpuTopAppBar(
                title = "Pilih Lokasi",
                onNavIconClicked = navigateUp,
                type = KpuTopAppBarType.Detail,
                subtitle = currentLocationCity
            )
        },
        modifier = modifier,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = saveLocationAndSendDataToPreviousScreen,
                containerColor = redKpu,
                icon = {
                   Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White)
                },
                text = {
                    Text(
                        text = "Simpan Lokasi",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = mapsUiSettings,
                contentPadding = PaddingValues(bottom = 72.dp)
            ) {
                if (currentLocation != null) {
                    Marker(
                        state = MarkerState(position = currentLocation!!)
                    )
                }
            }
            SmallFloatingActionButton(
                onClick = moveCameraToCurrentLocation,
                containerColor = Color.White,
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(imageVector = Icons.Default.MyLocation, contentDescription = null)
            }
        }
    }
}

@ExperimentalPermissionsApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewChooseLocationScreen() {
    SerkomKPUTheme {
        Surface {
            val dummyResultNavigator = object : ResultBackNavigator<String> {
                override fun navigateBack(result: String, onlyIfResumed: Boolean) {

                }

                override fun navigateBack(onlyIfResumed: Boolean) {

                }

                override fun setResult(result: String) {

                }
            }
            ChooseLocationScreen(
                resultNavigator = dummyResultNavigator
            )
        }
    }
}
