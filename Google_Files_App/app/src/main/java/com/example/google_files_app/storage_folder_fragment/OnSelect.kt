package com.example.google_files_app.storage_folder_fragment

import androidx.appcompat.app.AppCompatActivity
import java.io.File

interface OnSelect {
    fun onClick(file: File?)
    fun onLongSelect(file: File?)
}
