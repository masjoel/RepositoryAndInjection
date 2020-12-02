package com.masjoel.academy.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.masjoel.academy.R
import com.masjoel.academy.data.ContentEntity
import com.masjoel.academy.data.ModuleEntity
import com.masjoel.academy.ui.reader.CourseReaderViewModel
import com.masjoel.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*

class ModuleContentFragment : Fragment() {

    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            //val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
            /*val module = viewModel.getSelectedModule()
            populateWebView(module)*/
            progress_bar.visibility = View.VISIBLE
            viewModel.getSelectedModule().observe(viewLifecycleOwner, Observer{ module ->
                if (module != null) {
                    progress_bar.visibility = View.GONE
                    populateWebView(module)
                }
            })

        }
    }

    private fun populateWebView(module: ModuleEntity) {
        web_view.loadData(module.contentEntity?.content.toString(), "text/html", "UTF-8")
    }

}