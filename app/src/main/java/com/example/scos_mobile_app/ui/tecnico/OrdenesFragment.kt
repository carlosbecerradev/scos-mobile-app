package com.example.scos_mobile_app.ui.tecnico

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scos_mobile_app.data.network.OrdenesApi
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.OrdenesRepository
import com.example.scos_mobile_app.databinding.FragmentOrdenesBinding
import com.example.scos_mobile_app.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class OrdenesFragment : BaseFragment<OrdenesViewModel, FragmentOrdenesBinding, OrdenesRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sede = runBlocking { userPreferences.role.first() }
        if(sede != null) {
            viewModel.getOrdenesBySede(sede)
        }

        viewModel.ordenes.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    var ordenes = it.value
                    Log.i("-ordenes->", ordenes.toString())
                }
            }
        })
    }

    override fun getViewModel(): Class<OrdenesViewModel> = OrdenesViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrdenesBinding = FragmentOrdenesBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): OrdenesRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(OrdenesApi::class.java, token)
        return OrdenesRepository(api)
    }


}