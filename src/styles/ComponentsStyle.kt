package styles

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import styled.StyleSheet

object ComponentStyles : StyleSheet("ComponentStyles", isStatic = false) {
    val block by css {
        display = Display.block
    }
    val inlineBlock by css {
        display = Display.inlineBlock
    }
    val wholeWidth by css {
        width = LinearDimension("100% ")
    }
    val childInputMargin by css {
        marginBottom = LinearDimension(value = "20px")
    }
    val inputLabel by css {
        fontSize = LinearDimension("16px")
        fontWeight = FontWeight("700")
        lineHeight = LineHeight("24px")
    }
    val contentWhitePadding by css {
        padding(right = LinearDimension("80px"),
                top = LinearDimension("40px"),
                left = LinearDimension("80px"),
                bottom = LinearDimension("0px"))
        display = Display.inlineBlock
    }
    val testToLeft by css {
        padding(all = LinearDimension("0px"))
        textAlign = TextAlign.left
    }
    val header by css {
        fontWeight = FontWeight("500")
        fontSize = LinearDimension("2.5rem")
    }
    val input by css {
        padding(vertical = LinearDimension(".375rem"),
                horizontal = LinearDimension(".75rem"))
        fontFamily = "Montserrat, sans-serif"
        backgroundClip = BackgroundClip.paddingBox
        borderWidth = LinearDimension("1px")
        borderStyle = BorderStyle.solid
        borderRadius = LinearDimension("0.25rem")
        borderColor = Color("#ced4da")
    }
}

//val styles = CSSBuilder().apply{
//    body{
//
//    }
//}
