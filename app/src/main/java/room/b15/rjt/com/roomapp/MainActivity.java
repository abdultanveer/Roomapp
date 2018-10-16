package room.b15.rjt.com.roomapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WordRoomDatabase db;
    private WordDao mWordDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         db = WordRoomDatabase.getDatabase(this);
         mWordDao = db.wordDao();


    }

    public void clickHandler(View v){
        EditText wordEditText = findViewById(R.id.word_edittext);
        String data =  wordEditText.getText().toString();
        Word word = new Word(data);
        insert(word);
        wordEditText.setText("");
       // mWordDao.insert(word);
    }

    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
