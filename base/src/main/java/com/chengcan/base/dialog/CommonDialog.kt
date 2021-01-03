package com.chengcan.base.dialog

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment


class CommonDialog : DialogFragment(){

    private var params = Params()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        if (params.mTitle != null) {
            builder.setTitle(params.mTitle)
        }
        if (params.mNegativeButtonText != null) {
            builder.setNegativeButton(params.mNegativeButtonText, params.mNegativeButtonListener)
        }
        if (params.mMessage != null) {
            builder.setMessage(params.mMessage)
        }
        val dialog: AlertDialog = builder
//            .setView(getContentView(inflater))
//            .setNegativeButton("取消",
//                DialogInterface.OnClickListener { dialog, which -> })
            .create()

        //设置不可以取消
        dialog.setCancelable(params.mCancelable)
        dialog.setCanceledOnTouchOutside(params.mCancelable)
        return dialog
    }

//    protected fun getContentView(inflater: LayoutInflater): View? {
//        return inflater.inflate(R.layout.dialog_test, null)
//    }

    class Builder {
        private val params = Params()
        fun setTitle(title: String?): Builder {
            params.mTitle = title
            return this
        }

        fun setMessage(message: CharSequence?): Builder {
            params.mMessage = message
            return this
        }

        fun setCanceledOnTouchOutside(cancel: Boolean): Builder {
            params.mCancelable = cancel
            return this
        }

        fun setNegativeButton(
            text: CharSequence?,
            listener: DialogInterface.OnClickListener?
        ): Builder {
            params.mNegativeButtonText = text
            params.mNegativeButtonListener = listener
            return this
        }

        fun create(): CommonDialog {
            val baseDialog = CommonDialog()
            baseDialog.params = params
            return baseDialog
        }
    }
}

private class Params {
    var mTitle: CharSequence? = null
    var mNegativeButtonText: CharSequence? = null
    var mNegativeButtonListener: DialogInterface.OnClickListener? = null
    var mCancelable = true
    var mMessage: CharSequence? = null
    var mView: View? = null
}