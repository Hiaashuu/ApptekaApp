package com.hiaashuu.appteka.util

import java.security.MessageDigest
import java.util.Locale
import java.util.zip.CRC32

fun String.sha512() = hashString(type = "SHA-512", input = this)

fun String.sha256() = hashString(type = "SHA-256", input = this)

fun String.sha1() = hashString(type = "SHA-1", input = this)

fun String.md5() = hashString(type = "MD5", input = this)

private fun hashString(type: String, input: String): String {
    val bytes = MessageDigest
        .getInstance(type)
        .digest(input.toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}

fun String.crc32(): Int {
    val crc32Calculator = CRC32()
    crc32Calculator.update(this.toByteArray())
    return crc32Calculator.value.toInt()
}

fun String.capitalize(locale: Locale): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
}

const val HEX_CHARS = "0123456789ABCDEF"