package com.example.retotecnicooga.data.local.database.log

import com.example.retotecnicooga.data.local.database.EntityMapper
import com.example.retotecnicooga.domain.model.Log

object LogEntityMapper: EntityMapper<Log, LogEntity> {
    override fun asEntity(domain: Log): LogEntity {
        return LogEntity(
            id = domain.id,
            action = domain.action,
            idApplication = domain.idApplication,
            idAppDetail = domain.idAppDetail,
            reference = domain.reference,
            date = domain.date
        )
    }

    override fun asDomain(entity: LogEntity): Log {
        return Log(
            id = entity.id,
            action = entity.action,
            idApplication = entity.idApplication,
            idAppDetail = entity.idAppDetail,
            reference = entity.reference,
            date = entity.date
        )
    }


}

fun Log.asEntity(): LogEntity {
    return LogEntityMapper.asEntity(this)
}

fun LogEntity.asDomain(): Log {
    return LogEntityMapper.asDomain(this)
}