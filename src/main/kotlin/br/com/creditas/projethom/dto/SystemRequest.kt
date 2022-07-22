package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.System
import br.com.creditas.projethom.model.Team
import java.util.UUID

data class SystemRequest(
    val name: String,
    val teamId: UUID?,
    val documentation: String,
    val url: String
) {

    companion object {
        fun toEntity(
            systemRequest: SystemRequest,
            team: Team? = null
        ): System = System(
            name = systemRequest.name,
            owner = team,
            documentation = systemRequest.documentation,
            url = systemRequest.url
        )
    }

}
