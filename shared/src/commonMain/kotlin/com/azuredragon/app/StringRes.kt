package com.azuredragon.app

import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.StringResource

expect class StringRes {
    fun getString(
        id: StringResource,
        args: List<Any>? = null
    ): String

    fun getPluralString(
        id: PluralsResource,
        quantity: Int,
        args: List<Any>? = null,
    ): String
}