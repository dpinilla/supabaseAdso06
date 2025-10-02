package com.dap.supabase001

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dap.supabase001.databinding.FragmentInsertBinding
import com.dap.supabase001.model.ModelCajero


/**
 * A simple [Fragment] subclass.
 * Use the [InsertFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InsertFragment : Fragment() {

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

    }

    fun evento(){
        binding.btGuardar.setOnClickListener {

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

}