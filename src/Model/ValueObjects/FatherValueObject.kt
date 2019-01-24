package data

import kotlin.js.Date

class FatherValueObject(
        var firstName: String?,
        var lastName: String?,
        var pesel: String?,
        var dateOfBirth: Date?,
        var children: Array<ChildValueObject?>
)