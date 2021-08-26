package com.example.google_files_app.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentManager
import com.example.google_files_app.R
import kotlinx.android.synthetic.main.fragment_browse.*
import storage_folder_fragment.FragmentFolder


class BrowseFragment : Fragment(R.layout.fragment_browse) {
    private lateinit var fragmentManager2: FragmentManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutStorageInternal.setOnClickListener {
            fragmentManager2 = requireActivity().supportFragmentManager
            launchFirstFragment()
        }
    }

    private fun launchFirstFragment() {
        val fragmentTransaction = fragmentManager2.beginTransaction()
        val fragmentFolder = FragmentFolder()
        fragmentTransaction.add(R.id.container, fragmentFolder, "tag").addToBackStack("LaunchFolderFragment").commit()
    }


}