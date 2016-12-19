package util;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * 
 * Title: poperson项目组 <br>
 * Description: <br>
 * Copyright: 广州互缘网络科技有限公司 (C) 2013 <br>
 * 
 * @author <a href="mailto:zhuhongzheng@poperson.com">samson</a><br>
 * @e-mail: zhuhongzheng@poperson.com <br>
 * @version 1.0 <br>
 * @creatdate 2013-8-30 下午3:02:15 <br>
 * 
 */
public class GsonUtil
{
    private final static ThreadLocal<Gson> prettyGsonHolder = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            GsonBuilder builder = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping();
            builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
            return builder.create();
        }
    };
    
    private final static ThreadLocal<Gson> gsonHolder = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
            builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
            return builder.create();
        }
    };

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        return gsonHolder.get().toJson(obj);
    }
    
    public static String toPrettyJson(Object obj)
    {
        return prettyGsonHolder.get().toJson(obj);
    }
    
    public static <T> T fromJson(String jsonStr, Class<T> classOfT)throws JsonSyntaxException
    {
        return gsonHolder.get().fromJson(jsonStr, classOfT);
    }
    
    public static <T> T fromJson(String jsonStr, Type typeOfT)throws JsonSyntaxException
    {
        return gsonHolder.get().fromJson(jsonStr, typeOfT);
    }
}
