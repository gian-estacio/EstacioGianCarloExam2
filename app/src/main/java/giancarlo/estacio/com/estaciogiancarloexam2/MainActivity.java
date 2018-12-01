package giancarlo.estacio.com.estaciogiancarloexam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;
    EditText eFname, eLname, eExam1, eExam2;
    TextView tAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("grade");
        eFname = findViewById(R.id.FN);
        eLname = findViewById(R.id.LN);
        eExam1 = findViewById(R.id.E1);
        eExam2 = findViewById(R.id.E2);
        tAverage = findViewById(R.id.AVG);

    }

    public void getAverage(View v) {
        String fname = eFname.getText().toString().trim();
        String lname = eLname.getText().toString().trim();
        Long exam1 = Long.parseLong(eExam1.getText().toString().trim());
        Long exam2 = Long.parseLong(eExam2.getText().toString().trim());
        Long average = (exam1 + exam2) / 2;
        Student sgrade = new Student(fname,lname,average);
        String key = root.push().getKey();
        root.child(key).setValue(sgrade);

        Query query = root.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    tAverage.setText(child.child("average").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Toast.makeText(this,"Student's Average has been computed",Toast.LENGTH_LONG).show();
    }
}
