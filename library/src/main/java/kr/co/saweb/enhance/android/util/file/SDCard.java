package kr.co.saweb.enhance.android.util.file;

import android.os.Environment;

import java.io.File;

/**
 * Created by OKS on 2014-11-20.
 */
public class SDCard extends AndroidFile {
    public static final String TAG = SDCard.class.getName();

    private File sdCard;
    private File savePath;
    private String sdCardDir;

    @Override
    public File getSavePath() {
        sdCard = Environment.getExternalStorageDirectory();
        sdCardDir = sdCard.getAbsolutePath();

        if (saveDir == null) {
            setSaveDir("/enhance/sdcard/");
        }

        savePath = new File(sdCardDir + saveDir);

        if (!savePath.isDirectory())
            savePath.mkdirs();

        return savePath;
    }
}
