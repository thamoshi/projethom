package br.com.creditas.projethom.controller

import br.com.creditas.projethom.dto.CreditAnalysisResponse
import br.com.creditas.projethom.dto.EmployeeRequest
import br.com.creditas.projethom.dto.EmployeeResponse
import br.com.creditas.projethom.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/employees")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @GetMapping
    fun getEmployees(
        @RequestParam(required = false) teamName: String?
    ): List<EmployeeResponse> {
        return employeeService.listEmployees(teamName)
    }

    @GetMapping("/{id}")
    fun getEmployeeById(
        @PathVariable id: UUID,
        @RequestParam(required = false) withPersonInfo: Boolean
    ): EmployeeResponse {
        return employeeService.getEmployeeById(id, withPersonInfo)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerEmployee(
        @RequestBody @Valid newEmployeeRequest: EmployeeRequest
    ): EmployeeResponse = employeeService.registerEmployee(newEmployeeRequest)


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateEmployee(
        @PathVariable id: UUID,
        @RequestBody @Valid updateEmployeeRequest: EmployeeRequest
    ): EmployeeResponse = employeeService.updateEmployee(id, updateEmployeeRequest)

    @DeleteMapping("/{id}")
    fun deleteEmployee(
        @PathVariable id: UUID
    ) {
        employeeService.deleteEmployeeById(id)
    }

    @GetMapping("/info/{id}")
    fun getEmployeeInfo(
        @PathVariable id: UUID
    ): CreditAnalysisResponse {
        return employeeService.requestPersonInfo(id)
    }
}
