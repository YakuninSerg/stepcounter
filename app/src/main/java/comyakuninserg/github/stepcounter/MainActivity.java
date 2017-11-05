package comyakuninserg.github.stepcounter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    int stepCount;
    TextView countTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        countTextView=findViewById(R.id.count_textView);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepCounter==null)countTextView.setText("ER");
        else {
            countTextView.setText("0");
            sensorManager.registerListener(this,stepCounter,SensorManager.SENSOR_DELAY_FASTEST);
        }

    }

    public void onClick(View view) {
        stepCount=0;
        countTextView.setText(stepCount+"");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        stepCount++;
        countTextView.setText(stepCount+"");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
