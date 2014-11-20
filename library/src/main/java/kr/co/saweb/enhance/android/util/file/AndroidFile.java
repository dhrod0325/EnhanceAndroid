package kr.co.saweb.enhance.android.util.file;

import android.util.Log;

import java.io.File;

import kr.co.saweb.enhance.android.util.common.FileUtil;

/**
 * Created by OKS on 2014-11-20.
 */
public abstract class AndroidFile {
    public abstract File getSavePath();

    private String TAG = getClass().getName();

    protected String saveDir;

    public void writeText(String text, String fileName) throws Exception {
        File f = new File(getSavePath(), fileName);
        FileUtil.writeFile(text, f);
    }

    public String readText(String fileName) throws Exception {
        File f = new File(getSavePath(), fileName);

        if (f.isFile())
            return FileUtil.readFile(f);

        return null;
    }

    public void writeObject(Object object, String fileName) throws Exception {
        File f = new File(getSavePath(), fileName);
        FileUtil.objectWrite(object, f);
    }

    public Object readObject(String fileName) throws Exception {
        File f = new File(getSavePath(), fileName);

        if (f.isFile())
            return FileUtil.readObject(f);

        return null;
    }

    public String getSaveDir() {
        return saveDir;
    }

    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }
}
