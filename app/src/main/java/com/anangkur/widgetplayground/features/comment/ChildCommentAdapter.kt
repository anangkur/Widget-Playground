package com.anangkur.widgetplayground.features.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.widgetplayground.databinding.ItemCommentChildBinding

class ChildCommentAdapter : RecyclerView.Adapter<ChildCommentAdapter.ViewHolder>() {

    private val items = ArrayList<String>()

    inner class ViewHolder(
        private val binding: ItemCommentChildBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.text.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = ItemCommentChildBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: String) {
        this.items.add(item)
        notifyItemInserted(this.items.lastIndex)
    }
}