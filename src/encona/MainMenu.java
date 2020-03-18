/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encona;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import koneksi.koneksi;

/**
 *
 * @author ACER
 */
public class MainMenu extends javax.swing.JFrame {
    public Connection conn = new koneksi().connect();
    DefaultTableModel tabClient,tabVendor,tabBarang,tabProject,tabPO,tabPembayaran,tabUser;
    String tgl_join, id, start_project, end_project, tgl_po, tgl_bayar;
    HashMap param = new HashMap();
    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        Toolkit toolkit = getToolkit(); // Membuat form login muncul ditengah
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2, size.height/2-getHeight()/2); // Rumus form login muncul tengah
        fillComboClient();
        fillComboCategory();
        fillComboVendor();
        fillComboProject();
        fillComboNoPO();
        getDataComboClient();
        getDataComboBarang();
        getDataComboVendor();
        getDataComboProject();
        getDataComboCategory();
        getTarif();
     
        String []client={"ID Client","Nama Client","Alamat","Telepon","PIC","Tanggal Join","NPWP"};
        tabClient = new DefaultTableModel(client,0);
        tblClient.setModel(tabClient);
        tblClient.setAutoResizeMode(tblClient.AUTO_RESIZE_OFF); // Mulai atur width column JTable
        TableColumn col = tblClient.getColumnModel().getColumn(0); // Kolom 0
        col.setPreferredWidth(100);
        col = tblClient.getColumnModel().getColumn(1); // Kolom 1
        col.setPreferredWidth(200);
        col = tblClient.getColumnModel().getColumn(2); // Kolom 2
        col.setPreferredWidth(200);
        col = tblClient.getColumnModel().getColumn(3); // Kolom 3
        col.setPreferredWidth(100);
        col = tblClient.getColumnModel().getColumn(4); // Kolom 4
        col.setPreferredWidth(200);
        col = tblClient.getColumnModel().getColumn(5); // Kolom 5
        col.setPreferredWidth(100);
        col = tblClient.getColumnModel().getColumn(6); // Kolom 6
        col.setPreferredWidth(200);
        tampilkanClient();
        
        String []vendor={"ID Vendor","Nama Vendor","PIC","Contact","Email","Category","Alamat","Status"};
        tabVendor = new DefaultTableModel(vendor,0);
        tblVendor.setModel(tabVendor);
        tblVendor.setAutoResizeMode(tblVendor.AUTO_RESIZE_OFF); // Mulai atur width column JTable
        TableColumn col2 = tblVendor.getColumnModel().getColumn(0); // Kolom 0
        col2.setPreferredWidth(100);
        col2 = tblVendor.getColumnModel().getColumn(1); // Kolom 1
        col2.setPreferredWidth(200);
        col2 = tblVendor.getColumnModel().getColumn(2); // Kolom 2
        col2.setPreferredWidth(200);
        col2 = tblVendor.getColumnModel().getColumn(3); // Kolom 3
        col2.setPreferredWidth(100);
        col2 = tblVendor.getColumnModel().getColumn(4); // Kolom 4
        col2.setPreferredWidth(200);
        col2 = tblVendor.getColumnModel().getColumn(5); // Kolom 5
        col2.setPreferredWidth(100);
        col2 = tblVendor.getColumnModel().getColumn(6); // Kolom 6
        col2.setPreferredWidth(200);
        col2 = tblVendor.getColumnModel().getColumn(7); // Kolom 7
        col2.setPreferredWidth(100);
        tampilkanVendor();
        
        String []barang={"Kode Barang","Nama Barang","Category","Tarif"};
        tabBarang = new DefaultTableModel(barang,0);
        tblBarang.setModel(tabBarang);
        tblBarang.setAutoResizeMode(tblBarang.AUTO_RESIZE_OFF);
        TableColumn col3 = tblBarang.getColumnModel().getColumn(0); // Kolom 0
        col3.setPreferredWidth(100);
        col3 = tblBarang.getColumnModel().getColumn(1); // Kolom 1
        col3.setPreferredWidth(300);
        col3 = tblBarang.getColumnModel().getColumn(2); // Kolom 2
        col3.setPreferredWidth(100);
        col3 = tblBarang.getColumnModel().getColumn(3); // Kolom 3
        col3.setPreferredWidth(100);
        tampilkanBarang();
        
        String []project={"ID Project","Nama Project","Nama Client","ID Client","Tanggal Mulai","Tanggal Selesai","Nilai Project","Jumlah Vendor","Days Remaining"};
        tabProject = new DefaultTableModel(project,0);
        tblProject.setModel(tabProject);
        tblProject.setAutoResizeMode(tblProject.AUTO_RESIZE_OFF);
        TableColumn col4 = tblProject.getColumnModel().getColumn(0); 
        col4.setPreferredWidth(100);
        col4 = tblProject.getColumnModel().getColumn(1); 
        col4.setPreferredWidth(200);
        col4 = tblProject.getColumnModel().getColumn(2); 
        col4.setPreferredWidth(200);
        col4 = tblProject.getColumnModel().getColumn(3); 
        col4.setPreferredWidth(100);
        col4 = tblProject.getColumnModel().getColumn(4); 
        col4.setPreferredWidth(100);
        col4 = tblProject.getColumnModel().getColumn(5); 
        col4.setPreferredWidth(100);
        col4 = tblProject.getColumnModel().getColumn(6); 
        col4.setPreferredWidth(200);
        col4 = tblProject.getColumnModel().getColumn(7); 
        col4.setPreferredWidth(100);
        col4 = tblProject.getColumnModel().getColumn(8); 
        col4.setPreferredWidth(100);
        tampilkanProject();
        
        String []po={"Row ID","Nama Project","ID Project","Nomor PO","Nama Vendor","ID Vendor","PIC Vendor","Category","Nama Barang","Jumlah","Tarif","Tanggal PO"};
        tabPO = new DefaultTableModel(po,0);
        tblPO.setModel(tabPO);
        tblPO.setAutoResizeMode(tblPO.AUTO_RESIZE_OFF);
        TableColumn col5 = tblPO.getColumnModel().getColumn(0); 
        col5.setPreferredWidth(50);
        col5 = tblPO.getColumnModel().getColumn(1); 
        col5.setPreferredWidth(300);
        col5 = tblPO.getColumnModel().getColumn(2); 
        col5.setPreferredWidth(100);
        col5 = tblPO.getColumnModel().getColumn(3); 
        col5.setPreferredWidth(200);
        col5 = tblPO.getColumnModel().getColumn(4); 
        col5.setPreferredWidth(200);
        col5 = tblPO.getColumnModel().getColumn(5); 
        col5.setPreferredWidth(100);
        col5 = tblPO.getColumnModel().getColumn(6); 
        col5.setPreferredWidth(100);
        col5 = tblPO.getColumnModel().getColumn(7); 
        col5.setPreferredWidth(200);
        col5 = tblPO.getColumnModel().getColumn(8); 
        col5.setPreferredWidth(300);
        col5 = tblPO.getColumnModel().getColumn(9); 
        col5.setPreferredWidth(100);
        col5 = tblPO.getColumnModel().getColumn(10); 
        col5.setPreferredWidth(100);
        col5 = tblPO.getColumnModel().getColumn(11); 
        col5.setPreferredWidth(100);
        tampilkanPO();
        
        String []bayar={"Row ID","No PO","Status Bayar","Tanggal Bayar"};
        tabPembayaran = new DefaultTableModel(bayar,0);
        tblPembayaran.setModel(tabPembayaran);
        tblPembayaran.setAutoResizeMode(tblPembayaran.AUTO_RESIZE_OFF);
        TableColumn col6 = tblPembayaran.getColumnModel().getColumn(0); 
        col6.setPreferredWidth(50);
        col6 = tblPembayaran.getColumnModel().getColumn(1); 
        col6.setPreferredWidth(200);
        col6 = tblPembayaran.getColumnModel().getColumn(2); 
        col6.setPreferredWidth(200);
        col6 = tblPembayaran.getColumnModel().getColumn(3); 
        col6.setPreferredWidth(100);
        tampilkanPembayaran();
        
        String []user={"Rowid","Username","Password","Role"};
        tabUser = new DefaultTableModel(user,0);
        tblUser.setModel(tabUser);
        tampilkanUser();
        
    }
    
    private void tampilkanUser() {
        int row = tblUser.getRowCount();
        for(int s=0; s<row;s++){
         tabUser.removeRow(0);
        }
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from t_admin");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                tabUser.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !"+e.getMessage());
        }
    }
    
    private void tampilkanClient() {
        int row = tblClient.getRowCount();
        for(int s=0; s<row;s++){
         tabClient.removeRow(0);
        }
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from client order by id_client");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(rs.getDate(6));
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),strDate,rs.getString(7)};
                tabClient.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !"+e.getMessage());
        }
    }
    
    private void tampilkanBarang() {
        int row = tblBarang.getRowCount();
        for(int s=0; s<row;s++){
         tabBarang.removeRow(0);
        }
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from barang order by kd_barang");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                tabBarang.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !"+e.getMessage());
        }
    }
    
    public void tampilkanVendor() {
        int row = tblVendor.getRowCount();
        for(int s=0; s<row;s++){
         tabVendor.removeRow(0);
        }
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from vendor order by id_vendor");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
                tabVendor.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !"+e.getMessage());
        }
    }
    
    private void tampilkanProject() {
        int row = tblProject.getRowCount();
        for(int s=0; s<row;s++){
         tabProject.removeRow(0);
        }
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select a.*, datediff(tgl_selesai,curdate()) as days_remaining from project a order by id_project");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
                tabProject.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !"+e.getMessage());
        }
    }
    
    public void tampilkanPO() {
        int row = tblPO.getRowCount();
        for(int s=0; s<row;s++){
         tabPO.removeRow(0);
        }
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from po order by no_po");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)};
                tabPO.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !"+e.getMessage());
        }
    }
    
    private void tampilkanPembayaran() {
        int row = tblPembayaran.getRowCount();
        for(int s=0; s<row;s++){
         tabPembayaran.removeRow(0);
        }
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from pembayaran");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                tabPembayaran.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !"+e.getMessage());
        }
    }
    
    protected void kosongClient(){
        txtIdClient.setText("");
        txtNamaClient.setText("");
        txtAlamatClient.setText("");
        txtNoTelpClient.setText("");
        dtJoinClient.setCalendar(null);
        txtNamaPicClient.setText("");
        txtNpwp.setText("");
    }
    
    protected void kosongBarang(){
        txtKodeBarang.setText("");
        txtNamaBarang.setText("");
        cbCategoryBarang.setSelectedItem(null);
        txtTarifBarang.setText("");
    }
    
    protected void kosongUser(){
        txtUsername.setText("");
        txtPassword.setText("");
        cbRole.setSelectedItem(null);
        lblRow.setText("");
    }
    
    protected void kosongProject(){
        txtIdProject2.setText("");
        txtNamaProject2.setText("");
        txtIdClient2.setText("");
        cbNamaClient2.setSelectedItem(null);
        dcStartProject2.setCalendar(null);
        dcEndProject2.setCalendar(null);
        txtNilaiProject2.setText("");
        txtJumlahVendor2.setText("");
        lbHari.setText("");
    }
    
    protected void kosongPO(){
        cbNamaProject.setSelectedItem(null);
        txtIdProject.setText("");
        txtNoPO.setText("");
        cbNamaVendor.setSelectedItem(null);
        txtIdVendorPO.setText("");
        txtPicVendor.setText("");
        cbCategory.setSelectedItem(null);
        cbNamaBarang.setSelectedItem(null);
        txtJumlahBarang.setText("");
        txtTarif.setText("");
        dcTglPO.setCalendar(null);
    }
    
    protected void kosongPembayaran(){
        lblRowid.setText(null);
        cbNoPO.setSelectedItem("");
        cbStatusBayar.setSelectedItem("");
        dcTanggalBayar.setDate(null);
    }
    
    public void fillComboClient(){
        cbNamaClient2.removeAllItems();
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select distinct nama_client from client order by id_client");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nama_client = rs.getString("nama_client");
                cbNamaClient2.addItem(nama_client); // Tambah value combobox
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void fillComboCategory(){
        cbCategory.removeAllItems();
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select distinct category from barang");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String category = rs.getString("category");
                cbCategory.addItem(category); // Tambah value combobox
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void fillComboVendor(){
        //cbNamaVendor.removeAllItems();
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select distinct nama_vendor from vendor");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nama_vendor = rs.getString("nama_vendor");
                cbNamaVendor.addItem(nama_vendor); // Tambah value combobox
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void fillComboProject(){
        cbNamaProject.removeAllItems();
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select distinct nama_project from project");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nama_project = rs.getString("nama_project");
                cbNamaProject.addItem(nama_project); // Tambah value combobox
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void fillComboNoPO(){
        cbNoPO.removeAllItems();
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select distinct no_po from po");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String no_po = rs.getString("no_po");
                cbNoPO.addItem(no_po); // Tambah value combobox
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void getDataComboClient(){
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from client where nama_client ='"+cbNamaClient2.getSelectedItem()+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
            txtIdClient2.setText(rs.getString(1));
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void getDataComboVendor(){
        cbCategory.removeAllItems(); // Untuk clear selected item
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from vendor where nama_vendor ='"+cbNamaVendor.getSelectedItem()+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(3)};
            txtIdVendorPO.setText(rs.getString(1));
            txtPicVendor.setText(rs.getString(3));
            cbCategory.addItem(rs.getString(6));
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void getDataComboBarang(){
        //cbNamaBarang.setSelectedItem(null);
        cbNamaBarang.removeAllItems(); // Untuk clear selected item
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select distinct nama_barang from barang where category ='"+cbCategory.getSelectedItem()+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1)};
            cbNamaBarang.addItem(rs.getString(1));
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void getDataComboProject(){
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select distinct id_project from project where nama_project='"+cbNamaProject.getSelectedItem()+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            txtIdProject.setText(rs.getString(1));
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void getDataComboCategory(){
        //cbNamaBarang.setSelectedItem(null);
        cbCategory.removeAllItems(); // Untuk clear selected item
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select distinct category from vendor where nama_vendor='"+cbNamaVendor.getSelectedItem()+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            cbCategory.addItem(rs.getString(1));
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }
    
    public void getTarif(){
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select tarif from barang where category ='"+cbCategory.getSelectedItem()+"' and nama_barang = '"+cbNamaBarang.getSelectedItem()+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            int jumlah = Integer.parseInt(txtJumlahBarang.getText());
            int tarif_total = Integer.parseInt(rs.getString(1)) * jumlah;
            txtTarif.setText(String.valueOf(tarif_total));
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpUtama = new javax.swing.JPanel();
        jpTitle = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jpMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnClient = new javax.swing.JButton();
        btnVendor = new javax.swing.JButton();
        btnBarang = new javax.swing.JButton();
        btnProject = new javax.swing.JButton();
        btnPembayaran = new javax.swing.JButton();
        btnPO = new javax.swing.JButton();
        btnDataUser = new javax.swing.JButton();
        jpMainMenu = new javax.swing.JPanel();
        jpHome = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jpDataClient = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblClient = new javax.swing.JTable();
        btnCariClient = new javax.swing.JButton();
        txtCariClient = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnAddClient = new javax.swing.JButton();
        btnEditClient = new javax.swing.JButton();
        btnDeleteClient = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtIdClient = new javax.swing.JTextField();
        txtNamaClient = new javax.swing.JTextField();
        txtAlamatClient = new javax.swing.JTextField();
        txtNoTelpClient = new javax.swing.JTextField();
        txtNamaPicClient = new javax.swing.JTextField();
        dtJoinClient = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        txtNpwp = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jpDataBarang = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        btnCariBarang = new javax.swing.JButton();
        txtCariBarang = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnAddBarang = new javax.swing.JButton();
        btnEditBarang = new javax.swing.JButton();
        btnDeleteBarang = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtTarifBarang = new javax.swing.JTextField();
        cbCategoryBarang = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        btnResetBarang = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jpDataVendor = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVendor = new javax.swing.JTable();
        btnCariVendor = new javax.swing.JButton();
        txtCariVendor = new javax.swing.JTextField();
        btnAddVendor = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnDeleteVendor = new javax.swing.JButton();
        btnEditVendor = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jpDataProjects = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblProject = new javax.swing.JTable();
        btnCariProject = new javax.swing.JButton();
        txtCariProject = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnAddProject = new javax.swing.JButton();
        btnEditProject = new javax.swing.JButton();
        btnDeleteProject = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtIdProject2 = new javax.swing.JTextField();
        txtNamaProject2 = new javax.swing.JTextField();
        btnResetProject = new javax.swing.JButton();
        dcStartProject2 = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        dcEndProject2 = new com.toedter.calendar.JDateChooser();
        jLabel49 = new javax.swing.JLabel();
        txtIdClient2 = new javax.swing.JTextField();
        txtNilaiProject2 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtJumlahVendor2 = new javax.swing.JTextField();
        lbHari = new javax.swing.JLabel();
        cbNamaClient2 = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jpPembayaran = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPembayaran = new javax.swing.JTable();
        btnCariPembayaran = new javax.swing.JButton();
        txtCariPembayaran = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnAddPembayaran = new javax.swing.JButton();
        btnEditPembayaran = new javax.swing.JButton();
        btnDeletePembayaran = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        btnResetPembayaran = new javax.swing.JButton();
        cbNoPO = new javax.swing.JComboBox<>();
        cbStatusBayar = new javax.swing.JComboBox<>();
        dcTanggalBayar = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        lblRowid = new javax.swing.JLabel();
        jpPenerbitanPO = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPO = new javax.swing.JTable();
        btnCariPO = new javax.swing.JButton();
        txtCariPO = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnAddPO = new javax.swing.JButton();
        btnEditPO = new javax.swing.JButton();
        btnDeletePO = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtNoPO = new javax.swing.JTextField();
        cbCategory = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbNamaBarang = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        cbNamaVendor = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        txtIdVendorPO = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtJumlahBarang = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtTarif = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtPicVendor = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        cbNamaProject = new javax.swing.JComboBox<>();
        txtIdProject = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        dcTglPO = new com.toedter.calendar.JDateChooser();
        jLabel58 = new javax.swing.JLabel();
        btnResetPO = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        rowid = new javax.swing.JLabel();
        jpDataUser = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        btnCariUser = new javax.swing.JButton();
        txtCariUser = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnAddUser = new javax.swing.JButton();
        btnEditUser = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        cbRole = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JPasswordField();
        jPanel13 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        lblRow = new javax.swing.JLabel();
        jpHeader = new javax.swing.JPanel();
        jmbUtama = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpUtama.setBackground(new java.awt.Color(255, 255, 255));

        jpTitle.setBackground(new java.awt.Color(244, 238, 255));
        jpTitle.setPreferredSize(new java.awt.Dimension(493, 70));

        jLabel2.setFont(new java.awt.Font("Adobe Fangsong Std R", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(66, 72, 116));
        jLabel2.setText("E N C O N A   I N T I   I N D U S T R I");

        jLabel3.setFont(new java.awt.Font("Adobe Fangsong Std R", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 72, 116));
        jLabel3.setText("18 Office Park Building 12th Floor, Jalan T.B. Simatupang Kav 18, Pasar Minggu, RT.2/RW.1, Kebagusan, Jakarta Selatan");

        jLabel4.setFont(new java.awt.Font("Adobe Fangsong Std R", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(66, 72, 116));
        jLabel4.setText("Daerah Khusus Ibukota Jakarta 12520");

        javax.swing.GroupLayout jpTitleLayout = new javax.swing.GroupLayout(jpTitle);
        jpTitle.setLayout(jpTitleLayout);
        jpTitleLayout.setHorizontalGroup(
            jpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitleLayout.createSequentialGroup()
                .addGroup(jpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTitleLayout.createSequentialGroup()
                        .addGap(636, 636, 636)
                        .addComponent(jLabel4))
                    .addGroup(jpTitleLayout.createSequentialGroup()
                        .addGap(412, 412, 412)
                        .addComponent(jLabel3))
                    .addGroup(jpTitleLayout.createSequentialGroup()
                        .addGap(602, 602, 602)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTitleLayout.setVerticalGroup(
            jpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitleLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpMenu.setBackground(new java.awt.Color(66, 72, 116));
        jpMenu.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("W E L C O M E");

        btnClient.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        btnClient.setForeground(new java.awt.Color(66, 72, 116));
        btnClient.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon1_32px.png")); // NOI18N
        btnClient.setText("Data Client");
        btnClient.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnClient.setIconTextGap(20);
        btnClient.setPreferredSize(new java.awt.Dimension(295, 35));
        btnClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientActionPerformed(evt);
            }
        });

        btnVendor.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        btnVendor.setForeground(new java.awt.Color(66, 72, 116));
        btnVendor.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon2_32px.png")); // NOI18N
        btnVendor.setText("Data Vendor");
        btnVendor.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnVendor.setIconTextGap(20);
        btnVendor.setMaximumSize(new java.awt.Dimension(149, 41));
        btnVendor.setMinimumSize(new java.awt.Dimension(149, 41));
        btnVendor.setPreferredSize(new java.awt.Dimension(295, 35));
        btnVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendorActionPerformed(evt);
            }
        });

        btnBarang.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        btnBarang.setForeground(new java.awt.Color(66, 72, 116));
        btnBarang.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon4_32px.png")); // NOI18N
        btnBarang.setText("Data Barang");
        btnBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnBarang.setIconTextGap(20);
        btnBarang.setMaximumSize(new java.awt.Dimension(149, 41));
        btnBarang.setMinimumSize(new java.awt.Dimension(149, 41));
        btnBarang.setPreferredSize(new java.awt.Dimension(295, 35));
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });

        btnProject.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        btnProject.setForeground(new java.awt.Color(66, 72, 116));
        btnProject.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon3_32px.png")); // NOI18N
        btnProject.setText("Data List Project");
        btnProject.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnProject.setIconTextGap(20);
        btnProject.setMaximumSize(new java.awt.Dimension(149, 41));
        btnProject.setMinimumSize(new java.awt.Dimension(149, 41));
        btnProject.setPreferredSize(new java.awt.Dimension(295, 35));
        btnProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProjectActionPerformed(evt);
            }
        });

        btnPembayaran.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        btnPembayaran.setForeground(new java.awt.Color(66, 72, 116));
        btnPembayaran.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon5_32px.png")); // NOI18N
        btnPembayaran.setText("Pembayaran");
        btnPembayaran.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnPembayaran.setIconTextGap(20);
        btnPembayaran.setMaximumSize(new java.awt.Dimension(149, 41));
        btnPembayaran.setMinimumSize(new java.awt.Dimension(149, 41));
        btnPembayaran.setPreferredSize(new java.awt.Dimension(295, 35));
        btnPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembayaranActionPerformed(evt);
            }
        });

        btnPO.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        btnPO.setForeground(new java.awt.Color(66, 72, 116));
        btnPO.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon6_32px.png")); // NOI18N
        btnPO.setText("Penerbitan PO");
        btnPO.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnPO.setIconTextGap(20);
        btnPO.setMaximumSize(new java.awt.Dimension(149, 41));
        btnPO.setMinimumSize(new java.awt.Dimension(149, 41));
        btnPO.setPreferredSize(new java.awt.Dimension(295, 35));
        btnPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPOActionPerformed(evt);
            }
        });

        btnDataUser.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        btnDataUser.setForeground(new java.awt.Color(66, 72, 116));
        btnDataUser.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon7_32px.png")); // NOI18N
        btnDataUser.setText("Data User");
        btnDataUser.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnDataUser.setIconTextGap(20);
        btnDataUser.setMaximumSize(new java.awt.Dimension(149, 41));
        btnDataUser.setMinimumSize(new java.awt.Dimension(149, 41));
        btnDataUser.setPreferredSize(new java.awt.Dimension(295, 35));
        btnDataUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMenuLayout = new javax.swing.GroupLayout(jpMenu);
        jpMenu.setLayout(jpMenuLayout);
        jpMenuLayout.setHorizontalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClient, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
            .addComponent(btnVendor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPembayaran, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(88, 88, 88))
        );
        jpMenuLayout.setVerticalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMenuLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnClient, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDataUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnProject, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPO, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpMainMenu.setBackground(new java.awt.Color(255, 255, 255));
        jpMainMenu.setLayout(new java.awt.CardLayout());

        jpHome.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\encona_icon_big.png")); // NOI18N

        javax.swing.GroupLayout jpHomeLayout = new javax.swing.GroupLayout(jpHome);
        jpHome.setLayout(jpHomeLayout);
        jpHomeLayout.setHorizontalGroup(
            jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
        );
        jpHomeLayout.setVerticalGroup(
            jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        jpMainMenu.add(jpHome, "card2");

        jpDataClient.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setAutoscrolls(true);
        jScrollPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClient.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblClient);

        btnCariClient.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\find-16px.png")); // NOI18N
        btnCariClient.setToolTipText("Cari Data");
        btnCariClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariClientActionPerformed(evt);
            }
        });

        txtCariClient.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCariClient.setToolTipText("Cari ...");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 255));
        jLabel18.setText("Data Client");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddClient.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\add-16px.png")); // NOI18N
        btnAddClient.setText("Tambah");
        btnAddClient.setToolTipText("Tambah Data");
        btnAddClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddClientActionPerformed(evt);
            }
        });

        btnEditClient.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\edit-16px.png")); // NOI18N
        btnEditClient.setText("Edit");
        btnEditClient.setToolTipText("Edit Data");
        btnEditClient.setPreferredSize(new java.awt.Dimension(91, 25));
        btnEditClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditClientActionPerformed(evt);
            }
        });

        btnDeleteClient.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\delete-16px.png")); // NOI18N
        btnDeleteClient.setText("Hapus");
        btnDeleteClient.setToolTipText("Hapus Data");
        btnDeleteClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClientActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID Client");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Nama Client");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Alamat");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("No. Tlp");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Nama PIC");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Tanggal Join");

        dtJoinClient.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtJoinClientPropertyChange(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("NPWP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddClient, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditClient, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteClient, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel31))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNpwp, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaPicClient, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNoTelpClient, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlamatClient, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaClient, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtJoinClient, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNamaClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtAlamatClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNoTelpClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtNamaPicClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtJoinClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtNpwp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddClient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditClient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteClient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        jPanel3.setBackground(new java.awt.Color(66, 72, 116));

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tambah Data");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel19)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel19)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jpDataClientLayout = new javax.swing.GroupLayout(jpDataClient);
        jpDataClient.setLayout(jpDataClientLayout);
        jpDataClientLayout.setHorizontalGroup(
            jpDataClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataClientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDataClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpDataClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDataClientLayout.createSequentialGroup()
                        .addComponent(btnCariClient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariClient, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(195, 195, 195)
                        .addComponent(jLabel18))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpDataClientLayout.setVerticalGroup(
            jpDataClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataClientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDataClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpDataClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCariClient)
                        .addComponent(txtCariClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDataClientLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDataClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jpMainMenu.add(jpDataClient, "card3");

        jpDataBarang.setBackground(new java.awt.Color(255, 255, 255));

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblBarang);

        btnCariBarang.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\find-16px.png")); // NOI18N
        btnCariBarang.setToolTipText("Cari Data");
        btnCariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariBarangActionPerformed(evt);
            }
        });

        txtCariBarang.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCariBarang.setToolTipText("Cari ...");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setText("Data Barang");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddBarang.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\add-16px.png")); // NOI18N
        btnAddBarang.setText("Tambah");
        btnAddBarang.setToolTipText("Tambah Data");
        btnAddBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBarangActionPerformed(evt);
            }
        });

        btnEditBarang.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\edit-16px.png")); // NOI18N
        btnEditBarang.setText("Edit");
        btnEditBarang.setToolTipText("Edit Data");
        btnEditBarang.setPreferredSize(new java.awt.Dimension(91, 25));
        btnEditBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditBarangActionPerformed(evt);
            }
        });

        btnDeleteBarang.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\delete-16px.png")); // NOI18N
        btnDeleteBarang.setText("Hapus");
        btnDeleteBarang.setToolTipText("Hapus Data");
        btnDeleteBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteBarangActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Kode Barang");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Nama Barang");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Category");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Tarif");

        cbCategoryBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alat Konstruksi","Bahan Bakar","Peralatan Kantor","Material","Peralatan Teknis","Perlengkapan Karyawan","Perlengkapan Lapangan","Komunikasi","Beban lainnya" }));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("/ pcs");

        btnResetBarang.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\refresh-16px.png")); // NOI18N
        btnResetBarang.setToolTipText("Reset Form");
        btnResetBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAddBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(txtKodeBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(cbCategoryBarang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtTarifBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel25)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbCategoryBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtTarifBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(80, 80, 80)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnResetBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(66, 72, 116));

        jLabel27.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Tambah Data");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel27)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel27)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jpDataBarangLayout = new javax.swing.GroupLayout(jpDataBarang);
        jpDataBarang.setLayout(jpDataBarangLayout);
        jpDataBarangLayout.setHorizontalGroup(
            jpDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpDataBarangLayout.createSequentialGroup()
                        .addComponent(btnCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)))
                .addContainerGap())
        );
        jpDataBarangLayout.setVerticalGroup(
            jpDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCariBarang)
                            .addComponent(txtCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDataBarangLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jpMainMenu.add(jpDataBarang, "card3");

        jpDataVendor.setBackground(new java.awt.Color(255, 255, 255));

        tblVendor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVendorMouseClicked(evt);
            }
        });
        tblVendor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblVendorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblVendor);

        btnCariVendor.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\find-16px.png")); // NOI18N
        btnCariVendor.setToolTipText("Cari Data");
        btnCariVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariVendorActionPerformed(evt);
            }
        });

        txtCariVendor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCariVendor.setToolTipText("Cari ...");

        btnAddVendor.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\add-16px.png")); // NOI18N
        btnAddVendor.setToolTipText("Tambah Data");
        btnAddVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddVendorActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informasi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Data Vendor");

        btnDeleteVendor.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\delete-16px.png")); // NOI18N
        btnDeleteVendor.setToolTipText("Hapus Data");
        btnDeleteVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteVendorActionPerformed(evt);
            }
        });

        btnEditVendor.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\edit-16px.png")); // NOI18N
        btnEditVendor.setToolTipText("Edit Data");
        btnEditVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditVendorActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh Data");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDataVendorLayout = new javax.swing.GroupLayout(jpDataVendor);
        jpDataVendor.setLayout(jpDataVendorLayout);
        jpDataVendorLayout.setHorizontalGroup(
            jpDataVendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataVendorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDataVendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDataVendorLayout.createSequentialGroup()
                        .addComponent(btnAddVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(8, 8, 8))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpDataVendorLayout.setVerticalGroup(
            jpDataVendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataVendorLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jpDataVendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddVendor)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpDataVendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnDeleteVendor)
                        .addComponent(btnEditVendor)
                        .addComponent(btnCariVendor))
                    .addGroup(jpDataVendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCariVendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpMainMenu.add(jpDataVendor, "card3");

        jpDataProjects.setBackground(new java.awt.Color(255, 255, 255));

        tblProject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProjectMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblProject);

        btnCariProject.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\find-16px.png")); // NOI18N
        btnCariProject.setToolTipText("Cari Data");
        btnCariProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariProjectActionPerformed(evt);
            }
        });

        txtCariProject.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCariProject.setToolTipText("Cari ...");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 255));
        jLabel44.setText("Data Daftar Project");

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddProject.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\add-16px.png")); // NOI18N
        btnAddProject.setText("Tambah");
        btnAddProject.setToolTipText("Tambah Data");
        btnAddProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProjectActionPerformed(evt);
            }
        });

        btnEditProject.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\edit-16px.png")); // NOI18N
        btnEditProject.setText("Edit");
        btnEditProject.setToolTipText("Edit Data");
        btnEditProject.setPreferredSize(new java.awt.Dimension(91, 25));
        btnEditProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProjectActionPerformed(evt);
            }
        });

        btnDeleteProject.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\delete-16px.png")); // NOI18N
        btnDeleteProject.setText("Hapus");
        btnDeleteProject.setToolTipText("Hapus Data");
        btnDeleteProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProjectActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("ID Projects");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Nama Project");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Nama Client");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Tanggal Mulai");

        btnResetProject.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\refresh-16px.png")); // NOI18N
        btnResetProject.setToolTipText("Reset Form");
        btnResetProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetProjectActionPerformed(evt);
            }
        });

        dcStartProject2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcStartProject2PropertyChange(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Tanggal Selesai");

        dcEndProject2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcEndProject2PropertyChange(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("ID Client");

        txtIdClient2.setEditable(false);

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Nilai Project");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Jumlah Vendor");

        lbHari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbHari.setForeground(new java.awt.Color(255, 51, 51));

        cbNamaClient2.setEditable(true);
        cbNamaClient2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { null }));
        cbNamaClient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamaClient2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel49)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdClient2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel45)
                                .addComponent(jLabel48)
                                .addComponent(jLabel51))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIdProject2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dcStartProject2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dcEndProject2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNilaiProject2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel46)
                                .addComponent(jLabel47))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNamaProject2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(cbNamaClient2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel53)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnAddProject, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditProject, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteProject, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResetProject, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel54)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(txtJumlahVendor2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbHari))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdProject2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(txtNamaProject2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(cbNamaClient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtIdClient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(dcStartProject2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcEndProject2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addGap(37, 37, 37)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtNilaiProject2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(txtJumlahVendor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddProject, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditProject, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteProject, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnResetProject, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jPanel12.setBackground(new java.awt.Color(66, 72, 116));

        jLabel50.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Tambah Data");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel50)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel50)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jpDataProjectsLayout = new javax.swing.GroupLayout(jpDataProjects);
        jpDataProjects.setLayout(jpDataProjectsLayout);
        jpDataProjectsLayout.setHorizontalGroup(
            jpDataProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataProjectsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDataProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpDataProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpDataProjectsLayout.createSequentialGroup()
                        .addComponent(btnCariProject, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariProject, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel44)))
                .addContainerGap())
        );
        jpDataProjectsLayout.setVerticalGroup(
            jpDataProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataProjectsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDataProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDataProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpDataProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCariProject)
                            .addComponent(txtCariProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDataProjectsLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel44)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDataProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jpMainMenu.add(jpDataProjects, "card3");

        jpPembayaran.setBackground(new java.awt.Color(255, 255, 255));

        tblPembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPembayaranMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblPembayaran);

        btnCariPembayaran.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\find-16px.png")); // NOI18N
        btnCariPembayaran.setToolTipText("Cari Data");

        txtCariPembayaran.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCariPembayaran.setToolTipText("Cari ...");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 255));
        jLabel32.setText("Data Pembayaran");

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddPembayaran.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\add-16px.png")); // NOI18N
        btnAddPembayaran.setText("Tambah");
        btnAddPembayaran.setToolTipText("Tambah Data");
        btnAddPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPembayaranActionPerformed(evt);
            }
        });

        btnEditPembayaran.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\edit-16px.png")); // NOI18N
        btnEditPembayaran.setText("Edit");
        btnEditPembayaran.setToolTipText("Edit Data");
        btnEditPembayaran.setPreferredSize(new java.awt.Dimension(91, 25));
        btnEditPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPembayaranActionPerformed(evt);
            }
        });

        btnDeletePembayaran.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\delete-16px.png")); // NOI18N
        btnDeletePembayaran.setText("Hapus");
        btnDeletePembayaran.setToolTipText("Hapus Data");
        btnDeletePembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePembayaranActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Nomor PO");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Status Bayar");

        btnResetPembayaran.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\refresh-16px.png")); // NOI18N
        btnResetPembayaran.setToolTipText("Reset Form");
        btnResetPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPembayaranActionPerformed(evt);
            }
        });

        cbNoPO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { null }));

        cbStatusBayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Terbayar","Belum Terbayar" }));

        dcTanggalBayar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcTanggalBayarPropertyChange(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("Tanggal Bayar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnAddPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeletePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel52))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbStatusBayar, 0, 230, Short.MAX_VALUE)
                            .addComponent(cbNoPO, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcTanggalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(cbNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbStatusBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcTanggalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(148, 148, 148)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeletePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnResetPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(66, 72, 116));

        jLabel43.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Update Data Pembayaran");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel43)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel43)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        lblRowid.setForeground(new java.awt.Color(255, 255, 255));
        lblRowid.setText("jLabel8");

        javax.swing.GroupLayout jpPembayaranLayout = new javax.swing.GroupLayout(jpPembayaran);
        jpPembayaran.setLayout(jpPembayaranLayout);
        jpPembayaranLayout.setHorizontalGroup(
            jpPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPembayaranLayout.createSequentialGroup()
                        .addGroup(jpPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpPembayaranLayout.createSequentialGroup()
                                .addComponent(btnCariPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCariPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel32))))
                    .addGroup(jpPembayaranLayout.createSequentialGroup()
                        .addComponent(lblRowid)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpPembayaranLayout.setVerticalGroup(
            jpPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCariPembayaran)
                            .addComponent(txtCariPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPembayaranLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRowid)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jpMainMenu.add(jpPembayaran, "card3");

        jpPenerbitanPO.setBackground(new java.awt.Color(255, 255, 255));

        tblPO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPOMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPO);

        btnCariPO.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\find-16px.png")); // NOI18N
        btnCariPO.setToolTipText("Cari Data");
        btnCariPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPOActionPerformed(evt);
            }
        });

        txtCariPO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCariPO.setToolTipText("Cari ...");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 255));
        jLabel26.setText("Data Penerbitan PO");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddPO.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\add-16px.png")); // NOI18N
        btnAddPO.setText("Tambah");
        btnAddPO.setToolTipText("Tambah Data");
        btnAddPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPOActionPerformed(evt);
            }
        });

        btnEditPO.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\edit-16px.png")); // NOI18N
        btnEditPO.setText("Edit");
        btnEditPO.setToolTipText("Edit Data");
        btnEditPO.setPreferredSize(new java.awt.Dimension(91, 25));
        btnEditPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPOActionPerformed(evt);
            }
        });

        btnDeletePO.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\delete-16px.png")); // NOI18N
        btnDeletePO.setText("Hapus");
        btnDeletePO.setToolTipText("Hapus Data");
        btnDeletePO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePOActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Nomor PO");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Category");

        cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { null }));
        cbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoryActionPerformed(evt);
            }
        });
        cbCategory.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbCategoryPropertyChange(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nama Barang");

        cbNamaBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { null }));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("ID Vendor");

        cbNamaVendor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { null }));
        cbNamaVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamaVendorActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Nama Vendor");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Jumlah");

        txtJumlahBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahBarangKeyReleased(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Tarif");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("PIC Vendor");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Nama Project");

        cbNamaProject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { null }));
        cbNamaProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamaProjectActionPerformed(evt);
            }
        });

        txtIdProject.setEditable(false);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("ID Project");

        dcTglPO.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcTglPOPropertyChange(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("Tanggal");

        btnResetPO.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\refresh-16px.png")); // NOI18N
        btnResetPO.setToolTipText("Reset Form");
        btnResetPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnAddPO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditPO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeletePO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetPO, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel30))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbNamaBarang, 0, 269, Short.MAX_VALUE)))
                    .addComponent(jLabel55)
                    .addComponent(jLabel36)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNamaVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel37)
                            .addComponent(jLabel58))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdVendorPO, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPicVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcTglPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(18, 18, 18)
                        .addComponent(cbNamaProject, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addGap(37, 37, 37)
                        .addComponent(txtIdProject, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNamaProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNamaVendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdVendorPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPicVendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dcTglPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddPO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditPO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeletePO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnResetPO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel8.setBackground(new java.awt.Color(66, 72, 116));

        jLabel33.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Tambah Data");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel33)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel33)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        rowid.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rowid.setForeground(new java.awt.Color(255, 255, 255));
        rowid.setText("jLabel8");

        javax.swing.GroupLayout jpPenerbitanPOLayout = new javax.swing.GroupLayout(jpPenerbitanPO);
        jpPenerbitanPO.setLayout(jpPenerbitanPOLayout);
        jpPenerbitanPOLayout.setHorizontalGroup(
            jpPenerbitanPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPenerbitanPOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPenerbitanPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPenerbitanPOLayout.createSequentialGroup()
                        .addGroup(jpPenerbitanPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpPenerbitanPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpPenerbitanPOLayout.createSequentialGroup()
                                .addComponent(btnCariPO, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCariPO, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26))))
                    .addGroup(jpPenerbitanPOLayout.createSequentialGroup()
                        .addComponent(rowid)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpPenerbitanPOLayout.setVerticalGroup(
            jpPenerbitanPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPenerbitanPOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPenerbitanPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPenerbitanPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpPenerbitanPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCariPO)
                            .addComponent(txtCariPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPenerbitanPOLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPenerbitanPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rowid, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jpMainMenu.add(jpPenerbitanPO, "card3");

        jpDataUser.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane8.setAutoscrolls(true);
        jScrollPane8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUser.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblUser);

        btnCariUser.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\find-16px.png")); // NOI18N
        btnCariUser.setToolTipText("Cari Data");
        btnCariUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariUserActionPerformed(evt);
            }
        });

        txtCariUser.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCariUser.setToolTipText("Cari ...");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 255));
        jLabel28.setText("Data User");

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddUser.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\add-16px.png")); // NOI18N
        btnAddUser.setText("Tambah");
        btnAddUser.setToolTipText("Tambah Data");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnEditUser.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\edit-16px.png")); // NOI18N
        btnEditUser.setText("Edit");
        btnEditUser.setToolTipText("Edit Data");
        btnEditUser.setPreferredSize(new java.awt.Dimension(91, 25));
        btnEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUserActionPerformed(evt);
            }
        });

        btnDeleteUser.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\delete-16px.png")); // NOI18N
        btnDeleteUser.setText("Hapus");
        btnDeleteUser.setToolTipText("Hapus Data");
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Username");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Password");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Role");

        cbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Guest" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        jPanel13.setBackground(new java.awt.Color(66, 72, 116));

        jLabel62.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Tambah Data User");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel62)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel62)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        lblRow.setForeground(new java.awt.Color(255, 255, 255));
        lblRow.setText("jLabel9");

        javax.swing.GroupLayout jpDataUserLayout = new javax.swing.GroupLayout(jpDataUser);
        jpDataUser.setLayout(jpDataUserLayout);
        jpDataUserLayout.setHorizontalGroup(
            jpDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDataUserLayout.createSequentialGroup()
                        .addGroup(jpDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpDataUserLayout.createSequentialGroup()
                                .addComponent(btnCariUser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCariUser, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)))
                    .addGroup(jpDataUserLayout.createSequentialGroup()
                        .addComponent(lblRow)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpDataUserLayout.setVerticalGroup(
            jpDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDataUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCariUser)
                            .addComponent(txtCariUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDataUserLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRow)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jpMainMenu.add(jpDataUser, "card3");

        javax.swing.GroupLayout jpHeaderLayout = new javax.swing.GroupLayout(jpHeader);
        jpHeader.setLayout(jpHeaderLayout);
        jpHeaderLayout.setHorizontalGroup(
            jpHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpHeaderLayout.setVerticalGroup(
            jpHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpUtamaLayout = new javax.swing.GroupLayout(jpUtama);
        jpUtama.setLayout(jpUtamaLayout);
        jpUtamaLayout.setHorizontalGroup(
            jpUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 1350, Short.MAX_VALUE)
            .addGroup(jpUtamaLayout.createSequentialGroup()
                .addComponent(jpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpUtamaLayout.createSequentialGroup()
                        .addComponent(jpMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jpHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jpUtamaLayout.setVerticalGroup(
            jpUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUtamaLayout.createSequentialGroup()
                .addComponent(jpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpUtamaLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jpHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpUtamaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jpMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jmbUtama.setBackground(new java.awt.Color(204, 204, 204));
        jmbUtama.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jmbUtama.setForeground(new java.awt.Color(108, 92, 231));

        jMenu1.setText("| Master Data");

        jMenuItem17.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon16_16px.png")); // NOI18N
        jMenuItem17.setText("Home");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem17);
        jMenu1.add(jSeparator2);

        jMenuItem2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon1_16px.png")); // NOI18N
        jMenuItem2.setText("Data Client");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon2_16px.png")); // NOI18N
        jMenuItem1.setText("Data Vendor");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon4_16px.png")); // NOI18N
        jMenuItem3.setText("Data Barang");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jmbUtama.add(jMenu1);

        jMenu2.setText("| Transaksi");

        jMenuItem4.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon3_16px.png")); // NOI18N
        jMenuItem4.setText("Data List Project");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem6.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon6_16px.png")); // NOI18N
        jMenuItem6.setText("Penerbitan PO");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem5.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon5_16px.png")); // NOI18N
        jMenuItem5.setText("Pembayaran");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jmbUtama.add(jMenu2);

        jMenu3.setText("| Laporan");

        jMenuItem8.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon8_16px.png")); // NOI18N
        jMenuItem8.setText("Lap. Pembayaran");
        jMenu3.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon9_16px.png")); // NOI18N
        jMenuItem9.setText("Lap. Penerbitan PO");
        jMenu3.add(jMenuItem9);

        jMenuItem10.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon10_16px.png")); // NOI18N
        jMenuItem10.setText("Lap. List Project");
        jMenu3.add(jMenuItem10);

        jMenuItem11.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon11_16px.png")); // NOI18N
        jMenuItem11.setText("Lap. List Vendor");
        jMenu3.add(jMenuItem11);

        jMenuItem12.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon12_16px.png")); // NOI18N
        jMenuItem12.setText("Lap. Bulanan");
        jMenu3.add(jMenuItem12);

        jmbUtama.add(jMenu3);

        jMenu4.setText("| Pengaturan");

        jMenuItem13.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon13_16px.png")); // NOI18N
        jMenuItem13.setText("Akun");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem15.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon15_16px.png")); // NOI18N
        jMenuItem15.setText("About App");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem15);
        jMenu4.add(jSeparator1);

        jMenuItem14.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\Encona\\img\\icon14_16px.png")); // NOI18N
        jMenuItem14.setText("Logout");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jmbUtama.add(jMenu4);

        setJMenuBar(jmbUtama);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataVendor);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpHome);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataClient);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataBarang);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataProjects);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataClient);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_btnClientActionPerformed

    private void btnVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendorActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataVendor);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_btnVendorActionPerformed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataBarang);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_btnBarangActionPerformed

    private void btnProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjectActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataProjects);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_btnProjectActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpPembayaran);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void btnPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpPembayaran);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        tampilkanPembayaran();
    }//GEN-LAST:event_btnPembayaranActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpPenerbitanPO);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void btnPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPOActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpPenerbitanPO);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        kosongPO();
    }//GEN-LAST:event_btnPOActionPerformed

    private void btnDataUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataUserActionPerformed
        // TODO add your handling code here:
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataUser);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_btnDataUserActionPerformed

    private void btnAddVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVendorActionPerformed
        MasterVendor mv = new MasterVendor();
        mv.setVisible(true); // Menampilkan form menu data guru
        mv.setResizable(false); // Menonaktifkan tombol maximize. 
        mv.setTitle("Tambah Data"); // Untuk menghilangkan 3 tombol Min, Max, Close pada JFrame Properties centang Undecorated
    }//GEN-LAST:event_btnAddVendorActionPerformed

    private void btnAddClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddClientActionPerformed
        String insertClient = "insert into client (id_client,nama_client,alamat,no_telp,pic,tgl_join, npwp) values"
        +"('"+txtIdClient.getText()+"','"+txtNamaClient.getText()+"','"+txtAlamatClient.getText()+"','"+txtNoTelpClient.getText()+"','"
        +txtNamaPicClient.getText()+"','"+tgl_join+"','"+txtNpwp.getText()+"')";
        try {
            PreparedStatement stat = conn.prepareStatement(insertClient);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongClient();
            txtIdClient.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!"+e);
        }
        tampilkanClient();
    }//GEN-LAST:event_btnAddClientActionPerformed

    private void btnAddBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBarangActionPerformed
        String insertClient = "insert into barang (kd_barang,nama_barang,category,tarif) values"
        +"('"+txtKodeBarang.getText()+"','"+txtNamaBarang.getText()+"','"+cbCategoryBarang.getSelectedItem()+"','"+Integer.parseInt(txtTarifBarang.getText())+"')";
        try {
            PreparedStatement stat = conn.prepareStatement(insertClient);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongBarang();
            txtKodeBarang.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!"+e);
        }
        tampilkanBarang();
    }//GEN-LAST:event_btnAddBarangActionPerformed

    private void btnAddPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPOActionPerformed
        String insertPo = "insert into po (nama_project,id_project,no_po,nama_vendor,id_vendor,pic_vendor,category,nama_barang,jumlah,tarif,tgl_po) values"
                +"('"+cbNamaProject.getSelectedItem()
                +"','"+txtIdProject.getText()
                +"','"+txtNoPO.getText()
                +"','"+cbNamaVendor.getSelectedItem()
                +"','"+txtIdVendorPO.getText()
                +"','"+txtPicVendor.getText()
                +"','"+cbCategory.getSelectedItem()
                +"','"+cbNamaBarang.getSelectedItem()
                +"','"+txtJumlahBarang.getText()
                +"','"+txtTarif.getText()
                +"','"+tgl_po+"')";
        try {
            PreparedStatement stat = conn.prepareStatement(insertPo);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongPO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!"+e);
        }
        tampilkanPO();
        fillComboNoPO();
    }//GEN-LAST:event_btnAddPOActionPerformed

    private void tblClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientMouseClicked
        int i=tblClient.getSelectedRow();
        if(i>-1){
            txtIdClient.setText(tabClient.getValueAt(i, 0).toString());
            txtNamaClient.setText(tabClient.getValueAt(i, 1).toString());
            txtAlamatClient.setText(tabClient.getValueAt(i, 2).toString());
            txtNoTelpClient.setText(tabClient.getValueAt(i, 3).toString());
            txtNamaPicClient.setText(tabClient.getValueAt(i, 4).toString());
            try {
            Date tgl_join = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabClient.getValueAt(i,5));
            dtJoinClient.setDate(tgl_join);
            } catch(ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtNpwp.setText(tabClient.getValueAt(i, 6).toString());
        }
    }//GEN-LAST:event_tblClientMouseClicked

    private void btnEditClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditClientActionPerformed
        try {
            String sql = "update client "
                    + "set nama_client = ?, "
                    + "alamat = ?, "
                    + "no_telp = ?, "
                    + "pic = ?, "
                    + "tgl_join = ?, "
                    + "npwp = ? "
                    + "where id_client = '"+txtIdClient.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            //stat.setString(1, txtIdClient.getText());
            stat.setString(1, txtNamaClient.getText());
            stat.setString(2, txtAlamatClient.getText());
            stat.setString(3, txtNoTelpClient.getText());
            stat.setString(4, txtNamaPicClient.getText());
            stat.setString(5, tgl_join);
            stat.setString(6, txtNpwp.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongClient();
            txtIdClient.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal diupdate!"+e);
        }
        tampilkanClient();
    }//GEN-LAST:event_btnEditClientActionPerformed

    private void dtJoinClientPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtJoinClientPropertyChange
        if (dtJoinClient.getDate() != null) {
            //"dd MMM yyyy" untuk format tanggal 06 Des 2017
            SimpleDateFormat FormatTanggal = new SimpleDateFormat("yyyy-MM-dd");
            tgl_join = FormatTanggal.format(dtJoinClient.getDate());
        }
    }//GEN-LAST:event_dtJoinClientPropertyChange

    private void btnDeleteClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClientActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Data ingin dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0){
            String deleteClient = "delete from client where id_client = '"+txtIdClient.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(deleteClient);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
                kosongClient();
                txtIdClient.requestFocus();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus!"+e);
            }
            tampilkanClient();
        }
    }//GEN-LAST:event_btnDeleteClientActionPerformed

    private void btnCariClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariClientActionPerformed
        // TODO add your handling code here:
        int row = tblClient.getRowCount();
        for(int s=0; s<row;s++){
            tabClient.removeRow(0);
        }
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from client where id_client like '%"+txtCariClient.getText()+"%'"
                + "or nama_client like '%"+txtCariClient.getText()+"%'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
                tabClient.addRow(data);
                kosongClient();
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }//GEN-LAST:event_btnCariClientActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        tampilkanVendor();
        txtCariVendor.setText("");
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnEditVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditVendorActionPerformed
        MasterVendor mv = new MasterVendor();
        int i=tblVendor.getSelectedRow();
        mv.txtIdVendor.setText(tabVendor.getValueAt(i, 0).toString());
        mv.txtNamaVendor.setText(tabVendor.getValueAt(i, 1).toString());
        mv.txtNamaPicVendor.setText(tabVendor.getValueAt(i, 2).toString());
        mv.txtContactVendor.setText(tabVendor.getValueAt(i, 3).toString());
        mv.txtEmailVendor.setText(tabVendor.getValueAt(i, 4).toString());
        mv.cbCategory.setSelectedItem(tabVendor.getValueAt(i, 5).toString());
        mv.txtAlamat.setText(tabVendor.getValueAt(i, 6).toString());
        mv.cbStatus.setSelectedItem(tabVendor.getValueAt(i, 7).toString());
        mv.setVisible(true); // Menampilkan form menu data guru
        mv.setResizable(false); // Menonaktifkan tombol maximize. 
        mv.setTitle("Edit Data"); // Untuk menghilangkan 3 tombol Min, Max, Close pada JFrame Properties centang Undecorated
    }//GEN-LAST:event_btnEditVendorActionPerformed

    private void tblVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendorMouseClicked
        int i=tblVendor.getSelectedRow();
        String id = null;
        if(i>-1){
            id = tabVendor.getValueAt(i, 0).toString();
        }
    }//GEN-LAST:event_tblVendorMouseClicked

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        int i=tblBarang.getSelectedRow();
        if(i>-1){
            txtKodeBarang.setText(tabBarang.getValueAt(i, 0).toString());
            txtNamaBarang.setText(tabBarang.getValueAt(i, 1).toString());
            cbCategoryBarang.setSelectedItem(tabBarang.getValueAt(i, 2).toString());
            txtTarifBarang.setText(tabBarang.getValueAt(i, 3).toString());
        }
    }//GEN-LAST:event_tblBarangMouseClicked

    private void btnDeleteBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteBarangActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Data ingin dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0){
            String deleteClient = "delete from barang where kd_barang = '"+txtKodeBarang.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(deleteClient);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
                kosongBarang();
                txtKodeBarang.requestFocus();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus!"+e);
            }
            tampilkanBarang();
        }
    }//GEN-LAST:event_btnDeleteBarangActionPerformed

    private void btnResetBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetBarangActionPerformed
        kosongBarang();
    }//GEN-LAST:event_btnResetBarangActionPerformed

    private void btnEditBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBarangActionPerformed
        try {
            String sql = "update barang "
                    + "set nama_barang = ?, "
                    + "category = ?, "
                    + "tarif = ? "
                    + "where kd_barang = '"+txtKodeBarang.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtNamaBarang.getText());
            stat.setString(2, cbCategoryBarang.getSelectedItem().toString());
            stat.setInt(3, Integer.parseInt(txtTarifBarang.getText()));
            //stat.setString(3, txtTarifBarang.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongBarang();
            txtKodeBarang.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal diupdate!"+e);
        }
        tampilkanBarang();
    }//GEN-LAST:event_btnEditBarangActionPerformed

    private void btnDeleteVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteVendorActionPerformed
        int i = tblVendor.getSelectedRow();
        String id = tabVendor.getValueAt(i, 0).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Data ingin dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0){
            String deleteVendor = "delete from vendor where id_vendor = '"+id+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(deleteVendor);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus!"+e);
            }
            tampilkanBarang();
        }
    }//GEN-LAST:event_btnDeleteVendorActionPerformed

    private void btnCariVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariVendorActionPerformed
        int row = tblVendor.getRowCount();
        for(int s=0; s<row;s++){
            tabVendor.removeRow(0);
        }
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from vendor where id_vendor like '%"+txtCariVendor.getText()+"%'"
                + "or nama_vendor like '%"+txtCariVendor.getText()+"%'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
                tabVendor.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }//GEN-LAST:event_btnCariVendorActionPerformed

    private void tblPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPembayaranMouseClicked
        int i=tblPembayaran.getSelectedRow();
        if(i>-1){
            lblRowid.setText(tabPembayaran.getValueAt(i, 0).toString());
            cbNoPO.setSelectedItem(tabPembayaran.getValueAt(i, 1).toString());
            cbStatusBayar.setSelectedItem(tabPembayaran.getValueAt(i, 2).toString());
            try {
            Date tgl_bayar = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabPembayaran.getValueAt(i,3));
            dcTanggalBayar.setDate(tgl_bayar);
            } catch(ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            };
        }
    }//GEN-LAST:event_tblPembayaranMouseClicked

    private void btnAddPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddPembayaranActionPerformed

    private void btnEditPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPembayaranActionPerformed
        try {
            String sql = "update pembayaran "
                    + "set status_bayar = ?, "
                    + "tgl_bayar = ? "
                    + "where no_po = '"+cbNoPO.getSelectedItem()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, cbStatusBayar.getSelectedItem().toString());
            stat.setString(2, tgl_bayar);
            //stat.setString(3, txtTarifBarang.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongPembayaran();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal diupdate!"+e);
        }
        tampilkanPembayaran();
    }//GEN-LAST:event_btnEditPembayaranActionPerformed

    private void btnDeletePembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePembayaranActionPerformed
        int i = tblPembayaran.getSelectedRow();
        String id = tabPembayaran.getValueAt(i, 0).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Data ingin dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0){
            String deletePembayaran = "delete from pembayaran where rowid = '"+id+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(deletePembayaran);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus!"+e);
            }
            tampilkanPembayaran();
        }
    }//GEN-LAST:event_btnDeletePembayaranActionPerformed

    private void btnResetPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetPembayaranActionPerformed

    private void tblProjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProjectMouseClicked
        int i=tblProject.getSelectedRow();
        if(i>-1){
            txtIdProject2.setText(tabProject.getValueAt(i, 0).toString());
            txtNamaProject2.setText(tabProject.getValueAt(i, 1).toString());
            cbNamaClient2.setSelectedItem(tabProject.getValueAt(i, 2).toString());
            txtIdClient2.setText(tabProject.getValueAt(i, 3).toString());
            try {
            Date start_project = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabProject.getValueAt(i,4));
            dcStartProject2.setDate(start_project);
            } catch(ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            };
            try {
            Date end_project = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabProject.getValueAt(i,5));
            dcEndProject2.setDate(end_project);
            } catch(ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            };
            txtNilaiProject2.setText(tabProject.getValueAt(i, 6).toString());
            txtJumlahVendor2.setText(tabProject.getValueAt(i, 7).toString());
            String str = "Durasi project ini tersisa "+tabProject.getValueAt(i, 8).toString()+" hari lagi !";
            lbHari.setText(str);
            //lbHari.setText(tabProject.getValueAt(i, 6).toString());
        }
    }//GEN-LAST:event_tblProjectMouseClicked

    private void btnAddProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProjectActionPerformed
        String insertProject = "insert into project (id_project,nama_project,nama_client,id_client,tgl_mulai,tgl_selesai,nilai_project,jml_vendor) values"
        +"('"+txtIdProject2.getText()+"','"
             +txtNamaProject2.getText()+"','"
             +cbNamaClient2.getSelectedItem()+"','"
             +txtIdClient2.getText()+"','"
             +start_project+"','"
             +end_project+"','"
             +txtNilaiProject2.getText()+"','"
             +txtJumlahVendor2.getText()+"')";
        try {
            PreparedStatement stat = conn.prepareStatement(insertProject);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongProject();
            txtIdProject2.requestFocus();
            fillComboProject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!"+e);
        }
        tampilkanProject();
    }//GEN-LAST:event_btnAddProjectActionPerformed

    private void btnEditProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProjectActionPerformed
        try {
            String sql = "update project set "
                    + "nama_project = ?, "
                    + "nama_client = ?, "
                    + "id_client = ?, "
                    + "tgl_mulai = ?, "
                    + "tgl_selesai = ?, "
                    + "nilai_project = ?, "
                    + "jml_vendor = ? "
                    + "where id_project = '"+txtIdProject2.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            //stat.setString(1, txtIdClient.getText());
            stat.setString(1, txtNamaProject2.getText());
            stat.setString(2, cbNamaClient2.getSelectedItem().toString());
            stat.setString(3, txtIdClient2.getText());
            stat.setString(4, start_project);
            stat.setString(5, end_project);
            stat.setString(6, txtNilaiProject2.getText());
            stat.setString(7, txtJumlahVendor2.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongProject();
            txtIdProject2.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal diupdate!"+e);
        }
        tampilkanProject();
    }//GEN-LAST:event_btnEditProjectActionPerformed

    private void btnDeleteProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProjectActionPerformed
        int i = tblProject.getSelectedRow();
        String id = tabProject.getValueAt(i, 0).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Data ingin dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0){
            String deleteProject = "delete from project where id_project = '"+id+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(deleteProject);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus!"+e);
            }
            tampilkanProject();
            kosongProject();
            fillComboProject();
        }
    }//GEN-LAST:event_btnDeleteProjectActionPerformed

    private void btnResetProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetProjectActionPerformed
        kosongProject();
    }//GEN-LAST:event_btnResetProjectActionPerformed

    private void btnCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBarangActionPerformed
        int row = tblBarang.getRowCount();
        for(int s=0; s<row;s++){
            tabBarang.removeRow(0);
        }
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from Barang where kd_barang like '%"+txtCariBarang.getText()+"%'"
                + "or nama_Barang like '%"+txtCariBarang.getText()+"%'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                tabBarang.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    // TODO add your handling code here:
    }//GEN-LAST:event_btnCariBarangActionPerformed

    private void dcStartProject2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcStartProject2PropertyChange
        if (dcStartProject2.getDate() != null) {
            //"dd MMM yyyy" untuk format tanggal 06 Des 2017
            SimpleDateFormat FormatTanggal = new SimpleDateFormat("yyyy-MM-dd");
            start_project = FormatTanggal.format(dcStartProject2.getDate());
        }
    }//GEN-LAST:event_dcStartProject2PropertyChange

    private void dcEndProject2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcEndProject2PropertyChange
        if (dcEndProject2.getDate() != null) {
            //"dd MMM yyyy" untuk format tanggal 06 Des 2017
            SimpleDateFormat FormatTanggal = new SimpleDateFormat("yyyy-MM-dd");
            end_project = FormatTanggal.format(dcEndProject2.getDate());
        }
    }//GEN-LAST:event_dcEndProject2PropertyChange

    private void cbNamaClient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamaClient2ActionPerformed
        getDataComboClient();
    }//GEN-LAST:event_cbNamaClient2ActionPerformed

    private void btnCariProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariProjectActionPerformed
        int row = tblProject.getRowCount();
        for(int s=0; s<row;s++){
            tabProject.removeRow(0);
        }
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select a.*, datediff(tgl_selesai,curdate()) as days_remaining from project a where id_project like '%"+txtCariProject.getText()+"%'"
                + "or nama_project like '%"+txtCariProject.getText()+"%'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
                tabProject.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }//GEN-LAST:event_btnCariProjectActionPerformed

    private void cbNamaVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamaVendorActionPerformed
        getDataComboVendor();
    }//GEN-LAST:event_cbNamaVendorActionPerformed

    private void cbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoryActionPerformed
        getDataComboBarang();
    }//GEN-LAST:event_cbCategoryActionPerformed

    private void cbCategoryPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbCategoryPropertyChange
        
    }//GEN-LAST:event_cbCategoryPropertyChange

    private void txtJumlahBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahBarangKeyReleased
        getTarif();
    }//GEN-LAST:event_txtJumlahBarangKeyReleased

    private void tblVendorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVendorKeyReleased
        tampilkanVendor();
    }//GEN-LAST:event_tblVendorKeyReleased

    private void cbNamaProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamaProjectActionPerformed
        getDataComboProject();
    }//GEN-LAST:event_cbNamaProjectActionPerformed

    private void dcTglPOPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcTglPOPropertyChange
        if (dcTglPO.getDate() != null) {
            //"dd MMM yyyy" untuk format tanggal 06 Des 2017
            SimpleDateFormat FormatTanggal = new SimpleDateFormat("yyyy-MM-dd");
            tgl_po = FormatTanggal.format(dcTglPO.getDate());
        }
    }//GEN-LAST:event_dcTglPOPropertyChange

    private void tblPOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPOMouseClicked
        int i=tblPO.getSelectedRow();
        if(i>-1){
            rowid.setText(tabPO.getValueAt(i, 0).toString());
            cbNamaProject.setSelectedItem(tabPO.getValueAt(i, 1).toString());
            txtIdProject.setText(tabPO.getValueAt(i, 2).toString());
            txtNoPO.setText(tabPO.getValueAt(i, 3).toString());
            cbNamaVendor.setSelectedItem(tabPO.getValueAt(i, 4).toString());
            txtIdVendorPO.setText(tabPO.getValueAt(i, 5).toString());
            txtPicVendor.setText(tabPO.getValueAt(i, 6).toString());
            cbCategory.setSelectedItem(tabPO.getValueAt(i, 7).toString());
            cbNamaBarang.setSelectedItem(tabPO.getValueAt(i, 8).toString());
            txtJumlahBarang.setText(tabPO.getValueAt(i, 9).toString());
            txtTarif.setText(tabPO.getValueAt(i, 10).toString());
            try {
            Date tgl_po = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabPO.getValueAt(i,11));
            dcTglPO.setDate(tgl_po);
            } catch(ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            };
        }
    }//GEN-LAST:event_tblPOMouseClicked

    private void btnEditPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPOActionPerformed
        try {
            String sql = "update po "
                    + "set nama_project = ?,"
                    + "id_project = ?,"
                    + "no_po = ?,"
                    + "nama_vendor = ?,"
                    + "id_vendor = ?,"
                    + "pic_vendor = ?,"
                    + "category = ?,"
                    + "nama_barang = ?,"
                    + "jumlah = ?,"
                    + "tarif = ?,"
                    + "tgl_po = ?"
                    + "where rowid = '"+rowid.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, cbNamaProject.getSelectedItem().toString());
            stat.setString(2, txtIdProject.getText());
            stat.setString(3, txtNoPO.getText());
            stat.setString(4, cbNamaVendor.getSelectedItem().toString());
            stat.setString(5, txtIdVendorPO.getText());
            stat.setString(6, txtPicVendor.getText());
            stat.setString(7, cbCategory.getSelectedItem().toString());
            stat.setString(8, cbNamaBarang.getSelectedItem().toString());
            stat.setString(9, txtJumlahBarang.getText());
            stat.setString(10, txtTarif.getText());
            stat.setString(11, tgl_po);

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongPO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal diupdate!"+e);
        }
        tampilkanPO();
    }//GEN-LAST:event_btnEditPOActionPerformed

    private void btnDeletePOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePOActionPerformed
        int i = tblPO.getSelectedRow();
        String rowid = tabPO.getValueAt(i, 0).toString();
        String var_no_po = tabPO.getValueAt(i, 3).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Data ingin dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0){
            String deletePembayaran = "delete from pembayaran where no_po = '"+var_no_po+"'";
            String deleteProject = "delete from po where rowid = '"+rowid+"'";
            try {
                PreparedStatement stat1 = conn.prepareStatement(deletePembayaran);
                PreparedStatement stat2 = conn.prepareStatement(deleteProject);
                stat1.executeUpdate();
                stat2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus!"+e);
            }
            tampilkanPO();
            kosongPO();
            fillComboNoPO();
        }
    }//GEN-LAST:event_btnDeletePOActionPerformed

    private void btnCariPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPOActionPerformed
        int row = tblPO.getRowCount();
        for(int s=0; s<row;s++){
            tabPO.removeRow(0);
        }
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from po where no_po like '%"+txtCariPO.getText()+"%'"
                + "or nama_project like '%"+txtCariPO.getText()+"%'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)};
                tabPO.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }//GEN-LAST:event_btnCariPOActionPerformed

    private void btnResetPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPOActionPerformed
        kosongPO();
    }//GEN-LAST:event_btnResetPOActionPerformed

    private void dcTanggalBayarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcTanggalBayarPropertyChange
        if (dcTanggalBayar.getDate() != null) {
            //"dd MMM yyyy" untuk format tanggal 06 Des 2017
            SimpleDateFormat FormatTanggal = new SimpleDateFormat("yyyy-MM-dd");
            tgl_bayar = FormatTanggal.format(dcTanggalBayar.getDate());
        }
    }//GEN-LAST:event_dcTanggalBayarPropertyChange

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        int i=tblUser.getSelectedRow();
        if(i>-1){
            lblRow.setText(tabUser.getValueAt(i, 0).toString());
            txtUsername.setText(tabUser.getValueAt(i, 1).toString());
            txtPassword.setText(tabUser.getValueAt(i, 2).toString());
            cbRole.setSelectedItem(tabUser.getValueAt(i, 3).toString());
        }
    }//GEN-LAST:event_tblUserMouseClicked

    private void btnCariUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariUserActionPerformed
        int row = tblUser.getRowCount();
        for(int s=0; s<row;s++){
            tabUser.removeRow(0);
        }
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from t_admin where username like '%"+txtCariUser.getText()+"%'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                tabUser.addRow(data);
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal ditampilkan !!!"+e.getMessage());
        }
    }//GEN-LAST:event_btnCariUserActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        String insertUser = "insert into t_admin (username, password, role) values"
        +"('"+txtUsername.getText()+"','"
             +txtPassword.getText()+"','"
             +cbRole.getSelectedItem()+"')";
        try {
            PreparedStatement stat = conn.prepareStatement(insertUser);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongUser();
            txtUsername.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!"+e);
        }
        tampilkanUser();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUserActionPerformed
        try {
            String sql = "update t_admin "
                    + "set username = ?,"
                    + "password = ?,"
                    + "role = ?"
                    + "where rowid = '"+lblRow.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtUsername.getText());
            stat.setString(2, txtPassword.getText());
            stat.setString(3, cbRole.getSelectedItem().toString());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosongUser();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal diupdate!"+e);
        }
        tampilkanUser();
    }//GEN-LAST:event_btnEditUserActionPerformed

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed
        int i = tblUser.getSelectedRow();
        String rowid = tabUser.getValueAt(i, 0).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Data ingin dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0){
            String deleteUser = "delete from t_admin where rowid = '"+rowid+"'";
            try {
                PreparedStatement stat1 = conn.prepareStatement(deleteUser);
                stat1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus!"+e);
            }
            tampilkanUser();
            kosongUser();
        }
    }//GEN-LAST:event_btnDeleteUserActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        About abt = new About();
        abt.setVisible(true); // Menampilkan form about
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        jpMainMenu.removeAll();
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
        
        //add panel baru
        jpMainMenu.add(jpDataUser);
        jpMainMenu.repaint();
        jpMainMenu.revalidate();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBarang;
    private javax.swing.JButton btnAddClient;
    private javax.swing.JButton btnAddPO;
    private javax.swing.JButton btnAddPembayaran;
    private javax.swing.JButton btnAddProject;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnAddVendor;
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnCariBarang;
    private javax.swing.JButton btnCariClient;
    private javax.swing.JButton btnCariPO;
    private javax.swing.JButton btnCariPembayaran;
    private javax.swing.JButton btnCariProject;
    private javax.swing.JButton btnCariUser;
    private javax.swing.JButton btnCariVendor;
    private javax.swing.JButton btnClient;
    private javax.swing.JButton btnDataUser;
    private javax.swing.JButton btnDeleteBarang;
    private javax.swing.JButton btnDeleteClient;
    private javax.swing.JButton btnDeletePO;
    private javax.swing.JButton btnDeletePembayaran;
    private javax.swing.JButton btnDeleteProject;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnDeleteVendor;
    private javax.swing.JButton btnEditBarang;
    private javax.swing.JButton btnEditClient;
    private javax.swing.JButton btnEditPO;
    private javax.swing.JButton btnEditPembayaran;
    private javax.swing.JButton btnEditProject;
    private javax.swing.JButton btnEditUser;
    private javax.swing.JButton btnEditVendor;
    private javax.swing.JButton btnPO;
    private javax.swing.JButton btnPembayaran;
    private javax.swing.JButton btnProject;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnResetBarang;
    private javax.swing.JButton btnResetPO;
    private javax.swing.JButton btnResetPembayaran;
    private javax.swing.JButton btnResetProject;
    private javax.swing.JButton btnVendor;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JComboBox<String> cbCategoryBarang;
    private javax.swing.JComboBox<String> cbNamaBarang;
    private javax.swing.JComboBox<String> cbNamaClient2;
    private javax.swing.JComboBox<String> cbNamaProject;
    private javax.swing.JComboBox<String> cbNamaVendor;
    private javax.swing.JComboBox<String> cbNoPO;
    private javax.swing.JComboBox<String> cbRole;
    private javax.swing.JComboBox<String> cbStatusBayar;
    private com.toedter.calendar.JDateChooser dcEndProject2;
    private com.toedter.calendar.JDateChooser dcStartProject2;
    private com.toedter.calendar.JDateChooser dcTanggalBayar;
    private com.toedter.calendar.JDateChooser dcTglPO;
    private com.toedter.calendar.JDateChooser dtJoinClient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuBar jmbUtama;
    private javax.swing.JPanel jpDataBarang;
    private javax.swing.JPanel jpDataClient;
    private javax.swing.JPanel jpDataProjects;
    private javax.swing.JPanel jpDataUser;
    private javax.swing.JPanel jpDataVendor;
    private javax.swing.JPanel jpHeader;
    private javax.swing.JPanel jpHome;
    private javax.swing.JPanel jpMainMenu;
    private javax.swing.JPanel jpMenu;
    private javax.swing.JPanel jpPembayaran;
    private javax.swing.JPanel jpPenerbitanPO;
    private javax.swing.JPanel jpTitle;
    private javax.swing.JPanel jpUtama;
    private javax.swing.JLabel lbHari;
    private javax.swing.JLabel lblRow;
    private javax.swing.JLabel lblRowid;
    private javax.swing.JLabel rowid;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTable tblClient;
    private javax.swing.JTable tblPO;
    private javax.swing.JTable tblPembayaran;
    private javax.swing.JTable tblProject;
    private javax.swing.JTable tblUser;
    private javax.swing.JTable tblVendor;
    private javax.swing.JTextField txtAlamatClient;
    private javax.swing.JTextField txtCariBarang;
    private javax.swing.JTextField txtCariClient;
    private javax.swing.JTextField txtCariPO;
    private javax.swing.JTextField txtCariPembayaran;
    private javax.swing.JTextField txtCariProject;
    private javax.swing.JTextField txtCariUser;
    private javax.swing.JTextField txtCariVendor;
    private javax.swing.JTextField txtIdClient;
    private javax.swing.JTextField txtIdClient2;
    private javax.swing.JTextField txtIdProject;
    private javax.swing.JTextField txtIdProject2;
    private javax.swing.JTextField txtIdVendorPO;
    private javax.swing.JTextField txtJumlahBarang;
    private javax.swing.JTextField txtJumlahVendor2;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNamaClient;
    private javax.swing.JTextField txtNamaPicClient;
    private javax.swing.JTextField txtNamaProject2;
    private javax.swing.JTextField txtNilaiProject2;
    private javax.swing.JTextField txtNoPO;
    private javax.swing.JTextField txtNoTelpClient;
    private javax.swing.JTextField txtNpwp;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPicVendor;
    private javax.swing.JTextField txtTarif;
    private javax.swing.JTextField txtTarifBarang;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

