package com.example.retotecnicooga.data.local.database.appdetail

import com.example.retotecnicooga.data.local.database.EntityMapper
import com.example.retotecnicooga.domain.model.AppDetail

object AppDetailEntityMapper:EntityMapper<AppDetail,AppDetailEntity> {

    override fun asEntity(domain: AppDetail): AppDetailEntity {
        return AppDetailEntity(
            id = domain.id,
            idApplication = domain.idApplication,
            title = domain.title,
            description = domain.description,
            priority = domain.priority,
            state = domain.state,
            assignedTo = domain.assignedTo,
            dateCreated = domain.dateCreated,
            dateFinish = domain.dateFinish
        )
    }

    override fun asDomain(entity: AppDetailEntity): AppDetail {
        return AppDetail(
            id = entity.id,
            idApplication = entity.idApplication,
            title = entity.title,
            description = entity.description,
            priority = entity.priority,
            state = entity.state,
            assignedTo = entity.assignedTo,
            dateCreated = entity.dateCreated,
            dateFinish = entity.dateFinish
        )
    }
}

fun AppDetail.asEntity(): AppDetailEntity {
    return AppDetailEntityMapper.asEntity(this)
}

fun AppDetailEntity.asDomain(): AppDetail {
    return AppDetailEntityMapper.asDomain(this)
}