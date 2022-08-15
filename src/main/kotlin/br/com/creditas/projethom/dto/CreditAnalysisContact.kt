package br.com.creditas.projethom.dto

import java.util.UUID

data class CreditAnalysisContact(
    val emailAddress: String,
    val id: UUID,
    val phoneNumber: Long,
    val type: String
)
