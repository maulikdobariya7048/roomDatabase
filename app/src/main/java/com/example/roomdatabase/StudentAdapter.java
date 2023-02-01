package com.example.roomdatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.databinding.StudentListBinding;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private Context context;
    private List<StudentModel> listStudent;

    public StudentAdapter(Context context, List<StudentModel> listStudent) {
        this.context = context;
        this.listStudent = listStudent;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        StudentListBinding binding = StudentListBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {

        holder.binding.cardNumber.setText(String.valueOf(listStudent.get(position).getId()));
        holder.binding.txtFName.setText(listStudent.get(position).getFirstName());
        holder.binding.txtLName.setText(listStudent.get(position).getLastName());
        holder.binding.txtRollNumber.setText(String.valueOf(listStudent.get(position).getRollNumber()));
        holder.binding.txtDelete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Are you sure you want delete?");
            builder.setTitle("Delete?");
            builder.setPositiveButton("yes", (dialog, which) -> {
                StudentModel model = new StudentModel();
                int id = listStudent.get(position).getId();
                model.setId(id);
                DatabaseHelper helper = DatabaseHelper.getDb(context);
                helper.studentDao().deleteStudent(model);
                listStudent.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            });
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            builder.show();

        });
        holder.binding.txtUpdate.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", listStudent.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private StudentListBinding binding;

        public ViewHolder(@NonNull StudentListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
