package com.ahr.serkomkpu.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.serkomkpu.ui.component.KpuButton
import com.ahr.serkomkpu.ui.component.KpuTextField
import com.ahr.serkomkpu.ui.component.KpuTextFieldType
import com.ahr.serkomkpu.ui.component.KpuTopAppBar
import com.ahr.serkomkpu.ui.component.KpuTopAppBarType
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.poppinsFontFamily

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { KpuTopAppBar(title = "Masuk", type = KpuTopAppBarType.Auth) },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .padding(vertical = 24.dp, horizontal = 20.dp)
        ) {
            KpuTextField(
                label = "Masukan Email",
                text = "",
                onTextChanged = {},
                placeholder = "Masukan Email anda"
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuTextField(
                label = "Password",
                text = "",
                onTextChanged = {},
                placeholder = "Masukan password anda",
                type = KpuTextFieldType.Password
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Lupa password?",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFD32B28),
                    )
                )
            }
            KpuButton(
                text = "Masuk",
                onButtonClicked = { /*TODO*/ },
                modifier = Modifier.offset(y = (-8).dp)
                    .fillMaxWidth()
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewLoginScreen() {
    SerkomKPUTheme {
        Surface {
            LoginScreen()
        }
    }
}
