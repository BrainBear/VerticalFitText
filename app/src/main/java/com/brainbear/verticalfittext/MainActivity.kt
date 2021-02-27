package com.brainbear.verticalfittext

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.brainbear.verticalfittext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityMainBinding

    private val font by lazy {
        Typeface.createFromAsset(application.assets, "font/PFDinTextUniversal-Medium.otf")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.tvText.typeface = font

        viewBinding.textInput.setText("0123456789")
        viewBinding.sizeInput.setText("50")

        viewBinding.btnSet.setOnClickListener {
            viewBinding.tvText.text = viewBinding.textInput.text.toString()
            viewBinding.tvText.textSize =
                viewBinding.sizeInput.text.toString().toFloatOrNull() ?: 50f
        }

        viewBinding.btnSet.performClick()


        viewBinding.cbBaseline.isChecked = viewBinding.tvText.drawBaseline
        viewBinding.cbTop.isChecked = viewBinding.tvText.drawTopLine
        viewBinding.cbBottom.isChecked = viewBinding.tvText.drawBottomLine
        viewBinding.cbAscent.isChecked = viewBinding.tvText.drawAscentLine
        viewBinding.cbDescent.isChecked = viewBinding.tvText.drawDescentLine
        viewBinding.cbBounds.isChecked = viewBinding.tvText.drawTextBounds
        viewBinding.cbFontPadding.isChecked = viewBinding.tvText.includeFontPadding
        viewBinding.cbFit.isChecked = viewBinding.tvText.isFit

        viewBinding.cbBaseline.setOnCheckedChangeListener { _, isChecked ->
            viewBinding.tvText.drawBaseline = isChecked
        }

        viewBinding.cbTop.setOnCheckedChangeListener { _, isChecked ->
            viewBinding.tvText.drawTopLine = isChecked
        }

        viewBinding.cbBottom.setOnCheckedChangeListener { _, isChecked ->
            viewBinding.tvText.drawBottomLine = isChecked
        }

        viewBinding.cbAscent.setOnCheckedChangeListener { _, isChecked ->
            viewBinding.tvText.drawAscentLine = isChecked
        }

        viewBinding.cbDescent.setOnCheckedChangeListener { _, isChecked ->
            viewBinding.tvText.drawDescentLine = isChecked
        }
        viewBinding.cbBounds.setOnCheckedChangeListener { _, isChecked ->
            viewBinding.tvText.drawTextBounds = isChecked
        }

        viewBinding.cbFontPadding.setOnCheckedChangeListener { _, isChecked ->
            viewBinding.tvText.includeFontPadding = isChecked
        }

        viewBinding.cbFit.setOnCheckedChangeListener { _, isChecked ->
            viewBinding.tvText.isFit = isChecked
        }

    }


}