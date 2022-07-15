package br.com.creditas.projethom.repository

import br.com.creditas.projethom.model.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TeamRepository : JpaRepository<Team,UUID> {

}
