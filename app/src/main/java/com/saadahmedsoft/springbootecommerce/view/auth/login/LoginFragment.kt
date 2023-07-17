package com.saadahmedsoft.springbootecommerce.view.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonObject
import com.saadahmedsoft.base.helper.onClicked
import com.saadahmedsoft.helper.getString
import com.saadahmedsoft.springbootecommerce.api.RetroInstance
import com.saadahmedsoft.springbootecommerce.base.BaseFragment
import com.saadahmedsoft.springbootecommerce.databinding.FragmentLoginBinding
import com.saadahmedsoft.springbootecommerce.view.auth.AuthActivity

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override val title: String
        get() = "Login"
    override val isBackButtonVisible: Boolean
        get() = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        binding.btnLogin.onClicked {
            val body = JsonObject()
            body.addProperty("email", binding.etEmail.getString())
            body.addProperty("password", binding.etPassword.getString())

            RetroInstance.instance.login(body).getResponse {
                it.message!!.shortToast()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AuthActivity).setLoginTab()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun observeData() {}
}