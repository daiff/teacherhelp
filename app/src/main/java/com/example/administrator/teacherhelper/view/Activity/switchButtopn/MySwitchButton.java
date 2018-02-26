package com.example.administrator.teacherhelper.view.Activity.switchButtopn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;

/**
 * �Զ��廬������
 */
public class MySwitchButton extends View implements OnTouchListener {

	private Bitmap switchOnBkg;
	private Bitmap switchOffBkg;
	private Bitmap slipSwitchButton;
	private Rect onRect;
	private Rect offRect;

	private boolean isSlipping = false;
	private boolean isSwitchOn = false;
	private float previousX;
	private float currentX;

	private ArrayList<OnSwitchListener> onSwitchListenerList; // ���ؼ�����

	public MySwitchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		this.setOnTouchListener(this); // ���ô���������
		onSwitchListenerList = new ArrayList<OnSwitchListener>();
	}

	public void setImageResource(int switchBkg, int slipBtn) {
		switchOnBkg = BitmapFactory.decodeResource(this.getResources(),
				switchBkg);
		switchOffBkg = BitmapFactory.decodeResource(this.getResources(),
				switchBkg);
		slipSwitchButton = BitmapFactory.decodeResource(this.getResources(),
				slipBtn);

		// �Ұ��rect�������������Ұ��ʱ��ʾ����
		onRect = new Rect(switchOnBkg.getWidth() - slipSwitchButton.getWidth(),
				0, switchOnBkg.getWidth(), slipSwitchButton.getHeight());
		// ����rect����������������ʱ��ʾ�ر�
		offRect = new Rect(0, 0, slipSwitchButton.getWidth(),
				slipSwitchButton.getHeight());
	}

	public void setSwitchState(boolean switchState) {
		this.isSwitchOn = switchState;
		this.invalidate();
	}

	public boolean getSwitchState() {
		return this.isSwitchOn;
	}
	
	public void setOnSwitchStateListener(OnSwitchListener listener){
		onSwitchListenerList.add(listener);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Matrix matrix = new Matrix();
		Paint paint = new Paint();

		float leftSlipBtnX; // ������ť���������

		System.out.println("currentX=" + currentX + " switchOnBkg.width="
				+ switchOnBkg.getWidth());

		canvas.drawBitmap(switchOnBkg, matrix, paint);

		if (isSlipping) {
			// ������ڻ���
			if (currentX > switchOnBkg.getWidth()) {
				leftSlipBtnX = switchOnBkg.getWidth()
						- slipSwitchButton.getWidth();
			} else {
				leftSlipBtnX = currentX - slipSwitchButton.getWidth();
			}
		} else {
			if (isSwitchOn) {
				leftSlipBtnX = switchOnBkg.getWidth()
						- slipSwitchButton.getWidth();
			} else {
				leftSlipBtnX = 0;
			}
		}

		if (leftSlipBtnX < 0) {
			leftSlipBtnX = 0;
		} else if (leftSlipBtnX > switchOnBkg.getWidth()
				- slipSwitchButton.getWidth()) {
			leftSlipBtnX = switchOnBkg.getWidth() - slipSwitchButton.getWidth();
		}

		canvas.drawBitmap(slipSwitchButton, leftSlipBtnX, 0, paint);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(switchOnBkg.getWidth(), switchOnBkg.getHeight());
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			currentX = event.getX();
			break;
		case MotionEvent.ACTION_DOWN:
			isSlipping = true;
			break;
		case MotionEvent.ACTION_UP:
			isSlipping = false;
			boolean previousState = isSwitchOn;
			if (event.getX() > (switchOnBkg.getWidth() / 2)) {
				isSwitchOn = true;
			} else {
				isSwitchOn = false;
			}

			if(previousState != isSwitchOn){
				if(onSwitchListenerList.size() > 0){
					for(OnSwitchListener listener : onSwitchListenerList){
						listener.onSwitched(isSwitchOn);
					}
				}
			}
			break;

		default:
			break;
		}

		this.invalidate();
		return true;
	}

}
