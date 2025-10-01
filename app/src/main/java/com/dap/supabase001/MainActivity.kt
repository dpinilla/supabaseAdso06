package com.dap.supabase001

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dap.supabase001.adapter.CajeroAdapter
import com.dap.supabase001.databinding.ActivityMainBinding
import com.dap.supabase001.model.ModelCajero
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val supabase = createSupabaseClient(
    supabaseUrl = "https://nxbylqbkjmykgquvqziu.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54YnlscWJram15a2dxdXZxeml1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTgxNTEwNzIsImV4cCI6MjA3MzcyNzA3Mn0.vEhYxuUjE1e-ACDd5vpYCMIIc-ijq_pMgL_0VTmWO10"
) {
    install(Postgrest)
}

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val dataset = mutableListOf<ModelCajero>()
    lateinit var cajeroAdapter: CajeroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        cajeroAdapter = CajeroAdapter(dataset)

        //val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        binding.rvCajero.layoutManager = LinearLayoutManager(this)
        binding.rvCajero.adapter = cajeroAdapter
        lifecycleScope.launch {
            visualiza()
        }


    }

    private suspend fun visualiza() {
        withContext(Dispatchers.IO) {
            val datos =
                supabase.
                from("cajero").
                select().
                decodeList<ModelCajero>()
            withContext(Dispatchers.Main){
                dataset.clear()
                dataset.addAll(datos)
                cajeroAdapter.notifyDataSetChanged()
            }
        }
    }
}