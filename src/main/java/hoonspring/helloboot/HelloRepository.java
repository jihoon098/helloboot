package hoonspring.helloboot;

public interface HelloRepository {
	
	Hello findHello(String name);
	
	void increaseCount(String name);
	
	// 인터페이스에 구현을 포함할 수 있게 하는 default Method
	default int countOf(String name) {
		Hello hello = findHello(name);
		return hello == null ? 0 : hello.getCount();
	}
	
}
