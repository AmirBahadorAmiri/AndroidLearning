package com.amirbahadoramiri.androidlearning.views.kotlin.mvc.end_of_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amirbahadoramiri.androidlearning.R
import com.amirbahadoramiri.androidlearning.databinding.ItemMvvmRecyclerviewBinding
import com.amirbahadoramiri.androidlearning.models.User
import com.amirbahadoramiri.androidlearning.tools.logger.Logger

class EndOfRecyclerViewAdapter : RecyclerView.Adapter<EndOfRecyclerViewAdapter.Holder>() {

    var users = arrayListOf<User>()

    fun setData(newUser: ArrayList<User>) {
        if (!newUser.isNullOrEmpty()) {
            users = newUser
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: ItemMvvmRecyclerviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_mvvm_recyclerview,
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(position)

    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class Holder(val binding: ItemMvvmRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(position: Int) {
            binding.user = users.get(position)

            if (position == (users.size-1)) Logger.logd("END OF LIST $position")

        }

    }

}