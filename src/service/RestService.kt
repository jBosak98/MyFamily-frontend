package service

import data.FatherData
import data.FatherPostData
import kotlinext.js.jsObject
import kotlin.js.Date
import kotlin.js.Promise
import service.Axios


fun postFamily(family: FatherPostData) {
    val postFamiliConfig: AxiosRequestConfig = jsObject {
        url = restStrings.FAMILY_POST_URL
        method = restStrings.POST_METHOD
        timeout = 5000
    }

    Axios.post<FatherPostData>(restStrings.FAMILY_POST_URL, family, postFamiliConfig).then { res ->
        console.log("post$res")
    }.catch {
        console.log("post:$it")
    }

}

fun familiesService(): Promise<Array<FatherData>> {
    val getFamiliesConfig: AxiosRequestConfig = jsObject {
        url = restStrings.GET_METHOD
        method = restStrings.GET_METHOD
        timeout = 5000
    }
    return Axios.get<Array<FatherData>>(
            restStrings.FAMILIES_GET_URL,
            getFamiliesConfig).then { result ->
        result.data.forEach { it ->
            it.dateOfBirth = fixDate(it.dateOfBirth)

        }
        result.data
        }
}

fun getFamily(id: Long): Promise<FatherData> {
    val getFamilyConfig: AxiosRequestConfig = jsObject {
        url = "${restStrings.FAMILY_DETAILS_GET_URL}$id"
        method = restStrings.GET_METHOD
        timeout = 5000
    }
    return Axios.get<FatherData>(
            restStrings.FAMILY_DETAILS_GET_URL + id,
            getFamilyConfig).then { result ->
        result.data.children.forEach { child ->
            child.dateOfBirth = child.dateOfBirth?.let { fixDate(it) }
        }
        result.data.dateOfBirth = fixDate(result.data.dateOfBirth)
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
    private const val HOST_URL = "http://192.168.1.4:8080"
    const val FAMILIES_GET_URL = "$HOST_URL/api/fathers"
    const val FAMILY_POST_URL = "$HOST_URL/api/father/add"
    const val FAMILY_DETAILS_GET_URL = "$HOST_URL/api/father/"
    const val POST_METHOD = "post"
    const val GET_METHOD = "get"

}