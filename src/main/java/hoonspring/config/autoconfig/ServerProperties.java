package hoonspring.config.autoconfig;

public class ServerProperties {
	
	private String contextPath;
	
	private int port;
	
	
	// Getter & Setter
	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
