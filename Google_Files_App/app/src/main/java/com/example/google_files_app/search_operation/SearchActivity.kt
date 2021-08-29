package com.example.google_files_app.search_operation

import android.Manifest
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.google_files_app.R
import com.example.google_files_app.storage_folder_fragment.FolderAdapter
import com.example.google_files_app.storage_folder_fragment.FragmentFolder
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_search.*
import storage_folder_fragment.FragmentCategories
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        onClickOperations()
    }

    private fun onClickOperations() {
        ivSearchBack.setOnClickListener {
            onBackPressed()
        }
        etSearchItem.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.length == 0){
                        ivCancelSearch.visibility = View.INVISIBLE
                    } else {
                        ivCancelSearch.visibility = View.VISIBLE
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        ivCancelSearch.setOnClickListener {
            etSearchItem.text.clear()
        }
    }

}