package components

import app.MainView
import data.FatherData
import react.*
import react.dom.*
import service.familiesService


class ShowFamilyComponent : RComponent<ShowFamilyProps, ShowFamilyComponent.ShowFamilyState>() {
    init {
        state.families = mutableListOf()
    }

    override fun componentDidMount() {
        familiesService().then { result ->
            setState { result.forEach { it -> families.add(it) } }
        }.catch { e ->
            console.log(e.toString())
        }
    }

    override fun RBuilder.render() {

        div("content") {
            h1("header") { +"Families" }
            ul {
                if ( !state.families.isNullOrEmpty())
                    for (i in state.families)
                        li {
                            a("details/${i.id}") { +"${i.firstName} ${i.lastName}" }
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

fun RBuilder.showFamilyComponent() = child(ShowFamilyComponent::class) {


}


interface ShowFamilyProps : RProps {
    var x: MainView

}