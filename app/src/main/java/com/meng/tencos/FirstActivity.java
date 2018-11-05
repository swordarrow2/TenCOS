package com.meng.tencos;

import android.app.*;
import android.content.*;
import android.os.*;

import com.meng.tencos.utils.*;
import com.meng.tencos.ui.*;

public class FirstActivity extends Activity{
	
	public static boolean lightTheme = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        lightTheme=BizService.sharedPreference.getBoolean("useLightTheme",true);

        startActivity(new Intent(FirstActivity.this,MainActivity.class));
		finish();
    }
}
