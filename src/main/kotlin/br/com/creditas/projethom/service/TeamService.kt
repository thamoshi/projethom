package br.com.creditas.projethom.service

import org.springframework.stereotype.Service
import br.com.creditas.projethom.model.Team
import br.com.creditas.projethom.repository.TeamRepository

@Service
class TeamService(
  private val teamRepository: TeamRepository
) {

  fun teamList(): List<Team> {
    return teamRepository.findAll()
  }

}
