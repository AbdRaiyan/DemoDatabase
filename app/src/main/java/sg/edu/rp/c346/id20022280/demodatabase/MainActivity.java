package sg.edu.rp.c346.id20022280.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnGetTask;
    TextView tvResults;
    EditText etTitle,etDate;
    ListView lvTasks;

    ArrayList<String> alTasks = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTask = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lvTasks = findViewById(R.id.lvTasks);
        etDate = findViewById(R.id.editTextDate);
        etTitle = findViewById(R.id.editTextTitle);

        ArrayAdapter<String> aaTasks = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,alTasks);
        lvTasks.setAdapter(aaTasks);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(etTitle.getText().toString(),etDate.getText().toString());
            }
        });

        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<Task> data = db.getTask();
                db.close();

                aaTasks.clear();

                for (int i = 0; i < data.size(); i++) {
                    alTasks.add(data.get(i).toString());

                }
                aaTasks.notifyDataSetChanged();

            }
        });
    }
}