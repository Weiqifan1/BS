/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g4;

import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class Kill {
    public void nr02() {
        System.out.println("day2 - nr02 - Kill! kill! kill!");
        
        int[] sea = fixedSeaWithSips();

        //day1.printSea(sea);
        shoot(sea);

        //sea = placeShips(sea);
    }

    public void serviceInfo(int[] newsea, int[] inputSea) {
        int CommingRound = 1;
        //day1 day1 = new day1();
        HeatMapping heatMethods = new HeatMapping();
        heatMethods.printSea(newsea);
        System.out.println();
        heatMethods.printSea(inputSea);
        System.out.println();
        for (int i = 0; i < newsea.length; i++) {
            if (newsea[i] < 0) {
                CommingRound++;
            }
        }
        System.out.println("comming round: " + CommingRound);
        CommingRound = 0;
    }

    //empty seapoints in inputSea == 1;
    public void shoot(int[] inputSea) {

        //newsea is the unknown sea we are trying to shoot at. Therefore all indexes in it should be 1:
        int[] newsea = new int[100];
        for (int i = 0; i < newsea.length; i++) {
            newsea[i] = 1;
        }

        //day1 day1 = new day1();
        ArrayList<Integer> deadpoints = new ArrayList<Integer>();// when deadpoints.size == 17: alle ships are dead.
        int nextIndexoShoot = 0;
        int[] latestHeatMap = null;//day1.simpleHeatMap(10000, inputSea);
        int CommingRound = 1;
        System.out.println("comming round: " + CommingRound);

        while (deadpoints.size() < 17) {
            nextIndexoShoot = getFirstLivingIndex(newsea);
            if (inputSea[nextIndexoShoot] == 1) {
                newsea[nextIndexoShoot] = -1;
                serviceInfo(newsea, inputSea);
            } else {
                //here the kill sequense is activated:
                newsea[nextIndexoShoot] = -2;
                deadpoints.add(nextIndexoShoot);
                serviceInfo(newsea, inputSea);

                ArrayList<Integer> adjacent = killeTheLivingShip(newsea);

                while (adjacent.size() > 0) {
                    nextIndexoShoot = adjacent.get(0);
                    if (inputSea[nextIndexoShoot] == 1) {
                        newsea[nextIndexoShoot] = -1;
                        serviceInfo(newsea, inputSea);
                        
                    } else if (inputSea[nextIndexoShoot] > 1) {
                        newsea[nextIndexoShoot] = -2;
                        deadpoints.add(nextIndexoShoot);
                        serviceInfo(newsea, inputSea);
                    }
                    adjacent = killeTheLivingShip(newsea);
                }
            }
        }
        System.out.println("shoot: alle ships should be dead now (17 points)");
    }

    public ArrayList<Integer> killeTheLivingShip(int[] inputSea) {
        ArrayList<Integer> shipIndexesNotYetKilled = new ArrayList<Integer>();
        ArrayList<Integer> adjacentVacantPoints = new ArrayList<Integer>();
        //HashMap<Integer, Integer> surroundingFields = new HashMap<Integer, Integer>();
        //HashMap<Integer, Integer> tempHashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inputSea.length; i++) {
            if (inputSea[i] < -1) {
                shipIndexesNotYetKilled.add(i);
            }
        }
        //System.out.println("linje 67 -- fejl");
        //System.out.println(shipIndexesNotYetKilled);

        //System.out.println("linje 69--fejl");
        //shipIndexesNotYetKilled indeholder nu alle levende skibspunkter.
        // nu skal der laves en ArrayList med alle de indtil ligende punkter.
        for (int i = 0; i < shipIndexesNotYetKilled.size(); i++) {
            int seaIndex = shipIndexesNotYetKilled.get(i);
            //north
            if (seaIndex > 9 && inputSea[seaIndex - 10] == 1) {
                adjacentVacantPoints.add(seaIndex - 10);
            }
            //south
            if (seaIndex < 90 && inputSea[seaIndex + 10] == 1) {
                adjacentVacantPoints.add(seaIndex + 10);
            }
            //east
            if (seaIndex % 10 < 9 && inputSea[seaIndex + 1] == 1) {
                adjacentVacantPoints.add(seaIndex + 1);
            }
            //west
            if (seaIndex % 10 > 0 && inputSea[seaIndex - 1] == 1) {
                adjacentVacantPoints.add(seaIndex - 1);
            }
        }
        //System.out.println("linje 91 -- fejl");
        //System.out.println(adjacentVacantPoints);

        return adjacentVacantPoints;
    }

    //if all indexes in inputSea are < 0: -1 is returned
    public int getFirstLivingIndex(int[] inputSea) {
        for (int i = 0; i < inputSea.length; i++) {
            if (inputSea[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public int[] fixedSeaWithSips() {
        int[] fixedSea
                = {1, 1, 1, 1, 3, 3, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 5, 1, 1, 1,
                    7, 7, 7, 1, 1, 1, 5, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 5, 1, 1, 11,
                    1, 1, 9, 1, 1, 1, 1, 1, 1, 11,
                    1, 1, 9, 1, 1, 1, 1, 1, 1, 11,
                    1, 1, 9, 1, 1, 1, 1, 1, 1, 11,
                    1, 1, 9, 1, 1, 1, 1, 1, 1, 11};

        return fixedSea;
    }
}
