package sg.edu.rp.c347.p05_song;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnAdd, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSingers = findViewById(R.id.etSingers);
        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnRetrieve);
        rgStars = findViewById(R.id.rg);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(i);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                Integer year = Integer.parseInt(etYear.getText().toString());
            }
        });
    }
}
