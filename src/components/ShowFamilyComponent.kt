package components

import app.MainView
import data.FatherData
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import service.familiesService


class ShowFamilyComponent : RComponent<ShowFamilyProps, ShowFamilyComponent.ShowFamilyState>() {
    init { state.families = mutableListOf() }

    override fun componentDidMount() {
        familiesService()
                .then { result -> setState { result.toCollection(families) } }
                .catch { e -> console.log(e.toString()) }
    }

    override fun RBuilder.render() {

        div("content") {
            h1("header") { +"Families" }
            ul {
                if ( !state.families.isNullOrEmpty())
                    for (i in state.families)
                        li {
                            a{
                                +"${i.firstName} ${i.lastName}"
                                attrs {
                                    onClickFunction = {
                                        props.userId(i.id)
                                        props.handler(MainView.details)
                                    }
                                }

                            }
                            ul {
                                li { +"PESEL ${i.pesel}" }
                                i.dateOfBirth.let { it ->
                                    li { +"Date of birthday: ${it.getDate()}.${it.getMonth()}.${it.getFullYear()}" }
                                }
                            }
                        }


            }

        }
    }


    interface ShowFamilyState : RState {
        var families: MutableList<FatherData>
    }
}



interface ShowFamilyProps : RProps {
    var handler: (MainView) -> Unit
    var userId: (Long) -> Unit

}