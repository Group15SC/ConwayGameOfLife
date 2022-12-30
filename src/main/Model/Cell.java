package main.Model;

public class Cell {
    private final int x;
    private final int y;
    private CellStatus cellstatus;
    public Cell(int x, int y, CellStatus cellstatus){
        this.x = x;
        this.y = y;
        this.cellstatus = cellstatus;
    }

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
