package my.kunal.file_explorer

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

class FileOpener {
    //    @Throws(IOException::class)
    companion object {
        fun openFile(context: Context, file: File) {
            val selectedFile = file
            val uri = FileProvider.getUriForFile(context,
                context.applicationContext.packageName + ".provider", file)
            val intent = Intent(Intent.ACTION_VIEW)
            if (uri.toString().contains(".doc")) {

                intent.setDataAndType(uri, "text/*")

            } else if (uri.toString().contains(".pdf")) {
                intent.setDataAndType(uri, "application/pdf")

            } else if (uri.toString().contains(".mp3")
                || uri.toString().contains(".m4a")
                || uri.toString().contains(".wav")
                || uri.toString().contains(".wma")
            ) {
                intent.setDataAndType(uri, "audio/x-wav")
                intent.action = Intent.ACTION_QUICK_VIEW

            } else if (uri.toString().contains(".jpeg")
                || uri.toString().contains(".jpg")
                || uri.toString().contains(".png")
                || uri.toString().contains(".bmp")
            ) {
                intent.setDataAndType(uri, "image/jpeg")


            } else if (uri.toString().contains(".mp4")
                || uri.toString().contains(".mkv")
                || uri.toString().contains(".flv")
                || uri.toString().contains(".avi")
                || uri.toString().contains(".gif")
            ) {
                intent.setDataAndType(uri, "video/*")
            } else {
                intent.setDataAndType(uri, "*/*")
            }
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            context.startActivity(intent)
        }
    }

}