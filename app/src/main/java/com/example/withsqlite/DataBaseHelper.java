package com.example.withsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String STUTDY_SQLITE_TABLE = "STUTDY_SQLITE_TABLE";
    // ----列名
    public static final String COLUNM_NAME = "COLUNM_NAME";
    public static final String COLUNM_PHONE = "COLUNM_PHONE";
    public static final String COLUNM_PASSWORD = "COLUNM_PASSWORD";

    //构造函数参数
    // context 帮助找到数据库
    // 数据库的名称
    // 游标工厂对象 可以为null (作用是什么？)
    // 版本号
    public DataBaseHelper(@Nullable Context context) {
        // 写死传递给父类构造函数的参数
        super(context, "database.db", null, 1);
    }

    @Override
    // 第一次尝试访问数据库对象时会调用该方法创建新表
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建新表的语句
        String createNewTableSqlStr = "CREATE TABLE " + STUTDY_SQLITE_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COLUNM_NAME + " TEXT," + COLUNM_PHONE + " TEXT," + COLUNM_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(createNewTableSqlStr);

    }

    @Override
    // 当数据库的版本号发生变化时会调用该方法
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // 添加语句
    public boolean addOne(UserBean userBean) {
        //获取要编写的唯一一个数据库
        /* getWritableDatabase SQLiteOpenHelper中的方法*/
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // 有点像flutter里的键值对
        ContentValues cv = new ContentValues();
        cv.put(COLUNM_NAME, userBean.getName());
        cv.put(COLUNM_PHONE, userBean.getPhone());
        cv.put(COLUNM_PASSWORD, userBean.getPassWord());

        // 参数：1.表名 2.第二个参数是什么？ 3。内容
        long insert = sqLiteDatabase.insert(STUTDY_SQLITE_TABLE, null, cv);
        return insert != -1;
    }

}
