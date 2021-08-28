package storage_folder_fragment

import java.io.File
import java.util.*


class FileSize {

    companion object {

         fun getVideoSize(file: File): Long {
            var videoLength: Long = 0

             val files = file.listFiles()
            if(files != null ) {
                val count = files.size
                for (i in 0 until count) {
                    videoLength += if (files[i].isFile &&( files[i].name.lowercase(Locale.getDefault()).endsWith(".mp4")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".flv")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".avi")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".gif")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".mkv"))) {
                            files[i].length()
                    } else {
                        getVideoSize(files[i])
                    }

                }
            }
            return videoLength
        }

        fun getImageSize(file: File): Long {
            var imageLength: Long = 0

            val files = file.listFiles()
            if(files != null ) {
                val count = files.size
                for (i in 0 until count) {
                    imageLength+= if(files[i].isFile &&( files[i].name.lowercase(Locale.getDefault()).endsWith(".jpeg")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".jpg")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".png")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".bmp"))){

                        files[i].length()
                    }

                    else {
                        getImageSize(files[i])
                    }
                }
            }
            return imageLength
        }
         fun getDocSize(file: File): Long {
            var docLength: Long = 0

            val files = file.listFiles()
            if(files != null ) {
                val count = files.size
                for (i in 0 until count) {
                    docLength+= if(files[i].isFile &&( files[i].name.lowercase(Locale.getDefault()).endsWith(".doc")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".pdf")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".xls")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".xml"))){

                        files[i].length()
                    }

                    else {
                        getDocSize(files[i])
                    }
                }
            }
            return docLength
        }
        fun getAudioSize(file: File): Long {
            var audioSize: Long = 0

            val files = file.listFiles()
            if(files != null ) {
                val count = files.size
                for (i in 0 until count) {
                    audioSize+= if(files[i].isFile &&( files[i].name.lowercase(Locale.getDefault()).endsWith(".mp3")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".m4a")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".wma")
                                ||files[i].name.lowercase(Locale.getDefault()).endsWith(".wav"))){

                        files[i].length()
                    }

                    else {
                        getAudioSize(files[i])
                    }
                }
            }
            return audioSize
        }
        fun getAppSize(file: File): Long {
            var appSize: Long = 0

            val files = file.listFiles()
            if(files != null ) {
                val count = files.size
                for (i in 0 until count) {
                    appSize+= if(files[i].isFile &&( files[i].name.lowercase(Locale.getDefault()).endsWith(".apk"))){

                        files[i].length()
                    }

                    else {
                        getAppSize(files[i])
                    }
                }
            }
            return appSize
        }
        fun getDownloadSize(file: File): Long {
            var downloadSize: Long = 0

            val files = file.listFiles()
            if(files != null && file.isDirectory ) {
                val count = files.size

                    for (i in 0 until count) {
                        downloadSize += if (files[i].isFile) {

                            files[i].length()
                        } else {
                            getDownloadSize(files[i])
                        }
                    }

            }
            return downloadSize
        }




    }
}


