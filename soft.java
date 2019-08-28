/*************************
 Information:
 プログラム名：soft.java
 概要：食材情報の管理
 追記:指定のテキストファイルが同一ディレクトリに存在
 日時：2017/07/04
 Version：v1.0/v1.0
 入力：user.txt/food.txt/order.txt
 出力：food.txt
 End of Information;
 ************************/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.geometry.Insets;
import javafx.scene.Group;
import java.util.*;
import java.io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

//ユーザ情報クラス
class User{
String name,id;
public User(String name,String id){
this.name=name;
this.id=id;
}
}

//日付のクラス
class Days{
int year,month,day;
public Days(int year,int month,int day)
{
        this.year=year;
        this.month=month;
        this.day=day;
}

}
//食材データのクラス
class Fooddata {
String id,name,place,list;
int price,rest;
Days date,trade;
public Fooddata(String id,String name,String place,int rest,Days trade,int price,
Days date,String list){
this.id=id;
this.name=name;
this.rest=rest;
this.place=place;
this.trade=trade;
this.price=price;
this.date=date;
this.list=list;
}

}
class Order{
String id,name,adress,mail,tel,list;
public Order(String id,String name,String adress,String mail,String tel,String list){
this.id=id;
this.name=name;
this.adress=adress;
this.mail=mail;
this.tel=tel;
this.list=list;
}


}



class Flist {
static ArrayList<Fooddata> list = new ArrayList<Fooddata>();
    static void set(String file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                    String line=br.readLine();
                    String [] e=line.split("\\s+");
                    String [] d=e[4].split("-",0);
                    String [] d1=e[6].split("-",0);
                    Days x=new Days(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]));
                    Days x1=new Days(Integer.parseInt(d1[0]),Integer.parseInt(d1[1]),Integer.parseInt(d1[2]));
                    list.add(new Fooddata(e[0],e[1],e[2],Integer.parseInt(e[3]),x,Integer.parseInt(e[5]),x1,e[7])) ;
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("Cannot find the file");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Error IO");
            e.printStackTrace();
        } finally {
            if(br != null){
                try{
                    br.close();
                } catch(IOException e) {
                    System.out.println("Cannot close the file");
                    e.printStackTrace();
                }
            }
        }
    }
static void write(String file){
PrintWriter pw = null;
try{
        pw =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
        for(int i=0;i<list.size();i++){
        pw.print(list.get(i).id+" ");
        pw.print(list.get(i).name+" ");
        pw.print(list.get(i).place+" ");
        pw.print(list.get(i).rest+" ");
        pw.print(String.valueOf(list.get(i).trade.year)+"-"
        +String.valueOf(list.get(i).trade.month)+"-"
        +String.valueOf(list.get(i).trade.day)+" ");
        pw.print(list.get(i).price+" ");
        pw.print(String.valueOf(list.get(i).date.year)+"-"
        +String.valueOf(list.get(i).date.month)+"-"
        +String.valueOf(list.get(i).date.day)+" ");
        pw.println(list.get(i).list);
        }

        pw.close();
    }catch(IOException e){
      System.out.println(e);
    }
  }
}

class Ulist {
static ArrayList<User> list = new ArrayList<User>();
    static void set(String file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                    String line=br.readLine();
                    String [] e=line.split("\\s+");
                    list.add(new User(e[0],e[1]));
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("Cannot find the file");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Error IO");
            e.printStackTrace();
        } finally {
            if(br != null){
                try{
                    br.close();
                } catch(IOException e) {
                    System.out.println("Cannot close the file");
                    e.printStackTrace();
                }
            }
        }
    }
}

class Olist {
static ArrayList<Order> list = new ArrayList<Order>();
    static void set(String file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                    String line=br.readLine();
                    String [] e=line.split("\\s+");
                    list.add(new Order(e[0],e[1],e[2],e[3],e[4],e[5]));
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("Cannot find the file");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Error IO");
            e.printStackTrace();
        } finally {
            if(br != null){
                try{
                    br.close();
                } catch(IOException e) {
                    System.out.println("Cannot close the file");
                    e.printStackTrace();
                }
            }
        }
    }
}
class sprice implements Comparator<Fooddata>{
public int compare(Fooddata a,Fooddata b){
if(a.price<b.price)
return -1;
else if(a.price>b.price)
return 1;
else return 0;
}
}
class srest implements Comparator<Fooddata>{
public int compare(Fooddata a,Fooddata b){
if(a.rest<b.rest)
return -1;
else if(a.rest>b.rest)
return 1;
else return 0;
}
}
class sdate implements Comparator<Fooddata>{
public int compare(Fooddata a,Fooddata b){
if(a.date.year<b.date.year)
return -1;
else if(a.date.year>b.date.year)
return 1;
else{
if(a.date.month<b.date.month)
return -1;
else if(a.date.month>b.date.month)
return 1;
else 
{if(a.date.day<b.date.day)
return -1;
else if(a.date.day>b.date.day)
return 1;
else return 0;
}
}
}
}


public class soft extends Application {
        public static Stage save,save1,save2;
        int a=0,b=0,c=0;
        String order;
        Label label  =new Label();
        Label label1 = new Label("ユーザ名 :");
        Label label2 = new Label("暗証番号 :");
        TextField text =new TextField();
        PasswordField pass = new PasswordField();
        Button button =new Button("ログイン");
        private final TableView table = new TableView();
        private final TableView table1 = new TableView();
        static Ulist list;
        static Flist list1;
        static Olist list2;
        static ArrayList <Fooddata>slist=new ArrayList<Fooddata>();
        static List <Label>nl1=new ArrayList<Label>();
        static List <Label>nl2=new ArrayList<Label>();
        static List <Label>nl3=new ArrayList<Label>();
        static List <Label>nl4=new ArrayList<Label>();
        static List <Label>nl5=new ArrayList<Label>();
        static List <Label>nl6=new ArrayList<Label>();
        static List <Label>nl7=new ArrayList<Label>();
        static List <Label>nl8=new ArrayList<Label>();
        static List <Button>nnl1=new ArrayList<Button>();
        static List <Label>nnl2=new ArrayList<Label>();
        static List <Label>nnl3=new ArrayList<Label>();
        static List <Label>nnl4=new ArrayList<Label>();
        static List <Label>nnl5=new ArrayList<Label>();
        static List <Label>nnl6=new ArrayList<Label>();
        TextField text8 =new TextField();
        TextField text1 =new TextField();
        TextField text2 =new TextField();
        TextField text3 =new TextField();
        TextField text4 =new TextField();
        TextField text5 =new TextField();
        TextField text6 =new TextField();
        TextField text7 =new TextField();
        TextField text9 =new TextField();
        TextField y=new TextField(); TextField m=new TextField(); TextField d=new TextField();
        TextField y1=new TextField(); TextField m1=new TextField(); TextField d1=new TextField();

    public static void main(String[] args) {
            
            list.set("user.txt");
            list1.set("food.txt");
            list2.set("order.txt");
        for(int i=0;i<list1.list.size();i++){
            Label n1 = new Label();
            Label n2 = new Label();
            Label n3 = new Label();
            Label n4 = new Label();
            Label n5 = new Label();
            Label n6 = new Label();
            Label n7 = new Label();
            Label n8 = new Label();
            
            n1.setMinSize(60,5);
            n2.setMinSize(100,5);
            n3.setMinSize(120,5);
            n4.setMinSize(120,5);
            n5.setMinSize(120,5);
            n6.setMinSize(100,5);
            n7.setMinSize(120,5);
            n8.setMinSize(300,5);
            
            n1.setText(list1.list.get(i).id);
            n2.setText(list1.list.get(i).name);
            n3.setText(list1.list.get(i).place);
            n4.setText(String.valueOf(list1.list.get(i).rest));
            n5.setText(String.valueOf(list1.list.get(i).trade.year)+"-"
            +String.valueOf(list1.list.get(i).trade.month)+"-"
            +String.valueOf(list1.list.get(i).trade.day));
            n6.setText(String.valueOf(list1.list.get(i).price));
            n7.setText(String.valueOf(list1.list.get(i).date.year)+"-"
            +String.valueOf(list1.list.get(i).date.month)+"-"
            +String.valueOf(list1.list.get(i).date.day));
            n8.setText(list1.list.get(i).list);
            
            nl1.add(n1);nl2.add(n2);
            nl3.add(n3);nl4.add(n4);
            nl5.add(n5);nl6.add(n6);
            nl7.add(n7);nl8.add(n8);
}
 slist=list1.list;
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ログイン画面");
        save=stage;
        button.setOnAction(new Login());
        VBox v  =new VBox(20);
        HBox h  =new HBox(40);
        HBox h1 =new HBox(29);
        HBox h2 =new HBox(20);
        HBox h3 =new HBox(200);
        h.getChildren().add(label);
        h1.getChildren().addAll(label1,text);
        h2.getChildren().addAll(label2,pass);
        h3.getChildren().addAll(label,button);
        v.getChildren().addAll(h,h1,h2,h3);
        Scene scene = new Scene(v, 280, 175);
        stage.setScene(scene);
        stage.show();
    }


 class Login implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
    int j=0;
            for(int i=0;i<list.list.size();i++){
            if(list.list.get(i).name.equals(text.getText())&&list.list.get(i).id.equals(pass.getText()))
            {j=1;
            break;}
            if(i+1==list.list.size())
            {
                Stage s=new Stage();
                Label no =new Label("");
                s.setTitle("ログイン失敗通知");
                Label q=new Label("ユーザ名かパスワードが間違っています");
                VBox w=new VBox(10);
                w.getChildren().addAll(no,q);
                w.setAlignment(Pos.CENTER);
                Scene scene = new Scene(w, 300, 80);
                s.setScene(scene);
                s.show();
            }
            }
            if(j==1){
            
            Stage stage = new Stage();
            // モーダルウインドウに設定
        for(int i=0;i<list2.list.size();i++){
            Button nn1 = new Button();
            Label nn2 = new Label();
            Label nn3 = new Label();
            Label nn4 = new Label();
            Label nn5 = new Label();
            Label nn6 = new Label();
            nn1.setMinSize(60,5);
            nn2.setMinSize(100,5);
            nn3.setMinSize(120,5);
            nn4.setMinSize(120,5);
            nn5.setMinSize(120,5);
            nn6.setMinSize(100,5);
            nn1.setText(list2.list.get(i).id);
            nn2.setText(list2.list.get(i).name);
            nn3.setText(list2.list.get(i).adress);
            nn4.setText(list2.list.get(i).mail);
            nn5.setText(list2.list.get(i).tel);
            nn6.setText(list2.list.get(i).list);
            nn1.setOnAction(new Orderex());
            nnl1.add(nn1);nnl2.add(nn2);
            nnl3.add(nn3);nnl4.add(nn4);
            nnl5.add(nn5);nnl6.add(nn6);

 }
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("食材一覧");
            Scene scene = new Scene(new Group());
            Label label  =new Label();
            Label label1 = new Label("ユーザ名 :");
            Button button1 =new Button("発注");
            button1.setOnAction(new Order());
            Button button2 =new Button("ログアウト");
            button2.setOnAction(new Logout());
            Button button3 =new Button("更新");
            button3.setOnAction(new Renew());
            Button button4 =new Button("追加");
            button4.setOnAction(new Add());
            Button button5 =new Button("削除");
            button5.setOnAction(new Delete());
            Button button6 =new Button("検索");
            button6.setOnAction(new Search());
            //table.setEditable(true);           
            Label n1 = new Label("ID");
            Label n2 = new Label("名前");
            Label n3 = new Label("保存場所");
            Button n4 = new Button("在庫数(kg)");
            n4.setOnAction(new Srest());
            Label n5 = new Label("購入場所");
            Button n6 = new Button("値段(kg当たり)");
            n6.setOnAction(new Sprice());
            Button n7 = new Button("消費期限");
            n7.setOnAction(new Sdate());
            Label n8 = new Label("備考");
            n1.setMinSize(60,10);
            n2.setMinSize(100,10);
            n3.setMinSize(120,10);
            n4.setMinSize(120,8);
            n5.setMinSize(120,10);
            n6.setMinSize(100,8);
            n7.setMinSize(120,8);
            n8.setMinSize(300,10);
           // table.setItems(datalist); 
            HBox nh=new HBox();
            VBox v =new VBox(15);
            VBox nv =new VBox(15);
            HBox h  =new HBox(20);
            HBox h1 =new HBox(30);
            nh.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8);
            h.getChildren().addAll(button1,button2,label);
            h1.getChildren().addAll(label,button3,button4,button5,button6);
            v.getChildren().addAll(h,h1,nh);
            nv.getChildren().add(v);
            HBox th=nh;
            for(int i=0;i<list1.list.size();i++){
                nh=new HBox();
                nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
                nv.getChildren().add(nh);
            }

            h.setAlignment(Pos.BASELINE_RIGHT);
            h1.setAlignment(Pos.CENTER);
            ((Group) scene.getRoot()).getChildren().addAll(nv);
            stage.setScene(scene);
            // 新しいウインドウを表示
            stage.show();
            save.close();
            save=stage;
            save1=stage;}
    }
 }
class Logout implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){

        Stage stage=new Stage();
        stage.setTitle("ログイン画面");
        button.setOnAction(new Login());
        VBox v  =new VBox(20);
        HBox h  =new HBox(40);
        HBox h1 =new HBox(29);
        HBox h2 =new HBox(20);
        HBox h3 =new HBox(200);
        text.setText("");
        pass.setText("");
        h.getChildren().add(label);
        h1.getChildren().addAll(label1,text);
        h2.getChildren().addAll(label2,pass);
        h3.getChildren().addAll(label,button);
        v.getChildren().addAll(h,h1,h2,h3);
        Scene scene = new Scene(v, 280, 175);
        stage.setScene(scene);
        save.close();
        stage.show();
        save=stage;
}
}

class Food implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
            save.close();
            Stage stage = new Stage();
            // モーダルウインドウに設定
        
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("食材一覧");
            Scene scene = new Scene(new Group());
            Label label  =new Label();
            Label label1 = new Label("ユーザ名 :");
            Button button1 =new Button("発注");
            button1.setOnAction(new Order());
            Button button2 =new Button("ログアウト");
            button2.setOnAction(new Logout());
            Button button3 =new Button("更新");
            button3.setOnAction(new Renew());
            Button button4 =new Button("追加");
            button4.setOnAction(new Add());
            Button button5 =new Button("削除");
            button5.setOnAction(new Delete());
            Button button6 =new Button("検索");
            button6.setOnAction(new Search());
            //table.setEditable(true);           
            Label n1 = new Label("ID");
            Label n2 = new Label("名前");
            Label n3 = new Label("保存場所");
            Button n4 = new Button("在庫数(kg)");
            n4.setOnAction(new Srest());
            Label n5 = new Label("購入場所");
            Button n6 = new Button("値段(kg当たり)");
            n6.setOnAction(new Sprice());
            Button n7 = new Button("消費期限");
            n7.setOnAction(new Sdate());
            Label n8 = new Label("備考");
            n1.setMinSize(60,10);
            n2.setMinSize(100,10);
            n3.setMinSize(120,10);
            n4.setMinSize(120,10);
            n5.setMinSize(120,10);
            n6.setMinSize(100,10);
            n7.setMinSize(120,10);
            n8.setMinSize(300,10);
           // table.setItems(datalist); 
            HBox nh=new HBox();
            VBox v =new VBox(15);
            VBox nv =new VBox(15);
            HBox h  =new HBox(20);
            HBox h1 =new HBox(30);
            nh.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8);
            h.getChildren().addAll(button1,button2,label);
            h1.getChildren().addAll(label,button3,button4,button5,button6);
            v.getChildren().addAll(h,h1,nh);
            nv.getChildren().add(v);
            HBox th=nh;
            for(int i=0;i<list1.list.size();i++){
                nh=new HBox();
                nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
//                v=new VBox();
                nv.getChildren().add(nh);
//                nv=v;
            }

            h.setAlignment(Pos.BASELINE_RIGHT);
            h1.setAlignment(Pos.CENTER);
            ((Group) scene.getRoot()).getChildren().addAll(nv);
            // Scene scene = new Scene(v, 300,400 );
            stage.setScene(scene);
            // 新しいウインドウを表示
            save.close();
            stage.show();
            save=stage;
    }
 }
class Food2 implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
    save2.close();
            save.close();
            Stage stage = new Stage();
            // モーダルウインドウに設定
        
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("食材一覧");
            Scene scene = new Scene(new Group());
            Label label  =new Label();
            Label label1 = new Label("ユーザ名 :");
            Button button1 =new Button("発注");
            button1.setOnAction(new Order());
            Button button2 =new Button("ログアウト");
            button2.setOnAction(new Logout());
            Button button3 =new Button("更新");
            button3.setOnAction(new Renew());
            Button button4 =new Button("追加");
            button4.setOnAction(new Add());
            Button button5 =new Button("削除");
            button5.setOnAction(new Delete());
            Button button6 =new Button("検索");
            button6.setOnAction(new Search());
            //table.setEditable(true);           
            Label n1 = new Label("ID");
            Label n2 = new Label("名前");
            Label n3 = new Label("保存場所");
            Button n4 = new Button("在庫数(kg)");
            n4.setOnAction(new Srest());
            Label n5 = new Label("購入場所");
            Button n6 = new Button("値段(kg当たり)");
            n6.setOnAction(new Sprice());
            Button n7 = new Button("消費期限");
            n7.setOnAction(new Sdate());
            Label n8 = new Label("備考");
            n1.setMinSize(60,10);
            n2.setMinSize(100,10);
            n3.setMinSize(120,10);
            n4.setMinSize(120,10);
            n5.setMinSize(120,10);
            n6.setMinSize(100,10);
            n7.setMinSize(120,10);
            n8.setMinSize(300,10);
           // table.setItems(datalist); 
            HBox nh=new HBox();
            VBox v =new VBox(15);
            VBox nv =new VBox(15);
            HBox h  =new HBox(20);
            HBox h1 =new HBox(30);
            nh.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8);
            h.getChildren().addAll(button1,button2,label);
            h1.getChildren().addAll(label,button3,button4,button5,button6);
            v.getChildren().addAll(h,h1,nh);
            nv.getChildren().add(v);
            HBox th=nh;
            for(int i=0;i<list1.list.size();i++){
                nh=new HBox();
                nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
//                v=new VBox();
                nv.getChildren().add(nh);
//                nv=v;
            }

            h.setAlignment(Pos.BASELINE_RIGHT);
            h1.setAlignment(Pos.CENTER);
            ((Group) scene.getRoot()).getChildren().addAll(nv);
            // Scene scene = new Scene(v, 300,400 );
            stage.setScene(scene);
            // 新しいウインドウを表示
            save.close();
            stage.show();
            save=stage;
    }
 }

class Renew implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("更新");
        text1.setText("");text2.setText("");
        text3.setText("");text4.setText("");
        text5.setText("");text6.setText("");
        text7.setText("");text8.setText("");
        y.setText("");m.setText("");d.setText("");
        y1.setText("");m1.setText("");d1.setText("");
        Label l  =new Label("");
        Label label  =new Label("旧・食材IDと情報を更新する項目に入力をお願いします ");
        Label label1 = new Label("新・食材ID  ");
        Label label9 = new Label("旧・食材ID  ");
        Label label2 = new Label("名前  ");
        Label label3 = new Label("保存場所  ");
        Label label4 = new Label("在庫数  ");
        Label label5 = new Label("購入日  ");
        Label yl=new Label("年");
        Label ml=new Label("月");
        Label dl=new Label("日");
        Label yl1=new Label("年");
        Label ml1=new Label("月");
        Label dl1=new Label("日");
        Label label6 = new Label("値段  ");
        Label label7 = new Label("消費期限  ");
        Label label8 = new Label("備考  ");
        Button button=new Button("実行");
        button.setOnAction(new Renewex());
        VBox v =new VBox(15);
        HBox x = new HBox();
        HBox h  =new HBox();
        HBox h1 =new HBox(20);
        HBox h2 =new HBox(50);
        HBox h3 =new HBox(25);
        HBox h4 =new HBox(35);
        HBox h5 =new HBox(8);
        HBox h6 =new HBox(50);
        HBox h7 =new HBox(6);
        HBox h8 =new HBox(50);
        HBox h9 =new HBox(20);
        y.setMaxWidth(50); m.setMaxWidth(30); d.setMaxWidth(30);
        y1.setMaxWidth(50); m1.setMaxWidth(30); d1.setMaxWidth(30);
        h.getChildren().add(label);
        x.getChildren().add(l);
        h1.getChildren().addAll(label9,text1);
        h2.getChildren().addAll(label2,text2);
        h3.getChildren().addAll(label3,text3);
        h4.getChildren().addAll(label4,text4);
        h5.getChildren().addAll(label5,y,yl,m,ml,d,dl);
        h6.getChildren().addAll(label6,text6);
        h7.getChildren().addAll(label7,y1,yl1,m1,ml1,d1,dl1);
        h8.getChildren().addAll(label8,text8);
        h9.getChildren().addAll(label1,text9);
        //FlowPane pane = new FlowPane();
        v.getChildren().addAll(h,h1,h9,h2,h3,h4,h5,h6,h7,h8,button);
        v.setAlignment(Pos.CENTER);
        h.setAlignment(Pos.CENTER);
        h1.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        h3.setAlignment(Pos.CENTER);
        h4.setAlignment(Pos.CENTER);
        h5.setAlignment(Pos.CENTER);
        h6.setAlignment(Pos.CENTER);
        h7.setAlignment(Pos.CENTER);
        h8.setAlignment(Pos.CENTER);
        h9.setAlignment(Pos.CENTER);
        button.setAlignment(Pos.BASELINE_RIGHT);
        Scene scene = new Scene(v, 300, 450);
        stage.setScene(scene);
        stage.show();
        save1=stage;

}
}//ここまで更新アクション

//食材情報追加のためのアクション
class Add implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
        text1.setText("");text2.setText("");
        text3.setText("");text4.setText("");
        text5.setText("");text6.setText("");
        text7.setText("");text8.setText("");
        Stage stage =new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("追加");
        Label l  =new Label("");
        Label label  =new Label("追加する食材情報の入力をお願いします ");
        Label label1 = new Label("食材ID  ");
        Label label2 = new Label("名前  ");
        Label label3 = new Label("保存場所  ");
        Label label4 = new Label("在庫数  ");
        Label label5 = new Label("購入日 ");
        Label yl=new Label("年");
        Label ml=new Label("月");
        Label dl=new Label("日");
        Label yl1=new Label("年");
        Label ml1=new Label("月");
        Label dl1=new Label("日");
        y.setMaxWidth(50); m.setMaxWidth(30); d.setMaxWidth(30);
        y1.setMaxWidth(50); m1.setMaxWidth(30); d1.setMaxWidth(30);
        Label label6 = new Label("値段  ");
        Label label7 = new Label("消費期限  ");
        Label label8 = new Label("備考  ");
        text8 =new TextField();
        text1 =new TextField();
        text2 =new TextField();
        text3 =new TextField();
        text4 =new TextField();
        text5 =new TextField();
        text6 =new TextField();
        text7 =new TextField();
        text9 =new TextField();
        Button button=new Button("実行");
        button.setOnAction(new Addex());
        VBox v =new VBox(15);
        HBox x = new HBox();
        HBox h  =new HBox();
        HBox h1 =new HBox(40);
        HBox h2 =new HBox(50);
        HBox h3 =new HBox(25);
        HBox h4 =new HBox(35);
        HBox h5 =new HBox(8);
        HBox h6 =new HBox(50);
        HBox h7 =new HBox(6);
        HBox h8 =new HBox(50);
        HBox h9 =new HBox(20);
        h.getChildren().add(label);
        x.getChildren().add(l);
        h1.getChildren().addAll(label1,text1);
        h2.getChildren().addAll(label2,text2);
        h3.getChildren().addAll(label3,text3);
        h4.getChildren().addAll(label4,text4);
        h5.getChildren().addAll(label5,y,yl,m,ml,d,dl);
        h6.getChildren().addAll(label6,text6);
        h7.getChildren().addAll(label7,y1,yl1,m1,ml1,d1,dl1);
        h8.getChildren().addAll(label8,text8);
        v.getChildren().addAll(h,h1,h2,h3,h4,h5,h6,h7,h8,button);
        v.setAlignment(Pos.CENTER);
        h.setAlignment(Pos.CENTER);
        h1.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        h3.setAlignment(Pos.CENTER);
        h4.setAlignment(Pos.CENTER);
        h5.setAlignment(Pos.CENTER);
        h6.setAlignment(Pos.CENTER);
        h7.setAlignment(Pos.CENTER);
        h8.setAlignment(Pos.CENTER);
        button.setAlignment(Pos.BASELINE_RIGHT);
        Scene scene = new Scene(v, 300, 450);
        stage.setScene(scene);
        stage.show();
        save1=stage;

}
}
//ここまで食材追加アクション
//削除アクション
class Delete implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
        Stage stage =new Stage();
stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("削除");
        Label l  =new Label("");
        Label label  =new Label("削除する食材IDの入力をお願いします ");
        Label label1 = new Label("食材ID  ");
        text1 =new TextField();
        VBox v =new VBox(15);
        Button button=new Button("実行");
        button.setOnAction(new Deleteex());
        HBox h  =new HBox();
        HBox h1 =new HBox(40);
        h.getChildren().add(label);
        h1.getChildren().addAll(label1,text1);
        v.getChildren().addAll(h,h1,button);
        v.setAlignment(Pos.CENTER);
        h.setAlignment(Pos.CENTER);
        h1.setAlignment(Pos.CENTER);
        button.setAlignment(Pos.BASELINE_RIGHT);
        Scene scene = new Scene(v, 300, 150);
        stage.setScene(scene);
        stage.show();
        save1=stage;
}
}//ここまで削除

//検索アクション
class Search implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
        Stage stage =new Stage();
stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("検索");
        Label l  =new Label("");
        Label label  =new Label("検索する食材の名前の入力をお願いします ");
        Label label1 = new Label("食材名");
        text2 =new TextField();
        VBox v =new VBox(15);
        Button button=new Button("実行");
        button.setOnAction(new Searchex());
        HBox h  =new HBox();
        HBox h1 =new HBox(40);
        h.getChildren().add(label);
        h1.getChildren().addAll(label1,text2);
        v.getChildren().addAll(h,h1,button);
        v.setAlignment(Pos.CENTER);
        h.setAlignment(Pos.CENTER);
        h1.setAlignment(Pos.CENTER);
        button.setAlignment(Pos.BASELINE_RIGHT);
        Scene scene = new Scene(v, 300, 150);
        stage.setScene(scene);
        stage.show();
        save1=stage;
}
}
//発注アクション
class Order implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
        Stage stage = new Stage();
        stage.setTitle("発注先一覧");
        Scene scene = new Scene(new Group());
        Label label  =new Label();
        Button button =new Button("食材一覧");
        button.setOnAction(new Food());
        Button button2 =new Button("ログアウト");
        button2.setOnAction(new Logout());
        table1.setEditable(true);
        Label n1 = new Label("ID");
        Label n2 = new Label("発注先名");
        Label n3 = new Label("住所");
        Label n4 = new Label("メールアドレス");
        Label n5 = new Label("電話番号");
        Label n6 = new Label("ステータス");
        n1.setMinSize(60,5);
        n2.setMinSize(100,5);
        n3.setMinSize(120,5);
        n4.setMinSize(120,5);
        n5.setMinSize(120,5);
        n6.setMinSize(100,5);
        VBox v =new VBox(15);
        HBox h  =new HBox(20);
        HBox h1 =new HBox(30);
        VBox nv=new VBox(10);
        
        h1.getChildren().addAll(n1,n2,n3,n4,n5,n6);
        h.getChildren().addAll(button,button2,label);
        v.getChildren().addAll(h,h1);
      //  nv.getChildren().addAll(h1,h);
        for(int i=0;i<list2.list.size();i++){
                HBox nh=new HBox(30);
                nh.getChildren().addAll(nnl1.get(i),nnl2.get(i),nnl3.get(i),nnl4.get(i),
                nnl5.get(i),nnl6.get(i));
//                v=new VBox();
                v.getChildren().add(nh);
//                nv=v;
            }
        
        h.setAlignment(Pos.BASELINE_RIGHT);
        h1.setAlignment(Pos.CENTER);
        ((Group) scene.getRoot()).getChildren().addAll(v);
       // Scene scene = new Scene(v, 300,400 );
        stage.setScene(scene);
        save.close();
        stage.show();
        save=stage;
}
}

//ソートボタンの処理
class Sprice implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
    Collections.sort(slist,new sprice());
    nl1.clear();nl2.clear();nl3.clear();nl4.clear();
    nl5.clear();nl6.clear();nl7.clear();nl8.clear();
    for(int i=0;i<slist.size();i++){         
nl1.add(new Label(slist.get(i).id));nl2.add(new Label(slist.get(i).name));
nl3.add(new Label(slist.get(i).place));nl4.add(new Label(String.valueOf(slist.get(i).rest)));
nl5.add(new Label(String.valueOf(slist.get(i).trade.year)+"-"
+String.valueOf(slist.get(i).trade.month)+"-"
+String.valueOf(slist.get(i).trade.day)));
nl6.add(new Label(String.valueOf(slist.get(i).price)));
nl7.add(new Label(String.valueOf(slist.get(i).date.year)+"-"
+String.valueOf(slist.get(i).date.month)+"-"
+String.valueOf(slist.get(i).date.month)));
nl8.add(new Label(slist.get(i).list));
nl1.get(i).setMinSize(60,10);nl2.get(i).setMinSize(100,10);
  nl3.get(i).setMinSize(120,10);nl4.get(i).setMinSize(120,10);
  nl5.get(i).setMinSize(120,10);nl6.get(i).setMinSize(100,10);
  nl7.get(i).setMinSize(120,10);nl8.get(i).setMinSize(300,10);
    }
    Scene scene = new Scene(new Group());
     Label label  =new Label();
            Label label1 = new Label("ユーザ名 :");
            Button button1 =new Button("発注");
            button1.setOnAction(new Order());
            Button button2 =new Button("ログアウト");
            button2.setOnAction(new Logout());
            Button button3 =new Button("更新");
            button3.setOnAction(new Renew());
            Button button4 =new Button("追加");
            button4.setOnAction(new Add());
            Button button5 =new Button("削除");
            button5.setOnAction(new Delete());
            Button button6 =new Button("検索");
            button6.setOnAction(new Search());
            Label n1 = new Label("ID");
            Label n2 = new Label("名前");
            Label n3 = new Label("保存場所");
            Button n4 = new Button("在庫数(kg)");
            n4.setOnAction(new Srest());
            Label n5 = new Label("購入場所");
            Button n6 = new Button("値段(kg当たり)");
            n6.setOnAction(new Sprice());
            Button n7 = new Button("消費期限");
            n7.setOnAction(new Sdate());
            Label n8 = new Label("備考");
            n1.setMinSize(60,10);
            n2.setMinSize(100,10);
            n3.setMinSize(120,10);
            n4.setMinSize(120,10);
            n5.setMinSize(120,10);
            n6.setMinSize(100,10);
            n7.setMinSize(120,10);
            n8.setMinSize(300,10);
           // table.setItems(datalist); 
            HBox nh=new HBox();
            VBox v =new VBox(15);
            VBox nv =new VBox(15);
            HBox h  =new HBox(20);
            HBox h1 =new HBox(30);
            nh.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8);
            h.getChildren().addAll(button1,button2,label);
            h1.getChildren().addAll(label,button3,button4,button5,button6);
            v.getChildren().addAll(h,h1,nh);
            nv.getChildren().add(v);
            HBox th=nh;
            for(int i=0;i<list1.list.size();i++){
                nh=new HBox();
                nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
//                v=new VBox();
                nv.getChildren().add(nh);
//                nv=v;
            }

            h.setAlignment(Pos.BASELINE_RIGHT);
            h1.setAlignment(Pos.CENTER);
            ((Group) scene.getRoot()).getChildren().addAll(nv);
            // Scene scene = new Scene(v, 300,400 );
            save.setScene(scene);
            // 新しいウインドウを表示
}}

class Srest implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
    Collections.sort(slist,new srest());
    nl1.clear();nl2.clear();nl3.clear();nl4.clear();
    nl5.clear();nl6.clear();nl7.clear();nl8.clear();
    for(int i=0;i<slist.size();i++){         
nl1.add(new Label(slist.get(i).id));nl2.add(new Label(slist.get(i).name));
nl3.add(new Label(slist.get(i).place));nl4.add(new Label(String.valueOf(slist.get(i).rest)));
nl5.add(new Label(String.valueOf(slist.get(i).trade.year)+"-"
+String.valueOf(slist.get(i).trade.month)+"-"
+String.valueOf(slist.get(i).trade.day)));
nl6.add(new Label(String.valueOf(slist.get(i).price)));
nl7.add(new Label(String.valueOf(slist.get(i).date.year)+"-"
+String.valueOf(slist.get(i).date.month)+"-"
+String.valueOf(slist.get(i).date.month)));
nl8.add(new Label(slist.get(i).list));
nl1.get(i).setMinSize(60,10);nl2.get(i).setMinSize(100,10);
  nl3.get(i).setMinSize(120,10);nl4.get(i).setMinSize(120,10);
  nl5.get(i).setMinSize(120,10);nl6.get(i).setMinSize(100,10);
  nl7.get(i).setMinSize(120,10);nl8.get(i).setMinSize(300,10);
    }
    Scene scene = new Scene(new Group());
     Label label  =new Label();
            Label label1 = new Label("ユーザ名 :");
            Button button1 =new Button("発注");
            button1.setOnAction(new Order());
            Button button2 =new Button("ログアウト");
            button2.setOnAction(new Logout());
            Button button3 =new Button("更新");
            button3.setOnAction(new Renew());
            Button button4 =new Button("追加");
            button4.setOnAction(new Add());
            Button button5 =new Button("削除");
            button5.setOnAction(new Delete());
            Button button6 =new Button("検索");
            button6.setOnAction(new Search());
            Label n1 = new Label("ID");
            Label n2 = new Label("名前");
            Label n3 = new Label("保存場所");
            Button n4 = new Button("在庫数(kg)");
            n4.setOnAction(new Srest());
            Label n5 = new Label("購入場所");
            Button n6 = new Button("値段(kg当たり)");
            n6.setOnAction(new Sprice());
            Button n7 = new Button("消費期限");
            n7.setOnAction(new Sdate());
            Label n8 = new Label("備考");
            n1.setMinSize(60,10);
            n2.setMinSize(100,10);
            n3.setMinSize(120,10);
            n4.setMinSize(120,10);
            n5.setMinSize(120,10);
            n6.setMinSize(100,10);
            n7.setMinSize(120,10);
            n8.setMinSize(300,10);
           // table.setItems(datalist); 
            HBox nh=new HBox();
            VBox v =new VBox(15);
            VBox nv =new VBox(15);
            HBox h  =new HBox(20);
            HBox h1 =new HBox(30);
            nh.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8);
            h.getChildren().addAll(button1,button2,label);
            h1.getChildren().addAll(label,button3,button4,button5,button6);
            v.getChildren().addAll(h,h1,nh);
            nv.getChildren().add(v);
            HBox th=nh;
            for(int i=0;i<list1.list.size();i++){
                nh=new HBox();
                nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
//                v=new VBox();
                nv.getChildren().add(nh);
//                nv=v;
            }

            h.setAlignment(Pos.BASELINE_RIGHT);
            h1.setAlignment(Pos.CENTER);
            ((Group) scene.getRoot()).getChildren().addAll(nv);
            // Scene scene = new Scene(v, 300,400 );
            save.setScene(scene);
            // 新しいウインドウを表示
}}
class Sdate implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
    Collections.sort(slist,new sdate());
    nl1.clear();nl2.clear();nl3.clear();nl4.clear();
    nl5.clear();nl6.clear();nl7.clear();nl8.clear();
    for(int i=0;i<slist.size();i++){         
nl1.add(new Label(slist.get(i).id));nl2.add(new Label(slist.get(i).name));
nl3.add(new Label(slist.get(i).place));nl4.add(new Label(String.valueOf(slist.get(i).rest)));
nl5.add(new Label(String.valueOf(slist.get(i).trade.year)+"-"
+String.valueOf(slist.get(i).trade.month)+"-"
+String.valueOf(slist.get(i).trade.day)));
nl6.add(new Label(String.valueOf(slist.get(i).price)));
nl7.add(new Label(String.valueOf(slist.get(i).date.year)+"-"
+String.valueOf(slist.get(i).date.month)+"-"
+String.valueOf(slist.get(i).date.month)));
nl8.add(new Label(slist.get(i).list));
nl1.get(i).setMinSize(60,10);nl2.get(i).setMinSize(100,10);
  nl3.get(i).setMinSize(120,10);nl4.get(i).setMinSize(120,10);
  nl5.get(i).setMinSize(120,10);nl6.get(i).setMinSize(100,10);
  nl7.get(i).setMinSize(120,10);nl8.get(i).setMinSize(300,10);
    }
    Scene scene = new Scene(new Group());
     Label label  =new Label();
            Label label1 = new Label("ユーザ名 :");
            Button button1 =new Button("発注");
            button1.setOnAction(new Order());
            Button button2 =new Button("ログアウト");
            button2.setOnAction(new Logout());
            Button button3 =new Button("更新");
            button3.setOnAction(new Renew());
            Button button4 =new Button("追加");
            button4.setOnAction(new Add());
            Button button5 =new Button("削除");
            button5.setOnAction(new Delete());
            Button button6 =new Button("検索");
            button6.setOnAction(new Search());
            Label n1 = new Label("ID");
            Label n2 = new Label("名前");
            Label n3 = new Label("保存場所");
            Button n4 = new Button("在庫数(kg)");
            n4.setOnAction(new Srest());
            Label n5 = new Label("購入場所");
            Button n6 = new Button("値段(kg当たり)");
            n6.setOnAction(new Sprice());
            Button n7 = new Button("消費期限");
            n7.setOnAction(new Sdate());
            Label n8 = new Label("備考");
            n1.setMinSize(60,10);
            n2.setMinSize(100,10);
            n3.setMinSize(120,10);
            n4.setMinSize(120,10);
            n5.setMinSize(120,10);
            n6.setMinSize(100,10);
            n7.setMinSize(120,10);
            n8.setMinSize(300,10);
           // table.setItems(datalist); 
            HBox nh=new HBox();
            VBox v =new VBox(15);
            VBox nv =new VBox(15);
            HBox h  =new HBox(20);
            HBox h1 =new HBox(30);
            nh.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8);
            h.getChildren().addAll(button1,button2,label);
            h1.getChildren().addAll(label,button3,button4,button5,button6);
            v.getChildren().addAll(h,h1,nh);
            nv.getChildren().add(v);
            HBox th=nh;
            for(int i=0;i<list1.list.size();i++){
                nh=new HBox();
                nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
//                v=new VBox();
                nv.getChildren().add(nh);
//                nv=v;
            }

            h.setAlignment(Pos.BASELINE_RIGHT);
            h1.setAlignment(Pos.CENTER);
            ((Group) scene.getRoot()).getChildren().addAll(nv);
            // Scene scene = new Scene(v, 300,400 );
            save.setScene(scene);
            // 新しいウインドウを表示
}}
//実行ボタンの処理
//更新アクション
class Renewex implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
 if(text1.getText().equals("")){
Stage s =new Stage();
Label la=new Label("IDが入力されていません");
Label la1=new Label("IDの入力をお願いします");
VBox v=new VBox();
v.getChildren().addAll(la,la1);
v.setAlignment(Pos.CENTER);
Scene scene = new Scene(v, 300, 80);
s.setScene(scene);
s.show();
    }else{

if(!y.getText().equals("")||!m.getText().equals("")||!d.getText().equals(""))
text5.setText(y.getText()+"-"+m.getText()+"-"+d.getText());
if(!y1.getText().equals("")||!m1.getText().equals("")||!d1.getText().equals(""))
text7.setText(y1.getText()+"-"+m1.getText()+"-"+d1.getText());
   for(int i=0;i<list1.list.size();i++)
   if(text1.getText().equals(list1.list.get(i).id)){
if(!text9.getText().equals("")){
list1.list.get(i).id=text9.getText();
Label n1=new Label(text9.getText());
n1.setMinSize(60,10);
nl1.set(i,n1);
text9.setText("");
}
if(!text2.getText().equals(""))
{
    list1.list.get(i).name=text2.getText();
    Label n1=new Label(text2.getText());
n1.setMinSize(100,10);
nl2.set(i,n1);
text2.setText("");
}
if(!text3.getText().equals("")){
list1.list.get(i).place=text3.getText();
Label n1= new Label(text3.getText());
n1.setMinSize(120,10);
nl3.set(i,n1);
text3.setText("");
}
if(!text4.getText().equals("")){
list1.list.get(i).rest=Integer.parseInt(text4.getText());
Label n1=new Label(text4.getText());
n1.setMinSize(120,10);
nl4.set(i,n1);
text4.setText("");
}
if(!text5.getText().equals(""))
{list1.list.get(i).trade.year=Integer.parseInt(y.getText());
list1.list.get(i).trade.month=Integer.parseInt(m.getText());
list1.list.get(i).trade.day=Integer.parseInt(d.getText());
Label n1= new Label(text5.getText());
n1.setMinSize(120,10);
nl5.set(i,n1);
text5.setText("");
}
if(!text6.getText().equals(""))
{list1.list.get(i).price=Integer.parseInt(text6.getText());
Label n1=new Label(text6.getText());
n1.setMinSize(100,10);
nl6.set(i,n1);
text6.setText("");
}
if(!text7.getText().equals(""))
{list1.list.get(i).trade.year=Integer.parseInt(y1.getText());
list1.list.get(i).trade.month=Integer.parseInt(m1.getText());
list1.list.get(i).trade.day=Integer.parseInt(d1.getText());
Label n1=new Label(text7.getText());
n1.setMinSize(120,10);
nl7.set(i,n1);
text7.setText("");
}
if(!text8.getText().equals(""))
{list1.list.get(i).list=text8.getText();
Label n1=new Label(text8.getText());
n1.setMinSize(300,10);
nl8.set(i,n1);
text8.setText("");
}
   }
  Scene scene = new Scene(new Group());
     Label label  =new Label();
            Label label1 = new Label("ユーザ名 :");
            Button button1 =new Button("発注");
            button1.setOnAction(new Order());
            Button button2 =new Button("ログアウト");
            button2.setOnAction(new Logout());
            Button button3 =new Button("更新");
            button3.setOnAction(new Renew());
            Button button4 =new Button("追加");
            button4.setOnAction(new Add());
            Button button5 =new Button("削除");
            button5.setOnAction(new Delete());
            Button button6 =new Button("検索");
            button6.setOnAction(new Search());
            Label n1 = new Label("ID");
            Label n2 = new Label("名前");
            Label n3 = new Label("保存場所");
            Button n4 = new Button("在庫数(kg)");
            n4.setOnAction(new Srest());
            Label n5 = new Label("購入場所");
            Button n6 = new Button("値段(kg当たり)");
            n6.setOnAction(new Sprice());
            Button n7 = new Button("消費期限");
            n7.setOnAction(new Sdate());
            Label n8 = new Label("備考");
            n1.setMinSize(60,10);
            n2.setMinSize(100,10);
            n3.setMinSize(120,10);
            n4.setMinSize(120,10);
            n5.setMinSize(120,10);
            n6.setMinSize(100,10);
            n7.setMinSize(120,10);
            n8.setMinSize(300,10);
           // table.setItems(datalist); 
            HBox nh=new HBox();
            VBox v =new VBox(15);
            VBox nv =new VBox(15);
            HBox h  =new HBox(20);
            HBox h1 =new HBox(30);
            nh.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8);
            h.getChildren().addAll(button1,button2,label);
            h1.getChildren().addAll(label,button3,button4,button5,button6);
            v.getChildren().addAll(h,h1,nh);
            nv.getChildren().add(v);
            HBox th=nh;
            for(int i=0;i<list1.list.size();i++){
                nh=new HBox();
                nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
                nv.getChildren().add(nh);
//                nv=v;
            }
h.setAlignment(Pos.BASELINE_RIGHT);
            h1.setAlignment(Pos.CENTER);
            ((Group) scene.getRoot()).getChildren().addAll(nv);
            // Scene scene = new Scene(v, 300,400 );
            save.setScene(scene); 
text1.setText("");
list1.write("food.txt");
    save1.close();
    }
}
}      

//削除画面
class Deleteex implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
    if(text1.getText().equals("")){
Stage s =new Stage();
Label la=new Label("IDが入力されていません");
Label la1=new Label("IDの入力をお願いします");
VBox v=new VBox();
v.getChildren().addAll(la,la1);
v.setAlignment(Pos.CENTER);
Scene scene = new Scene(v, 300, 80);
s.setScene(scene);
s.show();
    }else{
          Stage stage=new Stage();
        Label label=new Label("削除する項目のIDは");
        Label label1=new Label(text1.getText());
        Label label2=new Label("でよろしいですね？");
        Button button=new Button("はい");
        button.setOnAction(new DeleteY());
        Button button1=new Button("いいえ");
        button1.setOnAction(new DeleteN());
        VBox v=new VBox(15);
        HBox h=new HBox(40);
        v.setAlignment(Pos.CENTER);
        h.setAlignment(Pos.CENTER);
        h.getChildren().addAll(label,button,button1);
        v.getChildren().addAll(label,label1,label2,h);
        Scene scene = new Scene(v, 200,160);
        stage.setScene(scene);
        stage.show();
        save2=stage;
    }
      
}
}
//削除YES
class DeleteY implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
int j=0;
for(int i=0;i<list1.list.size();i++){
if(list1.list.get(i).id.equals(text1.getText()))
{
list1.list.remove(i);
i--;
j=1;
}}
if(j==1){
nl1.clear();nl2.clear();nl3.clear();nl4.clear();
nl5.clear();nl6.clear();nl7.clear();nl8.clear();
for(int i=0;i<list1.list.size();i++){
            Label n1 = new Label();
            Label n2 = new Label();
            Label n3 = new Label();
            Label n4 = new Label();
            Label n5 = new Label();
            Label n6 = new Label();
            Label n7 = new Label();
            Label n8 = new Label();
            
            n1.setMinSize(60,5);
            n2.setMinSize(100,5);
            n3.setMinSize(120,5);
            n4.setMinSize(120,5);
            n5.setMinSize(120,5);
            n6.setMinSize(100,5);
            n7.setMinSize(120,5);
            n8.setMinSize(300,5);
            
            n1.setText(list1.list.get(i).id);
            n2.setText(list1.list.get(i).name);
            n3.setText(list1.list.get(i).place);
            n4.setText(String.valueOf(list1.list.get(i).rest));
            n5.setText(String.valueOf(list1.list.get(i).trade.year)+"-"
            +String.valueOf(list1.list.get(i).trade.month)+"-"
            +String.valueOf(list1.list.get(i).trade.day));
            n6.setText(String.valueOf(list1.list.get(i).price));
            n5.setText(String.valueOf(list1.list.get(i).date.year)+"-"
            +String.valueOf(list1.list.get(i).date.month)+"-"
            +String.valueOf(list1.list.get(i).date.day));
            n8.setText(list1.list.get(i).list);
            nl1.add(n1);nl2.add(n2);
            nl3.add(n3);nl4.add(n4);
            nl5.add(n5);nl6.add(n6);
            nl7.add(n7);nl8.add(n8);
}
Scene scene = new Scene(new Group());
     Label label  =new Label();
            Label label1 = new Label("ユーザ名 :");
            Button button1 =new Button("発注");
            button1.setOnAction(new Order());
            Button button2 =new Button("ログアウト");
            button2.setOnAction(new Logout());
            Button button3 =new Button("更新");
            button3.setOnAction(new Renew());
            Button button4 =new Button("追加");
            button4.setOnAction(new Add());
            Button button5 =new Button("削除");
            button5.setOnAction(new Delete());
            Button button6 =new Button("検索");
            button6.setOnAction(new Search());
            Label n1 = new Label("ID");
            Label n2 = new Label("名前");
            Label n3 = new Label("保存場所");
            Button n4 = new Button("在庫数(kg)");
            n4.setOnAction(new Srest());
            Label n5 = new Label("購入場所");
            Button n6 = new Button("値段(kg当たり)");
            n6.setOnAction(new Sprice());
            Button n7 = new Button("消費期限");
            n7.setOnAction(new Sdate());
            Label n8 = new Label("備考");
            n1.setMinSize(60,10);
            n2.setMinSize(100,10);
            n3.setMinSize(120,10);
            n4.setMinSize(120,10);
            n5.setMinSize(120,10);
            n6.setMinSize(100,10);
            n7.setMinSize(120,10);
            n8.setMinSize(300,10);
            HBox nh=new HBox();
            VBox v =new VBox(15);
            VBox nv =new VBox(15);
            HBox h  =new HBox(20);
            HBox h1 =new HBox(30);
            nh.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8);
            h.getChildren().addAll(button1,button2,label);
            h1.getChildren().addAll(label,button3,button4,button5,button6);
            v.getChildren().addAll(h,h1,nh);
            nv.getChildren().add(v);
            HBox th=nh;
            for(int i=0;i<list1.list.size();i++){
                nh=new HBox();
                nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
                nv.getChildren().add(nh);
//                nv=v;
            }
h.setAlignment(Pos.BASELINE_RIGHT);
            h1.setAlignment(Pos.CENTER);
            ((Group) scene.getRoot()).getChildren().addAll(nv);
            // Scene scene = new Scene(v, 300,400 );
            save.setScene(scene);
            // 新しいウインドウを表示
list1.write("food.txt");
save1.close();
save2.close();
}else{
Stage s=new Stage();
    Label no =new Label("");
    s.setTitle("削除失敗");
    Label q=new Label("存在しないIDです");
    VBox w=new VBox(10);
    w.getChildren().addAll(no,q);
                w.setAlignment(Pos.CENTER);
                Scene scene = new Scene(w, 300, 80);
                s.setScene(scene);
                s.show();
}
}
}

//削除NO
class DeleteN implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
    save2.close();
}}

//検索画面
class Searchex implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
    ArrayList <Label> l =new ArrayList<Label>();
    
    int c=0;
    Stage s=new Stage();
    s.setTitle("食材情報検索結果");
VBox nv=new VBox(30);
HBox nh =new HBox();
HBox h =new HBox();
HBox sh =new HBox(420);
HBox sh2 =new HBox(450);
Label la=new Label("該当する名称の食材の情報です");
Button button=new Button("食材一覧に戻る");
button.setOnAction(new Food2());
Label label = new Label("");
Label label2 = new Label("");
Label n1 = new Label("ID");
            Label n2 = new Label("名前");
            Label n3 = new Label("保存場所");
            Label n4 = new Label("在庫数(kg)");
            Label n5 = new Label("購入場所");
            Label n6 = new Label("値段(kg当たり)");
            Label n7 = new Label("消費期限");
            Label n8 = new Label("備考");
            n1.setMinSize(60,10);
            n2.setMinSize(100,10);
            n3.setMinSize(120,10);
            n4.setMinSize(120,10);
            n5.setMinSize(120,10);
            n6.setMinSize(100,10);
            n7.setMinSize(120,10);
            n8.setMinSize(300,10);
sh.getChildren().addAll(label,la);
   nv.getChildren().addAll(sh);
//sh =new HBox(300);
sh2.getChildren().addAll(label2,button);
nv.getChildren().addAll(sh2);
h.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8);
nv.getChildren().addAll(h);
   button.setAlignment(Pos.CENTER);   
        for(int i=0;i<list1.list.size();i++){
if(list1.list.get(i).name.equals(text2.getText()))
{
    nh=new HBox();

    nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
    nv.getChildren().add(nh);
    la.setAlignment(Pos.CENTER);
    c=1;
}
else if(i+1==list1.list.size()&&c==0){

    Label no =new Label("");
    s.setTitle("検索結果");
    Label q=new Label("登録されてない名称です");
    VBox w=new VBox(10);
    w.getChildren().addAll(no,q);
    w.setAlignment(Pos.CENTER);
    Scene scene = new Scene(w, 300, 80);
    s.setScene(scene);
}

}
if(c==1){
    Scene scene = new Scene(nv);
    s.setScene(scene);
    save.close();
    save1.close();
    save2=s;
}  
s.show();
text2.setText("");

}
}
//追加画面
class Addex implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
int c=0;
if(text1.getText().equals("")){
Stage s =new Stage();
Label la=new Label("IDが入力されていません");
Label la1=new Label("IDの入力をお願いします");
VBox v=new VBox();
v.getChildren().addAll(la,la1);
v.setAlignment(Pos.CENTER);
Scene scene = new Scene(v, 300, 80);
s.setScene(scene);
s.show();
c=1;
}

if(!text1.getText().equals("")){
for(int i=0;i<list1.list.size();i++){
if(text1.getText().equals(list1.list.get(i).id)){
Stage s =new Stage();
Label la=new Label("すでに存在するIDです");
Label la1=new Label("別のIDで登録をお願いします");
VBox v=new VBox();
v.getChildren().addAll(la,la1);
v.setAlignment(Pos.CENTER);
Scene scene = new Scene(v, 300, 80);
s.setScene(scene);
s.show();
c=1;}
}
if(text2.getText().equals("")){
Stage s =new Stage();
Label la=new Label("食材名が記入されていません");
Label la1=new Label("食材名の記入をお願いします");
VBox v=new VBox();
v.getChildren().addAll(la,la1);
v.setAlignment(Pos.CENTER);
Scene scene = new Scene(v, 300, 80);
s.setScene(scene);
s.show();
}
if(y.getText().equals("")||m.getText().equals("")||d.getText().equals("")){
Stage s =new Stage();
Label la=new Label("購入日の欄で埋まってない箇所があります");
Label la1=new Label("日付の入力をお願いします");
VBox v=new VBox();
v.getChildren().addAll(la,la1);
v.setAlignment(Pos.CENTER);
Scene scene = new Scene(v, 300, 80);
s.setScene(scene);
s.show();
c=1;
}
if(y1.getText().equals("")||m1.getText().equals("")||d1.getText().equals(""))
{
Stage s =new Stage();
Label la=new Label("消費期限の欄で埋まってない箇所があります");
Label la1=new Label("日付の入力をお願いします");
VBox v=new VBox();
v.getChildren().addAll(la,la1);
v.setAlignment(Pos.CENTER);
Scene scene = new Scene(v, 300, 80);
s.setScene(scene);
s.show();
c=1;
}
if(c!=1){
if(text2.getText()=="")
text2.setText("特記なし");
if(text3.getText().equals(""))
text3.setText("特記なし");
if(text4.getText().equals(""))
text4.setText("0");
if(text5.getText().equals(""))
text5.setText("特記なし");
if(text6.getText().equals(""))
text6.setText("0");
if(text7.getText().equals(""))
text7.setText("特記なし");
if(text8.getText().equals(""))
text8.setText("特記なし");

list1.list.add(new Fooddata(text1.getText(),text2.getText(),text3.getText(),Integer.parseInt(text4.getText()),
new Days(Integer.parseInt(y.getText()),Integer.parseInt(m.getText()),Integer.parseInt(d.getText())),
Integer.parseInt(text6.getText()),new Days(Integer.parseInt(y1.getText()),Integer.parseInt(m1.getText()),Integer.parseInt(d1.getText())),
text8.getText()));
 Label n1 = new Label();
            Label n2 = new Label();
            Label n3 = new Label();
            Label n4 = new Label();
            Label n5 = new Label();
            Label n6 = new Label();
            Label n7 = new Label();
            Label n8 = new Label();
            
            n1.setMinSize(60,5);
            n2.setMinSize(100,5);
            n3.setMinSize(120,5);
            n4.setMinSize(120,5);
            n5.setMinSize(120,5);
            n6.setMinSize(100,5);
            n7.setMinSize(120,5);
            n8.setMinSize(300,5);
            
            n1.setText(list1.list.get(list1.list.size()-1).id);
            n2.setText(list1.list.get(list1.list.size()-1).name);
            n3.setText(list1.list.get(list1.list.size()-1).place);
            n4.setText(String.valueOf(list1.list.get(list1.list.size()-1).rest));
            n5.setText(String.valueOf(list1.list.get(list1.list.size()-1).trade.year)+"-"
            +String.valueOf(list1.list.get(list1.list.size()-1).trade.month)+"-"
            +String.valueOf(list1.list.get(list1.list.size()-1).trade.day));
            n6.setText(String.valueOf(list1.list.get(list1.list.size()-1).price));
            n7.setText(String.valueOf(list1.list.get(list1.list.size()-1).date.year)+"-"
            +String.valueOf(list1.list.get(list1.list.size()-1).date.month)+"-"
            +String.valueOf(list1.list.get(list1.list.size()-1).date.day));
            n8.setText(list1.list.get(list1.list.size()-1).list);
            list1.write("food.txt");
            nl1.add(n1);nl2.add(n2);
            nl3.add(n3);nl4.add(n4);
            nl5.add(n5);nl6.add(n6);
            nl7.add(n7);nl8.add(n8);
            text1.setText("");text2.setText("");
            text3.setText("");text4.setText("");
            text5.setText("");text6.setText("");
            text7.setText("");text8.setText("");
            y.setText("");m.setText("");d.setText("");
            y1.setText("");m1.setText("");d1.setText("");


            Scene scene = new Scene(new Group());
            Label label  =new Label();
            Label label1 = new Label("ユーザ名 :");
            Button button1 =new Button("発注");
            button1.setOnAction(new Order());
            Button button2 =new Button("ログアウト");
            button2.setOnAction(new Logout());
            Button button3 =new Button("更新");
            button3.setOnAction(new Renew());
            Button button4 =new Button("追加");
            button4.setOnAction(new Add());
            Button button5 =new Button("削除");
            button5.setOnAction(new Delete());
            Button button6 =new Button("検索");
            button6.setOnAction(new Search());
            Label nn1 = new Label("ID");
            Label nn2 = new Label("名前");
            Label nn3 = new Label("保存場所");
            Button nn4 = new Button("在庫数(kg)");
            nn4.setOnAction(new Srest());
            Label nn5 = new Label("購入場所");
            Button nn6 = new Button("値段(kg当たり)");
            nn6.setOnAction(new Sprice());
            Button nn7 = new Button("消費期限");
            nn7.setOnAction(new Sdate());
            Label nn8 = new Label("備考");
            nn1.setMinSize(60,10);
            nn2.setMinSize(100,10);
            nn3.setMinSize(120,10);
            nn4.setMinSize(120,10);
            nn5.setMinSize(120,10);
            nn6.setMinSize(100,10);
            nn7.setMinSize(120,10);
            nn8.setMinSize(300,10);
            
            HBox nh=new HBox();
            VBox v =new VBox(15);
            VBox nv =new VBox(15);
            HBox h  =new HBox(20);
            HBox h1 =new HBox(30);
            nh.getChildren().addAll(nn1,nn2,nn3,nn4,nn5,nn6,nn7,nn8);
            h.getChildren().addAll(button1,button2,label);
            h1.getChildren().addAll(label,button3,button4,button5,button6);
            v.getChildren().addAll(h,h1,nh);
            nv.getChildren().add(v);
            HBox th=nh;
            for(int i=0;i<list1.list.size();i++){
                nh=new HBox();
                nh.getChildren().addAll(nl1.get(i),nl2.get(i),nl3.get(i),nl4.get(i),
                                nl5.get(i),nl6.get(i),nl7.get(i),nl8.get(i));
//                v=new VBox();
                nv.getChildren().add(nh);
//                nv=v;
            }

            h.setAlignment(Pos.BASELINE_RIGHT);
            h1.setAlignment(Pos.CENTER);
            ((Group) scene.getRoot()).getChildren().addAll(nv);
            // Scene scene = new Scene(v, 300,400 );
            save.setScene(scene);
            save1.close();
}
}

}
}
//注文実行
class Orderex implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
        Stage stage =new Stage();
        Button b =(Button)event.getSource(); 
        order="";
        order=b.getText();
        stage.setTitle("注文:"+b.getText());
        Label l  =new Label("");
        Label label  =new Label("注文内容の入力をお願いします ");
        Label label1 = new Label("商品名 ");
        Label label2 = new Label("数量 ");
        Label label3 = new Label("期日 ");
        Label yl=new Label("年");
        Label ml=new Label("月");
        Label dl=new Label("日");
        y =new TextField();
        m =new TextField();
        d =new TextField();
        text3 =new TextField();
        text1 =new TextField();
        text2 =new TextField();
        y.setMaxWidth(50); m.setMaxWidth(30); d.setMaxWidth(30);
        Button button=new Button("実行");
        button.setOnAction(new Orderex1());
        VBox v =new VBox(15);
        HBox x = new HBox();
        HBox h  =new HBox();
        HBox h1 =new HBox(30);
        HBox h2 =new HBox(45);
        HBox h3 =new HBox(5);
        h.getChildren().add(label);
        x.getChildren().add(l);
        h1.getChildren().addAll(label1,text1);
        h2.getChildren().addAll(label2,text2);
        h3.getChildren().addAll(label3,y,yl,m,ml,d,dl);
        v.getChildren().addAll(h,h1,h2,h3,button);
        v.setAlignment(Pos.CENTER);
        h.setAlignment(Pos.CENTER);
        h1.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER); 
        h3.setAlignment(Pos.CENTER); 
        button.setAlignment(Pos.BASELINE_RIGHT);
        Scene scene = new Scene(v, 250, 200);
        stage.setScene(scene);
        stage.show();
        save1=stage;
}
}

//発注実行
class Orderex1 implements EventHandler<ActionEvent>{
@Override
public void handle (ActionEvent event){
    int c=0;
if(text1.getText().equals("")||text2.getText().equals("")||y.getText().equals("")
||m.getText().equals("")||d.getText().equals(""))
{
Stage s =new Stage();
Label la=new Label("空白の欄があります");
Label la1=new Label("不足項目の入力をお願いします");
VBox v=new VBox();
v.getChildren().addAll(la,la1);
v.setAlignment(Pos.CENTER);
Scene scene = new Scene(v, 300, 80);
s.setScene(scene);
s.show();
c=1;
}
if(c!=1){
Stage s =new Stage();
Date day= new Date();
SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
String q1 = d1.format(day); 
Label la=new Label("注文確定しました");
Label la1=new Label("");
VBox v=new VBox(20);
PrintWriter pw = null;
String name=q1;

try{
pw =  new PrintWriter(new BufferedWriter(new FileWriter(order+q1+".txt")));
pw.println("注文内容をここに記載いたしますので何卒お願いいたします");
pw.print("注文する食材 : ");
pw.println(text1.getText());
pw.print("注文する量(kg) : ");
pw.println(text2.getText());
pw.print("注文の期日 : ");
pw.println(y.getText()+"年"+m.getText()+"月"+d.getText()+"日まで");
pw.close();
    }catch(IOException e){
      System.out.println(e);
    }
v.getChildren().addAll(la1,la);
v.setAlignment(Pos.CENTER);
Scene scene = new Scene(v, 300, 80);
s.setScene(scene);
s.show();

save1.close();
}
}}
}