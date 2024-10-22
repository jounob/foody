package com.example.foodyapp.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyapp.adapters.RecipesAdapters
import com.example.foodyapp.databinding.FragmentRecipesBinding
import com.example.foodyapp.ui.NetworkResult
import com.example.foodyapp.ui.viewmodels.MainViewModel
import com.example.foodyapp.ui.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val adapter by lazy { RecipesAdapters() }
    private lateinit var binding: FragmentRecipesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecipesBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()
        requestApiData()
        return binding.root
    }

    private fun requestApiData() {
        Log.d("RecipesFragment", "requestApiData called!")
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { it ->
            when (it) {
                is NetworkResult.Success -> {
                    Log.d("RecipesFragment Success", "${it.data}")
                    hideShimmerEffect()
                    it.data?.let {
                        Log.d("RecipesFragmentAdapter", "$adapter.")
                        adapter.setData(it)
                    }
                }

                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_LONG).show()
                }

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recipesRV.adapter = adapter
        binding.recipesRV.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.shimmer.showShimmer(true)
        binding.shimmer.visibility = VISIBLE
        binding.recipesRV.visibility = GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmer.hideShimmer()
        binding.shimmer.visibility = GONE
        binding.recipesRV.visibility = VISIBLE
    }
}
