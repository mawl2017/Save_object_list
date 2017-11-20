package com.example.ma.save_object_list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ma.save_object_list.entity.TestClass;
import com.example.ma.save_object_list.util.InputUtil;
import com.example.ma.save_object_list.util.OutputUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements View.OnClickListener{
    //写入本地
@Bind(R.id.btn_write_location)
    Button btn_write_location;
@Bind(R.id.btn_read_location)
Button btn_read_location;

//写入SD卡
@Bind(R.id.btn_write_SD)
Button btn_write_SD;
@Bind(R.id.btn_read_SD)
Button btn_read_SD;


//写入集合 到 SD卡
@Bind(R.id.btn_write_SD_list)
Button btn_write_SD_list;
@Bind(R.id.btn_read_SD_list)
Button btn_read_SD_list;

@Bind(R.id.tv_data)
    TextView tv_data;


TestClass bean;
List<TestClass> testes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btn_write_location.setOnClickListener(this);
        btn_read_location.setOnClickListener(this);

        btn_write_SD.setOnClickListener(this);
        btn_read_SD.setOnClickListener(this);

        btn_write_SD_list.setOnClickListener(this);
        btn_read_SD_list.setOnClickListener(this);

        bean=new TestClass("一号","女");

        testes=new ArrayList<TestClass>();
        testes.add(bean);
        testes.add(new TestClass("二号","女"));
        testes.add(new TestClass("三号","女"));
        testes.add(new TestClass("四号","女"));
        testes.add(new TestClass("五号","女"));

        TestClass bean12=new TestClass();
        bean12.setName("六号");
        bean12.setGender("女");
        testes.add(bean12);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_write_location:
                //写入 对象 到本地
                write_object_location(bean);
                break;
            case R.id.btn_read_location:
                //从本地 读取 对象
                read_object_local();
                break;

            case R.id.btn_write_SD:
            //将对象 写入 SD卡
                write_object_sd(bean);
                break;
            case R.id.btn_read_SD:
               //从 SD 卡 读取对象
                read_object_sd();
                break;

            case R.id.btn_write_SD_list:
            //将集合写入SD卡
                write_list_SD(testes);
                break;
            case R.id.btn_read_SD_list:
            //从 SD 卡 读取 集合
                read_list_SD();
                break;
        }
    }

    /**
     * 从SD卡 读取集合
     */
    private void read_list_SD() {
        List<TestClass> list = new InputUtil<TestClass>().readListFromSdCard("testlist1.out");
        if (list == null) {
            Toast.makeText(MainActivity.this, "SD卡读取 集合 失败", Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).getName());
                sb.append(list.get(i).getGender());
                tv_data.setText(list.get(i).getName()+",");
            }
            Toast.makeText(MainActivity.this, "SD卡读取 集合 成功="+sb, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 将集合写入SD卡
     * @param testes
     */
    private void write_list_SD(List<TestClass> testes) {
        if (new OutputUtil<TestClass>().writeListIntoSDcard("testlist1.out", testes)) {
            Toast.makeText(MainActivity.this, "集合写入SD卡成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "集合写入SD卡失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 从Sd 卡 读取对象
     */
    private void read_object_sd() {
        TestClass bean1 = new InputUtil<TestClass>().readObjectFromSdCard("test22.out");
        if (bean1 == null) {
            Toast.makeText(MainActivity.this, "SD卡读取 对象 失败", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "SD卡读取 对象 成功"+bean1.getName()+"-"+bean1.getGender(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
    *将对象 写入 SD卡
     */
    private void write_object_sd(TestClass bean) {
        if (new OutputUtil<TestClass>().writObjectIntoSDcard("test22.out", bean)) {
            Toast.makeText(MainActivity.this, "对象写入SD卡成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "对象写入SD卡失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 从本地读取 对象
     */
    private void read_object_local() {
        TestClass bean1 = new InputUtil<TestClass>().readObjectFromLocal(MainActivity.this, "test12.out");
        if (bean1 == null) {
            Toast.makeText(MainActivity.this, "本地读取 对象 失败", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "本地读取 对象 成功"+bean1.getName()+"-"+bean1.getGender(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 写入对象到 本地
     * @param bean
     */
    private void write_object_location(TestClass bean) {
        if (new OutputUtil<TestClass>().writeObjectIntoLocal(MainActivity.this, "test12.out", bean)) {
            Toast.makeText(MainActivity.this, " 对象 写入本地成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "写入本地失败", Toast.LENGTH_SHORT).show();
        }
    }
}
