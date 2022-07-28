package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.System
import java.time.LocalDateTime
import java.util.UUID

data class SystemResponse(
    val id: UUID,
    val name: String,
    val teamOwner: String?,
    val url: String,
    val createdAt: LocalDateTime
) {
    companion object {
        fun fromEntity(
            system: System
        ): SystemResponse = SystemResponse(
            id = system.id,
            name = system.name,
            teamOwner = system.owner?.name,
            url = system.url,
            createdAt = system.createdAt
        )
    }
}
