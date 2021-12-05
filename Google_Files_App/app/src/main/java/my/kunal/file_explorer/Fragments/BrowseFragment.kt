package my.kunal.file_explorer.Fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.kunal.file_explorer.SideActivityOperation
import my.kunal.file_explorer.storage_folder_fragment.FolderAdapter
import my.kunal.file_explorer.storage_folder_fragment.FragmentFolder
import my.kunal.file_explorer.storage_folder_fragment.OnSelect
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.fragment_browse.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.kunal.file_explorer.R
import my.kunal.file_explorer.storage_folder_fragment.FileSize
import my.kunal.file_explorer.storage_folder_fragment.FragmentCategories
import java.io.File


class BrowseFragment : Fragment(), OnSelect {
    private lateinit var fragmentManager2: FragmentManager
    var storage: File? = null

    private var fileList = ArrayList<File>()
    private lateinit var selectedSideActivityOperation: SideActivityOperation

    private lateinit var mTvVideosSize: TextView
    private lateinit var mTvImagesSize: TextView
    private lateinit var mTvDocumentsSize: TextView
    private lateinit var mTvAudioSize: TextView
    private lateinit var mTvAppsSize: TextView
    private lateinit var mTvDownloadsSize: TextView
    private lateinit var mTvInternalStorageSize: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity = context as Activity
        selectedSideActivityOperation = activity as SideActivityOperation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickOfCategories()

        mTvVideosSize = view.findViewById(R.id.tvVideosSize)
        mTvImagesSize = view.findViewById(R.id.tvImagesSize)
        mTvDocumentsSize = view.findViewById(R.id.tvDocumentsSize)
        mTvAudioSize = view.findViewById(R.id.tvAudioSize)
        mTvAppsSize = view.findViewById(R.id.tvAppsSize)
        mTvDownloadsSize = view.findViewById(R.id.tvDownloadsSize)
        mTvInternalStorageSize = view.findViewById(R.id.tvInternalStorageSize)


        var freeMemory = ""
        CoroutineScope(Dispatchers.Default).launch {
            freeMemory = freeMemory().toString() + " GB free"
            CoroutineScope(Dispatchers.Main).launch {
                mTvInternalStorageSize.text = freeMemory
            }
        }

        layoutStorageInternal.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Internal Storage")
            fragmentManager2 = requireActivity().supportFragmentManager
            launchFirstFragment()
        }

        runTimePermission(view)
        /*
        commented the method, for debugging
         */
//        buildList()

    }

    fun freeMemory(): Long {
        val statFs = StatFs(Environment.getRootDirectory().absolutePath)
        return (statFs.availableBlocksLong * statFs.blockSize) / (1024 * 1024 * 100).toLong()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_browse, container, false)

        return view
    }

    private fun launchFirstFragment() {
        val fragmentTransaction = fragmentManager2.beginTransaction()
        val fragmentFolder = FragmentFolder()
        fragmentTransaction.replace(R.id.container, fragmentFolder, "tag")
            .addToBackStack("LaunchFolderFragment").commit()

    }


    private fun onClickOfCategories() {
        layoutCategoryImages.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Images")

            val bundle = Bundle()
            bundle.putString("type", "image")
            val fragmentCategories = FragmentCategories()
            fragmentCategories.arguments = bundle
            requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                .addToBackStack(null).commit()
        }
        layoutCategoryVideos.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Videos")

            val bundle = Bundle()
            bundle.putString("type", "video")
            val fragmentCategories = FragmentCategories()
            fragmentCategories.arguments = bundle
            requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                .addToBackStack(null).commit()

        }
        layoutCategoryAudio.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Audio")

            val bundle = Bundle()
            bundle.putString("type", "audio")
            val fragmentCategories = FragmentCategories()
            fragmentCategories.arguments = bundle
            requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                .addToBackStack(null).commit()

        }
        layoutCategoryDocuments.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Documents")
            val bundle = Bundle()
            bundle.putString("type", "docs")
            val fragmentCategories = FragmentCategories()
            fragmentCategories.arguments = bundle
            requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                .addToBackStack(null).commit()
        }
        layoutCategoryApps.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Apps")

            val bundle = Bundle()
            bundle.putString("type", "apps")
            val fragmentCategories = FragmentCategories()
            fragmentCategories.arguments = bundle
            requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                .addToBackStack(null).commit()

        }
        layoutCategoryDownloads.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Downloads")

            val bundle = Bundle()
            bundle.putString("type", "downloads")
            val fragmentCategories = FragmentCategories()
            fragmentCategories.arguments = bundle
            requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                .addToBackStack(null).commit()

        }
        mcDownloads.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Images")

            val bundle = Bundle()
            bundle.putString("type", "downloadsImage")
            val fragmentCategories = FragmentCategories()
            fragmentCategories.arguments = bundle
            requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                .addToBackStack(null).commit()
        }
        mcPicture.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Images")

            val bundle = Bundle()
            bundle.putString("type", "pictures")
            val fragmentCategories = FragmentCategories()
            fragmentCategories.arguments = bundle
            requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                .addToBackStack(null).commit()

        }
        mcCameraImage.setOnClickListener {
            selectedSideActivityOperation.onSideActivitySelected("Images")
            val bundle = Bundle()
            bundle.putString("type", "cameraImage")
            val fragmentCategories = FragmentCategories()
            fragmentCategories.arguments = bundle
            requireFragmentManager().beginTransaction().replace(R.id.container, fragmentCategories)
                .addToBackStack(null).commit()
        }

    }


    private fun buildList() {
        CoroutineScope(Dispatchers.IO).launch {
            val internalStorage = System.getenv("EXTERNAL_STORAGE")!!
            storage = File(internalStorage)
            try {
                val data = requireArguments().getString("path").toString()
                val file = File(data)
                storage = file
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun runTimePermission(view: View) {
        Dexter.withContext(context).withPermissions(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                displayFile(view)
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

/*
                if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpeg")
                    || singleFile.name.lowercase(Locale.getDefault()).endsWith(".jpg")
                    || singleFile.name.lowercase(Locale.getDefault()).endsWith(".png")
                ) {
                    imageSize += singleFile.length()
                }
                if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".mp4")
                    || singleFile.name.lowercase(Locale.getDefault()).endsWith(".wav")
                ) {
                    videoSize += singleFile.length()
                }
                if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".mp3")
                    || singleFile.name.lowercase(Locale.getDefault()).endsWith(".m4a")
                ) {
                    audioSize += singleFile.length()
                }
                if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".apk")) {
                    appSize += singleFile.length()
                }
                if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".pdf")
                    || singleFile.name.lowercase(Locale.getDefault()).endsWith(".doc")
                    || singleFile.name.lowercase(Locale.getDefault()).endsWith(".xml")
                ) {
                    docSize += singleFile.length()
                }*/

                arrayList.add(singleFile)
            }
        }

        if (file != null) {
            CoroutineScope(Dispatchers.Main).launch {
                Log.d("Tag", FileSize.getVideoSize(file).toString())
                mTvVideosSize.text = sizeFormat(FileSize.getVideoSize(file))
                mTvImagesSize.text = sizeFormat(FileSize.getImageSize(file))
                mTvDocumentsSize.text = sizeFormat(FileSize.getDocSize(file))
                mTvAudioSize.text = sizeFormat(FileSize.getAudioSize(file))
                mTvAppsSize.text = sizeFormat(FileSize.getAppSize(file))
                mTvDownloadsSize.text = sizeFormat(FileSize.getDownloadSize(file))
            }

        }
        return arrayList
    }

    private fun sizeFormat(long: Long): String {

        if (long < 1000) {
            return "$long B"
        } else if (long > 1000 && long < 1000 * 1000) {
            return (long / 1024).toString() + " KB"
        } else if (long > 1000 * 1000 && long < 1000 * 1024 * 1024) {
            return (long / (1000 * 1000)).toString() + " MB"
        }
        return (long / (1000 * 1024 * 1024)).toString() + " GB"

    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun displayFile(view: View) {

        val mRecyclerView: RecyclerView? = view.findViewById(R.id.recyclerview)
        mRecyclerView?.setHasFixedSize(true)
        if (mRecyclerView != null) {
            mRecyclerView.layoutManager = LinearLayoutManager(context)
        }
        fileList = java.util.ArrayList()
        CoroutineScope(Dispatchers.IO).launch {
            fileList.addAll(findFiles(storage))
        }

        val adapter = context?.let { FolderAdapter(it, fileList, FragmentFolder()) }
        mRecyclerView?.adapter = adapter


    }

    override fun onClick(file: File?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (file != null) {
                if (file.isDirectory) {
                    val bundle = Bundle()
                    bundle.putString("path", file.absolutePath)
                    val fragmentFolder = FragmentFolder()
                    fragmentFolder.arguments = bundle
                    requireFragmentManager().beginTransaction()
                        .replace(R.id.container, fragmentFolder)
                        .addToBackStack(null).commit()
                }
            }
        }

    }

    override fun onLongSelect(file: File?) {}


}


