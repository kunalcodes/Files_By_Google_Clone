package my.kunal.file_explorer.search_operation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_search.*
import my.kunal.file_explorer.R
import java.util.*

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