package com.thealamin.isecure;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by Istiyak on 2/22/2016.
 */
public class ReplaceFont {

    public static void replacefont(Context context,
                                   String nameOfFontBeignReplace,
                                   String nameoffontinasset){
        Typeface cusTomFont = Typeface.createFromAsset(context.getAssets() , nameoffontinasset);
        replaceFont(nameOfFontBeignReplace,cusTomFont);
    }

    private static void replaceFont(String nameOfFontBeignReplace, Typeface cusTomFont) {

        try {
            Field myField = Typeface.class.getDeclaredField(nameOfFontBeignReplace);
            myField.setAccessible(true);
            myField.set(null,cusTomFont);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
