package br.com.creditas.projethom.controller

import br.com.creditas.projethom.dto.SystemRequest
import br.com.creditas.projethom.dto.SystemResponse
import br.com.creditas.projethom.service.SystemService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.UUID
import javax.validation.Valid

@Controller
class SystemController(
    private val systemService: SystemService
) {

    @GetMapping
    fun getSystems(): List<SystemResponse> {
        return systemService.listSystems()
    }

    @GetMapping("/{id}")
    fun getSystemById(
        @PathVariable id: UUID
    ): SystemResponse {
        return systemService.getSystemById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postSystem(
        @RequestBody @Valid newSystemRequest: SystemRequest
    ): SystemResponse = systemService.registerSystem(newSystemRequest)

}
