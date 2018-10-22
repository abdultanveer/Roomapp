package room.b15.rjt.com.roomapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    WordRoomDatabase db;
    private WordDao mWordDao;
    private List<Word> allWordsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         db = WordRoomDatabase.getDatabase(this);
         mWordDao = db.wordDao();

        ListView listView = findViewById(R.id.myListView);

            getAsyncTask getTask = new getAsyncTask(listView,mWordDao);
            getTask.execute();
//         allWordsList = mWordDao.getAllWords();

//         get.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Toast.makeText(MainActivity.this,  "data : " + allWordsList.get(0).getWord() , Toast.LENGTH_SHORT).show();
//             }
//         });

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

    private class getAsyncTask extends AsyncTask<Void, Void ,Void>
    {

        private WordDao mAsyncTaskDao;
        ListView mlistView;
        public getAsyncTask(ListView listView, WordDao mWordDao) {
            this.mlistView = listView;
            this.mAsyncTaskDao = mWordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            allWordsList = mWordDao.getAllWords();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

//            super.onPostExecute(aVoid);

            ArrayAdapter<Word> adapter = new ArrayAdapter<Word>(MainActivity.this,android.R.layout.simple_list_item_1,
                    android.R.id.text1,allWordsList);
            mlistView.setAdapter(adapter);

        }
    }


}
