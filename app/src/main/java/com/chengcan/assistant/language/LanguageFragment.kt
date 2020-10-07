package com.chengcan.assistant.language

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.chengcan.assistant.BaseFragment
import com.chengcan.assistant.R
import kotlinx.android.synthetic.main.fragment_language.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LanguageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LanguageFragment : BaseFragment() {


//    val safeArgs: BlankFragment2Args by navArgs()
//    var argument1 = safeArgs.argument1

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
        return inflater.inflate(R.layout.fragment_language, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }



        text.setOnClickListener( Navigation.createNavigateOnClickListener(R.id.action_blankFragment_to_blankFragment2, null))

//        text.setOnClickListener {

                //第一种
//            findNavController(this).navigate(R.id.blankFragment2,null, options)

            //第三种
//            val argument1 = 1
//            val action = BlankFragmentDirections.actionBlankFragmentToBlankFragment2(argument1)
//            findNavController(this).navigate(action)
//        }

//        text.setOnClickListener {
//            val argument1 = 1
//            val action = BlankFragmentDirections.actionBlankFragmentToBlankFragment2(argument1)
//            findNavController(this).navigate(action)
//        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

            // Handle the back button event
            Log.i("BlankFragment","onBack")


        }
        if(callback.isEnabled){
            callback.handleOnBackPressed()
        }
    }

    override fun onBackPressed():Boolean {
        super.onBackPressed()
        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LanguageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}