package com.azuredragon.app

import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
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

}