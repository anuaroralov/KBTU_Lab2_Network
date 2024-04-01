package com.example.lab2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lab2.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()
    private var _binding: FragmentFilterBinding? = null

    private val binding: FragmentFilterBinding
        get() = _binding ?: throw RuntimeException("FragmentFilterBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.request.observe(viewLifecycleOwner) { request ->
            updateUI(request)
        }

        binding.buttonFilter.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {
        val minWeight1 = binding.editTextWeightMinValue.text.toString().toIntOrNull()
        val maxWeight1 = binding.editTextWeightMaxValue.text.toString().toIntOrNull()
        val minLifeExpectancy1 =
            binding.editTextLifeExpectancyMinValue.text.toString().toIntOrNull()
        val maxLifeExpectancy1 =
            binding.editTextLifeExpectancyMaxValue.text.toString().toIntOrNull()

        val familyFriendly1 = getSelectedRadioButtonValue(binding.radioGroupFamilyFriendly)
        val playfulness1 = getSelectedRadioButtonValue(binding.radioGroupPlayfulness)
        val grooming1 = getSelectedRadioButtonValue(binding.radioGroupGrooming)
        val otherPetsFriendly1 = getSelectedRadioButtonValue(binding.radioGroupOtherPetsFriendly)
        val childrenFriendly1 = getSelectedRadioButtonValue(binding.radioGroupChildrenFriendly)

        val updatedRequest = viewModel.request.value?.copy(
            minWeight = minWeight1,
            maxWeight = maxWeight1,
            minLifeExpectancy = minLifeExpectancy1,
            maxLifeExpectancy = maxLifeExpectancy1,
            familyFriendly = familyFriendly1,
            playfulness = playfulness1,
            grooming = grooming1,
            otherPetsFriendly = otherPetsFriendly1,
            childrenFriendly = childrenFriendly1
        ) ?: Request()

        viewModel.updateRequest(updatedRequest)
        Log.d("FilterFragment", "Request LiveData updated")

        findNavController().popBackStack()
    }

    private fun getSelectedRadioButtonValue(radioGroup: RadioGroup): Int? {
        val radioButtonId = radioGroup.checkedRadioButtonId
        if (radioButtonId != -1) {
            val radioButton = radioGroup.findViewById<RadioButton>(radioButtonId)
            return radioButton.text.toString().toIntOrNull()
        }
        return null
    }

    private fun updateUI(request: Request) {
        Log.d("FilterFragment", "Updating UI with request: $request")

        binding.apply {
            editTextWeightMinValue.setText(request.minWeight?.toString() ?: "")
            editTextWeightMaxValue.setText(request.maxWeight?.toString() ?: "")
            editTextLifeExpectancyMinValue.setText(request.minLifeExpectancy?.toString() ?: "")
            editTextLifeExpectancyMaxValue.setText(request.maxLifeExpectancy?.toString() ?: "")
        }

        updateRadioGroup(binding.radioGroupFamilyFriendly, request.familyFriendly)
        updateRadioGroup(binding.radioGroupPlayfulness, request.playfulness)
        updateRadioGroup(binding.radioGroupGrooming, request.grooming)
        updateRadioGroup(binding.radioGroupOtherPetsFriendly, request.otherPetsFriendly)
        updateRadioGroup(binding.radioGroupChildrenFriendly, request.childrenFriendly)
    }


    private fun updateRadioGroup(radioGroup: RadioGroup, value: Int?) {
        value?.let { valValue ->
            radioGroup.children
                .filterIsInstance<RadioButton>()
                .firstOrNull { it.tag?.toString()?.toIntOrNull() == valValue }
                ?.let { radioButton ->
                    radioGroup.check(radioButton.id)
                } ?: radioGroup.clearCheck()
        } ?: radioGroup.clearCheck()
    }


}

