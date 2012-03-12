package edu.gatech.cic.teams.sudoers;

public class VaccinationData {
	private static String[][] chart;
	static boolean firstTime = true;

	public static String[][] getChart() {
		if (firstTime) {
			chart = new String[11][12];

			chart[0][0] = "Hepatitis B";
			chart[1][0] = "0";
			chart[2][0] = "0";
			chart[3][0] = "0";
			chart[4][0] = "0";
			chart[5][0] = "0";
			chart[6][0] = "0";
			chart[7][0] = "0";
			chart[8][0] = "0";
			chart[9][0] = "0";
			chart[10][0] = "0";

			chart[0][1] = "Hepatitis B";
			chart[1][1] = "0";
			chart[2][1] = "0";
			chart[3][1] = "0";
			chart[4][1] = "0";
			chart[5][1] = "0";
			chart[6][1] = "0";
			chart[7][1] = "0";
			chart[8][1] = "0";
			chart[9][1] = "0";
			chart[10][1] = "0";

			chart[0][2] = "Hepatitis B";
			chart[1][2] = "Rotavirus";
			chart[2][2] = "Diphtheria, tetanus, pertussis";
			chart[3][2] = "Haemophilus influenzae type b";
			chart[4][2] = "Pneumococcal";
			chart[5][2] = "Inactivated poliovirus";
			chart[6][2] = "0";
			chart[7][2] = "0";
			chart[8][2] = "0";
			chart[9][2] = "0";
			chart[10][2] = "0";

			chart[0][3] = "0";
			chart[1][3] = "Rotavirus";
			chart[2][3] = "Diphtheria, tetanus, pertussis";
			chart[3][3] = "Haemophilus influenzae type b";
			chart[4][3] = "Pneumococcal";
			chart[5][3] = "Inactivated poliovirus";
			chart[6][3] = "0";
			chart[7][3] = "0";
			chart[8][3] = "0";
			chart[9][3] = "0";
			chart[10][3] = "0";

			chart[0][4] = "Hepatitis B";
			chart[1][4] = "Rotavirus";
			chart[2][4] = "Diphtheria, tetanus, pertussis";
			chart[3][4] = "Haemophilus influenzae type b";
			chart[4][4] = "Pneumococcal";
			chart[5][4] = "Inactivated poliovirus";
			chart[6][4] = "Influenza";
			chart[7][4] = "0";
			chart[8][4] = "0";
			chart[9][4] = "0";
			chart[10][4] = "0";

			chart[0][5] = "Hepatitis B";
			chart[1][5] = "0";
			chart[2][5] = "0";
			chart[3][5] = "0";
			chart[4][5] = "0";
			chart[5][5] = "Inactivated poliovirus";
			chart[6][5] = "Influenza";
			chart[7][5] = "0";
			chart[8][5] = "0";
			chart[9][5] = "0";
			chart[10][5] = "Meningococca";

			chart[0][6] = "Hepatitis B";
			chart[1][6] = "0";
			chart[2][6] = "0";
			chart[3][6] = "Haemophilus influenzae type b";
			chart[4][6] = "Pneumococcal";
			chart[5][6] = "Inactivated poliovirus";
			chart[6][6] = "Influenza";
			chart[7][6] = "Measles, mumps, rubella";
			chart[8][6] = "Varicella";
			chart[9][6] = "Hepatitis A";
			chart[10][6] = "Meningococca";

			chart[0][7] = "Hepatitis B";
			chart[1][7] = "0";
			chart[2][7] = "Diphtheria, tetanus, pertussis";
			chart[3][7] = "Haemophilus influenzae type b";
			chart[4][7] = "Pneumococcal";
			chart[5][7] = "Inactivated poliovirus";
			chart[6][7] = "Influenza";
			chart[7][7] = "Measles, mumps, rubella";
			chart[8][7] = "Varicella";
			chart[9][7] = "Hepatitis A";
			chart[10][7] = "Meningococca";

			chart[0][8] = "Hepatitis B";
			chart[1][8] = "0";
			chart[2][8] = "Diphtheria, tetanus, pertussis";
			chart[3][8] = "0";
			chart[4][8] = "0";
			chart[5][8] = "Inactivated poliovirus";
			chart[6][8] = "Influenza";
			chart[7][8] = "0";
			chart[8][8] = "0";
			chart[9][8] = "Hepatitis A";
			chart[10][8] = "Meningococca";

			chart[0][9] = "0";
			chart[1][9] = "0";
			chart[2][9] = "0";
			chart[3][9] = "0";
			chart[4][9] = "0";
			chart[5][9] = "0";
			chart[6][9] = "Influenza";
			chart[7][9] = "0";
			chart[8][9] = "0";
			chart[9][9] = "Hepatitis A";
			chart[10][9] = "Meningococca";

			chart[0][10] = "0";
			chart[1][10] = "0";
			chart[2][10] = "0";
			chart[3][10] = "0";
			chart[4][10] = "Pneumococcal";
			chart[5][10] = "0";
			chart[6][10] = "Influenza";
			chart[7][10] = "0";
			chart[8][10] = "0";
			chart[9][10] = "Hepatitis A";
			chart[10][10] = "Meningococca";

			chart[0][11] = "0";
			chart[1][11] = "0";
			chart[2][11] = "Diphtheria, tetanus, pertussis";
			chart[3][11] = "0";
			chart[4][11] = "Pneumococcal";
			chart[5][11] = "Inactivated poliovirus";
			chart[6][11] = "Influenza";
			chart[7][11] = "Measles, mumps, rubella";
			chart[8][11] = "Varicella";
			chart[9][11] = "Hepatitis A";
			chart[10][11] = "Meningococca";
			
			firstTime = false;
		}
		return chart;
	}
}
