package br.com.creditas.projethom.dto

import br.com.creditas.projethom.exception.NotEnumValueException
import br.com.creditas.projethom.model.Employee
import br.com.creditas.projethom.model.Role
import br.com.creditas.projethom.model.Team
import java.util.UUID

data class EmployeeRequest(
    val personId: UUID? = null,
    val teamId: UUID,
    val role: String,
    val cpf: String,
    val name: String,
    val lastName: String,
    val birthDate: String
) {

    companion object {
        fun toEntity(
            employeeRequest: EmployeeRequest,
            team: Team,
            personId: UUID
        ): Employee {
            try {
                val newRole = Role.valueOf(employeeRequest.role.uppercase())
                return Employee(
                    personId = personId,
                    team = team,
                    role = newRole
                )
            } catch (e: IllegalArgumentException) {
                throw NotEnumValueException("role not found. Must be in ${Role.values().toList()}")
            }
        }
    }

}
