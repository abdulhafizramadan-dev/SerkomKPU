package com.ahr.serkomkpu.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.labelInputTextStyle
import com.ahr.serkomkpu.ui.theme.lightRedKpu
import com.ahr.serkomkpu.ui.theme.placeholderInputTextStyle


@ExperimentalMaterial3Api
@Composable
fun KpuAddressTextField(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    placeholder: String,
    onTextChanged: (String) -> Unit,
    onChooseLocationClicked: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = label, style = MaterialTheme.typography.labelInputTextStyle)
            TextButton(onClick = onChooseLocationClicked) {
                Text(text = "Cari di maps")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = lightRedKpu
                )
            }
        }
        KpuAddressOutlinedTextField(
            text = text,
            onTextChanged = onTextChanged,
            placeholder = placeholder,
            modifier = Modifier.offset(y = (-4).dp),
            keyboardOptions = keyboardOptions
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun KpuAddressOutlinedTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        placeholder = { Text(text = placeholder, style = MaterialTheme.typography.placeholderInputTextStyle) },
        shape = RoundedCornerShape(size = 8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color(0xFFDEDEDE),),
        textStyle = MaterialTheme.typography.placeholderInputTextStyle.copy(MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .height(89.dp)
            .fillMaxWidth(),
        keyboardOptions = keyboardOptions
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewKpuAddressTextField() {
    SerkomKPUTheme {
        var text by remember { mutableStateOf("") }
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                KpuAddressTextField(
                    label = "Alamat Rumah",
                    text = text,
                    onTextChanged = { text = it },
                    placeholder = "Masukan alamat rumah",
                )
            }
        }
    }
}
