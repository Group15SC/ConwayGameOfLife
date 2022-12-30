import java.util.ArrayList;
import java.util.List;
import main.board.Cell;
import main.board.CellStatus;
import main.board.Grid;
public class Initialboard {

    //public List<Cell> square=new ArrayList<>();
    //public List<Cell> boat=new ArrayList<>();
    //public List<Cell> ship=new ArrayList<>();
    //public Initialboard() {
        //square();
        //boat();
       // ship();
    //}


    //private void square(){
    public static void main(String[] args) {
            List<Cell> square=new ArrayList<Cell>();
            Cell c1=new Cell(15,15,CellStatus.RED);
            Cell c2=new Cell(14, 15,CellStatus.RED);
            Cell c3=new Cell(14, 16,CellStatus.RED);
            Cell c4=new Cell(15, 16,CellStatus.RED);
            Cell c5=new Cell(15, 25,CellStatus.BLUE);//for another player
            Cell c6=new Cell(14, 25,CellStatus.BLUE);
            Cell c7=new Cell(14, 24,CellStatus.BLUE);
            Cell c8=new Cell(14, 24,CellStatus.BLUE);
            square.add(c1);
            square.add(c2);
            square.add(c3);
            square.add(c4);
            square.add(c5);
            square.add(c6);
            square.add(c7);
            square.add(c8);

        }
    //}
    //private void boat() {
    public static void main(String[] args) {
            List<Cell> boat=new ArrayList<Cell>();
            Cell c1=new Cell(11,15,CellStatus.RED);
            Cell c2=new Cell(10, 15,CellStatus.RED);
            Cell c3=new Cell(11, 16,CellStatus.RED);
            Cell c4=new Cell(11, 17,CellStatus.RED);
            Cell c5=new Cell(12, 16,CellStatus.RED);
            Cell c6=new Cell(11, 25,CellStatus.BLUE);//for another player
            Cell c7=new Cell(10, 25,CellStatus.BLUE);
            Cell c8=new Cell(11, 24,CellStatus.BLUE);
            Cell c9=new Cell(11, 23,CellStatus.BLUE);
            Cell c10=new Cell(12, 24,CellStatus.BLUE);
            boat.add(c1);
            boat.add(c2);
            boat.add(c3);
            boat.add(c4);
            boat.add(c5);
            boat.add(c6);
            boat.add(c7);
            boat.add(c8);
            boat.add(c9);
            boat.add(c10);
    }
    //}
    //private void ship() {
    public static void main(String[] args) {
            List<Cell> ship=new ArrayList<Cell>();
            Cell c1=new Cell(7,15,CellStatus.RED);
            Cell c2=new Cell(6, 15,CellStatus.RED);
            Cell c3=new Cell(6, 16,CellStatus.RED);
            Cell c4=new Cell(7, 17,CellStatus.RED);
            Cell c5=new Cell(8, 17,CellStatus.RED);
            Cell c6=new Cell(8, 16,CellStatus.RED);
            Cell c7=new Cell(7, 25,CellStatus.BLUE);//for another player
            Cell c8=new Cell(6, 25,CellStatus.BLUE);
            Cell c9=new Cell(6, 24,CellStatus.BLUE);
            Cell c10=new Cell(7, 23,CellStatus.BLUE);
            Cell c11=new Cell(8, 23,CellStatus.BLUE);
            Cell c12=new Cell(8, 24,CellStatus.BLUE);
            ship.add(c1);
            ship.add(c2);
            ship.add(c3);
            ship.add(c4);
            ship.add(c5);
            ship.add(c6);
            ship.add(c7);
            ship.add(c8);
            ship.add(c9);
            ship.add(c10);
            ship.add(c11);
            ship.add(c12);
        }
    //}

}
