package com.ahr.serkomkpu.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.serkomkpu.R
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.heading3
import com.ahr.serkomkpu.ui.theme.redKpu
import com.ahr.serkomkpu.ui.theme.regularTextStyle

enum class KpuTopAppBarType {
    Default, Detail, Auth
}

@ExperimentalMaterial3Api
@Composable
fun KpuTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    type: KpuTopAppBarType = KpuTopAppBarType.Default,
    onNavIconClicked: () -> Unit = {},
    onRegisterButtonClicked: () -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier,
        shape = RectangleShape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.heading3,
                        modifier = Modifier.offset(y = 2.dp)
                    )
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.regularTextStyle.copy(
                                color = redKpu
                            ),
                            modifier = Modifier.offset(y = 2.dp)
                        )
                    }
                }
            },
            actions = {
                if (type == KpuTopAppBarType.Auth) {
                    Text(
                        text = "Daftar",
                        style = MaterialTheme.typography.heading3.copy(color = redKpu),
                        modifier = Modifier
                            .offset(y = 1.dp, x = (-16).dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onRegisterButtonClicked() }
                            .padding(bottom = 2.dp, top = 4.dp)
                            .padding(horizontal = 4.dp)
                    )
                }
            },
            navigationIcon = {
                if (type == KpuTopAppBarType.Auth || type == KpuTopAppBarType.Detail) {
                    IconButton(onClick = onNavIconClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left),
                            contentDescription = "Kembali ke halaman sebelumnya"
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.White
            )
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewAuthKpuTopAppBar() {
    SerkomKPUTheme {
        Surface {
            Scaffold(
                topBar = { KpuTopAppBar(title = "Masuk", subtitle = "Register", type = KpuTopAppBarType.Auth) }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}
