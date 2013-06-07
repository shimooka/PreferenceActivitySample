PreferenceActivitySample
========================
これは何？
-

PreferenceActivityを使ったサンプルアプリケーションのeclipseプロジェクト一式です。

内容は以下の様な感じ。

* MainActivityから設定画面(SettingActivity extends PreferenceActivity)を呼び出し
* SharedPreferences.OnSharedPreferenceChangeListenerを使ったサマリーの自動更新
* OnPreferenceChangeListenerの使用例
 * RingtonePreference更新時にSharedPreferences.OnSharedPreferenceChangeListenerが呼び出されない件への対応

関連リンク
-

* [ColorPickerPreference] - 中で使っているカラーピッカー
* [Y.A.M の 雑記帳: Android　Preference の summary を動的に変更]
* [android - RingtonePreference not firing onSharedPreferenceChanged - Stack Overflow]

 [ColorPickerPreference]:https://github.com/attenzione/android-ColorPickerPreference
 [android - RingtonePreference not firing onSharedPreferenceChanged - Stack Overflow]:http://stackoverflow.com/questions/6725105/ringtonepreference-not-firing-onsharedpreferencechanged
 [Y.A.M の 雑記帳: Android　Preference の summary を動的に変更]:http://y-anz-m.blogspot.jp/2010/07/androidpreference-summary.html
