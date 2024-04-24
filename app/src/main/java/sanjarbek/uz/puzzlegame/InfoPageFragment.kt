package sanjarbek.uz.puzzlegame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class InfoPageFragment : Fragment() {
    private lateinit var backButton:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_info_page, container, false)
        backButton=view.findViewById(R.id.info_page_back_button)
        backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        return view
    }
}