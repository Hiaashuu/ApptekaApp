package com.hiaashuu.appteka.core.permissions

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface UserCapabilitiesProvider {

    fun getCapabilities(): Map<String, Capability>?

    fun setCapabilities(capabilities: Map<String, Capability>?)

    fun clear()

    fun observeCapabilities(): Observable<Map<String, Capability>>
}

class UserCapabilitiesProviderImpl : UserCapabilitiesProvider {

    private var capabilities: Map<String, Capability>? = null
    private val subject = BehaviorSubject.create<Map<String, Capability>>()

    override fun getCapabilities(): Map<String, Capability>? = capabilities

    override fun setCapabilities(capabilities: Map<String, Capability>?) {
        this.capabilities = capabilities
        capabilities?.let { subject.onNext(it) }
    }

    override fun clear() {
        capabilities = null
        subject.onNext(emptyMap())
    }

    override fun observeCapabilities(): Observable<Map<String, Capability>> = subject
}