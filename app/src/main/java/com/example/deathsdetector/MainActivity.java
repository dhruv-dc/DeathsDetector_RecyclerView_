package com.example.deathsdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.deathsdetector.data.AnswerAsyncResponse;
import com.example.deathsdetector.data.QuestionBank;
import com.example.deathsdetector.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //public String url = "https://api.rootnet.in/covid19-in/stats/latest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Question> questionList = new QuestionBank().getQuestion(new AnswerAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                Log.d("Inside", "processFinished: "+questionArrayList);
            }
        });
        //Log.d("Main", "onCreate: "+questionList);
    }

}
