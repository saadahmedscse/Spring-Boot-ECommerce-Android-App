package com.saadahmedsoft.base.helper

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun linearLayoutManager(context: Context) = LinearLayoutManager(context)
fun staggeredGridLayoutManager(itemCount: Int) = StaggeredGridLayoutManager(itemCount, LinearLayoutManager.VERTICAL)
fun RecyclerView.setLinearLayoutManager(context: Context) {
    this.layoutManager = linearLayoutManager(context)
}

fun RecyclerView.setStaggeredGridLayoutManager(gridCount: Int) {
    this.layoutManager = staggeredGridLayoutManager(gridCount)
}