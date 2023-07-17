package com.saadahmedsoft.springbootecommerce.view.dashboard.home

import android.os.Bundle
import com.saadahmedsoft.springbootecommerce.base.BaseFragment
import com.saadahmedsoft.springbootecommerce.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val title: String
        get() = "App Name"
    override val isBackButtonVisible: Boolean
        get() = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        //
    }

    override fun observeData() {}
}