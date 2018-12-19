package data

import org.w3c.dom.NavigatorID
import react.Child
import kotlin.js.Date

data class FatherPostData(
//        var id: Long,
        var firstName: String?,
        val lastName: String?,
        val pesel: Int,
        var dateOfBirth: String?,
        val children: Set<ChildPostData>
)