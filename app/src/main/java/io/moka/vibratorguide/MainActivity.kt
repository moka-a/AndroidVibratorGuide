package io.moka.vibratorguide

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.SeekBar
import io.moka.lib.base.util.attr
import io.moka.lib.base.util.color
import io.moka.lib.base.util.spannableText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var length: Long = 1L
        set(value) {
            field = value
            if (value == 0L)
                field = 1L

            textView_length.text = spannableText(
                attr("진동 길이  "),
                attr("${field} ms", isBold = true, ratio = 1.3f))
        }

    var amplitude: Int = 1
        set(value) {
            field = value
            if (value == 0)
                field = 1

            textView_amplitude.text = spannableText(
                attr("진동 세기  "),
                attr("${field}", isBold = true, ratio = 1.3f))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
        initView()
    }

    private fun bindView() {
        imageView_play.setOnClickListener {
            play(longArrayOf(0, length), intArrayOf(0, amplitude))
        }

        seekBar_length.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(view: SeekBar?, value: Int, fromUser: Boolean) {
                length = value.toLong()
            }

            override fun onStartTrackingTouch(view: SeekBar?) {
            }

            override fun onStopTrackingTouch(view: SeekBar?) {
            }
        })

        seekBar_amplitude.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(view: SeekBar?, value: Int, fromUser: Boolean) {
                amplitude = value
            }

            override fun onStartTrackingTouch(view: SeekBar?) {
            }

            override fun onStopTrackingTouch(view: SeekBar?) {
            }
        })
    }

    private fun initView() {
        seekBar_length.max = 5000
        seekBar_amplitude.max = 255

        length = 1000L
        amplitude = 100

        seekBar_length.progress = length.toInt()
        seekBar_amplitude.progress = amplitude
    }

    private fun play(pattern: LongArray, amp: IntArray) {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.supVibrate(pattern, amp, -1)
    }

}
