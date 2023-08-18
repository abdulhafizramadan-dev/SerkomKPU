package com.ahr.serkomkpu.presentation.list_electorate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.serkomkpu.domain.model.Electorate
import com.ahr.serkomkpu.ui.theme.SerkomKPUTheme
import com.ahr.serkomkpu.ui.theme.greyTextFill
import com.ahr.serkomkpu.ui.theme.poppinsFontFamily
import com.ahr.serkomkpu.util.decodeStringBase64ToBitMap

@ExperimentalMaterial3Api
@Composable
fun KpuElectorateItem(
    modifier: Modifier = Modifier,
    electorate: Electorate,
    onCardClicked: (Int) -> Unit = {},
    onDeleteIconClicked: (Int) -> Unit = {}
) {
    Box(modifier = modifier) {
        ElevatedCard(
            onClick = { onCardClicked(electorate.id) },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    bitmap = electorate.image.decodeStringBase64ToBitMap(),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .height(85.dp)
                        .width(80.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = electorate.name,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF333333),
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = electorate.dateCollectionDate.toString(),
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFFD32B28),
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = electorate.nik,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFD32B28),
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = electorate.gender,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF777777),
                        ),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .height(36.dp)
                            .fillMaxWidth()
                            .offset(y = (-8).dp)
                    ) {
                        Text(
                            text = electorate.address,
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF777777),
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        IconButton(
                            onClick = { onDeleteIconClicked(electorate.id) },
                            modifier = Modifier.offset(y = (-2).dp, x = 14.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Hapus data pilih",
                                tint = greyTextFill,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewElectorateItem() {
    SerkomKPUTheme {
        Surface {
            val electorate = Electorate(
                nik = "201004000242002",
                name = "Ki Hadjar Dewantara",
                gender = "Pria",
                address = "Purwokerto Selatan",
            )
            KpuElectorateItem(electorate = electorate, modifier = Modifier.padding(all = 16.dp))
        }
    }
}
