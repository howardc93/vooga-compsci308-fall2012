package vooga.turnbased.gamecore;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import vooga.turnbased.gameobject.BattleObject;
import vooga.turnbased.gui.GameWindow;


/**
 * This is gamemode that will run a battle given two lists of BattleObjects that
 * will fight each other.
 * 
 * @author David Howdyshell, Michael Elgart, others (who?)
 * 
 */
public class BattleMode extends GameMode {
    private List<Team> myTeams;
    private BattleState myState;
    private int myTurnCount;
    private int myTeamStartRandomizer;

    /**
     * Constructor for a Battle.
     * 
     * @param gm The parent GameManager that is creating this battle. Will be
     *        alerted
     *        when battle ends.
     * @param modeObjectType The object type this mode uses, i.e.
     *        BattleObject.java
     */
    public BattleMode (GameManager gm, Class modeObjectType) {
        super(gm, modeObjectType);
    }

    private void makeTeams () {
        // BAD BAD TEST CODE
        setObjects();
        myTeams = new ArrayList<Team>();
        List<BattleObject> team1BattleObjects = new ArrayList<BattleObject>();
        team1BattleObjects.add((BattleObject) getObjects().get(0));
        List<BattleObject> team2BattleObjects = new ArrayList<BattleObject>();
        team2BattleObjects.add((BattleObject) getObjects().get(1));
        // BAD BAD BAD
        myTeams.add(new Team(team1BattleObjects));
        myTeams.add(new Team(team2BattleObjects));
    }

    @Override
    public void pause () {
        myTeams.clear();        
    }

    @Override
    public void resume () {
        makeTeams();
        initialize();
        System.out.println("BattleStarting!");        
    }
    
    @Override
    public void update () {
        // TODO Auto-generated method stub

    }


    @Override
    public void paint (Graphics g) {
        Image background = GameWindow.importImage("EditorBackgroundImage");
        g.drawImage(background, 0, 0, background.getWidth(null), background
                .getHeight(null), null);
    }

    public void initialize () {
        myState = BattleState.WAITING_FOR_MOVE;
        myTurnCount = 0;
        // Initialize myTeamStartRandomizer to 0 to number of teams (exclusive)
        // the seed value is going to determine which team starts where 0 =
        // "team 1"
        Random generator = new Random();
        myTeamStartRandomizer = generator.nextInt(myTeams.size());
    }

    public void updateLoop () {
        if (isBattleOver()) {
            endBattle();
        }
        // TODO: figure out how this should work. Right now we just give it the
        // previous team
        // TODO: Take into account animating, requesting user input for player
        // team, etc.
        nextTeam().makeMove(myTeams.get(myTurnCount - 1 % myTeams.size()));
    }

    private void endBattle () {
        // TODO: let myGameManager know the battle has ended
        // need to save game state (sprite health, status, etc)
        // then transition back to mapmode
    }

    private boolean isBattleOver () {
        boolean allDead = false;
        for (Team t : myTeams) {
            if (!t.stillAlive()) {
                allDead = true;
            }
        }
        return allDead;
    }

    @Override
    public void handleKeyPressed (KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void handleKeyReleased (KeyEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * Returns the team that should make the next move and increments
     * myTurnCount by 1.
     * 
     * @return Team that should make next move.
     */
    private Team nextTeam () {
        // Get team index and increment turncount
        myTeams.add(myTeams.remove(0));
        return myTeams.get(0);
    }

    private class Team {
        private final List<BattleObject> myBattleObjects;

        public Team (List<BattleObject> battleObjs) {
            myBattleObjects = battleObjs;
        }

        public boolean stillAlive () {
            for (BattleObject teamMember : myBattleObjects) {
                if (teamMember.isAlive()) { return true; }
            }
            return false;
        }

        public void makeMove (Team enemy) {
            // TODO: fill in behavior here
            // get user input to choose active enemy sprite
            // currentEnemyBattleObject.attackEnemy(currentPlayerBattleObject);
        }

        // TODO: Add more methods here to aid team behavior
    }

    private enum BattleState {
        WAITING_FOR_MOVE, MESSAGE, ANIMATING
    }

    @Override
    public void processGameEvents () {
        // TODO Auto-generated method stub

    }
}
