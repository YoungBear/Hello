package com.example.hello.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hello.R;
import com.example.hello.receiver.HomeKeyListener;

public class HomeKeyActivity extends Activity {
    private Context context;
    private HomeKeyListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_key);
        this.context = this;
        listener = new HomeKeyListener(this);

        listener.setOnHomeKeyPressListener(new HomeKeyListener.OnHomeKeyPressListener() {
            @Override
            public void onHomeKeyPress() {
                Toast.makeText(context, "按下了HOME键", Toast.LENGTH_SHORT).show();
            }
        });

        listener.setOnHomekeyLongPressListener(new HomeKeyListener.OnHomeKeyLongPressListener() {
            @Override
            public void onHomeKeyLongPress() {
                Toast.makeText(context, "长按了HOME键", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        listener.start();
        super.onStart();
    }

    @Override
    protected void onStop() {
        listener.stop();
        super.onStop();
    }
}
