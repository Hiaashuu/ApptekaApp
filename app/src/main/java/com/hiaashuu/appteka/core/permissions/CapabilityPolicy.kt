package com.hiaashuu.appteka.core.permissions

sealed class CapabilityResult {
    object Allowed : CapabilityResult()
    data class Denied(val capability: Capability) : CapabilityResult()
    object Unknown : CapabilityResult()
}

object CapabilityPolicy {

    fun check(action: String, capabilities: Map<String, Capability>?): CapabilityResult {
        val capability = capabilities?.get(action) ?: return CapabilityResult.Unknown
        return if (capability.allowed) {
            CapabilityResult.Allowed
        } else {
            CapabilityResult.Denied(capability)
        }
    }

    fun isAllowed(
        action: String,
        capabilities: Map<String, Capability>?,
        allowOnUnknown: Boolean = true,
    ): Boolean = when (val result = check(action, capabilities)) {
        is CapabilityResult.Allowed -> true
        is CapabilityResult.Denied -> false
        CapabilityResult.Unknown -> allowOnUnknown
    }
}