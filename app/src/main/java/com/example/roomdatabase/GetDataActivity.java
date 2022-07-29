
package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roomdatabase.Adapter.UserAdapter;
import com.example.roomdatabase.EntityClass.UserModel;

import java.util.ArrayList;
import java.util.List;

public class GetDataActivity extends AppCompatActivity {


    RecyclerView recyclerview;
    Button btnDeleteAllData;

    private List<UserModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);

        recyclerview = findViewById(R.id.recyclerview);
        btnDeleteAllData = findViewById(R.id.btnDeleteData);
        btnDeleteAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseClass.getDatabase(getApplicationContext()).getDao().deleteAllData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();

    }

    private void getData() {
        list = new ArrayList<>();
        list = DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
        recyclerview.setAdapter(new UserAdapter(getApplicationContext(), list, new UserAdapter.DeleteItemClicklistner() {
            @Override
            public void onItemDelete(int position, int id) {
                DatabaseClass.getDatabase(getApplicationContext()).getDao().deleteData(id);
                getData();
            }
        }));
    }
}