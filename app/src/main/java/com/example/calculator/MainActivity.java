package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.faendir.rhino_android.RhinoAndroidHelper;
import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton btnc,btnbracOp,btnbracclo,btndiv,btn7,btn8,btn9,btnmul,btn4,btn5,btn6,btnadd,
            btn1,btn2,btn3,btnsub,btnac,btn0,btnpoint,btnequal;
    TextView soltv1,restv;
    private Context context;
    private Scriptable scriptable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findid();


    }

    private void findid() {
        soltv1 = (TextView) findViewById(R.id.solution_et);
        restv = (TextView) findViewById(R.id.retult_et);

        btnc = (MaterialButton) findViewById(R.id.btn1);
        btnbracOp= (MaterialButton) findViewById(R.id.btn2);
        btnbracclo = (MaterialButton) findViewById(R.id.btn3);
        btndiv = (MaterialButton) findViewById(R.id.btn4);
        btn7 = (MaterialButton) findViewById(R.id.btn5);

        btn8 = (MaterialButton) findViewById(R.id.btn6);
        btn9 = (MaterialButton) findViewById(R.id.btn7);
        btnmul = (MaterialButton) findViewById(R.id.btn8);
        btn4 = (MaterialButton) findViewById(R.id.btn9);
        btn5 = (MaterialButton) findViewById(R.id.btn10);

        btn6 = (MaterialButton) findViewById(R.id.btn11);
        btnadd = (MaterialButton) findViewById(R.id.btn12);
        btn1 = (MaterialButton) findViewById(R.id.btn13);
        btn2 = (MaterialButton) findViewById(R.id.btn14);
        btn3 = (MaterialButton) findViewById(R.id.btn15);

        btnsub = (MaterialButton) findViewById(R.id.btn16);
        btnac = (MaterialButton) findViewById(R.id.btn17);
        btn0 = (MaterialButton) findViewById(R.id.btn18);
        btnpoint = (MaterialButton) findViewById(R.id.btn19);
        btnequal = (MaterialButton) findViewById(R.id.btn20);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnc.setOnClickListener(this);
        btnac.setOnClickListener(this);
        btndiv.setOnClickListener(this);
        btnmul.setOnClickListener(this);
        btnadd.setOnClickListener(this);
        btnsub.setOnClickListener(this);
        btnbracclo.setOnClickListener(this);
        btnbracOp.setOnClickListener(this);
        btnequal.setOnClickListener(this);
        btnpoint.setOnClickListener(this);
        btn0.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttontext = button.getText().toString();
        String calculate = soltv1.getText().toString();

        if(buttontext.equals("AC")){
            soltv1.setText("");
            restv.setText("0");
            return;
        }
        if(buttontext.equals("=")) {
            soltv1.setText(restv.getText());
            return;
        }
        if(buttontext.equals("C")) {
            if(calculate.isEmpty()){
                soltv1.setText("");
                restv.setText("0");
                return;
            }else{
                calculate = calculate.substring(0, calculate.length() - 1);
            }
        }else{
            calculate = calculate+buttontext;
        }
        soltv1.setText(calculate);

        String finalResult = getResults(calculate);
        if(!finalResult.equals("Err")){
            restv.setText(finalResult);
        }else if(finalResult.equals("org.mozilla.javascript.Undefined@0")) {
            restv.setText("0");
            return;
        }
        Log.d("TAG", "onClick: "+calculate);
        Log.d("TAG1", "finalresult: "+finalResult);
    }
    String getResults(String data){
        try{
            RhinoAndroidHelper rhinoAndroidHelper = new RhinoAndroidHelper(this);
             context = rhinoAndroidHelper.enterContext();
            ((org.mozilla.javascript.Context) context).setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",-1,null).toString();
            return finalResult;
        }catch(Exception e){
            Log.d("err", "getResults: "+e);
            return "Err";
        }
    }
}