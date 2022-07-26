package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.Team
import br.com.creditas.projethom.model.Tribe
import java.time.LocalDateTime
import java.util.UUID

data class TeamResponse(
    val id: UUID,
    val name: String,
    val description: String? = null,
    val tribe: Tribe,
    val createdAt: LocalDateTime
) {

    companion object {

        fun fromEntity(team: Team): TeamResponse {
            return TeamResponse(
                id = team.id,
                name = team.name,
                description = team.description,
                tribe = team.tribe,
                createdAt = team.createdAt
            )
        }

    }

}
