package com.delema.vk_cup.preferences_choosing_screen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.delema.vk_cup.databinding.ViewCategoryItemBinding
import com.delema.vk_cup.utils.toInt

class PreferencesChoosingViewHolder(
    private val binding: ViewCategoryItemBinding,
    private val differedItems: MutableList<String>,
    private val onClickItem: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) = with(binding) {
        categoryTitle.text = item
        setLevel(differedItems.contains(item).toInt())

        root.setOnClickListener {
            addOrRemoveItem(item)
            onClickItem()
        }
    }

    private fun setLevel(level: Int) = with(binding) {
        root.background.level = level
        view.background.level = level
        checker.background.level = level
    }

    private fun addOrRemoveItem(item: String) {
        if (differedItems.contains(item)) {
            differedItems.remove(item)
            setLevel(0)
        } else {
            differedItems.add(item)
            setLevel(1)
        }
    }
}