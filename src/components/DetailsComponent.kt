package components

import data.FatherData
import react.*
import react.dom.*
import service.getFamily

class DetailsComponent : RComponent<DetailsProps, DetailsState>() {
    override fun componentDidMount() {
        console.log("id${props.id}")
        getFamily(props.id).then {
            setState {
                family = it
                console.log(family)
            }
        }.catch { e ->
            console.log(e)
        }
    }

    override fun RBuilder.render() {

        div("content") {
            div {
                ul {
                    li {
                        h1 { +"${state.family?.firstName} ${state.family?.lastName}" }

                        table {
                            tbody {
                                tableElement("PESEL", state.family?.pesel.toString())
                                state.family?.dateOfBirth?.apply {
                                    tableElement("DATE OF BIRTHDAY", "${getDate()}.${getMonth()}.${getFullYear()}")
                                }

                            }
                        }
                    }
                }
            }
            ul {
                if (!state.family?.children.isNullOrEmpty())
                    for (i in state.family?.children!!) {
                        li {
                            table {
                                tbody {
                                    tableElement("IMIE", i.firstName!!)
                                    tableElement("NAZWISKO", i.lastName!!)
                                    tableElement("PESEL", i.pesel.toString())
                                    i.dateOfBirth?.apply {
                                        tableElement("DATE OF BIRTHDAY", "${getDate()}.${getMonth()}.${getFullYear()}")
                                    }
                                }
                            }
                        }

                    }
            }

        }

    }

    private fun RBuilder.tableElement(type: String, value: String) {
        tr {
            td { +type }
            th { +value }
        }
    }

}

interface DetailsState : RState {
    var family: FatherData?
}

interface DetailsProps : RProps {
    var id: Int
}

fun RBuilder.detailsComponent(s: String) = child(DetailsComponent::class) {
    this.attrs.id = s.toInt()
}