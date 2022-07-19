package br.com.creditas.projethom.controller

import br.com.creditas.projethom.dto.TeamRequest
import br.com.creditas.projethom.dto.TeamResponse
import br.com.creditas.projethom.service.TeamService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/teams")
class TeamController (
  private val service: TeamService
) {

  @GetMapping
  fun getTeams(): List<TeamResponse> {
    return service.teamList()
  }

  @GetMapping("/{id}")
  fun getTeamById(
    @PathVariable id:UUID
  ): TeamResponse {
    return service.teamById(id)
  }

  @PostMapping
  @Transactional
  fun postTeam(
    @RequestBody @Valid newTeamRequest: TeamRequest
  ) {
    service.registerTeam(newTeamRequest)
  }

}
