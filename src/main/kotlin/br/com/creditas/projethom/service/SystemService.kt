package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.SystemRequest
import br.com.creditas.projethom.dto.SystemResponse
import br.com.creditas.projethom.repository.SystemRepository
import br.com.creditas.projethom.repository.TeamRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SystemService(
    private val systemRepository: SystemRepository,
    private val teamRepository: TeamRepository
) {

    fun listSystems(): List<SystemResponse> {
        return systemRepository.findAll().map{
            SystemResponse.fromEntity(it)
        }
    }

    fun getSystemById(
        id: UUID
    ): SystemResponse {
        val system = systemRepository.findById(id).orElseThrow()
        return SystemResponse.fromEntity(system)
    }

    fun getDocumentationBySystemId(
        id: UUID
    ): String {
        val system = systemRepository.findById(id).orElseThrow()
        return system.documentation
    }

    fun registerSystem(
        systemRequest: SystemRequest
    ): SystemResponse {
        val team = if (systemRequest.teamId == null) {
            null
        } else {
            teamRepository.findById(systemRequest.teamId).orElseThrow()
        }
        val system = SystemRequest.toEntity(systemRequest, team)
        systemRepository.save(system)
        return SystemResponse.fromEntity(system)
    }

    fun updateSystem(
        id: UUID,
        updateSystemRequest: SystemRequest
    ): SystemResponse {
        val system = systemRepository.findById(id).orElseThrow()
        val team = if (updateSystemRequest.teamId == null) {
            null
        } else {
            teamRepository.findById(updateSystemRequest.teamId).orElseThrow()
        }
        system.name = updateSystemRequest.name
        system.owner = team
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
