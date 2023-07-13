package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Clé de sauvegarde de l'état du TextView
    private static final String TEXT_STATE = "currentText";
    private TextView mTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialise mTextView
        mTextView = findViewById(R.id.textView1);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        // Restaure TextView s'il y a un saveInstanceState
        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        // Placer un message dans la vue texte
        mTextView.setText(R.string.napping);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        // Démarre l'AsyncTask.
        // L'AsyncTask a un rappel qui mettra à jour l'affichage du texte.
        new SimpleAsyncTask(mTextView, progressBar).execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Enregistre l'état de TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}