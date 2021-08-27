package com.example.google_files_app

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException

class FileOpener {
//    @Throws(IOException::class)
     companion object {
        fun openFile(context: Context, file: File) {
            val selectedFile = file
            val uri = FileProvider.getUriForFile(
                context,
                context.applicationContext.packageName + ".provider", file
            )
            val intent = Intent(Intent.ACTION_VIEW)
            if (uri.toString().contains(".doc")) {
                intent.setDataAndType(uri, "application/msword")
            } else if (uri.toString().contains(".pdf")) {
                intent.setDataAndType(uri, "application/pdf")
            } else if (uri.toString().contains(".mp3")) {
                intent.setDataAndType(uri, "audio/x-wav")
            } else if (uri.toString().contains(".jpeg")) {
                intent.setDataAndType(uri, "image/jpeg")
            } else if (uri.toString().contains(".mp4")) {
                intent.setDataAndType(uri, "video/*")
            } else {
                intent.setDataAndType(uri, "*/*")
            }
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            context.startActivity(intent)
        }
    }

}