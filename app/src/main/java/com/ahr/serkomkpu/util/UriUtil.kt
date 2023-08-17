package com.ahr.serkomkpu.util

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import java.io.ByteArrayOutputStream

fun uriToBase64(contentResolver: ContentResolver, uri: Uri): String {
    val bitmapForEncoded = MediaStore.Images.Media.getBitmap(contentResolver,uri)
    val stream = ByteArrayOutputStream()
    bitmapForEncoded.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    val bytes = stream.toByteArray()
    return Base64.encodeToString(bytes, Base64.DEFAULT)
}
