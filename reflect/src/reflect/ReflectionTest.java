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
	 * 关于Class:
	 * 1. Class 是一个类
	 * 2. 对象照镜子后可以得到的信息: 某个类的数据成员名、方法和构造器、某个类到底实现了哪些接口.
	 * 3. 对于每个类而言, JRE 都为其保留一个不变的 Class 类型的对象.一个 Class 对象包含了某个类的有关信息.
	 * 4. Class 对象只能由系统建立.
	 * 5. 一个类在 JVM 中只会有一个 Class 实例.
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testClass() throws ClassNotFoundException {
		
		Class clazz = null;
		
		//1. 得到 Class 对象 有3种方法.
		//1.1 通过 类名.Class 的方式.最为安全可靠,程序性能更高.
		//、clazz = Person.class;
		//1.2 通过对象的 getClass() 方法获取.(必须先有对象才可以)
		Person person = new Person();
		
		//System.out.println(Person.class.getCanonicalName());
		//clazz = person.getClass();
		//1.3 通过 Class 对象的 forName() 静态方法获取.用的较多.
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
	 *  Class 类的 newInstance() 方法
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testNewInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		String className = "reflect.Person"; 
		
		Class clazz = Class.forName(className);
		
		// 利用  Class 对象的  newInstance() 方法创建一个 Class 对象的实例
		// 实际调用的是类的那个无参数的构造器
		Object obj = clazz.newInstance();
		
		System.out.println(obj);
	}
	
	@Test
	public void testClassLoader() throws ClassNotFoundException, FileNotFoundException{
		// 1. 获取一个系统的类加载器
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		System.out.println(classLoader);
		// 2. 获取一个系统的类加载器的父类加载器
		classLoader = classLoader.getParent();
		System.out.println(classLoader);
		// 3. 获取扩展类加载器的父类加载器(获取不到)
		classLoader = classLoader.getParent();
		System.out.println(classLoader);	
		// 4. 测试当前类由哪个类加载器进行加载
		classLoader = 
				Class.forName("reflect.Person").getClassLoader();
		System.out.println(classLoader);		
		// 5. 测试 JDK 提供的 Object 类由哪个类加载器进行加载
		classLoader = 
				Class.forName("java.lang.Object").getClassLoader();
		System.out.println(classLoader);			
		// 6. 关于类加载器的一个主要方法
		// 调用 getResourceAsStream 获取类路径下的文件对应的输入流.
		InputStream in = null;
				//new FileInputStream("test.properties");
		in = this.getClass().getClassLoader().getResourceAsStream("test.properties");
		
		System.out.println(in);
	}
	
	
	/**
	 * 	Class 是对一个类的描述
	 *  类的属性: Field
	 *  类的方法: Method
	 *  类的构造器: Constrctor
	 *  
	 *  Method 对应类中的方法.
	 *  1. 获取 Method:
	 *  1.1  获取类的方法的数组: clazz.getDeclaredMethod()
	 *  1.2 获取指定的方法: getDeclaredMethod(String name,
                                Class<?>... parameterTypes)
            name: 方法名.
            parameterTypes: 方法的参数类型(使用 Class 来描述)的列表.   
           
            Method  method = 
            		clazz.getDeclaredMethod("setName", String.class);
        1.3 通过 Method 对象执行 Method: 
        	public Object invoke(Object obj,Object ... args)
            
            obj: 执行哪个对象的方法,是一个类对象的实例
            args: 执行方法时需要传入的参数.
                             
                                
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
		
		// 1. 得到 clazz 对应的类中有哪些方法.不能获取private(私有)方法.
		Method[] methods = clazz.getMethods();
		
		for(Method method:methods){
			System.out.println(method.getName());
		}
		
		// 2. 获取所有方法.包括private(私有)方法.且只获取当前类声明的方法.
		Method[] methods1 = clazz.getDeclaredMethods();
		for(Method method:methods1){
			System.out.println("~" + method.getName());
		}	
		// 3. 指定的方法.
		Method  method = clazz.getDeclaredMethod("setName", String.class);
		
		System.out.println(method);
		
		method = clazz.getDeclaredMethod("setName", String.class,int.class);
		
		System.out.println(method);
		
		// 4. 执行方法.
		Object obj = clazz.newInstance();
		method.invoke(obj, "Stephen",12);
	
	}
	
	// 练习:
	
	
	/**
	 * @param className: 某个类的全类名.
	 * @param methodName: 类的一个方法的方法名.该方法可能是私有方法.
	 * @param args: 调用该方法需要传入的参数.
	 * @return: 调用该方法后的返回值
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
	 * @param obj: 方法执行的那个对象.
	 * @param methodName: 类的一个方法的方法名.该方法可能是私有方法.
	 * @param args: 调用该方法需要传入的参数.
	 * @return: 调用该方法后的返回值
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
		
		invoke(obj, "setName", "尚硅谷",12);
		
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
		// 1. 获取 Method 对象.
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
	 * 获取当前类的父类
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
	 * 若通过 Method 的 invoke() 方法调用方法,而访问权限不足,则可以先使用该方法
	 * 变为可被访问的:
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
	 * invoke 的升级版本
	 * @param obj: 某个类的一个对象.
	 * @param methodName: 类的一个方法的方法名.该方法可能是私有方法.还可能是该方法在父类中定义的（私有）方法.
	 * @param args: 调用该方法需要传入的参数.
	 * @return: 调用该方法后的返回值
	 */
	public Object invoke2(Object obj,String methodName,Object ... args){
		// 1. 获取 Method 对象
		Class[] parameterTypes = new Class[args.length];
		
		for(int i = 0; i < args.length; i++){
			parameterTypes[i] = args[i].getClass();
		}
		
		try {
			Method method = getMethod(obj.getClass(), methodName, parameterTypes);
			method.setAccessible(true);
			// 2. 执行 Method 方法
			// 3. 返回方法的返回值
			return method.invoke(obj, args);
			
		} catch (Exception e) {}
		return null;
	}
	
	/**
	 * 获取 clazz 的 methodName 方法.该方法可能是私有方法.还可能是该方法在父类中定义的（私有）方法.
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
		// Student 的 method1() 方法
		invoke2(obj, "method1",10);
		
		// Student 的父类的 method2() 方法被调用,返回值为 "private String method2"
		Object result = invoke2(obj, "method2"); 
		System.out.println(result);
		
	}
	
	
	// ；练习
	
	/**
	 * @throws ClassNotFoundException 
	 * 
	 */
	@Test
	public void testClassMethod() throws ClassNotFoundException{
		// 1. 全类名
		String className = "reflect.Student";
		// 2. 方法名: 可能在 1 给的类中,也可能在其父类中;可能是共有方法,也可能是私有方法.
		String methodName = "method3";
		// 3. 执行 2 对应的方法时需要传入的参数列表.
		Object [] args = {"尚硅谷",25};
		
		// 根据以上条件,执行 methodName 对应的方法,并打印返回值.
		
		// 1. 加载 className 对应的类,获取对应的 Class 对象 clazz
		Class clazz = Class.forName(className);
		// 2. 调用 clazz 的 getDeclaredMethod() 方法获取 2 对应的 Method 对象、
		// 注意: 
		// 2.1 因为该方法可能不在当前类中,所以有可能需要去父类中获取.
		// 2.2 因为给定的条件中并没有给定参数类型的列表, 需要从 args 来获取参数类型的列表.
		
		Class [] parameterTypes = new Class[args.length];
		
		for(int i = 0; i < args.length; i++){
			parameterTypes[i] = args[i].getClass();
		}
		
		for(;clazz != Object.class;clazz = clazz.getSuperclass()){
			
			try {
				
				Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
				// 3. 因为 Method 可能是私有的,所以需要让其变为可以被访问: setAccessible(true)
				method.setAccessible(true);
				// 4. 调用 Method
				// 4.1 利用 1 得到的 clazz 先创建 className 对应的类的一个对象.
				// 4.2 再调用 Method 的 invoke() 方法执行方法.
				method.invoke(clazz.newInstance(), args);
	
			} catch (Exception e) {}
		}
		

		
	}
	
	
	
	
	
	

}
