package hellochartsdemo.xr.mvpchartdemo;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */

public class LineChartManager {
    private LineChart lineChart;
    private YAxis leftAxis;   //左边Y轴
    private YAxis rightAxis;  //右边Y轴
    private XAxis xAxis;      //X轴

    public LineChartManager(LineChart mLineChart) {
        this.lineChart = mLineChart;
        leftAxis = lineChart.getAxisLeft();

        //rightAxis = lineChart.getAxisRight()
        //隐藏右边的y轴
         lineChart.getAxisRight().setEnabled(false);
        xAxis = lineChart.getXAxis();
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
//        lineChart.setDrawGridBackground(false);
//        //显示边界
//        lineChart.setDrawBorders(true);
        lineChart.setDragEnabled(false);// 拖拽禁止
        lineChart.setScaleEnabled(false);// 缩放禁止
//         隐藏纵横网格线
//        lineChart.getXAxis().setDrawGridLines(false);
//        lineChart.getAxisLeft().setDrawGridLines(false);
//        lineChart.getAxisRight().setDrawGridLines(false);
        //设置动画效果
        lineChart.animateY(1000, Easing.EasingOption.Linear);
        lineChart.animateX(1000, Easing.EasingOption.Linear);
        //XY轴描述
//        lineChart.setX

        //折线图例 标签 设置
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置(描述性信息）
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        String[] values = new String[]{
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov"
        };
        MyXFormatter xformatter = new MyXFormatter(values);
        //自定义x轴显示内容
        xAxis.setValueFormatter(xformatter);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
//        MyYFormatter yformatter = new MyYFormatter();
//        //自定义y轴格式
//        leftAxis.setValueFormatter(yformatter);
        //rightAxis.setAxisMinimum(0f);
    }

    /**
     * 初始化曲线 每一个LineDataSet代表一条线
     *
     * @param lineDataSet
     * @param color
     * @param mode        折线图是否填充
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, boolean mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(9f);
        //设置折线图填充
        lineDataSet.setDrawFilled(mode);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        //线模式为圆滑曲线（默认折线）
       // lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //线的模式为直线
       lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        //线的模式为走势图
         //lineDataSet.setMode(LineDataSet.Mode.STEPPED);


    }

    /**
     * 展示折线图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showLineChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initLineChart();
        ArrayList<Entry> entries = new ArrayList<>();
            for (int i = 0; i < xAxisValues.size(); i++) {
           entries.add(new Entry(xAxisValues.get(i), yAxisValues.get(i)));
          // entries.add

        }
        LineDataSet lineDataSet = new LineDataSet(entries, label);
        initLineDataSet(lineDataSet, color, true);
//        lineDataSet.setHighlightEnabled(false);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
//        ArrayList<String> xValues = new ArrayList<String>();
//        for (int i = 0; i < 11; i++) {
//            // x轴显示的数据，这里默认使用数字下标显示
//            xValues.add((i) + ":00");
//        }
//        LineData lineData = new LineData(xValues, dataSets);
//        //设置X轴的刻度数
        xAxis.setLabelCount(xAxisValues.size(), true);
//        LineData lineData = new LineData(xValues, dataSets);
        lineChart.setData(data);
    }

    /**
     * 展示线性图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues 多条曲线Y轴数据集合的集合
     * @param labels
     * @param colours
     */
    public void showLineChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                if (j >= xAxisValues.size()) {
                    j = xAxisValues.size() - 1;
                }
                entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
//            LineDataSet lineDataSet = new LineDataSet(yValues, "测试折线图" /*显示在比例图上*/);
            LineDataSet lineDataSet = new LineDataSet(entries, labels.get(i));
//            LineDataSet lineDataSet = new LineDataSet(xValues, labels.get(i));
            initLineDataSet(lineDataSet, colours.get(i), false);
            dataSets.add(lineDataSet);
        }
        LineData data = new LineData(dataSets);
        xAxis.setLabelCount(xAxisValues.size(), true);
        lineChart.setData(data);
    }

    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

     //  rightAxis.setAxisMaximum(max);
       // rightAxis.setAxisMinimum(min);
       // rightAxis.setLabelCount(labelCount, false);
        lineChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, true);
        lineChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(2f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
//        description.set
        lineChart.setDescription(description);

        lineChart.invalidate();
    }

    /**
     * 自定义x轴数值
     */
    class MyXFormatter  implements IAxisValueFormatter {
        private String[] mValues;

        public MyXFormatter(String[] values) {
            this.mValues = values;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value % mValues.length];
        }
    }

    /**
     * 自定义y轴数值
     *
     */

    class MyYFormatter  implements IAxisValueFormatter {
//        private String[] mValues;

//        public MyYFormatter(String[] values) {
//            this.mValues = values;value
//        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return value+"℃";
        }
    }


}
