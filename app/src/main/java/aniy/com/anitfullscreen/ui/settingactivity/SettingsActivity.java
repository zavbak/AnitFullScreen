package aniy.com.anitfullscreen.ui.settingactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;

import aniy.com.anitfullscreen.ConstantManager;
import aniy.com.anitfullscreen.R;

public class SettingsActivity extends Activity {

    SharedPreferences mSharedPreferences;
    EditText edUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        edUrl = (EditText) findViewById(R.id.edUrl);

        mSharedPreferences = getSharedPreferences(ConstantManager.APP_NAME_PREF,MODE_PRIVATE);
        String strUrl = mSharedPreferences.getString(ConstantManager.URL_PREF, "https://www.google.ru");
        edUrl.setText(strUrl);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences.Editor ed = mSharedPreferences.edit();
        ed.putString(ConstantManager.URL_PREF, edUrl.getText().toString());
        ed.commit();


    }
}
