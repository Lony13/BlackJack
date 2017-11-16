package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Created by Lony13 on 2016-12-27.
 */
public class Controller {
    @FXML
    private Button buttonStartGame;
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonHit;
    @FXML
    private Button buttonStand;
    @FXML
    private Button buttonPlayAgain;
    @FXML
    private Button buttonBet;
    @FXML
    private TextArea textArea;
    @FXML
    private TextArea textAreaMoney;
    @FXML
    private ImageView imageTlo;
    @FXML
    private Slider slider;
    @FXML
    private HBox playerImages;
    @FXML
    private HBox dealerImages;

    public Button getButtonStartGame(){
        return buttonStartGame;
    }

    public Button getButtonExit(){
        return buttonExit;
    }

    public Button getButtonHit(){
        return buttonHit;
    }

    public Button getButtonStand(){
        return buttonStand;
    }

    public Button getButtonPlayAgain() {
        return buttonPlayAgain;
    }

    public Button getButtonBet() {
        return buttonBet;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public TextArea getTextAreaMoney() {
        return textAreaMoney;
    }

    public ImageView getImageTlo() {
        return imageTlo;
    }

    public Slider getSlider() {
        return slider;
    }

    public HBox getPlayerImagesHbox(){
        return playerImages;
    }

    public HBox getDealerImagesHbox(){
        return dealerImages;
    }

    public ImageView[] getPlayerImages() {
        ObservableList<Node> nodeTable = playerImages.getChildren();
        ImageView[] images = new ImageView[8];
        int i =0;
        for (Node n : nodeTable) {
            images[i++] = (ImageView) n;
        }
        return images;
    }

    public ImageView[] getDealerImages() {
        ObservableList<Node> nodeTable = dealerImages.getChildren();
        ImageView[] images = new ImageView[8];
        int i = 0;
        for(Node n : nodeTable){
            images[i++] = (ImageView) n;
        }
        return images;
    }

    public void buttonExitPressed(ActionEvent event){
        System.exit(1);
    }
}