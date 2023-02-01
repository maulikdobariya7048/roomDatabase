package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.roomdatabase.databinding.ActivityUpdateactivityBinding;

public class UpdateActivity extends AppCompatActivity {

    private ActivityUpdateactivityBinding binding;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.uId.setText(String.valueOf((getIntent().getIntExtra("id", 0))));

        binding.btnUpdate.setOnClickListener(view -> {
            if (validate()) {
                int id = Integer.parseInt(binding.uId.getText().toString().trim());
                String fName = binding.uFName.getText().toString().trim();
                String lName = binding.uLName.getText().toString().trim();
                int rollNumber = Integer.parseInt(binding.uRollNumber.getText().toString().trim());

                StudentModel addStudent = new StudentModel();
                addStudent.setId(id);
                addStudent.setFirstName(fName);
                addStudent.setLastName(lName);
                addStudent.setRollNumber(rollNumber);

                helper = DatabaseHelper.getDb(this);
                helper.studentDao().updateStudent(addStudent);
                Toast.makeText(this, "Student Updated", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        binding.btnCancel.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private boolean validate() {
        if (binding.uFName.getText().toString().isEmpty()) {
            binding.uFName.setError("Field can not be empty");
            binding.uFName.requestFocus();
            return false;
        }
        if (binding.uLName.getText().toString().isEmpty()) {
            binding.uFName.setError("Field can not be empty");
            binding.uLName.requestFocus();
            return false;
        }
        if (binding.uRollNumber.getText().toString().isEmpty()) {
            binding.uRollNumber.setError("Field can not be empty");
            binding.uRollNumber.requestFocus();
            return false;
        }
        return true;
    }
}