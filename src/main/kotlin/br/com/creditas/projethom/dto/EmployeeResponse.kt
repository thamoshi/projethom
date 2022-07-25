package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.Employee
import br.com.creditas.projethom.model.Role
import java.util.UUID

data class EmployeeResponse(
    val id: UUID,
    val teamName: String,
    val role: Role
) {

    companion object {
        fun fromEntity(employee: Employee): EmployeeResponse {
            return EmployeeResponse(
                id = employee.id,
                teamName = employee.team.name,
                role = employee.role
            )
        }
    }

}
