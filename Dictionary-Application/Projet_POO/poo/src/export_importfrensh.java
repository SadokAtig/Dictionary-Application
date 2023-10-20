import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class export_importfrensh   {



    PreparedStatement stmtt= null ;
    Connection con = null ;
    Statement stmt = null ;
    ResultSet rs = null ;



    

    @FXML
    private TableColumn<obj,String> exemplef;

   

    @FXML
    private TableColumn<obj,String> idwordf;

    
   

    @FXML
    private TableView<obj> jTableExport;

   
    @FXML
    private TableColumn<obj,String> traductionf;

  

    @FXML
    private TableColumn<obj,String> typef;

   
    @FXML
    private TableColumn<obj,String> wordf;

    
   









/* 

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file save");
    int userSelection = fileChooser.showSaveDialog(this);
    if(userSelection == JFileChooser.APPROVE_OPTION){
        File fileToSave = fileChooser.getSelectedFile();
        //lets write to file
     
        try {
              FileWriter fw = new FileWriter(fileToSave);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i <((TableModel) jTableExport).getRowCount(); i++) {
                for (int j = 0; j  < ((TableModel) jTableExport).getColumnCount(); j++) {
                    //write
                    bw.write(((TableModel) jTableExport).getValueAt(i, j).toString()+",");
                }
                bw.newLine();//record per line 
            }
           // JOptionPane.showMessageDialog(this, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
            bw.close();
            fw.close();
        } catch (IOException ex) {
           //JOptionPane.showMessageDialog(this, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
*/



/*String file="C:\\Users\\atigs\\Desktop\\fx_export.txt";
        try {
            FileWriter fw = new FileWriter(file);
            stmtt = con.prepareStatement("select * from frensh");
            rs = stmtt.executeQuery();
            while(rs.next()){
                fw.append(rs.getString(1));
                fw.append(',');
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append(',');
                fw.append(rs.getString(5));
                fw.append(',');
                fw.append(rs.getString(6));
                fw.append(',');

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


/*    /* String filePath = "C:\\Users\\atigs\\Desktop\\fx_export.txt";
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int i = 0; i <  ((TableModel) jTableExport).getRowCount(); i++){//rows
                for(int j = 0; j < ((TableModel) jTableExport).getColumnCount(); j++){//columns
                    bw.write(((TableModel) jTableExport).getValueAt(i, j).toString()+" ");
                }
                bw.newLine();
            }
            
            bw.close();
            fw.close();
            
        } catch (IOException ex) {
           // Logger.getLogger(JTable_import_and_export_to_text_file.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        public ObservableList<obj> dic() throws ClassNotFoundException{
            ObservableList<obj> diction = FXCollections.observableArrayList();
            String query = "select *  from frensh, english where word=traductionf and wordf=traduction";
           con = connecte.connect();
           try {
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);
           
               while(rs.next()){
                   obj ob = new obj();
                   ob.setMot_E(rs.getString("word"));
                ob.setMot_F(rs.getString("wordf")); 
                ob.setType_E(rs.getString("type"));
                ob.setExpl_E(rs.getString("exemplef"));
                ob.setExpl_F(rs.getString("exemplef"));
                   diction.add(ob);
               }
           
           
           } catch (Exception e) {
            throw new RuntimeException(e);
           }
           return diction;
        }
        public void showdic() throws ClassNotFoundException{
            ObservableList<obj> list = dic();
            jTableExport.setItems(list);
         
           idwordf.setCellValueFactory(new PropertyValueFactory<obj,String>("Mot_E"));  
           wordf.setCellValueFactory(new PropertyValueFactory<obj,String>("Mot_F"));
            traductionf.setCellValueFactory(new PropertyValueFactory<obj,String>("Type_E"));
            typef.setCellValueFactory(new PropertyValueFactory<obj,String>("Expl_E") );
            exemplef.setCellValueFactory(new PropertyValueFactory<obj,String>("Expl_F"));
            
            
        }

        @FXML
    private TextField text;
        @FXML
        private Button export_data;
        @FXML
        void export(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
            String ch="";
            Object ob = null;
        try{
            File file =new File("fximpor.txt");
            ObjectInputStream ObjectInputStream = new ObjectInputStream(new FileInputStream(file));
            while ((ob=ObjectInputStream.readObject())!=null){
            if (ob instanceof obj){
                ch=ch+((obj) ob).Affichage();
            }
            text.setText(ch);
            }
            ObjectInputStream.close();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
     
        }
       
     
        
    @FXML
    private Button import_data;
 
    @FXML
    void impor(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
      
        try{
            File file =new File("fximpor.txt");
            showdic();
            ObservableList<obj> list = dic();
            ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (int i =0; i<list.size();i++){
            ObjectOutputStream.writeObject(list.get(i));
            System.out.println(list.get(i));
            }
            ObjectOutputStream.close();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }



    @FXML
    private Button close;
    @FXML
    void onclose(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button lis;
    
    @FXML
    void onlist(ActionEvent event) throws IOException {
        Stage stage = (Stage) lis.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("listage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    }

       
 
    
/* 
    @FXML
    void onimpor(ActionEvent event) {
        String filePath = "C:\\Users\\atigs\\Desktop\\fx_export.txt";
        File file = new File(filePath);
        
        
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            DefaultTableModel model = (DefaultTableModel)((Object) jTableImport).getModel();
            Object[] lines = br.lines().toArray();
            
            for(int i = 0; i < lines.length; i++){
                String[] row = lines[i].toString().split(" ");
                model.addRow(row);
            }
            
        
    }
*/
/* 
public class TranslationExporter {
    public static void exportTranslations(File file, TableView<dictionnaire_frensh> tableView) throws FileNotFoundException {
        ObservableList<dictionnaire_frensh> translations = tableView.getItems();
        PrintWriter writer = new PrintWriter(file);
        for (dictionnaire_frensh translation : translations) {
            writer.println(( translation).getSourceText() + "," + translation.getTargetText() + "," + translation.getTargetLanguage());
        }
        writer.close();
    }
}*/
   
  

       
    }