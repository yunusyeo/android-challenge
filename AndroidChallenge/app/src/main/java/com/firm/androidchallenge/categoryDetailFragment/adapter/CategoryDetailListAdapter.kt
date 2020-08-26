package com.firm.androidchallenge.categoryDetailFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.firm.androidchallenge.R
import com.firm.androidchallenge.categoryDetailFragment.CategoryDetailFragmentItemClick
import com.firm.androidchallenge.databinding.BindingItemCategoryDetail
import com.firm.androidchallenge.install.Application
import com.firm.androidchallenge.model.Sound

class CategoryDetailListAdapter(val listener: CategoryDetailFragmentItemClick) :
    ListAdapter<Sound, CategoryDetailListAdapter.MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category_detail,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sound = getItem(position)
        holder.binding.sound = sound
        holder.binding.executePendingBindings()
        //Favori listesindeki veriler ile buradaki veriler karşılaştırılarak,
        //veri favorilistesinde var ise
        //icon ve veriler güncelleniyor.
        if (!Application.favoriteList.isNullOrEmpty()) {
            for (soundFavorite: Sound in Application.favoriteList) {
                if (soundFavorite.title.equals(sound.title)) {
                    sound.isFavorite = true
                    sound.id = soundFavorite.id
                    holder.binding.addAndRemoveBtn.setImageResource(R.drawable.ic_favorite_list_add)
                }
            }
        }

        holder.binding.addAndRemoveBtn.setOnClickListener {
            if (sound.isFavorite) {
                sound.isFavorite = false
                listener.onItemClick(false, sound)
                holder.binding.addAndRemoveBtn.setImageResource(R.drawable.ic_favorite_list_remove)
            } else {
                sound.isFavorite = true
                listener.onItemClick(true, sound)
                holder.binding.addAndRemoveBtn.setImageResource(R.drawable.ic_favorite_list_add)
            }
        }
    }

    class MyViewHolder(val binding: BindingItemCategoryDetail) :
        RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Sound>() {
        override fun areItemsTheSame(oldItem: Sound, newItem: Sound): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Sound, newItem: Sound): Boolean {
            return oldItem == newItem
        }

    }
}