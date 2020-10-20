package com.chengcan.android

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.chengcan.base.BaseFragment
import kotlinx.android.synthetic.main.androidmodule_fragment_web.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WebFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WebFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.androidmodule_fragment_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //在app中打开网页，不是跳转到浏览器
        webview.webViewClient = object : WebViewClient(){
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

//            override fun onReceivedSslError(
//                view: WebView?,
//                handler: SslErrorHandler?,
//                error: SslError?
//            ) {
//                super.onReceivedSslError(view, handler, error)
//                handler?.proceed()
//            }
        }
//        webview.settings.javaScriptEnabled = true
//        webview.loadUrl("https://www.baidu.com/")
//        webview.loadUrl("https://developer.android.google.cn/")


        val webSettings: WebSettings = webview.getSettings()
        // 让WebView能够执行javaScript
        // 让WebView能够执行javaScript
        webSettings.javaScriptEnabled = true
        // 让JavaScript可以自动打开windows
        // 让JavaScript可以自动打开windows
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        // 设置缓存
        // 设置缓存
        webSettings.setAppCacheEnabled(true)
        // 设置缓存模式,一共有四种模式
        // 设置缓存模式,一共有四种模式
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true)
        // 将图片调整到合适的大小
        // 将图片调整到合适的大小
        webSettings.useWideViewPort = true
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        // 设置可以被显示的屏幕控制
        // 设置可以被显示的屏幕控制
        webSettings.displayZoomControls = true
        // 设置默认字体大小
        // 设置默认字体大小
        webSettings.defaultFontSize = 12




        webview.loadUrl("https://developer.android.google.cn/guide")
    }

    override fun onBackPressed():Boolean {
        super.onBackPressed()
        //如果可以返回上一级，而不是直接退出应用程序
        if (webview.canGoBack()) {
            webview.goBack()
            return true
        }

        return false
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OfficeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WebFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}