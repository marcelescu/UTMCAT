package com.utmcat.utils

data class Resursa<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resursa<T> {
            return Resursa(Status.SUCCES, data, null)
        }

        fun <T> error(msg: String, data: T?): Resursa<T> {
            return Resursa(Status.EROARE, data, msg)
        }

        fun <T> loading(data: T?): Resursa<T> {
            return Resursa(Status.INCARCARE, data, null)
        }

    }

}