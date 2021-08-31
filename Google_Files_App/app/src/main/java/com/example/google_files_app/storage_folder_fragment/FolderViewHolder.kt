package com.example.google_files_app.storage_folder_fragment

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.google_files_app.R

open class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mName: TextView = itemView.findViewById(R.id.tvName)
    var mDate: TextView = itemView.findViewById(R.id.tvSize)
   // var ivPreviewImage: ImageView = itemView.findViewById(R.id.ivFolderImage)
    var mContainer: CardView = itemView.findViewById(R.id.mContainer)
    var ivFileView : ImageView = itemView.findViewById(R.id.ivFolderImage)


}