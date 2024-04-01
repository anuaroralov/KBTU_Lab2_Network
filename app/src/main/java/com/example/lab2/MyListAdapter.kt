package com.example.lab2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab2.databinding.ItemCatBinding

class MyListAdapter(private val context: Context, private val onClickListener: (Cat) -> Unit) :
    ListAdapter<Cat, MyListAdapter.MovieViewHolder>(CatDiffCallback) {

    class MovieViewHolder(
        val binding: ItemCatBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemCatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val cat = getItem(position)
        with(holder.binding) {
            with(cat) {
                tvName.text = name
                tvLength.text =length
                tvOrigin.text= origin

                Glide.with(context)
                    .load(imageLink)
                    .fitCenter()
                    .into(ivImg)


                root.setOnClickListener {
                    onClickListener(cat)
                }
            }
        }
    }

    object CatDiffCallback : DiffUtil.ItemCallback<Cat>() {

        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }
    }
}

