@file:Suppress("INTERFACE_WITH_SUPERCLASS",
        "OVERRIDING_FINAL_MEMBER",
        "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
        "CONFLICTING_OVERLOADS",
        "EXTERNAL_DELEGATION",
        "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
        "UnsafeCastFromDynamic")


package service

import data.FatherEntity
import data.FatherValueObject
import kotlinext.js.jsObject
import kotlinx.html.InputFormMethod
import kotlin.js.Date
import kotlin.js.Promise


fun postFamily(family: FatherValueObject) {
    val postFamiliConfig: AxiosRequestConfig = jsObject {
        url = restStrings.FAMILY_POST_URL
        method = InputFormMethod.post
        timeout = 5000
    }

    Axios.post<FatherValueObject>(restStrings.FAMILY_POST_URL, family, postFamiliConfig).then { res ->
        console.log("post$res")
    }.catch {
        console.log("post:$it")
    }

}

fun familiesService(): Promise<Array<FatherEntity>> {
    val getFamiliesConfig: AxiosRequestConfig = jsObject {
        method = InputFormMethod.get
        timeout = 5000
    }
    return Axios.get<Array<FatherEntity>>(
            restStrings.FAMILIES_GET_URL,
            getFamiliesConfig).then { result ->
        result.data.forEach { it ->
           it.dateOfBirth = it.dateOfBirth?.let { fixDate(it) }

        }
        result.data
        }
}

fun getFamily(id: Long): Promise<FatherEntity> {
    val getFamilyConfig: AxiosRequestConfig = jsObject {
        url = "${restStrings.FAMILY_DETAILS_GET_URL}$id"
        method = InputFormMethod.get
        timeout = 5000
    }
    return Axios.get<FatherEntity>(
            restStrings.FAMILY_DETAILS_GET_URL + id,
            getFamilyConfig).then { result ->
        result.data.children.forEach { child ->
            child.dateOfBirth = child.dateOfBirth?.let { fixDate(it) }
        }
        result.data.dateOfBirth = result.data.dateOfBirth?.let { fixDate(it) }
        result.data
    }
}

fun fixDate(d: Date): Date{
    var dates = d.toString()

                .split("T")[0]
                .split("-")
    return  Date(
            dates[0].toInt(),
            dates[1].toInt(),
            dates[2].toInt())
}


object restStrings {
    private const val HOST_URL = "http://localhost:8080"
    const val FAMILIES_GET_URL = "$HOST_URL/api/fathers"
    const val FAMILY_POST_URL = "$HOST_URL/api/father/add"
    const val FAMILY_DETAILS_GET_URL = "$HOST_URL/api/father/"
}