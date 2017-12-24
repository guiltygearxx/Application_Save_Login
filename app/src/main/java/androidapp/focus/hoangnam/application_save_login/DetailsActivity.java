package androidapp.focus.hoangnam.application_save_login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HoangNam on 12/24/2017.
 */

public class DetailsActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        dbHelper = new DBHelper(this);

        txtMessage = findViewById(R.id.txt_user);
    }

    @Override
    protected void onStart() {

        super.onStart();

        txtMessage.setText("Hello " + dbHelper.getStoredData("username"));
    }

    private void clearLoginData() {

        dbHelper.clearStoredData("token");

        dbHelper.clearStoredData("username");
    }

    public void logout(View view) {

        this.clearLoginData();

        this.finish();
    }
}