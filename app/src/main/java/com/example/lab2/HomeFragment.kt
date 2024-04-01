package com.example.lab2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab2.databinding.FragmentHomeBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()
    private var searchJob: Job? = null
    private val debouncePeriod = 500L
    private var _binding: FragmentHomeBinding? = null

    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")

    private lateinit var listAdapter: MyListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = MyListAdapter(requireContext()) {launchDetailFragment(it)}

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter

        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchJob?.cancel()
                searchJob = viewLifecycleOwner.lifecycleScope.launch {
                    delay(debouncePeriod)
                    val updatedRequest = viewModel.request.value?.copy(
                        name = newText
                    ) ?: Request()
                    viewModel.updateRequest(updatedRequest)
                }
                return true
            }
        })

        viewModel.cats.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }

        viewModel.request.observe(viewLifecycleOwner) { request ->
            Log.d("RequestObserver", "Observing request: $request")
            if (request.isEmpty()) {
                viewModel.clearCats()
            } else {
                viewModel.getCats()
            }
        }


        binding.buttonFilter.setOnClickListener {
            launchFilterFragment()
        }

        binding.buttonResetFilters.setOnClickListener {
            viewModel.initRequest()
            binding.searchView.setQuery("", false)

        }

    }

    private fun launchFilterFragment() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFilterFragment())
    }

    private fun launchDetailFragment(cat:Cat) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(cat))
    }

}