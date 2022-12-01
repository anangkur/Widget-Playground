package com.anangkur.widgetplayground.features.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.widgetplayground.databinding.ItemCommentBinding
import com.anangkur.widgetplayground.model.Comment
import com.anangkur.widgetplayground.utils.extensions.clear
import com.anangkur.widgetplayground.utils.extensions.enable
import com.anangkur.widgetplayground.utils.extensions.gone
import com.anangkur.widgetplayground.utils.extensions.visible

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private val items = ArrayList<Comment>()

    inner class ViewHolder(
        private val binding: ItemCommentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val childAdapter = ChildCommentAdapter()

        fun bind(item: Comment) {
            binding.text.text = item.text
            binding.recyclerChildComment.adapter = childAdapter
            binding.recyclerChildComment.layoutManager = LinearLayoutManager(
                binding.root.context,
                RecyclerView.VERTICAL,
                false,
            )
            childAdapter.setItems(item.child)
            binding.buttonComment.setOnClickListener {
                showComment()
            }
            binding.buttonCancel.setOnClickListener {
                hideComment()
            }
            binding.buttonSend.setOnClickListener {
                childAdapter.addItem(binding.editTextComment.text?.toString().orEmpty())
                binding.editTextComment.clear()
                hideComment()
            }
            binding.editTextComment.doAfterTextChanged {
                binding.buttonSend.enable(it?.toString().orEmpty().isNotEmpty())
            }
        }

        private fun showComment() {
            binding.buttonComment.gone()
            binding.editTextComment.visible()
            binding.buttonSend.visible()
            binding.buttonCancel.visible()
        }

        private fun hideComment() {
            binding.buttonComment.visible()
            binding.editTextComment.gone()
            binding.buttonSend.gone()
            binding.buttonCancel.gone()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(
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

    fun setItems(items: List<Comment>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: Comment) {
        this.items.add(item)
        notifyItemInserted(this.items.lastIndex)
    }
}