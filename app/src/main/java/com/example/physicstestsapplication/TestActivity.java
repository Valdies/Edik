package com.example.physicstestsapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    private Question[] questions;
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Тут тоже самое как в прошлый раз делал. Я чуть чуть изменил сам подход. Пишешь сначала вопрос, потом варианты ответов и правильный ответ, я просто цифру написал
        questions = new Question[]{
                //типо вот                саначала вопрос,                                                тут варианты ответов           и цифра правильного ответа типо 0-первая кнопка, 1-вторая, 2-третья.
                new Question("у меня болезнь....", new String[]{"СПИД", "Зоофилия", "Федооок"}, 0),
                new Question("Ответ 1", new String[]{"Золото", "Кислород", "Серебро"}, 1),
                new Question("Какова скорость света в вакууме?", new String[]{"300 000 km/s", "150 000 km/s", "600 000 km/s"}, 0),
                new Question("Кто написал 'Войну и мир'?", new String[]{"Достоевский", "Лев Толстой", "Пушкин"}, 1),
                new Question("Какой газ является основным компонентом атмосферы Земли?", new String[]{"Кислород", "Азот", "Углекислый газ"}, 1),
                new Question("Что такое гравитация?", new String[]{"Сила притяжения", "Электромагнитная сила", "Сила трения"}, 0),
                new Question("Какое число π (Пи) с двумя знаками после запятой?", new String[]{"3.14", "3.15", "3.13"}, 0),
                new Question("Как называется процесс, при котором растения производят кислород?", new String[]{"Фотосинтез", "Гравитация", "Хемосинтез"}, 0),
                new Question("Какой орган человеческого тела отвечает за дыхание?", new String[]{"Сердце", "Легкие", "Печень"}, 1),
                new Question("Как зовут человечка, который живет на Луне?", new String[]{"Браво", "Лунтик", "Кот"}, 1),
                new Question("Какой минерал является самым твердым?", new String[]{"Кварц", "Алмаз", "Слюда"}, 1),
                new Question("Что такое фотон?", new String[]{"Частица света", "Частица материи", "Частица гравитации"}, 0),
                new Question("Какой континент самый большой?", new String[]{"Африка", "Азия", "Америка"}, 1),
                new Question("Какой элемент не является металлом?", new String[]{"Железо", "Кальций", "Кислород"}, 2),
                new Question("Какой цвет имеет морская волна?", new String[]{"Синий", "Зеленый", "Прозрачный"}, 0),
                new Question("Как называется процесс превращения жидкости в газ?", new String[]{"Конденсация", "Испарение", "Сублимация"}, 1),
                new Question("Кто разработал теорию относительности?", new String[]{"Ньютон", "Эйнштейн", "Коперник"}, 1),
                new Question("Какой период в русском языке следует за предложением?", new String[]{"Точка", "Запятая", "Вопросительный знак"}, 0),
                new Question("Какой стиль музыки считается классической?", new String[]{"Рок", "Классическая музыка", "Поп"}, 1),
                new Question("На каком языке написан 'Гамлет'?", new String[]{"Немецкий", "Французский", "Английский"}, 2),
                new Question("Какой элемент таблицы Менделеева обозначается буквой 'H'?", new String[]{"Гелий", "Водород", "Углерод"}, 1),
                new Question("Какое озеро самое глубокое в мире?", new String[]{"Каспийское море", "Байкал", "Северное море"}, 1)
        };

        // Выводит тебе первый вопрос
        showQuestion();
    }

    private void showQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question currentQuestion = questions[currentQuestionIndex];

            TextView testTextView = findViewById(R.id.test_text_view);
            testTextView.setText(currentQuestion.getQuestionText());

            Button answerButton1 = findViewById(R.id.answer_button_1);
            Button answerButton2 = findViewById(R.id.answer_button_2);
            Button answerButton3 = findViewById(R.id.answer_button_3);

            answerButton1.setText(currentQuestion.getAnswers()[0]);
            answerButton2.setText(currentQuestion.getAnswers()[1]);
            answerButton3.setText(currentQuestion.getAnswers()[2]);

            answerButton1.setOnClickListener(v -> checkAnswer(0));
            answerButton2.setOnClickListener(v -> checkAnswer(1));
            answerButton3.setOnClickListener(v -> checkAnswer(2));
        } else {
            // Заканцичвает квест
            Toast.makeText(this, "Тест завершен!", Toast.LENGTH_SHORT).show();
            finish(); // Выход из активности ну или переход на другой экран
        }
    }

    // Это всплывающие уведомления, они мне не оч нравятся. хочу сделать как в дуолинго, там капец красиво
    private void checkAnswer(int selectedAnswerIndex) {
        Question currentQuestion = questions[currentQuestionIndex];
        if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
            Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++; // Переход к следующему вопросу
        showQuestion(); // Показать следующий вопрос
    }
}


// Впринцепе на этом все, посмотри еще лайаут, там кнопки все. Их нада переделать           UwU
