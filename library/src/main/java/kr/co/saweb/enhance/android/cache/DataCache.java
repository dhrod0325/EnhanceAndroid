package kr.co.saweb.enhance.android.cache;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by OKS on 2014-10-08.
 */
public class DataCache {
    private class DataCacheEntry {
        private long createTime = 0;
        private Object data = null;

        public DataCacheEntry() {
        }

        public DataCacheEntry(Object data) {
            this.createTime = System.currentTimeMillis();
            this.data = data;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    private int maxCacheSize = 100;
    private int maxCacheTime = 60;

    private Hashtable<String, DataCacheEntry> dataCache = new Hashtable<String, DataCacheEntry>();
    private ArrayList keyList = new ArrayList<String>();

    public DataCache(int maxCacheTime, int maxCacheSize) {
        this.maxCacheTime = maxCacheTime;
        this.maxCacheSize = maxCacheSize;
    }

    public DataCache() {
    }

    public DataCache(int maxCacheTime) {
        this.maxCacheTime = maxCacheTime;
    }

    public Object get(String key) {
        if (maxCacheSize <= 0) {
            return null;
        }
        DataCacheEntry dataCacheEntry = dataCache.get(key);
        if (dataCacheEntry == null) {
            return null;
        } else {
            if (maxCacheTime > 0 && System.currentTimeMillis() - dataCacheEntry.getCreateTime() > (maxCacheTime * 1000)) {
                remove(key);
                return null;
            }
            keyList.remove(key);
            keyList.add(key);
            return dataCacheEntry.getData();
        }
    }

    public void put(String key, Object value) {
        if (maxCacheSize <= 0) {
            return;
        }

        DataCacheEntry entry = new DataCacheEntry(value);
        remove(key);
        if (maxCacheSize > 0) {
            if (dataCache.size() >= maxCacheSize) {
                clearExpired();
            }
            if (dataCache.size() >= maxCacheSize) {
                remove((String) keyList.get(0));
            }
        }
        dataCache.put(key, entry);
        keyList.add(key);
    }

    public void remove(String key) {
        dataCache.remove(key);
        keyList.remove(key);
    }

    public void clearExpired() {
        Iterator it = dataCache.keySet().iterator();
        String key;
        while (it.hasNext()) {
            key = (String) it.next();
            dataCache.get(key);
        }
    }

    public int getMaxCacheSize() {
        return maxCacheSize;
    }

    public void setMaxCacheSize(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    public int getMaxCacheTime() {
        return maxCacheTime;
    }

    public void setMaxCacheTime(int maxCacheTime) {
        this.maxCacheTime = maxCacheTime;
    }
}
