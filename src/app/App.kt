package app

import components.*
import react.*
import react.dom.div
import sidebar.Sidebar
import react.router.dom.LinkProps


class Application : RComponent<RProps, Application.ApplicationRState>() {

    init { if(state.selected == null ) state = ApplicationRState(MainView.show,null) }

    override fun RBuilder.render() {

        div ("container") {
            sidebar()
            when(state.selected) {
                MainView.search -> searchFamilyComponent()
                MainView.details -> detailsComponent(state.userId!!)
                MainView.addFamily -> addFamilyComponent()
                MainView.show  -> showFamilyComponent()
            }
        }
    }


    fun RBuilder.sidebar()  = child(Sidebar::class ){
            attrs {
                actualView = state.selected
                handler = { navBarSelected(it) }
            }
    }

    fun RBuilder.showFamilyComponent() = child(ShowFamilyComponent::class) {
        attrs {
            userId = { setState { userId = it } }
            handler = { navBarSelected(it) }
        }
    }


    private fun navBarSelected(newSelected: MainView) = setState { selected = newSelected }


    class ApplicationRState(var selected: MainView, var userId: Long?) : RState
}



fun RBuilder.Application() = child(Application::class) { }

enum class MainView {
    show,
    addFamily,
    search,
    details
}


