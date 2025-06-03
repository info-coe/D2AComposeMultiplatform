package org.infomericainc.infod2a.util.extension

import org.infomericainc.infod2a.data.remote.util.PathType
import org.infomericainc.infod2a.presentation.chat.viewmodel.AccountType

internal fun String.toPathType(): PathType? {
    return when (this) {
        "PowerApps" -> PathType.POWER_APPS
        "SalesForce" -> PathType.SALES_FORCE
        else -> null
    }
}

fun String.actualPathName(): String {
    return when (this) {
        AccountType.POWERAPPS.name -> "PowerApps"
        AccountType.SALESFORCE.name -> "SalesForce"
        else -> this
    }
}

internal fun PathType.getModel(): String {
    return when (this) {
        PathType.POWER_APPS -> "power-app-agent-home-inspection"
        PathType.SALES_FORCE -> "salesforce"
    }
}

internal fun PathType.getToolId(): String {
    return when (this) {
        PathType.POWER_APPS -> "power_app_home_inspections"
        PathType.SALES_FORCE -> "salesforce"
    }
}