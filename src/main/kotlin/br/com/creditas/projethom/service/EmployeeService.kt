package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.CreditAnalysisResponse
import br.com.creditas.projethom.dto.EmployeeRequest
import br.com.creditas.projethom.dto.EmployeeResponse
import br.com.creditas.projethom.exception.NotEnumValueException
import br.com.creditas.projethom.exception.NotFoundException
import br.com.creditas.projethom.integration.CreditAnalysisGateway
import br.com.creditas.projethom.model.Role
import br.com.creditas.projethom.repository.EmployeeRepository
import br.com.creditas.projethom.repository.TeamRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val teamRepository: TeamRepository,
    private val creditAnalysisGateway: CreditAnalysisGateway
) {
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
        id: UUID,
        info: Boolean
    ): EmployeeResponse {
        val employee = employeeRepository.findById(id)
            .orElseThrow { NotFoundException("team not found. Try listing all the teams registered to get the specific ID.") }
        val creditAnalysisResponse = if (info) {
            employee.personId?.let {
                creditAnalysisGateway.getPersonInfoByPersonId(it)
            } ?: throw NotFoundException("person Id is null")
        } else {
            null
        }
        return EmployeeResponse.fromEntity(employee, creditAnalysisResponse)
    }

    fun registerEmployee(
        employeeRequest: EmployeeRequest
    ): EmployeeResponse {
        val team =
            teamRepository.findById(employeeRequest.teamId)
                .orElseThrow { NotFoundException("team not found. Try listing all the teams registered to get the specific ID.") }
        val employee = EmployeeRequest.toEntity(employeeRequest, team)
        employeeRepository.save(employee)
        return EmployeeResponse.fromEntity(employee)
    }

    fun updateEmployee(
        id: UUID,
        updateEmployeeRequest: EmployeeRequest
    ): EmployeeResponse {
        try {
            val employee = employeeRepository.findById(id)
                .orElseThrow { NotFoundException("employee not found. Try listing all the employees registered to get the specific ID.") }
            val newTeam = teamRepository.findById(updateEmployeeRequest.teamId)
                .orElseThrow { NotFoundException("team not found. Try listing all the teams registered to get the specific ID.") }
            val newRole = Role.valueOf(updateEmployeeRequest.role.uppercase())
            employee.personId = updateEmployeeRequest.personId
            employee.team = newTeam
            employee.role = newRole
            employeeRepository.save(employee)
            return EmployeeResponse.fromEntity(employee)
        } catch (e: IllegalArgumentException) {
            throw NotEnumValueException("role not found. Must be in ${Role.values().toList()}")
        }
    }

    fun deleteEmployeeById(
        id: UUID
    ) {
        employeeRepository.findById(id)
            .orElseThrow { NotFoundException("employee not found. Try listing all the employees registered to get the specific ID.") }
        employeeRepository.deleteById(id)
    }

    fun requestPersonInfo(
        id: UUID
    ): CreditAnalysisResponse {
        val employee = employeeRepository.findById(id)
            .orElseThrow { NotFoundException("employee not found. Try listing all the employees registered to get the specific ID.") }
        return employee.personId?.let {
            creditAnalysisGateway.getPersonInfoByPersonId(it)
        } ?: throw NotFoundException("person Id is null")
    }

}
