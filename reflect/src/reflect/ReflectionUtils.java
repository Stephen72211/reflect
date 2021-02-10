package reflect;
/**
 * 反射的 Utils 函数集合
 * 提供访问私有变量, 获取泛型类型 Class, 提取集合中元素属性等 Utils 函数
 * @author Administrator
 *
 */
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.reflect.Type; 


public class ReflectionUtils {
    
    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     * @param object
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes){
        
        for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){
            try {
                //superClass.getMethod(methodName, parameterTypes);
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                //Method 不在当前类定义, 继续向上转型
            }
            //..
        }
        
        return null;
    }
    
    /**
     * 使 filed 变为可访问
     * @param field
     */
    public static void makeAccessible(Field field){
        if(!Modifier.isPublic(field.getModifiers())){
            field.setAccessible(true);
        }
    }
    
    /**
     * 循环向上转型, 获取对象的 DeclaredField
     * @param object
     * @param filedName
     * @return
     */
    public static Field getDeclaredField(Object object, String filedName){
        
        for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){
            try {
                return superClass.getDeclaredField(filedName);
            } catch (NoSuchFieldException e) {
                //Field 不在当前类定义, 继续向上转型
            }
        }
        return null;
    }
    
    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected)
     * @param object
     * @param methodName
     * @param parameterTypes
     * @param parameters
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     */
    public static Object invokeMethod(Object object, String methodName, Class<?> [] parameterTypes,
            Object [] parameters) throws InvocationTargetException{
        
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        
        if(method == null){
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
        }
        
        method.setAccessible(true);
        
        try {
            return method.invoke(object, parameters);
        } catch(IllegalAccessException e) {
            System.out.println("不可能抛出的异常");
        } 
        
        return null;
    }
    
    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     * @param object
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(Object object, String fieldName, Object value){
        Field field = getDeclaredField(object, fieldName);
        
        if (field == null)
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        
        makeAccessible(field);
        
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            System.out.println("不可能抛出的异常");
        }
    }

}
