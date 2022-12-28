package com.sol.fakestoreapiapp.utils

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Activity.showMessage(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun Fragment.showMessage(message:String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
}