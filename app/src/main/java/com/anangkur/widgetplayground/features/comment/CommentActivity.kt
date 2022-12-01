package com.anangkur.widgetplayground.features.comment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.widgetplayground.databinding.ActivityCommentBinding
import com.anangkur.widgetplayground.model.Comment
import com.anangkur.widgetplayground.utils.extensions.clear
import com.anangkur.widgetplayground.utils.extensions.enable

class CommentActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, CommentActivity::class.java))
        }
    }

    private lateinit var binding: ActivityCommentBinding

    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commentAdapter = CommentAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false,
        )
        binding.recyclerView.adapter = commentAdapter
        commentAdapter.setItems(createDummyComment())

        binding.editComment.doAfterTextChanged {
            binding.buttonSend.enable(it?.toString().orEmpty().isNotEmpty())
        }

        binding.buttonSend.setOnClickListener {
            commentAdapter.addItem(
                Comment(
                    text = binding.editComment.text?.toString().orEmpty(),
                    child = emptyList(),
                )
            )
            binding.editComment.clear()
        }
    }

    private fun createDummyComment(): List<Comment> {
        return listOf(
            Comment(
                text = "comment 1",
                child = listOf(
                    "child comment 1",
                    "child comment 2",
                    "child comment 3",
                    "child comment 4",
                    "child comment 5",
                ),
            ),
            Comment(
                text = "comment 2",
                child = emptyList(),
            )
        )
    }
}