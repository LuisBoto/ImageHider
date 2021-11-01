package hider;

import java.util.ArrayList;
import java.util.List;

public class MatrixListHandler {

	private int[][] matrix;
	private List<Integer> data;
	private int dataIndex;
	private int xCoordinate;
	private int yCoordinate;

	public MatrixListHandler(int[][] matrix) {
		this.loadData(matrix);
		this.matrix = matrix;
		this.dataIndex = 0;
		this.xCoordinate = 0;
		this.yCoordinate = 0;
	}

	private void loadData(int[][] matrix) {
		data = new ArrayList<Integer>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				data.add(matrix[i][j]);
			}
		}
	}
	
	public int getNextInt() {
		if (this.dataIndex >= this.data.size())
			return -1;
		int result = this.data.get(dataIndex);
		this.dataIndex++;
		return result;
	}
	
	public void setNextInt(int value) {
		//if (this.xCoordinate >= this.matrix.length || this.yCoordinate >= this.matrix[xCoordinate].length)
		//	throw new Exception("Invalid set");
		this.matrix[this.xCoordinate][this.yCoordinate] = value;
		if (this.yCoordinate >= this.matrix[this.xCoordinate].length) {
			this.xCoordinate++;
			this.yCoordinate = 0;
		} else
			this.yCoordinate++;
	}

}
