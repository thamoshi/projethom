package br.com.creditas.projethom.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
import br.com.creditas.projethom.model.System
import org.springframework.stereotype.Repository

@Repository
interface SystemRepository : JpaRepository<System, UUID> {
    fun findByOwnerName(
        teamName: String
    ): List<System>

}
