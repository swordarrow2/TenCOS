package com.meng.tencos.ui;


import android.content.Context;

import com.meng.tencos.utils.Base64Util;
import com.tencent.cos.COSClient;
import com.tencent.cos.COSConfig;

import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.meng.tencos.FirstActivity.sharedPreference;


public class BizService{

    private static final String MAC_NAME="HmacSHA1";
    private static final String ENCODING="UTF-8";
    private static BizService bizService;
    private String appid;
    // private final String appid = "1257751754";
    //同一 APPID 下，存储桶数量上限是 200 个（不区分地域）。同一 APPID 所有项目下的存储桶名称唯一且不支持重命名。
    public String bucket;
    private String SecretId;
    private String SecretKey;
    public COSClient cosClient;
    private COSConfig config=new COSConfig();
    /**
     * 设置园区；根据创建的cos空间时选择的园区
     * 华南园区：gz 或 COSEndPoint.COS_GZ(已上线)
     * 华北园区：tj 或 COSEndPoint.COS_TJ(已上线)
     * 华东园区：sh 或 COSEndPoint.COS_SH
     */
    public String region;

    // public static BizService getInstance() {
    //    return bizService;
    // }
    public static synchronized BizService getInstance(Context c){
        if(null==bizService){
            bizService=new BizService(c);
        }
        return bizService;
    }

    public BizService(Context c){
		//如果过去到的值为空,则使用demo的值
        appid=sharedPreference.getValue("appid","1253703400");
        bucket=sharedPreference.getValue("bucketname","demo");
        SecretId=sharedPreference.getValue("secretid","AKIDe8QWEnIWby92mmgCEUZTF8qeKwQj91MV");
        SecretKey=sharedPreference.getValue("secretkey","l9NohaoxufEb0SMZPTM6g4GG3f1GRmGM");
        region=sharedPreference.getValue("region","gz");

        bizService=this;
        config.setEndPoint(region);
        cosClient=new COSClient(c,appid,config,null);
    }


    /*
    * 获取多次有效签名
    * */
    public String getSign(){
        try{
            String Original=getSignOriginal();
            byte[] HmacSHA1=HmacSHA1Encrypt(SecretKey,Original);
            byte[] all=new byte[HmacSHA1.length+Original.getBytes(ENCODING).length];
            System.arraycopy(HmacSHA1,0,all,0,HmacSHA1.length);
            System.arraycopy(Original.getBytes(ENCODING),0,all,HmacSHA1.length,Original.getBytes(ENCODING).length);
            String SignData=Base64Util.encode(all);
            return SignData;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "get sign failed";
    }

    /*
    * 获取单次有效签名
    * */
    public String getSignOnce(String cosPath){
        try{
            String Original=getSignOriginalOnce(cosPath);
            byte[] HmacSHA1=HmacSHA1Encrypt(SecretKey,Original);
            byte[] all=new byte[HmacSHA1.length+Original.getBytes(ENCODING).length];
            System.arraycopy(HmacSHA1,0,all,0,HmacSHA1.length);
            System.arraycopy(Original.getBytes(ENCODING),0,all,HmacSHA1.length,Original.getBytes(ENCODING).length);
            String SignData=Base64Util.encode(all);
            return SignData;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "get sign failed";
    }


    private String getSignOriginal(){
        return String.format(
                "a=%s&b=%s&k=%s&e=%s&t=%s&r=%s&f=",
                appid,
                bucket,
                SecretId,
                String.valueOf(getLinuxDateSimple()+60),
                String.valueOf(getLinuxDateSimple()),
                getRandomTenStr());
    }


    private String getSignOriginalOnce(String cosPath){
        return String.format(
                "a=%s&b=%s&k=%s&e=%s&t=%s&r=%s&f=%s",
                appid,
                bucket,
                SecretId,
                "0",
                String.valueOf(getLinuxDateSimple()),
                getRandomTenStr(),
                "/"+appid+"/"+bucket+"/"+cosPath);
    }


    private long getLinuxDateSimple(){
        try{
            long unixTimestamp=System.currentTimeMillis()/1000L;
            return unixTimestamp;
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    private String getRandomTenStr(){
        String randomstr=null;
        randomstr=String.valueOf(new Random().nextInt(8)+1);
        int random=new Random().nextInt(3)+5;
        for(int i=0;i<random;i++){
            randomstr+=String.valueOf(new Random().nextInt(9));
        }
        return randomstr;
    }

    /**
     * @param SecretKey   密钥
     * @param EncryptText 签名串
     */
    private byte[] HmacSHA1Encrypt(String SecretKey,String EncryptText) throws Exception{
        byte[] data=SecretKey.getBytes(ENCODING);
        javax.crypto.SecretKey secretKey=new SecretKeySpec(data,MAC_NAME);
        Mac mac=Mac.getInstance(MAC_NAME);
        mac.init(secretKey);
        byte[] text=EncryptText.getBytes(ENCODING);
        return mac.doFinal(text);
    }

}
