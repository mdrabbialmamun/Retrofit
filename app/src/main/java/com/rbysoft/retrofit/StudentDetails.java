package com.rbysoft.retrofit;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rbysoft.retrofit.response.StudentResponse;
import com.squareup.picasso.Picasso;

public class StudentDetails extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        ImageView imageView =findViewById(R.id.imv);
        textView = findViewById(R.id.tv1);

        StudentResponse studentResponse =(StudentResponse) getIntent().getSerializableExtra("Student");

        String photoString =studentResponse.getImage();
        Uri photoUri =Uri.parse(MainActivity.BASE_URL+"retrofit/"+photoString);
        Picasso.get().load(photoUri).into(imageView);

        textView.setText(studentResponse.getName()+"\n"+studentResponse.getEmail()+"\n"+studentResponse.getPhone()+"\n"+studentResponse.getAddress()+"\n"+studentResponse.getFatherName()+"\n"+studentResponse.getMotherName());
    }
}
