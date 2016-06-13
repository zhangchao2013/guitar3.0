package dao;

import model.GuitarSpec;

public interface IGuitarDao {
	public boolean LoadGuitars();
	public boolean addGuitar(String serialNumber, double price,
            GuitarSpec spec);
	void deleteGuitar(String serialNumber);
}
