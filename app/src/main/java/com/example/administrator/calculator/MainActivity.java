package com.example.administrator.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Spinner type, bote, smod;
    private EditText fm;
    private TextView v1, v2;
    private String type1, bt, smod1;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button2);
        v1 = (TextView) findViewById(R.id.result10);
        v2 = (TextView) findViewById(R.id.result16);
        type = (Spinner) findViewById(R.id.Type);
        fm = (EditText) findViewById(R.id.fm);
        bote = (Spinner) findViewById(R.id.bote);
        smod = (Spinner) findViewById(R.id.smod);
        btn.setOnClickListener(new BtnListener());
        type.setOnItemSelectedListener(new FListener());
        type.setVisibility(View.VISIBLE);
        bote.setOnItemSelectedListener(new SListener());
        bote.setVisibility(View.VISIBLE);
        smod.setOnItemSelectedListener(new TListener());
        smod.setVisibility(View.VISIBLE);
    }

    class FListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            type1 = (String) type.getSelectedItem();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class SListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            bt = (String) bote.getSelectedItem();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class TListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            smod1 = (String) smod.getSelectedItem();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View arg0) {
            jisuan();
        }
    }

    private void jisuan() {
        double s = Double.valueOf(smod1); //somd
        int b = Integer.valueOf(bt); //波特率
        String f1 = fm.getText().toString().trim();
        if (!"".equals(f1) && isNumber(f1)) {
            double f = Double.valueOf(f1) * 1000000; //频率
            double q = (f * Math.pow(2, s)) / (32 * b);
            result = String.valueOf((3072 - q) / 12);
            v1.setText(result);
            int w = (new Double(Double.valueOf(result)).intValue());
            v2.setText(Integer.toHexString(w));
        } else {
            Toast.makeText(this, "输入不合法！！", Toast.LENGTH_SHORT).show();
            fm.setText("");
        }
    }

    private boolean isNumber(String str) {
        char c[] = str.toCharArray();
        int con = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '.') {
                con++;
            }
        }
        if (con == 0) {
            str = str + ".0";
        }
        String string[] = str.split("\\.");
        if (string.length > 2)
            return false;
        if (string.length == 2) {
            if (!isNumeric2(string[1]))
                return false;
            if (!isNumeric(string[0]))
                return false;
        } else {
            return false;
        }

        return true;
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(0) == '0')
                return false;
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumeric2(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
