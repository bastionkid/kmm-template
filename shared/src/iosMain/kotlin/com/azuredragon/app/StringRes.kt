package com.azuredragon.app

import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.resources.format

actual class StringRes {
    actual fun getString(
        id: StringResource,
        args: List<Any>?,
    ): String {
        return if (args == null) {
            id.desc().localized()
        } else {
            id.format(args).localized()
        }
    }

    actual fun getPluralString(
        id: PluralsResource,
        quantity: Int,
        args: List<Any>?,
    ): String {
        return if (args == null) {
            id.desc(quantity).localized()
        } else {
            id.format(
                number = quantity,
                args =args,
            ).localized()
        }
    }
}