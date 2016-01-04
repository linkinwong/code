
public class NumMatrix {
    private int[][] block_m;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int r = matrix.length;
        int c = matrix[0].length;
        block_m = new int[r][c];
        block_m[0][0] = matrix[0][0];
        for(int i=1; i<r; i++){
            block_m[i][0] += matrix[i-1][0];
        }
        for(int j=1; j<c; j++){
            block_m[0][j] += matrix[0][j-1];
        }
        
        for(int j=1; j< c; j++){
            for(int i=1; i<r; i++){
                block_m[i][j] = block_m[i-1][j] + block_m[i][j-1] + matrix[i][j] - block_m[i-1][j-1]; 
            }
        }
	}

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (col1==0 && row1==0) return block_m[0][0];
        if (col1 <1 ) return block_m[row2][0] - block_m[row1-1][0];
        if (row1 <1)  return block_m[0][col2] - block_m[0][col1-1];
        return block_m[row2][col2] - block_m[row2][col1-1] - block_m[row1-1][col2] + block_m[row1-1][col1-1];
        }
}
