package components

import data.Child
import data.FatherData
import data.FatherFamily
import kotlinext.js.jsObject
import react.*
import react.dom.*
import service.AxiosConfigSettings
import kotlin.js.Date

class ShowFamilyComponent : RComponent<RProps, ShowFamilyComponent.ShowFamilyState>() {
    init {
        state.families = mutableListOf()
    }

    override fun componentDidMount() {
        getFamilies()
    }

    override fun RBuilder.render() {
        div("content") {
            h1("header") { +"Families" }
            ul {
                for (i in 0 until state.families.size) {
                    li {
                        a("details/$i") { +"${state.families[i].firstName} ${state.families[i].lastName}" }
                        ul {
                            li { +"PESEL ${state.families[i].pesel}" }
                            state.families[i].dateOfBirth.let { it ->
                                li { +"Date of birthday: ${it.getDate()}.${it.getMonth()}.${it.getFullYear()}" }
                            }
                        }
                    }

                }

            }

        }
    }

    private fun getFamilies() {
        val config: AxiosConfigSettings = jsObject {
            url = restStrings.FAMILIES_GET_URL
            method = restStrings.GET_METHOD
            timeout = 3000
        }
        service.axios<Array<FatherData>>(config).then { res ->

            setState {
                families.clear()
                res.data.forEachIndexed { index, fatherData ->
                    families.add(fatherData)
                    fatherData.dateOfBirth.let {
                        var dates = it.toString()
                                .split("T")[0]
                                .split("-")
                        families[index].dateOfBirth = Date(
                                dates[0].toInt(),
                                dates[1].toInt(),
                                dates[2].toInt())
                    }
                }
            }
            console.log(res)
            postFamily()

        }.catch { error ->
            console.log(error)
        }

        }

    private fun postFamily() {
        val deborah: FatherFamily? = FatherFamily(
                null,
                "deborah",
                "aubert",
                654565433,
                Date(1990,10,25),
                emptySet<Child>())
        val config: AxiosConfigSettings = jsObject {
            url = restStrings.FAMILY_POST_URL
            method = restStrings.POST_METHOD
            data = deborah
            timeout = 5000

        }

        service.axios<FatherFamily>(config).then {
            console.log("post: $it")
        }.catch { error ->
            console.log("ERROR: $error")
        }
    }

    interface ShowFamilyState:RState {
        var families:MutableList<FatherData>
    }
}

fun RBuilder.showFamilyComponent() = child(ShowFamilyComponent::class){

}
    object restStrings {
        const val FAMILIES_GET_URL = "http://192.168.191.216:8080/api/fathers"
        const val FAMILY_POST_URL = "http://192.168.191.216:8080/api/father/add"
        const val POST_METHOD = "post"
        const val GET_METHOD = "get"

    }

