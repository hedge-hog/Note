package com.hedgehog.note.ui;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.hedgehog.note.dao.DaoMaster;
import com.hedgehog.note.dao.DaoSession;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by hedge_hog on 16/2/27.
 */
public class BaseApplication extends Application {

    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();

        Logger.init("SimpleNote")               // default PRETTYLOGGER or use just init()
                .methodCount(1)                 // default 2
                //.hideThreadInfo()               // default shown
                //.logLevel(LogLevel.NONE)        // default LogLevel.FULL
                .logLevel(LogLevel.FULL)
                .methodOffset(5)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional
    }


    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
