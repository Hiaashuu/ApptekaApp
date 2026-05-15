package com.hiaashuu.appteka.util.bdui

import android.view.View
import android.view.ViewGroup
import com.hiaashuu.appteka.util.bdui.factory.BduiComponentFactory
import com.hiaashuu.appteka.util.bdui.factory.BduiContainerFactory
import com.hiaashuu.appteka.util.bdui.factory.BduiNodeRenderer
import com.hiaashuu.appteka.util.bdui.model.BduiNode
import com.hiaashuu.appteka.util.bdui.model.component.BduiComponent
import com.hiaashuu.appteka.util.bdui.model.component.BduiHiddenComponent
import com.hiaashuu.appteka.util.bdui.model.container.BduiContainer

class BduiRenderer(
    private val componentFactory: BduiComponentFactory,
    private val containerFactory: BduiContainerFactory,
    private val hiddenStorage: BduiHiddenStorage
) : BduiNodeRenderer {

    override fun render(node: BduiNode, parent: ViewGroup): View? {
        return when (node) {
            is BduiHiddenComponent -> {

                hiddenStorage.registerHidden(node.id, node.value, node.action)
                null
            }
            is BduiContainer -> {
                val view = containerFactory.create(node, parent)
                parent.addView(view)
                view
            }
            is BduiComponent -> {
                val view = componentFactory.create(node, parent)
                view?.let { parent.addView(it) }
                view
            }
            else -> null
        }
    }

    fun clear() {
        hiddenStorage.clear()
    }
}