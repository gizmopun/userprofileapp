/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.UserProperty

class PhotoGridAdapter : ListAdapter<UserProperty,
        PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPropertyViewHolder, position: Int) {
        val userProperty = getItem(position)
        holder.bind(userProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UserProperty>() {
        override fun areItemsTheSame(oldItem: UserProperty, newItem: UserProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: UserProperty, newItem: UserProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class MarsPropertyViewHolder(private var binding:GridViewItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(userProperty: UserProperty) {
            binding.upropertyy = userProperty
            binding.executePendingBindings()
        }
    }
}


