package presenter;

import game.Game;
import game.Player;
import ui.GameUI;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GamePresenter {

    private GameUI ui;
    private GameInfo info;
    private Game game;

    public GamePresenter(GameUI ui, GameInfo info) {
        this.ui = ui;
        this.info = info;
        this.game = info.getGame();
    }

    public void start() {
        while(true) {
            switch(game.getActionDescription()) {
                case "default_roll":
                    ui.roll();
                    break;
                case "lucky_roll":
                    ui.setMessage("LUCKY! You can roll the dice one more time.");
                    ui.roll();
                    break;
                case "backward_roll":
                    ui.setMessage("Unfortunately, you are on the backward square.");
                    ui.roll();
                    break;
                case "set_freeze":
                    ui.setMessage("Unfortunately, you are on the freeze square.");
                    break;
                case "un_freeze":
                    ui.setMessage("You is frozen, please play come again next turn.");
                    break;
            }

            game.doAction();

            Map<String,Object> data = game.getActionData().getAll();
            if(data.containsKey("face") && !data.get("state").equals("move")) {
                ui.setMessage("Dice face: "+data.get("face"));
            }
            switch((String)data.get("state")) {
                case "move":
                    ui.setMessage("New position: "+(info.getBoard().getPiecePosition(((Player)data.get("player")).getPiece())+1));
                    ui.reRenderBoard();
                    break;
                case "ended":
                    ui.gameEnded((Player)data.get("player"));
                    break;
                case "switch":
                    ui.focusPlayer((Player)data.get("player"));
                    break;
                case "init":
                    ui.initRenderBoard(info.getBoardInfo(),info.getPlayers());
                    ui.setMessage("Current position: "+(info.getBoard().getPiecePosition(((Player)data.get("player")).getPiece())+1));
                    ui.focusPlayer((Player)data.get("player"));
                    break;
            }

            game.finishedAction();
            ui.delay();
        }
    }

    public Map<Player,Integer> getPlayersPosition() {
        Map<Player,Integer> pos = new HashMap<>();
        for(Player p: info.getPlayers()) {
            pos.put(p,info.getBoard().getPiecePosition(p.getPiece()));
        }
        return Collections.unmodifiableMap(pos);
    }

    public int getSquareMoves(int pos) {
        return info.getBoard().snakeLadderSquare(pos);
    }

    public void replay() {
        game.replay();
    }
}
