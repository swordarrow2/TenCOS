package com.meng.tencos;

import android.app.*;
import android.content.*;
import android.os.*;

import com.meng.tencos.lib.SharedPreferenceHelper;
import com.meng.tencos.utils.*;
import com.meng.tencos.ui.*;

public class FirstActivity extends Activity{

    public static SharedPreferenceHelper sharedPreference;
	public static boolean lightTheme = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //  ExceptionCatcher.getInstance().init(this);
        sharedPreference=new SharedPreferenceHelper(this,"main");
        lightTheme=sharedPreference.getBoolean("useLightTheme",true);

        startActivity(new Intent(FirstActivity.this,MainActivity.class));
		finish();
    }
}
