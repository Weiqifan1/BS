/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g04;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Board;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Ship;
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
    
    public MyShooter() {
    }

    @Override
    public void startMatch(int rounds, Fleet ships, int sizeX, int sizeY) {
        //do ntohing
    }

    @Override
    public void startRound(int round) {
        //Do nothing
    }

    @Override
    public void placeShips(Fleet fleet, Board board) {
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
    }

    @Override
    public void incoming(Position pos) {
        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        Position shot = new Position(nextX, nextY);
        --nextX;
        if(nextX < 0)
        {
            nextX = 9; 
            --nextY;
            if(nextY < 0)
            {
                nextY = 9;
            }
        }
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
