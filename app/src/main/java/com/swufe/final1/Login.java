package com.swufe.final1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

//1. 登录
//昵称不能为空或者空格——>随便，只是一个名字，输不输入都无所谓

public class Login extends AppCompatActivity {
    Button button;
    EditText editText;
    private static final String TAG = "Login1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.main_et_username);
        button = (Button) findViewById(R.id.main_btn);

        //当点击按钮时：
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, InputWordsActivity.class);
                Bundle bundle = new Bundle();

                Log.i(TAG,editText.getText().toString());

                //昵称可以为空
                if(editText.getText().toString().equals("")) {
                    //得等一会儿就会消失了
                    Toast.makeText(Login.this,"请为自己起一个好听的名字吧~",Toast.LENGTH_LONG).show();
                    //不输入昵称也可以正常跳转
                    startActivity(intent);
                }else{
                    //通过bundle传给指定页面
                    bundle.putString("name", editText.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}
