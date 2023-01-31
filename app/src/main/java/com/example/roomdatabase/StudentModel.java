package com.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "studentTable")
public class StudentModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "fName")
    private String firstName;

    @ColumnInfo(name = "lName")
    private String lastName;

    @ColumnInfo(name = "rollNumber")
    private int rollNumber;

    public StudentModel() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRollNumber() {
        return rollNumber;
    }
}
