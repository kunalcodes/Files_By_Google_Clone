package com.example.google_files_app.storage_folder_fragment


import android.content.Context
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.text.format.Formatter
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.google_files_app.FileOpener
import com.example.google_files_app.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class FolderAdapter(
    private val context: Context,
    private val file: ArrayList<File>,
    select: FragmentFolder

) :
    RecyclerView.Adapter<FolderViewHolder>() {
    private val select: OnSelect = select

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.mName.text = file[position].name
        holder.mName.isSelected = true
        var item = 0

        if (file[position].isDirectory) {
            val files = file[position].listFiles()
            if(files!= null)
            for (singleFile in files) {
                if (!singleFile.isHidden) {
                    item++

                }
            }

            holder.mDate.text =    SimpleDateFormat("dd MMM yyyy").format(Date(file[position].lastModified()))


        } else {
            holder.mDate.text = Formatter.formatShortFileSize(context, file[position].length()) +" , "+
                    SimpleDateFormat("dd MMM").format(Date(file[position].lastModified()))
        }
        holder.mContainer.setOnClickListener(View.OnClickListener {
            select.onClick(file[position])
        })
        holder.mContainer.setOnLongClickListener(OnLongClickListener {
            select.onLongSelect(file[position])
            true
        })
        if (file[position].name.lowercase(Locale.getDefault()).endsWith("jpeg")
            || file[position].name.lowercase(Locale.getDefault()).endsWith("jpg")
            || file[position].name.lowercase(Locale.getDefault()).endsWith("png")
        ) {

            val myBitmap = BitmapFactory.decodeFile(file[position].absolutePath)
            holder.ivPreviewImage.setImageBitmap(myBitmap)
            holder.ivPreviewImage.visibility = View.VISIBLE

        }
        if (file[position].name.lowercase(Locale.getDefault()).endsWith("mp4")) {
            val bMap = ThumbnailUtils.createVideoThumbnail(
                file[position].absolutePath,
                MediaStore.Video.Thumbnails.MICRO_KIND
            )
            holder.ivPreviewImage.setImageBitmap(bMap)
            holder.ivPreviewImage.visibility = View.VISIBLE

        }
        if(!file[position].isDirectory) {
            holder.mContainer.setOnClickListener(View.OnClickListener {
                FileOpener.openFile(context, file[position])


            })
        }

    }

    override fun getItemCount(): Int {
        return file.size
    }

}
