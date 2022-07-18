package br.com.creditas.projethom.mapper

import br.com.creditas.projethom.dto.TeamForm
import br.com.creditas.projethom.model.Team
import org.springframework.stereotype.Component

@Component
class TeamFormMapper(): Mapper<TeamForm, Team> {
  override fun map(t: TeamForm): Team {
    return Team(
      name = t.name,
      description = t.description,
      tribe = t.tribe
    )
  }
}
