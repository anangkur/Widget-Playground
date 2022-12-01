package com.anangkur.widgetplayground.features.dynamicgridlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.widgetplayground.databinding.ItemDynamicGridBinding
import com.anangkur.widgetplayground.model.FbReactions
import com.anangkur.widgetplayground.model.Post
import com.anangkur.widgetplayground.utils.extensions.gone
import com.anangkur.widgetplayground.utils.extensions.loadImage
import com.anangkur.widgetplayground.utils.extensions.visible

class DynamicGridAdapter : RecyclerView.Adapter<DynamicGridAdapter.ViewHolder>() {

    private val items = ArrayList<Post>()

    inner class ViewHolder(
        private val binding: ItemDynamicGridBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.textTitle.text = item.title
            binding.textDescription.text = item.description
            when  {
                item.images.size >= 4 -> {
                    showAllImages(item.images)
                }
                item.images.size == 3 -> {
                    showThreeImages(item.images[0], item.images[1], item.images[2])
                }
                item.images.size == 2 -> {
                    showOnlyTwoImages(item.images.first(), item.images.last())
                }
                item.images.size == 1 -> {
                    showOnlyOneImage(item.images.first())
                }
                else -> {
                    hideAllImages()
                }
            }
            binding.buttonReact.setReactions(*FbReactions.reactions)
            binding.buttonReact.defaultReaction = FbReactions.defaultReact
            binding.buttonReact.setEnableReactionTooltip(true)
        }

        private fun hideAllImages() {
            binding.imageBottomLeft.gone()
            binding.imageBottomRight.gone()
            binding.imageTopLeft.gone()
            binding.imageTopRight.gone()
            binding.layerTransparent.gone()
            binding.textAdditional.gone()
        }

        private fun showOnlyOneImage(image: String) {
            hideAllImages()
            binding.imageTopLeft.visible()
            binding.imageTopLeft.loadImage(image)
        }

        private fun showOnlyTwoImages(firstImage: String, secondImage: String) {
            hideAllImages()
            binding.imageTopLeft.visible()
            binding.imageTopRight.visible()
            binding.imageTopLeft.loadImage(firstImage)
            binding.imageTopRight.loadImage(secondImage)
        }

        private fun showThreeImages(firstImage: String, secondImage: String, thirdImage: String) {
            hideAllImages()
            binding.imageTopLeft.visible()
            binding.imageTopRight.visible()
            binding.imageBottomLeft.visible()
            binding.imageTopLeft.loadImage(firstImage)
            binding.imageTopRight.loadImage(secondImage)
            binding.imageBottomLeft.loadImage(thirdImage)
        }

        private fun showAllImages(images: List<String>) {
            binding.imageTopLeft.loadImage(images[0])
            binding.imageTopRight.loadImage(images[1])
            binding.imageBottomLeft.loadImage(images[2])
            binding.imageBottomRight.loadImage(images[3])
            setupAdditionalText(images.size)
        }

        private fun setupAdditionalText(size: Int) {
            if (size > 4) {
                binding.layerTransparent.visible()
                binding.textAdditional.visible()
                binding.textAdditional.text = "+${size-4}"
            } else {
                binding.layerTransparent.gone()
                binding.textAdditional.gone()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = ItemDynamicGridBinding.inflate(LayoutInflater.from(parent.context),
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

    fun setItems(items: List<Post>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}