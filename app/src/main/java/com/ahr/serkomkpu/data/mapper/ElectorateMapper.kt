package com.ahr.serkomkpu.data.mapper

import com.ahr.serkomkpu.data.local.ElectorateEntity
import com.ahr.serkomkpu.domain.model.Electorate

fun Electorate.toEntity(): ElectorateEntity =
    ElectorateEntity(
        id = id,
        image = image,
        nik = nik,
        name = name,
        phone = phone,
        gender = gender,
        dateCollectionDate = dateCollectionDate,
        address = address
    )

fun List<ElectorateEntity>.toDomains(): List<Electorate> =
    map { it.toDomain() }

fun ElectorateEntity.toDomain(): Electorate =
    Electorate(
        id = id,
        image = image,
        nik = nik,
        name = name,
        phone = phone,
        gender = gender,
        dateCollectionDate = dateCollectionDate,
        address = address
    )
