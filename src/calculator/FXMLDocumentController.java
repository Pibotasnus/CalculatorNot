package calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author root
 */
public class FXMLDocumentController implements Initializable {
   
    @FXML private Button bcom;
    @FXML private Button bdiv;
    @FXML private Button bminus;
    @FXML private Button bop;
    @FXML private Button bplus;
    @FXML private Button bdel;
    @FXML private Button bequ;
    @FXML private Button bmult;
    @FXML private Button bclo;
    @FXML private Button b0;
    @FXML private Button b1;
    @FXML private Button b2;
    @FXML private Button b3;
    @FXML private Button b4;
    @FXML private Button b5;
    @FXML private Button b6;
    @FXML private Button b7;
    @FXML private Button b8;
    @FXML private Button b9;
    @FXML private Button breset;
    @FXML private TextField disp;
    private String st;
    private boolean flag;
    
    public static void display(String title, String message){
        Stage window = new Stage();
	VBox layout = new VBox(10);
	Button bu = new Button("Hey");
	layout.getChildren().add(bu);
	Scene scene = new Scene(layout);
        window.setScene(scene);
	window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        st = "";
        b1.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '1';
            disp.setText(st);
            flag = false;
        });
        b2.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '2';
            disp.setText(st);
            flag = false;
        });
        b3.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '3';
            disp.setText(st);
            flag = false;
        });
        b4.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '4';
            disp.setText(st);
            flag = false;
        });
        b5.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '5';
            disp.setText(st);
            flag = false;
        });
        b6.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '6';
            disp.setText(st);
            flag = false;
        });
        b7.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '7';
            disp.setText(st);
            flag = false;
        });
        b8.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '8';
            disp.setText(st);
            flag = false;
        });
        b9.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '9';
            disp.setText(st);
            flag = false;
        });
        b0.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '0';
            disp.setText(st);
            flag = false;
        });
        bplus.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '+';
            disp.setText(st);
            flag = false;
        });
        bminus.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '-';
            disp.setText(st);
            flag = false;
        });
        bdiv.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '/';
            disp.setText(st);
            flag = false;
        });
        bmult.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += 'x';
            disp.setText(st);
            flag = false;
        });
        breset.setOnAction(e -> {
            st = "";
            disp.setText(st);
            flag = false;
        });
        bequ.setOnAction(e -> {
            String op = disp.getText();
            st = "";
            disp.setText(calculate(op));
            flag = true;
        });
        bcom.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '.';
            disp.setText(st);
            flag = false;
        });
        bop.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += '(';
            disp.setText(st);
            flag = false;
        });
        bclo.setOnAction(e -> {
            st = flag?"":disp.getText();
            st += ')';
            disp.setText(st);
            flag = false;
        });
        bdel.setOnAction(e -> {
            st = flag?"":disp.getText();
            st = st.substring(0, st.length()-1);
            disp.setText(st);
            flag = false;
        });
    }    

    private String calculate(String op) {
        String pat1 = "([0-9]+[.]?([0-9]+)?)([/*x])([0-9]+[.]?([0-9]+)?)";
        String pat2 = "([0-9]+[.]?([0-9]+)?)([+-])([0-9]+[.]?([0-9]+)?)";
        String pat3 = "[0-9]+[.]?([0-9]?)?";
        String pat4 = "([(](.*)[)])";
        Pattern r1 = Pattern.compile(pat1);
        Pattern r2 = Pattern.compile(pat2);
        Pattern r3 = Pattern.compile(pat3);
        Pattern r4 = Pattern.compile(pat4);
        Matcher t = r4.matcher(op);
        double res = 0;
        String result = "";
        if(t.find()){
            System.out.println(t.group(2));
            op = op.replaceFirst(pat4,calculate(t.group(2)));
            op = op.replace(")","");
            op = op.replace("(","");
            System.out.println(op);
            return calculate(op);
        }
        else{
            Matcher m = r1.matcher(op);
            if(m.find()){
                System.out.println(m.group(1));
                System.out.println(m.group(4));
                System.out.println(m.group(3));
                double x = Double.parseDouble(m.group(1));
                double y = Double.parseDouble(m.group(4));
                char operator = m.group(3).charAt(0);
                switch(operator){
                    case '*':
                        res = x*y;
                        break;
                    case 'x':
                        res = x*y;
                        break;
                    case '/':
                        if(y==0){
                            result = "Math Error";
                            return result;
                        }
                        res = x/y;
                        break;
                }
                result = String.format("%f",res);
                op = op.replaceFirst(pat1, result);
                return this.calculate(op);
            }
            else{
                Matcher n = r2.matcher(op);
                if(n.find()){
                    System.out.println(n.group(1));
                    System.out.println(n.group(4));
                    System.out.println(n.group(3));
                    double x = Double.parseDouble(n.group(1));
                    double y = Double.parseDouble(n.group(4));
                    char operator = n.group(3).charAt(0);
                    switch(operator){
                        case '+':
                            res = x+y;
                            break;
                        case '-':
                            res = x-y;
                            break;
                    }
                    result = String.format("%.2f",res);
                    op = op.replaceFirst(pat2, result);
                    return this.calculate(op);
                }
                else{
                    Matcher p = r3.matcher(op);
                    if(p.find()){
                        return op;
                    }
                }
            }
        }
        if(flag){
            result = String.format("%f",res);
        }
        if(result.isEmpty()){
            result = "Syntaxe Error";
        }
        return result;
    } 
}
