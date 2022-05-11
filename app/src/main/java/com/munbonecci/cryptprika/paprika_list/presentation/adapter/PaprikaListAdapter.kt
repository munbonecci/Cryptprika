package com.munbonecci.cryptprika.paprika_list.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import coil.load
import com.munbonecci.cryptprika.common.Constants.CHART_7_DAYS
import com.munbonecci.cryptprika.common.Constants.CHART_BASE_URL
import com.munbonecci.cryptprika.common.Constants.COIN_LOGO_BASE_URL
import com.munbonecci.cryptprika.common.Constants.LOGO_PNG
import com.munbonecci.cryptprika.databinding.LayoutPaprikaItemBinding
import com.munbonecci.cryptprika.paprika_list.domain.model.Coin

class PaprikaListAdapter(private val clickListener: OnClickListener) :
    ListAdapter<Coin, ViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id;
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HolderPaprika(
            LayoutPaprikaItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as HolderPaprika).bind(item, position)
    }

    inner class HolderPaprika(
        private val binding: LayoutPaprikaItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Coin, position: Int) {
            binding.coinNameText.text = "${item.rank} - ${item.name} (${item.symbol})"
            binding.coinImageView.load("$COIN_LOGO_BASE_URL${item.id}$LOGO_PNG")
            binding.coinChartView.load("$CHART_BASE_URL${item.id}$CHART_7_DAYS")

            itemView.setOnClickListener {
                clickListener.onItemClick(item, position)
            }
        }
    }

    interface OnClickListener {
        fun onItemClick(coin: Coin, position: Int)
    }
}