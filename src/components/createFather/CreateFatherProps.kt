package components.createFather

import data.FatherValueObject
import react.RProps

interface CreateFatherProps : RProps {
    var isFatherFinished: (Boolean) -> Unit
    var fatherProps: (FatherValueObject) -> Unit
    var numberOfChildren: (Int) -> Unit
}