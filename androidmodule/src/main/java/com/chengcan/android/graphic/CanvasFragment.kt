package com.chengcan.android.graphic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chengcan.android.R
import com.chengcan.base.BaseFragment
import com.chengcan.log.Logger


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val ARG_VIEW = "view"

/**
 * A simple [Fragment] subclass.
 * Use the [CanvasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CanvasFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = arguments?.getString(ARG_VIEW)
        Logger.i("CanvasFragment", v.toString())
        v?.let {
            val clazz = Class.forName(v)
            val method = clazz.getDeclaredConstructor(Context::class.java)
            return method.newInstance(context) as View
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
}

