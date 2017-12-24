package androidapp.focus.hoangnam.application_save_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsername;

    private EditText txtPassword;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.ed_username);

        txtPassword = findViewById(R.id.ed_password);

        dbHelper = new DBHelper(this);
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (this.isLoggedIn()) this.goToLoginActivity();
    }

    private Boolean isLoggedIn() {

        String token = dbHelper.getStoredData("token");

        return token != null;
    }

    private void goToLoginActivity() {

        Intent intent = new Intent(this, DetailsActivity.class);

        startActivity(intent);
    }

    private Boolean validate() {

        String userName = txtUsername.getText().toString();

        String password = txtPassword.getText().toString();

        if (userName == null || userName.trim().isEmpty()) {

            Toast.makeText(this, "User chưa nhập", Toast.LENGTH_LONG).show();

            return false;
        }

        if (password == null || password.trim().isEmpty()) {

            Toast.makeText(this, "Password chưa nhập", Toast.LENGTH_LONG).show();

            return false;
        }

        return true;
    }

    private String generateToken(String username, String password) {

        return "This is a token present to username";
    }

    private void saveLoginData() {

        String userName = txtUsername.getText().toString();

        String password = txtPassword.getText().toString();

        String token = generateToken(userName, password);

        dbHelper.saveLoginData(userName, token);
    }

    public void login(View view) {

        if (!this.validate()) return;

        this.saveLoginData();

        this.goToLoginActivity();
    }
}
