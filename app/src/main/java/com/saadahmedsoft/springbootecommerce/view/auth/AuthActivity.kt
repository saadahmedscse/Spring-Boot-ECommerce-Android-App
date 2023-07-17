package com.saadahmedsoft.springbootecommerce.view.auth

import android.os.Bundle
import android.widget.TextView
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.drugstore.app.helper.setTextColor
import com.saadahmedsoft.base.helper.navigate
import com.saadahmedsoft.base.helper.onClicked
import com.saadahmedsoft.base.helper.setBackground
import com.saadahmedsoft.springbootecommerce.R
import com.saadahmedsoft.springbootecommerce.base.BaseActivity
import com.saadahmedsoft.springbootecommerce.databinding.ActivityAuthBinding
import com.saadahmedsoft.springbootecommerce.databinding.AppToolbarBinding

class AuthActivity : BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {
    override val toolbarBinding: AppToolbarBinding?
        get() = null

    private var prevTab: TextView? = null
    private lateinit var currentTab: TextView

    override fun onActivityCreate(savedInstanceState: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.authContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.fade_enter)
            .setExitAnim(R.anim.fade_exit)
            .setPopEnterAnim(R.anim.fade_enter)
            .setPopExitAnim(R.anim.fade_exit)
            .setPopUpTo(navController.graph.startDestinationId, false)
            .build()

        currentTab = binding.tabLogin

        binding.tabLogin.onClicked {
            setLoginTab()

            if (pageTitle == "Create New Account") onBackPressedDispatcher.onBackPressed()
        }

        binding.tabJoin.onClicked {
            prevTab = currentTab
            currentTab = binding.tabJoin
            changeTab()

            if (pageTitle == "Login") navController.navigate(R.id.createAccountFragment, null, options)
        }
    }

    override fun observeData() {}

    private fun changeTab() {
        prevTab?.setBackground(R.drawable.ripple_bg_white_5)
        prevTab?.setTextColor(this, R.color.colorDarkGrey)

        currentTab.setBackground(R.drawable.ripple_bg_main_5)
        currentTab.setTextColor(this, R.color.static_white)
    }

    fun setLoginTab() {
        prevTab = currentTab
        currentTab = binding.tabLogin
        changeTab()
    }
}