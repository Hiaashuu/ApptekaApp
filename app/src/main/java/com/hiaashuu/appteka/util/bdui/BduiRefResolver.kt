package com.hiaashuu.appteka.util.bdui

import com.hiaashuu.appteka.util.bdui.model.BduiRef

class BduiRefResolver(
    private val valueProvider: BduiValueProvider
) {

    fun resolve(value: Any?): Any? {
        return when (value) {
            null -> null
            is BduiRef -> resolveRef(value)
            is Map<*, *> -> resolveMap(value)
            is List<*> -> resolveList(value)
            else -> value
        }
    }

    private fun resolveRef(ref: BduiRef): Any? {
        return valueProvider.getPropertyValue(ref.id, ref.property)
    }

    @Suppress("UNCHECKED_CAST")
    private fun resolveMap(map: Map<*, *>): Any? {

        val type = map["type"]
        if (type == "ref") {
            val id = map["id"] as? String
            val property = map["property"] as? String
            if (id != null && property != null) {
                return resolveRef(BduiRef(id = id, property = property))
            }
        }

        return map.mapValues { (_, v) -> resolve(v) }
    }

    private fun resolveList(list: List<*>): List<*> {
        return list.map { resolve(it) }
    }
}

interface BduiValueProvider {

    fun getPropertyValue(id: String, property: String): Any?
}