package com.ahr.serkomkpu.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun decodeStringBase64ToBitMap(base64String: String): Bitmap {
    val bytesDecoded = Base64.decode(base64String, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(bytesDecoded,0,bytesDecoded.size)
}