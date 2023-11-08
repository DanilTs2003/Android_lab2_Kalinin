package com.example.lab2;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.content.res.ColorStateList;
import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.Gravity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private int cardCounter = 0;
    private FloatingActionButton fabAddItem;
    private int cardViewCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        fabAddItem = findViewById(R.id.fab_add_item);

        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCardView();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void addCardView() {
        final boolean[] EDT = {false};
        // Создание нового прямоугольника
        CardView cardView = new CardView(this);
        cardView.setId(cardCounter);
        cardView.setCardElevation(6);
        cardView.setRadius(16);
        cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundColor));
        // Устанавливаем маргины между карточками
        LinearLayout.LayoutParams cardViewLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cardViewLayoutParams.setMargins(0, 20, 0, 0); // Устанавливаем верхний маргин в 20 пикселей
        cardView.setLayoutParams(cardViewLayoutParams);

        // Создание внутреннего LinearLayout внутри CardView
        LinearLayout innerLinearLayout = new LinearLayout(this);
        innerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextInputLayout textInputLayout = new TextInputLayout(this);
        textInputLayout.setId(View.generateViewId());
        LinearLayout.LayoutParams textInputLayoutParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 100
        );
        textInputLayoutParams.gravity = Gravity.BOTTOM; // Установка выравнивания внизу
        textInputLayout.setLayoutParams(textInputLayoutParams);
        textInputLayout.setBoxStrokeColor(ContextCompat.getColor(this, R.color.boxStrokeColor));

        // Создание TextInputEditText
        TextInputEditText editText = new TextInputEditText(this);
        LinearLayout.LayoutParams editTextLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,   LinearLayout.LayoutParams.MATCH_PARENT
        ); // Установка высоты в пикселях
        editTextLayoutParams.setMargins(0, 0, 0,0 ); // Установка нулевых маргинов
        editText.setLayoutParams(editTextLayoutParams);
        editText.setHint("");
        editText.setTextSize(20); // Установка размера текста в sp
        editTextLayoutParams.gravity = Gravity.BOTTOM;
        editText.setTextColor(Color.BLACK);
        editText.setEnabled(false);
        // Добавление TextInputEditText в TextInputLayout
        textInputLayout.addView(editText);



        // Создание FloatingActionButton для кнопки "Удалить"
        FloatingActionButton fabDelete = new FloatingActionButton(this);
        fabDelete.setImageResource(R.drawable.bin);
        fabDelete.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.buttonBackgroundColor)));
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Добавьте здесь обработчик для удаления карточки
                linearLayout.removeView(cardView); // Удаляем CardView из LinearLayout
                linearLayout.requestLayout();
            }
        });

        // Создание FloatingActionButton для кнопки "Редактировать"
        FloatingActionButton fabEdit = new FloatingActionButton(this);
        fabEdit.setImageResource(R.drawable.edit);
        fabEdit.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.buttonBackgroundColor)));
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EDT[0]) {
                    // Если редактирование уже включено, отключите его
                    editText.setEnabled(false);
                    EDT[0] = false;
                } else {
                    // Если редактирование выключено, включите его
                    editText.setEnabled(true);
                    EDT[0] = true;
                }
            }
        });

        // Добавление элементов во внутренний LinearLayout
        innerLinearLayout.addView(textInputLayout);
        innerLinearLayout.addView(fabDelete);
        innerLinearLayout.addView(fabEdit);

        // Добавление внутреннего LinearLayout в CardView
        cardView.addView(innerLinearLayout);

        // Добавление CardView в ваш контейнер
        linearLayout.addView(cardView, 0);
        cardCounter++;
    }
}

