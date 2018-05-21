package ui;

import game.Piece;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class PieceAdapter extends Image {

    private Piece piece;

    private PieceAdapter(Piece piece, String url) {
        super(url);
        this.piece = piece;
    }

    public int getStyle() {
        return piece.getStyle();
    }

    public Piece getPiece() {
        return piece;
    }

    public static class PieceAdapterBuilder {
        private String img;
        private Piece piece;
        private static final String style1 = "image_sl/player-yellow.png";
        private static final String style2 = "image_sl/player-blue.png";
        private static final String style3 = "image_sl/player-green.png";
        private static final String style4 = "image_sl/player-purple.png";

        public PieceAdapterBuilder(Piece piece) {
            int style = piece.getStyle();
            this.piece = piece;
            if(style == 1) {
                img = style1;
            } else if(style == 2) {
                img = style2;
            } else if(style == 3) {
                img = style3;
            } else if(style == 4) {
                img = style4;
            } else {
                throw new IllegalArgumentException("The style number must be 1 to 4");
            }
        }

        public PieceAdapter build() {
            return new PieceAdapter(piece, img);
        }
    }
}
