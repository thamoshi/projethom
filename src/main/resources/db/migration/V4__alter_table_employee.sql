ALTER TABLE employee
    ADD CONSTRAINT UQ_PersonId_TeamId UNIQUE(person_id,team_id);