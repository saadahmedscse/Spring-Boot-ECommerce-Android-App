package com.saadahmedsoft.springbootecommerce.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.saadahmedsoft.springbootecommerce.utils.SessionManager
import com.saadahmedsoft.base.helper.navigate
import com.saadahmedsoft.base.helper.snackBar
import com.saadahmedsoft.base.helper.toast
import com.saadahmedsoft.base.viewmodel.ToolbarViewModel

abstract class BaseFragment<BINDING: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> BINDING
) : Fragment() {

    private lateinit var _binding: BINDING
    private lateinit var _session: SessionManager
    private val toolbarViewModel by activityViewModels<ToolbarViewModel>()

     val binding: BINDING
        get() = _binding

    val session: SessionManager
        get() = _session

    abstract val title: String
    abstract val isBackButtonVisible: Boolean

    abstract fun onFragmentCreate(savedInstanceState: Bundle?)
    abstract fun observeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        _session = SessionManager.getInstance(requireContext())
        observeData()
        onFragmentCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbarViewModel.setTitle(title)
        toolbarViewModel.setBackButtonState(isBackButtonVisible)
        return _binding.root
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
        snackBar(requireContext(), _binding.root, message, duration)
    }

    private fun showToast(message: String, duration: Int) {
        toast(requireContext(), message, duration)
    }

    fun navigate(@IdRes id: Int) {
        binding.root.navigate(id)
    }

    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}