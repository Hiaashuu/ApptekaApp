package com.hiaashuu.appteka.util.bdui

import com.hiaashuu.appteka.util.SchedulersFactory
import com.hiaashuu.appteka.util.bdui.model.action.BduiAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiCallbackAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiCopyAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiDelayAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiFocusAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiLoadAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiOpenUrlAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiReloadAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiRouteAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiRpcAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiRpcResponse
import com.hiaashuu.appteka.util.bdui.model.action.BduiScrollToAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiSequenceAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiShareAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiSnackbarAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiStoreAction
import com.hiaashuu.appteka.util.bdui.model.action.BduiTransformAction
import com.hiaashuu.appteka.util.bdui.model.transform.BduiBatchTransform
import com.hiaashuu.appteka.util.bdui.model.transform.BduiPropertyTransform
import com.hiaashuu.appteka.util.bdui.model.transform.BduiTransform
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class BduiActionHandler(
    private val transformHandler: BduiTransformHandler,
    private val refResolver: BduiRefResolver,
    private val preferencesStorage: BduiPreferencesStorage,
    private val listener: BduiActionListener,
    private val schedulers: SchedulersFactory
) {

    fun execute(action: BduiAction): Completable {
        val resolvedAction = resolveRefsInAction(action)
        return executeResolved(resolvedAction)
    }

    private fun executeResolved(action: BduiAction): Completable {
        return when (action) {
            is BduiRpcAction -> executeRpc(action)
            is BduiCallbackAction -> executeCallback(action)
            is BduiTransformAction -> executeTransform(action)
            is BduiSequenceAction -> executeSequence(action)
            is BduiRouteAction -> executeRoute(action)
            is BduiOpenUrlAction -> executeOpenUrl(action)
            is BduiSnackbarAction -> executeSnackbar(action)
            is BduiCopyAction -> executeCopy(action)
            is BduiShareAction -> executeShare(action)
            is BduiDelayAction -> executeDelay(action)
            is BduiStoreAction -> executeStore(action)
            is BduiLoadAction -> executeLoad(action)
            is BduiReloadAction -> executeReload()
            is BduiFocusAction -> executeFocus(action)
            is BduiScrollToAction -> executeScrollTo(action)
        }
    }

    private fun executeRpc(action: BduiRpcAction): Completable {
        return listener.onRpcRequest(action)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .flatMapCompletable { response ->
                execute(response.action)
            }
    }

    private fun executeCallback(action: BduiCallbackAction): Completable {
        return Completable.fromAction {
            listener.onCallback(action.name, action.data)
        }.subscribeOn(schedulers.mainThread())
    }

    private fun executeTransform(action: BduiTransformAction): Completable {
        return Completable.fromAction {
            transformHandler.apply(action.transform)
        }.subscribeOn(schedulers.mainThread())
    }

    private fun executeSequence(action: BduiSequenceAction): Completable {
        return Observable.fromIterable(action.actions)
            .concatMapCompletable { execute(it) }
    }

    private fun executeRoute(action: BduiRouteAction): Completable {
        return Completable.fromAction {
            listener.onRoute(action.screen, action.params)
        }.subscribeOn(schedulers.mainThread())
    }

    private fun executeOpenUrl(action: BduiOpenUrlAction): Completable {
        return Completable.fromAction {
            listener.onOpenUrl(action.url, action.external)
        }.subscribeOn(schedulers.mainThread())
    }

    private fun executeSnackbar(action: BduiSnackbarAction): Completable {
        return Completable.fromAction {
            listener.onSnackbar(action.message, action.duration, action.actionText) {
                action.action?.let { execute(it).subscribe({}, {}) }
            }
        }.subscribeOn(schedulers.mainThread())
    }

    private fun executeCopy(action: BduiCopyAction): Completable {
        return Completable.fromAction {
            listener.onCopy(action.text, action.label)
        }.subscribeOn(schedulers.mainThread())
    }

    private fun executeShare(action: BduiShareAction): Completable {
        return Completable.fromAction {
            listener.onShare(action.text, action.title)
        }.subscribeOn(schedulers.mainThread())
    }

    private fun executeDelay(action: BduiDelayAction): Completable {
        return Completable.timer(action.duration, TimeUnit.MILLISECONDS, schedulers.mainThread())
    }

    private fun executeStore(action: BduiStoreAction): Completable {
        return Completable.fromAction {
            preferencesStorage.store(action.key, action.value)
        }.subscribeOn(schedulers.io())
    }

    private fun executeLoad(action: BduiLoadAction): Completable {
        return Completable.fromAction {
            val value = preferencesStorage.load(action.key, action.defaultValue)
            transformHandler.apply(
                BduiPropertyTransform(
                    id = action.targetId,
                    property = "value",
                    value = value ?: action.defaultValue ?: ""
                )
            )
        }.subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
    }

    private fun executeReload(): Completable {
        return Completable.fromAction {
            listener.onReload()
        }.subscribeOn(schedulers.mainThread())
    }

    private fun executeFocus(action: BduiFocusAction): Completable {
        return Completable.fromAction {
            listener.onFocus(action.id, action.showKeyboard)
        }.subscribeOn(schedulers.mainThread())
    }

    private fun executeScrollTo(action: BduiScrollToAction): Completable {
        return Completable.fromAction {
            listener.onScrollTo(action.id, action.smooth)
        }.subscribeOn(schedulers.mainThread())
    }

    private fun resolveRefsInAction(action: BduiAction): BduiAction {
        return when (action) {
            is BduiRpcAction -> resolveRefsInRpcAction(action)
            is BduiCallbackAction -> resolveRefsInCallbackAction(action)
            is BduiTransformAction -> resolveRefsInTransformAction(action)
            is BduiSequenceAction -> resolveRefsInSequenceAction(action)
            is BduiRouteAction -> resolveRefsInRouteAction(action)
            is BduiOpenUrlAction -> resolveRefsInOpenUrlAction(action)
            is BduiSnackbarAction -> resolveRefsInSnackbarAction(action)
            is BduiCopyAction -> resolveRefsInCopyAction(action)
            is BduiShareAction -> resolveRefsInShareAction(action)
            is BduiDelayAction -> action
            is BduiStoreAction -> resolveRefsInStoreAction(action)
            is BduiLoadAction -> action
            is BduiReloadAction -> action
            is BduiFocusAction -> action
            is BduiScrollToAction -> action
        }
    }

    private fun resolveRefsInRpcAction(action: BduiRpcAction): BduiRpcAction {
        return action.copy(
            payload = refResolver.resolve(action.payload)
        )
    }

    private fun resolveRefsInCallbackAction(action: BduiCallbackAction): BduiCallbackAction {
        return action.copy(
            data = refResolver.resolve(action.data)
        )
    }

    private fun resolveRefsInTransformAction(action: BduiTransformAction): BduiTransformAction {
        return action.copy(
            transform = resolveRefsInTransform(action.transform)
        )
    }

    private fun resolveRefsInSequenceAction(action: BduiSequenceAction): BduiSequenceAction {
        return action.copy(
            actions = action.actions.map { resolveRefsInAction(it) }
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun resolveRefsInRouteAction(action: BduiRouteAction): BduiRouteAction {
        return action.copy(
            params = refResolver.resolve(action.params) as? Map<String, Any>
        )
    }

    private fun resolveRefsInOpenUrlAction(action: BduiOpenUrlAction): BduiOpenUrlAction {
        return action.copy(
            url = refResolver.resolve(action.url)?.toString() ?: action.url
        )
    }

    private fun resolveRefsInSnackbarAction(action: BduiSnackbarAction): BduiSnackbarAction {
        return action.copy(
            message = refResolver.resolve(action.message)?.toString() ?: action.message,
            actionText = action.actionText?.let { refResolver.resolve(it)?.toString() },
            action = action.action?.let { resolveRefsInAction(it) }
        )
    }

    private fun resolveRefsInCopyAction(action: BduiCopyAction): BduiCopyAction {
        return action.copy(
            text = refResolver.resolve(action.text)?.toString() ?: action.text,
            label = refResolver.resolve(action.label)?.toString() ?: action.label
        )
    }

    private fun resolveRefsInShareAction(action: BduiShareAction): BduiShareAction {
        return action.copy(
            text = refResolver.resolve(action.text)?.toString() ?: action.text,
            title = action.title?.let { refResolver.resolve(it)?.toString() }
        )
    }

    private fun resolveRefsInStoreAction(action: BduiStoreAction): BduiStoreAction {
        return action.copy(
            value = refResolver.resolve(action.value)
        )
    }

    private fun resolveRefsInTransform(transform: BduiTransform): BduiTransform {
        return when (transform) {
            is BduiPropertyTransform -> transform.copy(
                value = refResolver.resolve(transform.value) ?: transform.value
            )
            is BduiBatchTransform -> transform.copy(
                transforms = transform.transforms.map { resolveRefsInTransform(it) }
            )
        }
    }
}

interface BduiActionListener {

    fun onCallback(name: String, data: Any?)

    fun onRpcRequest(action: BduiRpcAction): Single<BduiRpcResponse>

    fun onRoute(screen: String, params: Map<String, Any>?)

    fun onOpenUrl(url: String, external: Boolean)

    fun onSnackbar(message: String, duration: String, actionText: String?, onAction: () -> Unit)

    fun onCopy(text: String, label: String)

    fun onShare(text: String, title: String?)

    fun onReload()

    fun onFocus(id: String, showKeyboard: Boolean)

    fun onScrollTo(id: String, smooth: Boolean)
}