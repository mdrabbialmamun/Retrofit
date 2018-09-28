package com.rbysoft.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rbysoft.retrofit.Adapter.StudentAdapter;
import com.rbysoft.retrofit.Service.StudentService;
import com.rbysoft.retrofit.response.StudentResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL ="https://indomitable71.000webhostapp.com/";

    private StudentService studentService;
    private ListView listView;
    private List<StudentResponse> student = new ArrayList<>();
    private StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentResponse studentResponse =student.get(position);
                startActivity(new Intent(MainActivity.this,StudentDetails.class)
                .putExtra("Student",studentResponse));
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        studentService =retrofit.create(StudentService.class);
        Call<List<StudentResponse>>call=studentService.getAllStudent();
        call.enqueue(new Callback<List<StudentResponse>>() {
            @Override
            public void onResponse(Call<List<StudentResponse>> call, Response<List<StudentResponse>> response) {
                //for condition place

                if(response.code()==200){
                    student =response.body();
                    studentAdapter = new StudentAdapter(MainActivity.this,student);
                    listView.setAdapter(studentAdapter);
                }
                if (response.code()==404){
                    Toast.makeText(MainActivity.this,"Server not found",Toast.LENGTH_LONG).show();
                }
                if (response.code()==500){
                    Toast.makeText(MainActivity.this,"Internal Error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<StudentResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"No internet Connection",Toast.LENGTH_LONG).show();
            }
        });
    }
}
