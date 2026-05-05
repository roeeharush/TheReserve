package ecosystem.core;
import ecosystem.entities.AbstractEntity;
import java.util.List;


public class Environment {
    List<AbstractEntity> entities;
    AbstractEntity[][] map;
    int rows, cols;

     public boolean isPositionFree(Position pos){
         if((pos.getCol()>=0 && pos.getCol()<this.cols) && (pos.getRow()>= 0 && pos.getRow()<this.rows ))
             return true;
         if (map[pos.getRow()][pos.getCol()] != null || map[pos.getRow()][pos.getCol()].getSymbol()=='X')
             return false;

         return false;
     }


}
