package ui;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

import game.Piece;
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
	private PieceAdapter[] playersPiece;
	private Player currentPlayer;
	private boolean msgSet = false;
	private int lastFace = 0;
	private String theWinner;
	private ImageStack[] imageStack;

	public void add() {
	    imageStack = new ImageStack[64];
        imageStack[0] = new ImageStack(box1);
        imageStack[1] = new ImageStack(box2);
        imageStack[2] = new ImageStack(box3);
        imageStack[3] = new ImageStack(box4);
        imageStack[4] = new ImageStack(box5);
        imageStack[5] = new ImageStack(box6);
        imageStack[6] = new ImageStack(box7);
        imageStack[7] = new ImageStack(box8);
        imageStack[8] = new ImageStack(box9);
        imageStack[9] = new ImageStack(box10);
        imageStack[10] = new ImageStack(box11);
        imageStack[11] = new ImageStack(box12);
        imageStack[12] = new ImageStack(box13);
        imageStack[13] = new ImageStack(box14);
        imageStack[14] = new ImageStack(box15);
        imageStack[15] = new ImageStack(box16);
        imageStack[16] = new ImageStack(box17);
        imageStack[17] = new ImageStack(box18);
        imageStack[18] = new ImageStack(box19);
        imageStack[19] = new ImageStack(box20);
        imageStack[20] = new ImageStack(box21);
        imageStack[21] = new ImageStack(box22);
        imageStack[22] = new ImageStack(box23);
        imageStack[23] = new ImageStack(box24);
        imageStack[24] = new ImageStack(box25);
        imageStack[25] = new ImageStack(box26);
        imageStack[26] = new ImageStack(box27);
        imageStack[27] = new ImageStack(box28);
        imageStack[28] = new ImageStack(box29);
        imageStack[29] = new ImageStack(box30);
        imageStack[30] = new ImageStack(box31);
        imageStack[31] = new ImageStack(box32);
        imageStack[32] = new ImageStack(box33);
        imageStack[33] = new ImageStack(box34);
        imageStack[34] = new ImageStack(box35);
        imageStack[35] = new ImageStack(box36);
        imageStack[36] = new ImageStack(box37);
        imageStack[37] = new ImageStack(box38);
        imageStack[38] = new ImageStack(box39);
        imageStack[39] = new ImageStack(box40);
        imageStack[40] = new ImageStack(box41);
        imageStack[41] = new ImageStack(box42);
        imageStack[42] = new ImageStack(box43);
        imageStack[43] = new ImageStack(box44);
        imageStack[44] = new ImageStack(box45);
        imageStack[45] = new ImageStack(box46);
        imageStack[46] = new ImageStack(box47);
        imageStack[47] = new ImageStack(box48);
        imageStack[48] = new ImageStack(box49);
        imageStack[49] = new ImageStack(box50);
        imageStack[50] = new ImageStack(box51);
        imageStack[51] = new ImageStack(box52);
        imageStack[52] = new ImageStack(box53);
        imageStack[53] = new ImageStack(box54);
        imageStack[54] = new ImageStack(box55);
        imageStack[55] = new ImageStack(box56);
        imageStack[56] = new ImageStack(box57);
        imageStack[57] = new ImageStack(box58);
        imageStack[58] = new ImageStack(box59);
        imageStack[59] = new ImageStack(box60);
        imageStack[60] = new ImageStack(box61);
        imageStack[61] = new ImageStack(box62);
        imageStack[62] = new ImageStack(box63);
        imageStack[63] = new ImageStack(box64);
	}
    @Override
    public void startGame() {
        setDefaultEvent();
    }


	@Override
	public void initRenderBoard(int[] squares, Player[] players) {
	    if(imageStack == null) {
            add();
        } else {
	        for(int i=0; i<imageStack.length; i++) {
	            imageStack[i].clear();
            }
        }
		playersPiece = new PieceAdapter[players.length];
		for(int i=0; i<playersPiece.length; i++) {
		    playersPiece[i] = new PieceAdapter.PieceAdapterBuilder(players[i].getPiece()).build();
        }
		this.squares = squares;
		for (int i = 0; i < squares.length; i++) {
			if (squares[i] == Square.NORMAL_SQUARE) {
				// DO NOTHING
			} else if (squares[i] == Square.LADDER_SQUARE) {
				try {
                    imageStack[i].push(
							new Image(new File("src/image_sl/ladder.png").toURI().toURL().toExternalForm()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else if (squares[i] == Square.SNAKE_SQUARE) {
				try {
                    imageStack[i].push(
							new Image(new File("src/image_sl/snake.png").toURI().toURL().toExternalForm()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else if (squares[i] == Square.FREEZE_SQUARE) {
				try {
                    imageStack[i].push(
							new Image(new File("src/image_sl/snowflake.png").toURI().toURL().toExternalForm()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

			} else if (squares[i] == Square.BACKWARD_SQUARE) {
				try {
					if(((i-1)/8)%2 == 0) {
                        imageStack[i].push(
                                new Image(new File("src/image_sl/backward.png").toURI().toURL().toExternalForm()));
                    } else {
                        imageStack[i].push(
                                new Image(new File("src/image_sl/backward-rotate.png").toURI().toURL().toExternalForm()));
                    }
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

			} else if (squares[i] == Square.GOAL_SQUARE) {
                try {
                    imageStack[i].push(
                            new Image(new File("src/image_sl/goal.png").toURI().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
			}
		}
        for(PieceAdapter p : playersPiece) {
            imageStack[0].push(p);
        }
        render();
	}

	@Override
	public void reRenderBoard() {
		render();
	}

	@Override
	public void focusPlayer(Player player) {
		currentPlayer = player;
        int pos = presenter.getPlayersPosition().get(currentPlayer);
        int index = getPieceIndex(currentPlayer.getPiece());
        imageStack[pos].focusImage(playersPiece[index]);
        render();
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
		presenter.newGame();
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

        replayBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentPlayer = null;
                presenter.replay();
                startGame();
            }
        });

	}

	private void setPopup(boolean show) {
        background.setVisible(show);
        endgameBG.setVisible(show);
        winnerImg.setVisible(show);
    }

	private void gameEnded() {
        setPopup(true);

        winnerName.setText(theWinner);

        newgameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setPopup(false);
                currentPlayer = null;
                setDefaultEvent();
                rollButton.setText("Start");
                setMessage("New Game");
                newGame();
            }
        });

        replayBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setPopup(false);
                currentPlayer = null;
                setDefaultEvent();
                rollButton.setText("Start");
                setMessage("Replay Game");
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

	public void setCreator(GameInfo.GameCreator creator) {
	    this.creator = creator;
	    presenter = new GamePresenter(this,creator.build());
        startGame();
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
	    int pos = presenter.getPlayersPosition().getOrDefault(currentPlayer,-1);
	    if(pos >= 0) {
            int index = getPieceIndex(currentPlayer.getPiece());
            for (int i = 0; i < imageStack.length; i++) {
                if (pos >= 0) {
                    if (i != pos) {
                        imageStack[i].remove(playersPiece[index]);
                    } else if (!imageStack[i].contains(playersPiece[index]) && i == pos) {
                        imageStack[i].push(playersPiece[index]);
                    }
                }
                imageStack[i].renderImage();
            }
        } else {
	        for(ImageStack i : imageStack) {
	            i.renderImage();
            }
        }
    }

    private int getPieceIndex(Piece p) {
        int index = -1;
        for(int i=0; i<playersPiece.length; i++) {
            if(p.equals(playersPiece[i].getPiece())) {
                index = i;
                break;
            }
        }
        return index;
    }
	
	public void initialize(){
        endgameBG.setVisible(false);
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
