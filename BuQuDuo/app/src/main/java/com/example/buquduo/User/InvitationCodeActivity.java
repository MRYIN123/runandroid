package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.buquduo.Base.MyTool;
import com.example.buquduo.Network.BQDHttpTool;
import com.example.buquduo.Network.MyBaseCallBack;
import com.example.buquduo.R;
import com.example.buquduo.bar.OnTitleBarListener;
import com.example.buquduo.bar.TitleBar;

public class InvitationCodeActivity extends AppCompatActivity {


    Button copyBtn;
    Button pasteBtn;
    Button ensureBtn;
    EditText inputTxt;
    TextView codeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_code);


        initView();

        initData();

        setupTitleBar();

        updateUI();
    }

    private void initView() {
        copyBtn = findViewById(R.id.copycodebtn);
        pasteBtn = findViewById(R.id.pastecodebtn);
        ensureBtn = findViewById(R.id.invicode_ensurebtn);
        codeTxt = findViewById(R.id.invitationcodeTxt);
        inputTxt = findViewById(R.id.invi_inputTxt);

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTool.copyAction();
            }
        });


        pasteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTool.pasteAction();
            }
        });
        ensureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ensureaction();
            }
        });

    }


    private void ensureaction() {
        BQDHttpTool.getShareInstance().get("", new MyBaseCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MyTool.makeToast(InvitationCodeActivity.this,e.getMessage());
            }

            @Override
            public void onResponse(Object response, int id) {
                MyTool.makeToast(InvitationCodeActivity.this,"输入成功");
                InvitationCodeActivity.this.finish();
            }
        });
    }

    private void initData() {

    }

    private void setupTitleBar() {
        ((TitleBar)findViewById(R.id.titlebar_invitation)).setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                InvitationCodeActivity.this.finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }


    private void updateUI() {

    }





}
