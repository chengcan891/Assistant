package com.chengcan.android.userinterface.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import com.chengcan.android.R
import com.chengcan.base.BaseFragment
import kotlinx.android.synthetic.main.androidmodule_fragment_notification.*

/**
 * https://blog.csdn.net/qq_41405257/article/details/80487997
 */
class DialogFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.androidmodule_fragment_dialog, container, false)
    }

    val options = navOptions {
        anim {
            enter = R.anim.base_slide_in_right
            exit = R.anim.base_slide_out_left
            popEnter = R.anim.base_slide_in_left
            popExit = R.anim.base_slide_out_right
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv1.setOnClickListener {

        }

//        val data: ArrayList<ItemWrapper> = ArrayList()
//        data.add(ItemWrapper("尺寸", R.id.display))
//        data.add(
//            ItemWrapper(
//                "坐标系",
//                R.id.canvas,
//                bundleOf(ARG_VIEW to CoordinateSystemView::class.java.name)
//            )
//        )
//        data.add(ItemWrapper("颜色", R.id.canvas, bundleOf(ARG_VIEW to ColorView::class.java.name)))
//        data.add(
//            ItemWrapper(
//                "文字",
//                R.id.canvas,
//                bundleOf(ARG_VIEW to DrawTextView::class.java.name)
//            )
//        )
//        val adapter = NavAdapter(data, this)
//        recyclerview.adapter = adapter
//        recyclerview.layoutManager = LinearLayoutManager(context)
//        recyclerview.addItemDecoration(
//            DividerItemDecoration(
//                context,
//                DividerItemDecoration.VERTICAL
//            )
//        )


//        text.setOnClickListener {
//
//
//            val bundle = Bundle()
//            bundle.putString(ARG_VIEW, CoordinateSystemView::class.java.name)
//            NavHostFragment.findNavController(this@GraphicFragment).navigate(R.id.canvas, bundle, options)
//        }
    }
}

class MyAdapter(val data: ArrayList<String>) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val text = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(text)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val textView = holder.view.findViewById<TextView>(android.R.id.text1)
        textView.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)