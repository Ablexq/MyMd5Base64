package com.xq.mymd5base64.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitmapUtil {

    public static byte[] getBase64Bytes(String base64Str) {
        String url = base64Str.split(",")[1];
        return Base64.decode(url, Base64.DEFAULT);
    }

    public static boolean isBase64Img(String url) {
        return !TextUtils.isEmpty(url) && (
                url.startsWith("data:image/png;base64,")
                        || url.startsWith("data:image/*;base64,")
                        || url.startsWith("data:image/jpeg;base64,")
                        || url.startsWith("data:image/jpg;base64,"));
    }

    /**
     * bitmap转为base64
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     * base64不要带类似前缀data:image/png;base64
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        String url = base64Data.split(",")[1];
        byte[] bytes = Base64.decode(url, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
