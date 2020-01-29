package com.example.facebooksdk.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.facebooksdk.R
import com.example.facebooksdk.rest.RestClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class LinkFragment : Fragment() {

    lateinit var appLinkDataString: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(
            R.layout.link_fragment, container,
            false
        )

        val serverAnswerText = view.findViewById<TextView>(R.id.serverAnswerText)
        val deepLinkText = view.findViewById<TextView>(R.id.deepLinkText)

        deepLinkText.text = appLinkDataString
        getData(serverAnswerText)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            appLinkDataString = it.getString("appLinkDataString", "0")
        }
    }

    private fun getData(serverAnswerText: TextView) {
        GlobalScope.launch {
            val requestResult = async {
                try {
                    RestClient.getClient.getServerInformation()
                } catch (e: Throwable) {
                    print(e)
                    null
                }
            }.await()

            if (requestResult != null) {
                activity?.runOnUiThread {
                    serverAnswerText.text = requestResult
                }
            }
        }

    }

    companion object {
        fun newInstance(param1: String): LinkFragment {
            val fragment = LinkFragment()
            val args = Bundle()
            args.putString("appLinkDataString", param1)
            fragment.arguments = args
            return fragment
        }
    }
}
