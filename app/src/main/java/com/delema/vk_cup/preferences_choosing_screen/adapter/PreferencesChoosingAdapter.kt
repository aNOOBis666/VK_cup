package com.delema.vk_cup.preferences_choosing_screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.delema.vk_cup.R
import com.delema.vk_cup.databinding.ViewCategoryItemBinding

class PreferencesChoosingAdapter(
    private val onClick: () -> Unit
) : ListAdapter<String, PreferencesChoosingViewHolder>(DiffCallback) {

    private val differedItems = mutableListOf<String>()

    fun getChangedItems(): List<String> = differedItems

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PreferencesChoosingViewHolder = PreferencesChoosingViewHolder(
        ViewCategoryItemBinding.bind(parent.inflate(R.layout.view_category_item)),
        differedItems,
        onClick
    )

    override fun onBindViewHolder(holder: PreferencesChoosingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
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