package components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h1

class SearchFamilyComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        h1 {
            +"SEARCH"
        }
    }

}

fun RBuilder.searchFamilyComponent() = child(SearchFamilyComponent::class){

}