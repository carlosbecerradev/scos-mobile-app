package com.example.scos_mobile_app.ui.adaptador

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.scos_mobile_app.R
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import com.example.scos_mobile_app.databinding.ItemOrdenBinding

class AdaptadorOrdenDeServicioDto(
    private val dataSet: List<OrdenDeServicioDto>
): RecyclerView.Adapter<AdaptadorOrdenDeServicioDto.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var binding: ItemOrdenBinding = ItemOrdenBinding.bind(view)

        fun enlazarItem(orden: OrdenDeServicioDto) {
            binding.tvOrdenCodigo.text = orden.id.toString()
            binding.tvOrdenFechaCreacion.text = orden.fechaDeCreacion.toString().replace("T", "  ")
            binding.tvOrdenTipoDeIncidencia.text = orden.tipoDeIncidencia.toString()
            binding.tvOrdenDescripciion.text = orden.descripcionDelProblema.toString()

            binding.btnAceptarOrden.setOnClickListener {
                Log.i("-recycler->", orden.id.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_orden, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val elemento: OrdenDeServicioDto = dataSet.get(position)
        holder.enlazarItem(elemento)
    }

    override fun getItemCount(): Int = dataSet.size

}