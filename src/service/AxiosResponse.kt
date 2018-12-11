package service

external interface AxiosResponse<T> {
    val data: T
    val status: Number
    val statusText: String
    val headers: dynamic
    val config: AxiosConfigSettings
}

