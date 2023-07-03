package com.azuredragon.app.core.common

fun Int.formatToAmountString(currencySymbol: String = CURRENCY_DOLLAR): String {
    return "$currencySymbol$this}"
}

const val CURRENCY_INR = "₹"
const val CURRENCY_DOLLAR = "$"