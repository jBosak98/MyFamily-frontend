package data

import kotlin.js.Date


external interface FatherData {
    val id: Long
    var firstName: String?
    val lastName: String?
    val pesel: Int
    var dateOfBirth: Date
    val children: Array<Child>
}