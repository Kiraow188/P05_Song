package sg.edu.rp.c347.p05_song;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
                int selectedButtonId = rgStars.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedButtonId);
                Integer stars = Integer.parseInt(rb.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                long row_affected = dbh.insertSong(title, singers, year, stars);
                dbh.close();
                if (row_affected != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                    etTitle.setText("");
                    etSingers.setText("");
                    etYear.setText("");
                    rgStars.clearCheck();
                }
            }
        });
    }
}
