package edu.dartmouth.cs.dartquiz;

class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean wasCheater;

    Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        wasCheater = false;
    }

    int getTextResId() {
        return mTextResId;
    }

    boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    void setCheater () { wasCheater = true; }

    boolean getCheater () {return wasCheater; }

}
