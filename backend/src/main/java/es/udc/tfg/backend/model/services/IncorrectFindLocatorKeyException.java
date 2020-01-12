package es.udc.tfg.backend.model.services;

@SuppressWarnings("serial")
public class IncorrectFindLocatorKeyException extends Exception {
	
	private String locator;
	private String key;

	public IncorrectFindLocatorKeyException(String locator, String key) {
		
		this.locator = locator;
		this.key = key;
		
	}

	public String getLocator() {
		return locator;
	}

	public String getKey() {
		return key;
	}
	
}
