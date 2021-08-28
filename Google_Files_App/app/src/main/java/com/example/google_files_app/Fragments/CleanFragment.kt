package com.example.google_files_app.Fragments

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.google_files_app.R
import kotlinx.android.synthetic.main.fragment_clean.*
import java.io.File
import java.lang.StringBuilder
import java.math.RoundingMode
import java.text.DecimalFormat


class CleanFragment : Fragment(R.layout.fragment_clean) {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




/*
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val folderSize = stat.blockSizeLong
        val totalFolders = stat.blockCountLong
        var size: Double = (folderSize * totalFolders).toDouble()*/
        val path = Environment.getExternalStorageDirectory()
        val stat = StatFs(path.path)
        val BlockSize = stat.blockSize.toLong()
        val TotalBlocks = stat.blockCount.toLong()
        tvInternalStorageDetails.text = getTotalInternalMemorySize()

        var suffix: String

       /* if (size >= 1024) {

            suffix = " KB used"
            size /= 1024
            // tvInternalStorageUsage.text = size.toString() + suffix

        }
        val availableSpace = stat.availableBlocks.toLong() * stat.blockSize.toLong()
        if (size >= 1024 * 1024) {

            suffix = " GB used"
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            size /= 1024 * 1024

        }
        val statFs = Environment.getStorageDirectory().totalSpace
        val memory = " GB • Internal"
        val totalStorage = size + statFs



        val pathOS: File = Environment.getRootDirectory() //Os Storage

        val statOS = StatFs(pathOS.absolutePath).totalBytes

        val free_OS_memory: Long = 0
        // var total_OS_memory = statOS.blockCountLong * statOS.blockSizeLong /(1024*1024*1024)*/

    }
    fun getTotalInternalMemorySize(): String {
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val BlockSize = stat.blockSize.toLong()
        val TotalBlocks = stat.blockCount.toLong()
        return formatSize(TotalBlocks * BlockSize)
    }

    fun formatSize(size: Long): String {
        var size = size
        var suffixSize: String? = null
        if (size >= 1000) {
            suffixSize = "KB"
            size /= 1000
            if (size >= 1000) {
                suffixSize = "MB"
                size /= 1000
                if (size >= 1000) {
                    size /= 1000
                    size += 1
                    suffixSize = "GB"
                }
            }
        }
        val BufferSize = StringBuilder(
            size.toString()
        )
        var commaOffset = BufferSize.length - 3
        while (commaOffset > 0) {
            BufferSize.insert(commaOffset, ',')
            commaOffset -= 3
        }
        if (suffixSize != null) BufferSize.append(suffixSize)
        return BufferSize.toString()
    }


}
