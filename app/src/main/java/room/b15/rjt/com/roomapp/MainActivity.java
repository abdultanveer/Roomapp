package room.b15.rjt.com.roomapp;

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
        mWordDao.insert(word);
    }
}
