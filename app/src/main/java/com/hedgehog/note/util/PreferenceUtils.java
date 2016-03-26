package com.hedgehog.note.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hedge_hog on 16/3/26.
 */
public class PreferenceUtils {

    /**
     * 保存一个boolean型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param value 值
     */
    public static void putBoolean(Context context, String preferencesName, int mode, String key, boolean value){
        context.getSharedPreferences(preferencesName, mode).edit().putBoolean(key, value).commit();
    }

    /**
     * 保存一个boolean型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param value 值
     */
    public static void putBoolean(Context context, String preferencesName, String key, boolean value){
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
    }

    /**
     * 保存一个boolean型的值到默认的Preference中
     * @param context 上下文
     * @param key 键
     * @param value 值
     */
    public static void putBoolean(Context context, String key, boolean value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).commit();
    }

    /**
     * 从指定的Preference中取出一个boolean型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static boolean getBoolean(Context context, String preferencesName, int mode, String key, boolean defaultValue){
        return context.getSharedPreferences(preferencesName, mode).getBoolean(key, defaultValue);
    }

    /**
     * 从指定的Preference中取出一个boolean型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static boolean getBoolean(Context context, String preferencesName, String key, boolean defaultValue){
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getBoolean(key, defaultValue);
    }

    /**
     * 从指定的Preference中取出一个boolean型的值，默认值为false
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @return 值
     */
    public static boolean getBoolean(Context context, String preferencesName, String key){
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getBoolean(key, false);
    }

    /**
     * 从默认的Preference中取出一个boolean型的值
     * @param context 上下文
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

    /**
     * 从默认的Preference中取出一个boolean型的值，默认值为false
     * @param context 上下文
     * @param key 键
     * @return 值
     */
    public static boolean getBoolean(Context context, String key){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false);
    }

    /**
     * 保存一个float型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param value 值
     */
    public static void putFloat(Context context, String preferencesName, int mode, String key, float value){
        context.getSharedPreferences(preferencesName, mode).edit().putFloat(key, value).commit();
    }

    /**
     * 保存一个float型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param value 值
     */
    public static void putFloat(Context context, String preferencesName, String key, float value){
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit().putFloat(key, value).commit();
    }

    /**
     * 保存一个float型的值到默认的Preference中
     * @param context 上下文
     * @param key 键
     * @param value 值
     */
    public static void putFloat(Context context, String key, float value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(key, value).commit();
    }

    /**
     * 从指定的Preference中取出一个float型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static float getFloat(Context context, String preferencesName, int mode, String key, float defaultValue){
        return context.getSharedPreferences(preferencesName, mode).getFloat(key, defaultValue);
    }

    /**
     * 从指定的Preference中取出一个float型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static float getFloat(Context context, String preferencesName, String key, float defaultValue){
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getFloat(key, defaultValue);
    }

    /**
     * 从指定的Preference中取出一个float型的值，默认值为0
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @return 值
     */
    public static float getFloat(Context context, String preferencesName, String key){
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getFloat(key, 0);
    }

    /**
     * 从默认的Preference中取出一个float型的值
     * @param context 上下文
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static float getFloat(Context context, String key, float defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(key, defaultValue);
    }

    /**
     * 从默认的Preference中取出一个float型的值，默认值为0
     * @param context 上下文
     * @param key 键
     * @return 值
     */
    public static float getFloat(Context context, String key){
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(key, 0);
    }


    /**
     * 保存一个int型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param value 值
     */
    public static void putInt(Context context, String preferencesName, int mode, String key, int value){
        context.getSharedPreferences(preferencesName, mode).edit().putInt(key, value).commit();
    }

    /**
     * 保存一个int型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param value 值
     */
    public static void putInt(Context context, String preferencesName, String key, int value){
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
    }

    /**
     * 保存一个int型的值到默认的Preference中
     * @param context 上下文
     * @param key 键
     * @param value 值
     */
    public static void putInt(Context context, String key, int value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).commit();
    }

    /**
     * 从指定的Preference中取出一个int型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static int getInt(Context context, String preferencesName, int mode, String key, int defaultValue){
        return context.getSharedPreferences(preferencesName, mode).getInt(key, defaultValue);
    }

    /**
     * 从指定的Preference中取出一个int型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static int getInt(Context context, String preferencesName, String key, int defaultValue){
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getInt(key, defaultValue);
    }

    /**
     * 从指定的Preference中取出一个int型的值，默认值为0
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @return 值
     */
    public static int getInt(Context context, String preferencesName, String key){
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getInt(key, 0);
    }

    /**
     * 从默认的Preference中取出一个int型的值
     * @param context 上下文
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static int getInt(Context context, String key, int defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defaultValue);
    }

    /**
     * 从默认的Preference中取出一个int型的值，默认值为0
     * @param context 上下文
     * @param key 键
     * @return 值
     */
    public static int getInt(Context context, String key){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, 0);
    }

    /**
     * 保存一个long型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param value 值
     */
    public static void putLong(Context context, String preferencesName, int mode, String key, long value){
        context.getSharedPreferences(preferencesName, mode).edit().putLong(key, value).commit();
    }

    /**
     * 保存一个long型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param value 值
     */
    public static void putLong(Context context, String preferencesName, String key, long value){
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit().putLong(key, value).commit();
    }

    /**
     * 保存一个long型的值到默认的Preference中
     * @param context 上下文
     * @param key 键
     * @param value 值
     */
    public static void putLong(Context context, String key, long value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(key, value).commit();
    }

    /**
     * 从指定的Preference中取出一个long型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static long getLong(Context context, String preferencesName, int mode, String key, long defaultValue){
        return context.getSharedPreferences(preferencesName, mode).getLong(key, defaultValue);
    }

    /**
     * 从指定的Preference中取出一个long型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static long getLong(Context context, String preferencesName, String key, long defaultValue){
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getLong(key, defaultValue);
    }

    /**
     * 从指定的Preference中取出一个long型的值，默认值为0
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @return 值
     */
    public static long getLong(Context context, String preferencesName, String key){
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getLong(key, 0);
    }

    /**
     * 从默认的Preference中取出一个long型的值
     * @param context 上下文
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static long getLong(Context context, String key, long defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, defaultValue);
    }

    /**
     * 从默认的Preference中取出一个long型的值，默认值为0
     * @param context 上下文
     * @param key 键
     * @return 值
     */
    public static long getLong(Context context, String key){
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, 0);
    }

    /**
     * 保存一个String型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param value 值
     */
    public static void putString(Context context, String preferencesName, int mode, String key, String value){
        context.getSharedPreferences(preferencesName, mode).edit().putString(key, value).commit();
    }

    /**
     * 保存一个String型的值到指定的Preference中
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param value 值
     */
    public static void putString(Context context, String preferencesName, String key, String value){
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    /**
     * 保存一个String型的值到默认的Preference中
     * @param context 上下文
     * @param key 键
     * @param value 值
     */
    public static void putString(Context context, String key, String value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).commit();
    }

    /**
     * 从指定的Preference中取出一个String型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static String getString(Context context, String preferencesName, int mode, String key, String defaultValue){
        return context.getSharedPreferences(preferencesName, mode).getString(key, defaultValue);
    }

    /**
     * 从指定的Preference中取出一个String型的值
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static String getString(Context context, String preferencesName, String key, String defaultValue){
        return context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getString(key, defaultValue);
    }

    /**
     * 从默认的Preference中取出一个String型的值
     * @param context 上下文
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public static String getString(Context context, String key, String defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue);
    }

    /**
     * 从默认的Preference中取出一个String型的值，默认值为0
     * @param context 上下文
     * @param key 键
     * @return 值
     */
    public static String getString(Context context, String key){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, null);
    }

    /**
     * 保存一个Set<String>到指定的Preference中，如果当前系统的SDK版本小于11，则会将Set<String>转换成JSON字符串保存
     * @param preferences Preferences
     * @param key 键
     * @param value 值
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void putStringSet(SharedPreferences preferences, String key, Set<String> value){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            preferences.edit().putStringSet(key, value).commit();
        }else{
            putObject(preferences, key, value);
        }
    }

    /**
     * 保存一个Set<String>到指定的Preference中，如果当前系统的SDK版本小于11，则会将Set<String>转换成JSON字符串保存
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param value 值
     */
    public static void putStringSet(Context context, String preferencesName, int mode, String key, Set<String> value){
        SharedPreferences preferences = context.getSharedPreferences(preferencesName, mode);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            preferences.edit().putStringSet(key, value).commit();
        }else{
            putObject(preferences, key, value);
        }
    }

    /**
     * 保存一个Set<String>到指定的Preference中，如果当前系统的SDK版本小于11，则会将Set<String>转换成JSON字符串保存
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param value 值
     */
    public static void putStringSet(Context context, String preferencesName, String key, Set<String> value){
        SharedPreferences preferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            preferences.edit().putStringSet(key, value).commit();
        }else{
            putObject(preferences, key, value);
        }
    }

    /**
     * 保存一个Set<String>到默认的Preference中，如果当前系统的SDK版本小于11，则会将Set<String>转换成JSON字符串保存
     * @param context 上下文
     * @param key 键
     * @param value 值
     */
    public static void putStringSet(Context context, String key, Set<String> value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            preferences.edit().putStringSet(key, value).commit();
        }else{
            putObject(preferences, key, value);
        }
    }

    /**
     * 从指定的Preference中取出一个Set<String>，如果当前系统的SDK版本小于11，则会先取出JSON字符串然后再转换成Set<String>
     * @param preferences Preferences
     * @param key 键
     * @param defaultValue 默认值
     * @return 字符串集合
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(SharedPreferences preferences, String key, Set<String> defaultValue){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            return preferences.getStringSet(key, defaultValue);
        }else{
            Set<String> strings = getObject(preferences, key, new TypeToken<Set<String>>(){}.getType());
            if(strings == null){
                strings = defaultValue;
            }
            return strings;
        }
    }

    /**
     * 从指定的Preference中取出一个Set<String>，如果当前系统的SDK版本小于11，则会先取出JSON字符串然后再转换成Set<String>
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param defaultValue 默认值
     * @return 字符串集合
     */
    public static Set<String> getStringSet(Context context, String preferencesName, int mode, String key, Set<String> defaultValue){
        SharedPreferences preferences = context.getSharedPreferences(preferencesName, mode);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            return preferences.getStringSet(key, defaultValue);
        }else{
            Set<String> strings = getObject(preferences, key, new TypeToken<Set<String>>(){}.getType());
            if(strings == null){
                strings = defaultValue;
            }
            return strings;
        }
    }

    /**
     * 从指定的Preference中取出一个Set<String>，如果当前系统的SDK版本小于11，则会先取出JSON字符串然后再转换成Set<String>
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param defaultValue 默认值
     * @return 字符串集合
     */
    public static Set<String> getStringSet(Context context, String preferencesName, String key, Set<String> defaultValue){
        SharedPreferences preferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            return preferences.getStringSet(key, defaultValue);
        }else{
            Set<String> strings = getObject(preferences, key, new TypeToken<Set<String>>(){}.getType());
            if(strings == null){
                strings = defaultValue;
            }
            return strings;
        }
    }

    /**
     * 从指定的Preference中取出一个Set<String>，如果当前系统的SDK版本小于11，则会先取出JSON字符串然后再转换成Set<String>
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @return 字符串集合
     */
    public static Set<String> getStringSet(Context context, String preferencesName, String key){
        SharedPreferences preferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            return preferences.getStringSet(key, null);
        }else{
            return getObject(preferences, key, new TypeToken<Set<String>>(){}.getType());
        }
    }

    /**
     * 从默认的Preference中取出一个Set<String>，如果当前系统的SDK版本小于11，则会先取出JSON字符串然后再转换成Set<String>
     * @param context 上下文
     * @param key 键
     * @param defaultValue 默认值
     * @return 字符串集合
     */
    public static Set<String> getStringSet(Context context, String key, Set<String> defaultValue){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            return preferences.getStringSet(key, defaultValue);
        }else{
            Set<String> strings = getObject(preferences, key, new TypeToken<Set<String>>(){}.getType());
            if(strings == null){
                strings = defaultValue;
            }
            return strings;
        }
    }

    /**
     * 从默认的Preference中取出一个Set<String>，如果当前系统的SDK版本小于11，则会先取出JSON字符串然后再转换成Set<String>
     * @param context 上下文
     * @param key 键
     * @return 字符串集合
     */
    public static Set<String> getStringSet(Context context, String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            return preferences.getStringSet(key, null);
        }else{
            return getObject(preferences, key, new TypeToken<Set<String>>(){}.getType());
        }
    }

    /**
     * 保存一个对象到指定的Preference中，此对象会被格式化为JSON格式再存
     * @param preferences Preferences
     * @param key 键
     * @param object 对象
     */
    public static void putObject(SharedPreferences preferences, String key, Object object){
        preferences.edit().putString(key, new Gson().toJson(object)).commit();
    }

    /**
     * 保存一个对象到指定的Preference中，此对象会被格式化为JSON格式再存
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param object 对象
     */
    public static void putObject(Context context, String preferencesName, int mode, String key, Object object){
        context.getSharedPreferences(preferencesName, mode).edit().putString(key, new Gson().toJson(object)).commit();
    }

    /**
     * 保存一个对象到指定的Preference中，此对象会被格式化为JSON格式再存
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param object 对象
     */
    public static void putObject(Context context, String preferencesName, String key, Object object){
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit().putString(key, new Gson().toJson(object)).commit();
    }

    /**
     * 保存一个对象到默认的Preference中，此对象会被格式化为JSON格式再存
     * @param context 上下文
     * @param key 键
     * @param object 对象
     */
    public static void putObject(Context context, String key, Object object){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, new Gson().toJson(object)).commit();
    }

    /**
     * 从指定的Preference中取出一个对象
     * @param preferences Preferences
     * @param key 键
     * @param clas 对象Class
     * @return T
     */
    public static <T> T getObject(SharedPreferences preferences, String key, Class<T> clas){
        String configJson = preferences.getString(key, null);
        if(StringUtils.isNotEmpty(configJson)){
            return (T) new Gson().fromJson(configJson, clas);
        }else{
            return null;
        }
    }

    /**
     * 从指定的Preference中取出一个对象
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param clas 对象Class
     * @return T
     */
    public static <T> T getObject(Context context, String preferencesName, int mode, String key, Class<T> clas){
        String configJson = context.getSharedPreferences(preferencesName, mode).getString(key, null);
        if(StringUtils.isNotEmpty(configJson)){
            return (T) new Gson().fromJson(configJson, clas);
        }else{
            return null;
        }
    }

    /**
     * 从指定的Preference中取出一个对象
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param clas 对象Class
     * @return T
     */
    public static <T> T getObject(Context context, String preferencesName, String key, Class<T> clas){
        String configJson = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getString(key, null);
        if(StringUtils.isNotEmpty(configJson)){
            return (T) new Gson().fromJson(configJson, clas);
        }else{
            return null;
        }
    }

    /**
     * 从默认的Preference中取出一个对象
     * @param context 上下文
     * @param key 键
     * @param clas 对象Class
     * @return T
     */
    public static <T> T getObject(Context context, String key, Class<T> clas){
        String configJson = PreferenceManager.getDefaultSharedPreferences(context).getString(key, null);
        if(StringUtils.isNotEmpty(configJson)){
            return (T) new Gson().fromJson(configJson, clas);
        }else{
            return null;
        }
    }

    /**
     * 从指定的Preference中取出一个对象
     * @param preferences Preferences
     * @param key 键
     * @param typeOfT 集合类型
     * @return T
     */
    public static <T> T getObject(SharedPreferences preferences, String key, Type typeOfT){
        String configJson = preferences.getString(key, null);
        if(StringUtils.isNotEmpty(configJson)){
            return new Gson().fromJson(configJson, typeOfT);
        }else{
            return null;
        }
    }

    /**
     * 从指定的Preference中取出一个对象
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param key 键
     * @param typeOfT 集合类型
     * @return T
     */
    public static <T> T getObject(Context context, String preferencesName, int mode, String key, Type typeOfT){
        String configJson = context.getSharedPreferences(preferencesName, mode).getString(key, null);
        if(StringUtils.isNotEmpty(configJson)){
            return new Gson().fromJson(configJson, typeOfT);
        }else{
            return null;
        }
    }

    /**
     * 从指定的Preference中取出一个对象
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param key 键
     * @param typeOfT 集合类型
     * @return T
     */
    public static <T> T getObject(Context context, String preferencesName, String key, Type typeOfT){
        String configJson = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).getString(key, null);
        if(StringUtils.isNotEmpty(configJson)){
            return new Gson().fromJson(configJson, typeOfT);
        }else{
            return null;
        }
    }

    /**
     * 从默认的Preference中取出一个对象
     * @param context 上下文
     * @param key 键
     * @param typeOfT
     * @return T
     */
    public static <T> T getObject(Context context, String key, Type typeOfT){
        String configJson = PreferenceManager.getDefaultSharedPreferences(context).getString(key, null);
        if(StringUtils.isNotEmpty(configJson)){
            return new Gson().fromJson(configJson, typeOfT);
        }else{
            return null;
        }
    }

    /**
     * 删除
     * @param preferences Preferences
     * @param keys 键集合
     */
    public static void remove(SharedPreferences preferences, String... keys){
        if(keys == null){
            return;
        }
        SharedPreferences.Editor editor = preferences.edit();
        for(String key : keys){
            editor.remove(key);
        }
        editor.commit();
    }

    /**
     * 删除
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param mode 模式、类型
     * @param keys 键集合
     */
    public static void remove(Context context, String preferencesName, int mode, String... keys){
        if(keys == null){
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(preferencesName, mode).edit();
        for(String key : keys){
            editor.remove(key);
        }
        editor.commit();
    }

    /**
     * 删除
     * @param context 上下文
     * @param preferencesName Preferences名称
     * @param keys 键集合
     */
    public static void remove(Context context, String preferencesName, String... keys){
        if(keys == null){
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit();
        for(String key : keys){
            editor.remove(key);
        }
        editor.commit();
    }

    /**
     * 删除
     * @param context 上下文
     * @param keys 键集合
     */
    public static void remove(Context context, String... keys){
        if(keys == null){
            return;
        }
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        for(String key : keys){
            editor.remove(key);
        }
        editor.commit();
    }

    /**
     * Represents a generic type {@code T}. Java doesn't yet provide a way to
     * represent generic types, so this class does. Forces clients to create a
     * subclass of this class which enables retrieval the type information even at
     * runtime.
     *
     * <p>For example, to create a type literal for {@code List<String>}, you can
     * create an empty anonymous inner class:
     *
     * <p>
     * {@code TypeToken<List<String>> list = new TypeToken<List<String>>() {};}
     *
     * <p>This syntax cannot be used to create type literals that have wildcard
     * parameters, such as {@code Class<?>} or {@code List<? extends CharSequence>}.
     *
     * @author Bob Lee
     * @author Sven Mawson
     * @author Jesse Wilson
     */
    public static class TypeToken<T> {
        final Class<? super T> rawType;
        final Type type;
        final int hashCode;

        /**
         * Constructs a new type literal. Derives represented class from type
         * parameter.
         *
         * <p>Clients create an empty anonymous subclass. Doing so embeds the type
         * parameter in the anonymous class's type hierarchy so we can reconstitute it
         * at runtime despite erasure.
         */
        @SuppressWarnings("unchecked")
        protected TypeToken() {
            this.type = getSuperclassTypeParameter(getClass());
            this.rawType = (Class<? super T>) $Gson$Types.getRawType(type);
            this.hashCode = type.hashCode();
        }

        /**
         * Unsafe. Constructs a type literal manually.
         */
        @SuppressWarnings("unchecked")
        TypeToken(Type type) {
            this.type = $Gson$Types.canonicalize($Gson$Preconditions.checkNotNull(type));
            this.rawType = (Class<? super T>) $Gson$Types.getRawType(this.type);
            this.hashCode = this.type.hashCode();
        }

        /**
         * Returns the type from super class's type parameter in {@link $Gson$Types#canonicalize
         * canonical form}.
         */
        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * Returns the raw (non-generic) type for this type.
         */
        public final Class<? super T> getRawType() {
            return rawType;
        }

        /**
         * Gets underlying {@code Type} instance.
         */
        public final Type getType() {
            return type;
        }

        /**
         * Check if this type is assignable from the given class object.
         *
         * @deprecated this implementation may be inconsistent with javac for types
         *     with wildcards.
         */
        @Deprecated
        public boolean isAssignableFrom(Class<?> cls) {
            return isAssignableFrom((Type) cls);
        }

        /**
         * Check if this type is assignable from the given Type.
         *
         * @deprecated this implementation may be inconsistent with javac for types
         *     with wildcards.
         */
        @Deprecated
        public boolean isAssignableFrom(Type from) {
            if (from == null) {
                return false;
            }

            if (type.equals(from)) {
                return true;
            }

            if (type instanceof Class<?>) {
                return rawType.isAssignableFrom($Gson$Types.getRawType(from));
            } else if (type instanceof ParameterizedType) {
                return isAssignableFrom(from, (ParameterizedType) type,
                        new HashMap<String, Type>());
            } else if (type instanceof GenericArrayType) {
                return rawType.isAssignableFrom($Gson$Types.getRawType(from))
                        && isAssignableFrom(from, (GenericArrayType) type);
            } else {
                throw buildUnexpectedTypeError(
                        type, Class.class, ParameterizedType.class, GenericArrayType.class);
            }
        }

        /**
         * Check if this type is assignable from the given type token.
         *
         * @deprecated this implementation may be inconsistent with javac for types
         *     with wildcards.
         */
        @Deprecated
        public boolean isAssignableFrom(TypeToken<?> token) {
            return isAssignableFrom(token.getType());
        }

        /**
         * Private helper function that performs some assignability checks for
         * the provided GenericArrayType.
         */
        private static boolean isAssignableFrom(Type from, GenericArrayType to) {
            Type toGenericComponentType = to.getGenericComponentType();
            if (toGenericComponentType instanceof ParameterizedType) {
                Type t = from;
                if (from instanceof GenericArrayType) {
                    t = ((GenericArrayType) from).getGenericComponentType();
                } else if (from instanceof Class<?>) {
                    Class<?> classType = (Class<?>) from;
                    while (classType.isArray()) {
                        classType = classType.getComponentType();
                    }
                    t = classType;
                }
                return isAssignableFrom(t, (ParameterizedType) toGenericComponentType,
                        new HashMap<String, Type>());
            }
            // No generic defined on "to"; therefore, return true and let other
            // checks determine assignability
            return true;
        }

        /**
         * Private recursive helper function to actually do the type-safe checking
         * of assignability.
         */
        private static boolean isAssignableFrom(Type from, ParameterizedType to,
                                                Map<String, Type> typeVarMap) {

            if (from == null) {
                return false;
            }

            if (to.equals(from)) {
                return true;
            }

            // First figure out the class and any type information.
            Class<?> clazz = $Gson$Types.getRawType(from);
            ParameterizedType ptype = null;
            if (from instanceof ParameterizedType) {
                ptype = (ParameterizedType) from;
            }

            // Load up parameterized variable info if it was parameterized.
            if (ptype != null) {
                Type[] tArgs = ptype.getActualTypeArguments();
                TypeVariable<?>[] tParams = clazz.getTypeParameters();
                for (int i = 0; i < tArgs.length; i++) {
                    Type arg = tArgs[i];
                    TypeVariable<?> var = tParams[i];
                    while (arg instanceof TypeVariable<?>) {
                        TypeVariable<?> v = (TypeVariable<?>) arg;
                        arg = typeVarMap.get(v.getName());
                    }
                    typeVarMap.put(var.getName(), arg);
                }

                // check if they are equivalent under our current mapping.
                if (typeEquals(ptype, to, typeVarMap)) {
                    return true;
                }
            }

            for (Type itype : clazz.getGenericInterfaces()) {
                if (isAssignableFrom(itype, to, new HashMap<String, Type>(typeVarMap))) {
                    return true;
                }
            }

            // Interfaces didn't work, try the superclass.
            Type sType = clazz.getGenericSuperclass();
            return isAssignableFrom(sType, to, new HashMap<String, Type>(typeVarMap));
        }

        /**
         * Checks if two parameterized types are exactly equal, under the variable
         * replacement described in the typeVarMap.
         */
        private static boolean typeEquals(ParameterizedType from,
                                          ParameterizedType to, Map<String, Type> typeVarMap) {
            if (from.getRawType().equals(to.getRawType())) {
                Type[] fromArgs = from.getActualTypeArguments();
                Type[] toArgs = to.getActualTypeArguments();
                for (int i = 0; i < fromArgs.length; i++) {
                    if (!matches(fromArgs[i], toArgs[i], typeVarMap)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        private static AssertionError buildUnexpectedTypeError(
                Type token, Class<?>... expected) {

            // Build exception message
            StringBuilder exceptionMessage =
                    new StringBuilder("Unexpected type. Expected one of: ");
            for (Class<?> clazz : expected) {
                exceptionMessage.append(clazz.getName()).append(", ");
            }
            exceptionMessage.append("but got: ").append(token.getClass().getName())
                    .append(", for type token: ").append(token.toString()).append('.');

            return new AssertionError(exceptionMessage.toString());
        }

        /**
         * Checks if two types are the same or are equivalent under a variable mapping
         * given in the type map that was provided.
         */
        private static boolean matches(Type from, Type to, Map<String, Type> typeMap) {
            return to.equals(from)
                    || (from instanceof TypeVariable
                    && to.equals(typeMap.get(((TypeVariable<?>) from).getName())));

        }

        @Override public final int hashCode() {
            return this.hashCode;
        }

        @Override public final boolean equals(Object o) {
            return o instanceof TypeToken<?>
                    && $Gson$Types.equals(type, ((TypeToken<?>) o).type);
        }

        @Override public final String toString() {
            return $Gson$Types.typeToString(type);
        }

        /**
         * Gets type literal for the given {@code Type} instance.
         */
        public static TypeToken<?> get(Type type) {
            return new TypeToken<Object>(type);
        }

        /**
         * Gets type literal for the given {@code Class} instance.
         */
        public static <T> TypeToken<T> get(Class<T> type) {
            return new TypeToken<T>(type);
        }
    }

}
