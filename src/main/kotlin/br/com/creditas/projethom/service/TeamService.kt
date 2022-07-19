package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.TeamRequest
import br.com.creditas.projethom.dto.TeamResponse
import org.springframework.stereotype.Service
import br.com.creditas.projethom.repository.TeamRepository
import java.util.UUID

@Service
class TeamService(
  private val teamRepository: TeamRepository,
  private val teamResponse: TeamResponse
) {

  fun teamList(): List<TeamResponse> {
    return teamRepository.findAll().map{
      teamResponse.fromEntity(it)
    }
  }

  fun teamById(id:UUID): TeamResponse {
    val team = teamRepository.findById(id).orElseThrow()
    return teamResponse.fromEntity(team)
  }

  fun registerTeam(
    teamRequest: TeamRequest
  ){
    val team = teamRequest.toEntity(teamRequest)
    teamRepository.save(team)
  }

}
