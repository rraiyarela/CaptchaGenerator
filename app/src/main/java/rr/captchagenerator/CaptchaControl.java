package rr.captchagenerator;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class CaptchaControl extends RelativeLayout{

	ImageView imgCaptcha;
	
	EditText etCaptch;
	
	ImageButton imgBtnRefresh;
	
	Captcha mCaptcha = null;
	
	int bgColor = -1;
	
	int textColor = -1;
	
	Context context;
	
	public CaptchaControl(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		init(context);
	}

	public CaptchaControl(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init(context);
	}

	public CaptchaControl(Context context) {
		super(context);
		
		init(context);
	}
	
	private void init(Context context){
		this.context = context;
		/*RelativeLayout mRelativeLayout = new RelativeLayout(context);
		mRelativeLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));*/
		LayoutInflater li = LayoutInflater.from(context);
		
		View view = li.inflate(R.layout.captcha_layout, this, true);
		
		imgCaptcha = (ImageView)view.findViewById(R.id.cap_img);
		etCaptch = (EditText)view.findViewById(R.id.cap_et);
		imgBtnRefresh = (ImageButton)view.findViewById(R.id.btn_refresh);
		
		imgBtnRefresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				imgCaptcha.setImageBitmap(null);
				renderCaptcha();
			}
		});
		
		renderCaptcha();
	}
	
	private void renderCaptcha(){
		generateBackgroundColor();
		mCaptcha = Captcha.generateCaptcha(textColor, context);
		imgCaptcha.setBackgroundColor(bgColor);
		imgCaptcha.setImageBitmap(mCaptcha.getBitmap());
	}
	
	private void generateBackgroundColor(){
		Random rnd = new Random();	
		bgColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		textColor = getForeGroundColorBasedOnBGBrightness();
	}
	
	private int getBrightness() {
	    return (int) Math.sqrt(Color.red(bgColor) * Color.red(bgColor) * .241 +
	    		Color.green(bgColor) * Color.green(bgColor) * .691 +
	      Color.blue(bgColor) * Color.blue(bgColor) * .068);
	}

	public int getForeGroundColorBasedOnBGBrightness() {
	    if (getBrightness() < 130)
	        return Color.WHITE;
	    else
	        return Color.BLACK;
	}
	
	public boolean isCaptchaValid(){
		//Toast.makeText(MainActivity.this, String.valueOf(mCaptcha.validateCaptcha(etCap.getText())), Toast.LENGTH_LONG).show();
		boolean valid = mCaptcha.validateCaptcha(etCaptch.getText());
		etCaptch.setText("");
		
		imgBtnRefresh.performClick();
		
		return valid;
	}

}
