package reflect;

public class Person {

	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
public void setName(String name,Integer age){
		System.out.println("Name:" + name);
		System.out.println("Age:" + age);
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	private void testPrivateMethod(){
		
	}
	
	private String method2(){
		
		return "private String method2";
	}
	
	private Object method3(String name,Integer age){
		
		Person person = new Person(name,age);
		System.out.println(person.getName() + "/" + person.age);
		return person;
	}
		
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public Person() {
		
	}
}
