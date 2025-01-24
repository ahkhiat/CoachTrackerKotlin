package com.devid_academy.coachtrackerkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast


class CreateEventFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_event_create, container, false)

        var buttonContainer = view.findViewById<LinearLayout>(R.id.fg_create_event_buttonContainer)

        val categories = getFakeCategories()

        categories.forEach { category ->
            val categoryButton = Button(requireContext()).apply {
                text = category.name

                setOnClickListener {

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fg_container, CreateEventFragment())
                        .commit()
                }
            }
            buttonContainer.addView(categoryButton)
        }
    return view



    }


}

//Données attendues :

//        fetch de la table event type pour afficher autant de boutons que de catégories
// reflechir pour ajouter un champ "isVisible" pour que seuls les catés choisies comme
// visibles s'affichent

// Les boutons dirigent vers les frag AddMatch, AddTraining, AddCamp