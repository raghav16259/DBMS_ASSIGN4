import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;


public class DynamicTable extends Application{

    Stage primarystage;
    static String table;
    static int query;
    static String player;
    static String team1;
    static String team2;
    static int wins;
    DynamicTable()
    {

    }
    DynamicTable(Stage ps,int query,String table,String player,String team1,String team2,int wins)
    {
        primarystage=new Stage();
        this.table=table;
        this.query=query;
        this.player=player;
        this.team1=team1;
        this.team2=team2;
        this.wins=wins;
    }
    public void launch() throws Exception {
        this.start(primarystage);
    }

    //TABLE VIEW AND DATA
    private ObservableList<ObservableList> data;
    private TableView tableview;

    //MAIN EXECUTOR
    public static void main(String[] args) {
        launch(args);
    }

    //CONNECTION DATABASE
    public void buildData(){
        Connection c ;
        data = FXCollections.observableArrayList();
        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ipl?useSSL=false", "myuser", "xxxx");
            Statement stmt = conn.createStatement();
            String SQL="";
            if(query==1) {
                if (table.equals("points"))
                    SQL = "SELECT * FROM points ORDER BY points DESC, runrate DESC";
                else
                    SQL = "SELECT * from " + table;
            }
            else if(query==2)
            {
                SQL="SELECT name,runs, wicket FROM player WHERE name='"+ player+"'";
            }
            else if (query==3)
            {
                SQL="SELECT * FROM fixtures where hometeam='"+team1+"' UNION SELECT  * FROM fixtures where awayteam='"+team2+"' UNION SELECT  * FROM fixtures where awayteam='"+team1+"' UNION SELECT  * FROM fixtures where hometeam='"+team2+"'";
            }
            else if(query==4)
            {
                if(wins<=2)
                SQL="SELECT points.id , name , won from points,teams WHERE points.id = teams.id AND won >= ALL (SELECT won from points WHERE won="+wins+")";
            }
            else if(query==5)
            {
               SQL="SELECT COUNT(player.name), team FROM player GROUP BY team";
            }
            else if(query==6)
            {
                SQL="SELECT player.name,teams.name,runs,wicket from (teams JOIN player on captain=player.name)";
            }
            else if(query==7)
            {
                SQL="SELECT name,runs,team from player WHERE runs=(select max(runs) from player where team='"+team1+"') and team ='"+team1+"'";
            }
            else if(query==8)
            {
                SQL="SELECT fixtures.date,fixtures.hometeam, fixtures.awayteam,fixtures.stadium,stadium.city,fixtures.result FROM (fixtures JOIN stadium ON fixtures.stadium=stadium.name\n" +
                        ") WHERE city='"+team1+"'";
            }
            else if(query==9)
            {
                SQL="SELECT player.team,sum(player.wicket) FROM player GROUP BY player.team HAVING sum(player.wicket)>"+wins;
            }
            else if(query==10)
            {
                SQL="SELECT * from points where runrate=(SELECT min(runrate) from points )";
            }
            else if(query==11)
            {
                SQL="SELECT * from stadium WHERE stadium.hometeam in (SELECT hometeam from stadium where hometeam='"+team1+"')";
            }
            else if(query==12)
            {
                SQL="SELECT avg(runs), avg(wicket) from player";
            }

            //ResultSet
            ResultSet rs = stmt.executeQuery(SQL);

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        tableview = new TableView();
        buildData();
        Scene scene = new Scene(tableview);
        stage.setScene(scene);
        stage.show();
    }
}