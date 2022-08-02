package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.EmployeeRequest
import br.com.creditas.projethom.dto.EmployeeResponse
import br.com.creditas.projethom.exception.NotEnumValueException
import br.com.creditas.projethom.exception.NotFoundException
import br.com.creditas.projethom.model.Role
import br.com.creditas.projethom.repository.EmployeeRepository
import br.com.creditas.projethom.repository.TeamRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val teamRepository: TeamRepository
) {
    private val teamNotFoundMessage = "team not found. Try listing all the teams registered to get the specific ID."
    private val employeeNotFoundMessage =
        "employee not found. Try listing all the employees registered to get the specific ID."
    private val notRoleValueMessage = "role not found. Must be in ${Role.values().toList()}"

    fun listEmployees(
        teamName: String?
    ): List<EmployeeResponse> {
        return teamName?.let {
            try {
                employeeRepository.findByTeamName(teamName).map {
                    EmployeeResponse.fromEntity(it)
                }
            } catch (e: IllegalArgumentException) {
                emptyList()
            }
        } ?: employeeRepository.findAll().map {
            EmployeeResponse.fromEntity(it)
        }
    }

    fun getEmployeeById(
        id: UUID
    ): EmployeeResponse {
        try {
            val employee = employeeRepository.getReferenceById(id)
            return EmployeeResponse.fromEntity(employee)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw NotFoundException(employeeNotFoundMessage)
        }
    }

    fun registerEmployee(
        employeeRequest: EmployeeRequest
    ): EmployeeResponse {
        val team =
            teamRepository.findById(employeeRequest.teamId).orElseThrow { NotFoundException(teamNotFoundMessage) }
        val employee = EmployeeRequest.toEntity(employeeRequest, team)
        employeeRepository.save(employee)
        return EmployeeResponse.fromEntity(employee)
    }

    fun updateEmployee(
        id: UUID,
        updateEmployeeRequest: EmployeeRequest
    ): EmployeeResponse {
        try {
            val employee = employeeRepository.getReferenceById(id)
            val newRole = Role.valueOf(updateEmployeeRequest.role.uppercase())
            val newTeam = teamRepository.findById(updateEmployeeRequest.teamId)
                .orElseThrow { NotFoundException(teamNotFoundMessage) }
            employee.personId = updateEmployeeRequest.personId
            employee.team = newTeam
            employee.role = newRole
            employeeRepository.save(employee)
            return EmployeeResponse.fromEntity(employee)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw NotFoundException(employeeNotFoundMessage)
        } catch (e: IllegalArgumentException) {
            throw NotEnumValueException(notRoleValueMessage)
        }
    }

    fun deleteEmployeeById(
        id: UUID
    ) {
        try {
            employeeRepository.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw NotFoundException(employeeNotFoundMessage)
        }
    }

}
