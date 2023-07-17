package com.saadahmedsoft.springbootecommerce.view.auth

import android.os.Bundle
import com.saadahmedsoft.springbootecommerce.base.BaseActivity
import com.saadahmedsoft.springbootecommerce.databinding.ActivityAuthBinding
import com.saadahmedsoft.springbootecommerce.databinding.AppToolbarBinding

class AuthActivity : BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {
    override val toolbarBinding: AppToolbarBinding?
        get() = null

    override fun onActivityCreate(savedInstanceState: Bundle?) {
        //
    }

    override fun observeData() {}
}