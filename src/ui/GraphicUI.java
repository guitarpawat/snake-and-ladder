package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import game.Die;
import game.Game;
import game.Player;
import game.Square;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import presenter.GameInfo;
import presenter.GamePresenter;

public class GraphicUI implements GameUI {
	@FXML
	private Button rollButton;

	@FXML
	private Label message;
	
	@FXML
	private Label win;

	@FXML
	private ImageView dice1;

	@FXML
	private ImageView dice2;

	@FXML
	private ImageView dice3;

	@FXML
	private ImageView dice4;

	@FXML
	private ImageView dice5;

	@FXML
	private ImageView dice6;

	@FXML
	private ImageView dice;
	
	@FXML
    private Button newgameBtn;

    @FXML
    private Button replayBtn;

    @FXML
    private Button exitBtn;
	
	@FXML
    private ImageView newgameImg;

    @FXML
    private ImageView replayImg;

    @FXML
    private ImageView exitImg;
    
    @FXML
    private AnchorPane endgameBG;

    @FXML
    private TextField playerName;
    
    @FXML
    private TextField playerNum;
	
	private int[] squares;
	private GamePresenter presenter;
	private GameInfo.GameCreator creator;
	private Player[] players;
	private Player currentPlayer;
	private BeginUI begin;
	private boolean msgSet = false;
	private int lastFace = 0;


    @Override
    public void startGame() {

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
		currentPlayer = player;
        setMessage(currentPlayer.getName()+"'s Turn");
	}
//
	@Override
	public void roll() {
        dice.setVisible(true);
        dice1.setVisible(false);
        dice2.setVisible(false);
        dice3.setVisible(false);
        dice4.setVisible(false);
        dice5.setVisible(false);
        dice6.setVisible(false);
        rollButton.setText("Roll");
        setMessage("Click to roll the dice...",true);
        rollButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showFace();
                setDefaultEvent();
                msgSet = false;
                handleDefaultAction();
            }
        });
//		rollButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//
//				die.roll();
//				int face = die.getFace();
//				diceFace(face);
//				setDefaultEvent();
//				msgSet = false;
//			}
//		});
		
	}

	@Override
	public void setMessage(String message) {
        setMessage(message,false);
	}

	public void setMessage(String message, boolean append) {
        msgSet = true;
        if(append) {
            this.message.setText(this.message.getText()+"\n"+message);
        } else {
            this.message.setText(message);
        }
    }
	
	public void newGame(){
		presenter = new GamePresenter(this,creator.build());
		startGame();
	}
//
	@Override
	public void gameEnded(Player winner) {
        setMessage("Winner is "+winner.getName()+"!");
        rollButton.setText("Options");
        rollButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameEnded();
            }
        });

	}

	private void gameEnded() {
        endgameBG.setVisible(true);
        newgameImg.setVisible(true);
        replayImg.setVisible(true);
        exitImg.setVisible(true);

        newgameBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                newGame();
            }
        });

        replayBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentPlayer = null;
                presenter.replay();
            }
        });

        exitBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

    }

	@Override
	public void diceFace(int face) {
        lastFace = face;
	}

	private void showFace() {
        dice.setVisible(false);
        dice1.setVisible(false);
        dice2.setVisible(false);
        dice3.setVisible(false);
        dice4.setVisible(false);
        dice5.setVisible(false);
        dice6.setVisible(false);
        if(lastFace == 0) {
            dice.setVisible(true);
        } else if (lastFace == 1) {
            dice1.setVisible(true);
        } else if (lastFace == 2) {
            dice2.setVisible(true);
        } else if (lastFace == 3) {
            dice3.setVisible(true);
        } else if (lastFace == 4) {
            dice4.setVisible(true);
        } else if (lastFace == 5) {
            dice5.setVisible(true);
        } else if (lastFace == 6) {
            dice6.setVisible(true);
        }
    }
//	
	private void render() {
        Map<Player,Integer> pos = presenter.getPlayersPosition();
//        for(int i=0; i<squares.length; i++) {
//            List<String> names = new ArrayList<>();
//            for(Player p: pos.keySet()) {
//                if(pos.get(p) == i) {
//                    if(p.equals(currentPlayer)) {
//                        names.add(p.getName()+" <= Current Player.");
//                    } else {
//                        names.add(p.getName());
//                    }
//                }
//            }
//            if(names.size() == 0 && squares[i] == Square.NORMAL_SQUARE) {
//                continue;
//            }
//            System.out.print("SQUARE "+(i+1));
//            switch(squares[i]) {
//                case Square.BACKWARD_SQUARE:
//                    System.out.print("\tBACKWARD SQUARE");
//                    break;
//                case Square.FREEZE_SQUARE:
//                    System.out.print("\tFREEZE SQUARE");
//                    break;
//                case Square.GOAL_SQUARE:
//                    System.out.print("\tGOAL SQUARE");
//                    break;
//                case Square.LADDER_SQUARE:
//                    System.out.print("\tLADDER SQUARE");
//                    System.out.print("\tGoto: "+(i+1+presenter.getSquareMoves(i)));
//                    break;
//                case Square.SNAKE_SQUARE:
//                    System.out.print("\tSNAKE SQUARE");
//                    System.out.print("\tGoto: "+(i+1+presenter.getSquareMoves(i)));
//                    break;
//            }
//            System.out.println();
//            for(String name: names) {
//                System.out.println("..."+name);
//            }
//        }
    }
	
	public void initialize(){
        endgameBG.setVisible(false);
        newgameImg.setVisible(false);
        replayImg.setVisible(false);
        exitImg.setVisible(false);
        setDefaultEvent();
	    // Just for debugging!
		creator = new GameInfo.GameCreator(10).addPlayer("Sept").addPlayer("Guitar").addPlayer("Mai")
                .snake(0).ladder(0).backward(0).freeze(0);
		newGame();

	}

	private void setDefaultEvent() {
        rollButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDefaultAction();
            }
        });
    }

    private void handleDefaultAction() {
        while(true) {
            rollButton.setText("Next");
            presenter.next();
            if(msgSet) {
                msgSet = false;
                break;
            }
        }
    }






}
