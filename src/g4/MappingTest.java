/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g4;

/**
 *
 * @author GertLehmann
 */
public class MappingTest {
    
        public static void main(String[] args) {
      
        
        
        nr01();
    
        
        
    }
    
    
     public static void nr01() {
        
        HeatMapping heatmap = new HeatMapping(); 
         
        System.out.println("day1 - nr01 - test range of ship placement");

        int[] sea = new int[10 * 10];
        for (int i = 0; i < sea.length; i++) {
            sea[i] = 1;
        }

//        System.out.println("empty sea:");
//        this.printSea(sea);
//        System.out.println("");
//        
//        System.out.println("place ships Anonymous:");
//        sea = this.placeShipsAnonymous(sea);
//        this.printSea(sea);
//        System.out.println("");
//        
//        for (int i = 0; i < sea.length; i++) {
//            sea[i] = 1;
//        }        
//
//        sea[61] = -1;
//        sea[62] = -1;
//        sea[63] = -1;
//        sea[64] = -1;
//        sea[65] = -1;
//        sea[66] = -1;
//        this.printSea(sea);
        System.out.println("simple heatmap:");
        int[] fleet1 = {2, 3, 3, 4, 5};
        int[] heat1 = heatmap.simpleHeatMap(sea, fleet1);
        heatmap.printHeatmap(10000, heat1);
        System.out.println("");

        System.out.println("simple heatmap:");
        int[] fleet = {2, 3, 3, 4};
        int[] heat = heatmap.simpleHeatMap(sea, fleet);
        heatmap.printHeatmap(10000, heat);

    }   
    
}