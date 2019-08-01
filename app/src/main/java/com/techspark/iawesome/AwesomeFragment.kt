package com.techspark.iawesome

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.techspark.iawesome.database.AwesomeModel
import com.techspark.iawesome.view.AwesomeRecyclerAdapter
import kotlinx.android.synthetic.main.awesome_fragment.*


class AwesomeFragment : Fragment() {

    companion object {
        fun newInstance() = AwesomeFragment()
    }

    private lateinit var viewModel: AwesomeViewModel
    private lateinit var adapter: AwesomeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.awesome_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AwesomeViewModel::class.java)
        viewModel.messages.observe(this, Observer { messages->
            adapter = AwesomeRecyclerAdapter(messages)
            awesome_list.adapter = adapter
        })
    }


}
