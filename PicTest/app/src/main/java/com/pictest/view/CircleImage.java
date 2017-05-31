package com.pictest.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 鑷畾涔夊渾瑙掑浘鐗�
 * 
 * 
 */
@SuppressLint("DrawAllocation")
public class CircleImage extends View {

	private Paint mpaint;
	private Paint mappaint;
	private Bitmap bitmap;
	private Path path;
	private float width;
	private float wi;
	private float tan;
	private float no;
	private int level = 1;
	private String text = "";
	private String color;

	public CircleImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		path = new Path();
		mpaint = new Paint();
		mappaint = new Paint();
		mpaint.setColor(Color.parseColor("#23b7aa"));
		mappaint.setColor(Color.parseColor("#ffffff"));
		mpaint.setAntiAlias(true);
		mappaint.setAntiAlias(true);
		mpaint.setStrokeWidth(4);
		mpaint.setStyle(Paint.Style.FILL_AND_STROKE);
		mappaint.setStyle(Paint.Style.FILL_AND_STROKE);

	}

	public void setBackground(int id, int level) {
		bitmap = BitmapFactory.decodeResource(getResources(), id);
		this.level = level;
		invalidate();
	}

	public void setText(String text, String color, int level) {
		this.level = level;
		this.text = text;
		this.color = color;
		invalidate();
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		width = getWidth();

		int angle = 29 + ((level - 1) * 2);
		if (angle < 29) {
			angle = 29;
		} else if (angle > 90) {
			angle = 90;
		}
		tan = (float) Math.tan(angle * Math.PI / 180);
		if (tan > 2) {
			tan = 2;
		}
		no = width / 4 * (tan - 1);
		wi = (width / 2) * tan;

		if (null != bitmap) {
			float mapwidth = bitmap.getWidth();
			float mapheight = bitmap.getHeight();
			float scaleWidth = width / mapwidth * 1f;
			float scaleHeight = width / mapheight * 1f;
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, (int) mapwidth,
					(int) mapheight, matrix, true);
			newbm = removeYuanjiao(newbm, level);
			canvas.drawBitmap(newbm, 0, 0, mappaint);
		} else if (null != text) {
			drawtext(canvas);
		}

	}

	private void drawtext(Canvas canvas) {
		int length = text.length();
		if (length >= 3) {
			mpaint.setTextSize(width / 3f);
		} else {
			mpaint.setTextSize(width / 2.5f);
		}
		try {
			mpaint.setColor(Color.parseColor(color));
		} catch (Exception e) {
			Log.e("ERROR", "color鍊奸敊璇�");
		}
		mpaint.setStrokeWidth(1);
		if (level>=20) {
			
			canvas.drawCircle(width / 2, width / 2, width / 2 * 0.95f, mpaint);
		}else{
			RectF rectF = new RectF(0, 0, width, width);
			canvas.drawRoundRect(rectF, level, level, mpaint);
		}
		
		mpaint.setColor(Color.parseColor("#ffffff"));
		int baseX = (int) (canvas.getWidth() / 2 - mpaint.measureText(text) / 2);
		int baseY = (int) ((canvas.getHeight() / 2) - ((mpaint.descent() + mpaint
				.ascent()) / 2));
		canvas.drawText(text, baseX, baseY, mpaint);

	}

	// private void drawtest1(Canvas canvas) {
	// path.moveTo(width / 4 * 3 + no, 0);
	// path.quadTo(width, 0, width, width / 4 - no);
	// path.lineTo(width, 0);
	// canvas.drawPath(path, mappaint);// 缁樺埗鍙充笂
	// path.close();
	// path.moveTo(width / 4 - no, 0);
	// path.quadTo(0, 0, 0, width / 4 - no);
	// path.lineTo(0, 0);
	// canvas.drawPath(path, mappaint);// 缁樺埗宸︿笂
	// path.close();
	// path.moveTo(0, width / 4 * 3 + no);
	// path.quadTo(0, width, width / 4 - no, width);
	// path.lineTo(0, width);
	// canvas.drawPath(path, mappaint);// 缁樺埗宸︿笅
	// path.close();
	// path.moveTo(width / 4 * 3 + no, width);
	// path.quadTo(width, width, width, width / 4 * 3 + no);
	// path.lineTo(width, width);
	// canvas.drawPath(path, mappaint);// 缁樺埗鍙充笅
	// path.close();
	// }
	//
	// private void drawtest(Canvas canvas) {
	// path.moveTo(width / 2, 0);
	// path.cubicTo(width / 2 + wi, 0, width, width / 2 - wi, width, width / 2);
	// path.lineTo(width, 0);
	// canvas.drawPath(path, mappaint);// 缁樺埗鍙充笂
	// path.close();
	// path.moveTo(width / 2, 0);
	// path.cubicTo(width / 2 - wi, 0, 0, width / 2 - wi, 0, width / 2);
	// path.lineTo(0, 0);
	// canvas.drawPath(path, mappaint);// 缁樺埗宸︿笂
	// path.close();
	// path.moveTo(0, width / 2);
	// path.cubicTo(0, width / 2 + wi, width / 2 - wi, width, width / 2, width);
	// path.lineTo(0, width);
	// canvas.drawPath(path, mappaint);// 缁樺埗宸︿笅
	// path.close();
	// path.moveTo(width / 2, width);
	// path.cubicTo(width / 2 + wi, width, width, width / 2 + wi, width,
	// width / 2);
	// path.lineTo(width, width);
	// canvas.drawPath(path, mappaint);// 缁樺埗鍙充笅
	// path.close();
	//
	// }
	public static Bitmap removeYuanjiao(Bitmap bitmap, int pixels) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Bitmap creBitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(creBitmap);

		Paint paint = new Paint();
		float roundPx = pixels;
		RectF rectF = new RectF(0, 0, bitmap.getWidth() - pixels,
				bitmap.getHeight() - pixels);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		if (pixels >= 20) {

			canvas.drawCircle(width / 2, width / 2, width / 2, paint);
		} else {
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		}
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

		canvas.drawBitmap(bitmap, 0, 0, paint);
		if (!bitmap.isRecycled())
			bitmap.recycle();

		return creBitmap;
	}
}
