package com.example.scos_mobile_app.ui.cliente

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.scos_mobile_app.R
import com.example.scos_mobile_app.data.network.ClienteApi
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.ClienteRepository
import com.example.scos_mobile_app.data.responses.Cliente
import com.example.scos_mobile_app.databinding.FragmentHomeClienteBinding
import com.example.scos_mobile_app.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeClienteFragment : BaseFragment<HomeClienteViewModel, FragmentHomeClienteBinding, ClienteRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = runBlocking { userPreferences.username.first() }
        viewModel.getCliente(username.toString())

        viewModel.cliente.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    updateUI(it.value)
                    Log.e("--//-->", it.value.toString())
                }
            }
        })
    }

    private fun updateUI(cliente: Cliente) {
        var mensaje = "Bienvenido " + cliente.nombres + " " + cliente.apellidos
        binding.txtWelcomeCliente.setText(mensaje)
    }

    override fun getViewModel(): Class<HomeClienteViewModel> = HomeClienteViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeClienteBinding = FragmentHomeClienteBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ClienteRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ClienteApi::class.java, token)
        return ClienteRepository(api)
    }

}