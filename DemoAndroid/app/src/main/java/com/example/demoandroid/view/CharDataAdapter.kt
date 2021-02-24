package com.example.demoandroid.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demoandroid.common.AppUtils
import com.example.demoandroid.data.model.RelatedTopic
import com.example.demoandroid.databinding.DataListItemBinding

/**
 *  ListAdapter implementation
 */
class CharDataAdapter(private val onClick: (RelatedTopic) -> Unit) : ListAdapter<RelatedTopic, CharDataAdapter.CustomViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding =
                DataListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class CustomViewHolder(var binding: DataListItemBinding, val onClick: (RelatedTopic) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(chars: RelatedTopic) {
            binding.charName.text = AppUtils.splitCharacters(chars.Text!!)[0]
            binding.cardLayout.setOnClickListener {
                onClick(chars)
            }
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<RelatedTopic>() {
        override fun areItemsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean {
            return oldItem == newItem
        }
    }
}