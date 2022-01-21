package com.twinkle.chatversionone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T:ViewDataBinding>(private var context: Context):RecyclerView.Adapter<BaseRecyclerAdapter.MyViewHolder>(),Filterable {



    class MyViewHolder(var dataBinding: ViewDataBinding) :RecyclerView.ViewHolder(dataBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val dataBinding:T = DataBindingUtil.inflate(LayoutInflater.from(context),getLayout(),parent,false)
        return MyViewHolder(dataBinding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        setViewHolder(holder.dataBinding as T,position)
    }

    abstract fun setViewHolder(holder: T, position: Int)

    override fun getItemCount() = getCount()

    abstract fun getLayout(): Int
    abstract fun getCount(): Int

}