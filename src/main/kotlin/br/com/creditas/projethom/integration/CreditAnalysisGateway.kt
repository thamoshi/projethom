package br.com.creditas.projethom.integration

import br.com.creditas.projethom.dto.CreditAnalysisRequest
import br.com.creditas.projethom.dto.CreditAnalysisResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

@FeignClient(
    value = "creditAnalysis",
    url = "\${external-api.credit-analysis.url}",
    decode404 = false
)
interface CreditAnalysisGateway {
    @GetMapping(path = ["/accounts/{id}"])
    fun getPersonInfoByPersonId(
        @PathVariable("id") id: UUID
    ): CreditAnalysisResponse

    @GetMapping(path = ["/accounts/find"])
    fun getPersonInfoByCpf(
        @RequestParam("cpf") cpf: String
    ): CreditAnalysisResponse

    @PostMapping(path = ["/accounts"])
    fun postNewPersonInformation(
        @RequestBody creditAnalysisRequest: CreditAnalysisRequest
    ): CreditAnalysisResponse
}