package br.com.creditas.projethom.controller

import br.com.creditas.projethom.model.Team
import br.com.creditas.projethom.service.TeamService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teams")
class TeamController (
  private val service: TeamService
) {

  @GetMapping
  fun getTeams(): List<Team> {
    return service.teamList()
  }

}
