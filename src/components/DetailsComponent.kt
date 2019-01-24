package components

import data.FatherEntity
import react.*
import react.dom.*
import service.getFamily

class DetailsComponent : RComponent<DetailsProps, DetailsState>() {

    override fun componentDidMount() {
        getFamily(props.id)
                .then { setState { family = it } }
                .catch { e -> console.log(e) }
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
                                    tableElement("firstName", i.firstName!!)
                                    tableElement("lastName", i.lastName!!)
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

    private fun RBuilder.tableElement(type: String, value: String) = tr {
            td { +type }
            th { +value }
    }


}

interface DetailsState : RState {
    var family: FatherEntity?
}

interface DetailsProps : RProps {
    var id: Long
}

fun RBuilder.detailsComponent(userId: Long) = child(DetailsComponent::class) {
    attrs { id = userId }
}