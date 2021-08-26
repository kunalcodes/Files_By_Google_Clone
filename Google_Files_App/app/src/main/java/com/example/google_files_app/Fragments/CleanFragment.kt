package com.example.google_files_app.Fragments

import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.view.View
import androidx.fragment.app.Fragment
import com.example.google_files_app.R
import kotlinx.android.synthetic.main.fragment_clean.*
import java.math.RoundingMode
import java.text.DecimalFormat
import android.content.Context.ACTIVITY_SERVICE

import androidx.core.content.ContextCompat.getSystemService

import android.app.ActivityManager


class CleanFragment : Fragment(R.layout.fragment_clean) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val folderSize = stat.blockSizeLong
        val totalFolders = stat.blockCountLong
        var size:Double = ((folderSize * totalFolders).toDouble())

        var suffix: String
        if (size >= 1024) {
            suffix = " KB used"
            size /= 1024
            tvInternalStorageUsage.text =size.toString() +suffix
        }
        if (size >= 1024*1024) {
            suffix = " GB used"
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            size /= 1024*1024

            tvInternalStorageUsage.text =   df.format(size).toString() + suffix
        }
      val statFs =  Environment.getRootDirectory().path.length
        val memory =" GB . Internal"
        tvInternalStorageDetails.text = statFs.toString() + memory
    }




}
