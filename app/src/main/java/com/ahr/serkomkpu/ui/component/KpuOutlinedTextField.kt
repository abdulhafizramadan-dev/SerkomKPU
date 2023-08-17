package com.ahr.serkomkpu.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.greyTextFill
import com.ahr.serkomkpu.ui.theme.placeholderInputTextStyle

@ExperimentalMaterial3Api
@Composable
fun KpuOutlinedTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    text: String,
    onTextChanged: (String) -> Unit,
    leadingIcon: @Composable () -> Unit = {}
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        leadingIcon = leadingIcon,
        placeholder = { Text(text = placeholder, style = MaterialTheme.typography.placeholderInputTextStyle) },
        shape = RoundedCornerShape(size = 8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color(0xFFDEDEDE),),
        textStyle = MaterialTheme.typography.placeholderInputTextStyle.copy(MaterialTheme.colorScheme.onBackground),
        maxLines = 1,
        singleLine = true,
        modifier = modifier.height(52.dp)
            .fillMaxWidth(),
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewKpuOutlinedTextField() {
    SerkomKPUTheme {
        var text by remember { mutableStateOf("") }
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
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
                    }
                )
            }
        }
    }
}
