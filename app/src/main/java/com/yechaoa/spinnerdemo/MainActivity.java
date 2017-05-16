package com.yechaoa.spinnerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private String[] list1;
    private ArrayList<String> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        list1 = new String[]{"1","2","3"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, list1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);

        list2 = new ArrayList<>();
        list2.add("Android");
        list2.add("IOS");
        list2.add("H5");
        spinner3.setAdapter(new MyAdapter());
    }

    /**
     * 初始化监听器
     */
    private void initListener() {
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
    }

    /**
     *
     * @param parent parent是你当前所操作的Spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner1:
                String[] letter = getResources().getStringArray(R.array.letter);
                Log.i("spinner1点击------",letter[position]);
                break;
            case R.id.spinner2:
                Log.i("spinner2点击------",list1[position]);
                break;
            case R.id.spinner3:
                Log.i("spinner3点击------",list2.get(position));
                break;
        }
    }

    /**
     * 没有数据的时候执行
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * 自定义的Adapter
     */
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list2.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder ;
            if(convertView==null){
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_text, viewGroup, false);
                holder = new ViewHolder();
                holder.itemText= (TextView) convertView.findViewById(R.id.item_text);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.itemText.setText(list2.get(position));
            return convertView;
        }
    }
    class ViewHolder {
        TextView itemText;
    }

}
