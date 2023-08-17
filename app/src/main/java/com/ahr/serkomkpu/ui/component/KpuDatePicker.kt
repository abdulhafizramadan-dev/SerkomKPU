package com.ahr.serkomkpu.ui.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.labelInputTextStyle
import com.ahr.serkomkpu.ui.theme.placeholderInputTextStyle


@ExperimentalMaterial3Api
@Composable
fun KpuDatePicker(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    onClicked: () -> Unit = {},
    focusRequester: FocusRequester = FocusRequester.Default
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = label, style = MaterialTheme.typography.labelInputTextStyle)
        Spacer(modifier = Modifier.height(4.dp))
        KpuDatePickerTextField(
            text = text,
            onTextChanged = onTextChanged,
            placeholder = placeholder,
            onClicked = onClicked,
            focusRequester = focusRequester
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun KpuDatePickerTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    onClicked: () -> Unit = {},
    focusRequester: FocusRequester = FocusRequester.Default
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        placeholder = { Text(text = placeholder, style = MaterialTheme.typography.placeholderInputTextStyle) },
        maxLines = 1,
        singleLine = true,
        shape = RoundedCornerShape(size = 8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color(0xFFDEDEDE),),
        textStyle = MaterialTheme.typography.placeholderInputTextStyle.copy(MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable { onClicked() }
            .focusRequester(focusRequester)
            .onFocusChanged { if (it.isFocused) onClicked() },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Today,
                contentDescription = null,
                tint = Color(0xFF49454F)
            )
        }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewKpuDatePicker() {
    SerkomKPUTheme {
        var text by remember { mutableStateOf("") }
        val context = LocalContext.current
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                KpuDatePicker(
                    label = "Tanggal Pendataan",
                    text = text,
                    onTextChanged = { text = it },
                    placeholder = "Masukan tanggal pendataan",
                    onClicked = {
                        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
