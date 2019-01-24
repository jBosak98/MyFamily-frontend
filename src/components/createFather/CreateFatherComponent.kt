package components

import components.AddFamilyComponent.Companion.INPUT_DOB
import components.AddFamilyComponent.Companion.INPUT_FIRSTNAME
import components.AddFamilyComponent.Companion.INPUT_LASTNAME
import components.AddFamilyComponent.Companion.INPUT_NUM_OF_CHILDREN
import components.AddFamilyComponent.Companion.INPUT_PESEL
import components.createFather.CreateFatherProps
import components.createFather.CreateFatherState
import data.FatherValueObject
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.get
import react.RBuilder
import react.RComponent
import react.ReactElement
import react.dom.*
import react.setState
import service.isValidPesel
import kotlin.browser.document
import kotlin.js.Date


class CreateFatherComponent : RComponent<CreateFatherProps, CreateFatherState>() {

    override fun componentWillMount() {
        setState {
            isValidate = false
            father = FatherValueObject(null, null, null, null, emptyArray())
        }
    }

    override fun RBuilder.render() {
        h1("new-father-header") { +"FATHER" }
        br { }
        whiteDiv {
            fatherForm()
            br { }
            submitButton()
        }
    }



    private fun RBuilder.submitButton() {
        a(classes = "material-icons submit-icon") {
            +"done"
            attrs.onClickFunction = {
                if(isFormValidate(document.getElementById("fatherForm") as HTMLFormElement)){
                    props.fatherProps(state.father)
                    props.isFatherFinished(true)
                }
            }
        }
    }

    private fun isFormValidate(fatherForm: HTMLFormElement): Boolean {
        (fatherForm["pesel"] as HTMLInputElement).also {
            it.checkValidity()
        }
        return fatherForm.reportValidity()
    }

    private fun RBuilder.fatherForm() {
        form(classes = "content-white-padding") {
            attrs.id = "fatherForm"
            inputElement(INPUT_FIRSTNAME, InputType.text) {
                required = true
            }
            inputElement(INPUT_LASTNAME, InputType.text) {
                required = true
            }
            inputElement(INPUT_PESEL, InputType.number) {
                required = true
            }
            inputElement(INPUT_DOB, InputType.date) {
                required = false
            }
            inputElement(INPUT_NUM_OF_CHILDREN, InputType.number) {
                required = true
                min = "0"
                max = "20"
            }
        }
    }

    private fun updateFather(hie: HTMLInputElement) {
        setState {
            when (hie.id) {
                INPUT_PESEL -> father.pesel = hie.value
                INPUT_NUM_OF_CHILDREN -> props.numberOfChildren(hie.valueAsNumber.toInt())
                INPUT_DOB -> father.dateOfBirth = hie.valueAsDate as? Date
                INPUT_FIRSTNAME -> father.firstName = hie.value
                INPUT_LASTNAME -> father.lastName = hie.value
            }
        }
        props.fatherProps(state.father)
    }


    private fun RBuilder.inputElement(name: String,
                                      type: InputType,
                                      setAttrs: (INPUT).() -> Unit
    ): ReactElement = div("text-to-left") {
        label("input-label") { +name }
        div {
            input(classes = "input whole-width", type = type, name = name)
                {inputAttrs(name,attrs,setAttrs)}
        }
    }

    private fun inputAttrs(name:String,input: INPUT, setAttrs: (INPUT).() -> Unit) =
            input.apply{
                id = name
                setAttrs(this)
                onChangeFunction = {
                    if (input.id == INPUT_PESEL)   checkPesel(it.target as HTMLInputElement)
                    updateFather(it.target as HTMLInputElement)
                }
            }



    private fun checkPesel(inputElement: HTMLInputElement) =
            when (isValidPesel(inputElement.value)) {
                false -> inputElement.setCustomValidity("Invalid pesel!")
                true -> inputElement.setCustomValidity("")
            }



    private inline fun RBuilder.whiteDiv(
            function: react.dom.RDOMBuilder<kotlinx.html.LI>.() -> Unit)
            = ul("inline-block") { li { function() } }

}
