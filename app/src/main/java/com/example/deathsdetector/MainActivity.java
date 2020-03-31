package com.example.deathsdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView stateTextview;
    public TextView positiveCasesTextview;
    public TextView deathsInStateTextview;
    public TextView dischargedTextview;
    public TextView stateCounterTextview;
    public ImageButton nextButton;
    public ImageButton prevButton;
    private int currentStateIndex = 0;
    private int currentPositiveCasesIndex=0;
    private int currentDischargedIndex=0;
    private int currentDeathInStateIndex=0;
    public List<Question> questionList;
    //public String url = "https://api.rootnet.in/covid19-in/stats/latest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prevButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);

        dischargedTextview = findViewById(R.id.discharged_text_view);
        deathsInStateTextview = findViewById(R.id.deaths_sw_text_view);
        positiveCasesTextview = findViewById(R.id.pc_text_view);
        stateCounterTextview = findViewById(R.id.counter_text);
        stateTextview = findViewById(R.id.state_text_view);

        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);

            questionList = new QuestionBank().getQuestion(new AnswerAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {

//                stateTextview.setText(questionArrayList.get(currentStateIndex).getStateNames());
//                dischargedTextview.setText(questionArrayList.get(currentDischargedIndex).getDischarged());
//                deathsInStateTextview.setText(questionArrayList.get(currentDeathInStateIndex).getDeathsInState());
//                positiveCasesTextview.setText(questionArrayList.get(currentPositiveCasesIndex).getPositiveCasesInState());

                Log.d("statename ; ", "processFinished: "+questionArrayList.get(currentStateIndex).getStateNames());
                Log.d("positivecases ", "processFinished: "+questionArrayList.get(currentPositiveCasesIndex).getPositiveCasesInState());
            }
        });
        //Log.d("Main", "onCreate: "+questionList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.previous_button:
            case R.id.next_button:
                break;
        }
    }
}
