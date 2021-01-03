package com.chengcan.base.adapter

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


abstract class CommonRecyclerViewAdapter<T>(
    val context: Context,
    val data: MutableList<T>,
    var layoutId: Int,
    val multiTypeSupport: MultiTypeSupport<T>?
) :
    RecyclerView.Adapter<CommonRecyclerViewAdapter.VH>() {

    protected val inflater: LayoutInflater = LayoutInflater.from(context)

    fun update(data: List<T>) {
        this.data.clear();
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        // 多布局支持
        return multiTypeSupport?.getLayoutId(data[position], position) ?: super.getItemViewType(
            position
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        // 多布局支持
        if (multiTypeSupport != null) {
            layoutId = viewType
        }
        // 先inflate数据
        val itemView: View = inflater.inflate(layoutId, parent, false)
        // 返回ViewHolder
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        // 设置点击和长按事件
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener {
                mItemClickListener!!.onItemClick(data[position], position)
            }
        }

        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                mLongClickListener!!.onLongClick(data[position], position)
                return@setOnLongClickListener true
            }
        }
        convert(holder, data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    abstract fun convert(holder: VH, item: T)

    var mItemClickListener: OnItemClickListener<T>? = null
    var mLongClickListener: OnLongClickListener<T>? = null

    open fun setOnItemClickListener(itemClickListener: OnItemClickListener<T>?) {
        mItemClickListener = itemClickListener
    }

    open fun setOnLongClickListener(longClickListener: OnLongClickListener<T>?) {
        mLongClickListener = longClickListener
    }

    interface MultiTypeSupport<T> {
        // 根据当前位置或者条目数据返回布局
        fun getLayoutId(item: T, position: Int): Int
    }

    interface OnItemClickListener<T> {
        fun onItemClick(t: T, position: Int)
    }

    interface OnLongClickListener<T> {
        fun onLongClick(t: T, position: Int)
    }

    class VH(private val convertView: View) : RecyclerView.ViewHolder(convertView) {
        private val views: SparseArray<View> = SparseArray()
//        private var convertView: View = view

        fun <T : View> getView(id: Int): T {
            var view = views[id]
            if (view == null) {
                view = convertView.findViewById(id)
                views.put(id, view)
            }
            return view as T
        }


        /**
         * 填充对应控件的方法   可以写n多个
         * @param id
         * @param str
         */
        fun setText(id: Int, str: String?) {
            val view = getView<TextView>(id)
            view.text = str
        }

        fun setImage(id: Int, imageId: Int) {
            val view: ImageView = getView(id)
            view.setImageResource(imageId)
        }
    }
}

