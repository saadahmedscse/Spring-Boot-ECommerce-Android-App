package com.saadahmedsoft.springbootecommerce.view.dashboard

import android.os.Bundle
import com.saadahmedsoft.springbootecommerce.base.BaseActivity
import com.saadahmedsoft.springbootecommerce.databinding.ActivityDashboardBinding
import com.saadahmedsoft.springbootecommerce.databinding.AppToolbarBinding

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(ActivityDashboardBinding::inflate) {
    override val toolbarBinding: AppToolbarBinding
        get() = binding.appToolbar

    override fun onActivityCreate(savedInstanceState: Bundle?) {
        //
    }

    override fun observeData() {}
}