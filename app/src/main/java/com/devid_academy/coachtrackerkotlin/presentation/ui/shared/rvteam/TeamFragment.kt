package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvteam

import android.graphics.fonts.FontFamily
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import com.devid_academy.coachtrackerkotlin.R
import android.graphics.Typeface
import androidx.fragment.app.viewModels
import com.devid_academy.coachtrackerkotlin.data.dto.TeamDTO
import com.devid_academy.coachtrackerkotlin.databinding.FragmentProfileBinding
import com.devid_academy.coachtrackerkotlin.databinding.FragmentRvCalendarBinding
import com.devid_academy.coachtrackerkotlin.databinding.FragmentTeamBinding
import com.devid_academy.coachtrackerkotlin.util.getStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private var _binding: FragmentTeamBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TeamViewModel by viewModels()
//    private lateinit var team: TeamDTO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            viewModel.teamLiveData.observe(viewLifecycleOwner) {
                Log.i("TEAM FRAG", "TEAM : $it")
                if(it != null ) {

                    fgTeamTvTeamName.text = it.name

                    if((it.players)!!.isNotEmpty()) {
                        Log.i("TEAM", "Players : ${it.players}")

                        for (player in it.players) {
                            val textView = TextView(context).apply {
                                text = "- " + player.user.firstname +
                                         " " + player.user.lastname
                                textSize = 16f
                                setPadding(16, 8, 16, 8)
                                this.typeface = ResourcesCompat.getFont(requireContext(), R.font.poppinsregular)

                            }
                            llPlayers.addView(textView)
                        }
                    } else {
                        Log.i("TEAM", "Pas de joueurs dans l'équipe")
                    }

                    if((it.coaches)!!.isNotEmpty()) {
                        Log.i("TEAM", "Players : ${it.coaches}")

                        for (coach in it.coaches) {
                            val textView = TextView(context).apply {
                                text = "- " + coach.user?.firstname +
                                        " " + coach.user?.lastname
                                textSize = 16f
                                setPadding(16, 8, 16, 8)
                                this.typeface = ResourcesCompat.getFont(requireContext(), R.font.poppinsregular)

                            }
                            llCoaches.addView(textView)
                        }
                    } else {
                        Log.i("TEAM", "Pas de joueurs dans l'équipe")
                    }




                }

            }




        }



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


