package br.com.creditas.projethom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ProjethomApplication

fun main(args: Array<String>) {
    runApplication<ProjethomApplication>(*args)
}
