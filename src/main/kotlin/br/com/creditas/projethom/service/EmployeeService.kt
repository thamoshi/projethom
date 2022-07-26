package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.EmployeeRequest
import br.com.creditas.projethom.dto.EmployeeResponse
import br.com.creditas.projethom.repository.EmployeeRepository
import br.com.creditas.projethom.repository.TeamRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val teamRepository: TeamRepository
) {

    fun listEmployees(
        teamName: String?
    ): List<EmployeeResponse> {
        return if (teamName == null) {
            employeeRepository.findAll().map {
                EmployeeResponse.fromEntity(it)
            }
        } else {
            try {
                employeeRepository.findByTeamName(teamName).map {
                    EmployeeResponse.fromEntity(it)
                }
            } catch(e: IllegalArgumentException) {
                emptyList()
            }
        }
    }

    fun getEmployeeById(
        id: UUID
    ): EmployeeResponse {
        val employee = employeeRepository.getReferenceById(id)
        return EmployeeResponse.fromEntity(employee)

    }

    fun registerEmployee(
        employeeRequest: EmployeeRequest
    ): EmployeeResponse {
        val team = teamRepository.getReferenceById(employeeRequest.teamId)
        val employee = EmployeeRequest.toEntity(employeeRequest, team)
        employeeRepository.save(employee)
        return EmployeeResponse.fromEntity(employee)
    }

    fun updateEmployee(
        id: UUID,
        updateEmployeeRequest: EmployeeRequest
    ): EmployeeResponse {
        val employee = employeeRepository.getReferenceById(id)
        val newTeam = teamRepository.getReferenceById(updateEmployeeRequest.teamId)
        employee.personId = updateEmployeeRequest.personId
        employee.team = newTeam
        employee.role = updateEmployeeRequest.role
        employeeRepository.save(employee)
        return EmployeeResponse.fromEntity(employee)
    }

    fun deleteEmployeeById(
        id: UUID
    ) {
        employeeRepository.deleteById(id)
    }

}
