package com.devid_academy.coachtrackerkotlin.presentation.ui.coach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devid_academy.coachtrackerkotlin.R


class AddMatchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_match, container, false)
    }


}

// Formulaire avec 3 inputs :
// - date
// - equipe adverse
// - lieux

