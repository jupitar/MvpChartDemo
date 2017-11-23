package hellochartsdemo.xr.mvpchartdemo;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/11/23.
 */

public class LineChartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        LineChart lineChart1 = (LineChart) findViewById(R.id.line_chart1);
        LineChart lineChart2 = (LineChart) findViewById(R.id.line_chart2);
        LineChartManager lineChartManager1 = new LineChartManager(lineChart1);
        LineChartManager lineChartManager2 = new LineChartManager(lineChart2);
        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()

        List<List<Float>> yValues = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j <= 10; j++) {
                float data=(float) (Math.random() * 80);
                yValue.add(data);
            }
            yValues.add(yValue);
        }

        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);
        colours.add(Color.RED);
        colours.add(Color.CYAN);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("折线一");
        names.add("折线二");
        names.add("折线三");
        names.add("折线四");
        //获取最大值
         float max=getMax(yValues,0);
         //获取最小值
         float min=getMin(yValues,0);
        //创建多条折线的图表
        lineChartManager1.showLineChart(xValues, yValues.get(0), names.get(1), colours.get(3));
//        lineChartManager1.setDescription("温度");
        //lineChartManager1.setYAxis(100, 0, 11);
        lineChartManager1.setYAxis(max, 0, (int)((max-min)/10));
        lineChartManager1.setHightLimitLine(70,"高温报警",Color.RED);
        max=getMax(yValues,1);
        min=getMin(yValues,1);
        lineChartManager2.showLineChart(xValues, yValues, names, colours);
        lineChartManager2.setYAxis(max+10, min-1,  yValues.get(0).size());
//        lineChartManager2.setDescription("温度");
    }

    private float getMax(List<List<Float>> datas,int flag) {
        float max = datas.get(0).get(0);
        if(flag==1) {
            for (List<Float> data : datas) {
                for (Float num : data)
                    if (num > max)
                        max = num;

            }
        }
        if(flag==0){
            for(Float num:datas.get(0))
                if (num > max)
                    max = num;

        }
        return max;

    }

    private float getMin(List<List<Float>> datas,int flag){
        float min=datas.get(0).get(0);

        if(flag==1){
            for(List<Float> data:datas){
                for(Float num:data)
                    if(num<min)
                        min=num;

            }
        }if(flag==0){
            for(Float num:datas.get(0))
                if (num < min)
                    min = num;
        }

        return min;

    }


}
