package br.com.creditas.projethom.mapper

interface Mapper<T,U> {

  fun map(t: T): U

}
