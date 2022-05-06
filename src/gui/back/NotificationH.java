/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author hanna
 */
class NotificationH {
public static void NotifcationOnAction(String text,String title) {
      Image image=new Image("/img/loggg.png");

        Notifications notifications=Notifications.create();
        notifications.graphic(new ImageView(image));
        notifications.text(title);
        notifications.title(text);
        notifications.hideAfter(Duration.seconds(4));
        /*notifications.darkStyle();*/
     /*   notifications.position(Pos.BOTTOM_CENTER);*/
        notifications.show();


    }
}
