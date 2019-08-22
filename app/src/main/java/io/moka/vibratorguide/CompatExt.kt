package io.moka.vibratorguide

import android.annotation.SuppressLint
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

@SuppressLint("MissingPermission", "NewApi")
fun Vibrator.supVibrate(pattern: LongArray, amp: IntArray, repeat: Int) {
    if (pattern.isEmpty())
        pattern.plus(longArrayOf(0, 1000, 200, 1000, 2400))

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val vibrateEffect = VibrationEffect.createWaveform(pattern, amp, repeat)
        vibrate(vibrateEffect)
    }
    else {
        vibrate(pattern, -1)
    }
}
