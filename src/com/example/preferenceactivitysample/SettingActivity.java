package com.example.preferenceactivitysample;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class SettingActivity extends PreferenceActivity {
	private static final String[] PREFERENCE_KEYS = {"checkbox_key", "edittext_key", "list_key", "ringtone_key", "color_key"};
	
    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
        Preference prefHogeHoge = findPreference("test_key");
        prefHogeHoge.setOnPreferenceClickListener(new PrefClickListener());
        
        for (String key: PREFERENCE_KEYS) {
	        setPreferenceSummary(key);
        }
    }
	private class PrefClickListener implements OnPreferenceClickListener {
        public boolean onPreferenceClick(Preference preference) {
            // クリックされたときの処理
            Log.i("pref", "clicked");
            return true;
        }
    }
	
	/**
	 * @see http://stackoverflow.com/questions/6725105/ringtonepreference-not-firing-onsharedpreferencechanged
	 */
	private class RingtoneChangeListener implements OnPreferenceChangeListener {
		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
            preference.setSummary((String)newValue);
            
            /**
             * True to update the state of the Preference with the new value. 
             * @see https://developer.android.com/intl/ja/reference/android/preference/Preference.OnPreferenceChangeListener.html
             */
			return true;
		}
    }

    @SuppressWarnings("deprecation")
	@Override  
    protected void onResume() {  
        super.onResume();  
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);  
        findPreference("ringtone_key").setOnPreferenceChangeListener(new RingtoneChangeListener());
        
    }  
       
    @SuppressWarnings("deprecation")
	@Override  
    protected void onPause() {  
        super.onPause();  
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);  
    }  
      
    /**
     * @see http://y-anz-m.blogspot.jp/2010/07/androidpreference-summary.html
     */
    private SharedPreferences.OnSharedPreferenceChangeListener listener =   
        new SharedPreferences.OnSharedPreferenceChangeListener() {  
           
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {  
			setPreferenceSummary(sharedPreferences, key);
        }

    };  
    
	private void setPreferenceSummary(String key) {
		setPreferenceSummary(PreferenceManager.getDefaultSharedPreferences(this), key);
	}
	private void setPreferenceSummary(SharedPreferences sharedPreferences, String key) {
		@SuppressWarnings("deprecation")
		Preference pref = findPreference(key);  
		if (pref == null) {
			return;
		}
    	if (key.equals("checkbox_key")) {
            pref.setSummary(sharedPreferences.getBoolean(key, false) ? "チェック" : "未チェック");
    	} else if (key.equals("color_key")) {
    		int color = sharedPreferences.getInt(key, 0xffffffff);
            pref.setSummary("alpha:" + Color.alpha(color) + " R:" + Color.red(color) + " G:" + Color.green(color) + " B:" + Color.blue(color));
    	} else {
            pref.setSummary(sharedPreferences.getString(key, ""));
    	}
	}  
}
