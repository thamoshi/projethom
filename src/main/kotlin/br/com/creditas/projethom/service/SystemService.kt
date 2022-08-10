package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.CatFactResponse
import br.com.creditas.projethom.dto.SystemRequest
import br.com.creditas.projethom.dto.SystemResponse
import br.com.creditas.projethom.exception.NotFoundException
import br.com.creditas.projethom.repository.SystemRepository
import br.com.creditas.projethom.repository.TeamRepository
import com.fasterxml.jackson.databind.util.JSONPObject
import com.fasterxml.jackson.databind.util.JSONWrappedObject
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.awt.PageAttributes.MediaType
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
        val system = systemRepository.findById(id)
            .orElseThrow { NotFoundException("system not found. Try listing all the systems registered to get the specific ID") }
        return SystemResponse.fromEntity(system)
    }

    fun getDocumentationBySystemId(
        id: UUID
    ): String {
        val system = systemRepository.findById(id)
            .orElseThrow { NotFoundException("system not found. Try listing all the systems registered to get the specific ID") }
        return system.documentation

    }

    fun registerSystem(
        systemRequest: SystemRequest
    ): SystemResponse {
        val owner = systemRequest.teamId?.let {
            teamRepository.findById(it)
                .orElseThrow { NotFoundException("team not found. Try listing all the teams registered to get the specific ID.") }
        }
        val system = SystemRequest.toEntity(systemRequest, owner)
        systemRepository.save(system)
        return SystemResponse.fromEntity(system)
    }

    fun updateSystem(
        id: UUID,
        updateSystemRequest: SystemRequest
    ): SystemResponse {

        val system = systemRepository.findById(id)
            .orElseThrow { NotFoundException("system not found. Try listing all the systems registered to get the specific ID") }
        val owner = updateSystemRequest.teamId?.let {
            teamRepository.findById(it)
                .orElseThrow { NotFoundException("team not found. Try listing all the teams registered to get the specific ID.") }
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
        systemRepository.findById(id)
            .orElseThrow { NotFoundException("system not found. Try listing all the systems registered to get the specific ID") }
        systemRepository.deleteById(id)
    }

    fun requestSystemUrl(
        id: UUID
    ): Any? {
        val system = systemRepository.findById(id)
            .orElseThrow { NotFoundException("system not found. Try listing all the systems registered to get the specific ID") }
        return WebClient.create(system.url)
            .get()
            .retrieve()
            .bodyToMono<Any>()
            .block()
    }

}
