package com.workfort.pstuian.app.data.local.course

import androidx.lifecycle.LiveData
import androidx.room.*
import com.workfort.pstuian.app.data.local.database.ColumnNames
import com.workfort.pstuian.app.data.local.database.TableNames

@Dao
interface CourseDao {
    @Query("SELECT * FROM " + TableNames.COURSE_SCHEDULE
            + " ORDER BY " + ColumnNames.Course.COURSE_CODE + " ASC")
    fun getAllLive(): LiveData<List<CourseEntity>>

    @Query("SELECT * FROM " + TableNames.COURSE_SCHEDULE
            + " WHERE " + ColumnNames.Course.FACULTY_ID + "=:faculty"
            + " ORDER BY " + ColumnNames.Course.COURSE_CODE + " ASC")
    fun getAllLive(faculty: String): LiveData<List<CourseEntity>>

    @Query("SELECT * FROM " + TableNames.COURSE_SCHEDULE
            + " ORDER BY " + ColumnNames.Course.COURSE_CODE + " ASC")
    fun getAll(): List<CourseEntity>

    @Query("SELECT * FROM " + TableNames.COURSE_SCHEDULE
            + " WHERE " + ColumnNames.Course.FACULTY_ID + "=:facultyId"
            + " ORDER BY " + ColumnNames.Course.COURSE_CODE + " ASC")
    suspend fun getAll(facultyId: Int): List<CourseEntity>

    @Query("SELECT * FROM " + TableNames.COURSE_SCHEDULE
            + " WHERE " + ColumnNames.Course.ID + "=:id LIMIT 1")
    fun get(id: String): CourseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(courseEntity: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CourseEntity>)

    @Update
    fun update(courseEntity: CourseEntity)

    @Delete
    fun delete(courseEntity: CourseEntity)

    @Query("DELETE FROM " + TableNames.COURSE_SCHEDULE
            + " WHERE " + ColumnNames.Course.FACULTY_ID + "=:faculty")
    suspend fun deleteAll(faculty: String)

    @Query("DELETE FROM " + TableNames.COURSE_SCHEDULE)
    suspend fun deleteAll()
}