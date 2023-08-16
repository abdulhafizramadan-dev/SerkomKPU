package com.ahr.serkomkpu.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.labelInputTextStyle
import com.ahr.serkomkpu.ui.theme.poppinsFontFamily

@ExperimentalMaterial3Api
@Composable
fun KpuRadioButton(
    modifier: Modifier = Modifier,
    label: String,
    selectedOption: String,
    options: List<String>,
    onOptionChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = label, style = MaterialTheme.typography.labelInputTextStyle)
        Row(
            modifier = Modifier.offset(x = (-4).dp, y = (-8).dp)
        ) {
            options.forEach {  option ->
                KpuOptionRadioItem(
                    selectedOption = selectedOption,
                    option = option,
                    onOptionChanged = onOptionChanged
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun KpuOptionRadioItem(
    modifier: Modifier = Modifier,
    selectedOption: String,
    option: String,
    onOptionChanged: (String) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = option == selectedOption,
            onClick = { onOptionChanged(option) }
        )
        Text(
            text = option,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000),
            )
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewKpuRadioButton() {
    SerkomKPUTheme {
        val options = remember {
            listOf("Laki-Laki", "Perempuan")
        }
        var selectedOption by remember { mutableStateOf(options[0]) }
        Surface(
            modifier = Modifier.padding(16.dp)
        ) {
            KpuRadioButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Jenis Kelamin",
                selectedOption = selectedOption,
                options = options,
                onOptionChanged = { selectedOption = it }
            )
        }
    }
}
