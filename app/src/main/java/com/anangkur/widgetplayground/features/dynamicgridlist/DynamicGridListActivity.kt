package com.anangkur.widgetplayground.features.dynamicgridlist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.widgetplayground.databinding.ActivityDynamicGridListBinding
import com.anangkur.widgetplayground.model.Post

class DynamicGridListActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, DynamicGridListActivity::class.java))
        }
    }

    private lateinit var binding: ActivityDynamicGridListBinding

    private lateinit var dynamicGridAdapter: DynamicGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDynamicGridListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dynamicGridAdapter = DynamicGridAdapter()
        binding.recyclerView.adapter = dynamicGridAdapter
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false,
        )
        dynamicGridAdapter.setItems(createDummyDynamicGrid())
    }

    private fun createDummyDynamicGrid(): List<Post> {
        return listOf(
            Post(
                title = "title 0",
                description = "description 0",
                images = emptyList(),
            ),
            Post(
                title = "title 1",
                description = "description 1",
                images = listOf(
                    "https://picsum.photos/id/237/200/300",
                ),
            ),
            Post(
                title = "title 2",
                description = "description 2",
                images = listOf(
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                ),
            ),
            Post(
                title = "title 3",
                description = "description 3",
                images = listOf(
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                ),
            ),
            Post(
                title = "title 4",
                description = "description 4",
                images = listOf(
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                ),
            ),
            Post(
                title = "title 5",
                description = "description 5",
                images = listOf(
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                ),
            ),
            Post(
                title = "title 6",
                description = "description 6",
                images = listOf(
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                    "https://picsum.photos/id/237/200/300",
                ),
            )
        )
    }
}