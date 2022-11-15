package com.capstone.patech_android.util

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import java.io.*

class ImageResolver(private val context: Context) {

    fun createImgBase64(uri: Uri): String {
        Log.d("uri", "$uri")
        val options = BitmapFactory.Options()
        val inputStream = context.contentResolver.openInputStream(uri)
        val byteArrayOutputStream = ByteArrayOutputStream()
        getRotatedBitmap(
            BitmapFactory.decodeStream(inputStream, null, options),
            getOrientationOfImage(uri)
        )?.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream)
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP)
    }

    private fun getOrientationOfImage(uri: Uri): Int {
        val exif: ExifInterface?
        try {
            val filePath = getPathFromUri(uri)
            exif = ExifInterface(filePath)
        } catch (e: IOException) {
            e.printStackTrace()
            return -1
        }
        val orientation: Int = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)
        if (orientation != -1) {
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> return 90
                ExifInterface.ORIENTATION_ROTATE_180 -> return 180
                ExifInterface.ORIENTATION_ROTATE_270 -> return 270
            }
        }
        return 0
    }

    @Throws(Exception::class)
    fun getRotatedBitmap(bitmap: Bitmap?, degrees: Int): Bitmap? {
        if (bitmap == null) return null
        if (degrees == 0) return bitmap
        val matrix = Matrix()
        matrix.postRotate(degrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    private fun getPathFromUri(uri: Uri): String {
        val cursor: Cursor = context.contentResolver.query(uri, null, null, null, null)
            ?: throw NullPointerException()
        cursor.moveToNext()
        val columnIndex = cursor.getColumnIndex("_data")
        val path = if (columnIndex >= 0) {
            cursor.getString(columnIndex)
        } else {
            throw IllegalAccessException()
        }
        cursor.close()
        return path
    }

    private fun replaceFileName(fileName: String): String = "${fileName.replace(".", "_")}.jpeg"

    @SuppressLint("Recycle")
    private fun saveFile(image1 : ByteArrayOutputStream) {

        // 압축된 이미지 갤러리에 저장
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "image_1024.JPG")
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/*")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Images.Media.IS_PENDING, 1)
        }
        val contentResolver: ContentResolver = context.contentResolver
        val item = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        try {
            val pdf = contentResolver.openFileDescriptor(item!!, "w", null)
            if (pdf == null) {
                Log.d("asdf", "null")
            } else {
                val strToByte = image1.toByteArray()
                val fos = FileOutputStream(pdf.fileDescriptor)
                fos.write(strToByte)
                fos.close()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    values.clear()
                    values.put(MediaStore.Images.Media.IS_PENDING, 0)
                    contentResolver.update(item, values, null, null)
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
