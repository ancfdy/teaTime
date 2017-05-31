package com.teatime.library_teatime.View.flyrefresh.internal;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

/**
 * Created by jing on 15-5-21.
 */
public class RotatableDrawable extends LayerDrawable {

    private float mDegree = 0;

    /**
     * 使用指定图层的列表创建一个可绘制的新图层。
     *
     * @param layers 在此新绘图中用作图层的可绘制列表。
     */
    public RotatableDrawable(Drawable[] layers) {
        super(layers);
    }

    public RotatableDrawable(Drawable drawable) {
        this(new Drawable[]{drawable});
    }

    public void setDegree(float degree) {
        mDegree = degree;
    }

    public float getDegree() {
        return mDegree;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        Rect bounds = getBounds();
        canvas.rotate(mDegree, bounds.centerX(), bounds.centerY());
        super.draw(canvas);
        canvas.restore();
    }

}
