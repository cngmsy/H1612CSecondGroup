package com.jiyun.qcloud.dashixummoban.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jiyun.qcloud.dashixummoban.dom.DaoMaster;
import com.jiyun.qcloud.dashixummoban.dom.DaoSession;
import com.jiyun.qcloud.dashixummoban.dom.SousuoleiDao;

/**
 * Created by KING on 2017/8/16 16:20
 * 邮箱:992767879@qq.com
 */

public class DanliGreen {
    private static DanliGreen danli;
    private final DaoMaster.DevOpenHelper devOpenHelper;

    private DanliGreen(Context context){
        devOpenHelper = new DaoMaster.DevOpenHelper(context, "sousuo.db");
    }
    public static DanliGreen geiIntence(Context context){
        if(danli==null){
            synchronized (DanliGreen.class){
                if(danli==null){
                    danli=new DanliGreen(context);
                }
            }
        }
        return danli;
    }
    public SQLiteDatabase reader(){
        SQLiteDatabase readableDatabase = devOpenHelper.getReadableDatabase();
        return readableDatabase;
    }
    public SQLiteDatabase writer(){
        return devOpenHelper.getWritableDatabase();
    }
    public SousuoleiDao getDaor(){
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        SousuoleiDao sousuoleiDao = daoSession.getSousuoleiDao();
        return sousuoleiDao;
    }
    public SousuoleiDao getDaow(){
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        SousuoleiDao sousuoleiDao = daoSession.getSousuoleiDao();
        return sousuoleiDao;
    }

}
