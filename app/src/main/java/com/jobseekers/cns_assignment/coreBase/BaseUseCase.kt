package com.jobseekers.cns_assignment.coreBase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeoutException


abstract class BaseUseCase {

    /**
     * This method fetches the data from remoted service and emit data as flow to maintain API states
     */

    fun <T> baseApiResultHandler(call: suspend () -> Response<T>): Flow<APIState<Any>?> = flow {
        emit(APIState.ShowHideDialog(true))
        try {
            val response = call()
            if (response.isSuccessful) {
                emit(APIState.ShowHideDialog(false))
                emit(response.body()?.let { APIState.NetworkResponseSuccess(it) })
            } else {
                emit(APIState.ShowHideDialog(false))
                response.errorBody().let { error ->
                    error?.close()
                    emit(APIState.Error(error.toString()))
                }
            }

        } catch (e: Exception) {
            emit(APIState.ShowHideDialog(false))
            e.printStackTrace()
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        502 -> emit(APIState.Error(ApiCode.BAD_GATEWAY.codeOrDesc))
                        500 -> emit(APIState.Error(ApiCode.INTERVAL_SERVER.codeOrDesc))
                        404 -> emit(APIState.Error(ApiCode.URL_NOT_RESPONDING.codeOrDesc))
                        else -> {
                            emit(APIState.Error(ApiCode.URL_NOT_RESPONDING.codeOrDesc))
                        }
                    }
                }
                is TimeoutException -> {
                    emit(APIState.Error(ApiCode.TIME_OUT.codeOrDesc))
                }
                is IOException -> {
                    emit(APIState.Error(ApiCode.INTERVAL_SERVER.codeOrDesc))
                }else ->{
                emit(APIState.Error(e.localizedMessage?:ApiCode.UN_EXPECTED.codeOrDesc))
                }

            }


        }

    }.flowOn(Dispatchers.IO)


}

enum class ApiCode(val codeOrDesc: String) {
    NO_INTERNET("no address associated"),
    TIME_OUT("It seems you have a connection issue, please try again later"),
    BAD_GATEWAY("We are having trouble reaching server, please try again later."),
    INTERVAL_SERVER("Something went wrong at our end, please try again later."),
    URL_NOT_RESPONDING("Resource at given url is not responding."),
    UN_EXPECTED("Something unexpected happened, please try again later."),

}