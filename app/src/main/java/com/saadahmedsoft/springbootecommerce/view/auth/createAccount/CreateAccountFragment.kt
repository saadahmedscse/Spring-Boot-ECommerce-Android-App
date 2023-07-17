package com.saadahmedsoft.springbootecommerce.view.auth.createAccount

import android.os.Bundle
import com.saadahmedsoft.springbootecommerce.base.BaseFragment
import com.saadahmedsoft.springbootecommerce.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding>(FragmentCreateAccountBinding::inflate) {
    override val title: String
        get() = "Create New Account"
    override val isBackButtonVisible: Boolean
        get() = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        //
    }

    override fun observeData() {}
}