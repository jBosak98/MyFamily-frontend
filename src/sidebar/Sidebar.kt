package sidebar

import app.MainView
import kotlinext.js.js
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*

class Sidebar: RComponent<Sidebar.SidebarProps, RState>() {

    override fun RBuilder.render() {
        nav("sidebar") {
            ul("sidebar-list") {
                sidebarItem("supervised_user_circle", MainView.show)
                sidebarItem("search", MainView.search)
                sidebarItem("add_circle", MainView.addFamily)
            }
        }
    }


    interface SidebarProps: RProps {
        var handler: (MainView) -> Unit
        var actualView: MainView
    }

    fun RBuilder.sidebarItem(name:String, view: MainView){
        li("sidebar-element"){
            a(classes = "sidebar-link") {
                i("material-icons pointer-cursor") {
                    +name
                    attrs { onClickFunction = { props.handler(view) } }
                }

            }
            if (view == props.actualView){
                attrs.style = js { background = "#00a8ff" }
            }
        }
    }

}
