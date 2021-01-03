package com.chengcan.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chengcan.base.BaseFragment
import com.chengcan.diary.app.DiaryApplication
import com.chengcan.log.Logger
import kotlinx.android.synthetic.main.diarymodule_fragment_diary.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiaryFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val safeArgs: DiaryFragmentArgs by navArgs()
//        var argument1 = safeArgs.argument1

//        Log.i("BlankFragment2", argument1.toString())
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.diarymodule_fragment_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        start.setOnClickListener {
            GlobalScope.launch { // 在后台启动一个新的协程并继续
//                DiaryApplication.db.diaryDao().insert(Diary(System.currentTimeMillis(), "33333"))

                val diaries = DiaryApplication.db.diaryDao().queryDiary(getTodayBeginMilliseconds(), getTodayEndMilliseconds())
                Logger.i("DiaryFragment",diaries.toString())
            }

        }
    }

    private fun getDayBeginMilliseconds(day: Int): Long {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        calendar.add(Calendar.DATE, day)
        return calendar.timeInMillis
    }

    fun getTodayBeginMilliseconds(): Long {
        return getDayBeginMilliseconds(0)
    }

    fun getTodayEndMilliseconds(): Long {
        return getDayBeginMilliseconds(1)
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
         * @return A new instance of fragment BlankFragment2.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            BlankFragment2().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}