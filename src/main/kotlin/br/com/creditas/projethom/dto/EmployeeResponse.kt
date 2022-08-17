package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.Employee
import br.com.creditas.projethom.model.Role
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.UUID
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL

@JsonInclude(NON_NULL)
data class EmployeeResponse(
    val id: UUID,
    val personInfo: PersonInfo?,
    val teamName: String,
    val role: Role
) {
    companion object {
        fun fromEntity(
            employee: Employee,
            creditAnalysisResponse: CreditAnalysisResponse? = null
        ): EmployeeResponse {
            val personInfo = creditAnalysisResponse?.let {
                PersonInfo(
                    name = creditAnalysisResponse.name,
                    lastName = creditAnalysisResponse.lastName,
                    cpf = creditAnalysisResponse.cpf,
                    birthDate = creditAnalysisResponse.birthDate
                )
            }
            return EmployeeResponse(
                id = employee.id,
                personInfo = personInfo,
                teamName = employee.team.name,
                role = employee.role
            )
        }
    }

}
