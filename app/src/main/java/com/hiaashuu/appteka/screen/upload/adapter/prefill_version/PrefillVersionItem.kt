package com.hiaashuu.appteka.screen.upload.adapter.prefill_version

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.screen.upload.adapter.other_versions.VersionItem

class PrefillVersionItem(
    override val id: Long,
    val versions: List<VersionItem>,
    val selectedVersion: VersionItem?
) : Item