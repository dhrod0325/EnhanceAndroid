package kr.co.saweb.enhance.android.util.file;

import android.content.Context;

import java.io.File;

/**
 * Created by OKS on 2014-11-20.
 */
public class FileStorage extends AndroidFile {
    public static final String TAG = FileStorage.class.getName();

    private static FileStorage instance = new FileStorage();

    public static FileStorage getInstance() {
        return instance;
    }


    @Override
    public File getSavePath() {



        return null;
    }
}
