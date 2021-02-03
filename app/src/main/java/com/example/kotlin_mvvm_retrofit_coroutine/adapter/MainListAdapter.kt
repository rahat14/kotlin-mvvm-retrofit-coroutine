package com.example.kotlin_mvvm_retrofit_coroutine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kotlin_mvvm_retrofit_coroutine.R
import com.example.kotlin_mvvm_retrofit_coroutine.model.ItemAlbum
import kotlinx.android.synthetic.main.item_layout.view.*

class MainListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemAlbum>() {

        override fun areItemsTheSame(oldItem: ItemAlbum, newItem: ItemAlbum): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemAlbum, newItem: ItemAlbum): Boolean {

            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return viewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is viewholder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ItemAlbum>) {
        differ.submitList(list)

    }

    class viewholder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ItemAlbum) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            itemView.textViewUserName.text = item.title
            itemView.textViewUserEmail.text = "Album Id : ${item.albumId}"
            // load image
            itemView.imageViewAvatar.load(item.thumbnailUrl)

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ItemAlbum)
    }
}
