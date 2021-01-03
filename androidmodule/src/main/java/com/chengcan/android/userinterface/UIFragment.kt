package com.chengcan.android.userinterface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chengcan.android.R
//import com.chengcan.android.main.ItemWrapper
//import com.chengcan.android.main.NavAdapter
import com.chengcan.base.BaseFragment
import com.chengcan.base.adapter.ItemWrapper
import com.chengcan.base.adapter.NavAdapter
import kotlinx.android.synthetic.main.androidmodule_fragment_ui.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UIFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UIFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.androidmodule_fragment_ui, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: ArrayList<ItemWrapper> = ArrayList()
        data.add(
            ItemWrapper(
                getString(R.string.androidmodule_notification),
                R.id.notification
            )
        )
        data.add(
            ItemWrapper(
                getString(R.string.androidmodule_dialog),
                R.id.dialog
            )
        )

        val adapter = NavAdapter(data, this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


//        mAdapter.setSelectionTracker(mSelectionTracker)


//        text.setOnClickListener {
//            val options = navOptions {
//                anim {
//                    enter = R.anim.slide_in_right
//                    exit = R.anim.slide_out_left
//                    popEnter = R.anim.slide_in_left
//                    popExit = R.anim.slide_out_right
//                }
//            }
////            findNavController(this).navigate(R.id.officeWeb,null, options)
//
//
//        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AndroidFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UIFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

