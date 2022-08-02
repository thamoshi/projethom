package br.com.creditas.projethom.controller

import br.com.creditas.projethom.dto.TeamRequest
import br.com.creditas.projethom.dto.TeamResponse
import br.com.creditas.projethom.service.TeamService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/teams")
class TeamController(
    private val teamService: TeamService
) {
    @GetMapping
    fun getTeams(
        @RequestParam(required = false) tribe: String?
    ): List<TeamResponse> {
        return teamService.listTeams(tribe)
    }

    @GetMapping("/{id}")
    fun getTeamById(
        @PathVariable id: UUID
    ): TeamResponse {
        return teamService.getTeamById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerTeam(
        @RequestBody @Valid newTeamRequest: TeamRequest
    ): TeamResponse = teamService.registerTeam(newTeamRequest)

    @PutMapping("/{id}")
    fun updateTeam(
        @PathVariable id: UUID,
        @RequestBody @Valid updateTeamRequest: TeamRequest
    ): TeamResponse = teamService.updateTeam(id, updateTeamRequest)

    @DeleteMapping("/{id}")
    fun deleteTeam(
        @PathVariable id: UUID
    ) {
        teamService.deleteTeamById(id)
    }

}
