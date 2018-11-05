package com.meng.tencos.lib;

import android.widget.Toast;

import com.meng.tencos.ui.MainActivity;

public class log{

    public static void e(final Object o){
        if(o instanceof Exception){
            ((Exception)o).printStackTrace();
        }
        MainActivity.instence.runOnUiThread(new Runnable(){

				@Override
				public void run(){
					// TODO: Implement this method
					Toast.makeText(MainActivity.instence,"发生错误:"+o.toString(),Toast.LENGTH_SHORT).show();
					i("发生错误:"+o.toString());
				}
			});
    }


    public static void i(final Object o){
        MainActivity.instence.runOnUiThread(new Runnable(){

				@Override
				public void run(){
					// TODO: Implement this method
					MainActivity.instence.rightText.setText(
                        MainActivity.instence.rightText.getText().toString()+
						o.toString()+"\n"
					);
				}
			});
    }

    public static void t(final Object o){
        MainActivity.instence.runOnUiThread(new Runnable(){

				@Override
				public void run(){
					// TODO: Implement this method
					Toast.makeText(MainActivity.instence,o.toString(),Toast.LENGTH_SHORT).show();
					i(o.toString());
				}
			});
    }
}
