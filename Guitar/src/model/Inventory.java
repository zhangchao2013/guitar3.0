package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
	private static List<Guitar> Guitars;

	public Inventory() {
		Guitars = new LinkedList<Guitar>();
	}

	public void addGuitar(String serialNumber, double price, GuitarSpec spec) {
		Guitar guitar = new Guitar(serialNumber, price, spec);
		Guitars.add(guitar);
	}

	public Guitar getGuitar(String serialNumber) {
		for (Iterator<Guitar> i = Guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			if (guitar.getSerialNumber().equals(serialNumber)) {
				return guitar;
			}
		}
		return null;
	}

	public static List<Guitar> search(GuitarSpec spec){
		List<Guitar> guitarMatched=new LinkedList<Guitar>();
		for (Iterator<Guitar> i = Guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			if (guitar.getSpec().isMatched(spec)) {
				guitarMatched.add(guitar);
			}
		}
		return guitarMatched;
	}
	
	public static List<Guitar> showallguitar(){
		List<Guitar> allguitars=new LinkedList<Guitar>();
		for (Iterator<Guitar> i = Guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			allguitars.add(guitar);
		}
		return allguitars;
	}
}