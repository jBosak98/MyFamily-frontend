package components.createFather

import data.FatherValueObject
import react.RState

interface CreateFatherState : RState {
    var isValidate: Boolean
    var father: FatherValueObject
}