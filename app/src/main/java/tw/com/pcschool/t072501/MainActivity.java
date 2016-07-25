package tw.com.pcschool.t072501;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener{
    private TextView m_tv_message;
    private int w=-1;
   boolean b[] = new boolean[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    //建立一個 init 為出現文字
    public void init(){
        m_tv_message = (TextView)findViewById(R.id.tv_message);
    }
    //按下主畫面案鈕
    public void btnA(View v)
    {
        new AlertDialog.Builder(this)
                .setMessage("你好帥")
                .setPositiveButton("我知道", this)//將此案鈕 OnClick 事件委託給當前的 Activity (做居中協調)
                .show();
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        m_tv_message.setText("我知道");

    }
    public void btnB(View v)
    {
     AlertDialogYesNoListener listener =new AlertDialogYesNoListener();
    new AlertDialog.Builder(this)
            .setMessage("你好帥")
            .setPositiveButton("謝謝!!", listener)
            .setNegativeButton("少狗腿", listener)
            .show();
    }
    public class AlertDialogYesNoListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText("謝謝");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("少狗腿");
                    break;
            }
        }
    }
    //老師的方法
    public void btnC(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("請說實話");
        builder.setMessage("這是訊息");

        final EditText ed = new EditText(MainActivity.this);
        builder.setView(ed);

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = ed.getText().toString();
                m_tv_message.setText("你比較像許效舜");
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
    //老師的方法
    public void btnD(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("喜歡的水果");
        final String[]  fruits=  {"蘋果","水梨","芭樂"};
        builder.setSingleChoiceItems(fruits, w, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    w=which;
            }
        });

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_tv_message.setText(fruits[w]);
                // Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
    public void btnE(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("多選項對話框");
        final String[] sweets = {"花生", "紅豆", "綠豆"};
        builder.setMultiChoiceItems(sweets, b, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        b[which] = isChecked;
            }
        });


        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = "";
                for(int i=0; i<=2;i++)
                {
                    if (b[i]){
                        str = str + sweets[i];
                    }
                    m_tv_message.setText(str);
                }
                // tv.setText(fruits[w]);
                // Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

}
