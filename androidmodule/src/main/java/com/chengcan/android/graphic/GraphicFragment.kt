package com.chengcan.android.graphic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chengcan.android.R
import com.chengcan.android.graphic.view.ColorView
import com.chengcan.android.graphic.view.CoordinateSystemView
import com.chengcan.android.main.ItemWrapper
import com.chengcan.android.main.NavAdapter
import com.chengcan.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_graphic.*

/**
 * https://blog.csdn.net/qq_41405257/article/details/80487997
 */
class GraphicFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graphic, container, false)
    }

    val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: ArrayList<ItemWrapper> = ArrayList()
        data.add(ItemWrapper("坐标系", R.id.canvas, bundleOf(ARG_VIEW to CoordinateSystemView::class.java.name)))
        data.add(ItemWrapper("颜色", R.id.canvas, bundleOf(ARG_VIEW to ColorView::class.java.name)))
        val adapter = NavAdapter(data, this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )



//        text.setOnClickListener {
//
//
//            val bundle = Bundle()
//            bundle.putString(ARG_VIEW, CoordinateSystemView::class.java.name)
//            NavHostFragment.findNavController(this@GraphicFragment).navigate(R.id.canvas, bundle, options)
//        }
    }
}