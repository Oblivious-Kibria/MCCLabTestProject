package abohawya.com.mcclabtestproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import abohawya.com.mcclabtestproject.model.DatabaseHandler;

public class MainActivity extends AppCompatActivity{

    EditText input_name, input_age, input_email, input_phone;
    TextView selectImage;
    Button submit, showUsers;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_name = (EditText) findViewById(R.id.input_name);
        input_age = (EditText) findViewById(R.id.input_age);
        input_email = (EditText) findViewById(R.id.input_email);
        input_phone = (EditText) findViewById(R.id.input_phone);
        selectImage = (TextView) findViewById(R.id.selectImage);
        submit = (Button) findViewById(R.id.submit);
        showUsers= (Button) findViewById(R.id.showUsers);
        image = (ImageView) findViewById(R.id.image);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                    DatabaseHandler db = new DatabaseHandler(getApplication());
                    String name = input_name.getText().toString();
                    int age = Integer.parseInt(input_age.getText().toString());
                    String email = input_name.getText().toString();
                    String phone = input_phone.getText().toString();
                    User user = new User(name, age, email, phone);
                    db.addContact(user);
                    Toast.makeText(MainActivity.this, "User Added Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplication(), UserList.class));
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String name = input_name.getText().toString();
        String age = input_age.getText().toString();
        String email = input_email.getText().toString();
        String phone = input_phone.getText().toString();


        if (name.isEmpty()) {
            input_name.setError("enter your name");
            valid = false;
        } else {
            input_name.setError(null);
        }

        if (age.isEmpty()) {
            input_age.setError("enter your age");
            valid = false;
        } else {
            input_age.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("enter a valid email address");
            valid = false;
        } else {
            input_email.setError(null);
        }

        if (phone.isEmpty()) {
            input_phone.setError("enter phone no");
            valid = false;
        } else {
            input_phone.setError(null);
        }

        return valid;
    }


}
