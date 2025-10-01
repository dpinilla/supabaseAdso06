package com.dap.supabase001.model

import kotlinx.serialization.Serializable

@Serializable
data class ModelCajero(
    val id: Int? = null,
    val cajnombre: String? = null,
    val cajapellido: String?=null,
    val cajcaja: Int? = null,
    val cajimagen: String?=null
)
