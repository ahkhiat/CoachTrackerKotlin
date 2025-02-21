package com.devid_academy.coachtrackerkotlin.presentation.ui.coach

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.dto.VisitorTeamDTO
import com.devid_academy.coachtrackerkotlin.databinding.FragmentCreateMatchBinding
import com.devid_academy.coachtrackerkotlin.presentation.viewmodel.EventViewModel
import com.devid_academy.coachtrackerkotlin.presentation.viewmodel.SpinnerViewModel
import kotlinx.coroutines.launch

class CreateMatchFragment : Fragment() {

    private var _binding: FragmentCreateMatchBinding? = null
    private val binding get() = _binding!!
    private val spinnerViewModel: SpinnerViewModel by activityViewModels()

    private lateinit var visitorTeamSpinner: Spinner
    private lateinit var stadiumSpinner: Spinner
    private lateinit var seasonSpinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerViewModel.getVisitorTeamList()
        spinnerViewModel.getStadiumList()
        spinnerViewModel.getSeasonList()

        with(binding) {

            createMatchFgTvDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                    createMatchFgTvDate.setText(formattedDate)
                }, year, month, day)

                datePickerDialog.show()
            }

            visitorTeamSpinner = createMatchFgSpinnerVisitorTeam
            stadiumSpinner = createMatchFgSpinnerStadium
            seasonSpinner = createMatchFgSpinnerSeason

            lifecycleScope.launch {
                spinnerViewModel.visitorTeamList.collect { visitorTeams ->
                    if (visitorTeams.isNotEmpty()) {
                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            visitorTeams
                        )

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        visitorTeamSpinner.adapter = adapter
                    }
                }
            }

            lifecycleScope.launch {
                spinnerViewModel.stadiumList.collect { stadiumList ->
                    if (stadiumList.isNotEmpty()) {
                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            stadiumList
                        )
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        stadiumSpinner.adapter = adapter
                    }
                }
            }

            lifecycleScope.launch {
                spinnerViewModel.seasonList.collect { seasonList ->
                    if (seasonList.isNotEmpty()) {
                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            seasonList
                        )

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        seasonSpinner.adapter = adapter
                    }
                }
            }





        }






    }


}