package com.hiaashuu.appteka.screen.details.adapter.description

import android.content.Context
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.google.android.material.R.style.Widget_Material3_CircularProgressIndicator_ExtraSmall
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec
import com.google.android.material.progressindicator.IndeterminateDrawable
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.bind

interface DescriptionItemView : ItemView {

    fun setText(value: String)

    fun setPackageName(value: String)

    fun setVersionsCount(count: Int)

    fun setUploadDate(value: String)

    fun setChecksum(value: String)

    fun setSourceUrl(value: String?)

    fun disableTranslateButton()

    fun enableTranslateButton()

    fun showTranslateButton()

    fun showOriginalButton()

    fun setOnTranslateClickListener(listener: (() -> Unit)?)

    fun setOnGooglePlayClickListener(listener: (() -> Unit)?)

    fun setOnVersionsClickListener(listener: (() -> Unit)?)

}

class DescriptionItemViewHolder(view: View) : BaseItemViewHolder(view), DescriptionItemView {

    private val context = view.context
    private val descriptionTitle: View = view.findViewById(R.id.description_title)
    private val description: TextView = view.findViewById(R.id.description)
    private val readMoreText: TextView = view.findViewById(R.id.description_read_more)
    private val translateButton: MaterialButton = view.findViewById(R.id.translate_button)
    private val versionsButton: MaterialButton = view.findViewById(R.id.versions_button)
    private val googlePlayButton: View = view.findViewById(R.id.google_play_button)
    private val uploadDate: TextView = view.findViewById(R.id.upload_date)
    private val checksum: TextView = view.findViewById(R.id.app_checksum)
    private val packageContainer: View = view.findViewById(R.id.package_container)
    private val checksumContainer: View = view.findViewById(R.id.checksum_container)
    private val sourceUrlContainer: View = view.findViewById(R.id.app_source_url_container)
    private val appPackage: TextView = view.findViewById(R.id.app_package)

    private var currentText: String? = null
    private var isExpanded = false
    private var currentChecksum: String? = null
    private var currentUrl: String? = null
    private var currentPackage: String? = null

    private var translateClickListener: (() -> Unit)? = null
    private var googlePlayClickListener: (() -> Unit)? = null
    private var versionsClickListener: (() -> Unit)? = null

    private val progressIndicatorDrawable = IndeterminateDrawable
        .createCircularDrawable(
            context,
            CircularProgressIndicatorSpec(
                context, null, 0,
                Widget_Material3_CircularProgressIndicator_ExtraSmall
            )
        )

    init {
        translateButton.setOnClickListener { translateClickListener?.invoke() }
        googlePlayButton.setOnClickListener { googlePlayClickListener?.invoke() }
        versionsButton.setOnClickListener { versionsClickListener?.invoke() }

        uploadDate.isSelected = true
        checksum.isSelected = true
        appPackage.isSelected = true

        readMoreText.setOnClickListener {
            isExpanded = !isExpanded
            val textToSet = currentText
            currentText = null
            setText(textToSet ?: "")
        }

        readMoreText.setOnLongClickListener {
            currentText?.let { text ->
                copyToClipboard("Description", text)
            }
            true
        }

        packageContainer.setOnLongClickListener {
            currentPackage?.let { text ->
                copyToClipboard("Package Name", text)
            }
            true
        }

        checksumContainer.setOnLongClickListener {
            currentChecksum?.let { text ->
                copyToClipboard("Checksum", text)
            }
            true
        }

        sourceUrlContainer.setOnLongClickListener {
            currentUrl?.let { url ->
                copyToClipboard("Source URL", url)
            }
            true
        }

        sourceUrlContainer.setOnClickListener {
            currentUrl?.let { url ->
                try {
                    val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url))
                    context.startActivity(intent)
                } catch (e: Exception) {}
            }
        }
    }

    private fun copyToClipboard(label: String, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = android.content.ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "$label copied", Toast.LENGTH_SHORT).show()
    }

    override fun setText(value: String) {
        if (currentText == value) return
        currentText = value

        description.text = value
        descriptionTitle.visibility = if (value.isEmpty()) View.GONE else View.VISIBLE

        if (isExpanded) {
            description.maxLines = Integer.MAX_VALUE
            readMoreText.visibility = View.VISIBLE
            readMoreText.text = "Show less"
        } else {
            description.maxLines = 10
            readMoreText.visibility = View.GONE
            description.post {
                if (description.layout != null && description.layout.lineCount > 10) {
                    readMoreText.visibility = View.VISIBLE
                    readMoreText.text = "See full description"
                }
            }
        }
    }

    override fun setPackageName(value: String) {
        currentPackage = value
        appPackage.text = value
        packageContainer.visibility = if (value.isEmpty()) View.GONE else View.VISIBLE
        appPackage.isSelected = true
    }

    override fun setVersionsCount(count: Int) {
        versionsButton.isVisible = count > 1
        versionsButton.text = context.resources.getQuantityString(
            R.plurals.other_versions_count,
            count,
            count
        )
    }

    override fun setUploadDate(value: String) {
        uploadDate.text = value
        uploadDate.visibility = if (value.isEmpty()) View.GONE else View.VISIBLE
        uploadDate.isSelected = true
    }

    override fun setChecksum(value: String) {
        currentChecksum = value
        checksum.text = value
        checksumContainer.visibility = if (value.isEmpty()) View.GONE else View.VISIBLE
        checksum.isSelected = true
    }

    override fun setSourceUrl(value: String?) {
        currentUrl = value
        sourceUrlContainer.visibility = if (value.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    override fun disableTranslateButton() {
        translateButton.icon = progressIndicatorDrawable

        translateButton.setText(R.string.wait)
        translateButton.isClickable = false
    }

    override fun enableTranslateButton() {
        translateButton.isClickable = true
    }

    override fun showTranslateButton() {
        translateButton.setIconResource(R.drawable.ic_translate)
        translateButton.setText(R.string.translate)
    }

    override fun showOriginalButton() {
        translateButton.setIconResource(R.drawable.ic_translate_off)
        translateButton.setText(R.string.original)
    }

    override fun setOnTranslateClickListener(listener: (() -> Unit)?) {
        this.translateClickListener = listener
    }

    override fun setOnGooglePlayClickListener(listener: (() -> Unit)?) {
        this.googlePlayClickListener = listener
    }

    override fun setOnVersionsClickListener(listener: (() -> Unit)?) {
        this.versionsClickListener = listener
    }

    override fun onUnbind() {
        this.translateClickListener = null
        this.googlePlayClickListener = null
        this.versionsClickListener = null
    }

}