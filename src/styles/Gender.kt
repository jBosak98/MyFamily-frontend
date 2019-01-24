package styles

import components.AddChildrenComponent
import kotlinx.css.Cursor
import kotlinx.css.Display
import kotlinx.css.properties.Time
import kotlinx.css.properties.scaleX
import kotlinx.css.properties.transform
import kotlinx.css.properties.transition
import styled.StyleSheet

object GenderStyle : StyleSheet("ComponentStyles", isStatic = false) {
    val male by css {
        cursor = Cursor.pointer
        display = Display.inlineBlock

        transform {
            transition(duration = Time("0.8s"))
            scaleX(-1)
        }
    }
    val female by css {
        cursor = Cursor.pointer
        display = Display.inlineBlock

        transform {
            transition(duration = Time("0.8s"))
            scaleX(1)
        }
    }
}