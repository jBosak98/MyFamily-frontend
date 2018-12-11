package data

import kotlin.js.Date

data class FatherFamily (
        var id: Long?,
        var firstName: String?,
        var lastName: String?,
        var pesel: Int?,
        var dateOfBirth: Date?,
        var children: Set<Child>
)