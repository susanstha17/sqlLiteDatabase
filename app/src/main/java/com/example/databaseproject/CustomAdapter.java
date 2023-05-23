package com.example.databaseproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<StudentModel> mydata = new ArrayList<>();
    public CustomAdapter(ViewActivity viewActivity, ArrayList<StudentModel> data) {
        context=viewActivity;
        mydata=data;

    }

    @Override
    public int getCount() {
        return mydata.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.single_item,null);
        TextView id=view.findViewById(R.id.id);
        TextView name=view.findViewById(R.id.name);
        TextView address=view.findViewById(R.id.address);
        TextView phone=view.findViewById(R.id.phone);

        id.setText(""+mydata.get(i).getId());
        name.setText(mydata.get(i).getName());
        address.setText(mydata.get(i).getAddress());
        phone.setText(mydata.get(i).getPhone());


        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View myview) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.edit_data,null);
                builder.setView(view1);
                AlertDialog ad = builder.create();
                ad.show();

                EditText ename = view1.findViewById(R.id.edit_name);
                EditText eaddress = view1.findViewById(R.id.edit_address);
                EditText ephone = view1.findViewById(R.id.edit_phone);
                Button edit = view1.findViewById(R.id.btnedit);
                Button delete = view1.findViewById(R.id.btndelete);
                ename.setText(mydata.get(i).getName());
                eaddress.setText(mydata.get(i).getAddress());
                ephone.setText(mydata.get(i).getPhone());
                

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StudentModel  sm = new StudentModel();
                        sm.setId(mydata.get(i).getId());
                        sm.setName(ename.getText().toString());
                        sm.setAddress(eaddress.getText().toString());
                        sm.setPhone(ephone.getText().toString());
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.updatedata(sm);
                        ad.dismiss();
                        ViewActivity va = (ViewActivity)  context;
                        va.recreate();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.delete(mydata.get(i).getId());
                        ad.dismiss();
                        ViewActivity va = (ViewActivity)  context;
                        va.recreate();

                    }
                });




                return true;
            }
        });

        return view;
    }
}
