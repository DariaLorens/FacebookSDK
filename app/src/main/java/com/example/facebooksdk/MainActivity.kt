package com.example.facebooksdk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.facebooksdk.fragments.FragmentHelloWord
import com.example.facebooksdk.fragments.LinkFragment
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppLinkData.fetchDeferredAppLinkData(
            this
        ) {
        }

        // Get user consent
        FacebookSdk.setAutoInitEnabled(false)
        FacebookSdk.fullyInitialize()
        AppLinkData.fetchDeferredAppLinkData(
            this
        ) {
            if (it == null) {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                val frag: Fragment = FragmentHelloWord()
                ft.add(R.id.container, frag)
                ft.commit()
            } else {
                this.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.container, LinkFragment.newInstance(it.toString()))
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

}

