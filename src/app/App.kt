package app

import components.ShowFamilyComponent
import components.AddFamilyComponent
import components.detailsComponent
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch
import sidebar.Sidebar


class Application : RComponent<Application.ApplicationRProps, Application.ApplicationRState>() {


    init {
        state = ApplicationRState(MainView.show)
    }

    override fun componentDidMount() {

//            state =  MainView.show

    }

    override fun RBuilder.render() {



        console.log(state.selected)
        div ("container") {
            sidebar()
            browserRouter {
                switch {
                    route("/add", AddFamilyComponent::class, exact = true)
                    route<RProps>("/details/:id") { props ->
                        console.log(props.location.pathname.split("/")[2])

                        detailsComponent(props.location.pathname.split("/")[2])
                    }
                    route("/", ShowFamilyComponent::class, exact = false)
                }
            }
            when(state.selected) {
//                MainView.show  -> showFamilyComponent()
//                    showFamilyComponent()
//                MainView.details -> detailsComponent()

            }


        }

    }



    interface ApplicationRProps : RProps {

    }

    class ApplicationRState(var selected: MainView) : RState
}



fun RBuilder.Application() = child(Application::class) {

}

enum class MainView {
    show,
    add,
    search,
    details
}

 fun RBuilder.sidebar()  = child(Sidebar::class ){

 }
