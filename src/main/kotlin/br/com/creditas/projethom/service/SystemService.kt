package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.SystemRequest
import br.com.creditas.projethom.dto.SystemResponse
import br.com.creditas.projethom.exception.NotFoundException
import br.com.creditas.projethom.repository.SystemRepository
import br.com.creditas.projethom.repository.TeamRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SystemService(
    private val systemRepository: SystemRepository,
    private val teamRepository: TeamRepository
) {
    private val systemNotFoundMessage =
        "system not found. Try listing all the systems registered to get the specific ID"
    private val teamNotFoundMessage = "team not found. Try listing all the teams registered to get the specific ID."

    fun listSystems(
        teamName: String? = null
    ): List<SystemResponse> {
        return teamName?.let {
            try {
                systemRepository.findByOwnerName(teamName).map {
                    SystemResponse.fromEntity(it)
                }
            } catch (e: IllegalArgumentException) {
                emptyList()
            }
        } ?: systemRepository.findAll().map {
            SystemResponse.fromEntity(it)
        }
    }

    fun getSystemById(
        id: UUID
    ): SystemResponse {
        try {
            val system = systemRepository.getReferenceById(id)
            return SystemResponse.fromEntity(system)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw NotFoundException(systemNotFoundMessage)
        }
    }

    fun getDocumentationBySystemId(
        id: UUID
    ): String {
        try {
            val system = systemRepository.getReferenceById(id)
            return system.documentation
        } catch (e: JpaObjectRetrievalFailureException) {
            throw NotFoundException(systemNotFoundMessage)
        }
    }

    fun registerSystem(
        systemRequest: SystemRequest
    ): SystemResponse {
        val owner = systemRequest.teamId?.let {
            teamRepository.findById(it).orElseThrow { NotFoundException(teamNotFoundMessage) }
        }
        val system = SystemRequest.toEntity(systemRequest, owner)
        systemRepository.save(system)
        return SystemResponse.fromEntity(system)
    }

    fun updateSystem(
        id: UUID,
        updateSystemRequest: SystemRequest
    ): SystemResponse {
        try {
            val system = systemRepository.getReferenceById(id)
            val owner = updateSystemRequest.teamId?.let {
                teamRepository.findById(it).orElseThrow { NotFoundException(teamNotFoundMessage) }
            }
            system.name = updateSystemRequest.name
            system.owner = owner
            system.documentation = updateSystemRequest.documentation
            system.url = updateSystemRequest.url
            systemRepository.save(system)
            return SystemResponse.fromEntity(system)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw NotFoundException(systemNotFoundMessage)
        }
    }

    fun deleteSystemById(
        id: UUID
    ) {
        try {
            systemRepository.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw NotFoundException(systemNotFoundMessage)
        }
    }

}
