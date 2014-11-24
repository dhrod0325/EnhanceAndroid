package kr.co.saweb.enhance.android.util.file.cache;

import android.util.Log;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.saweb.enhance.android.util.file.SDCard;

/**
 * Created by OKS on 2014-11-21.
 */
public class FileCache {
    private int maxCacheTime = 60;//한시간

    private SDCard card = new SDCard();

    public final static String prefix = "fileCache_";

    public final String TAG = getClass().getName();

    public FileCache() {
        this.maxCacheTime = maxCacheTime * 1000;
    }

    public FileCache(int maxCacheTime) {
        this.maxCacheTime = maxCacheTime * 1000;
    }

    public void setMaxCacheTime(int maxCacheTime) {
        this.maxCacheTime = maxCacheTime;
    }

    public void put(String key, Object object) throws Exception {
        String k = getKey(key);

        CacheEntry entry = new CacheEntry(object);
        card.writeObject(entry, k);
    }

    public Object get(String key) throws Exception {
        String k = getKey(key);

        CacheEntry entry = (CacheEntry) card.readObject(k);

        if (entry == null)
            return null;

        if (maxCacheTime > 0 && System.currentTimeMillis() - entry.getCreateTime() > maxCacheTime) {
            card.deleteFile(k);

            return null;
        } else {
            return entry.getData();
        }
    }

    private String getKey(String key) {
        return prefix + key;
    }

    public void clear() {
        File f = card.getSavePath();

        File[] files = f.listFiles();

        if (files == null)
            return;

        for (File file : files) {
            if (file.isFile()) {
                Log.e(TAG, "delete cache : " + file.getAbsolutePath());

                file.delete();
            }
        }
    }
}
