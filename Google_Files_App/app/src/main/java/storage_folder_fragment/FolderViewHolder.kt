package storage_folder_fragment

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.google_files_app.R

class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mName: TextView = itemView.findViewById(R.id.tvName)
    var mDate: TextView = itemView.findViewById(R.id.tvSize)
    var mContainer: CardView = itemView.findViewById(R.id.mContainer)

}