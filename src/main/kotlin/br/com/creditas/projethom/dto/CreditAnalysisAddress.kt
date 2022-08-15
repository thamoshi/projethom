package br.com.creditas.projethom.dto

import java.util.UUID

data class CreditAnalysisAddress(
    val cep: String,
    val city: String,
    val id: UUID,
    val state: String,
    val street: String
)
