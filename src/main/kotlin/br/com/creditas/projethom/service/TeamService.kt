package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.TeamView
import br.com.creditas.projethom.mapper.TeamViewMapper
import org.springframework.stereotype.Service
import br.com.creditas.projethom.repository.TeamRepository

@Service
class TeamService(
  private val teamRepository: TeamRepository,
  private val teamViewMapper: TeamViewMapper
) {

  fun teamList(): List<TeamView> {
    val teams = teamRepository.findAll()
    return teams.map{
      teamViewMapper.map(it)
    }
  }

}
