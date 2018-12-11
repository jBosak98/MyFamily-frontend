package service

import kotlin.js.Promise

@JsModule("axios")
external fun <T> axios(config: AxiosConfigSettings): Promise<AxiosResponse<T>>

