package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.Status
import br.com.creditas.projethom.model.System
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import java.time.LocalDateTime
import java.util.UUID

@JsonInclude(NON_NULL)
data class SystemResponse(
    val id: UUID,
    val name: String,
    val teamOwner: String?,
    val health: Status?,
    val createdAt: LocalDateTime
) {
    companion object {
        fun fromEntity(
            system: System,
            healthStatus: Status? = null
        ): SystemResponse = SystemResponse(
            id = system.id,
            name = system.name,
            teamOwner = system.owner?.name ?: "Does not have an owner",
            health = healthStatus,
            createdAt = system.createdAt
        )
    }
}
