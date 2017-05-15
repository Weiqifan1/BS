/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g04;

import battleship.interfaces.BattleshipsPlayer;
import tournament.player.PlayerFactory;
import tournament.game.*;
        
/**
 *
 * @author Christian
 */
public class G04 implements PlayerFactory<BattleshipsPlayer>{

    public G04(){}

    @Override
    public BattleshipsPlayer getNewInstance() {
        return new MyShooter();
    }

    @Override
    public String getID() {
        return "G04";
    }

    @Override
    public String getName() {
        return "ChrGertLene BS";
    }

    @Override
    public String[] getAuthors() {
        return new String[] {"Christian", "Gert", "Lene"};
    }
    
}
