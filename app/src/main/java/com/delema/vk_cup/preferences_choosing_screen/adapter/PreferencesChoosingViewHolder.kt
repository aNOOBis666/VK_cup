package com.delema.vk_cup.preferences_choosing_screen.adapter

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.delema.vk_cup.R
import com.delema.vk_cup.utils.toInt

class PreferencesChoosingViewHolder(
    private val binding: View,
    private val differedItems: MutableList<String>,
    private val onClickItem: () -> Unit
) : RecyclerView.ViewHolder(binding) {

    private var categoryTitle: TextView? = null
    private var root: RelativeLayout? = null
    private var view: View? = null
    private var checker: View? = null

    fun bind(item: String) {
        initViews()
        categoryTitle?.text = item
        setLevel(differedItems.contains(item).toInt())

        root?.setOnClickListener {
            addOrRemoveItem(item)
            onClickItem()
        }
    }

    private fun initViews() {
        categoryTitle = binding.findViewById(R.id.category_title)
        root = binding.findViewById(R.id.root)
        view = binding.findViewById(R.id.view)
        checker = binding.findViewById(R.id.checker)
    }

    private fun setLevel(level: Int) = with(binding) {
        root?.background?.level = level
        view?.background?.level = level
        checker?.background?.level = level
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