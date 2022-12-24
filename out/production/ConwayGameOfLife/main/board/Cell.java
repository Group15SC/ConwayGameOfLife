package model;



public class cell {
    private int x;
    private int y;
    private Cellstatus cellstatus;
    public cell(int x, int y, Cellstatus cellstatus){
        this.x=x;
        this.y=y;
        this.cellstatus=cellstatus;
    }

    public void setCellstatus(Cellstatus cellstatus) {
        this.cellstatus = cellstatus;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cellstatus getCellstatus() {
        return cellstatus;
    }
    public char getColor(){
        char color='';
        case player1live:
            color='BLUE';
        case player2live:
            color='RED';
        case dead:
            color='BLANK';

    }

}
