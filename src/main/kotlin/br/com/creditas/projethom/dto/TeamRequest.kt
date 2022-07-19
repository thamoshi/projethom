package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.Team
import br.com.creditas.projethom.model.Tribe
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class TeamRequest(
    @field:Size(min = 1, max = 50, message = "Team name must have between 1 and 50 characters")
    val name: String,
    val description: String,
    val tribe: Tribe
) {
    fun toEntity(teamRequest: TeamRequest): Team {
        return Team(
            name = teamRequest.name,
            description = teamRequest.description,
            tribe = teamRequest.tribe
        )
    }
}
