package com.hiaashuu.appteka.screen.details.adapter.play

import android.content.res.Resources
import com.hiaashuu.appteka.R

interface PlayResourceProvider {

    fun securityScanningShort(): String

    fun securitySafeShort(): String

    fun securitySuspiciousShort(): String

    fun securityMalwareShort(): String

    fun securityNotCheckedShort(): String

}

class PlayResourceProviderImpl(val resources: Resources) : PlayResourceProvider {

    override fun securityScanningShort(): String {
        return resources.getString(R.string.security_scanning_short)
    }

    override fun securitySafeShort(): String {
        return resources.getString(R.string.security_safe_short)
    }

    override fun securitySuspiciousShort(): String {
        return resources.getString(R.string.security_suspicious_short)
    }

    override fun securityMalwareShort(): String {
        return resources.getString(R.string.security_malware_short)
    }

    override fun securityNotCheckedShort(): String {
        return resources.getString(R.string.security_not_checked_short)
    }

}