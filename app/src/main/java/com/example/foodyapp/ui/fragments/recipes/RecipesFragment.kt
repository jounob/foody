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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyapp.adapters.RecipesAdapters
import com.example.foodyapp.binding_adapters.RecipesRowBinding.Companion.errorImageViewVisibility
import com.example.foodyapp.binding_adapters.RecipesRowBinding.Companion.errorTextViewVisibility
import com.example.foodyapp.data.database.RecipesEntity
import com.example.foodyapp.databinding.FragmentRecipesBinding
import com.example.foodyapp.ui.NetworkResult
import com.example.foodyapp.ui.viewmodels.MainViewModel
import com.example.foodyapp.ui.viewmodels.RecipesViewModel
import com.example.foodyapp.util.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val adapter by lazy { RecipesAdapters() }
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentRecipesBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()
        readDatabase()
        return binding.root
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

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    Log.d("RecipesFragment", "readDatabase called!")
                    adapter.setData(database[0].foodRecipeDM)
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }
                setErrorView(database)
            }
        }
    }

    private fun requestApiData() {
        Log.d("RecipesFragment", "requestApiData called!")
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { it ->
            when (it) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    it.data?.let {
                        adapter.setData(it)
                    }
                }

                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_LONG).show()
                }

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    adapter.setData(database[0].foodRecipeDM)
                }
            }
        }
    }

    private fun setErrorView(database: List<RecipesEntity>? = null) {
        errorTextViewVisibility(binding.errorTV, mainViewModel.recipesResponse.value, database)
        errorImageViewVisibility(binding.errorIV, mainViewModel.recipesResponse.value, database)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
