package dao;

public class UsingDB {
	private static String db = "sqlite";
	/*private static String db = "mysql";*/
	public static IGuitarDao createGuitarDao() {
		IGuitarDao result = null;
		/*Class.forName(daoName+"."+"GuitarImpl").newInstance();*/
		switch (db) {
		case "sqlite":
			result = new sqliteDao.GuitarImpl();
			break;
		case "mysql":
			result = new mysqlDao.GuitarImpl();
			break;
		}
		return result;	
	}
		
}
