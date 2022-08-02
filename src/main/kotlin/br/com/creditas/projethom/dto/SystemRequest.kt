package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.System
import br.com.creditas.projethom.model.Team
import java.util.UUID
import javax.validation.constraints.Size

data class SystemRequest(
    @field:Size(min = 1, max = 50, message = "System name must have between 1 and 50 characters")
    val name: String,
    val teamId: UUID? = null,
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
