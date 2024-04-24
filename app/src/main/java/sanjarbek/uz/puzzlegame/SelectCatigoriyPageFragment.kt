package sanjarbek.uz.puzzlegame

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlin.properties.Delegates

class SelectCatigoriyPageFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private lateinit var playerButton: ImageView
    private lateinit var isPlayIcon: ImageView

    private var isPlay by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
        isPlay = sharedPreferencesHelper.getIsPlayer()
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.kuk_juguli)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_select_catigoriy_page, container, false)

        playerButton = view.findViewById(R.id.select_page_player_button)
        isPlayIcon = view.findViewById(R.id.select_page_volume_icon)
        if (isPlay) {
            mediaPlayer.start()
            isPlayIcon.setImageResource(R.drawable.baseline_volume_up_24)
        } else {
            mediaPlayer.pause()
            isPlayIcon.setImageResource(R.drawable.baseline_volume_off_24)
        }

        val hexadecimalButton = view.findViewById<ImageView>(R.id.navigation_hexadecimal_pagi)

        hexadecimalButton.setOnClickListener {
            navigationToHexadecimalPage()
        }
        val ninesButton = view.findViewById<ImageView>(R.id.navigation_nines_pagi)

        ninesButton.setOnClickListener {
            navigationToFoursomePage()
        }
        val twentyFiveButton = view.findViewById<ImageView>(R.id.navigation_twenty_five_page)

        twentyFiveButton.setOnClickListener {
            navigationTwentyFivePage()
        }

        val infoButton = view.findViewById<ImageView>(R.id.navigation_info_page)

        infoButton.setOnClickListener {
            navigationInfoPage()
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerButton.setOnClickListener {
            isPlay = !isPlay
            if (isPlay) {
                mediaPlayer.start()
                isPlayIcon.setImageResource(R.drawable.baseline_volume_up_24)
            } else {
                mediaPlayer.pause()
                isPlayIcon.setImageResource(R.drawable.baseline_volume_off_24)
            }
            sharedPreferencesHelper.saveIsPlater(isPlay)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun navigationToFoursomePage() {
        val foursomePageFragment = NinesPageFragment()
        val fragmentManager = parentFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.container,
            foursomePageFragment,
            NinesPageFragment::class.java.name
        )
        transaction.addToBackStack(NinesPageFragment::class.java.name)
        mediaPlayer.pause()
        transaction.commit()
    }

    private fun navigationToHexadecimalPage() {
        val hexadecimalPageFragment = HexadecimalPageFragment()
        val fragmentManager = parentFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.container,
            hexadecimalPageFragment,
            HexadecimalPageFragment::class.java.name
        )
        transaction.addToBackStack(HexadecimalPageFragment::class.java.name)
        mediaPlayer.pause()
        transaction.commit()
    }

    private fun navigationTwentyFivePage() {
        val twentyFivePageFragment = TwentyFivePageFragment()
        val fragmentManager = parentFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.container,
            twentyFivePageFragment,
            TwentyFivePageFragment::class.java.name
        )
        transaction.addToBackStack(TwentyFivePageFragment::class.java.name)
        mediaPlayer.pause()
        transaction.commit()
    }

    private fun navigationInfoPage() {
        val infoPageFragment = InfoPageFragment()
        val fragmentManager = parentFragmentManager
        val transaction=fragmentManager.beginTransaction()
        transaction.replace(
            R.id.container,
            infoPageFragment,
            InfoPageFragment::class.java.name
        )
        transaction.addToBackStack(InfoPageFragment::class.java.name)
        transaction.commit()
    }


}