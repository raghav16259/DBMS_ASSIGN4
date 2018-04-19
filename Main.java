import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
    @FXML private TextField textbox;
    static Stage ps;
    public static void main()
    {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {

        ps=primaryStage;

            Parent root=FXMLLoader.load(getClass().getResource("sample_form.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public void submit() throws Exception {
        String x=textbox.getText();
        int val=Integer.parseInt(x);
        if(val==1)
        {
            Query query=new Query(ps,1);
            try {
                query.launch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
