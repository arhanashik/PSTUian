package com.workfort.pstuian.app.ui.students

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.workfort.pstuian.R
import com.workfort.pstuian.app.data.local.batch.BatchEntity
import com.workfort.pstuian.app.data.local.constant.Const
import com.workfort.pstuian.app.data.local.faculty.FacultyEntity
import com.workfort.pstuian.app.data.local.student.StudentEntity
import com.workfort.pstuian.app.ui.base.activity.BaseActivity
import com.workfort.pstuian.app.ui.faculty.listener.StudentClickEvent
import com.workfort.pstuian.app.ui.studentprofile.StudentProfileActivity
import com.workfort.pstuian.app.ui.students.adapter.StudentsAdapter
import com.workfort.pstuian.app.ui.students.intent.StudentsIntent
import com.workfort.pstuian.app.ui.students.viewstate.StudentsState
import com.workfort.pstuian.databinding.ActivityStudentsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class StudentsActivity : BaseActivity<ActivityStudentsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityStudentsBinding
            = ActivityStudentsBinding::inflate

    override fun getMenuId(): Int = R.menu.menu_search
    override fun getSearchMenuItemId(): Int = R.id.action_search
    override fun getSearchQueryHint(): String = getString(R.string.hint_search_student)

    private val mViewModel: StudentsViewModel by viewModel()
    private lateinit var mAdapter: StudentsAdapter
    private lateinit var mFaculty: FacultyEntity
    private lateinit var mBatch: BatchEntity

    override fun afterOnCreate(savedInstanceState: Bundle?) {
        val faculty = intent.getParcelableExtra<FacultyEntity>(Const.Key.FACULTY)
        if(faculty == null) finish()
        else mFaculty = faculty

        val batch = intent.getParcelableExtra<BatchEntity>(Const.Key.BATCH)
        if(batch == null) finish()
        else mBatch = batch

        setHomeEnabled()
        title = mBatch.name
        initStudentsList()

        observeStudents()
        lifecycleScope.launch {
            //initialize the data flow
            mViewModel.handleIntent(mFaculty.id, mBatch.id)
            mViewModel.intent.send(StudentsIntent.GetStudents)
        }
    }

    override fun onSearchQueryChange(searchQuery: String) {
        mAdapter.filter.filter(searchQuery)
    }

    private fun initStudentsList() {
        mAdapter = StudentsAdapter()
        mAdapter.setListener(object: StudentClickEvent {
            override fun onClickStudent(student: StudentEntity) {
                val intent = Intent(this@StudentsActivity, StudentProfileActivity::class.java)
                intent.putExtra(Const.Key.FACULTY, mFaculty)
                intent.putExtra(Const.Key.BATCH, mBatch)
                intent.putExtra(Const.Key.STUDENT, student)
                startActivity(intent)
            }
        })

        binding.rvStudents.adapter = mAdapter
    }

    private fun observeStudents() {
        lifecycleScope.launch {
            mViewModel.studentsState.collect {
                when (it) {
                    is StudentsState.Idle -> {
                    }
                    is StudentsState.Loading -> {
                        with(binding) {
                            shimmerLayout.visibility = View.VISIBLE
                            shimmerLayout.startShimmer()
                            rvStudents.visibility = View.GONE
                            tvMessage.visibility = View.GONE
                        }
                    }
                    is StudentsState.Students -> {
                        with(binding) {
                            shimmerLayout.stopShimmer()
                            shimmerLayout.visibility = View.GONE
                            rvStudents.visibility = View.VISIBLE
                            tvMessage.visibility = View.GONE
                        }
                        renderStudents(it.students)
                    }
                    is StudentsState.Error -> {
                        with(binding) {
                            shimmerLayout.stopShimmer()
                            shimmerLayout.visibility = View.GONE
                            rvStudents.visibility = View.GONE
                            tvMessage.visibility = View.VISIBLE
                        }
                        Timber.e(it.error?: "Can't load data")
                    }
                }
            }
        }
    }

    private fun renderStudents(data: List<StudentEntity>) {
        mAdapter.setStudents(data.toMutableList())
    }
}
