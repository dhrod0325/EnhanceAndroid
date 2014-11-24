package kr.co.saweb.enhance.android.util.file.cache;

import kr.co.saweb.enhance.android.util.file.AndroidFile;

/**
 * Created by OKS on 2014-11-24.
 */
public abstract class AbstractCache extends AndroidFile {
    protected int maxCacheTime = 3600;//한시간

    public final static String prefix = "fileCache_";

    public AbstractCache(int maxCacheTime) {
        this.maxCacheTime = maxCacheTime;
    }

    public void setSaveDir(String dir) {
        setSaveDir(dir);
    }

    public void setMaxCacheTime(int maxCacheTime) {
        this.maxCacheTime = maxCacheTime;
    }

    public void put(String key, Object object) throws Exception {
        String k = getKey(key);

        CacheEntry entry = new CacheEntry(object);
        writeObject(entry, k);
    }

    public Object get(String key) throws Exception {
        String k = getKey(key);

        CacheEntry entry = (CacheEntry) readObject(k);

        if (entry == null)
            return null;

        if (maxCacheTime > 0 && System.currentTimeMillis() - entry.getCreateTime() > maxCacheTime) {
            deleteFile(k);

            return null;
        } else {
            return entry.getData();
        }
    }

    public void clearCache() {
        clear();
    }

    private String getKey(String key) {
        return prefix + key;
    }
}
