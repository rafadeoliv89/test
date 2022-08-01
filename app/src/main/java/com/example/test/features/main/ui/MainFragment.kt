package com.example.test.features.main.ui

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.core.ui.BaseFragment
import com.example.test.databinding.FragmentMainListBinding
import com.example.test.features.main.data.response.ApiResponse
import com.example.test.features.main.ui.adapter.MainAdapter
import com.example.test.features.main.viewmodel.MainViewModel
import com.example.test.features.main.viewstate.MainViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainListBinding>() {

    val viewModel: MainViewModel by viewModels()

    private var adapter: MainAdapter? = null

    override fun layoutId() = R.layout.fragment_main_list

    override fun create() {
        viewModel.getRepositories()
    }

    override fun init() {
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is MainViewState.Loading -> showLoading(it.show)
                is MainViewState.Error -> Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        viewModel.listRepositories.observe(viewLifecycleOwner) {
            setupAdapter(it)
        }
    }

    private fun setupAdapter(list: List<ApiResponse>) {
        adapter = MainAdapter(list)
        binding?.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            recyclerViewMainList.layoutManager = linearLayoutManager
            recyclerViewMainList.adapter = adapter

            recyclerViewMainList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val finalItemCount = recyclerView.layoutManager?.itemCount
                    if (finalItemCount == linearLayoutManager.findLastVisibleItemPosition() + 1) {
                        viewModel.getRepositories()
                    }
                }
            })
        }
    }

}