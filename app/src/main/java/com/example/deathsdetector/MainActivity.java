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
import com.example.deathsdetector.controller.AppController;
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
    public TextView totalCasesTextview;
    public TextView positiveCasesTextview;
    public TextView deathsInStateTextview;
    public TextView dischargedTextview;
    public TextView stateCounterTextview;
    public ImageButton nextButton;
    private RequestQueue mQueue;
    public int totalCases;
    public ImageButton prevButton;
    private int currentStateIndex = 0;
    private int currentPositiveCasesIndex=0;
    private int currentDischargedIndex=0;
    private int currentDeathInStateIndex=0;
    private int currentTotalCases=0;
    public List<Question> questionList;
    public String sukaru;
    public String url = "https://api.rootnet.in/covid19-in/stats/latest";

    @Override
    protected void onStart() {
        super.onStart();
        totalCasesTextview = (TextView)findViewById(R.id.total_cases);
        mQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (JSONObject)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONObject jsonObject1 = jsonObject.getJSONObject("summary");
                            Log.d("Maintotalcases ", "onResponse: "+jsonObject1.getString("total"));
                            String sukaru = jsonObject1.getString("total");
                            totalCasesTextview.setText("Total Cases : "+sukaru);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("error ", "onErrorResponse: "+error);
                error.printStackTrace();
            }
        });
        AppController.getInstance().addToRequestQueue(request);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCasesTextview = findViewById(R.id.total_cases);

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



                //totalCasesTextview.setText("Total Cases : "+Integer.toString());
                stateTextview.setText(questionArrayList.get(currentStateIndex).getStateNames());
                dischargedTextview.setText("Recovered : "+String.valueOf(questionArrayList.get(currentDischargedIndex).getDischarged()));
                deathsInStateTextview.setText("Deaths : "+String.valueOf(questionArrayList.get(currentDeathInStateIndex).getDeathsInState()));
                positiveCasesTextview.setText("Positive Cases : " +String.valueOf(questionArrayList.get(currentPositiveCasesIndex).getPositiveCasesInState()));
                stateCounterTextview.setText(currentStateIndex + " / " + questionArrayList.size());

                Log.d("statename ; ", "processFinished: "+questionArrayList.get(currentStateIndex).getStateNames());
                Log.d("positivecases ", "processFinished: "+questionArrayList.get(currentPositiveCasesIndex).getPositiveCasesInState());
            }
        });
        Log.d("Main", "onCreate: "+questionList);
        Log.d("Mainsize", "onCreate: "+questionList.size());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.previous_button:
                if (currentStateIndex>0){
                    currentStateIndex = (currentStateIndex - 1)%questionList.size();
                    currentDeathInStateIndex = (currentDeathInStateIndex-1)%questionList.size();
                    currentDischargedIndex = (currentDischargedIndex - 1)%questionList.size();
                    currentPositiveCasesIndex = (currentPositiveCasesIndex - 1)%questionList.size();
                    updateState();
                }
                break;
            case R.id.next_button:
                currentStateIndex = (currentStateIndex + 1)%questionList.size();
                currentDeathInStateIndex = (currentDeathInStateIndex+1)%questionList.size();
                currentDischargedIndex = (currentDischargedIndex + 1)%questionList.size();
                currentPositiveCasesIndex = (currentPositiveCasesIndex + 1)%questionList.size();
                updateState();
                break;
        }
    }

    private void updateState() {

        Log.d("stateupdae? ", "updateState: "+questionList.get(currentStateIndex));
        String state = questionList.get(currentStateIndex).getStateNames();
        int discharged = questionList.get(currentDischargedIndex).getDischarged();
        int positivacases = questionList.get(currentPositiveCasesIndex).getPositiveCasesInState();
        int deathinstate = questionList.get(currentDeathInStateIndex).getDeathsInState();
        stateTextview.setText(String.valueOf(state));
        deathsInStateTextview.setText("Deaths : "+String.valueOf(deathinstate));
        dischargedTextview.setText("Recovered : "+String.valueOf(discharged));
        positiveCasesTextview.setText("Positive Cases : " +String.valueOf(positivacases));
        stateCounterTextview.setText(currentStateIndex + " / " + questionList.size());
    }
}
