package myself.exercise.chuankou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jerome.serialport.SerialPortUtil;

public class MainActivity extends AppCompatActivity {
    private Button send;
    SerialPortUtil spu;
    String b="红露梦";
    byte[] b1 = b.getBytes();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button) findViewById(R.id.button);
        spu = SerialPortUtil.getInstance();
//        try {
//            b = URLEncoder.encode("红楼梦","UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spu.sendBuffer(b1);
            }
        });

        spu.setOnDataReceiveListener(new SerialPortUtil.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                Log.e("aaa", "onDataReceive: Size="+size );
//
//                try {
//                    b =  URLEncoder.encode(new String(buffer),"GBK");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }

                Log.e("aaa", "onDataReceive: ="+new String(buffer,0,size) );
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        spu.closeSerialPort();
    }
}
