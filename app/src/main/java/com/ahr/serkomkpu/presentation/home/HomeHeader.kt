package com.ahr.serkomkpu.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.serkomkpu.R
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.heading2
import com.ahr.serkomkpu.ui.theme.heading3
import com.ahr.serkomkpu.ui.theme.regularTextStyle

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    electorateCount: Int,
    onLogoutButtonClicked: () -> Unit = {}
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_home_header),
            contentDescription = null,
            modifier = Modifier
                .height(186.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 20.dp, end = 2.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "APLIKASI KPU PURWOKERTO",
                    style = MaterialTheme.typography.heading3.copy(
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
                IconButton(onClick = onLogoutButtonClicked) {
                    Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout", tint = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(36.dp))
            KpuElectorateCountBox(
                electorateCount = electorateCount,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
fun KpuElectorateCountBox(
    modifier: Modifier = Modifier,
    electorateCount: Int
) {
    Box(modifier = modifier) {
        ElevatedCard(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_kpu),
                    contentDescription = null,
                    modifier = Modifier.size(90.dp)
                )
                Column {
                    Text(
                        text = "Total $electorateCount",
                        style = MaterialTheme.typography.heading2.copy(
                            color = Color(0xFF333538)
                        )
                    )
                    Text(
                        text = "Data Calon Pemilih",
                        style = MaterialTheme.typography.regularTextStyle,
                        modifier = Modifier.offset(x = 2.dp, y = (-5).dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeHeader() {
    SerkomKPUTheme {
        Surface {
            HomeHeader(
                electorateCount = 100
            )
        }
    }
}
