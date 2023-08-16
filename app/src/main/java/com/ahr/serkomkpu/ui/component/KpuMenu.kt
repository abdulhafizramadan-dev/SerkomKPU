package com.ahr.serkomkpu.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.heading2
import com.ahr.serkomkpu.ui.theme.redKpu

@Composable
fun KpuMenu(
    modifier: Modifier = Modifier,
    text: String,
    onClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(redKpu)
            .clickable { onClicked() }
            .fillMaxWidth()
            .padding(vertical = 18.dp, horizontal = 32.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.heading2.copy(color = Color.White),
            modifier = Modifier.align(Alignment.Center)
                .padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
fun PreviewKpuMenu() {
    SerkomKPUTheme {
        Surface(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            KpuMenu(
                text = "Entry Data Pemilih",
                onClicked = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
