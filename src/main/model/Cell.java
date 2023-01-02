package main.model;

public class Cell {
    private final int x;
    private final int y;
    private CellStatus cellstatus;

    /**
     * construct a new cell with given coordinate and cell status
     * @param x: x coordinate of the cell
     * @param y: y coordinate of the cell
     * @param cellstatus: RED, BLUE for alive cells, BLANK for empty(dead) cells
     */
    public Cell(int x, int y, CellStatus cellstatus){
        this.x = x;
        this.y = y;
        this.cellstatus = cellstatus;
    }

    /**
     * set a cell to a given status
     * @param cellstatus: RED, BLUE for alive cells, BLANK for empty(dead) cells
     */
    public void setCellStatus(CellStatus cellstatus) {
        this.cellstatus = cellstatus;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public CellStatus getCellStatus() {
        return this.cellstatus;
    }

}
