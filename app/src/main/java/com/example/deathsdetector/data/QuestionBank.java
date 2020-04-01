package com.example.deathsdetector.data;

import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.deathsdetector.MainActivity;
import com.example.deathsdetector.controller.AppController;
import com.example.deathsdetector.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    ArrayList<Question> questionArrayList = new ArrayList<>();
    private String url = "https://api.rootnet.in/covid19-in/stats/latest";

    public List<Question> getQuestion(final AnswerAsyncResponse callBack) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONObject jsonObject1 = jsonObject.getJSONObject("summary");

                    Log.d("JsonObject", "onResponse: "+jsonObject);

                    Log.d("Json","OnResponse: "+response.getString("data"));

                    Log.d("Total Cases", "onResponse: "+jsonObject1.getString("total"));
                    Log.d("Total Deaths", "onResponse: "+jsonObject1.getString("deaths"));

                    Log.d("Sucheaa", "onResponse: "+jsonObject1);

                    //Question questionoptional = new Question();
                    //totalCases = Integer.parseInt(jsonObject1.getString("total"));

                    //Intent in=new Intent(MainActivity.class);
                    //Log.d("TotalCasesoriginal ", "onResponse: "+totalCases);
                    JSONArray jsonArray = jsonObject.getJSONArray("regional");

                    for (int i=0;i<jsonArray.length();i++) {

                        Question question=new Question();

                        JSONObject information = jsonArray.getJSONObject(i);
                        //Log.d("FirstObject", "onResponse: " + information.getString("loc"));

                        String stateName = information.getString("loc");
                        int positiveCases = information.getInt("confirmedCasesIndian");
                        int dischargedFromHospital = information.getInt("discharged");
                        int deathsInState = information.getInt("deaths");

                        question.setStateNames(stateName);
                        question.setDeathsInState(deathsInState);
                        question.setDischarged(dischargedFromHospital);
                        question.setPositiveCasesInState(positiveCases);

                        //Log.d("InformationOf " + i + " "+stateName, "onResponse: Positive cases " + positiveCases + " Deaths " + deathsInState + " Discharged " + dischargedFromHospital);
                        //Log.d("Bello ", "onResponse: " +question.getPositiveCasesInState());
                        //add question to arrayList
                        questionArrayList.add(question);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(null != callBack)  callBack.processFinished(questionArrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error ", "onErrorResponse: "+error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


        return questionArrayList;
    }

}
