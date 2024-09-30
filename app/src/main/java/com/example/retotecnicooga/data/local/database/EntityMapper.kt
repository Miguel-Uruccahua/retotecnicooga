package com.example.retotecnicooga.data.local.database

interface EntityMapper<Domain, Entity> {

    fun asEntity(domain: Domain): Entity

    fun asDomain(entity: Entity): Domain
}
