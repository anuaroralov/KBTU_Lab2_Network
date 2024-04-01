package com.example.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.lab2.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    private val args by navArgs<DetailFragmentArgs>()

    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cat = args.cat
        with(cat) {
            with(binding) {
                Glide.with(requireActivity())
                    .load(imageLink)
                    .fitCenter()
                    .into(ivImg)

                lengthTextView.text=length
                originTextView.text=origin

                weightRangeTextView.text = getString(R.string.weight_range, minWeight, maxWeight)
                lifeExpectancyRangeTextView.text = getString(R.string.life_expectancy_range, minLifeExpectancy, maxLifeExpectancy)

                familyFriendlyStars.updateStars(familyFriendly ?: 0)
                sheddingStars.updateStars(shedding ?: 0)
                generalHealthStars.updateStars(generalHealth ?: 0)
                playfulnessStars.updateStars(playfulness ?: 0)
                childrenFriendlyStars.updateStars(childrenFriendly ?: 0)
                groomingStars.updateStars(grooming ?: 0)
                intelligenceStars.updateStars(intelligence ?: 0)
                otherPetsFriendlyStars.updateStars(otherPetsFriendly ?: 0)
            }
        }



    }


}