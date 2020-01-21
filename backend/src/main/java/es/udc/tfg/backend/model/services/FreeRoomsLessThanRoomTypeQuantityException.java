package es.udc.tfg.backend.model.services;

@SuppressWarnings("serial")
public class FreeRoomsLessThanRoomTypeQuantityException extends Exception {
    
	private int quantity;
	
	public FreeRoomsLessThanRoomTypeQuantityException(int quantity) {
	
		this.quantity = quantity;

	}

	public int getQuantity() {
		return quantity;
	}


    
}
