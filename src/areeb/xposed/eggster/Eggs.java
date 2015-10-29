/*
 * Copyright (C) 2014 Areeb Jamal (iamareebjamal)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package areeb.xposed.eggster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nineoldandroids.view.ViewPropertyAnimator;


@SuppressLint({ "WorldReadableFiles", "NewApi" })
public class Eggs extends AppCompatActivity {



	SharedPreferences pref;
	SharedPreferences.Editor edit;
	Boolean enabled;
	String current_egg;

	ImageView gbImg, hcImg, icsImg, jbImg, kkImg, lImg, lpImg, mpImg;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	ImageView openLayout;
	RelativeLayout logLayout;

	SwitchCompat GB_Check, HC_Check, ICS_Check, JB_Check, KK_Check, L_Check, LP_Check, MP_Check;

	int GB = 0;
	int HC = 1;
	int ICS = 2;
	int JB = 3;
	int KK = 4;
	int L = 5;
	int LP = 6;
    int MP = 7;

	String[] versionName = { "Gingerbread", "Honeycomb", "Ice Cream Sandwich",
			"Jelly Bean", "Kitkat", "L Preview", "Lollipop", "M Preview" };
	String[] versionSName = { "GB", "HC", "ICS", "JB", "KK", "L", "LP" , "MP"};
	int[] versionCode = { 10, 11, 14, 16, 19, 20, 21, 22};

	@SuppressLint("CommitPrefEdits")
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		pref = getSharedPreferences("easter_preference",
				Context.MODE_WORLD_READABLE);
		edit = pref.edit();

		enabled = pref.getBoolean("enabled", false);
		current_egg = pref.getString("egg_name", "");

		
		GB_Check = (SwitchCompat) findViewById(R.id.gbCheck);
		HC_Check = (SwitchCompat) findViewById(R.id.hcCheck);
		ICS_Check = (SwitchCompat) findViewById(R.id.icsCheck);
		JB_Check = (SwitchCompat) findViewById(R.id.jbCheck);
		KK_Check = (SwitchCompat) findViewById(R.id.kkCheck);
		L_Check = (SwitchCompat) findViewById(R.id.ldpCheck);
		LP_Check = (SwitchCompat) findViewById(R.id.lollipopCheck);
        MP_Check = (SwitchCompat) findViewById(R.id.mpCheck);

		gbImg = (ImageView) findViewById(R.id.gbImg);
		hcImg = (ImageView) findViewById(R.id.hcImg);
		icsImg = (ImageView) findViewById(R.id.icsImg);
		jbImg = (ImageView) findViewById(R.id.jbImg);
		kkImg = (ImageView) findViewById(R.id.kkImg);
		lImg = (ImageView) findViewById(R.id.ldpImg);
		lpImg = (ImageView) findViewById(R.id.lollipopImg);
        mpImg = (ImageView) findViewById(R.id.mpImg);
		
		/*
		 * int count = 4;
		 * 
		 * do{
		 * 
		 * double index = Math.random();
		 * 
		 * index = index*10;
		 * 
		 * String temp = Double.toString(index);
		 * 
		 * count = Integer.parseInt(temp);
		 * 
		 * } while (count > 4);
		 */

		colorize();

		if (enabled == false) {

			decolorize();

		} else 
			
		{

		if (!current_egg.equals("")) {

			if (current_egg.equals("GB")) {

				GB_Check.setChecked(true);

			} else if (current_egg.equals("HC")) {

				HC_Check.setChecked(true);

			} else if (current_egg.equals("ICS")) {

				ICS_Check.setChecked(true);

			} else if (current_egg.equals("JB")) {

				JB_Check.setChecked(true);

			} else if (current_egg.equals("KK")) {

				KK_Check.setChecked(true);

			} else if (current_egg.equals("L")) {

				L_Check.setChecked(true);

			} else if (current_egg.equals("LP")) {

				LP_Check.setChecked(true);

			} else if (current_egg.equals("MP")) {

                MP_Check.setChecked(true);

            }
			
		}

		}

		

		GB_Check.setOnCheckedChangeListener(toggleIt(GB));
		HC_Check.setOnCheckedChangeListener(toggleIt(HC));
		ICS_Check.setOnCheckedChangeListener(toggleIt(ICS));
		JB_Check.setOnCheckedChangeListener(toggleIt(JB));
		KK_Check.setOnCheckedChangeListener(toggleIt(KK));
		L_Check.setOnCheckedChangeListener(toggleIt(L));
		LP_Check.setOnCheckedChangeListener(toggleIt(LP));
		MP_Check.setOnCheckedChangeListener(toggleIt(MP));

		SwitchCompat logCheck = (SwitchCompat) findViewById(R.id.logCheck);
		
		Boolean log = pref.getBoolean("log", false);
		
		if (log == true){
			
			logCheck.setChecked(true);
			
		}
		
		logCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				
				
					edit.putBoolean("log", arg1);
					edit.apply();
				
				
			}
		});
		
		
		logLayout = (RelativeLayout) findViewById(R.id.logLayout);
		openLayout = (ImageView) findViewById(R.id.openLayout);
		
		logLayout.setVisibility(View.GONE);
		

	}
	
	
	@Override
	public boolean onKeyDown(int keycode, KeyEvent e){
		
		switch (keycode){
			case KeyEvent.KEYCODE_MENU :
				toggleView(this.getWindow().getCurrentFocus());
		}
		
		return super.onKeyDown(keycode, e);
	}
	
public void toggleView(View view){

		
		if (openLayout.getVisibility() == View.VISIBLE){
			
			logLayout.setVisibility(View.VISIBLE);
			openLayout.setVisibility(View.GONE);
            ViewPropertyAnimator.animate(logLayout).translationYBy(-logLayout.getHeight()).setDuration(200).setInterpolator(new DecelerateInterpolator()).start();
			
		} else {

			logLayout.setVisibility(View.GONE);
			openLayout.setVisibility(View.VISIBLE);
            ViewPropertyAnimator.animate(logLayout).translationYBy(logLayout.getHeight()).setDuration(200).setInterpolator(new DecelerateInterpolator()).start();
		}
		
	}
	
	public void settings(View view){
		
		startActivity(new Intent(this, PrefSettings.class));
		
	}
	
	
	

	public OnCheckedChangeListener toggleIt(int ver) {

		if (ver > 7)
			return null; //Return if greater than index

		final int version = ver;

		OnCheckedChangeListener occl = new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton p1, boolean p2) {
				// TODO: Implement this method
				if (p2 == true) {

					toggleEgg(version);
					
					edit.putBoolean("enabled", true);

					colorize();
					edit.apply();

				}

				if (p2 == false) {
					
					
					if (areChecked() == false) {

						edit.putBoolean("enabled", false);

						decolorize();
						edit.apply();

					}

				}

			}

		};

		return occl;

	}

	public boolean areChecked() {

		if (GB_Check.isChecked() == true)
			return true;

		if (HC_Check.isChecked() == true)
			return true;

		if (ICS_Check.isChecked() == true)
			return true;

		if (JB_Check.isChecked() == true)
			return true;

		if (KK_Check.isChecked() == true)
			return true;
		
		if (L_Check.isChecked() == true)
			return true;
		
		if (LP_Check.isChecked() == true)
			return true;

        if(MP_Check.isChecked() == true)
            return true;

		return false;
	}

	public void toggle(View view) {

		enabled = pref.getBoolean("enabled", true);

		if (enabled == false) {

			edit.putBoolean("enabled", true);

			colorize();

		} else if (enabled == true) {

			edit.putBoolean("enabled", false);

			decolorize();

		}

		edit.apply();

	}

	public void toggleEgg(int i) {

		// Enable i
		if (i > 7)
			return; // Return if i is greater than array index

		disableExcept(i);

		edit.putString("egg_name", versionSName[i]);
		edit.apply();

	}

	public void disableExcept(int i) {

		if (i > 7)
			return; // Return if i is greater than array index

		for (int j = 0; j < i; j++) {

			if (versionSName[j].equals("GB")) {

				// disable GB
				GB_Check.setChecked(false);

			} else if (versionSName[j].equals("HC")) {

				// disable HC
				HC_Check.setChecked(false);

			} else if (versionSName[j].equals("ICS")) {

				// disable ICS
				ICS_Check.setChecked(false);

			} else if (versionSName[j].equals("JB")) {

				// disable JB
				JB_Check.setChecked(false);

			} else if (versionSName[j].equals("KK")) {

				// disable KK
				KK_Check.setChecked(false);

			} else if (versionSName[j].equals("L")) {

				// disable L
				L_Check.setChecked(false);

			} else if (versionSName[j].equals("LP")) {

				// disable LP
				LP_Check.setChecked(false);

			} else if (versionSName[j].equals("MP")) {

                // disable MP
                MP_Check.setChecked(false);

            }

		}

		for (int j = 7; j > i; j--) {

			if (versionSName[j].equals("GB")) {

				// disable GB
				GB_Check.setChecked(false);

			} else if (versionSName[j].equals("HC")) {

				// disable HC
				HC_Check.setChecked(false);

			} else if (versionSName[j].equals("ICS")) {

				// disable ICS
				ICS_Check.setChecked(false);

			} else if (versionSName[j].equals("JB")) {

				// disable JB
				JB_Check.setChecked(false);

			} else if (versionSName[j].equals("KK")) {

				// disable KK
				KK_Check.setChecked(false);

			} else if (versionSName[j].equals("L")) {

				// disable L
				L_Check.setChecked(false);

			} else if (versionSName[j].equals("LP")) {

				// disable LP
				LP_Check.setChecked(false);

			} else if (versionSName[j].equals("MP")) {

                // disable MP
                MP_Check.setChecked(false);

            }

		}

	}

	public void ginger(View view) {

		startActivity(new Intent(this,
				areeb.xposed.eggster.gb.PlatLogoActivity.class));

	}

	public void honey(View view) {

		startActivity(new Intent(this,
				areeb.xposed.eggster.hc.PlatLogoActivity.class));

	}
	
	public void ics(View view) {

		startActivity(new Intent(this,
				areeb.xposed.eggster.ics.PlatLogoActivity.class));

	}


	public void jelly(View view) {

		startActivity(new Intent(this,
				areeb.xposed.eggster.jb.PlatLogoActivity.class));

	}
	
	public void kitkat(View view) {

		startActivity(new Intent(this,
				areeb.xposed.eggster.kk.PlatLogoActivity.class));

	}

	public void androidl(View view) {

		startActivity(new Intent(this,
				areeb.xposed.eggster.l.PlatLogoActivity.class));

	}
	
	public void lollipop(View view) {

		startActivity(new Intent(this,
				areeb.xposed.eggster.lp.PlatLogoActivity.class));

	}

    public void mpreview(View view) {

        startActivity(new Intent(this,
                areeb.xposed.eggster.mp.PlatLogoActivity.class));

    }
	
	public void colorize() {

		setColorFilter(gbImg, 105, 206, 91);
		setColorFilter(hcImg, 250, 120, 40);
		setColorFilter(icsImg, 138, 90, 90);
		setColorFilter(jbImg, 255, 68, 68);
		setColorFilter(kkImg, 160, 90, 50);
		setColorFilter(lImg, 70, 161, 229);
		setColorFilter(lpImg, 255, 58, 118);
        setColorFilter(mpImg, 250, 140, 0);
	}

	public void decolorize() {

		setColorFilter(gbImg, 100, 100, 100);
		setColorFilter(hcImg, 100, 100, 100);
		setColorFilter(icsImg, 100, 100, 100);
		setColorFilter(jbImg, 100, 100, 100);
		setColorFilter(kkImg, 100, 100, 100);
		setColorFilter(lImg, 100, 100, 100);
		setColorFilter(lpImg, 100, 100, 100);
        setColorFilter(mpImg, 100, 100, 100);
	}

	public static void setColorFilter(ImageView iv, float redValue, float greenValue,
			float blueValue) {

		redValue = redValue / 255;
		blueValue = blueValue / 255;
		greenValue = greenValue / 255;

		/*
		 * However I have learned about different positions of matrix by hit and
		 * trial, here I'll explain what little I got from it.
		 * 
		 * Color Matrix is a 4x5 matrix (4 rows and 5 columns) with 20 elements.
		 * Every element has a role play, but I'll only discuss elements that
		 * I've used, because I'm not sure about others, and I'm little unsure
		 * about these too.
		 * 
		 * The below results are purely based on my observations by hit and
		 * trial and may be entirely wrong for any other image. Image here used
		 * was kitkat easter egg with black bg and white and grey fg.
		 * 
		 * a(1,1) = RED value of white foreground a(2,2) = GREEN value of white
		 * foreground a(3,3) = BLUE value of white foreground
		 * 
		 * (According to my input, they should've given grey color, but they
		 * give translucent white (more opaque when you're nearer to one),
		 * however I wanted that only :p
		 * 
		 * a(1,4) = RED value of black background a(2,4) = GREEN value of black
		 * background a(3,4) = BLUE value of black background
		 * 
		 * (They somehow also control alpha(opacity) of image)
		 * 
		 * a(4,4) = Overall alpha of image
		 * 
		 * The elements of matrix require scale values in RGB. Meaning value
		 * should be between 0 and 1 in float. This should explain my need to
		 * divide values by 255 as RGB values range from 0 to 255
		 * 
		 * So, matrix should look like this (F = Foreground, B = Background)
		 * 
		 * | RF, 0 , 0 , RB, 0, | | 0 , GF, 0 , GB, 0, | | 0 , 0 , BF, BB, 0, |
		 * | 0 , 0 , 0 , AA, 0 |
		 */

		float[] colorMatrix = { 0.5f, 0, 0, redValue, 0, 0, 0.5f, 0,
				greenValue, 0, 0, 0, 0.5f, blueValue, 0, 0, 0, 0, 1, 0 };

		ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);

		iv.setColorFilter(colorFilter);

	}
}

/*
 * CAR (Commonly Answered Rants)
 * 
 * # You may see many redundant methods. That's because I wanted to use
 * CheckButtons rather than RadioButtons and thus had to implement some
 * unneeded(normally) methods.
 * 
 * # Code may seem ugly to some. Sorry, it's the best I can do. You may also
 * blame my ignorance, because I could have searched to make it better. And it
 * was made in my internet and PC deprived hours on AIDE as time pass. So, this
 * is very probable you may find things that could've done by easier method.
 * 
 * # Pardon any unwanted result you get by any comment or code. Also pardon my
 * lack of knowledge. Kindly inform of any shortcomings if you can. I will
 * highly appreciate that.
 * 
 * # If you think you can improve it or do a better job(in code department),
 * contribute to the project. Constructive criticism and any advice is welcome.
 * 
 * # You can report about any glitch you face by sending me log of error. You
 * can do it by typing 'logcat Xposed:I *:S' (without quotes) in terminal in
 * case of module malfunction, or in case of a Force Close, you can type 'logcat
 * AndroidRuntime:I *:S'.
 * 
 * Remember I'm just a PM or a post away :)
 * 
 * **Credits**
 * 
 * # rovo89 for Xposed
 * # Giupy 99 for his ultimate commits when I couldn't handle it
 * # GermainZ for a small peek of code from Crappalinks
 * # AOSP for all easter eggs, their codes and resources
 * # Geo Piskas for Jelly Bean Flinger code saving me time to port that
 * # Zhuowei for his NyanDream project, which enabled me to take peeks
 *   for some non-functional code fixing in Kitkat Easter Egg and ICS one
 * # BoD for Android Switch backport library
 * # Photoshop, AIDE, and other tools, etc
 * # Please remind me if I forgot someone
 */
