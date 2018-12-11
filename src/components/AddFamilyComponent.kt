package components

import kotlinext.js.js
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.style
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1
import react.dom.h5
import react.dom.input
import react.router.dom.redirect

class AddFamilyComponent: RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        div ("addFamily"){


            var itemsInRow = 3
            div {
                attrs.style = js {
                    position =  "flex"
                    alignItems = "center"
                    justifyContent = "center"
                    display = "flex"
                    flexDirection = "column"
                } as String
                for(numOfRow in 0..5/itemsInRow){
                    div{
                        attrs.style = js {
                            display = "flex"
                            flexDirection = "row"
                            alignItems = "center"
                        } as String
                        console.log(numOfRow.toString())
                        for (numOfColumn in numOfRow*itemsInRow until 5) {
                            if (numOfColumn >= itemsInRow*(numOfRow+1)){
                                break
                            }
                            nextChild(numOfColumn+numOfRow*itemsInRow)
                        }
                    }
                }
//                buttons()

            }

        }
    }

}

private fun RBuilder.nextChild(index: Int){
//    var child: dataClass.Child? = dataClass.Child(null,null,null, null,null)
    div {
        attrs.style = js {
            padding = "1rem"
            marginRight = "1rem"
            marginLeft = "1rem"
            marginTop = "1rem"
            background = "cyan"
        } as String
        h5 { +"Name" }
        input {
            attrs.id = "nameChild"
            attrs {
                type = InputType.text
                onChangeFunction = {
//                    child!!.name = (it.target as HTMLInputElement).value
//                    onChangeChild(index, child)
                }
            }
        }
        h5 { +"Surname" }
        input {
            attrs {
                type = InputType.text
                onChangeFunction = {
//                    child!!.surname = (it.target as HTMLInputElement).value
//                    onChangeChild(index, child)
                }
            }}
        h5 { +"Pesel" }
        input { attrs.type = InputType.number
//            attrs.id = "peselChild"
            attrs {
                onChangeFunction = {
//                    child!!.pesel = (it.target as HTMLInputElement).valueAsNumber.toInt()
//                    onChangeChild(index, child)
                }
            }

        }
        h5 { +"Date   of birth" }
        input {
            attrs {
                type = InputType.date
                onChangeFunction = {
//                    child!!.dateOfBirth = (it.target as HTMLInputElement).valueAsDate
//                    onChangeChild(index, child)
                }
            }
        }
        div {
            attrs.style = js {
                display = "flex"
                alignItems = "center"
                justifyContent = "center"
            } as String
            div {
                attrs.style  = js {
                    marginRight = "1rem"
                } as String
                h5 { +"Male" }
                input {
                    attrs {
                        name = "genderInput$index"

                        type = InputType.radio
                        onChangeFunction = {

//                            child!!.gender = MALE
//                            onChangeChild(index, child)
                        }
                    }
                }

            }
            div {
                attrs.style  = js {
                    marginLeft = "1rem"
                } as String
                h5 { +"Female" }
                input {
                    attrs {
                        name = "genderInput$index"
                        type = InputType.radio
                        onChangeFunction = {
//                            child!!.gender = FEMALE
//                            onChangeChild(index, child)
                        }
                    }
                }

            }
        }

    }
//    return child

}