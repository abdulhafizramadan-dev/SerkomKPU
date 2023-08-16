package com.ahr.serkomkpu.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.labelInputTextStyle
import com.ahr.serkomkpu.ui.theme.placeholderInputTextStyle

enum class KpuTextFieldType {
    Text, Password
}

@ExperimentalMaterial3Api
@Composable
fun KpuTextField(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    type: KpuTextFieldType = KpuTextFieldType.Text
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = label, style = MaterialTheme.typography.labelInputTextStyle)
        Spacer(modifier = Modifier.height(4.dp))
        when (type) {
            KpuTextFieldType.Text -> {
                KpuOutlinedTextField(
                    text = text,
                    onTextChanged = onTextChanged,
                    placeholder = placeholder
                )
            }
            KpuTextFieldType.Password -> {
                KpuOutlinedTextFieldPassword(
                    text = text,
                    onTextChanged = onTextChanged,
                    placeholder = placeholder
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun KpuOutlinedTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String
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
    )
}

@ExperimentalMaterial3Api
@Composable
fun KpuOutlinedTextFieldPassword(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String
) {
    var passwordVisible by remember { mutableStateOf(true) }
    val toggleVisualTransformation: () -> Unit = {
        passwordVisible = !passwordVisible
    }
    val visualTransformation = remember(key1 = passwordVisible) {
        if (passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    }

    val passwordIcon = remember(key1 = passwordVisible) {
        if (passwordVisible) {
            Icons.Default.Visibility
        } else {
            Icons.Default.VisibilityOff
        }
    }
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        placeholder = { Text(text = placeholder, style = MaterialTheme.typography.placeholderInputTextStyle) },
        maxLines = 1,
        singleLine = true,
        shape = RoundedCornerShape(size = 8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color(0xFFDEDEDE),),
        textStyle = MaterialTheme.typography.placeholderInputTextStyle.copy(MaterialTheme.colorScheme.onBackground),
        visualTransformation = visualTransformation,
        trailingIcon = {
            IconButton(onClick = toggleVisualTransformation) {
                Icon(imageVector = passwordIcon, contentDescription = null)
            }
        },
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
    )
}


@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewKpuTextField() {
    SerkomKPUTheme {
        var text by remember { mutableStateOf("") }
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                KpuTextField(
                    label = "Masukan Email",
                    text = text,
                    onTextChanged = { text = it },
                    placeholder = "Masukan Email anda"
                )
                Spacer(modifier = Modifier.height(20.dp))
                KpuTextField(
                    label = "Password",
                    text = text,
                    onTextChanged = { text = it },
                    placeholder = "Masukan password anda",
                    type = KpuTextFieldType.Password
                )
            }
        }
    }
}
