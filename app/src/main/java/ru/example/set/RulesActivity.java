package ru.example.set;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        //Окрашиваем статусбар и нижнюю панель
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.navbar_statbar_colors));

        //Записываем текст в Текстовый виджет в формате HTML
        TextView text = findViewById(R.id.textView);
        text.setText(Html.fromHtml("<h1><span style=\"color: #0000ff;\"><strong>ПРАВИЛА:</strong></span></h1>\n" +
                "<p>Цель игры - определить <span style=\"color: #000000;\"><strong>SET</strong></span> из трех карт из 21 выложенной карты.&nbsp;</p>\n" +
                "<p>Каждая карта имеет следующие четыре функции:</p>\n" +
                "<p><span style=\"color: #339966;\"><strong>ЦВЕТ</strong>:</span> Каждая карточка красного, синего или зеленого цвета.</p>\n" +
                "<p><span style=\"color: #339966;\"><strong>СИМВОЛ</strong></span>: каждая карточка содержит прямоугольники, треугольники или овалы.</p>\n" +
                "<p><span style=\"color: #339966;\"><strong>НОМЕР</strong></span>: у каждой карты есть один, два или три символа.</p>\n" +
                "<p><span style=\"color: #339966;\"><strong>Затененная</strong></span>: каждая карта сплошная, открытая или затененная.</p>\n" +
                "<p>A <strong>SET</strong> состоит из трех карт, в которых каждая функция <em>ЛИБО</em> одинакова на каждой карте <em>ИЛИ</em> отличается на каждой карте.</p>\n" +
                "<p><span style=\"color: #0000ff;\"><strong>Одиночная игра:</strong></span></p>\n" +
                "<p>Цель состоит в том, чтобы найти все возможные <strong>SET`ы</strong> и прокрутить колоду из 81 карты как можно быстрее.</p>\n"));
    }
}