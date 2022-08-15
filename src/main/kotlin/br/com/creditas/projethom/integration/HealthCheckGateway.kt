package br.com.creditas.projethom.integration

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class HealthCheckGateway {
    fun checkSystemHealth(url: String): Any? {
        return WebClient.create(url)
            .get()
            .retrieve()
            .bodyToMono(Any::class.java)
            .block()
    }
}