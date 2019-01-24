package components.CreateChildren

import data.ChildValueObject
import react.RProps

interface AddChildrenProps : RProps {
    var itemsInRow: Int
    var size: Int
    var childrenProps: (ArrayList<ChildValueObject?>) -> Unit
    var submitted: (Boolean) -> Unit
}
