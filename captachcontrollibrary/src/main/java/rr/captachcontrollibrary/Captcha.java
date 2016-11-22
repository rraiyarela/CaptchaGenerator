package rr.captachcontrollibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;

import java.security.SecureRandom;
import java.util.Random;

public final class Captcha {
	private static final char[] characterSet = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	
	private static final int MIN_CAPTCHA_LENGTH = 5;
	
	private static Random random;
	
	private static Bitmap bitmap;
	
	private static String strCaptcha;
	
	//char[] result = null;
	
	private Captcha(){
		this(MIN_CAPTCHA_LENGTH);
	}
	
	@SuppressLint("TrulyRandom")
	private Captcha(int length){
		random = new SecureRandom();
		
		//result = new char[length];
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	/*public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}*/
	
	/*public String getStrCaptcha() {
		return strCaptcha;
	}*/
	
	/*public void setStrCaptcha(String strCaptcha) {
		this.strCaptcha = strCaptcha;
	}*/
	
	public static Captcha generateCaptcha(int textColor, Context context){
		Captcha mCaptcha = new Captcha();
		StringBuffer sb = new StringBuffer(MIN_CAPTCHA_LENGTH);
		
		for (int i = 0; i < MIN_CAPTCHA_LENGTH; i++) {
	        // picks a random index out of character set > random character
	        int randomCharIndex = random.nextInt(characterSet.length);
	        sb.append(characterSet[randomCharIndex]);
	    }
		
		strCaptcha = sb.toString();
		
		generateCaptchaImage(textColor, context);
		
		return mCaptcha;
	}
	
	private static void generateCaptchaImage(int textColor, Context context){
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    paint.setTextSize(18 * context.getResources().getDisplayMetrics().density);
	    //paint.setColor(Color.WHITE);
	    paint.setColor(textColor);
	    paint.setTextAlign(Paint.Align.LEFT);
	    
	    float baseline = -paint.ascent(); // ascent() is negative
	    
	    int width = (int) (paint.measureText(strCaptcha) + 0.5f); // round
	    int height = (int) (baseline + paint.descent() + 0.5f);
	    
	    if (bitmap != null) bitmap.recycle();
	    
	    bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	    
	    Canvas canvas = new Canvas(bitmap);
	    canvas.drawText(strCaptcha, 0, baseline, paint);
	}
	
	public boolean validateCaptcha(Editable input){
		if (input == null) return false;
		if (input != null && input.toString().trim().length() == 0) return false;
		return input.toString().equalsIgnoreCase(strCaptcha);
	}
}
