package org.smartmobiletech.euriskoapp.ui.timer

import android.util.Log

class TimerRunner: Runnable {


    private var timeInSeconds = 0L

        override fun run() {
            try {
                timeInSeconds += 1
                Log.e("timeInSeconds", timeInSeconds.toString())
//                updateStopWatchView(timeInSeconds)
            } finally {
//                mHandler!!.postDelayed(this, mInterval.toLong())
            }
        }

        fun time(): Long {
            return timeInSeconds
        }
}