package com.example.threadpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        sb = new StringBuffer();
        sb.append("开始运行程序" + "\n");
        Log.d("TAG", "开始运行程序");
        Date date = new Date();
        int taskSize = 5;
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        List<Future> list = new ArrayList<Future>();

        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i + " ");
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(f);
        }
        pool.shutdown();
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            try {
                System.out.println(">>>" + f.get().toString());
                Date date2 = new Date();
                sb.append("----程序结束运行----，程序运行时间【"
                        + (date2.getTime() - date.getTime()) + "毫秒】" + "\n");
                System.out.println("----程序结束运行----，程序运行时间【"
                        + (date2.getTime() - date.getTime()) + "毫秒】");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        tv.setText(sb.toString());

    }

    private class MyCallable implements Callable {
        private final String taskNum;

        public MyCallable(String s) {
            this.taskNum = s;
        }

        @Override
        public Object call() throws Exception {
            sb.append(">>>" + taskNum + "任务启动" + "\n");
            Log.d("TAG", ">>>" + taskNum + "任务启动");
            Date dateTmp1 = new Date();
            Thread.sleep(1000);
            Date dateTmp2 = new Date();
            long time = dateTmp2.getTime() - dateTmp1.getTime();
            sb.append(">>>" + taskNum + "任务终止" + "\n");
            Log.d("TAG", ">>>" + taskNum + "任务终止");
            return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
        }
    }
}
