package reflect;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.junit.Test;

public class ReflectionTest {


	
	
	/**
	 * ����Class:
	 * 1. Class ��һ����
	 * 2. �����վ��Ӻ���Եõ�����Ϣ: ĳ��������ݳ�Ա���������͹�������ĳ���ൽ��ʵ������Щ�ӿ�.
	 * 3. ����ÿ�������, JRE ��Ϊ�䱣��һ������� Class ���͵Ķ���.һ�� Class ���������ĳ������й���Ϣ.
	 * 4. Class ����ֻ����ϵͳ����.
	 * 5. һ������ JVM ��ֻ����һ�� Class ʵ��.
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testClass() throws ClassNotFoundException {
		
		Class clazz = null;
		
		//1. �õ� Class ���� ��3�ַ���.
		//1.1 ͨ�� ����.Class �ķ�ʽ.��Ϊ��ȫ�ɿ�,�������ܸ���.
		//��clazz = Person.class;
		//1.2 ͨ������� getClass() ������ȡ.(�������ж���ſ���)
		Person person = new Person();
		
		//System.out.println(Person.class.getCanonicalName());
		//clazz = person.getClass();
		//1.3 ͨ�� Class ����� forName() ��̬������ȡ.�õĽ϶�.
		String className = "reflect.Person"; 
		clazz = Class.forName(className);
		
		Field[] fields = clazz.getDeclaredFields();
		
		Method[] methods = clazz.getDeclaredMethods();
		
		for(Field field:fields){
			
			System.out.println(field);
		}
		
		for(Method method:methods){
			System.out.println(method.toString());
		}
			
	}
	
	
	/**
	 *  Class ��� newInstance() ����
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testNewInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		String className = "reflect.Person"; 
		
		Class clazz = Class.forName(className);
		
		// ����  Class �����  newInstance() ��������һ�� Class �����ʵ��
		// ʵ�ʵ��õ�������Ǹ��޲����Ĺ�����
		Object obj = clazz.newInstance();
		
		System.out.println(obj);
	}
	
	@Test
	public void testClassLoader() throws ClassNotFoundException, FileNotFoundException{
		// 1. ��ȡһ��ϵͳ���������
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		System.out.println(classLoader);
		// 2. ��ȡһ��ϵͳ����������ĸ��������
		classLoader = classLoader.getParent();
		System.out.println(classLoader);
		// 3. ��ȡ��չ��������ĸ��������(��ȡ����)
		classLoader = classLoader.getParent();
		System.out.println(classLoader);	
		// 4. ���Ե�ǰ�����ĸ�����������м���
		classLoader = 
				Class.forName("reflect.Person").getClassLoader();
		System.out.println(classLoader);		
		// 5. ���� JDK �ṩ�� Object �����ĸ�����������м���
		classLoader = 
				Class.forName("java.lang.Object").getClassLoader();
		System.out.println(classLoader);			
		// 6. �������������һ����Ҫ����
		// ���� getResourceAsStream ��ȡ��·���µ��ļ���Ӧ��������.
		InputStream in = null;
				//new FileInputStream("test.properties");
		in = this.getClass().getClassLoader().getResourceAsStream("test.properties");
		
		System.out.println(in);
	}
	
	
	/**
	 * 	Class �Ƕ�һ���������
	 *  �������: Field
	 *  ��ķ���: Method
	 *  ��Ĺ�����: Constrctor
	 *  
	 *  Method ��Ӧ���еķ���.
	 *  1. ��ȡ Method:
	 *  1.1  ��ȡ��ķ���������: clazz.getDeclaredMethod()
	 *  1.2 ��ȡָ���ķ���: getDeclaredMethod(String name,
                                Class<?>... parameterTypes)
            name: ������.
            parameterTypes: �����Ĳ�������(ʹ�� Class ������)���б�.   
           
            Method  method = 
            		clazz.getDeclaredMethod("setName", String.class);
        1.3 ͨ�� Method ����ִ�� Method: 
        	public Object invoke(Object obj,Object ... args)
            
            obj: ִ���ĸ�����ķ���,��һ��������ʵ��
            args: ִ�з���ʱ��Ҫ����Ĳ���.
                             
                                
	 *  
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testMethod() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Class clazz = Class.forName("reflect.Person");
		
		// 1. �õ� clazz ��Ӧ����������Щ����.���ܻ�ȡprivate(˽��)����.
		Method[] methods = clazz.getMethods();
		
		for(Method method:methods){
			System.out.println(method.getName());
		}
		
		// 2. ��ȡ���з���.����private(˽��)����.��ֻ��ȡ��ǰ�������ķ���.
		Method[] methods1 = clazz.getDeclaredMethods();
		for(Method method:methods1){
			System.out.println("~" + method.getName());
		}	
		// 3. ָ���ķ���.
		Method  method = clazz.getDeclaredMethod("setName", String.class);
		
		System.out.println(method);
		
		method = clazz.getDeclaredMethod("setName", String.class,int.class);
		
		System.out.println(method);
		
		// 4. ִ�з���.
		Object obj = clazz.newInstance();
		method.invoke(obj, "Stephen",12);
	
	}
	
	// ��ϰ:
	
	
	/**
	 * @param className: ĳ�����ȫ����.
	 * @param methodName: ���һ�������ķ�����.�÷���������˽�з���.
	 * @param args: ���ø÷�����Ҫ����Ĳ���.
	 * @return: ���ø÷�����ķ���ֵ
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testInvoke1(){
		try {
			invoke("reflect.Person",  "setName", "atguigu",10);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object invoke(String className,String methodName,Object ... args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		Class clazz = Class.forName(className);
		
		Object obj = clazz.newInstance();
		
		try {
			invoke(obj,methodName, args);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Method method = clazz.getDeclaredMethod(methodName, args);
		
		return null;
	}
	
	/**
	 * @param obj: ����ִ�е��Ǹ�����.
	 * @param methodName: ���һ�������ķ�����.�÷���������˽�з���.
	 * @param args: ���ø÷�����Ҫ����Ĳ���.
	 * @return: ���ø÷�����ķ���ֵ
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 */

	@Test
	public void testInvoke() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException{
		Object obj = new Person();
		
		invoke(obj, "setName", "�й��",12);
		
	}
	
	public Object invoke(Object obj,String methodName,Object ... args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException{
		
		Class[] parameterTypes = getParameterTypes(args);
		
		Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
		
		return method.invoke(obj, args);
		 
	}


	/**
	 * @param args
	 * @return
	 */
	private Class[] getParameterTypes(Object... args) {
		// 1. ��ȡ Method ����.
		Class[] parameterTypes = new Class[args.length];
		
		int i = 0;
		for(Object object:args ){
			System.out.println("^^" + args[i]);
			parameterTypes[i] =  args[i].getClass();
			System.out.println(parameterTypes[i]);
			i++;
		}
		return parameterTypes;
		
		/*for (int i = 0; i <args.length; i++) {
		System.out.println("^^" + args[i]);
			parameterTypes[i] =  args[i].getClass();
			System.out.println(parameterTypes[i]);
	}*/
	}
	
	/**
	 * ��ȡ��ǰ��ĸ���
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testGetSuperClass() throws ClassNotFoundException{
		
		String className = "reflect.Student";
		
		Class clazz = Class.forName(className);
		
		Class superClazz = clazz.getSuperclass();
		
		System.out.println(superClazz);
		
	}
	
	
	/**
	 * ��ͨ�� Method �� invoke() �������÷���,������Ȩ�޲���,�������ʹ�ø÷���
	 * ��Ϊ�ɱ����ʵ�:
	 * method.setAccessible(true);
	 * @throws Exception
	 */
	@Test
	public void testInvokePrivateMethod() throws Exception{
		
		Object obj = new Student();
		
		Class clazz = obj.getClass();
		
		Method method = clazz.getDeclaredMethod("method1", Integer.class);
		
		method.setAccessible(true);
		System.out.println(method);
		
		method.invoke(obj, 10);
	}
	
	/**
	 * invoke �������汾
	 * @param obj: ĳ�����һ������.
	 * @param methodName: ���һ�������ķ�����.�÷���������˽�з���.�������Ǹ÷����ڸ����ж���ģ�˽�У�����.
	 * @param args: ���ø÷�����Ҫ����Ĳ���.
	 * @return: ���ø÷�����ķ���ֵ
	 */
	public Object invoke2(Object obj,String methodName,Object ... args){
		// 1. ��ȡ Method ����
		Class[] parameterTypes = new Class[args.length];
		
		for(int i = 0; i < args.length; i++){
			parameterTypes[i] = args[i].getClass();
		}
		
		try {
			Method method = getMethod(obj.getClass(), methodName, parameterTypes);
			method.setAccessible(true);
			// 2. ִ�� Method ����
			// 3. ���ط����ķ���ֵ
			return method.invoke(obj, args);
			
		} catch (Exception e) {}
		return null;
	}
	
	/**
	 * ��ȡ clazz �� methodName ����.�÷���������˽�з���.�������Ǹ÷����ڸ����ж���ģ�˽�У�����.
	 * @param clazz
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public Method getMethod(Class clazz,String methodName,Class ... parameterTypes){
		
		for(;clazz != Object.class;clazz=clazz.getSuperclass()){
			try {
				Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e) {}
			
		}
		return null;
	}
	
	@Test
	public void testGetMethod() throws Exception{
		
		Class clazz = Class.forName("reflect.Student");
		
		Method method = getMethod(clazz, "method1",Integer.class);
		System.out.println(method);
		
		method = getMethod(clazz, "method2");
		System.out.println(method);
		
		
	}
	
	
	@Test
	public void testInvoke2(){
		
		Object obj = new Student();
		// Student �� method1() ����
		invoke2(obj, "method1",10);
		
		// Student �ĸ���� method2() ����������,����ֵΪ "private String method2"
		Object result = invoke2(obj, "method2"); 
		System.out.println(result);
		
	}
	
	
	// ����ϰ
	
	/**
	 * @throws ClassNotFoundException 
	 * 
	 */
	@Test
	public void testClassMethod() throws ClassNotFoundException{
		// 1. ȫ����
		String className = "reflect.Student";
		// 2. ������: ������ 1 ��������,Ҳ�������丸����;�����ǹ��з���,Ҳ������˽�з���.
		String methodName = "method3";
		// 3. ִ�� 2 ��Ӧ�ķ���ʱ��Ҫ����Ĳ����б�.
		Object [] args = {"�й��",25};
		
		// ������������,ִ�� methodName ��Ӧ�ķ���,����ӡ����ֵ.
		
		// 1. ���� className ��Ӧ����,��ȡ��Ӧ�� Class ���� clazz
		Class clazz = Class.forName(className);
		// 2. ���� clazz �� getDeclaredMethod() ������ȡ 2 ��Ӧ�� Method ����
		// ע��: 
		// 2.1 ��Ϊ�÷������ܲ��ڵ�ǰ����,�����п�����Ҫȥ�����л�ȡ.
		// 2.2 ��Ϊ�����������в�û�и����������͵��б�, ��Ҫ�� args ����ȡ�������͵��б�.
		
		Class [] parameterTypes = new Class[args.length];
		
		for(int i = 0; i < args.length; i++){
			parameterTypes[i] = args[i].getClass();
		}
		
		for(;clazz != Object.class;clazz = clazz.getSuperclass()){
			
			try {
				
				Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
				// 3. ��Ϊ Method ������˽�е�,������Ҫ�����Ϊ���Ա�����: setAccessible(true)
				method.setAccessible(true);
				// 4. ���� Method
				// 4.1 ���� 1 �õ��� clazz �ȴ��� className ��Ӧ�����һ������.
				// 4.2 �ٵ��� Method �� invoke() ����ִ�з���.
				method.invoke(clazz.newInstance(), args);
	
			} catch (Exception e) {}
		}
		

		
	}
	
	
	
	
	
	

}
