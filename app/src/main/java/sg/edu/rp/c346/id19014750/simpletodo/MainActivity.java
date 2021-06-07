package sg.edu.rp.c346.id19014750.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddDel;
    EditText etInput;
    Button btnAdd, btnDel, btnClear;
    ListView lvToDo;
    ArrayList<String> alToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddDel = findViewById(R.id.spinner);
        etInput = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        btnClear = findViewById(R.id.btnClear);
        lvToDo = findViewById(R.id.listViewToDo);

        alToDo = new ArrayList<String>();

        ArrayAdapter aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etInput.setHint(getString(R.string.hintAdd));
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        break;
                    case 1:
                        etInput.setHint(getString(R.string.hintDel));
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toDo = etInput.getText().toString();
                alToDo.add(toDo);
                aaToDo.notifyDataSetChanged();

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = Integer.parseInt(etInput.getText().toString());

                if(alToDo.isEmpty()){
                    String text = getString(R.string.listEmpty);
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                }
                else if(index > alToDo.size() - 1 || index < 0) {
                    String text = getString(R.string.wrongNo);
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                }
                else{
                    alToDo.remove(index);
                    aaToDo.notifyDataSetChanged();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInput.setText("");
            }
        });

    }
}