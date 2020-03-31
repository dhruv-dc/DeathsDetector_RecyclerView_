package com.example.deathsdetector.data;

import com.example.deathsdetector.model.Question;

import java.util.ArrayList;

public interface AnswerAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);

}
