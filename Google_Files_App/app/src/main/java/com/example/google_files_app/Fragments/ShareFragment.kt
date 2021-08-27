package com.example.google_files_app.Fragments

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.google_files_app.R

class ShareFragment : Fragment() {


    val FINE_LOCATION_REQ = 101
    val WIFI_RQ = 102
    val BLUETOOTH_REQ = 103
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
//
//    private fun checkForPermission(permission:String, name : String , requestCode: Int){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            when{
//                context?.let { ContextCompat.checkSelfPermission(it,permission) } == PackageManager. PERMISSION_GRANTED -> {
//                    Toast.makeText(ContextCompat.checkSelfPermission(permission), "$name permission granted", Toast.LENGTH_SHORT).show()
//                }
//                shouldShowRequestPermissionRationale(permission) ->
//
//            }
//        }
//
//    }
}