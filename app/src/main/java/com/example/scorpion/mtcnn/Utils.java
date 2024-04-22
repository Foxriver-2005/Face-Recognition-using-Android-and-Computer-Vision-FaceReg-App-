package com.example.scorpion.mtcnn;

import static java.lang.Math.max;
import static java.lang.Math.min;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.scorpion.R;

import java.util.Vector;

public class Utils {

    private static Canvas canvas;
    private static Paint paint;
    //isMutable=true
    public static Bitmap copyBitmap(Bitmap bitmap) {
        return bitmap.copy(bitmap.getConfig(), true);
    }

    //bitmap
    public static void drawRect(Context context, Bitmap bitmap, Rect rect, int thick, boolean flag, String text) {
        try {
            canvas = new Canvas(bitmap);
            paint = new Paint();

            if (flag) {
                paint.setColor(ContextCompat.getColor(context, R.color.green));
            } else {
                paint.setColor(ContextCompat.getColor(context, R.color.red));
            }
            paint.setAntiAlias(true);
            paint.setTextSize(30f);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(thick);
            canvas.drawRect(rect, paint);
            canvas.drawText(text, rect.left, rect.top, paint);
            //Log.i("Util","[*]draw rect");
        } catch (Exception e) {
            Log.i("Utils", "[*] error" + e);
        }
    }

    //Draw points in the picture
    public static void drawPoints(Bitmap bitmap, Point[] landmark, int thick) {
        for (int i = 0; i < landmark.length; i++) {
            int x = landmark[i].x;
            int y = landmark[i].y;
        }
    }

    public static void drawBox(Bitmap bitmap, Box box, int thick) {
//        drawRect(bitmap, box.transform2Rect(), thick);
    }

    public static void drawLandmarks(Bitmap bitmap, Box box, int thick) {
        drawPoints(bitmap, box.landmark, thick);
    }

    //Flip alone diagonal
    public static void flip_diag(float[] data, int h, int w, int stride) {
        float[] tmp = new float[w * h * stride];
        for (int i = 0; i < w * h * stride; i++) tmp[i] = data[i];
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                for (int z = 0; z < stride; z++)
                    data[(x * h + y) * stride + z] = tmp[(y * w + x) * stride + z];
            }
    }

    public static void expand(float[] src, float[][] dst) {
        int idx = 0;
        for (int y = 0; y < dst.length; y++)
            for (int x = 0; x < dst[0].length; x++)
                dst[y][x] = src[idx++];
    }

    public static void expand(float[] src, float[][][] dst) {
        int idx = 0;
        for (int y = 0; y < dst.length; y++)
            for (int x = 0; x < dst[0].length; x++)
                for (int c = 0; c < dst[0][0].length; c++)
                    dst[y][x][c] = src[idx++];

    }

    public static void expandProb(float[] src, float[][] dst) {
        int idx = 0;
        for (int y = 0; y < dst.length; y++)
            for (int x = 0; x < dst[0].length; x++)
                dst[y][x] = src[idx++ * 2 + 1];
    }

    public static Rect[] boxes2rects(Vector<Box> boxes) {
        int cnt = 0;
        for (int i = 0; i < boxes.size(); i++) if (!boxes.get(i).deleted) cnt++;
        Rect[] r = new Rect[cnt];
        int idx = 0;
        for (int i = 0; i < boxes.size(); i++)
            if (!boxes.get(i).deleted)
                r[idx++] = boxes.get(i).transform2Rect();
        return r;
    }

    public static Vector<Box> updateBoxes(Vector<Box> boxes) {
        Vector<Box> b = new Vector<Box>();
        for (int i = 0; i < boxes.size(); i++)
            if (!boxes.get(i).deleted)
                b.addElement(boxes.get(i));
        return b;
    }

    public static Bitmap crop(Bitmap bitmap, Rect rect) {
        Bitmap cropped = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
        return cropped;
    }

    public static void rectExtend(Bitmap bitmap, Rect rect, int pixels) {
        rect.left = max(0, rect.left - pixels);
        rect.right = min(bitmap.getWidth() - 1, rect.right + pixels);
        rect.top = max(0, rect.top - pixels);
        rect.bottom = min(bitmap.getHeight() - 1, rect.bottom + pixels);
    }

    static public void showPixel(int v) {
        Log.i("MainActivity", "[*]Pixel:R" + ((v >> 16) & 0xff) + "G:" + ((v >> 8) & 0xff) + " B:" + (v & 0xff));
    }

    static public Bitmap resize(Bitmap _bitmap, int new_width) {
        float scale = ((float) new_width) / _bitmap.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap bitmap = Bitmap.createBitmap(_bitmap, 0, 0, _bitmap.getWidth(), _bitmap.getHeight(), matrix, true);
        return bitmap;
    }
}