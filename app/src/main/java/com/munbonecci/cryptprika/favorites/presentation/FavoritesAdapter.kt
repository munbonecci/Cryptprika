package com.munbonecci.cryptprika.favorites.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.munbonecci.cryptprika.common.Constants.COIN_LOGO_BASE_URL
import com.munbonecci.cryptprika.common.Constants.LOGO_PNG
import com.munbonecci.cryptprika.database.favorites.Favorite
import com.munbonecci.cryptprika.databinding.LayoutFavoriteGridItemBinding

class FavoritesAdapter(private val clickListener: OnClickListener) :
    ListAdapter<Favorite, ViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HolderFavorites(
            LayoutFavoriteGridItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as HolderFavorites).bind(item, position)
    }

    inner class HolderFavorites(
        private val binding: LayoutFavoriteGridItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Favorite, position: Int) {
            binding.favoriteNameText.text = item.name
            binding.favoriteCoinImage.load("$COIN_LOGO_BASE_URL${item.id}$LOGO_PNG")

            itemView.setOnClickListener {
                clickListener.onItemClick(item, position)
            }
        }
    }

    interface OnClickListener {
        fun onItemClick(favorite: Favorite, position: Int)
    }
}