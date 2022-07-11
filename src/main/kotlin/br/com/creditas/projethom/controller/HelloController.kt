package br.com.creditas.projethom.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

  @GetMapping
  fun HelloWord(): String {
    return "Hello World! Olá mundo!"
  }

}
