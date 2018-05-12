package ui;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
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
import javafx.scene.image.Image;
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

	@FXML
	private ImageView background;

	@FXML
	private ImageView board;

	@FXML
	private Label winnerName;

	@FXML
	private ImageView winnerImg;

	@FXML
	private ImageView backWard;

	private Image img;

	@FXML
	private ImageView box1;
	@FXML
	private ImageView box2;
	@FXML
	private ImageView box3;
	@FXML
	private ImageView box4;
	@FXML
	private ImageView box5;
	@FXML
	private ImageView box6;
	@FXML
	private ImageView box7;
	@FXML
	private ImageView box8;
	@FXML
	private ImageView box9;
	@FXML
	private ImageView box10;
	@FXML
	private ImageView box11;
	@FXML
	private ImageView box12;
	@FXML
	private ImageView box13;
	@FXML
	private ImageView box14;
	@FXML
	private ImageView box15;
	@FXML
	private ImageView box16;
	@FXML
	private ImageView box17;
	@FXML
	private ImageView box18;
	@FXML
	private ImageView box19;
	@FXML
	private ImageView box20;
	@FXML
	private ImageView box21;
	@FXML
	private ImageView box22;
	@FXML
	private ImageView box23;
	@FXML
	private ImageView box24;
	@FXML
	private ImageView box25;
	@FXML
	private ImageView box26;
	@FXML
	private ImageView box27;
	@FXML
	private ImageView box28;
	@FXML
	private ImageView box29;
	@FXML
	private ImageView box30;
	@FXML
	private ImageView box31;
	@FXML
	private ImageView box32;
	@FXML
	private ImageView box33;
	@FXML
	private ImageView box34;
	@FXML
	private ImageView box35;
	@FXML
	private ImageView box36;
	@FXML
	private ImageView box37;
	@FXML
	private ImageView box38;
	@FXML
	private ImageView box39;
	@FXML
	private ImageView box40;
	@FXML
	private ImageView box41;
	@FXML
	private ImageView box42;
	@FXML
	private ImageView box43;
	@FXML
	private ImageView box44;
	@FXML
	private ImageView box45;
	@FXML
	private ImageView box46;
	@FXML
	private ImageView box47;
	@FXML
	private ImageView box48;
	@FXML
	private ImageView box49;
	@FXML
	private ImageView box50;
	@FXML
	private ImageView box51;
	@FXML
	private ImageView box52;
	@FXML
	private ImageView box53;
	@FXML
	private ImageView box54;
	@FXML
	private ImageView box55;
	@FXML
	private ImageView box56;
	@FXML
	private ImageView box57;
	@FXML
	private ImageView box58;
	@FXML
	private ImageView box59;
	@FXML
	private ImageView box60;
	@FXML
	private ImageView box61;
	@FXML
	private ImageView box62;
	@FXML
	private ImageView box63;
	@FXML
	private ImageView box64;

	private int[] squares;
	private GamePresenter presenter;
	private GameInfo.GameCreator creator;
	private Player[] players;
	private Player currentPlayer;
	private BeginUI begin;
	private boolean msgSet = false;
	private int lastFace = 0;
	private String theWinner;
	private ImageView[] boxes = new ImageView[65];

	@Override
	public void startGame() {

	}

	public void add() {
		boxes[0] = box1;
		boxes[1] = box2;
		boxes[2] = box3;
		boxes[3] = box4;
		boxes[4] = box5;
		boxes[5] = box6;
		boxes[6] = box7;
		boxes[7] = box8;
		boxes[8] = box9;
		boxes[9] = box10;
		boxes[10] = box11;
		boxes[11] = box12;
		boxes[12] = box13;
		boxes[13] = box14;
		boxes[14] = box15;
		boxes[15] = box16;
		boxes[16] = box17;
		boxes[17] = box18;
		boxes[18] = box19;
		boxes[19] = box20;
		boxes[20] = box21;
		boxes[21] = box22;
		boxes[22] = box23;
		boxes[23] = box24;
		boxes[24] = box25;
		boxes[25] = box26;
		boxes[26] = box27;
		boxes[27] = box28;
		boxes[28] = box29;
		boxes[29] = box30;
		boxes[30] = box31;
		boxes[31] = box32;
		boxes[32] = box33;
		boxes[33] = box34;
		boxes[34] = box35;
		boxes[35] = box36;
		boxes[36] = box37;
		boxes[37] = box38;
		boxes[38] = box39;
		boxes[39] = box40;
		boxes[40] = box41;
		boxes[41] = box42;
		boxes[42] = box43;
		boxes[43] = box44;
		boxes[44] = box45;
		boxes[45] = box46;
		boxes[46] = box47;
		boxes[47] = box48;
		boxes[48] = box49;
		boxes[49] = box50;
		boxes[50] = box51;
		boxes[51] = box52;
		boxes[52] = box53;
		boxes[53] = box54;
		boxes[54] = box55;
		boxes[55] = box56;
		boxes[56] = box57;
		boxes[57] = box58;
		boxes[58] = box59;
		boxes[59] = box60;
		boxes[60] = box61;
		boxes[61] = box62;
		boxes[62] = box63;
		boxes[63] = box64;
	}

	@Override
	public void initRenderBoard(int[] squares, Player[] players) {
		add();
		this.players = players;
		this.squares = squares;
		for (int i = 0; i < squares.length - 1; i++) {
			if (squares[i] == Square.NORMAL_SQUARE) {

			} else if (squares[i] == Square.NORMAL_SQUARE) {

			} else if (squares[i] == Square.LADDER_SQUARE) {

			} else if (squares[i] == Square.SNAKE_SQUARE) {

			} else if (squares[i] == Square.FREEZE_SQUARE) {
				try {
					boxes[i].setImage(
							new Image(new File("src/image_sl/snowflake.png").toURI().toURL().toExternalForm()));
					boxes[i].setVisible(true);
					System.out.println("s"+i);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (squares[i] == Square.BACKWARD_SQUARE) {
				try {
					boxes[i].setImage(
							new Image(new File("src/image_sl/backward.png").toURI().toURL().toExternalForm()));
					boxes[i].setVisible(true);
					System.out.println("b"+i);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (squares[i] == Square.GOAL_SQUARE) {

			}

			render();
		}
	}

	@Override
	public void reRenderBoard() {
		render();
	}

	@Override
	public void focusPlayer(Player player) {
		currentPlayer = player;
		setMessage(currentPlayer.getName() + "'s Turn");
	}

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
		setMessage("Click to roll the dice...", true);
		rollButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showFace();
				setDefaultEvent();
				msgSet = false;
				handleDefaultAction();
			}
		});
		// rollButton.setOnAction(new EventHandler<ActionEvent>() {
		//
		// @Override
		// public void handle(ActionEvent event) {
		//
		// die.roll();
		// int face = die.getFace();
		// diceFace(face);
		// setDefaultEvent();
		// msgSet = false;
		// }
		// });

	}

	@Override
	public void setMessage(String message) {
		setMessage(message, false);
	}

	public void setMessage(String message, boolean append) {
		msgSet = true;
		if (append) {
			this.message.setText(this.message.getText() + "\n" + message);
		} else {
			this.message.setText(message);
		}
	}

	public void newGame() {
		presenter = new GamePresenter(this, creator.build());
		startGame();
	}

	@Override
	public void gameEnded(Player winner) {
		theWinner = winner.getName();
		setMessage("Winner is " + theWinner + "!");
		rollButton.setText("Options");
		rollButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameEnded();
			}
		});

	}

	private void gameEnded() {
		background.setVisible(true);
		endgameBG.setVisible(true);
		newgameImg.setVisible(true);
		replayImg.setVisible(true);
		exitImg.setVisible(true);
		winnerImg.setVisible(true);
		winnerName.setText(theWinner);

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
		if (lastFace == 0) {
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
		Map<Player, Integer> pos = presenter.getPlayersPosition();
		// for(int i=0; i<squares.length; i++) {
		// List<String> names = new ArrayList<>();
		// for(Player p: pos.keySet()) {
		// if(pos.get(p) == i) {
		// if(p.equals(currentPlayer)) {
		// names.add(p.getName()+" <= Current Player.");
		// } else {
		// names.add(p.getName());
		// }
		// }
		// }
		// if(names.size() == 0 && squares[i] == Square.NORMAL_SQUARE) {
		// continue;
		// }
		// System.out.print("SQUARE "+(i+1));
		// switch(squares[i]) {
		// case Square.BACKWARD_SQUARE:
		// System.out.print("\tBACKWARD SQUARE");
		// break;
		// case Square.FREEZE_SQUARE:
		// System.out.print("\tFREEZE SQUARE");
		// break;
		// case Square.GOAL_SQUARE:
		// System.out.print("\tGOAL SQUARE");
		// break;
		// case Square.LADDER_SQUARE:
		// System.out.print("\tLADDER SQUARE");
		// System.out.print("\tGoto: "+(i+1+presenter.getSquareMoves(i)));
		// break;
		// case Square.SNAKE_SQUARE:
		// System.out.print("\tSNAKE SQUARE");
		// System.out.print("\tGoto: "+(i+1+presenter.getSquareMoves(i)));
		// break;
		// }
		// System.out.println();
		// for(String name: names) {
		// System.out.println("..."+name);
		// }
		// }
	}

	public void initialize() {
		endgameBG.setVisible(false);
		newgameImg.setVisible(false);
		replayImg.setVisible(false);
		exitImg.setVisible(false);
		setDefaultEvent();
		// Just for debugging!
		creator = new GameInfo.GameCreator().addPlayer("Sept").addPlayer("Guitar").addPlayer("Mai").snake(0).ladder(0)
				.backward(3).freeze(3);
		newGame();

	}

	public void createBackwardImages() {

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
		while (true) {
			rollButton.setText("Next");
			presenter.next();
			if (msgSet) {
				msgSet = false;
				break;
			}
		}
	}

}
