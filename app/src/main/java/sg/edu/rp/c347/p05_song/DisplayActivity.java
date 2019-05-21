package sg.edu.rp.c347.p05_song;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    ListView lv;
    Button btn5stars;
    ArrayAdapter aa, aaf;
    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        btn5stars = findViewById(R.id.btn5Stars);

        DBHelper db = new DBHelper(this);
        db.getWritableDatabase();
        db.close();

        al = new ArrayList<Song>();

        lv = findViewById(R.id.lv);

        al = db.getAllSongs();

        final ArrayList<Song> data =  db.getAllSongs();

        final ArrayList<Song> five = db.getAllSongs(5);


        db.close();

        aa = new CustomAdapter(this, R.layout.row, data);

        aaf = new CustomAdapter(this,R.layout.row, five);

        lv.setAdapter(aa);
        aa.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Intent i = new Intent(DisplayActivity.this,
                        ThirdActivity.class);
                Song datas = data.get(position);

                String id = datas.toString().split(",")[0].split(":")[1];
                String title = datas.toString().split(",")[1].trim();
                String singer = datas.toString().split(",")[2].trim();
                String year = datas.toString().split(",")[3].trim();
                String star = datas.toString().split(",")[4].trim();

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
