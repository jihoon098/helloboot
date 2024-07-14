package hoonspring.helloboot;

public interface HelloServiceInf {
	
	String sayHello(String name);
	
	default int countOf(String count) {
		return 0;
	}
	
}