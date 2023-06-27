package com.azuredragon.app

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
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
}