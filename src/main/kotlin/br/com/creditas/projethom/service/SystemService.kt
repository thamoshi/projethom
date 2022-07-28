package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.SystemRequest
import br.com.creditas.projethom.dto.SystemResponse
import br.com.creditas.projethom.repository.SystemRepository
import br.com.creditas.projethom.repository.TeamRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SystemService(
    private val systemRepository: SystemRepository,
    private val teamRepository: TeamRepository
) {

    fun listSystems(
        teamName: String? = null
    ): List<SystemResponse> {
        return teamName?.let {
            systemRepository.findByOwnerName(teamName).map {
                SystemResponse.fromEntity(it)
            }
        } ?: systemRepository.findAll().map {
            SystemResponse.fromEntity(it)
        }
    }

    fun getSystemById(
        id: UUID
    ): SystemResponse {
        val system = systemRepository.getReferenceById(id)
        return SystemResponse.fromEntity(system)
    }

    fun getDocumentationBySystemId(
        id: UUID
    ): String {
        val system = systemRepository.getReferenceById(id)
        return system.documentation
    }

    fun registerSystem(
        systemRequest: SystemRequest
    ): SystemResponse {
        val owner = systemRequest.teamId?.let {
            teamRepository.getReferenceById(it)
        }
        val system = SystemRequest.toEntity(systemRequest, owner)
        systemRepository.save(system)
        return SystemResponse.fromEntity(system)
    }

    fun updateSystem(
        id: UUID,
        updateSystemRequest: SystemRequest
    ): SystemResponse {
        val system = systemRepository.getReferenceById(id)
        val owner = updateSystemRequest.teamId?.let {
            teamRepository.getReferenceById(it)
        }
        system.name = updateSystemRequest.name
        system.owner = owner
        system.documentation = updateSystemRequest.documentation
        system.url = updateSystemRequest.url
        systemRepository.save(system)
        return SystemResponse.fromEntity(system)
    }

    fun deleteSystemById(
        id: UUID
    ) {
        systemRepository.deleteById(id)
    }

}
