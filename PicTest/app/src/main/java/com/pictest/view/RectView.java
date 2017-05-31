package com.pictest.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RectView extends View {
	private Paint paint;
	private Paint paint1;
	public RectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	private void init() {
		
		paint=new Paint();
		paint.setColor(Color.parseColor("#0000aa"));
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);
		paint1=new Paint();
		paint1.setColor(Color.parseColor("#22111111"));
		paint1.setAntiAlias(true);
		paint1.setStyle(Paint.Style.FILL);
		paint1.setStrokeWidth(2);
		
	}
	
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO �Զ����ɵķ������
		super.onDraw(canvas);
		float width = getWidth();
		float height = getHeight();
		float wi=width-40;
		double ks=wi*53.98/90;//85.60

		RectF r=new RectF(20, height/2-(int)ks/2, width-20, height/2+(int)ks/2);
//		canvas.drawRect(r, paint);
		canvas.drawRoundRect(r, 15f, 15f, paint);
		
		
		
		
		RectF rect=new RectF(0,0,width,height/2-(int)ks/2);
		canvas.drawRect(rect, paint1);
		
		
		RectF rect1=new RectF(0,height/2-(int)ks/2,20,height/2+(int)ks/2);
		canvas.drawRect(rect1, paint1);
		
		
		RectF rect2=new RectF(width-20,height/2-(int)ks/2,width,height/2+(int)ks/2);
		canvas.drawRect(rect2, paint1);
		
		
		RectF rect3=new RectF(0,height/2+(int)ks/2,width,height);
		canvas.drawRect(rect3, paint1);
		
	}
	private void drawshader(Canvas canvas) {
		RectF rect=new RectF();
		canvas.drawRect(rect, paint);
		
		
		
	}

}
