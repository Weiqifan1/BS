/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g4;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Board;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Ship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Christian
 */
public class MyShooter implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;

    private int nextX;
    private int nextY;

    private HeatMapping mapping = new HeatMapping();
    private int[] seaMap;

    public MyShooter() {
    }

    @Override
    public void startMatch(int rounds, Fleet ships, int sizeX, int sizeY) {
        seaMap = new int[sizeX * sizeY];
    }

    @Override
    public void startRound(int round) {
        for (int i = 0; i < seaMap.length; i++) {
            seaMap[i] = 1;
        }
    }

    @Override
    public void placeShips(Fleet fleet, Board board) {
        nextX = 9;
        nextY = 9;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        ArrayList<Integer> potentialSpace = new ArrayList<Integer>();
        ArrayList<Integer> usedSpaces = new ArrayList<Integer>();

        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            boolean vertical = rnd.nextBoolean();
            boolean goodSpace = false;
            Position pos = new Position(0, 0);

            while (goodSpace == false) {
                if (vertical) {

                    int x = rnd.nextInt(sizeX);
                    int y = rnd.nextInt(sizeY - (s.size() - 1));
                    pos = new Position(x, y);

                    for (int j = 0; j < s.size(); j++) {
                        int indexLtoRBtoT = x + (y * 10) + (j * 10);
                        potentialSpace.add(indexLtoRBtoT);
                    }
                    boolean fieldIsOk = true;
                    for (int j = 0; j < potentialSpace.size(); j++) {
                        if (usedSpaces.contains(potentialSpace.get(j))) {
                            fieldIsOk = false;
                        }
                    }
                    if (fieldIsOk == true && potentialSpace.size() == s.size()) {
                        usedSpaces.addAll(potentialSpace);
                        goodSpace = true;
                    }
                    potentialSpace.clear();

                } else {
                    int x = rnd.nextInt(sizeX - (s.size() - 1));
                    int y = rnd.nextInt(sizeY);
                    pos = new Position(x, y);

                    for (int j = 0; j < s.size(); j++) {
                        int indexLtoRBtoT = x + (y * 10) + j;
                        potentialSpace.add(indexLtoRBtoT);
                    }
                    boolean fieldIsOk = true;
                    for (int j = 0; j < potentialSpace.size(); j++) {
                        if (usedSpaces.contains(potentialSpace.get(j))) {
                            fieldIsOk = false;
                        }
                    }
                    if (fieldIsOk == true && potentialSpace.size() == s.size()) {
                        usedSpaces.addAll(potentialSpace);
                        goodSpace = true;
                    }
                    potentialSpace.clear();
                }
            }
            board.placeShip(pos, s, vertical);
        }
    }

    /*
    nextX = 9;
        nextY = 9;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for(int i = 0; i < fleet.getNumberOfShips(); ++i)
        {
            Ship s = fleet.getShip(i);
            boolean vertical = rnd.nextBoolean();
            Position pos;
            if(vertical)
            {
                int x = rnd.nextInt(sizeX);
                int y = rnd.nextInt(sizeY-(s.size()-1));
                pos = new Position(x, y);
            }
            else
            {
                int x = rnd.nextInt(sizeX-(s.size()-1));
                int y = rnd.nextInt(sizeY);
                pos = new Position(x, y);
            }
            board.placeShip(pos, s, vertical);
        }
     */
    @Override
    public void incoming(Position pos) {
        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        
        int shipCount = enemyShips.getNumberOfShips();
        int[] mapFleet = new int[shipCount]; 
        for (int i = 0; i < shipCount; i++) {
            mapFleet[i] = enemyShips.getShip(i).size();
        }
        int[] heatMap = mapping.simpleHeatMap(seaMap, mapFleet);
        int index = 0;
        for (int i = 1; i < heatMap.length; i++) {
            if (heatMap[index]<=heatMap[i]) {
                index = i;
            } 
            
        }
        nextX = index%10;
        nextY = 9-index/10;
        
  
        Position shot = new Position(nextX, nextY);

        return shot;
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        //Do nothing
    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
        //Do nothing
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }

}
