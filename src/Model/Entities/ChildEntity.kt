package Model.Entities

import data.FatherEntity
import kotlin.js.Date

data class ChildEntity (
        var id: Long,
        var firstName: String?,
        var lastName: String?,
        var pesel: Int,
        var dateOfBirth: Date?,
        var gender: Int,
        var father: FatherEntity?

//    companion object {
//
//        val MALE = 1
//        val FEMALE = 0
//    }
)
