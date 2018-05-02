package ui;

import game.Player;
import game.Square;
import presenter.GameInfo;
import presenter.GamePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI implements GameUI {

    private int[] squares;
    private Player[] players;
    private Player currentPlayer;
    private GamePresenter presenter;

    private Scanner scanner = new Scanner(System.in);

    public ConsoleUI() {
        this(new GameInfo.GameCreator().addPlayer("Guitar").addPlayer("Sept"));
    }

    public ConsoleUI(GameInfo.GameCreator creator) {
        presenter = new GamePresenter(this,creator.build());
        presenter.start();
    }

    @Override
    public void initRenderBoard(int[] squares, Player[] players) {
        this.players = players;
        this.squares = squares;
        render();
    }

    @Override
    public void reRenderBoard() {
        render();
    }

    @Override
    public void focusPlayer(Player player) {
        System.out.println("-------------------------------");
        currentPlayer = player;
        System.out.println(currentPlayer.getName()+"'s Turn");
    }

    @Override
    public void roll() {
        System.out.println("Press enter to roll the dice...");
        scanner.nextLine();
    }

    @Override
    public void delay() {
        try {
            Thread.sleep(321);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setMessage(String message) {
        System.out.println(message);
    }

    private void render() {
        Map<Player,Integer> pos = presenter.getPlayersPosition();
        for(int i=0; i<squares.length; i++) {
            List<String> names = new ArrayList<>();
            for(Player p: pos.keySet()) {
                if(pos.get(p) == i) {
                    if(p.equals(currentPlayer)) {
                        names.add(p.getName()+" <= Current Player.");
                    } else {
                        names.add(p.getName());
                    }
                }
            }
            if(names.size() == 0 && squares[i] == Square.NORMAL_SQUARE) {
                continue;
            }
            System.out.print("SQUARE "+(i+1));
            switch(squares[i]) {
                case Square.BACKWARD_SQUARE:
                    System.out.print("\tBACKWARD SQUARE");
                    break;
                case Square.FREEZE_SQUARE:
                    System.out.print("\tFREEZE SQUARE");
                    break;
                case Square.GOAL_SQUARE:
                    System.out.print("\tGOAL SQUARE");
                    break;
                case Square.LADDER_SQUARE:
                    System.out.print("\tLADDER SQUARE");
                    System.out.print("\tGoto: "+(i+1+presenter.getSquareMoves(i)));
                    break;
                case Square.SNAKE_SQUARE:
                    System.out.print("\tSNAKE SQUARE");
                    System.out.print("\tGoto: "+(i+1+presenter.getSquareMoves(i)));
                    break;
            }
            System.out.println();
            for(String name: names) {
                System.out.println("..."+name);
            }
        }
    }
}
