package com.devid_academy.coachtrackerkotlin.util

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.devid_academy.coachtrackerkotlin.R

fun Fragment.navController() = findNavController()

fun getStatus(status: Int): Int {
    return when (status) {
        0 -> R.string.pending
        1 -> R.string.accepted
        2 -> R.string.rejected
        else -> R.string.status_undefined
    }
}

fun <T> Fragment.fillSpinner(liveData: LiveData<List<T>>, spinner: Spinner){
    liveData.observe(viewLifecycleOwner) {
        if (it.isNotEmpty()) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                it
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}

fun makeToast(context: Context ,message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}