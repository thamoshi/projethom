package br.com.creditas.projethom.service

import br.com.creditas.projethom.dto.TeamRequest
import br.com.creditas.projethom.dto.TeamResponse
import br.com.creditas.projethom.exception.NotEnumValueException
import br.com.creditas.projethom.exception.NotFoundException
import br.com.creditas.projethom.model.Tribe
import org.springframework.stereotype.Service
import br.com.creditas.projethom.repository.TeamRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import java.util.UUID

@Service
class TeamService(
    private val teamRepository: TeamRepository
) {

    private val teamNotFoundMessage: String =
        "team not found. Try listing all the teams registered to get the specific ID"
    private val notTribeValueMessage: String = "tribe not found. Must be in ${Tribe.values().toList()}"

    fun listTeams(tribe: String?): List<TeamResponse> {
        return tribe?.let {
            try {
                val teams = teamRepository.findByTribe(
                    tribe.let {
                        Tribe.valueOf(tribe.uppercase())
                    }
                )
                teams.map {
                    TeamResponse.fromEntity(it)
                }
            } catch (e: IllegalArgumentException) {
                throw NotEnumValueException(notTribeValueMessage)
            }
        } ?: teamRepository.findAll().map {
            TeamResponse.fromEntity(it)
        }
    }

    fun getTeamById(id: UUID): TeamResponse {
        try {
            val team = teamRepository.getReferenceById(id)
            return TeamResponse.fromEntity(team)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw NotFoundException(teamNotFoundMessage)
        }
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
        try {
            val team = teamRepository.getReferenceById(id)
            val newTribe = Tribe.valueOf(updateTeamRequest.tribe.uppercase())
            team.name = updateTeamRequest.name
            team.description = updateTeamRequest.description
            team.tribe = newTribe
            teamRepository.save(team)
            return TeamResponse.fromEntity(team)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw NotFoundException(teamNotFoundMessage)
        } catch (e: IllegalArgumentException) {
            throw NotEnumValueException(notTribeValueMessage)
        }
    }

    fun deleteTeamById(
        id: UUID
    ) {
        try {
            teamRepository.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw NotFoundException(teamNotFoundMessage)
        }
    }

}
