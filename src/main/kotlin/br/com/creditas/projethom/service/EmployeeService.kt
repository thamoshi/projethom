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

    fun listEmployees(): List<EmployeeResponse> {
        return employeeRepository.findAll().map {
            EmployeeResponse.fromEntity(it)
        }
    }

    fun getEmployeeById(
        id: UUID
    ): EmployeeResponse{
        val employee = employeeRepository.findById(id).orElseThrow()
        return EmployeeResponse.fromEntity(employee)

    }

    fun listEmployeesByTeamName(
        team: String
    ): List<EmployeeResponse> {
        val employees = employeeRepository.findByTeamName(team)
        return employees.map {
            EmployeeResponse.fromEntity(it)
        }
    }

    fun registerEmployee(
        employeeRequest: EmployeeRequest
    ): EmployeeResponse {
        val team = teamRepository.findById(employeeRequest.teamId).orElseThrow()
        val employee = EmployeeRequest.toEntity(employeeRequest, team)
        employeeRepository.save(employee)
        return EmployeeResponse.fromEntity(employee)
    }

    fun updateEmployee(
        id: UUID,
        updateEmployeeRequest: EmployeeRequest
    ): EmployeeResponse {
        val employee = employeeRepository.findById(id).orElseThrow()
        val newTeam = teamRepository.findById(updateEmployeeRequest.teamId).orElseThrow()
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
