package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.FileHandler;

public class StorageFileNotFoundException extends StorageException {

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
