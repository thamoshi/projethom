package br.com.creditas.projethom.controller

import br.com.creditas.projethom.dto.SystemRequest
import br.com.creditas.projethom.dto.SystemResponse
import br.com.creditas.projethom.service.SystemService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/systems")
class SystemController(
    private val systemService: SystemService
) {

    @GetMapping
    fun getSystems(
        @RequestParam(required = false) teamName: String?
    ): List<SystemResponse> {
        return systemService.listSystems(teamName)
    }

    @GetMapping("/{id}")
    fun getSystemById(
        @PathVariable id: UUID
    ): SystemResponse {
        return systemService.getSystemById(id)
    }

    @GetMapping("/documentation/{id}")
    fun getSystemDocumentation(
        @PathVariable id: UUID
    ): String {
        return systemService.getDocumentationBySystemId(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postSystem(
        @RequestBody @Valid newSystemRequest: SystemRequest
    ): SystemResponse = systemService.registerSystem(newSystemRequest)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun putSystem(
        @PathVariable id: UUID,
        @RequestBody @Valid updateSystemRequest: SystemRequest
    ): SystemResponse = systemService.updateSystem(id,updateSystemRequest)

    @DeleteMapping("/{id}")
    fun deleteSystem(
        @PathVariable id: UUID
    ) {
        systemService.deleteSystemById(id)
    }

}
