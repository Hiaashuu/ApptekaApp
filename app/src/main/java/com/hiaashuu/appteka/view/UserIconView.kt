package com.hiaashuu.appteka.view

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import androidx.core.graphics.drawable.DrawableCompat
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGImageView
import com.google.android.material.imageview.ShapeableImageView
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.dto.BadgeMark
import com.hiaashuu.appteka.dto.UserIcon
import com.tomclaw.imageloader.util.fetch

interface UserIconView {

    fun bind(userIcon: UserIcon)

    fun bindLocalImage(uri: Uri)

    fun clearLocalImage()

    fun bindBadge(badge: BadgeMark?)

}

class UserIconViewImpl(view: View) : UserIconView {

    private val backView: View = view.findViewById(R.id.icon_back)
    private val svgView: SVGImageView = view.findViewById(R.id.icon_svg)
    private val imageView: ShapeableImageView? = view.findViewById(R.id.icon_image)

    private val badgeRing: View? = view.findViewById(R.id.badge_ring)
    private val badgeBack: View? = view.findViewById(R.id.badge_back)
    private val badgeSvg: SVGImageView? = view.findViewById(R.id.badge_svg)

    override fun bind(userIcon: UserIcon) {
        var drawable: Drawable = backView.background
        drawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(drawable, Color.parseColor(userIcon.color))
        backView.background = drawable

        val svg = SVG.getFromString(userIcon.icon)
        svgView.setSVG(svg)

        val image = userIcon.image
        if (image != null) {
            showImageOverlay(image.previewUrl)
        } else {
            hideImageOverlay()
        }

        bindBadge(null)
    }

    override fun bindLocalImage(uri: Uri) {
        showImageOverlay(uri.toString())
    }

    override fun clearLocalImage() {
        hideImageOverlay()
    }

    private fun showImageOverlay(source: String) {
        val target = imageView ?: return
        target.visibility = View.VISIBLE
        target.fetch(source) {
            centerCrop()
        }
    }

    private fun hideImageOverlay() {
        val target = imageView ?: return
        target.setImageDrawable(null)
        target.visibility = View.GONE
    }

    override fun bindBadge(badge: BadgeMark?) {
        val ring = badgeRing ?: return
        val back = badgeBack ?: return
        val svg = badgeSvg ?: return

        if (badge == null) {
            ring.visibility = View.GONE
            return
        }

        ring.visibility = View.VISIBLE

        val tint = parseColorOrNull(badge.color) ?: Color.GRAY
        val bg = DrawableCompat.wrap(back.background)
        DrawableCompat.setTint(bg, tint)
        back.background = bg

        svg.setSVG(SVG.getFromString(badge.icon))
    }

    private fun parseColorOrNull(color: String?): Int? = try {
        color?.let { Color.parseColor(it) }
    } catch (_: IllegalArgumentException) {
        null
    }

}