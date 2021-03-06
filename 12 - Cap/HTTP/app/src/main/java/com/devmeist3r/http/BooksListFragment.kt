package com.devmeist3r.http

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_books_list.*

class BooksListFragment: Fragment() {

    private var asyncTask: BooksDownloadTask? = null
    private var booksList = mutableListOf<Book>()
    private var adapter: ArrayAdapter<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_books_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, booksList)
        listView.emptyView = txtMessage
        listView.adapter = adapter
        if (booksList.isNotEmpty()) {
            showProgress(false)
        } else {
            if( asyncTask == null) {
                if (BookHttp.hasConnection((requireContext()))) {
                    startDownloadJson()
                } else {
                    progressBar.visibility = View.GONE
                    txtMessage.setText(R.string.error_no_connection)
                }
            } else if (asyncTask?. status == AsyncTask.Status.RUNNING) {
                showProgress(true)
            }
        }
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            txtMessage.setText(R.string.message_progress)
        }
        txtMessage.visibility = if(show) View.VISIBLE else View.GONE
        progressBar.visibility = if(show) View.VISIBLE else View.GONE
    }

}