package com.altintasomer.application.accuweathercase.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.altintasomer.application.accuweathercase.data.network.model.local.SearchingItem
import com.altintasomer.application.accuweathercase.databinding.ItemLayoutBinding

class SearchingListAdapter(
    private var searchingItemList: ArrayList<SearchingItem>,
    private val onItemClickListener: OnItemClickListener? = null
) :
    RecyclerView.Adapter<SearchingListAdapter.SearchingItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(searchingItem: SearchingItem?)
    }

    class SearchingItemViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateList(searchingItemList: ArrayList<SearchingItem>) {
        this.searchingItemList.clear()
        this.searchingItemList.addAll(searchingItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchingItemViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchingItemViewHolder(binding).apply {
            binding.root.setOnClickListener {
                adapterPosition.also {
                    if (it != Adapter.NO_SELECTION) {
                        onItemClickListener?.onItemClick(searchingItemList?.get(it))
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: SearchingItemViewHolder, position: Int) {
        with(holder.binding) {
            searchingItem = searchingItemList?.get(position)
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = searchingItemList?.size ?:0

}