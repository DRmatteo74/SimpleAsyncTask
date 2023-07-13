package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;


public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private ProgressBar progressBar;

    SimpleAsyncTask(TextView tv, ProgressBar bar) {
        mTextView = new WeakReference<>(tv);
        progressBar = bar;
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Génère un nombre aléatoire entre 0 et 10
        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

        for (int i=0; i<=100; i++){
            publishProgress(i);
            try {
                Thread.sleep(s/100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return "Enfin réveillé après avoir dormi pendant " + s + " millisecondes !";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    protected void onPreExecute(ProgressBar bar) {
        bar.setProgress(0);
    }

    protected void onProgressUpdate(Integer... values) {
        int progress = values[0]; // Récupérez la valeur mise à jour
        progressBar.setProgress(progress); // Mettez à jour la ProgressBar
    }
}
