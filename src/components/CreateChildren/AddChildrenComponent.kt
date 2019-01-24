package components

import components.AddFamilyComponent.Companion.INPUT_DOB
import components.AddFamilyComponent.Companion.INPUT_FIRSTNAME
import components.AddFamilyComponent.Companion.INPUT_LASTNAME
import components.AddFamilyComponent.Companion.INPUT_PESEL
import components.CreateChildren.AddChildrenProps
import components.CreateChildren.AddChildrenState
import data.ChildValueObject
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.asList
import react.RBuilder
import react.RComponent
import react.dom.a
import react.dom.div
import react.dom.form
import react.setState
import service.isValidPesel
import styled.*
import styles.ComponentStyles
import styles.GenderStyle
import kotlin.browser.document


class AddChildrenComponent : RComponent<AddChildrenProps, AddChildrenState>() {


    override fun componentWillMount() {
        if (props.size == 0) {
            props.submitted(true)
        }
        setState {
            male = true
            children = ArrayList(props.size)
            for (i in 0 until props.size) {
                val c = ChildValueObject(null, null, null, null, 0)
                children.add(i, c)
            }
        }
    }

    override fun RBuilder.render() {
        div {
            styledH1 {
                +"CHILDREN"
                css(ComponentStyles.header)
            }

            form {
                attrs.id = "childrenForm"
                renderChildren()

            }

        }
        submitButton()

    }

    private fun RBuilder.renderChildren() {
        for (i in 0 until props.size) {
            styledUl {
                css(ComponentStyles.inlineBlock)
                styledLi {
                    css(ComponentStyles.block)
                    div("add-child-element") {
                        createChild(i)
                    }
                }
            }
        }
    }


    private fun RBuilder.submitButton() {
        a(classes = "material-icons submit-icon") {
            +"done"
            attrs.onClickFunction = {
                if (areChildrenValid(document.getElementById("childrenForm") as HTMLFormElement))
                    props.submitted(true)
            }
        }
    }

    private fun areChildrenValid(form: HTMLFormElement): Boolean {
        form.elements.asList().filter { it.id == "pesel" }.map {
            it as HTMLInputElement
            when (isValidPesel(it.value)) {
                false -> it.setCustomValidity("Invalid pesel!")
                else -> it.setCustomValidity("")
            }
        }
        form.checkValidity()
        if (form.reportValidity()) {
            return true
        }
        return false
    }

    private fun RBuilder.createChild(index: Int) {
        styledDiv {
            css(ComponentStyles.block)
            css(ComponentStyles.contentWhitePadding)
            css(ComponentStyles.testToLeft)

            inputElement(INPUT_FIRSTNAME, InputType.text, setAttrs = { required = true }, onChangeFun = {
                setState { children[index]?.firstName = it.value }
            })
            inputElement(INPUT_LASTNAME, InputType.text, setAttrs = { required = true }, onChangeFun = {
                setState { children[index]?.lastName = it.value }
            })
            inputElement(INPUT_PESEL, InputType.number, setAttrs = {
                required = true
            }, onChangeFun = {
                it.setCustomValidity("")
                setState { children[index]?.pesel = it.value }
            })
            inputElement(INPUT_DOB, InputType.date, setAttrs = {}, onChangeFun = {
                setState { children[index]?.dateOfBirth = it.valueAsDate }
            })

            styledLabel {
                css(ComponentStyles.inputLabel)
                +"MALE  "
            }
            genderButton(index)
            styledLabel {
                css(ComponentStyles.inputLabel)
                +"FEMALE  "
            }

        }
    }

    fun RBuilder.genderButton(i: Int) {
        styledDiv {
            attrs.onClickFunction = {
                setState {
                    when (children[i]?.gender) {
                        0 -> children[i]?.gender = 1
                        1 -> children[i]?.gender = 0
                    }
                }
            }
            when (state.children[i]?.gender) {
                1 -> css(GenderStyle.male)
                0 -> css(GenderStyle.female)
            }

            a(classes = "material-icons") { +"hdr_strong" }
        }

    }


    private fun RBuilder.inputElement(name: String, type: InputType, setAttrs: (INPUT).() -> Unit, onChangeFun: (HTMLInputElement) -> Unit) {
        styledLabel {
            css(ComponentStyles.inputLabel)
            +name
        }

        styledInput(name = name, type = type
        ) {
            css(ComponentStyles.block)
            css(ComponentStyles.wholeWidth)
            css(ComponentStyles.childInputMargin)
            css(ComponentStyles.input)

            attrs {
                id = name
                setAttrs(this)
                onChangeFunction = {
                    onChangeFun(it.target as HTMLInputElement)
                    props.childrenProps(state.children)

                }

            }
        }

    }
}



