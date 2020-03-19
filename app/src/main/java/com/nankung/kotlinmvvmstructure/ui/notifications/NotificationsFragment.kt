package com.nankung.kotlinmvvmstructure.ui.notifications

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nankung.bottomnavex.ui.notifications.NotificationsViewModel
import com.nankung.common.module.base.mvvm.fragment.AppMvvmFragment
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.util.obtainMovieViewModel
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : AppMvvmFragment() {

    private lateinit var viewModel: NotificationsViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel()
        receiveArgument()
    }


    @SuppressLint("SetTextI18n")
    private fun receiveArgument(){
        arguments?.let {arguments ->
            val args = NotificationsFragmentArgs.fromBundle(arguments)
            val message1 = args.message1
            val number1 = args.number1
            val message2 = args.message2
            val number2 = args.number2
            text_notifications?.text = " $number1 \n $message1 \n $number2 \n $message2"
            //Toast.makeText(activity, "$name, $number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtainViewModel(): NotificationsViewModel = obtainMovieViewModel(
        NotificationsViewModel::class.java)
}
