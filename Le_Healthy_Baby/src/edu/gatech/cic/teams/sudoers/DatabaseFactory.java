package edu.gatech.cic.teams.sudoers;

public class DatabaseFactory {
	private static DatabaseOpenHelper mDatabase = new DatabaseOpenHelper(
			MyApplication.getAppContext());

	public static DatabaseOpenHelper getDatabaseHelper() {
		if (mDatabase == null) {
			System.out.println("WT!");
		}
		return mDatabase;
	}
}