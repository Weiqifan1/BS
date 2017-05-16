/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g4;

import battleship.interfaces.Fleet;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author GertLehmann
 */
public class HeatMapping {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;

    private int nextX;
    private int nextY;

    public int[] simpleHeatMap(int[] sea, int[] fleet) {

        int iterations = 100000;
        int[] newsea = new int[10 * 10];

        int[] tempsea = null;
        for (int i = 0; i < iterations; i++) {
            tempsea = distributeShips(sea, fleet);
            for (int j = 0; j < tempsea.length; j++) {
                if (tempsea[j] > 1) {
                    newsea[j] += 1;
                }
            }
        }

        return newsea;

    }
    
           
    public int[] convertFleet(Fleet fleet) {
        int shipCount = fleet.getNumberOfShips();
        int[] fleetArray = new int[shipCount]; 
        for (int i = 0; i < shipCount; i++) {
            fleetArray[i] = fleet.getShip((shipCount-1)-i).size();
        }
        return fleetArray;
    }


    public int[] distributeShips(int[] sea, int[] fleet) {

        int[] newsea = new int[10 * 10];
        System.arraycopy(sea, 0, newsea, 0, sea.length);
        //newsea er nu en kopi af sea.

        nextX = 9;
        nextY = 9;
        sizeX = 10;
        sizeY = 10;
        ArrayList<Integer> potentialSpace = new ArrayList<Integer>();
        ArrayList<Integer> usedSpaces = new ArrayList<Integer>();

        for (int i = 0; i < fleet.length; ++i) {
            int s = fleet[i];
            boolean vertical;

            boolean goodSpace = false;

            while (goodSpace == false) {
                vertical = rnd.nextBoolean();
                
                if (vertical) {
                    
                    int x = rnd.nextInt(sizeX);
                    int y = rnd.nextInt(sizeY - (s - 1));//rnd.nextInt(sizeY-(s-1));

                    for (int j = 0; j < s; j++) {
                        int indexLtoRBtoT = x + (y * 10) + (j * 10);
                        potentialSpace.add(indexLtoRBtoT);
                    }
                    boolean fieldIsOk = true;
                    for (int j = 0; j < potentialSpace.size(); j++) {
                        if (usedSpaces.contains(potentialSpace.get(j)) || newsea[potentialSpace.get(j)] < 1) {
                            fieldIsOk = false;
                        }
                    }
                    if (fieldIsOk == true && potentialSpace.size() == s) {
                        usedSpaces.addAll(potentialSpace);
                        goodSpace = true;
                    }
                    potentialSpace.clear();

                } else {
                    int x = rnd.nextInt(sizeX - (s - 1));//rnd.nextInt(sizeX-(s-1));
                    int y = rnd.nextInt(sizeY);

                    for (int j = 0; j < s; j++) {
                        int indexLtoRBtoT = x + (y * 10) + j;
                        potentialSpace.add(indexLtoRBtoT);
                    }
                    boolean fieldIsOk = true;
                    for (int j = 0; j < potentialSpace.size(); j++) {
                        if (usedSpaces.contains(potentialSpace.get(j)) || newsea[potentialSpace.get(j)] < 1) {
                            fieldIsOk = false;
                            
                        }
                    }
                    if (fieldIsOk == true && potentialSpace.size() == s) {
                        usedSpaces.addAll(potentialSpace);
                        goodSpace = true;
                    }
                    potentialSpace.clear();
                }
            }

            //board.placeShip(pos, s, vertical);
        }
        for (int i = 0; i < usedSpaces.size(); i++) {
            newsea[usedSpaces.get(i)] = 2;
        }
        return newsea;
    }

    public void printHeatmap(int divisor, int[] sea) {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        int largestNumber = 0;
        int largestNumberLength = 0;
        int currentNumberLength = 0;

        for (int i = 0; i < sea.length; i++) {
            if (sea[i] > largestNumber) {
                largestNumber = sea[i];
            }
        }
        largestNumberLength = Integer.toString(largestNumber).length();

        for (int i = 0; i < sea.length; i++) {
            currentNumberLength = Integer.toString(sea[i]).length();
            if (sea[i] < 0) {
                currentNumberLength++;
            }
            for (int j = 0; j < largestNumberLength - currentNumberLength; j++) {
                System.out.print(" ");
            }
            if (sea[i] == largestNumber) {
                System.out.print("[" + (sea[i] / divisor) + "]");
            } else {
                System.out.print(" " + (sea[i] / divisor) + " ");
            }
            if (i % Math.sqrt(sea.length) == Math.sqrt(sea.length) - 1) {
                System.out.println();
            }
        }
    }

    public void printSea(int[] sea) {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        int largestNumber = 0;
        int largestNumberLength = 0;
        int currentNumberLength = 0;

        for (int i = 0; i < sea.length; i++) {
            if (sea[i] > largestNumber) {
                largestNumber = sea[i];
            }
        }
        largestNumberLength = Integer.toString(largestNumber).length();
        largestNumberLength++;

        for (int i = 0; i < sea.length; i++) {
            currentNumberLength = Integer.toString(sea[i]).length();
            if (sea[i] < 0) {
                currentNumberLength++;
            }

            for (int j = 0; j < largestNumberLength - currentNumberLength; j++) {
                System.out.print(" ");
            }

            if (sea[i] == 1) {
                System.out.print(" " + ".");
            } else if (sea[i] == -1) {
                System.out.print("  " + "+");
            } else {
                System.out.print(" " + sea[i]);
            }

            if (i % Math.sqrt(sea.length) == Math.sqrt(sea.length) - 1) {
                System.out.println();
            }
        }
    }

}
