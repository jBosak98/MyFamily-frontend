package components

import data.FatherValueObject
import react.*
import react.dom.div
import service.postFamily


class AddFamilyComponent : RComponent<RProps, AddFamilyState>() {
    companion object {
        const val INPUT_FIRSTNAME = "firstName"
        const val INPUT_LASTNAME = "lastName"
        const val INPUT_PESEL = "pesel"
        const val INPUT_DOB = "Date of birth"
        const val INPUT_NUM_OF_CHILDREN = "number of children"
    }

    override fun componentWillMount() = setState { isFatherFinished = false }

    override fun RBuilder.render() {
        div("content") {
            console.log("IS_FATHER_FINISHED: " + state.isFatherFinished)
            when (state.isFatherFinished) {
                true -> addChildrenComponent()
                false -> createFatherComponent()
            }
        }
    }


    fun RBuilder.createFatherComponent() = child(CreateFatherComponent::class) {
        attrs {
            isFatherFinished = { setState { isFatherFinished = it } }
            fatherProps = { setState { family = it } }
            numberOfChildren = { setState { numberOfChildren = it } }
        }
    }

    fun RBuilder.addChildrenComponent() = child(AddChildrenComponent::class) {
        attrs {
            itemsInRow = 3
            size =  state.numberOfChildren
            childrenProps = { it ->
                setState {
                    it.forEachIndexed {index, it ->
                        it?.let { child ->
                            family.children?.set(index,child)
                        }

                    }
                }
            }
            submitted = {
                setState {
                    if(family.children == null) family.children = emptyArray()
                    postFamily(family = family)
                }
            }
        }
    }
}


fun RBuilder.addFamilyComponent() = child(AddFamilyComponent::class) {

}

interface AddFamilyState : RState {
    var isFatherFinished: Boolean
    var family: FatherValueObject
    var numberOfChildren: Int
}