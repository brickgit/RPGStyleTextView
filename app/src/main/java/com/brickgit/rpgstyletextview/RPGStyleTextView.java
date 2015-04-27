package com.brickgit.rpgstyletextview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

public class RPGStyleTextView extends TextView {
	private String mTextString;
	private SpannableString mSpannableString;

	private boolean mIsTextResetting = false;

	private int mDuration = 5000;

	ValueAnimator mAnimator;
	ValueAnimator.AnimatorUpdateListener mAnimatorListener = new ValueAnimator.AnimatorUpdateListener() {
		@Override
		public void onAnimationUpdate(ValueAnimator valueAnimator) {
			Float percent = (Float) valueAnimator.getAnimatedValue();
			resetSpannableString(percent);
		}
	};

	public RPGStyleTextView(Context context) {
		super(context);
		init();
	}

	public RPGStyleTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init(){
		mAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
		mAnimator.addUpdateListener(mAnimatorListener);
		mAnimator.setDuration(mDuration);
	}

	public void show() {
		mAnimator.start();
	}

	private void resetSpannableString(double percent) {
		mIsTextResetting = true;

		mSpannableString = new SpannableString(mTextString);
		int color = getCurrentTextColor();
		int length = mSpannableString.length();
		int start = (int) (length * percent);
		for (int i = start; i < length; i++) {
			ForegroundColorSpan fcp = new ForegroundColorSpan(Color.argb(0, Color.red(color), Color.green(color), Color.blue(color)));
			mSpannableString.setSpan(fcp, i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}

		setText(mSpannableString);
		invalidate();

		mIsTextResetting = false;
	}

	private void resetIfNeeded() {
		if (!mIsTextResetting){
			mTextString = getText().toString();
			resetSpannableString(0.0);
		}
	}

	public void setText(String text) {
		super.setText(text);
		resetIfNeeded();
	}

	@Override
	public void setText(CharSequence text, TextView.BufferType type) {
		super.setText(text, type);
		resetIfNeeded();
	}

	public void setDuration(int mDuration){
		this.mDuration = mDuration;
		mAnimator.setDuration(mDuration);
	}
}
