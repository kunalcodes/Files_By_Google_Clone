package storage_folder_fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.google_files_app.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.item_view.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class FragmentFolder : Fragment(), OnSelect {

    var storage: File? = null
    private var fileList = ArrayList<File>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runTimePermission()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val  view = inflater.inflate(R.layout.fragment_folder,container,false)
        buildList()

        return view
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
        val files = file!!.listFiles()!!
        for (singleFile in files) {
            if (singleFile.isDirectory && !singleFile.isHidden) {
                arrayList.add(singleFile)
            }
        }
        for (singleFile in files) {

            if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpeg") || singleFile.name.lowercase(
                    Locale.getDefault()
                )
                    .endsWith(".jpg")
                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".mp3") || singleFile.name.lowercase(
                    Locale.getDefault()
                )
                    .endsWith(".mp4")
                || singleFile.name.lowercase(Locale.getDefault()).endsWith(".pdf")
            ) {

                arrayList.add(singleFile)
            }
        }
        return arrayList
    }

    private fun displayFile() {

        val mRecyclerView: RecyclerView? = requireView().findViewById(R.id.recyclerview)
        mRecyclerView?.setHasFixedSize(true)
        if (mRecyclerView != null) {
            mRecyclerView.layoutManager = LinearLayoutManager(context)
        }
        fileList = java.util.ArrayList()
        fileList.addAll(findFiles(storage))

        val  adapter = context?.let { FolderAdapter(it, fileList, this) }
        mRecyclerView?.adapter = adapter


    }

    override  fun onClick(file: File?) {
        if (file != null) {
            if (file.isDirectory) {
                val bundle = Bundle()
                bundle.putString("path", file.absolutePath)
                val blankFragment = FragmentFolder()
                blankFragment.arguments = bundle
                requireFragmentManager().beginTransaction().replace(R.id.container, blankFragment)
                    .addToBackStack(null).commit()
            }
        }
    }



    override fun onLongSelect(file: File?) {}
}