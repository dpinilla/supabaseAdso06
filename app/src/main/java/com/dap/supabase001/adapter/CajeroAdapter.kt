package com.dap.supabase001.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dap.supabase001.R
import com.dap.supabase001.databinding.ActivityItemMainBinding
import com.dap.supabase001.model.ModelCajero

class CajeroAdapter (private val dataSet: MutableList<ModelCajero>) :
    RecyclerView.Adapter<CajeroAdapter.ViewHolderCajero>() {
    class ViewHolderCajero (view: View) : RecyclerView.ViewHolder(view) {
        val binding = ActivityItemMainBinding.bind(view)
        fun inicializa(cajero: ModelCajero){
            binding.tvNombre.text = cajero.cajnombre
            binding.tvApellido.text = cajero.cajapellido
            binding.tvCaja.text = cajero.cajcaja.toString()
            binding.tvImagen.text = cajero.cajimagen
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CajeroAdapter.ViewHolderCajero {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_main, parent, false)

        return ViewHolderCajero(view)
    }

    override fun onBindViewHolder(holder: CajeroAdapter.ViewHolderCajero, position: Int) {
        holder.inicializa(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}