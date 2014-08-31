package com.tonny.myapps.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CalculatorActivity extends Activity {


    Button sum;
    Button sub;
    Button multiply;
    Button divide;
    Button clear;
    EditText userInput;
    TextView result;
    TextView track;
    static StringBuffer trackHistory = new StringBuffer();
    static double total = 0;
    static boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        sum = (Button) findViewById(R.id.sum);
        sub = (Button) findViewById(R.id.sub);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        clear = (Button) findViewById(R.id.clear);
        userInput = (EditText) findViewById(R.id.userInput);
        result = (TextView) findViewById(R.id.result);
        track = (TextView) findViewById(R.id.track);
        track.setMovementMethod(new ScrollingMovementMethod());
        sum.setOnClickListener(click);
        sub.setOnClickListener(click);
        multiply.setOnClickListener(click);
        divide.setOnClickListener(click);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = 0;
                isFirst = true;
                trackHistory = new StringBuffer();
                track.setText("");
                userInput.setText("");
                result.setText("Start Over");
            }
        });
    }

    public View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!isEmpty(userInput)) {
                String a = userInput.getText().toString().trim();
                if (isDigit(a)) {
                    double input = Double.parseDouble(a);
                    if (isFirst) {
                        total = input;
                        trackHistory.append("    " + total + "\n");
                        userInput.setText("");
                        track.setText(trackHistory);
                        isFirst = false;
                        return;
                    }
                    switch (view.getId()) {
                        case R.id.sum:
                            trackHistory.append("+    ");
                            total = total + input;
                            result.setText("Here is your Result : " + total);
                            break;
                        case R.id.sub:
                            trackHistory.append("-    ");
                            total = total - input;
                            result.setText("Here is your Result : " + total);
                            break;
                        case R.id.multiply:
                            trackHistory.append("x    ");
                            total = total * input;
                            result.setText("Here is your Result : " + total);
                            break;
                        case R.id.divide:
                            trackHistory.append("/    ");
                            total = total / input;
                            result.setText("Here is your Result : " + total);
                            break;
                        default:
                            result.setText("OOPS :( ");
                            break;
                    }
                    userInput.setText("");
                    trackHistory.append(input + "\n" + "-----------------" + "\n" + "     " + total + "\n");
                    track.setText(trackHistory);
                } else {
                    result.setText("You still Kidding me ;)");
                }
            } else {
                result.setText("Are You Kidding me ;)");
            }
        }
    };

    private boolean isEmpty(EditText etVar) {
        return null == etVar || null == etVar.getText() || etVar.getText().toString().isEmpty();
    }

    private boolean isDigit(String var) {
        if (null == var || var.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(var);
        return matcher.matches();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
