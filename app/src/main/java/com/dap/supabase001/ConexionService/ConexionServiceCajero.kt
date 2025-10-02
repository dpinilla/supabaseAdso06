package com.dap.supabase001.ConexionService

import com.dap.supabase001.model.ModelCajero
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

class ConexionServiceCajero {
    companion object{
        val supabase = createSupabaseClient(
            supabaseUrl = "https://nxbylqbkjmykgquvqziu.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54YnlscWJram15a2dxdXZxeml1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTgxNTEwNzIsImV4cCI6MjA3MzcyNzA3Mn0.vEhYxuUjE1e-ACDd5vpYCMIIc-ijq_pMgL_0VTmWO10"
        ) {
            install(Postgrest)
        }

        suspend fun visualizaSupabase(): List<ModelCajero>{
            return supabase.
                from("cajero").
                select().
                decodeList<ModelCajero>()
        }

        suspend fun insertaSupabase(datos: ModelCajero){
            //val city = City(name = "The Shire", countryId = 554)
            //val result =
                supabase.
                from("cajero").
                insert(datos) {
                select()
            }.decodeSingle<ModelCajero>()
        }
    }
}