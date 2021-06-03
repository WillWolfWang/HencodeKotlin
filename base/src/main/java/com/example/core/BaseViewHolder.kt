package com.example.core

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
abstract class BaseViewHolder: RecyclerView.ViewHolder {
    constructor(itemView: View): super(itemView)

    @SuppressLint("UseSparseArrays")
    private val viewHashMap: HashMap<Int, View?> = HashMap()

    protected fun <T: View?> getView(id: Int): T? {
        var view:View? = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap[id] = view
        }
        // 这里有一个类转型检查异常，不加 suppress 编译不过
        return view as T?
    }

    protected fun setText(id: Int, text: String?) {
        (getView<View>(id) as TextView).text = text
    }
}