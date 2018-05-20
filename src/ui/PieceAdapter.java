package ui;

import game.Piece;
import javafx.scene.image.Image;

import java.io.File;
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
        private String url;
        private Piece piece;
        private static final String style1 = "src/image_sl/player-yellow.png";
        private static final String style2 = "src/image_sl/player-blue.png";
        private static final String style3 = "src/image_sl/player-green.png";
        private static final String style4 = "src/image_sl/player-purple.png";

        public PieceAdapterBuilder(Piece piece) {
            int style = piece.getStyle();
            this.piece = piece;
            try {
                if(style == 1) {
                    url =  new File(style1).toURI().toURL().toExternalForm();
                } else if(style == 2) {
                    url =  new File(style2).toURI().toURL().toExternalForm();
                } else if(style == 3) {
                    url =  new File(style3).toURI().toURL().toExternalForm();
                } else if(style == 4) {
                    url =  new File(style4).toURI().toURL().toExternalForm();
                } else {
                    throw new IllegalArgumentException("The style number must be 1 to 4");
                }
            } catch(MalformedURLException e) {
                e.printStackTrace();
            }
        }

        public PieceAdapter build() {
            return new PieceAdapter(piece, url);
        }
    }
}
