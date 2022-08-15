package br.com.creditas.projethom.integration

import br.com.creditas.projethom.dto.CreditAnalysisResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    value = "creditAnalysis",
    url = "\${external-api.credit-analysis.url}",
    decode404 = false
)
interface CreditAnalysisGateway {
    @GetMapping(path = ["/accounts/{id}"])
    fun getPersonInfoByCpf(
        @PathVariable("id") id: String
    ): CreditAnalysisResponse
}