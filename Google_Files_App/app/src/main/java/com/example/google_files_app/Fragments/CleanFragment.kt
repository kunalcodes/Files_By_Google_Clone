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
import java.math.RoundingMode
import java.text.DecimalFormat


class CleanFragment : Fragment(R.layout.fragment_clean) {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val folderSize = stat.blockSizeLong
        val totalFolders = stat.blockCountLong
        var size: Double = (folderSize * totalFolders).toDouble()

        var suffix: String

        if (size >= 1024) {

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

            // tvInternalStorageUsage.text = df.format(size).toString() + suffix
        }
        val statFs = Environment.getStorageDirectory().totalSpace
        val memory = " GB • Internal"
        val totalStorage = size + statFs



        val pathOS: File = Environment.getRootDirectory() //Os Storage

        val statOS = StatFs(pathOS.absolutePath).totalBytes

        val free_OS_memory: Long = 0
        // var total_OS_memory = statOS.blockCountLong * statOS.blockSizeLong /(1024*1024*1024)

        tvInternalStorageDetails.text = statOS.toString()+ memory
    }


}
