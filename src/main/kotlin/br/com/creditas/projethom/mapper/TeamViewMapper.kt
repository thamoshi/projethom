package br.com.creditas.projethom.mapper

import br.com.creditas.projethom.dto.TeamView
import br.com.creditas.projethom.model.Team
import org.springframework.stereotype.Component

@Component
class TeamViewMapper(): Mapper<Team,TeamView> {

  override fun map(t: Team): TeamView {
    return TeamView(
      id = t.id,
      name = t.name,
      description = t.description,
      tribe = t.tribe,
      createdAt = t.createdAt
    )
  }

}
