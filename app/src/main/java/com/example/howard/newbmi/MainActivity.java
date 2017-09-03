package com.example.howard.newbmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private EditText etheight;
    private EditText etweight;
    private RadioButton rb1;
    private RadioButton rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String Sex = "";
                //從輸入介面中取出了的身高、體重值，要將身高、體重值傳送給 child_Activity 後作計算
                etheight = (EditText) MainActivity.this.findViewById(R.id.hight);
                etweight = (EditText) MainActivity.this.findViewById(R.id.weight);
                rb1 = (RadioButton) MainActivity.this.findViewById(R.id.male);
                rb2 = (RadioButton) MainActivity.this.findViewById(R.id.female);
                double height = Double.parseDouble(etheight.getText().toString());
                double weight = Double.parseDouble(etweight.getText().toString());
                if (rb1.isChecked()) {
                    Sex = "M";
                } else {
                    Sex = "F";
                }
                //這些附加在 Intent 上的訊息都儲存在 Bundle 物件中
                Intent it = new Intent();
                it.setClass(MainActivity.this, NewBMI2_child.class);
                //透過「intent.putExtras(bundle)」敘述，將「bundle」 物件附加在 Intent 上，隨著 Intent 送出而送出
                it.putExtra("height", height);
                it.putExtra("weight", weight);
                it.putExtra("Sex", Sex);
                MainActivity.this.startActivityForResult(it, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                String Sex = bundle.getString("Sex");
                double height = bundle.getDouble("height");
                double weight = bundle.getDouble("weight");
                etheight.setText("" + height);
                etweight.setText("" + weight);
                if (Sex.equals("M")) {
                    rb1.setChecked(true);
                } else {
                    rb2.setChecked(true);
                }
                break;
            default:
                break;
        }
    }
}
