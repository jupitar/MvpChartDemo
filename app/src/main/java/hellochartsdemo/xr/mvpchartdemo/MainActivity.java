package hellochartsdemo.xr.mvpchartdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button lineChart,zhuChart,pineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView(){
        lineChart=findViewById(R.id.lineChart);
        zhuChart=findViewById(R.id.zhuChart);
        pineChart=findViewById(R.id.pineChart);

    }

    private void setListener() {
        //折线图
        lineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LineChartActivity.class);
                startActivity(intent);

            }
        });

        //柱状图
        zhuChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,zhuChartActivity.class);
                startActivity(intent);
            }
        });

        //饼状图
        pineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
