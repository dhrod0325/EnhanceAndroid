package kr.co.saweb.enhance.android.util.file.cache;

import java.io.Serializable;

/**
 * Created by OKS on 2014-11-21.
 */
public class CacheEntry implements Serializable {
    private long createTime = 0;
    private Object data = null;

    public CacheEntry(Object data) {
        this.createTime = System.currentTimeMillis();
        this.data = data;
    }

    public long getCreateTime() {
        return createTime;
    }

    public Object getData() {
        return data;
    }
}