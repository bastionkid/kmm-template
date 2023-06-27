package com.azuredragon.app

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc

expect class StringRes {
    fun getString(
        id: StringResource,
        args: List<Any>? = null
    ): String
}