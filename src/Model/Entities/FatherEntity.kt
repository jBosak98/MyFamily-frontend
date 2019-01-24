package data

import Model.Entities.ChildEntity
import kotlin.js.Date

data class FatherEntity (
        var id: Long,
        var firstName: String?,
        var lastName: String?,
        var pesel: Int?,
        var dateOfBirth: Date?,
        var children: Set<ChildEntity>
)