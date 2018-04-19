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
    static int query;
    @FXML private TextField textbox1;
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
    }
    public void show()
    {
        System.out.println(query);
        if(query==1) {
            textbox1.setVisible(true);
            label1.setVisible(true);
            label1.setText("Enter Table Name");
        }
        else if(query==2)
        {
            textbox1.setVisible(true);
            label1.setVisible(true);
            label1.setText("Enter Player Name");
        }
        else if(query==3)
        {
            textbox1.setVisible(true);
            textbox2.setVisible(true);
            label1.setVisible(true);
            label2.setVisible(true);
            label1.setText("Enter Team1 id");
            label2.setText("Enter Team2 id");
        }
        else if(query==4)
        {
            label1.setVisible(true);
            textbox1.setVisible(true);
            label1.setText("Enter number of wins");
        }
        else if(query==7)
        {
            label1.setVisible(true);
            textbox1.setVisible(true);
            label1.setText("Enter Team Name");
        }
        else if(query==8)
        {
            label1.setVisible(true);
            textbox1.setVisible(true);
            label1.setText("Enter city name");
        }
        else if(query==9)
        {
            label1.setVisible(true);
            textbox1.setVisible(true);
            label1.setText("Enter number of wickets");
        }
        else if(query==11)
        {
            label1.setVisible(true);
            textbox1.setVisible(true);
            label1.setText("Enter the team name");
        }
    }
    public void submit() throws Exception {
        if(query==1) {
            String x = textbox1.getText();
            DynamicTable db=new DynamicTable(primarystage,query,x,null,null,null,0);
            db.launch();
        }
        if(query==2)
        {
            String x = textbox1.getText();
            DynamicTable db=new DynamicTable(primarystage,query,null,x,null,null,0);
            db.launch();
        }
        if(query==3)
        {
            String x=textbox1.getText();
            String y=textbox2.getText();
            DynamicTable db=new DynamicTable(primarystage,query,null,null,x,y,0);
            db.launch();
        }
        if(query==4||query==9)
        {
            String x=textbox1.getText();
            int y=Integer.parseInt(x);
            DynamicTable db=new DynamicTable(primarystage,query,null,null,null,null,y);
            db.launch();
        }
        if(query==5||query==6||query==10||query==12)
        {
            DynamicTable db=new DynamicTable(primarystage,query,null,null,null,null,0);
            db.launch();
        }
        if(query==7||query==8||query==11)
        {
            String x=textbox1.getText();
            DynamicTable db=new DynamicTable(primarystage,query,null,null,x,null,0);
            db.launch();
        }

    }
}
