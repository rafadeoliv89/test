package com.example.test.features.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ItemMainBinding
import com.example.test.features.main.data.response.ApiResponse
import com.squareup.picasso.Picasso

class MainAdapter(private val items: List<ApiResponse>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ApiResponse) {
            binding.textViewMainRepositoryNameValue.text = data.name
            binding.textViewMainRepositoryCountStartsValue.text = data.starsCount.toString()
            binding.textViewMainRepositoryForkValue.text = data.forksCount.toString()
            data.apiOwner?.let { apiOwner ->
                binding.textViewMainRepositoryNameAuthorValue.text = apiOwner.login
                Picasso.get().load(apiOwner.avatar).into(binding.imageViewMainRepositoryAuthorPhoto)
            }
        }
    }

}