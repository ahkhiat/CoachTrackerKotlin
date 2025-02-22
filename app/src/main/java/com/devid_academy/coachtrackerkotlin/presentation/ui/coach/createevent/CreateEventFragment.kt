package com.devid_academy.coachtrackerkotlin.presentation.ui.coach.createevent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.databinding.FragmentEventCreateBinding
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvcalendar.RvCalendarViewModel


class CreateEventFragment : Fragment() {

    private var _binding: FragmentEventCreateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RvCalendarViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        var buttonContainer = view.findViewById<LinearLayout>(R.id.fg_create_event_buttonContainer)

        viewModel.getEventTypes()

        viewModel.categories.observe(requireActivity()) {
            buttonContainer.removeAllViews()

            it.forEach {
                Log.d("EventCreateFragment", "Categories reçues: $it")
                val categoryButton = Button(requireContext()).apply {
                    text = it.name
                    setOnClickListener {
//                            parentFragmentManager.commit {
//                                replace(R.id.fg_container, CreateMatchFragment())
//                                addToBackStack(null)
//                            }
                        navController.navigate(R.id.action_createEventFragment_to_createMatchFragment)
                    }
                }
                buttonContainer.addView(categoryButton)
            }
        }


    }


}

//Données attendues :

//        fetch de la table event type pour afficher autant de boutons que de catégories
// reflechir pour ajouter un champ "isVisible" pour que seuls les catés choisies comme
// visibles s'affichent

// Les boutons dirigent vers les frag AddMatch, AddTraining, AddCamp