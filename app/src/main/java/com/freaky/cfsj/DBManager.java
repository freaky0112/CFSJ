package com.freaky.cfsj;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Freaky on 2014/8/2.
 */
public class DBManager extends Activity{

    private final int BUFFER_SIZE=400000;

    private static final int FILE_SELECT_CODE = 0;
    private Uri uri;
    private SQLiteDatabase database;
    private Context context;

    DBManager(Context context){
        this.context=context;
    }

    public void openDatabase(){
        this.database=this.openDatabase(Common.DB_PATH+"/"+Common.DB_NAME);
    }

    private SQLiteDatabase openDatabase(String dbfile){
        try{
            if (!(new File(dbfile).exists())) {//判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                showFileChooseer();
                InputStream is =new FileInputStream(uri.toString());
                        //this.context.getResources().openRawResource(R.raw.countries); //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
                    null);
            return db;
        }catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }

    public void closeDatabase() {
        this.database.close();
    }
    /** 调用文件选择软件来选择数据源文件**/
    private  void showFileChooseer(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try{
            startActivityForResult(Intent.createChooser(intent, "请选择要导入的源文件"), FILE_SELECT_CODE);
        }catch (ActivityNotFoundException ex){
            Toast.makeText(this,"请安装文件管理器",Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        if(resultCode==RESULT_OK){
            uri=data.getData();
        }
    }

}
