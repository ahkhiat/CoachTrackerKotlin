package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvcalendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.repository.EventRepository
import com.devid_academy.coachtrackerkotlin.databinding.FragmentRvCalendarBinding
import com.devid_academy.coachtrackerkotlin.util.EVENT_KEY
import com.devid_academy.coachtrackerkotlin.util.navController

class RvCalendarFragment : Fragment() {

    private var _binding: FragmentRvCalendarBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventAdapter: RvCalendarAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventRepository: EventRepository
    private val viewmodel: RvCalendarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRvCalendarBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            eventAdapter = RvCalendarAdapter(
                onItemClick = {
                    val bundle = Bundle()
                    bundle.putParcelable(EVENT_KEY, it)
                    navController().navigate(R.id.action_rvCalendarFragment_to_showEventFragment, bundle )

                }
            )

            rvCalendar.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = eventAdapter
            }

            rvBtnCreateEvent.setOnClickListener {
                navController().navigate(R.id.action_rvCalendarFragment_to_createEventFragment)
            }


            refresh()

            viewmodel.isLoading.observe(viewLifecycleOwner) {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }




        }




    }
    private fun refresh() {
        binding.progressBar.visibility = View.VISIBLE
        EventRepository().getEvents {
            eventAdapter.submitList(it)
//            recyclerView.scrollToPosition(eventAdapter.itemCo

            Log.d("DEBUG", "Nombre d'événements récupérés : ${eventAdapter.itemCount}")
        }
    }

}


// Sur cette page, on va afficher le calendrier de tous les événements à venir,
// Entrainements et matchs, sous forme d'un RecyclerView

// Données attendues :
// Tous les Events : - Date
// - Visitor team
// - Stadium