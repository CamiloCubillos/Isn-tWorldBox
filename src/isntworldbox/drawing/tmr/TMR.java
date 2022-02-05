package isntworldbox.drawing.tmr;

public class TMR {
    private int[][] matrix;
    public int rows;
    public int cols;
    public TMR(int _rows, int _cols){
        
        rows = _rows;
        cols = _cols;
        
        // Initialize matrix with terrain id = 0 (water)
        matrix = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols;j++){
                matrix[i][j] = 0;
            }
        }
    }
    
    public void setTerrain(int _row, int _col, int _terrainID){
        matrix[_row][_col] = _terrainID;
    }
    
    public int getTerrainID(int _row, int _col){
        return matrix[_row][_col];
    }
}
