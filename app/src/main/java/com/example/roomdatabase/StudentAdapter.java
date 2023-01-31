package com.example.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        StudentListBinding binding = StudentListBinding.inflate(inflater, parent, false);
//        return new ViewHolder(binding);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {

        holder.cardNumber.setText(String.valueOf(listStudent.get(position).getId()));
        holder.txtFName.setText(listStudent.get(position).getFirstName());
        holder.txtLName.setText(listStudent.get(position).getLastName());
        holder.txtRollNumber.setText(String.valueOf(listStudent.get(position).getRollNumber()));
        holder.txtDelete.setOnClickListener(view -> {
            StudentModel model = new StudentModel();
            int id = listStudent.get(position).getId();
            model.setId(id);
            DatabaseHelper helper = DatabaseHelper.getDb(context);
            helper.studentDao().deleteStudent(model);
            notifyItemRemoved(position);
            Toast.makeText(context, "Deleted",Toast.LENGTH_SHORT).show();
        });
        holder.txtUpdate.setOnClickListener(view -> {
            context.startActivity(new Intent(context, UpdateActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cardNumber, txtFName, txtLName, txtRollNumber, txtDelete, txtUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardNumber = itemView.findViewById(R.id.cardNumber);
            txtFName = itemView.findViewById(R.id.txtFName);
            txtLName = itemView.findViewById(R.id.txtLName);
            txtRollNumber = itemView.findViewById(R.id.txtRollNumber);
            txtDelete = itemView.findViewById(R.id.txtDelete);
            txtUpdate = itemView.findViewById(R.id.txtUpdate);
        }
    }
}
