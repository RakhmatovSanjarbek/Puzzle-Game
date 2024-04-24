package sanjarbek.uz.puzzlegame

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@Suppress("DEPRECATION")
class TwentyFivePageFragment : Fragment() {

    private val puzzleData = listOf(
        PuzzleModel(1),
        PuzzleModel(2),
        PuzzleModel(3),
        PuzzleModel(4),
        PuzzleModel(5),
        PuzzleModel(6),
        PuzzleModel(7),
        PuzzleModel(8),
        PuzzleModel(9),
        PuzzleModel(10),
        PuzzleModel(11),
        PuzzleModel(12),
        PuzzleModel(13),
        PuzzleModel(14),
        PuzzleModel(15),
        PuzzleModel(16),
        PuzzleModel(17),
        PuzzleModel(18),
        PuzzleModel(19),
        PuzzleModel(20),
        PuzzleModel(21),
        PuzzleModel(22),
        PuzzleModel(23),
        PuzzleModel(24),
        PuzzleModel(),
    )

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var itemVoice: MediaPlayer

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private var isPlay by Delegates.notNull<Boolean>()

    private lateinit var playerButton: ImageView

    private var counter: Int = 0

    private lateinit var timerPousButton: ImageView

    private var isTimerPous = true

    private lateinit var timerTextView: TextView
    private lateinit var cilcCounter: TextView
    private var startTime: Long = 0L
    private val customHandler = Handler()
    private var timeInMilliseconds: Long = 0L
    private var timeSwapBuff: Long = 0L
    private var updatedTime: Long = 0L

    private val puzzleAdapter by lazy {
        TwentyFivePageAdapter { puzzleModel: PuzzleModel, emptyPuzzleModel: PuzzleModel ->
            onItemClick(puzzleModel, emptyPuzzleModel)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun onItemClick(puzzleModel: PuzzleModel, emptyPuzzleModel: PuzzleModel) {
        puzzleAdapter.changeItem(puzzleModel, emptyPuzzleModel)
        if (puzzleAdapter.dataList.map { it.cardNumber } == listOf(
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                11,
                12,
                13,
                14,
                15,
                16,
                17,
                18,
                19,
                20,
                21,
                22,
                23,
                24,
                -1
            )
        ) {
            showAlertDialog()
        }
        itemVoice.start()
        GlobalScope.launch {
            delay(500)  // Pause for 100 milliseconds (0.1 seconds)
            itemVoice.pause()
        }
        counter++
        cilcCounter.text = counter.toString()
        if (!isTimerPous) {
            isTimerPous = true
            startTimer()
            timerPousButton.setImageResource(R.drawable.baseline_pause_24)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
        isPlay = sharedPreferencesHelper.getIsPlayer()
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.kuk_juguli)
        mediaPlayer.isLooping = true
        itemVoice = MediaPlayer.create(requireContext(), R.raw.item_voice)
        itemVoice.isLooping = true
        mediaPlayer.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_twenty_five_page, container, false)
        playerButton = view.findViewById(R.id.twenty_five_page_player_button)
        if (isPlay) {
            mediaPlayer.start()
            playerButton.setImageResource(R.drawable.baseline_volume_up_24)
        } else {
            mediaPlayer.pause()
            playerButton.setImageResource(R.drawable.baseline_volume_off_24)
        }

        startTime = SystemClock.uptimeMillis()
        customHandler.postDelayed(updateTimerThread, 0)

        cilcCounter = view.findViewById(R.id.twenty_five_page_cilc_counter)
        timerTextView = view.findViewById(R.id.twenty_five_page_tv_timer)
        val refreshButton = view.findViewById<ImageView>(R.id.twenty_five_page_refresh_button)
        refreshButton.setOnClickListener {
            puzzleAdapter.loadData(puzzleData.shuffled())
            startTime = SystemClock.uptimeMillis()
            customHandler.postDelayed(updateTimerThread, 0)
            counter = 0
            cilcCounter.text = counter.toString()
        }

        timerPousButton = view.findViewById(R.id.twenty_five_page_timet_pouse_button)
        timerPousButton.setOnClickListener {
            isTimerPous = !isTimerPous
            if (isTimerPous) {
                startTimer()
                timerPousButton.setImageResource(R.drawable.baseline_pause_24)
            } else {
                stopTimer()
                timerPousButton.setImageResource(R.drawable.baseline_play_arrow_24)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = view.findViewById<RecyclerView>(R.id.twenty_five_page_rv)
        rv.adapter = puzzleAdapter
        rv.setHasFixedSize(false)
        puzzleAdapter.loadData(puzzleData.shuffled())

        playerButton.setOnClickListener {
            isPlay = !isPlay
            if (isPlay) {
                mediaPlayer.start()
                playerButton.setImageResource(R.drawable.baseline_volume_up_24)
            } else {
                mediaPlayer.pause()
                playerButton.setImageResource(R.drawable.baseline_volume_off_24)
            }
            sharedPreferencesHelper.saveIsPlater(isPlay)
        }

        val backButton = view.findViewById<ImageView>(R.id.twenty_five_page_bask_button)
        backButton.setOnClickListener {
            showBackAlertDialog()
        }
    }

    private val updateTimerThread = object : Runnable {
        override fun run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime
            updatedTime = timeSwapBuff + timeInMilliseconds

            var seconds = (updatedTime / 1000).toInt()
            var minutes = seconds / 60
            seconds %= 60
            val hours = minutes / 60
            minutes %= 60

            timerTextView.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            customHandler.postDelayed(this, 0)
        }
    }

    private fun startTimer() {
        startTime = SystemClock.uptimeMillis()
        customHandler.postDelayed(updateTimerThread, 0)
    }

    private fun stopTimer() {
        timeSwapBuff += timeInMilliseconds
        customHandler.removeCallbacks(updateTimerThread)
    }

    @SuppressLint("SetTextI18n")
    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog_layout, null)
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialogMessage)
        val restartButton = dialogView.findViewById<RelativeLayout>(R.id.restartButton)
        val backToMainButton = dialogView.findViewById<RelativeLayout>(R.id.backToMainButton)

        dialogTitle.text = "Ofarin!"
        dialogMessage.text = "Sariflangan vaqt: ${timerTextView.text} \n Urunishlar soni: $counter"

        builder.setView(dialogView)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        restartButton.setOnClickListener {
            puzzleAdapter.loadData(puzzleData.shuffled())
            startTime = SystemClock.uptimeMillis()
            customHandler.postDelayed(updateTimerThread, 0)
            counter = 0
            cilcCounter.text = counter.toString()
            dialog.dismiss()
        }

        backToMainButton.setOnClickListener {
            dialog.dismiss()
            activity?.supportFragmentManager?.popBackStack()
            mediaPlayer.release()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showBackAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog_layout, null)
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialogMessage)
        val restartButton = dialogView.findViewById<RelativeLayout>(R.id.restartButton)
        val backToMainButton = dialogView.findViewById<RelativeLayout>(R.id.backToMainButton)

        dialogTitle.text = "Dasturdan chiqish"
        dialogMessage.text = "Sariflangan vaqt: ${timerTextView.text} \n Urunishlar soni: $counter"

        builder.setView(dialogView)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        restartButton.setOnClickListener {
            puzzleAdapter.loadData(puzzleData.shuffled())
            startTime = SystemClock.uptimeMillis()
            customHandler.postDelayed(updateTimerThread, 0)
            counter = 0
            cilcCounter.text = counter.toString()
            dialog.dismiss()
        }

        backToMainButton.setOnClickListener {
            dialog.dismiss()
            activity?.supportFragmentManager?.popBackStack()
            mediaPlayer.release()
        }
    }
}