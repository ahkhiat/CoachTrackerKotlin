package com.devid_academy.coachtrackerkotlin.presentation.ui.coach.createevent

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.databinding.FragmentEventCreateBinding
import com.devid_academy.coachtrackerkotlin.util.fillSpinner

class CreateEventFragment : Fragment() {

    private var _binding: FragmentEventCreateBinding? = null
    private val binding get() = _binding!!
    private val createEventViewModel: CreateEventViewModel by activityViewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            groupRadio.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.create_radio_btn1 -> {
                        createMatchFgSpinnerVisitorTeam.visibility = View.INVISIBLE
                    }
                    R.id.create_radio_btn2 -> {
                        createMatchFgSpinnerVisitorTeam.visibility = View.VISIBLE
                    }
                }

            }

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

            fillSpinner(createEventViewModel.visitorTeamList, createMatchFgSpinnerVisitorTeam)
            fillSpinner(createEventViewModel.stadiumList, createMatchFgSpinnerStadium)
            fillSpinner(createEventViewModel.seasonList, createMatchFgSpinnerSeason)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}