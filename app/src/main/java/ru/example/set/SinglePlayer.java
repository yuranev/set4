package ru.example.set;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

@SuppressLint("Registered")
public class SinglePlayer extends AppCompatActivity implements View.OnClickListener {

    //Определяем переменные
    Card[] checkCards = new Card[81];
    ImageView[] replaceViews = new ImageView[2];
    int[] selectedViewsTags = {0, 0};
    int[] selectedViewsIds = {0, 0};
    Card[] selectedCards = new Card[3];
    int count = 0;
    int dealtCards;
    boolean isSet;
    Deck deck = new Deck();
    ImageView[] iViews = new ImageView[12];
    int cardsLeft;
    int score = 0;
    TextView timeView;
    TextView scoreView;
    long highscore;
    long start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_single);

        Calendar c = Calendar.getInstance();

        start = c.getTimeInMillis();

        shuffle(deck.getDeck(), 0);

        startClock();

        timeView =  this.findViewById(R.id.timercount);
        scoreView =  this.findViewById(R.id.scorecount);

        iViews[0] =  this.findViewById(R.id.image_1);
        iViews[1] =  this.findViewById(R.id.image_2);
        iViews[2] =  this.findViewById(R.id.image_3);
        iViews[3] =  this.findViewById(R.id.image_4);
        iViews[4] =  this.findViewById(R.id.image_5);
        iViews[5] =  this.findViewById(R.id.image_6);
        iViews[6] =  this.findViewById(R.id.image_7);
        iViews[7] =  this.findViewById(R.id.image_8);
        iViews[8] =  this.findViewById(R.id.image_9);
        iViews[9] =  this.findViewById(R.id.image_10);
        iViews[10] =  this.findViewById(R.id.image_11);
        iViews[11] =  this.findViewById(R.id.image_12);

        for (int i = 0; i < 12; i++) {
            deck.getDeck()[i].setiView(iViews[i]);
            iViews[i].setImageResource(deck.getDeck()[i].getDrawableID());
            iViews[i].setTag(deck.getDeck()[i].getDrawableID());
        }
        dealtCards = 11;

        for (int i = 0; i < 12; i++){
            iViews[i].setOnClickListener(this);
        }
    }

    public void shuffle(Card[] deck, int lowbound)
    {
        Random rand = new Random();
        for (int i = 80; i > lowbound; i--)
        {
            int j = (rand.nextInt(80 - lowbound)) + lowbound;
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    //Проверяем доску
    public boolean checkDeck(){
        int idx = 0;
        for(int i = 0; i < 81; i++)
        {
            checkCards[i] = null;
        }
        for(int i = 0; i < 81; i++){
            if(deck.getDeck()[i].getValid()){
                checkCards[idx] = deck.getDeck()[i];
                idx++;
            }
        }

        for(int i = 0; i < idx - 1; i++){
            for(int j = i+1; j < idx - 1; j++){
                for(int k = j+1; k < idx; k++){
                    if(validateSet(checkCards[i], checkCards[j])){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void startClock(){
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(10);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Calendar c = Calendar.getInstance();

                                if(score != 27) {
                                    long now = c.getTimeInMillis();
                                    long diff = now - start;
                                    long sec = diff / 1000;
                                    highscore = sec;
                                    cardsLeft = 81 - (dealtCards + 1) + 12;
                                    @SuppressLint("DefaultLocale")
                                    String curTime = String.format("      %d", sec);
                                    @SuppressLint("DefaultLocale")
                                    String cardsCount = String.format("      %d", cardsLeft);
                                    @SuppressLint("DefaultLocale")
                                    String scoreCount = String.format("      %d", score);
                                    timeView.setText(curTime);
                                    scoreView.setText(scoreCount);
                                }
                            }
                        });
                    }
                } catch (InterruptedException ignored) {
                }
            }
        };

        t.start();
    }

    //При нажатии на карточку мы проверяем её тэг, запоминаем его, записываем, соответственно, а так же
    //делаем её прозрачнее
    @Override
    public void onClick(View v) {
        v.setAlpha((float)0);

        int tag = (Integer) v.getTag();
        if(tag != selectedViewsTags[0] && tag != selectedViewsTags[1]){
            selectedViewsTags[count] = tag;
            selectedViewsIds[count] = v.getId();
            System.out.println(selectedViewsTags[0]);
            System.out.println(selectedViewsTags[1]);
            count++;
        }
        else{
            if(tag == selectedViewsTags[0]){
                selectedViewsTags[0] = selectedViewsTags[1];
                selectedViewsTags[1] = selectedViewsTags[2];
            }
            if(tag == selectedViewsTags[1]){
                selectedViewsTags[1] = selectedViewsTags[2];
            }
            //v.setAlpha((float)1.0);
            count--;
        }



        //Если выбраны 2 карты, то проверяем их по правилам игры
        if(count == 2){
            Log.e("6","6");
            //Функция проверки карт
            validate();
        }

    }

    //Проверка выбранных карт по правилам соответствия
    public void validate(){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 81; j++){
                if(deck.getDeck()[j].getDrawableID() == selectedViewsTags[i]){
                    deck.getDeck()[j].setSelected(true);
                }
            }
        }

        int idx = 0;
        for(int k = 0; k < 81; k++){
            if(deck.getDeck()[k].getSelected()){
                selectedCards[idx] = deck.getDeck()[k];
                idx++;
            }
        }


        isSet = validateSet(selectedCards[0], selectedCards[1]);

        if(isSet) {
            Log.e("3","3");
            selectedCards[0].setValid(false);
            selectedCards[1].setValid(false);
            replaceViews[0] =  this.findViewById(selectedViewsIds[0]);
            replaceViews[1] =  this.findViewById(selectedViewsIds[1]);
            if(dealtCards == 80){
                Log.e("2","2");
                for (int i = 1; i < 4; i++) {
                    replaceViews[i - 1].setVisibility(View.INVISIBLE);
                }
            }
            else {
                Log.e("1","1");
                for (int i = 1; i < 3; i++) {
                    deck.getDeck()[dealtCards + i].setiView(replaceViews[i - 1]);
                    replaceViews[i - 1].setImageResource(R.drawable.empty);
                    replaceViews[i - 1].setTag(deck.getDeck()[dealtCards + i].getDrawableID());
                }
                dealtCards += 2;
            }
            score++;

            if(score == 27 || !checkDeck()){
                SharedPreferences prefs = this.getSharedPreferences("myprefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong("HighScore", highscore);
                editor.apply();
                Intent intent = new Intent(SinglePlayer.this, MainActivity.class);
                startActivity(intent);
            }
        }

        reset();
        clickListen();
    }


    public void clickListen(){
        for (int i = 0; i < 12; i++){
            iViews[i].setOnClickListener(this);
        }
    }

    //Обновляем все данные, удаляем выбранные карты, идентификаторы, возвращаем прозрачность на 1
    public void reset(){
        Log.e("4","4");
        replaceViews = new ImageView[2];
        selectedViewsTags[0] = 0;
        selectedViewsTags[1] = 0;
        selectedViewsIds[0] = 0;
        selectedViewsIds[1] = 0;
        selectedCards = new Card[2];
        count = 0;
        for(int k = 0; k < 81; k++) {
            deck.getDeck()[k].setSelected(false);
        }
        for(int i = 0; i < 12; i++){
            Log.e("5","5");
            iViews[i].setAlpha((float)1.0);
        }
    }

    //Проверяем логику (a == b == c) и (a != b != c != a)
    static boolean validateHelper(String a, String b)
    {
        boolean res = false;

        if(a.equals(b))
        {
            res = true;
        }

        return res;
    }

    static boolean validateSet(Card a, Card b)
    {
        String x;
        String y;
        x = a.getColor();
        y = b.getColor();
        boolean colorCheck = validateHelper(x, y);
        return (colorCheck);
    }

    //Сет ли это?
    static boolean isSet(Deck deck, int a, int b)
    {
        return validateSet(deck.getDeck()[a], deck.getDeck()[b]);
    }
}