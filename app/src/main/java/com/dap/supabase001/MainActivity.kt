package com.dap.supabase001

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dap.supabase001.ConexionService.ConexionServiceCajero
import com.dap.supabase001.adapter.CajeroAdapter
import com.dap.supabase001.databinding.ActivityMainBinding
import com.dap.supabase001.model.CajeroViewModel
import com.dap.supabase001.model.ModelCajero
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MainActivity : AppCompatActivity() {
    private val model: CajeroViewModel by viewModels()
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

        recyclerView()
        observer()
        lifecycleScope.launch {
            visualiza()
        }
        evento()
    }

    fun evento(){
        binding.btInsertar.setOnClickListener {
            supportFragmentManager.commit {
                add<InsertFragment>(R.id.main)
                addToBackStack(null)
            }
        }
    }

    fun recyclerView(){
        cajeroAdapter = CajeroAdapter(dataset)

        //val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        binding.rvCajero.layoutManager = LinearLayoutManager(this)
        binding.rvCajero.adapter = cajeroAdapter
    }
    fun observer(){
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentName.observe(this, Observer { newName ->
            // Update the UI, in this case, a TextView.
            cajeroAdapter.dataSet = newName
            cajeroAdapter.notifyDataSetChanged()
        })
    }

    private suspend fun visualiza() {
        withContext(Dispatchers.IO) {
            val datos = ConexionServiceCajero.visualizaSupabase()
            withContext(Dispatchers.Main){
               model.addAllCajero(datos)
            }
        }
    }
}