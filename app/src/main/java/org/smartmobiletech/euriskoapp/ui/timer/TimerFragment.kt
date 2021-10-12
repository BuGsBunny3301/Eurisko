package org.smartmobiletech.euriskoapp.ui.timer

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import org.smartmobiletech.euriskoapp.AppPreferences
import org.smartmobiletech.euriskoapp.R
import org.smartmobiletech.euriskoapp.Utility.TIMER_INTERVAL
import org.smartmobiletech.euriskoapp.Utility.getFormattedStopWatch

class TimerFragment : Fragment(), LifecycleObserver {

    private lateinit var timerViewModel: TimerViewModel
    private lateinit var txtDate: TextView
    private lateinit var txtWatch: TextView


    private val mInterval = TIMER_INTERVAL
    private var mHandler: Handler? = null

    private var timeInSeconds = 0L

    private var root: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if(root == null) {
            root = inflater.inflate(R.layout.fragment_timer, container, false)

            timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
            txtWatch = root!!.findViewById(R.id.txt_stop_watch)

            txtDate = root!!.findViewById(R.id.txt_date)
            txtDate.text = AppPreferences.date

            ProcessLifecycleOwner.get().lifecycle.addObserver(this)

            initWatch()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val builder = AlertDialog.Builder(requireContext())
            with(builder) {
                setTitle("Confirm exit?")
                setPositiveButton("Yes") { _, _ ->
                    stopTimer()
                    requireActivity().finish()
                }
                setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }
        }
        return root
    }


    private fun initWatch() {
        timeInSeconds = AppPreferences.watch
        txtWatch.text = getFormattedStopWatch((timeInSeconds * 1000))
    }

    private fun startTimer() {
        mHandler = Handler(Looper.getMainLooper())
        mStatusChecker.run()
    }

    private fun stopTimer() {
        mHandler?.removeCallbacks(mStatusChecker)
        AppPreferences.watch = timeInSeconds
    }

    private var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                timeInSeconds += 1
                updateStopWatchView(timeInSeconds)
            } finally {
                mHandler!!.postDelayed(this, mInterval.toLong())
            }
        }
    }

    private fun updateStopWatchView(timeInSeconds: Long) {
        val formattedTime = getFormattedStopWatch((timeInSeconds * 1000))
        txtWatch.text = formattedTime
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        stopTimer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForgrounded() {
        startTimer()
    }
}