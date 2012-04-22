/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

public class BabyData {
	/**
	 * contains length data [0][x] is day [1][x] is height
	 */
	public double[][] lengthData;
	/**
	 * number of entries in length data
	 */
	public int lengthDataLen;

	public BabyData() {
		lengthData = new double[2][1856];
		lengthDataLen = 0;
	}

	/**
	 * Add and entry to length data loog
	 * 
	 * @param day
	 * @param length
	 */
	public void addLengthData(double day, double length) {
		lengthData[0][lengthDataLen] = day;
		lengthData[1][lengthDataLen] = length;
		lengthDataLen++;
	}
}
