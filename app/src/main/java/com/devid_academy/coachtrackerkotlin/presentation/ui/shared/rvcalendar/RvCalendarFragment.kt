package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvcalendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.manager.AuthManager
import com.devid_academy.coachtrackerkotlin.databinding.FragmentRvCalendarBinding
import com.devid_academy.coachtrackerkotlin.util.EVENT_KEY
import com.devid_academy.coachtrackerkotlin.util.makeToast
import com.devid_academy.coachtrackerkotlin.util.navController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RvCalendarFragment : Fragment() {

    private var _binding: FragmentRvCalendarBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var authManager: AuthManager

    private lateinit var eventAdapter: RvCalendarAdapter
    private val viewmodel: RvCalendarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRvCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        eventAdapter = RvCalendarAdapter(
            onItemClick = {
                navController.navigate(R.id.action_rvCalendarFragment_to_showEventFragment)
            }
        )
        with(binding) {
            rvCalendar.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = eventAdapter
            }
//            swipeRefresh.setOnRefreshListener {
//                viewModel.getArticles()
//                swipeRefresh.isRefreshing = false
//            }
            viewmodel.events.observe(viewLifecycleOwner) {
                eventAdapter.submitList(it)
            }


            rvBtnCreateEvent.setOnClickListener {
                navController.navigate(R.id.action_rvCalendarFragment_to_createEventFragment)
            }


            viewmodel.isLoading.observe(viewLifecycleOwner) {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
            rvBtnLogout.setOnClickListener {
                authManager.logout()
                makeToast(requireContext(), getString(R.string.logout))
                navController.navigate(R.id.action_rvCalendarFragment_to_loginFragment)            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


// Sur cette page, on va afficher le calendrier de tous les événements à venir,
// Entrainements et matchs, sous forme d'un RecyclerView

// Données attendues :
// Tous les Events : - Date
// - Visitor team
// - Stadium