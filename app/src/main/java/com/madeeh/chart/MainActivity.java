package com.madeeh.chart;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    RelativeLayout layout;
    PieChart chart;

    float[] yData={20,30,5,15,32};
    String[] xData={"Ali","Khalid","Ibrahim","Khalil","Zaki"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        chart=(PieChart)findViewById(R.id.chart);

        chart.setDescription("This is a Demo");
        chart.setUsePercentValues(true);

        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if(entry==null){
                    return;
                }

                String msg=xData[entry.getXIndex()]+ "="+ entry.getVal() + "%";
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        ArrayList<Entry> yVals=new ArrayList<Entry>();
        ArrayList<String> xVals=new ArrayList<String>();

        for (int i=0;i<yData.length;i++){
            yVals.add(new Entry(yData[i],i));
            xVals.add(xData[i]);
        }


        PieDataSet ds=new PieDataSet(yVals,"Marks");
        ds.setSliceSpace(3);
        ds.setSelectionShift(5);

        PieData data=new PieData(xVals,ds);
        chart.setData(data);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);

        chart.invalidate();

    }

}
