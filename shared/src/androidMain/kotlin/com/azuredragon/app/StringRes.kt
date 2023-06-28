package com.azuredragon.app

import android.content.Context
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.format

actual class StringRes(
    private val context: Context,
) {
    actual fun getString(
        id: StringResource,
        args: List<Any>?,
    ): String {
        return if (args == null) {
            id.getString(context)
        } else {
            id.format(args).toString(context)
        }
    }

    actual fun getPluralString(
        id: PluralsResource,
        quantity: Int,
        args: List<Any>?,
    ): String {
        return if (args == null) {
            id.getQuantityString(
                context = context,
                number = quantity,
            )
        } else {
            id.format(
                number = quantity,
                args = args,
            ).toString(context)
        }
    }

}