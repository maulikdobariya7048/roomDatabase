package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.os.Bundle;
import android.view.View;

import com.example.roomdatabase.databinding.ActivityListBinding;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ActivityListBinding binding;
    private StudentAdapter adapter;
    private DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        helper = DatabaseHelper.getDb(this);

        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        List<StudentModel> list = helper.studentDao().getAllStudent();
        adapter = new StudentAdapter(this, list);
        binding.recView.setAdapter(adapter);

        if (list.size() > 0) {
            binding.txtNoData.setVisibility(View.GONE);
        } else {
            binding.txtNoData.setVisibility(View.VISIBLE);
        }
    }
}