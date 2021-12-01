package my.kunal.file_explorer.storage_folder_fragment

import java.io.File

interface OnSelect {
    fun onClick(file: File?)
    fun onLongSelect(file: File?)
}
