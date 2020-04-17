package com.destr.financehelper.presentation.screen.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.destr.financehelper.R
import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import com.destr.financehelper.presentation.common.MvpNavFragment
import com.destr.financehelper.presentation.util.SimpleOnItemSelectedListener
import com.destr.financehelper.presentation.util.TaskDiffCallback
import kotlinx.android.synthetic.main.course_fragment.*
import kotlinx.android.synthetic.main.course_fragment.view.*
import moxy.presenter.InjectPresenter

class CourseFragment : MvpNavFragment(), CourseView {

    @InjectPresenter
    lateinit var coursePresenter: CoursePresenter

    private val onPairLongClick = { position: Int, pairValue: String ->
        Toast.makeText(context, "pair name: " + pairValue, Toast.LENGTH_SHORT).show()
        coursePresenter.onCourseItemLongClick(position, pairValue)
    }

    private val courseAdapter by lazy { CourseAdapter(TaskDiffCallback(), onPairLongClick) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.course_fragment, container, false).apply {
        setupCourseViews(this)
    }

    override fun onStart() {
        super.onStart()
        coursePresenter.onStart()
    }

    private fun setupCourseViews(view: View) = with(view) { course_list.adapter = courseAdapter }

    override fun setFirstCurrency(currencies: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, currencies)
        firstCurrencyView.adapter = adapter
        firstCurrencyView.setSelection(0)
        firstCurrencyView.onItemSelectedListener = object : SimpleOnItemSelectedListener() {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) = coursePresenter.onFirstCurrencyViewClicked(position)
        }
    }

    override fun setSecondCurrency(currencies: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, currencies)
        secondCurrency.adapter = adapter
        secondCurrency.setSelection(0)
        secondCurrency.onItemSelectedListener = object : SimpleOnItemSelectedListener() {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) = coursePresenter.onSecondCurrencyViewClicked(position)
        }
    }

    override fun setPairs(pairs: Map<String, PairDetail>) = courseAdapter.submitList(pairs.toList())
}