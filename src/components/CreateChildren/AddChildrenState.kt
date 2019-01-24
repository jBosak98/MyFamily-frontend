package components.CreateChildren

import data.ChildValueObject
import react.RState

interface AddChildrenState : RState {
    var male: Boolean?
    var children: ArrayList<ChildValueObject?>
}