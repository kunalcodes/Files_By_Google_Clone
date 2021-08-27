package com.example.google_files_app.storage_folder_fragment

import android.os.Bundle


/**
 * This listener is used to communicate between fragments.
 */
interface FragmentListener {
    fun onFragmentDataPassed(bundle: Bundle?)
}