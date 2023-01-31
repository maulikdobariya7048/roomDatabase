package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.roomdatabase.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnEnter.setOnClickListener(view -> {

            if (validate()) {
                String fName = binding.fName.getText().toString().trim();
                String lName = binding.lName.getText().toString().trim();
                int rollNumber = Integer.parseInt(binding.rollNumber.getText().toString().trim());

                StudentModel addStudent = new StudentModel();
                addStudent.setFirstName(fName);
                addStudent.setLastName(lName);
                addStudent.setRollNumber(rollNumber);

                helper = DatabaseHelper.getDb(this);
                helper.studentDao().addStudent(addStudent);
                Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();

                binding.fName.setText("");
                binding.lName.setText("");
                binding.rollNumber.setText("");
            }
        });

        binding.btnList.setOnClickListener(view -> startActivity(new Intent(this, ListActivity.class)));
    }

    private boolean validate() {
        if (binding.fName.getText().toString().equals("")) {
            binding.fName.setError("Field can not be empty");
            binding.fName.requestFocus();
            return false;
        }
        if (binding.lName.getText().toString().equals("")) {
            binding.lName.setError("Field can not be empty");
            binding.lName.requestFocus();
            return false;
        }
        if (binding.rollNumber.getText().toString().isEmpty()) {
            binding.rollNumber.setError("Field can not be empty");
            binding.rollNumber.requestFocus();
            return false;
        }
        return true;
    }
}