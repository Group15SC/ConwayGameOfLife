package main.board;
public class Cell {
    private int x;
    private int y;
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
        return x;
    }

    public int getY() {
        return y;
    }

    public CellStatus getCellStatus() {
        return cellstatus;
    }

}
