package com.saadahmedsoft.springbootecommerce.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.saadahmedsoft.springbootecommerce.databinding.AppToolbarBinding
import com.saadahmedsoft.springbootecommerce.utils.SessionManager
import com.saadahmedsoft.base.helper.*
import com.saadahmedsoft.base.viewmodel.ToolbarViewModel
import com.saadahmedsoft.shortintent.Animator

abstract class BaseActivity<BINDING: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> BINDING
) : AppCompatActivity() {

    private lateinit var _binding: BINDING
    private lateinit var _session: SessionManager
    private val toolbarViewModel by viewModels<ToolbarViewModel>()

    val binding: BINDING
        get() = _binding

    val session: SessionManager
        get() = _session

    private var pageTitle = ""

    abstract val toolbarBinding: AppToolbarBinding?

    abstract fun onActivityCreate(savedInstanceState: Bundle?)

    abstract fun observeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        _session = SessionManager.getInstance(this)
        setContentView(_binding.root)
        observeData()
        initToolbar()
        onActivityCreate(savedInstanceState)
    }

    private fun initToolbar() {
        if (toolbarBinding != null) {
            observe(toolbarViewModel.title) {
                toolbarBinding?.toolbarTitle?.text = it
            }
            observe(toolbarViewModel.isBackButtonVisible) {
                toolbarBinding?.toolbarBtn?.visibility = if (it) View.VISIBLE else View.GONE
            }
            toolbarBinding?.toolbarBtn?.onClicked {
                super.getOnBackPressedDispatcher().onBackPressed()
                Animator.animateSlideDown(this)
            }
        }
    }

    @JvmName("shortSnackBar1")
    @Deprecated(message = "New method available", replaceWith = ReplaceWith("message.shortSnackBar()"))
    fun shortSnackBar(message: String?) {
        message?.let { showSnackBar(message, Snackbar.LENGTH_SHORT) }
    }

    fun String?.shortSnackBar() {
        this?.let { showSnackBar(it, Snackbar.LENGTH_SHORT) }
    }

    @JvmName("longSnackBar1")
    @Deprecated("New method available", replaceWith = ReplaceWith("message.longSnackBar()"))
    fun longSnackBar(message: String?) {
        message?.let { showSnackBar(message, Snackbar.LENGTH_LONG) }
    }

    fun String?.longSnackBar() {
        this?.let { showSnackBar(it, Snackbar.LENGTH_LONG) }
    }

    @JvmName("shortToast1")
    @Deprecated("New method available", replaceWith = ReplaceWith("message.shortToast()"))
    fun shortToast(message: String?) {
        message?.let { showToast(message, Toast.LENGTH_SHORT) }
    }

    fun String?.shortToast() {
        this?.let { showToast(it, Toast.LENGTH_SHORT) }
    }

    @JvmName("longToast1")
    @Deprecated("New method available", replaceWith = ReplaceWith("message.longToast()"))
    fun longToast(message: String?) {
        message?.let { showToast(message, Toast.LENGTH_LONG) }
    }

    fun String?.longToast() {
        this?.let { showToast(it, Toast.LENGTH_LONG) }
    }

    private fun showSnackBar(message: String, duration: Int) {
        snackBar(this, _binding.root, message, duration)
    }

    private fun showToast(message: String, duration: Int) {
        toast(this, message, duration)
    }
}