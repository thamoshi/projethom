package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.Employee
import br.com.creditas.projethom.model.Role
import br.com.creditas.projethom.model.Team
import java.util.UUID

data class EmployeeRequest(
    val personId: UUID? = null,
    val teamId: UUID,
    val role: Role
) {

    companion object {
        fun toEntity(
            employeeRequest: EmployeeRequest,
            team: Team
        ): Employee {
            return Employee(
                personId = employeeRequest.personId,
                team = team,
                role = employeeRequest.role
            )
        }
    }

}
