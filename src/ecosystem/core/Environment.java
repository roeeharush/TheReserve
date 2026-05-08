package ecosystem.core;
import ecosystem.entities.AbstractEntity;

import java.util.ArrayList;
import java.util.List;


public class Environment {
    private final List<AbstractEntity> entities;
    private final AbstractEntity[][] map;
    private final int rows;
    private final int cols;

    public Environment(int rows , int cols) {
        if (rows < 10)
            rows = 10;
        if (cols < 10)
            cols = 10;

        this.rows = rows;
        this.cols = cols;
        this.map = new AbstractEntity[rows][cols];
        this.entities = new ArrayList<>();
    }

        @Override
        public boolean equals (Object o){
            if (this == o)
                return true;

            if (o instanceof Environment other) {
                return this.rows == other.rows
                        && this.cols == other.cols
                        && this.entities.equals(other.entities)
                        && java.util.Arrays.deepEquals(this.map, other.map);
            }
            return false;
    }

    public boolean isPositionFree (Position pos){
        if ((pos.getCol() >= 0 && pos.getCol() < this.cols) && (pos.getRow() >= 0 && pos.getRow() < this.rows))
            return map[pos.getRow()][pos.getCol()] == null && map[pos.getRow()][pos.getCol()].getSymbol() != 'X';

        return true;
        }

    public boolean addEntity(AbstractEntity entity) {
        if (entity == null || !isPositionFree(entity.getPosition())) {
            return false;
        }
        entities.add(entity);
        map[entity.getPosition().getRow()][entity.getPosition().getCol()] = entity;
        return true;
    }

    public boolean removeEntity(AbstractEntity entity) {
        if (entity == null || !entities.contains(entity)) {
            return false;
        }
        entities.remove(entity);
        map[entity.getPosition().getRow()][entity.getPosition().getCol()] = null;
        return true;


    }

     public List<AbstractEntity> getNearbyEntities(Position pos){

         List<AbstractEntity> entitiesNew = new ArrayList<>();
         for ( AbstractEntity e :entities  ) {
             int distance = e.getPosition().distanceTo(pos);
             if(distance>0 && distance <=2)
                 entitiesNew.add(e);
         }
         return entitiesNew;
     }















    }


