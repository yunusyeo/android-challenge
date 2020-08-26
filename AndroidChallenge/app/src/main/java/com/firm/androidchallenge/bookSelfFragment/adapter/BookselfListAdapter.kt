package com.firm.androidchallenge.bookSelfFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.firm.androidchallenge.R
import com.firm.androidchallenge.bookSelfFragment.BookselFragmentItemClick
import com.firm.androidchallenge.databinding.BindingItemBookself
import com.firm.androidchallenge.model.Category

class BookselfListAdapter(val listener:BookselFragmentItemClick) :
    ListAdapter<Category, BookselfListAdapter.MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_bookself,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = getItem(position)
        holder.binding.category = category
        holder.binding.executePendingBindings()
        holder.binding.cardView.setOnClickListener {
            listener.onItemClick(category)
        }
    }

    class MyViewHolder(val binding: BindingItemBookself) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }
}