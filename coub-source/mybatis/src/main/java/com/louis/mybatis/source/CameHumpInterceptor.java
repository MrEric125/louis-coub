package com.louis.mybatis.source;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Statement;
import java.util.*;


/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
@Intercepts(
        @Signature(type = ResultSetHandler.class,
                method = "handleResultSets",
                args = {Statement.class})
)
public class CameHumpInterceptor implements Interceptor {
    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //先执行得到结采，再对结采进行处理
        List<Object> list = (List<Object>) invocation.proceed();
        for (Object object : list) {
//如采结采是Map 类型，就对Map 的key 进行转换
            if (object instanceof Map) {
                processMap((Map) object);
            } else {
                break;

            }
        }
        return list;
    }

    private void processMap(Map map) {
        Set<String> keySet = new HashSet<String>(map.keySet());
        for (String key : keySet) {
// 将以大写开头的字符串转换为小写，如采包含下画线也会处理为驼峰
// 此处只通过这两个简单的标识来判断是否进行转换
            if ((key.charAt(0) >= 'A' && key.charAt(0) <= 'Z')
                    || key.indexOf("_") >= 0) {
                Object value = map.get(key);
                map.remove(key);
                map.put(underlineToCamelhump(key), value);
            }
        }
    }

    private String underlineToCamelhump(String inputString) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '_') {
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }

        }
        return sb.toString();
    }


}
