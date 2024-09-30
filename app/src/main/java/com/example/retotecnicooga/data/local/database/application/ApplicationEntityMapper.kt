package com.example.retotecnicooga.data.local.database.application

import com.example.retotecnicooga.data.local.database.EntityMapper
import com.example.retotecnicooga.domain.model.Application

object ApplicationEntityMapper:EntityMapper<Application, ApplicationEntity> {

    override fun asEntity(domain: Application): ApplicationEntity {
        return ApplicationEntity(
            id = domain.id,
            type = domain.type,
            minCompatibility = domain.minCompatibility,
            maxCompatibility = domain.maxCompatibility,
            state = domain.state,
            isUpToolsFollow = domain.isUpToolsFollow
        )
    }

    override fun asDomain(entity: ApplicationEntity): Application {
        return Application(
            id = entity.id,
            type = entity.type,
            minCompatibility = entity.minCompatibility,
            maxCompatibility = entity.maxCompatibility,
            state = entity.state,
            isUpToolsFollow = entity.isUpToolsFollow
        )
    }
}

fun Application.asEntity(): ApplicationEntity {
    return ApplicationEntityMapper.asEntity(this)
}

fun ApplicationEntity.asDomain(): Application {
    return ApplicationEntityMapper.asDomain(this)
}