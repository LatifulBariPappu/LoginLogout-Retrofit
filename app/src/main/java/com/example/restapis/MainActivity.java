package com.example.restapis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restapis.api.apicontroller;
import com.example.restapis.databinding.ActivityMainBinding;
import com.example.restapis.models.signinmodel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkexistence();

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processlogin();

            }
        });
    }

    void checkexistence() {
        SharedPreferences  sp=getSharedPreferences("logincredentials",MODE_PRIVATE);
        if(sp.contains("email")){
            startActivity(new Intent(MainActivity.this,DashboardActivity.class));
            finish();
        }else{
            binding.signintv.setText("Please login");
            binding.signintv.setTextColor(Color.RED);
        }
    }

    void processlogin() {
        String email=binding.t1.getText().toString();
        String password=binding.t2.getText().toString();
        Call<signinmodel> call= apicontroller
                .getInstance()
                .getapi()
                .verifyusers(email,password);
        call.enqueue(new Callback<signinmodel>() {
            @Override
            public void onResponse(Call<signinmodel> call,Response<signinmodel> response) {
                signinmodel obj=response.body();
                String output=obj.getMessage();
                if(output.equals("exist")){
                    SharedPreferences sp=getSharedPreferences("logincredentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("email",binding.t1.getText().toString());
                    editor.putString("password",binding.t2.getText().toString());
                    editor.commit();
                    editor.apply();
                    startActivity(new Intent(MainActivity.this,DashboardActivity.class));
                    finish();
                }
                if(output.equals("failed")){
                    binding.t1.setText("");
                    binding.t2.setText("");
                    binding.signintv.setText("Invalid email or password");
                    binding.signintv.setTextColor(Color.RED);

                }
            }
            @Override
            public void onFailure(Call<signinmodel> call,Throwable t) {
                binding.t1.setText("");
                binding.t2.setText("");
                binding.signintv.setText(t.toString());
                binding.signintv.setTextColor(Color.RED);
            }
        });
    }
}