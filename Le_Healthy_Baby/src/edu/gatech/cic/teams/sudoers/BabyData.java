package edu.gatech.cic.teams.sudoers;

public class BabyData {
	public double [][]lengthData;
	public int lengthDataLen;
	public BabyData() {
		lengthData = new double[2][1856];
		lengthDataLen = 0;
	}
	public void addLengthData(double day, double length) {
		lengthData[0][lengthDataLen] = day;
		lengthData[1][lengthDataLen] = length;
		lengthDataLen++;
	}
}
