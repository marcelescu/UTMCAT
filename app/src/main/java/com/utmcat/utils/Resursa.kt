package com.utmcat.utils

import com.utmcat.utils.Status.EROARE
import com.utmcat.utils.Status.INCARCARE
import com.utmcat.utils.Status.SUCCES

data class Resursa<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T): Resursa<T> = Resursa(status = SUCCES, data = data, message = null)

        fun <T> error(data: T?, message: String): Resursa<T> =
            Resursa(status = EROARE, data = data, message = message)

        fun <T> loading(data: T?): Resursa<T> = Resursa(status = INCARCARE, data = data, message = null)
    }

}