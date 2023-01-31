package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    String TABLE_NAME = "studentTable";

    @Query("select * from " + TABLE_NAME)
    List<StudentModel> getAllStudent();

    @Insert
    void addStudent(StudentModel studentModel);

    @Update
    void updateStudent(StudentModel studentModel);

    @Delete
    void deleteStudent(StudentModel studentModel);

}
