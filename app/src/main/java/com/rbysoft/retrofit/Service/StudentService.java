package com.rbysoft.retrofit.Service;

import com.rbysoft.retrofit.response.StudentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentService {
    @GET("retrofit/retro.php") //Dir + file.php name
    Call<List<StudentResponse>> getAllStudent();
}
