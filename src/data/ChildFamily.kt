package data

import kotlin.js.Date

data class Child (
        var id: Long?,
        var firstName: String?,
        var lastName: String?,
        var pesel: Int,
        var dateOfBirth: Date?,
        var gender: Int,
        var father: FatherFamily?

//    companion object {
//
//        val MALE = 1
//        val FEMALE = 0
//    }
)
