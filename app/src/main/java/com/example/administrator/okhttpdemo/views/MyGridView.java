package com.example.administrator.okhttpdemo.views;


import android.widget.GridView;

public class MyGridView extends GridView {
	public boolean hasScrollBar = true;

	public MyGridView(android.content.Context context, android.util.AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置不滚动
	 */
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}

