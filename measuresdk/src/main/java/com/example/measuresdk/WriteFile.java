package com.example.measuresdk;

import android.os.Environment;
import android.util.Log;

import com.example.measuresdk.entity.MeasureData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ysx on 2017/8/9.
 */

public class WriteFile {
    private static final String TAG = "WriteFile";

    public static final String CHAR_SET = "GBK";
    
    public static final String FILE_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/";

    private static void init(String fileName) {
        Log.d(TAG, "init: fileName: " + fileName);
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeDataToFile(MeasureData measureData) {
        Log.d(TAG, "writeDataToFile: start... size(): " + measureData.getMeasureItemList().size());
        String fileName = FILE_PATH + measureData.getName() + ".csv";
        init(fileName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(fileName));
            String title = MeasureData.getTitle();
            out.write(title.getBytes(CHAR_SET));
            for (MeasureData.MeasureItem item : measureData.getMeasureItemList()) {
                String content = item.getPosition()
                        + MeasureData.SEPARATOR + item.getFloor()
                        + MeasureData.SEPARATOR + item.getPoint_x()
                        + MeasureData.SEPARATOR + item.getPoint_y()
                        + MeasureData.SEPARATOR + item.getPci()
                        + MeasureData.SEPARATOR + item.getRsrp()
                        + MeasureData.SEPARATOR + item.getSinr()
                        + "\r\n";
                Log.d(TAG, "writeDataToFile: content: " + content);
                out.write(content.getBytes(CHAR_SET));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "writeDataToFile: end...");
    }
}
