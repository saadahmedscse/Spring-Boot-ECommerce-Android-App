package com.saadahmedsoft.springbootecommerce.view.auth.createAccount

import android.content.Intent
import android.os.Bundle
import com.google.gson.JsonObject
import com.saadahmedsoft.base.helper.onClicked
import com.saadahmedsoft.helper.getString
import com.saadahmedsoft.springbootecommerce.api.RetroInstance
import com.saadahmedsoft.springbootecommerce.base.BaseFragment
import com.saadahmedsoft.springbootecommerce.databinding.FragmentCreateAccountBinding
import com.saadahmedsoft.springbootecommerce.view.dashboard.DashboardActivity

class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding>(FragmentCreateAccountBinding::inflate) {
    override val title: String
        get() = "Create New Account"
    override val isBackButtonVisible: Boolean
        get() = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        binding.btnJoin.onClicked {
            val body = JsonObject()
            body.addProperty("firstName", binding.etFirstName.getString())
            body.addProperty("lastName", binding.etLastName.getString())
            body.addProperty("email", binding.etEmail.getString())
            body.addProperty("phone", binding.etPhone.getString().ifBlank { null })
            body.addProperty("password", binding.etPassword.getString())
            body.addProperty("confirmPassword", binding.etConfirmPassword.getString())

            RetroInstance.instance.createAccount(body).getResponse {
                it.status?.let { status ->
                    if (status) {
                        val loginBody = JsonObject()
                        loginBody.addProperty("email", binding.etEmail.getString())
                        loginBody.addProperty("password", binding.etPassword.getString())

                        RetroInstance.instance.login(body).getResponse { loginRes ->
                            loginRes.status?.let { loginResponseStatus ->
                                if (loginResponseStatus) {
                                    session.setBearerToken(loginRes.token!!)
                                    val i = Intent(requireContext(), DashboardActivity::class.java)
                                    requireContext().startActivity(i)
                                    requireActivity().finish()
                                }
                                else loginRes.message.shortSnackBar()
                            }
                        }
                    } else it.message.shortSnackBar()
                }
            }
        }
    }

    override fun observeData() {}
}