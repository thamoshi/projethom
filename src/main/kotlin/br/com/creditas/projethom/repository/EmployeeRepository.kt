package br.com.creditas.projethom.repository

import br.com.creditas.projethom.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
interface EmployeeRepository: JpaRepository<Employee, UUID> {
    fun findByTeamName(
        teamName: String
    ): List<Employee>

}
