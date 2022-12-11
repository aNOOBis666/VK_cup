package com.delema.vk_cup.preferences_choosing_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.delema.vk_cup.R

class PreferencesChoosingAdapter(
    private val onClick: () -> Unit
) : ListAdapter<String, PreferencesChoosingViewHolder>(DiffCallback) {

    private val differedItems = mutableListOf<String>()

    fun getChangedItems(): List<String> = differedItems

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PreferencesChoosingViewHolder = PreferencesChoosingViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_category_item, parent, false),
        differedItems,
        onClick
    )

    override fun onBindViewHolder(holder: PreferencesChoosingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    private object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String,
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean = oldItem == newItem
    }
}