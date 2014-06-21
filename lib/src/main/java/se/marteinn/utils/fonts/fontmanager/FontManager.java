package se.marteinn.utils.fonts.fontmanager;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;


/**
 * Handles dynamic font display, first load up the fonts with define,
 * then use either get or annotation to apply typeface.
 *
 * Created by martinsandstrom on 2014-06-21.
 */
public class FontManager {
    private static FontManager mInstance;

    private HashMap<String, Typeface> mFonts = new HashMap<String, Typeface>();
    private Context mContext;

    static public FontManager getInstance() {
        return mInstance;
    }

    static public FontManager createInstance(Context context) {
        mInstance = new FontManager(context);
        return mInstance;
    }

    /**
     * Constructor
     */
    public FontManager(Context context) {
        mContext = context;
    }

    /**
     * Builds a typeface list by font file name, the file name are after apply used as typeface key.
     */
    public FontManager define(String[] fonts) {
        Typeface typeface;

        for (String font : fonts) {
            typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/"+font);
            mFonts.put(font, typeface);
        }

        return this;
    }

    /**
     * Retrive typeface by name.
     */
    public Typeface get(String font) {
        return mFonts.get(font);
    }

    /**
     * Apply fonts defined by annotation.
     */
    public void apply(Object activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        ApplyFont annotation;

        for(Field field : fields) {
            annotation = field.getAnnotation(ApplyFont.class);

            if (annotation != null) {
                try {
                    Object item = field.get(activity);
                    Method method = item.getClass().getMethod("setTypeface", Typeface.class);

                    Typeface typeface = FontManager.getInstance().get(annotation.value());
                    method.invoke(item, typeface);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
