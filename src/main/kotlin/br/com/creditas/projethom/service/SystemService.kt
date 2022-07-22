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

    fun registerSystem(
        systemRequest: SystemRequest
    ): SystemResponse {
        val system = SystemRequest.toEntity(systemRequest)
        systemRepository.save(system)
        return SystemResponse.fromEntity(system)
    }

}
