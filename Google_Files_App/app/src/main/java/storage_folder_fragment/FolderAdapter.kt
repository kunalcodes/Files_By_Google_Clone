package storage_folder_fragment


import android.content.Context
import android.text.format.Formatter
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.google_files_app.R
import java.io.File


class FolderAdapter(private val context: Context, private val file: ArrayList<File>, select: OnSelect) :
    RecyclerView.Adapter<FolderViewHolder>() {
    private val select: OnSelect = select

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.mName.text = file[position].name
        holder.mName.isSelected = true
        var item = 0
        if (file[position].isDirectory) {
            val files = file[position].listFiles()
            for (singleFile in files) {
                if (!singleFile.isHidden) {
                    item++
                }
            }
            holder.mDate.text = "$item Files"
        } else {
            holder.mDate.text = Formatter.formatShortFileSize(context ,file[position].length())
        }
        holder.mContainer.setOnClickListener(View.OnClickListener {
            select.onClick(file[position])
        })
        holder.mContainer.setOnLongClickListener(OnLongClickListener {
            select.onLongSelect(file[position])
            true
        })
    }

    override fun getItemCount(): Int {
        return file.size
    }

}
