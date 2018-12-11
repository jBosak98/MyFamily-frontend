package sidebar

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*

class Sidebar: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        nav("sidebar") {
            ul("sidebar-list") {
                li ("sidebar-element"){
                    a ("/",null,"sidebar-link"){
                        i("material-icons"){
                            +"supervised_user_circle"
                        }
                    }
                }
                li("sidebar-element") {
                    a ("/search",null,"sidebar-link"){
                        i ("material-icons"){
                            +"search"
                        }
                    }
                }
                li("sidebar-element"){
                    a("/add",null,"sidebar-link") {
                        i("material-icons") {
                            +"add_circle"
                        }
                    }

                }
            }
        }    }

}
