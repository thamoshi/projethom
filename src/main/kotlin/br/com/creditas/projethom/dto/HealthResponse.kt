package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.Status

data class HealthResponse(
    val status: Status,
    val body: Any?
)
