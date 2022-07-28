package com.example.guesthestar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ImageView ivStar;
    private Button bFirstOption, bSecondOption, bThirdOption, bFourthOption;
    private final String link = "https://kino-teatr.ua/uk/persons.phtml";
    private String pageFromInternet;
    private ArrayList<String> urlImages;
    private ArrayList<String> names;
    private Bitmap bitmapImgStar;

    int randomIntForTrueAnswer;
    int randomIntForButton;

    private boolean isTrueAnswerFirstB = false;
    private boolean isTrueAnswerSecondB = false;
    private boolean isTrueAnswerThirdB = false;
    private boolean isTrueAnswerFourthB = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setAnswers();
        onClickFirstB();
        onClickSecondB();
        onClickThirdB();
        onClickFourthB();
    }

    void init() {
        ivStar = findViewById(R.id.image_view_star);
        bFirstOption = findViewById(R.id.button_1);
        bSecondOption = findViewById(R.id.button_2);
        bThirdOption = findViewById(R.id.button_3);
        bFourthOption = findViewById(R.id.button_4);

        pageFromInternet = getPageFromInternet(link);
        urlImages = getUrlImagesArray(pageFromInternet);
        names = getNamesArray(pageFromInternet);
    }

    void setAnswers() {
        randomIntForTrueAnswer = (int) (Math.random() * urlImages.size() - 1);
        randomIntForButton = (int) (Math.random() * 4) + 1;
        bitmapImgStar = getBitmapImgStar(urlImages.get(randomIntForTrueAnswer));
        if (bitmapImgStar != null) {
            ivStar.setImageBitmap(bitmapImgStar);
        }
        switch (randomIntForButton) {
            case 1:
                bFirstOption.setText(names.get(randomIntForTrueAnswer));
                isTrueAnswerFirstB = true;
                break;
            case 2:
                bSecondOption.setText(names.get(randomIntForTrueAnswer));
                isTrueAnswerSecondB = true;
                break;
            case 3:
                bThirdOption.setText(names.get(randomIntForTrueAnswer));
                isTrueAnswerThirdB = true;
                break;
            case 4:
                bFourthOption.setText(names.get(randomIntForTrueAnswer));
                isTrueAnswerFourthB = true;
                break;
        }

        ArrayList<String> shuffleArrayList = new ArrayList<>(names);
        shuffleArrayList.remove(randomIntForTrueAnswer);
        Collections.shuffle(shuffleArrayList, new Random());
        String falseAnswer1 = shuffleArrayList.get(0);
        String falseAnswer2 = shuffleArrayList.get(1);
        String falseAnswer3 = shuffleArrayList.get(2);
        String falseAnswer4 = shuffleArrayList.get(3);

        if (isTrueAnswerFirstB == false) {
            bFirstOption.setText(falseAnswer1);
        }
        if (isTrueAnswerSecondB == false) {
            bSecondOption.setText(falseAnswer2);
        }
        if (isTrueAnswerThirdB == false) {
            bThirdOption.setText(falseAnswer3);
        }
        if (isTrueAnswerFourthB == false) {
            bFourthOption.setText(falseAnswer4);
        }

    }

    void onClickFirstB() {
        bFirstOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrueAnswerFirstB == true) {
                    Toast.makeText(getApplicationContext(), "It's true!", Toast.LENGTH_SHORT).show();
                    isTrueAnswerFirstB = false;
                } else {
                    Toast.makeText(getApplicationContext(), "NO! Loser", Toast.LENGTH_SHORT).show();
                    isTrueAnswerFirstB = false;
                    isTrueAnswerSecondB = false;
                    isTrueAnswerThirdB = false;
                    isTrueAnswerFourthB = false;
                }
                setAnswers();
            }
        });
    }

    void onClickSecondB() {
        bSecondOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrueAnswerSecondB == true) {
                    Toast.makeText(getApplicationContext(), "It's true!", Toast.LENGTH_SHORT).show();
                    isTrueAnswerSecondB = false;
                } else {
                    Toast.makeText(getApplicationContext(), "NO! Loser", Toast.LENGTH_SHORT).show();
                    isTrueAnswerFirstB = false;
                    isTrueAnswerSecondB = false;
                    isTrueAnswerThirdB = false;
                    isTrueAnswerFourthB = false;
                }
                setAnswers();
            }
        });
    }

    void onClickThirdB() {
        bThirdOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrueAnswerThirdB == true) {
                    Toast.makeText(getApplicationContext(), "It's true!", Toast.LENGTH_SHORT).show();
                    isTrueAnswerThirdB = false;
                } else {
                    Toast.makeText(getApplicationContext(), "NO! Loser", Toast.LENGTH_SHORT).show();
                    isTrueAnswerFirstB = false;
                    isTrueAnswerSecondB = false;
                    isTrueAnswerThirdB = false;
                    isTrueAnswerFourthB = false;
                }
                setAnswers();
            }
        });
    }

    void onClickFourthB() {
        bFourthOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrueAnswerFourthB == true) {
                    Toast.makeText(getApplicationContext(), "It's true!", Toast.LENGTH_SHORT).show();
                    isTrueAnswerFourthB = false;
                } else {
                    Toast.makeText(getApplicationContext(), "NO! Loser", Toast.LENGTH_SHORT).show();
                    isTrueAnswerFirstB = false;
                    isTrueAnswerSecondB = false;
                    isTrueAnswerThirdB = false;
                    isTrueAnswerFourthB = false;
                }
                setAnswers();
            }
        });
    }


    String getPageFromInternet(String link) {
        DownloadStarNameAndImg downloadStarNameAndImg = new DownloadStarNameAndImg();
        try {
            String pageFromInternet = downloadStarNameAndImg.execute(link).get();
            Log.i("STRING", pageFromInternet);
            return pageFromInternet;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    ArrayList<String> getUrlImagesArray(String pageFromInternet) {
        ArrayList<String> urlImages = new ArrayList<>();
        Pattern patternUrlImg = Pattern.compile("data-uk-img data-src=\"(.*?)\" alt=\" \">");
        Matcher matcherUrlImg = patternUrlImg.matcher(pageFromInternet);
        while (matcherUrlImg.find()) {
            urlImages.add(matcherUrlImg.group(1));
        }
        return urlImages;
    }

    ArrayList<String> getNamesArray(String pageFromInternet) {
        ArrayList<String> names = new ArrayList<>();
        Pattern patternName = Pattern.compile("<span class=\"uk-text-small uk-text-muted uk-width-auto\">(.*?)</span>");
        Matcher matcherName = patternName.matcher(pageFromInternet);
        while (matcherName.find()) {
            names.add(matcherName.group(1));
        }
        return names;
    }

    Bitmap getBitmapImgStar(String urlImg) {
        DownloadImage downloadImage = new DownloadImage();
        try {
            Bitmap bitmapImageStar = downloadImage.execute(urlImg).get();
            return bitmapImageStar;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class DownloadStarNameAndImg extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder stringBuilder = new StringBuilder();
            URL url = null;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return stringBuilder.toString();
        }
    }

    private static class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            URL urlImg = null;
            HttpURLConnection httpURLConnection = null;

            try {
                urlImg = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) urlImg.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return null;
        }
    }

}