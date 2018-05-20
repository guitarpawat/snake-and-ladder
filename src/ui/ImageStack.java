package ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ImageStack extends Stack<Image> {
    private ImageView imageView;

    public ImageStack(ImageView view) {
        imageView = view;
    }

    public void focusImage(Image img) {
        remove(img);
        push(img);
    }

    public void renderImage() {
        if(!empty()) {
            imageView.setImage(peek());
            imageView.setVisible(true);
        } else {
            imageView.setVisible(false);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

//    @Override
//    public boolean remove(Object img) {
//        List<Image> images = new ArrayList<>();
//        while(!empty()) {
//            Image latest = pop();
//            if(latest.equals(img)) {
//                break;
//            }
//            images.add(latest);
//        }
//        while(!images.isEmpty()) {
//            push(images.get(images.size()-1));
//            images.remove(peek());
//        }
//        return true;
//    }
}
