package kr.co.saweb.enhance.android.util.file;

import android.util.Log;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kr.co.saweb.enhance.android.util.common.FileUtil;

/**
 * Created by OKS on 2014-11-20.
 */
public abstract class AndroidFile {
    public abstract File getSavePath();

    private String TAG = getClass().getName();

    protected String saveDir;

    List<String> cacheKeys = new ArrayList<String>();

    public void writeText(String text, String fileName) throws Exception {
        cacheKeys.add(fileName);

        File f = new File(getSavePath(), fileName);

        log("writeText", f.getAbsolutePath());

        FileUtil.writeFile(text, f);
    }

    public String readText(String fileName) throws Exception {
        File f = new File(getSavePath(), fileName);

        if (!f.isFile()) {
            writeText("", fileName);
        }

        log("readText", f.getAbsolutePath());

        return FileUtil.readFile(f);
    }

    public void writeObject(Object object, String fileName) throws Exception {
        cacheKeys.add(fileName);

        File f = new File(getSavePath(), fileName);
        log("writeObject", f.getAbsolutePath());

        FileUtil.objectWrite(object, f);
    }

    public Object readObject(String fileName) throws Exception {
        File f = new File(getSavePath(), fileName);

        if (f.isFile()) {
            log("readObject", f.getAbsolutePath());
            return FileUtil.readObject(f);
        }

        return null;
    }

    public void deleteFile(String fileName) throws Exception {
        cacheKeys.remove(fileName);

        File f = new File(getSavePath(), fileName);

        if (f.isFile())
            f.delete();
    }

    public void clear() {
        Log.d(TAG, "clear cache call!");

        for (String fileName : cacheKeys) {
            try {
                deleteFile(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected String getSaveDir() {
        return saveDir;
    }

    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }

    private void log(String type, String fileName) {
        Log.d(TAG, type + " file : " + fileName);
    }
}
