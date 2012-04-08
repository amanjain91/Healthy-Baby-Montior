/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import android.database.sqlite.SQLiteDatabase;

public class HeightChartData {
	/**
	 * @throws FileNotFoundException
	 * 
	 */
	SQLiteDatabase db;
	String filename, tablename;
	String SQL;

	public HeightChartData(String tablename, String filename)
			throws FileNotFoundException {
		this.filename = filename;
		this.tablename = tablename;
		SQL = "";
	}

	public void extract() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filename));
		String line;
		line = sc.nextLine(); // first line is useless since it mentions column
								// names
		while (sc.hasNext()) {
			line = sc.nextLine();
			insert(line);
		}
	}

	public void insert(String line) {
		Scanner sc = new Scanner(line);
		Integer day, L;
		Double M, S, P01, P1, P3, P5, P10, P15, P25, P50, P75, P85, P90, P95, P97, P99, P999;
		day = sc.nextInt();
		L = sc.nextInt();
		M = sc.nextDouble();
		S = sc.nextDouble();
		P01 = sc.nextDouble();
		P1 = sc.nextDouble();
		P3 = sc.nextDouble();
		P5 = sc.nextDouble();
		P10 = sc.nextDouble();
		P15 = sc.nextDouble();
		P25 = sc.nextDouble();
		P50 = sc.nextDouble();
		P75 = sc.nextDouble();
		P85 = sc.nextDouble();
		P90 = sc.nextDouble();
		P95 = sc.nextDouble();
		P97 = sc.nextDouble();
		P99 = sc.nextDouble();
		P999 = sc.nextDouble();
		String query = "INSERT INTO " + tablename + "VALUES " + "(" + day
				+ ", " + L + ", " + M + ", " + S + ", " + P01 + ", " + P1
				+ ", " + P3 + ", " + P5 + ", " + P10 + ", " + P15 + ", " + P25
				+ ", " + P50 + ", " + P75 + ", " + P85 + ", " + P90 + ", "
				+ P95 + ", " + P97 + ", " + P99 + ", " + P999 + ");";
		SQL = SQL + query;

	}

	public String updatedb(SQLiteDatabase db) throws FileNotFoundException {
		SQL = "DROP TABLE IF EXISTS " + tablename + ";";
		String query = "CREATE TABLE " + tablename
				+ " ( Day INTEGER PRIMARY KEY, " + "L Integer" + "M Double"
				+ "S Double" + "P01 Double" + "P1 Double" + "P3 Double"
				+ "P5 Double" + "P10 Double" + "P15 Double" + "P25 Double"
				+ "P50 Double" + "P75 Double" + "P85 Double" + "P90 Double"
				+ "P95 Double" + "P97 Double" + "P99 Double" + "P999 Double );";
		SQL = SQL + query;
		return SQL;
	}
}
