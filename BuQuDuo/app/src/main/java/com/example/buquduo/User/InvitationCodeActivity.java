package com.example.buquduo.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.buquduo.R;

public class InvitationCodeActivity extends AppCompatActivity {


    Button copyBtn;
    Button pasteBtn;
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

    }

    private void initData() {

    }

    private void setupTitleBar() {

    }


    private void updateUI() {

    }





}
