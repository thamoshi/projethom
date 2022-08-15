package br.com.creditas.projethom.dto

import java.util.UUID

data class CreditAnalysisResponse(
    val address: CreditAnalysisAddress,
    val birthDate: String,
    val contacts: List<CreditAnalysisContact>,
    val cpf: Long,
    val id: UUID,
    val lastName: String,
    val name: String,
    val score: String
)
