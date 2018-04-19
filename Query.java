import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Query extends Application {
    Stage primarystage;
    int query;
    @FXML public TextField textbox1;
    @FXML private TextField textbox2;
    @FXML private TextField textbox3;
    @FXML private TextField textbox4;
    @FXML private TextField textbox5;
    @FXML private TextField textbox6;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;
    @FXML private Label label6;

    public Query()
    {

    }
    public Query(Stage ps,int query)
    {
        primarystage=ps;
        this.query=query;

    }
    public void launch() throws Exception {
        this.start(primarystage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root= FXMLLoader.load(getClass().getResource("screen2.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        /*if(query==1)
        {
            textbox2.setVisible(false);
            textbox3.setVisible(false);
            textbox4.setVisible(false);
            textbox5.setVisible(false);
            textbox6.setVisible(false);
        }*/
        hello();
    }
    public void hello()
    {
        if(query==1)
        {

        }
    }
}
