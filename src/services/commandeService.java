package services;


import entities.Commande;
import utils.MyDB;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mintoua
 */
public class commandeService implements ICommande<Commande> {
     Connection cnx;
     Statement ste;

    public commandeService(){
        cnx = MyDB.getInstance().getConnexion();
    }

     @Override
    public int getLastCommande() {
        String req = "select * from `commande` order by id_com desc limit 1";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                return rs.getInt("id_com");
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return -1;
    }


    
    
     @Override
    public void create(Commande commande){
        try {
            String request
                    = "INSERT INTO `commande` (etatcommande,datecommande,prixtotal) VALUES(?,?,?)";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setInt(1,commande.getEtatcommande());
            st.setString(2,commande.getDatecommande());
            st.setFloat(3, commande.getPrixtotal());
            st.executeUpdate(); // uniquement avec l'ajout, la suppression et la modification dans le BD
            System.out.println("Votre Commande a été enregistrée");
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

   
     @Override
    public boolean delete(int idCommande) throws SQLException {
        String req = "delete from `commande` where id_com=?";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idCommande);
            String req2 = "select id from ligne_commande where id_commande = ?";
            try {
                PreparedStatement pst2  = cnx.prepareStatement(req2);
                pst2.setInt(1,idCommande);
                ResultSet rs = pst2.executeQuery();
                while (rs.next()){
                    LigneCommandeService s1= new LigneCommandeService ();
                    s1.delete(rs.getInt(1));
                }
            }catch (SQLException err){
                System.out.println(err.getMessage());
            }

            pst.executeUpdate();
            System.out.println("Commande annulé");
            return true;

        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return false;
    }

   
     @Override
    public boolean update(int idCommande) throws SQLException {
        String req = "update `commande` set etatcommande=1 where id_com=? ";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idCommande);
            pst.executeUpdate();
            System.out.println("Commande mis à jour avec success");
            return true;
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return false;
    }

 
     @Override
    public ObservableList<Commande> getAll() throws SQLException {
        ObservableList <Commande> list = FXCollections.observableArrayList();
        // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from `commande` ";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Commande(rs.getInt("id_com"), rs.getInt("etatcommande"),rs.getString("datecommande"), rs.getFloat("prixtotal")));
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return list;
    }

    public List<Commande> readAll() throws SQLException {
        List <Commande> list = new ArrayList();
       // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from `commande` ";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Commande(rs.getInt("id_com"), rs.getInt("etatcommande"),rs.getString("datecommande"), rs.getFloat("prixtotal")));
                return list;
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return list;
    }
    
    
     public ArrayList<Commande> TrierParId() {

        ArrayList<Commande> List = new ArrayList<>();
        try {

            String req = "select * from commande ORDER BY prixtotal";
          PreparedStatement pre = cnx.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);

            while (rs.next()) {
                Commande m = new Commande();

               
                m.setId_com(rs.getInt("id_com"));
                m.setPrixtotal(rs.getFloat("prixtotal"));
                m.setDatecommande(rs.getString("datecommande"));
                m.setEtatcommande(rs.getInt("etatcommande"));
              
               
          

                List.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List;
    }
    
     
     public List<Commande> RechercherCommande(String x) {
        ArrayList<Commande> List = new ArrayList<>();
        try {
            String req = "Select * from commande where  id_com like '%" + x + "%' or prixtotal like '%" + x + "%'  or datecommande like '%" + x + "%' ";
            System.out.println("aa: "+x);
       PreparedStatement pre = cnx.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);
                while (rs.next()) {
                 Commande m = new Commande();

               
                m.setId_com(rs.getInt("id_com"));
                m.setPrixtotal(rs.getFloat("prixtotal"));
                m.setDatecommande(rs.getString("datecommande"));
                m.setEtatcommande(rs.getInt("etatcommande"));
                    List.add(m);
                }

            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (List.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        return List;
    }
    

    
    public Commande getCommande(int idCommande) throws SQLException {
        String req = "select * from `commande` where id_com=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idCommande);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return new Commande(rs.getInt("id_com"), rs.getInt("etatcommande"),rs.getString("datecommande"), rs.getFloat("prixtotal"));
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return new Commande();
    }
     @Override
    public int orders(){
        String req = "select count(*) from commande";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while ( rs.next()){
                return  rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public ObservableList<PieChart.Data> getdata(){
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        String request = "select count(etatcommande),datecommande from `commande` where etatcommande = 0";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                String date = rs.getString(2);
                Double count = rs.getDouble(1);
                PieChart.Data data = new PieChart.Data(date,count);
                list.add(data);
            }
        } catch (SQLException e) {
          
        }
        return list;
    }

    
    public int[] statistiques() throws SQLException {
        int nbreVentes[]={0,0,0,0,0,0,0,0,0,0,0,0};
        String req = "select datecommande from  `commande` ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).contains("-01-")) {
                    nbreVentes[0]++;
                }
                else if (rs.getString(1).contains("-02-")) {
                    nbreVentes[1]++;
                }
                else if (rs.getString(1).contains("-03-")) {
                    nbreVentes[2]++;
                }
                else if (rs.getString(1).contains("-04-")) {
                    nbreVentes[3]++;
                }
                else if (rs.getString(1).contains("-05-")) {
                    nbreVentes[4]++;
                }
                else if (rs.getString(1).contains("-06-")) {
                    nbreVentes[5]++;
                }
                else if (rs.getString(1).contains("-07-")) {
                    nbreVentes[6]++;
                }
                else if (rs.getString(1).contains("-08-")) {
                    nbreVentes[7]++;
                }
                else if (rs.getString(1).contains("-09-")) {
                    nbreVentes[8]++;
                }
                else if (rs.getString(1).contains("-10-")) {
                    nbreVentes[9]++;
                }
                else if (rs.getString(1).contains("-11-")) {
                    nbreVentes[10]++;
                }
                else if (rs.getString(1).contains("-12-")) {
                    nbreVentes[11]++;
                }

            }
            return nbreVentes;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return nbreVentes;
    }


   


}
