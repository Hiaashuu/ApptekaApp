package com.hiaashuu.appteka.core

data class ParsedProxyAddress(
    val host: String,
    val port: Int?,
    val type: ProxyType?
)

object ProxyAddressParser {

    private val SCHEME_REGEX = Regex(
        "^(https?|socks[45][ah]?|socks)://(.+)$",
        RegexOption.IGNORE_CASE
    )

    private val IPV6_WITH_PORT_REGEX = Regex(
        "^\\[([a-fA-F0-9:.%a-zA-Z0-9]+)]:(\\d+)$"
    )

    private val HOST_PORT_REGEX = Regex(
        "^([^:]+):(\\d+)$"
    )

    fun parse(input: String): ParsedProxyAddress? {
        val trimmed = input.trim()
        if (trimmed.isEmpty()) return null

        val schemeMatch = SCHEME_REGEX.find(trimmed)
        if (schemeMatch != null) {
            val scheme = schemeMatch.groupValues[1].lowercase()
            val rest = schemeMatch.groupValues[2]

            val type = when {
                scheme == "http" || scheme == "https" -> ProxyType.HTTP
                scheme.startsWith("socks") -> ProxyType.SOCKS
                else -> null
            }

            val hostPort = parseHostPort(rest)
            return if (hostPort != null) {
                ParsedProxyAddress(
                    host = hostPort.first,
                    port = hostPort.second,
                    type = type
                )
            } else {

                ParsedProxyAddress(
                    host = rest.removeSurrounding("[", "]"),
                    port = null,
                    type = type
                )
            }
        }

        val hostPort = parseHostPort(trimmed)
        if (hostPort != null) {
            return ParsedProxyAddress(
                host = hostPort.first,
                port = hostPort.second,
                type = null
            )
        }

        return null
    }

    private fun parseHostPort(input: String): Pair<String, Int>? {

        val ipv6Match = IPV6_WITH_PORT_REGEX.find(input)
        if (ipv6Match != null) {
            val host = ipv6Match.groupValues[1]
            val port = ipv6Match.groupValues[2].toIntOrNull()
            if (port != null && port in 1..65535) {
                return host to port
            }
        }

        if (!input.contains("::") || input.startsWith("[")) {
            val hostPortMatch = HOST_PORT_REGEX.find(input)
            if (hostPortMatch != null) {
                val host = hostPortMatch.groupValues[1]
                val port = hostPortMatch.groupValues[2].toIntOrNull()
                if (port != null && port in 1..65535) {
                    return host to port
                }
            }
        }

        return null
    }

}