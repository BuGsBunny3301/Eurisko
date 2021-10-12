package org.smartmobiletech.euriskoapp.ui.detailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.GsonBuilder
import org.smartmobiletech.euriskoapp.R
import org.smartmobiletech.euriskoapp.modules.UserData
import org.smartmobiletech.euriskoapp.ui.listview.ListViewModel


class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel
    lateinit var root: View
    lateinit var rowData: UserData
    lateinit var userId: TextView
    lateinit var txtId: TextView
    lateinit var title: TextView
    lateinit var completed: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_details, container, false)

        activity?.let {
            viewModel = ViewModelProvider(it).get(DetailsViewModel::class.java)
        }

        val row = arguments?.getString("rowdata")
        rowData = GsonBuilder().create().fromJson(row, UserData::class.java)

        userId = root.findViewById(R.id.txt_user_id)
        txtId = root.findViewById(R.id.txt_id)
        title = root.findViewById(R.id.txt_title)
        completed = root.findViewById(R.id.txt_completed)

        userId.text = "User Id:" + rowData.userId
        txtId.text = "Id:" + rowData.id.toString()
        title.text = "Title: " + rowData.title
        completed.text = "Completed: " + rowData.completed.toString()


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
            findNavController().popBackStack()
        }
        return root
    }
}