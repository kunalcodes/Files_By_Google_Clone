package my.kunal.file_explorer.storage_folder_fragment

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import my.kunal.file_explorer.R
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class FragmentCategories : Fragment(), OnSelect {




    private var fileList = ArrayList<File>()
    var path: File? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        runTimePermission()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        buildList()


        return view
    }

    private fun buildList() {
        val bundle: Bundle? = this.arguments

        if (bundle != null) {
           path = if (bundle.getString("type").equals("downloads")) {
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            }

            else {
                Environment.getExternalStorageDirectory()
            }

             if (bundle.getString("type").equals("pictures")) {
                path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            }
            if (bundle.getString("type").equals("downloadsImage")) {
                path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            }
            if (bundle.getString("type").equals("cameraImage")) {
                path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            }


        }
    }

    private fun runTimePermission() {
        Dexter.withContext(context).withPermissions(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
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

    private fun findFiles(file: File?): ArrayList<File> {
        val arrayList = ArrayList<File>()
        val files = file?.listFiles()
        if (files != null) {
            for (singleFile in files) {

                if (singleFile.isDirectory && !singleFile.isHidden) {
                    arrayList.addAll(findFiles(singleFile))
                } else {
                    when (arguments?.getString("type")) {
                        "image" ->
                            if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpeg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".png")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".bmp")
                            ) {
                                arrayList.add(singleFile)

                            }
                        "pictures" ->
                            if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpeg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".png")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".bmp")
                            ) {
                                arrayList.add(singleFile)

                            }
                        "downloadsImage" ->
                            if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpeg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".png")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".bmp")
                            ) {
                                arrayList.add(singleFile)

                            }
                        "cameraImage" ->
                            if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpeg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".png")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".bmp")
                            ) {
                                arrayList.add(singleFile)

                            }

                        "video" ->
                            if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".mp4")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".mkv")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".flv")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".avi")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".gif")
                            ) {
                                arrayList.add(singleFile)

                            }
                        "audio" ->
                            if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".mp3")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".m4a")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".wav")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".wma")
                            ) {
                                arrayList.add(singleFile)

                            }

                        "apps" ->
                            if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".apk")) {
                                arrayList.add(singleFile)
                            }
                        "docs" ->
                            if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".pdf")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".doc")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".xml")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".xls")
                            ) {
                                arrayList.add(singleFile)
                            }
                        "downloads" ->
                           /* if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpeg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpg")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".png")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".mp4")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".wav")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".apk")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".pdf")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".doc")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".xml")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".mp3")
                                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".m4a")
                            ) {*/
                                arrayList.add(singleFile)

                           // }
                    }
                }
            }

        }

        return arrayList
    }
    private fun displayFile() {

        val mRecyclerView: RecyclerView? =
            requireView().findViewById(R.id.recyclerviewCategories)
        mRecyclerView?.setHasFixedSize(true)
        if (mRecyclerView != null) {
            mRecyclerView.layoutManager = LinearLayoutManager(context)
        }
        fileList = java.util.ArrayList()
        fileList.addAll(findFiles(path))

        val adapter = context?.let { FolderAdapter(it, fileList, FragmentFolder()) }
        mRecyclerView?.adapter = adapter


    }

    override fun onClick(file: File?) {
        if (file != null) {
            if (file.isDirectory) {
                val bundle = Bundle()
                bundle.putString("path", file.absolutePath)
                val blankFragment = FragmentCategories()
                blankFragment.arguments = bundle
                requireFragmentManager().beginTransaction()
                    .replace(R.id.container, blankFragment)
                    .addToBackStack(null).commit()
            }
        }
    }


    override fun onLongSelect(file: File?) {}


}