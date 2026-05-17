package com.hiaashuu.appteka.core

import java.io.IOException
import java.net.Proxy
import java.net.ProxySelector
import java.net.SocketAddress
import java.net.URI

class DynamicProxySelector(
    private val proxyConfigProvider: ProxyConfigProvider
) : ProxySelector() {

    override fun select(uri: URI?): List<Proxy> {
        val config = proxyConfigProvider.getProxyConfig()
        val proxy = config.toProxy()

        return if (config.enabled && proxy != null) {
            listOf(proxy)
        } else {
            ProxySelector.getDefault()?.select(uri) ?: listOf(Proxy.NO_PROXY)
        }
    }

    override fun connectFailed(uri: URI?, sa: SocketAddress?, ioe: IOException?) {
        val config = proxyConfigProvider.getProxyConfig()
        if (!config.enabled) {
            ProxySelector.getDefault()?.connectFailed(uri, sa, ioe)
        }
    }

}