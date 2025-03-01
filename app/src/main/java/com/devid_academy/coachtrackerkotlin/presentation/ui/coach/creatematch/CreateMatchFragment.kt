package com.devid_academy.coachtrackerkotlin.presentation.ui.coach.creatematch

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import com.devid_academy.coachtrackerkotlin.databinding.FragmentCreateMatchBinding
import com.devid_academy.coachtrackerkotlin.util.fillSpinner

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

//        spinnerViewModel.getVisitorTeamList()
//        spinnerViewModel.getStadiumList()
//        spinnerViewModel.getSeasonList()

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

            fillSpinner(spinnerViewModel.visitorTeamList, createMatchFgSpinnerVisitorTeam)
            fillSpinner(spinnerViewModel.stadiumList, createMatchFgSpinnerStadium)
            fillSpinner(spinnerViewModel.seasonList, createMatchFgSpinnerSeason)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}