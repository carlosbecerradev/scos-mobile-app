package com.example.scos_mobile_app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.scos_mobile_app.MainActivity

import com.example.scos_mobile_app.databinding.FragmentLoginBinding
import com.example.scos_mobile_app.data.network.AuthApi
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.AuthRepository
import com.example.scos_mobile_app.ui.base.BaseFragment
import com.example.scos_mobile_app.ui.startNewActivity
import com.example.scos_mobile_app.ui.visible
import java.util.*

class LoginFragnent  : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.loginProgressBar.visible(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.loginProgressBar.visible(false)
            when(it) {
                is Resource.Success -> {
                    viewModel.saveAuthToken(it.value)
                    requireActivity().startNewActivity(MainActivity::class.java)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login failure", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.btnIniciarSesion.setOnClickListener {
            val username = binding.edtNombreDeUsuario.text.toString().trim()
            val pasasword = binding.edtContrasenia.text.toString().trim()
            //@todo add input validations
            binding.loginProgressBar.visible(true)
            viewModel.login(username, pasasword)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}