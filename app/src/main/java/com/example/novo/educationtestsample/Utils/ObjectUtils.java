package com.example.novo.educationtestsample.Utils;

import java.lang.reflect.Field;

/**
 * Created by Hisham on 1/18/2016.
 */
public class ObjectUtils {

    /**
     * Dump all the values inside the object using reflection.
     *
     * @param object
     * @return all object values in a string
     */
    public String dumpObject(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append(object.getClass().getSimpleName()).append('{');

        boolean firstRound = true;

        for (Field field : fields) {
            if (!firstRound) {
                sb.append(", ");
            }
            firstRound = false;
            field.setAccessible(true);
            try {
                final Object fieldObj = field.get(object);
                final String value;
                if (null == fieldObj) {
                    value = "null";
                } else {
                    value = fieldObj.toString();
                }
                sb.append(field.getName()).append('=').append('\'')
                        .append(value).append('\'');
            } catch (IllegalAccessException ignore) {
                // this should never happen
            }
        }
        sb.append('}');
        return sb.toString();
    }

}
