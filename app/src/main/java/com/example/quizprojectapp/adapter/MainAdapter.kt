package com.example.quizprojectapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.ProgrammerModel
import com.example.quizprojectapp.databinding.ItemMainBinding

class MainAdapter(val onClick:(ProgrammerModel) -> Unit):ListAdapter<ProgrammerModel, MainAdapter.MainViewHolder>(MainDiff()) {


    inner class MainViewHolder(val binding: ItemMainBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ProgrammerModel) {
            binding.tvC.text = model.name
            Glide.with(binding.root)
                .load(model.img)
                .into(binding.imgC)
            itemView.setOnClickListener {
                onClick(model)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MainDiff:DiffUtil.ItemCallback<ProgrammerModel>() {
    override fun areItemsTheSame(oldItem: ProgrammerModel, newItem: ProgrammerModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProgrammerModel, newItem: ProgrammerModel): Boolean {
        return oldItem == newItem
    }

}
