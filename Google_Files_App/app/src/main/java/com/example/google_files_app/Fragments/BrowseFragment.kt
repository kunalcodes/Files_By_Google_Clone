package com.example.google_files_app.Fragments

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.google_files_app.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.fragment_browse.*
import storage_folder_fragment.FolderAdapter
import storage_folder_fragment.FragmentCategories
import storage_folder_fragment.FragmentFolder
import storage_folder_fragment.OnSelect
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class BrowseFragment : Fragment() ,OnSelect {
    private lateinit var fragmentManager2: FragmentManager
    var storage: File? = null
    private var fileList = ArrayList<File>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickOfCategories()
        runTimePermission()
        buildList()
        layoutStorageInternal.setOnClickListener {
            fragmentManager2 = requireActivity().supportFragmentManager
            launchFirstFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val  view = inflater.inflate(R.layout.fragment_browse,container,false)
        buildList()

        return view
    }

    private fun launchFirstFragment() {
        val fragmentTransaction = fragmentManager2.beginTransaction()
        val fragmentFolder = FragmentFolder()
        fragmentTransaction.add(R.id.container, fragmentFolder, "tag")
            .addToBackStack("LaunchFolderFragment").commit()

    }



       private fun onClickOfCategories() {
           layoutCategoryImages.setOnClickListener {
           val bundle = Bundle()
           bundle.putString("type", "image")
               val fragmentCategories = FragmentCategories()
               fragmentCategories.arguments = bundle
               requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                   .addToBackStack(null).commit()
           }
           layoutCategoryVideos.setOnClickListener {
               val bundle = Bundle()
               bundle.putString("type", "video")
               val fragmentCategories = FragmentCategories()
               fragmentCategories.arguments = bundle
               requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                   .addToBackStack(null).commit()

           }
           layoutCategoryAudio.setOnClickListener {
               val bundle = Bundle()
               bundle.putString("type", "audio")
               val fragmentCategories = FragmentCategories()
               fragmentCategories.arguments = bundle
               requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                   .addToBackStack(null).commit()

           }
           layoutCategoryDocuments.setOnClickListener {

               val bundle = Bundle()
               bundle.putString("type", "docs")
               val fragmentCategories = FragmentCategories()
               fragmentCategories.arguments = bundle
               requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                   .addToBackStack(null).commit()
           }
           layoutCategoryApps.setOnClickListener {
               val bundle = Bundle()
               bundle.putString("type", "apps")
               val fragmentCategories = FragmentCategories()
               fragmentCategories.arguments = bundle
               requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                   .addToBackStack(null).commit()

           }
           layoutCategoryDownloads.setOnClickListener {
               val bundle = Bundle()
               bundle.putString("type", "downloads")
               val fragmentCategories = FragmentCategories()
               fragmentCategories.arguments = bundle
               requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                   .addToBackStack(null).commit()

           }


       }




       private fun buildList() {
           val internalStorage = System.getenv("EXTERNAL_STORAGE")!!
           storage = File(internalStorage)
           try {
               val  data = requireArguments().getString("path").toString()
               val file = File(data)
               storage = file
           } catch (e: Exception) {
               e.printStackTrace()
           }
       }

       private fun runTimePermission() {
           Dexter.withContext(context).withPermissions(
               Manifest.permission.WRITE_EXTERNAL_STORAGE,
               Manifest.permission.READ_EXTERNAL_STORAGE
           ).withListener(object : MultiplePermissionsListener {
               @RequiresApi(Build.VERSION_CODES.N)
               override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                   displayFile()
               }

               override fun onPermissionRationaleShouldBeShown(
                   list: List<PermissionRequest>,
                   permissionToken: PermissionToken
               ) {
                   permissionToken.continuePermissionRequest()
               }
           }).check()
       }

       @RequiresApi(Build.VERSION_CODES.N)
       private fun findFiles(file: File?): ArrayList<File> {
           val arrayList = ArrayList<File>()
           val files = file?.listFiles()
           if (files != null) {
               for (singleFile in files) {
                   if (singleFile.isDirectory && !singleFile.isHidden) {
                       arrayList.addAll(findFiles(singleFile))
                   }else if(singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpeg") || singleFile.name.lowercase(
                           Locale.getDefault()
                       )
                           .endsWith(".jpg")
                       || singleFile.name.lowercase(Locale.getDefault()).endsWith(".mp3") || singleFile.name.lowercase(
                           Locale.getDefault())
                           .endsWith(".mp4")
                       || singleFile.name.lowercase(Locale.getDefault()).endsWith(".pdf")){

                       arrayList.add(singleFile)
                   }
               }
           }
           arrayList.sortWith(Comparator.comparing(File::lastModified).reversed())
           return arrayList
       }

       @RequiresApi(Build.VERSION_CODES.N)
       private fun displayFile() {

           val mRecyclerView: RecyclerView? = requireView().findViewById(R.id.recyclerview)
           mRecyclerView?.setHasFixedSize(true)
           if (mRecyclerView != null) {
               mRecyclerView.layoutManager = LinearLayoutManager(context)
           }
           fileList = java.util.ArrayList()
           fileList.addAll(findFiles(storage))

           val  adapter = context?.let { FolderAdapter(it, fileList, FragmentFolder()) }
           mRecyclerView?.adapter = adapter


       }

    override  fun onClick(file: File?) {
        if (file != null) {
            if (file.isDirectory) {
                val bundle = Bundle()
                bundle.putString("path", file.absolutePath)
                val fragmentFolder = FragmentFolder()
                fragmentFolder.arguments = bundle
                requireFragmentManager().beginTransaction().replace(R.id.container, fragmentFolder)
                    .addToBackStack(null).commit()
            }
        }
    }



    override fun onLongSelect(file: File?) {}

}