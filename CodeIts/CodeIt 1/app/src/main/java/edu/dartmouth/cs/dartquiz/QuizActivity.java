package edu.dartmouth.cs.dartquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String CHEATER_TAG = "false";
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean mIsCheater;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_soccer, true),
            new Question(R.string.question_ivy, true),
            new Question(R.string.question_weather, true),
            new Question(R.string.question_class, false),
            new Question(R.string.question_hope, true)
    };
    private int mCurrentIndex = 0;
    private TextView mQuestionTextView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == REQUEST_CODE_CHEAT)) {
            if (data != null) {
                mIsCheater = (CheatActivity.wasAnswerShown(data) || mIsCheater);
            }
        }

        if (mIsCheater){
            mQuestionBank[mCurrentIndex].setCheater();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState()");
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBoolean(CHEATER_TAG, mIsCheater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.d(TAG, "onCreate()");

        Button mTrueButton = findViewById(R.id.true_button);
        Button mFalseButton = findViewById(R.id.false_button);
        Button mPrevButton = findViewById(R.id.prev_button);
        Button mNextButton = findViewById(R.id.next_button);
        Button mCheatButton = findViewById(R.id.cheat_button);

        if (savedInstanceState != null) {
            Log.d(TAG, "recovered mCurrentIndex");
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(CHEATER_TAG, false);
        }

        mQuestionTextView = findViewById(R.id.question_text);

        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mCurrentIndex = mCurrentIndex + 1;
                mIsCheater = false;
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question);
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex == 0) {
                    mCurrentIndex = mQuestionBank.length - 1;
                } else {
                    mCurrentIndex = mCurrentIndex - 1;
                }
                int question = mQuestionBank[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question);
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start CheatActivity.
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                // startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

    }

    private void checkAnswer(boolean answer) {
        boolean isAnswerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        if (mIsCheater || mQuestionBank[mCurrentIndex].getCheater()) {
            Toast.makeText(QuizActivity.this,
                    R.string.judgement_text,
                    Toast.LENGTH_SHORT).show();
        } else {

            if (answer == isAnswerTrue) {
                Toast.makeText(QuizActivity.this,
                        R.string.correct_text,
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(QuizActivity.this,
                        R.string.incorrect_text,
                        Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

}
