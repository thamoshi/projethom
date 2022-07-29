package br.com.creditas.projethom.exception

import java.lang.RuntimeException

class NotFoundException(message: String?) : RuntimeException(message) {
}