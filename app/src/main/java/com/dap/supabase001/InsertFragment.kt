package com.dap.supabase001

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dap.supabase001.ConexionService.ConexionServiceCajero
import com.dap.supabase001.databinding.FragmentInsertBinding
import com.dap.supabase001.model.CajeroViewModel
import com.dap.supabase001.model.ModelCajero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.getValue


class InsertFragment : Fragment() {
    private val model: CajeroViewModel by activityViewModels()
    lateinit var binding: FragmentInsertBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInsertBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_insert, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        evento()

    }

    fun evento(){
        binding.btGuardar.setOnClickListener {
            lifecycleScope.launch {
                insert(capturaDatos())
            }

        }
    }

    fun capturaDatos(): ModelCajero{
        return ModelCajero(
            null,
            binding.etNombre.text.toString(),
            "",
            null,
            binding.etImagen.text.toString()
        )
    }

    suspend fun insert(datos: ModelCajero){
        withContext(Dispatchers.IO){
            ConexionServiceCajero.insertaSupabase(datos)
            withContext(Dispatchers.Main){
                model.addCajero(datos)
            }
        }
    }

}