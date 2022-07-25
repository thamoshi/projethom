package br.com.creditas.projethom.controller

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
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/employees")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @GetMapping
    fun getEmployees(): List<EmployeeResponse> {
        return employeeService.listEmployees()
    }

    @GetMapping("/{id}")
    fun getEmployeeById(
        @PathVariable id: UUID
    ): EmployeeResponse {
        return employeeService.getEmployeeById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postEmployee(
        @RequestBody @Valid newEmployeeRequest: EmployeeRequest
    ): EmployeeResponse = employeeService.registerEmployee(newEmployeeRequest)


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun putEmployee(
        @PathVariable id: UUID,
        @RequestBody @Valid updateEmployeeRequest: EmployeeRequest
    ): EmployeeResponse = employeeService.updateEmployee(id, updateEmployeeRequest)

    @DeleteMapping("/{id}")
    fun deleteEmployee(
        @PathVariable id: UUID
    ) {
        employeeService.deleteEmployeeById(id)
    }
}
