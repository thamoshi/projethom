package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.TeamRequest
import br.com.creditas.projethom.dto.TeamResponse
import br.com.creditas.projethom.model.Tribe
import org.springframework.stereotype.Service
import br.com.creditas.projethom.repository.TeamRepository
import java.util.UUID

@Service
class TeamService(
    private val teamRepository: TeamRepository
) {

    fun listTeams(tribe: String?): List<TeamResponse> {
        if (tribe == null) {
            return teamRepository.findAll().map {
                TeamResponse.fromEntity(it)
            }
        } else {
            return try {
                val teams = teamRepository.findByTribe(
                    tribe.let {
                        Tribe.valueOf(tribe.uppercase())
                    }
                )
                teams.map {
                    TeamResponse.fromEntity(it)
                }
            } catch (e: IllegalArgumentException) {
                emptyList()
            }
        }
    }

    fun getTeamById(id: UUID): TeamResponse {
        val team = teamRepository.getReferenceById(id)
        return TeamResponse.fromEntity(team)
    }

    fun registerTeam(
        teamRequest: TeamRequest
    ): TeamResponse {
        val team = TeamRequest.toEntity(teamRequest)
        teamRepository.save(team)
        return TeamResponse.fromEntity(team)
    }

    fun updateTeam(
        id: UUID,
        updateTeamRequest: TeamRequest
    ): TeamResponse {
        val team = teamRepository.getReferenceById(id)
        team.name = updateTeamRequest.name
        team.description = updateTeamRequest.description
        team.tribe = updateTeamRequest.tribe
        teamRepository.save(team)
        return TeamResponse.fromEntity(team)
    }

    fun deleteTeamById(
        id: UUID
    ) {
        teamRepository.deleteById(id)
    }

}
