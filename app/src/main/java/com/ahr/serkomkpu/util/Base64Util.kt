package com.ahr.serkomkpu.util

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

fun String.decodeStringBase64ToBitMap(): ImageBitmap {
    val bytesDecoded = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(bytesDecoded,0,bytesDecoded.size).asImageBitmap()
}