package sg.edu.rp.c347.p05_song;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    Spinner spn;
    ListView lv;
    Button btn5stars;
    ArrayAdapter aa, aaf;
    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        btn5stars = findViewById(R.id.btn5Stars);
        spn = findViewById(R.id.spinner);

        DBHelper db = new DBHelper(this);
        db.getWritableDatabase();
        db.close();

        al = new ArrayList<Song>();
        lv = findViewById(R.id.lv);
        al = db.getAllSongs();

        final ArrayList<Song> data =  db.getAllSongs();
        final ArrayList<Song> five = db.getAllSongs(5);
        final ArrayList<String> year = db.getAllYear();
        db.close();

        aa = new CustomAdapter(this, R.layout.row, data);
        aaf = new CustomAdapter(this,R.layout.row, five);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, year);

        spn.setAdapter(adapter);
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Intent i = new Intent(DisplayActivity.this,
                        ThirdActivity.class);
                Song song = data.get(position);
                Log.d("The song is:", song.toString());
                String id = String.valueOf(song.getId());
                String title = song.getTitle();
                String singer = song.getSingers();
                String year = String.valueOf(song.getYear());
                String star = String.valueOf(song.getStars());

                Song target = new Song(Integer.parseInt(id), title, singer, Integer.parseInt(year), Integer.parseInt(star));
                i.putExtra("data", target);
                startActivityForResult(i, 9);
            }
        });

        btn5stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.setAdapter(aaf);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            lv.performClick();
        }
    }
}
