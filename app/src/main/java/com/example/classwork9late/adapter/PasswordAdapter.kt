package com.example.classwork9late.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.classwork9late.model.Number
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.classwork9late.R
import com.example.classwork9late.common.list
import com.example.classwork9late.databinding.BtnItemNumberBinding
import com.example.classwork9late.databinding.BtnItemRemoveBinding
import com.example.classwork9late.databinding.BtnItemTouchBinding

class PasswordAdapter : ListAdapter<Number, RecyclerView.ViewHolder>(ItemDiffCallback) {

    var numberClick: ((Number) -> Unit)? = null
    var touchCLick: ((Number) -> Unit)? = null
    var removeClick: ((Number) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType != NUMBER) {
            return if (viewType == TOUCH)
                SecondViewHolder(
                    BtnItemTouchBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else return ThirdViewHolder(
                BtnItemRemoveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        return FirstViewHolder(BtnItemNumberBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    companion object {
        const val NUMBER = 1
        const val TOUCH = 2
        const val REMOVE = 3
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position].type != "NUMBER") {
            return if (list[position].type == "TOUCH") TOUCH
            else REMOVE
        }
        return NUMBER
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FirstViewHolder -> {
                holder.bind()
            }
            is SecondViewHolder -> {
                holder.bind()
            }
            is ThirdViewHolder -> {
                holder.bind()
            }
        }
    }

    inner class FirstViewHolder(private val binding: BtnItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val model = list[adapterPosition]
            binding.btnNumber1.text = model.number.toString()
            binding.btnNumber1.setOnClickListener {
                numberClick?.invoke(model)
            }
        }
    }

    inner class SecondViewHolder(private val binding: BtnItemTouchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val model = list[adapterPosition]
            binding.btnNumberTouch.setBackgroundResource(R.drawable.ic_touch__id_1)
            binding.btnNumberTouch.setOnClickListener {
                touchCLick?.invoke(model)
            }
        }
    }

    inner class ThirdViewHolder(private val binding: BtnItemRemoveBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val model = list[adapterPosition]
            binding.btnNumberTouch.setBackgroundResource(R.drawable.ic_remove)
            binding.btnNumberTouch.setOnClickListener {
                removeClick?.invoke(model)
            }
        }
    }


    object ItemDiffCallback : DiffUtil.ItemCallback<Number>() {
        override fun areItemsTheSame(oldItem: Number, newItem: Number): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Number, newItem: Number): Boolean {
            return oldItem == newItem
        }
    }
}