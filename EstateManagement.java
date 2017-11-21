

/**
 * Owner : Adedayo Matt
 * Date Created : January 13, 2016
 * Last Revised: January 31, 2016
 * 
 **** PROGRAM SUMMARY: This program allows to save and read information using 
 * Formatter and Scanner class respectively, information can also be altered 
 * and deleted but restoration of deleted information automatically has not been
 * coded, but can be checked manually on the hard drive by following the Garbage
 * URL: "C:\estateManagement\ZGarbage".
 * 
 * All images and icon used in this program are located in this URL:
 * "C:\estateManagement\Resources\".
 * 
 * For help or any enquiry, you can contact
 * ----------------------------------------------------
 * phone: +2348139004572
 * email: adedayomatt@gmail.com
 * facebook: www.facebook.com/kayode.adedayo1
 * ------------------------------------------------------
 */

package estatemanagement;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EstateManagement {

   
    /**
     * Global Variables
     */
    JTextField UserLogin;
    JTextField Ctitle,LastName, FirstName,Gender, Contact, Address, Date_E, Date_Ex;
    JMenuItem Switch,about,Exit,settings,contact,authorize;
    ArrayList<String> cl = new ArrayList<String>();
    FlowLayout flow = new FlowLayout();
    Font textFont = new Font("Segoe Print", Font.PLAIN,15);
       // Maximum number List to display on the home page as recently added/updated
    int NumberofRecentlyAdded = 7;
    String currentUser;
    Formatter editClient;
    /**
     * DataBase URLs for Original data
     */ 
    String addressURL = "C:\\estateManagement\\Original\\Addresses\\";
    String clientPath = "C:\\estateManagement\\Original\\profiles\\";
    String ListURL = "C:\\estateManagement\\Original\\list\\ALLCLIENTS.txt";
    
    //Resources like images used are in this URL
    String ResourcesURL = "C:\\estateManagement\\Resources\\";
     /**
     * DataBase URLs for Deleted data
     */ 
    String GarbageAddress="C:\\estateManagement\\ZGarbage\\GarbagedAddresses\\";
    String GarbageProfile="C:\\estateManagement\\ZGarbage\\Garbagedprofiles\\";
    String GarbageList= "C:\\estateManagement\\ZGarbage\\Garbagedlist\\ALLGARBAGE.txt";
    
  /**
   * String array for ComboBox (day,month and year)
   */
    String[] titles = {"title", "Mr", "Mrs", "Miss", "Ms"};
    String[] day = {"DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
        "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] month = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
    String[] year = {"YY", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022",
        "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
    
 
    
    /** Main method: This is where execution begins
     * Execution begins with the security method where the administrator have to login
     * @param args the command line arguments
     
     */
    public static void main(String[] args) {
        
        EstateManagement E = new EstateManagement();
        E.LOGIN();
                
    }
    
/**INPUTPAGE method: This is where  data can be input to be saved 
 * @param Frametitle gives the frame title since this method is not only called 
 *when new profile is to be saved but also when updating profile, so there is
 * need to manipulate the JFrame title.
 * 
 * @param save_update_restore determines what to be inscribed on the button that 
 * will either save or update or restore(not available yet) data in the 
 * JTextField, and also manipulate the confirm message.
 * 
 * @param Clienttitle: Title 
 * @param SName: Surname
 * @param FName: First name
 * @param gender: Gender
 * @param contact: Contact
 * @param address: Address
 * @param entry: Date of Entry
 * @param exit : Date of Exit.
 */
    void INPUTPAGE(String Frametitle,final String save_update_restore,
            String Clienttitle, String SName, String FName, String gender,
            String contact, String address, String entry, String exit) {
        
        final JFrame Frame = Frame(Frametitle);
        logo(Frame);
        JPanel main = GridPanel(Frame, 8,3);
        JRadioButton Male;
        JRadioButton Female;
        ButtonGroup SEX = new ButtonGroup();

        Text(main, "");
        JPanel T = FlowPanel(main);
        Text(T, "Title");
        Ctitle = TextField(T, 5, false);
        Combo(T, Ctitle, titles);
        main.add(T);
        Text(main, "");

        infoPanel(main, "Surname");
        LastName = TextField(main, 5, true);
        Text(main, "");
        infoPanel(main, "First Name");
        FirstName = TextField(main, 5, true);
        Text(main, "");

        infoPanel(main, "Gender");
        JPanel sex = FlowPanel(main);
        Gender = TextField(sex, 5, false);
        Male = radioButton(sex, Gender, "Male");
        Female = radioButton(sex, Gender, "Female");
        SEX.add(Male);
        SEX.add(Female);
        Text(main, "");

        infoPanel(main, "Contact");
        Contact = TextField(main, 5, true);
        Text(main, "");
        infoPanel(main, "Addresss");
        Address = TextField(main, 5, true);
        Text(main, "");

        infoPanel(main, "Date of Entry");
        JPanel DateIn = FlowPanel(main);
        final JTextField dayI = TextField(DateIn, 2, false);
        Combo(DateIn, dayI, day);
        final JTextField monthI = TextField(DateIn, 2, false);
        Combo(DateIn, monthI, month);
        final JTextField yearI = TextField(DateIn, 3, false);
        Combo(DateIn, yearI, year);
        Date_E = TextField(DateIn, 7, false);
        JButton OK = new JButton("OK");
        OK.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date_E.setText(dayI.getText() + "-" + monthI.getText() + "-" + yearI.getText());
                    }
                });
        DateIn.add(OK);
        Text(main, "");

        infoPanel(main, "Date of Exit");
        JPanel DateOut = FlowPanel(main);
        final JTextField dayO = TextField(DateOut, 2, false);
        Combo(DateOut, dayO, day);
        final JTextField monthO = TextField(DateOut, 2, false);
        Combo(DateOut, monthO, month);
        final JTextField yearO = TextField(DateOut, 3, false);
        Combo(DateOut, yearO, year);

        Date_Ex = TextField(DateOut, 7, false);
        JButton OK2 = new JButton("OK");
        OK2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date_Ex.setText(dayO.getText() + "-" + monthO.getText() + "-" + yearO.getText());
                    }
                } );
        DateOut.add(OK2);

        JPanel buttons = FlowPanel(main);
        //Save button
        JButton saveAccount = new JButton(save_update_restore);
        saveAccount.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        if(Ctitle.getText().isEmpty()||LastName.getText().isEmpty()||FirstName.getText().isEmpty()||
                                Gender.getText().isEmpty()||Contact.getText().isEmpty()||Address.getText().isEmpty()||
                                Date_E.getText().isEmpty()||Date_Ex.getText().isEmpty()
                                ){
                        JOptionPane.showMessageDialog(null,"None of the fields should be empty",
                        "Not "+save_update_restore+"d", JOptionPane.ERROR_MESSAGE);
                        }
        //if all the JTextField are not empty, save the inputs.
                        else{
                            
                        saveData(save_update_restore+"d");
                        Frame.dispose();
           //Manipulating save_update_restore
                        switch(save_update_restore){
           //if it is update, save it, and go back to the detail page of the edited profile 
                            case "update":
                                File updated = new File(clientPath+Ctitle.getText()+" "+LastName.getText()+" "+FirstName.getText()+" .txt");
                                DETAILPAGE(updated);
                                break;
      //if it is save, save it, and go back to the homepage
                            case "save":
                                HOMEPAGE();
                                break;
                            default:
                                System.out.println("this is default");
                        }
                       }
                    }
                }
        );
        JButton view = new JButton("view all clients");
        view.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Frame.dispose();
                        ALLCLIENTPAGE();
                     }
                });
         buttons.add(saveAccount);
        buttons.add(view);
/**
 * By default, set the the text in the JTextFields to Strings as defined in the argument
 */
        Ctitle.setText(Clienttitle);
        LastName.setText(SName);
        FirstName.setText(FName);
        Gender.setText(gender);
        Contact.setText(contact);
        Address.setText(address);
        Date_E.setText(entry);
        Date_Ex.setText(exit);
        bottom(Frame);

        Frameproperties(Frame);
    }
    
    /**
     ** @param keyword: keyword to search for
     * @param Found: Array of found element that matches the keyword
     * @param N : Number of element that matches the keyword == length of Found[]
     */
    void SEARCHPAGE(String[] Found,String keyword, int N){
    JFrame searchFrame = Frame("Search");
    logo(searchFrame);
    
    JPanel main =GridPanel(searchFrame,1,4);
    
    JPanel result = FlowPanel(main);
   Text(result,"      "+String.valueOf(N)+" results Found for '"+ keyword+"'. ");
    AllClients(searchFrame,FlowPanel(main),Found,false);
  
    Text(result,"search again?");
  searchPanel(searchFrame,result,keyword);
   
   
    bottom(searchFrame);
    Frameproperties(searchFrame);
    }
    
    void HOMEPAGE(){
        JFrame HomeFrame = Frame("Home");
        logo(HomeFrame);
        JPanel main = GridPanel(HomeFrame,1,4);
        main.setBackground(Color.white);
        
       JPanel First = NavigationPanel(HomeFrame,main);
       First.setBackground(main.getBackground());
       
       JPanel second = new JPanel();
       second.setBackground(main.getBackground());
       second.setLayout(new BorderLayout());
       
       //First get the client list
        ArrayList fetch = getClientsList("OriginalList");
      //make another arraylist to put the recently added
        ArrayList<String> recentlyAdded = new ArrayList<>();
        JLabel RA = new JLabel("                     "
                + "                   "
                + "            "
                + "Recently Added/Updated");
        second.add(RA,BorderLayout.NORTH);
  /**
   * if number of elements in the list is greater than the number recently added
   * as specified in @variable NumberofRecentlyAdded
   */
        if(fetch.size()>NumberofRecentlyAdded){
   
    //* Add the element from the last index into the recentlyAdded ArrayList.
  
        for(int i = fetch.size()-1;i>=fetch.size()-NumberofRecentlyAdded;i--)
        {
        recentlyAdded.add(fetch.get(i).toString());
        }
   //* convert the recentlyAdded to String array 
        String[] setList = new String[recentlyAdded.size()];
       recentlyAdded.toArray(setList);
       
         //*put the list on the homepage using AllClients which returns JList
       JPanel recentList = FlowPanel(second);
        recentList.setBackground(main.getBackground());
        AllClients( HomeFrame,recentList, setList,false);
     }
 /*
   * if number of elements in the list is less than the number recently added
   * as specified in @variable NumberofRecentlyAdded, just convert to String array
   * and display using the AllClient method.
   */      
        else{
            
            String[] setList = new String[fetch.size()];
       fetch.toArray(setList);
        
        JPanel recentList = FlowPanel(second);
        recentList.setBackground(main.getBackground());
     AllClients( HomeFrame,recentList, setList,false);
        
        }
        
        main.add(second);
       JPanel empty = FlowPanel(main);
       empty.setBackground(main.getBackground());
      bottom(HomeFrame);
        Frameproperties(HomeFrame);
    }
    
    /**
     * ALLCLIENTPAGE method display all the saved client in a JList 
     */
    void ALLCLIENTPAGE() {
        final JFrame AllClientFrame = Frame("All Clients");
        logo(AllClientFrame);
        JPanel main = GridPanel(AllClientFrame,1,4);
        main.setBackground(Color.white);
        
       JPanel Navigation = NavigationPanel(AllClientFrame,main);
       Navigation.setBackground(main.getBackground());
        
        ArrayList fetch = getClientsList("OriginalList");
        String[] setList = new String[fetch.size()];
        fetch.toArray(setList);

        JPanel mainList = FlowPanel(main);
        mainList.setBackground(main.getBackground());
   AllClients( AllClientFrame,mainList, setList,true);
   
       JPanel empty = FlowPanel(main);
       empty.setBackground(main.getBackground());
       Text(empty,"can't find a client on the list?, try");
       JButton RecycleBin = new JButton("check Garbage");
       
       JPanel empty2 = FlowPanel(main);
       empty2.setBackground(main.getBackground());
       
       RecycleBin.setBackground(empty.getBackground());
       RecycleBin.addActionListener(
       new ActionListener(){
public void actionPerformed(ActionEvent e) {
              AllClientFrame.dispose();
              GARBAGEPAGE();
    }
      } );
      
       empty.add(RecycleBin);
        bottom(AllClientFrame);
        Frameproperties(AllClientFrame);

    }
    
    /**
     * 
     * @param profile : File to read the data from.
     */
    void DETAILPAGE(final File profile) {
        
        final File AddressFile = new File(addressURL + profile.getName());
        final String title = profile.getName().substring(0, profile.getName().length() - 4);

        final JFrame DetailFrame = Frame(title);
        logo(DetailFrame);
        JPanel main = GridPanel(DetailFrame, 1, 3);
        main.setBackground(Color.white);
        
        JPanel west = FlowPanel(main);
        west.setBackground(main.getBackground());
        
        JPanel center =GridPanel(main,7,3);
        center.setBackground(main.getBackground());
        
        JPanel east = FlowPanel(main);
        east.setBackground(main.getBackground());
        
        final  JLabel FULLNAME, GENDER, CONTACT, ADDRESS, DATEIN, DATEOUT;
        final JLabel subtitle, surname, lastname,gender,con_tact,addr,
                datein,dateout,lastSaved;
        
        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");
                lastSaved = new JLabel();
                FULLNAME = new JLabel();
                 GENDER = new JLabel();
                CONTACT = new JLabel();
                ADDRESS = new JLabel();
                DATEIN = new JLabel();
                DATEOUT = new JLabel();
              JLabel  photoLabel = Text(west,"");
              
             JButton changeDP = new JButton("change profile picture");
             changeDP.addActionListener(
             new ActionListener(){
             public void actionPerformed(ActionEvent e){
             JOptionPane.showMessageDialog(null,"not avilable yet");
                        }
                    }
             );
             west.add(changeDP);
             
 subtitle = new JLabel();
 surname = new JLabel();
 lastname = new JLabel();dateout = new JLabel();
 gender = new JLabel();datein = new JLabel();
 con_tact = new JLabel();addr = new JLabel();
 
 

        try {
     String T, SN, FN, GD, CT, DE, DEx;
     String t1,t2,t3,t4,t5;
                
                    String FullName;
                    center.add(lastSaved);
                    center.add(FULLNAME);
                    center.add(GENDER);
                    center.add(CONTACT);
                    center.add(ADDRESS);
                    center.add(DATEIN);
                    center.add(DATEOUT);
                    ButtonsOnPanel(east,edit);
                    ButtonsOnPanel(east,delete);
                    Text(east," "+title+"'s profile?");
                    
                    try(Scanner getDetails = new Scanner(profile)) {
                        
          try{
                        while (getDetails.hasNext()) {
                            
                            T = getDetails.next();
                            SN = getDetails.next();
                            FN = getDetails.next();
                            subtitle.setText(T.substring(0,T.length()-2));
                            surname.setText(SN.substring(0,SN.length()-2));
                            lastname.setText(FN.substring(0,FN.length()-2));
                            
                            FullName = T.substring(0,T.length()-2) +"  " 
                                    + SN.substring(0,SN.length()-2) +"  "
                                    + FN.substring(0,FN.length()-2);
                            FULLNAME.setText("Client Name:  "+FullName);
                            
                            GD = getDetails.next();
                            gender.setText(GD.substring(0,GD.length()-2));
                            GENDER.setText("Gender:  "+GD.substring(0,GD.length()-2));
                            
                            CT = getDetails.next();
                            con_tact.setText(CT.substring(0,CT.length()-2));
                            CONTACT.setText("Contact:  "+CT.substring(0,CT.length()-2));
                            
                            DE = getDetails.next();
                            datein.setText(DE.substring(0,DE.length()-2));
                            DATEIN.setText("Date of Entry:  "+DE.substring(0,DE.length()-2));
                            
                            DEx = getDetails.next();
                            dateout.setText(DEx.substring(0,DEx.length()-2));
                            DATEOUT.setText("Date of Exit:  "+DEx.substring(0,DEx.length()-2));
                            
                            t1 = getDetails.next();
                            t2 = getDetails.next();
                            t3 = getDetails.next();
                            t4 = getDetails.next();
                            t5 = getDetails.next();
                            lastSaved.setText("Last updated: "+t1+" "+t2+" "+t3+", "+t5.substring(0,t5.length()-2)+"   "+t4);
                        }
                        if(gender.getText().equals("Male")){
                        photoLabel.setIcon(new ImageIcon(ResourcesURL+"male.png"));
                        }
                        else if(gender.getText().equals("Female")){
                        photoLabel.setIcon(new ImageIcon(ResourcesURL+"female.png"));
                        }
                    }
                    catch(NoSuchElementException error808){
                        errorModule(null, error808, "error808", "Some data are missing in the selected profile");
                }
                }

               try( Scanner getAdress = new Scanner(AddressFile)){;
                    String F = "";
                    while (getAdress.hasNext()) {
                        String ADDR = getAdress.next();
                        F += ADDR + " ";
                        ADDRESS.setText("Address: "+F.substring(2,F.length()-3));
                        addr.setText(F.substring(2,F.length()-3));
                    }
                    
               }
            
        } catch (FileNotFoundException error505) {
            errorModule(null, error505, "error505", "Couldn't read the profile");
         }
             edit.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                              deleteFile(profile, AddressFile,"Edit");
                                DetailFrame.dispose();
    INPUTPAGE("update","update", subtitle.getText(),surname.getText(), 
            lastname.getText(),gender.getText(), con_tact.getText(),
           addr.getText(), datein.getText(), dateout.getText());
                           }
                        } );
             delete.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    deleteFile(profile, AddressFile,"Delete");
                                    JOptionPane.showMessageDialog(null,
                                            title + "'s profile deleted successfully",
                                            "deleted",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    DetailFrame.dispose();
                                }
                            }
                    );
                
       
        bottom(DetailFrame);
        Frameproperties(DetailFrame);
    }
    /**
     * GARBAGEPAGE method show all the deleted profiles. This profiles can be
     * restored back.(Not available yet)
     */
    void GARBAGEPAGE(){
    JFrame GFrame = Frame("Garbage");
    logo(GFrame);
    JPanel main = GridPanel(GFrame,6,1);
   
    
 //*Getting the list from the garbage
    ArrayList<String> GarbageArray = getClientsList("Garbage");
    String[] Garbage = new String[GarbageArray.size()];
    GarbageArray.toArray(Garbage);
    
    Text(main,"This page is under maintainance you may not be able to restore deleted data for now").setForeground(Color.red);
            
    Text(main," we're sorry for any incovenience this might cause.").setForeground(Color.red);
    Text(main,"You can however check the Recycle bin manually, check ").setForeground(Color.red);
    JButton instruction = new JButton("how to check manually");
    
    ButtonsOnPanel(main,instruction);
    instruction.addActionListener(
    new ActionListener(){
 public void actionPerformed(ActionEvent e) {
 JOptionPane.showMessageDialog(null,"Instruction not availabe yet\nWe'll sure get this done soon.\n\n-Matt",
         "how to check deleted profiles",JOptionPane.INFORMATION_MESSAGE );
 }
  }
 );
    bottom(GFrame);
    Frameproperties(GFrame);
    }
   
    void saveData(String ActionDid) {
        Date currentTime = new Date();
     File accountAd = new File(addressURL + Ctitle.getText() + " " + LastName.getText() + " " + FirstName.getText() + " .txt");
        File account = new File(clientPath + Ctitle.getText() + " " + LastName.getText() + " " + FirstName.getText() + " .txt");
        if (account.exists() || accountAd.exists()) {
            JOptionPane.showMessageDialog(null, "There is already an acount "
                    + "for " + Ctitle.getText() + " " + LastName.getText() + " " + FirstName.getText(),
                    "account already exist", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                try (Formatter save = new Formatter(account)) {
                    save.format(
                            Ctitle.getText() + "//\t"
                            + LastName.getText() + "//\t"
                            + FirstName.getText() + "//\t"
                            + Gender.getText() + "//\t"
                            + Contact.getText() + "//\t"
                            +Date_E.getText() + "//\t"
                            + Date_Ex.getText() + "//\t"
                            + currentTime.toString().substring(0,20)+currentTime.toString().
                                substring(24,currentTime.toString().length()) + "//\t"
                    );
                    save.close();
                }

                File addressFile = new File(addressURL + Ctitle.getText() + " " + LastName.getText() + " " + FirstName.getText() + " .txt");
                Formatter saveAddress = new Formatter(addressFile);
                saveAddress.format("//"+Address.getText()+"//");
                saveAddress.close();

                AddtoList(Ctitle.getText() + " " + LastName.getText() + " " + FirstName.getText());

                JOptionPane.showMessageDialog(null, Ctitle.getText() + " " + LastName.getText() + " " + FirstName.getText()
                        + "'s profile has been "+ActionDid,
                        "Succesful", JOptionPane.INFORMATION_MESSAGE);

            } catch (FileNotFoundException error101) {
                errorModule(null, error101, "error101", Ctitle.getText() + " " + LastName.getText() + " " + FirstName.getText()
                        + " profile was not "+ActionDid)
                         ;
            }
        }
    }
    
    void deleteFile(File profile, File addressfile,String edit_delete) {
       // File List = new File(ListURL);
        String deletedData ="", deletedAddress = "";
        String Name = profile.getName().substring(0, profile.getName().length() - 4);
        
        try{
            String data, address;
            
        Scanner readProfile = new Scanner(profile);
        Scanner readAddress = new Scanner(addressfile);
        
        //fetch details
        while(readProfile.hasNext()){
        data = readProfile.next();
        deletedData += data+" ";
       }readProfile.close();
        
        //fetch the Address
       
        while(readAddress.hasNext()){
           address  = readAddress.next();
        deletedAddress += address+" ";
        }readAddress.close();
        }
        catch(FileNotFoundException e){
        JOptionPane.showMessageDialog(null,
                "can't transfer to the recycle bin"
                        ,"error0000001",
                JOptionPane.ERROR_MESSAGE); 
        }
        
        //Transfering to Garbage
        try{
            File GarbagedProfile = new File(GarbageProfile+profile.getName());
            File GarbagedAddress = new File(GarbageAddress+addressfile.getName());
            File GarbagedList = new File(GarbageList);
            String finalGarbageList = "";
        Formatter writeToGarbageProfile = new Formatter(GarbagedProfile);
        Formatter writeToGarbageAddress = new Formatter(GarbagedAddress);
       
        
        writeToGarbageProfile.format(deletedData);
        writeToGarbageProfile.close();
        writeToGarbageAddress.format(deletedAddress);
       writeToGarbageAddress.close();
       
       ArrayList<String> GArray = getClientsList("Garbage");
       GArray.add(Name);
       for(String temp: GArray){
       finalGarbageList += temp;
       }
       try{
        Formatter writeToGarbageList = new Formatter(GarbagedList);
       writeToGarbageList.format(finalGarbageList);
        writeToGarbageList.close();
       }
       catch(FileNotFoundException r){
         JOptionPane.showMessageDialog(null,
                "can't transfer to the recycle bin"
                        ,"error0000000004",
                JOptionPane.ERROR_MESSAGE); 
        }
        }
        catch(FileNotFoundException r){
         JOptionPane.showMessageDialog(null,
                "can't transfer to the recycle bin"
                        ,"error0000000004",
                JOptionPane.ERROR_MESSAGE); 
        }
        
        
        
        JOptionPane.showMessageDialog(null,
                edit_delete+" " + Name + "'s profile?",
                "delete?",
                JOptionPane.QUESTION_MESSAGE);
        switch(edit_delete){
            case "Edit":
        profile.delete();
        addressfile.delete();
        RemoveFromList(Name);
         break;
            case "Delete":
                profile.delete();
        addressfile.delete();
        RemoveFromList(Name);
                ALLCLIENTPAGE();
                break;
            default:
              JOptionPane.showMessageDialog(null,
                "something is wrong,"
                        + "\nhint:\n %%check the switch handling edit/delete in the deleteFile method"
                        + "\n%%check all other methods where the delete methods is called"
                        + "","error",
                JOptionPane.ERROR_MESSAGE); 
              //&&readAddress.hasNext()
                break;
        }
        
    
    }

    void AddtoList(String ClientName) {
        String total = "";

        ArrayList<String> newList = new ArrayList<>();
        newList = getClientsList("OriginalList");
        newList.add(ClientName);

        for (String x : newList) {
            total += x;
        }
        try {
            editClient = new Formatter(ListURL);
            editClient.format(total);
            editClient.close();

        } catch (FileNotFoundException error404) {
            errorModule(null, error404, "error404", "Couldn't add a new profile");
        }
     }
    
    void RemoveFromList(String ClientName) {
        String total = "";
   
        ArrayList<String> newList = new ArrayList<>();
        newList = getClientsList("OriginalList");

        if (newList.contains(ClientName)) {
            int IndexNo = newList.indexOf(ClientName)+ 1;
            JOptionPane.showMessageDialog(null,
                    "Client profile was found\n\nClient Name: '" + ClientName + "'"
                    + "\nIndex Number: 00" +String.valueOf(IndexNo) ,
                    " Client found",
                    JOptionPane.INFORMATION_MESSAGE);
            newList.remove(ClientName);
        }

        for (String x : newList) {
            total += x;
        }
        try {
            File open = new File(ListURL);
            editClient = new Formatter(open);
            editClient.format(total);
            editClient.close();

        } catch (FileNotFoundException error707) {
            errorModule(null, error707, "error707", "There was a problem while updating "
                    + "the client list database");
        }

    }

    ArrayList getClientsList(String where) {
        Scanner get = null;
        ArrayList<String> List = new ArrayList<>();
        String a, b, c, full;
        
        try {
            switch(where){
                case "OriginalList":
            get = new Scanner(new File(ListURL));
                    break;
                case "Garbage":
                    get = new Scanner(new File(GarbageList));
                    break;
            }
            try {
                while (get.hasNext()) {
                    a = get.next();
                    b = get.next();
                    c = get.next();
                    full = a + " " + b + " " + c + " ";
                    List.add(full);
                }
            } catch (Exception error202) {
    errorModule(null, error202, "error202", "Problem encountered while reading from the client list file");
            }
        } catch (FileNotFoundException error303) {
            errorModule(null, error303, "error303", "Couldn't fetch the Client list");
           
        }
        return List;
    }

    JPanel NavigationPanel(final JFrame current,Container parent){
    JPanel NavigatePanel = GridPanel(parent,8,1);
    NavigatePanel.setBackground(Color.orange);
    Text(NavigatePanel,"Total number of Clients: "+getClientsList("OriginalList").size());
    Text(NavigatePanel,"         Quick Buttons");
    
    JButton showAll = new JButton("All Clients");
    showAll.setToolTipText("show the list of all saved profiles");
    JButton AddNew = new JButton("Add new Client");
    AddNew.setToolTipText("create new profile");
    JButton RB = new JButton("Recycle Bin");
    RB.setToolTipText("show deleted profiles(not available yet)");
    
    showAll.addActionListener(
    new ActionListener(){
public void actionPerformed(ActionEvent e) {
    current.dispose();
        ALLCLIENTPAGE();    
} } );
    
    AddNew.addActionListener(
    new ActionListener(){
public void actionPerformed(ActionEvent e) {
    current.dispose();
        INPUTPAGE("Add new Client", "save","", "", "", "", "", "", "", "");
    }});
    
    RB.addActionListener(
    new ActionListener(){
public void actionPerformed(ActionEvent e) {
    current.dispose();
        GARBAGEPAGE();    
        }});
    
    ButtonsOnPanel(NavigatePanel,showAll);
    ButtonsOnPanel(NavigatePanel,AddNew);
    ButtonsOnPanel(NavigatePanel,RB);
    
    parent.add(NavigatePanel);
    return NavigatePanel;
    }
    
    JPanel searchPanel(final JFrame current,Container parent,String keyword){
        
        JPanel searchPanel = FlowPanel(parent);
    searchPanel.setBackground(parent.getBackground());
    
   final JTextField searchField = TextField(searchPanel,15,true);
   searchField.setBackground(Color.pink);
   searchField.setText("search");
   searchField.setText(keyword);

    JButton search = new JButton(new ImageIcon(ResourcesURL+"Search.png"));
    search.setToolTipText("search");
    search.setBackground(Color.white);
    search.addActionListener(
    new ActionListener(){
@Override
public void actionPerformed(ActionEvent e) {
    /**
     * check if the search JTextField is empty
     */
    if(searchField.getText().isEmpty()){
     JOptionPane.showMessageDialog(null,"search field cannot be empty " +searchField.getText()
                        ,"nothing to find", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * if the it's not empty, it it can proceed to search the list
     */
    else{
        /**
         * First get all the Client list
         */
    ArrayList<String> searchList = getClientsList("OriginalList");
    /**
     * Another array to add the elements that match the search keyword 
     */
    ArrayList<String> FoundList = new ArrayList();
    /**
     * Looping through the list and checking each element if it contain the
     * search keyword
     */
     for(String check: searchList){
    if(check.contains(searchField.getText()))
    {
        /**
         * if any element contain the search keyword, add it to FoundList
        * */
        FoundList.add(check);
         }
            }
     /**
      * if the no element contains the search keyword, then the FoundList is empty
      */
     if(FoundList.isEmpty()){
    JOptionPane.showMessageDialog(null,"No match found for '" +searchField.getText()+"'",
                        "not found", JOptionPane.INFORMATION_MESSAGE);
}
     /**
      * if FoundList is not empty, pass the element into a String array >found<
      */
         else{
               String[] found = new String[searchList.size()];
        FoundList.toArray(found);
         JOptionPane.showMessageDialog(null,FoundList.size()+" results found",
                        "found", JOptionPane.INFORMATION_MESSAGE);
         /**
          * dispose the current JFrame where the the search panel is and go...
          */
         current.dispose();
         /**
          * then go to the search page
          */
         SEARCHPAGE(found,searchField.getText(),FoundList.size());
         
                     }      
                }
            }
        }
    );
    searchPanel.add(search);
    parent.add(searchPanel);
    return searchPanel;
    }

    JList AllClients(final JFrame F,Container Parent, String[] clients,boolean showTotal) {
      JPanel list_No = new JPanel();
      list_No.setLayout(new BorderLayout());
         list_No.setBackground(Color.white);
        
       final JList allClients;
        JLabel TotalClient = new JLabel();
        int Total;

        
        if(showTotal==true){
            TotalClient.setText("             "
                    + "Showing all (" + clients.length+")  Clients");
        }
        
        list_No.add(TotalClient,BorderLayout.NORTH);
        JPanel ListPanel = FlowPanel(list_No);
        
        if (clients.length >= 1) {

            allClients = new JList(clients);
            allClients.setFont(new Font("Segoe Print", Font.BOLD , 15));
            allClients.setBackground(Color.cyan);
            allClients.setVisibleRowCount(7);
            allClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            allClients.setFixedCellWidth(250);
            ListPanel.add(new JScrollPane(allClients));

            allClients.addListSelectionListener(
                    new ListSelectionListener() {
                        
                        @Override
                         public void valueChanged(ListSelectionEvent e) {
                            String SelectedClient = allClients.getSelectedValue().toString();

                            File clientProfile = new File(clientPath + SelectedClient + ".txt");
                            if (clientProfile.exists()) {
                                F.dispose();
                                DETAILPAGE(clientProfile);

                            } else {
                                JOptionPane.showMessageDialog(null,
                                        SelectedClient + "'s profile not found\nit may have been deleted",
                                        "profile not found",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
            );
Parent.add(list_No);
        } else {
            String[] fake = new String[0];
            allClients = new JList(fake);
            JPanel JP = FlowPanel(Parent);
            JP.setBackground(Color.GREEN);

            JLabel noList = new JLabel();
            noList.setForeground(Color.red);
            noList.setFont(new Font("Segoe Print", Font.BOLD + Font.ITALIC, 23));
            noList.setText("NO CLIENT TO DISPLAY");
            JP.add(noList);
JButton Goback = new JButton("Create new profile");
Goback.addActionListener(
        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                F.dispose();
                                INPUTPAGE("New","save","","","","","","","","");
                            }
                        }

);
JP.add(Goback);
        }
        return allClients;
    }

    
    void infoPanel(Container parent, String info) {
        Text(parent, "                          "
                + "                        "
                + info);
    }

    //JFrame with Menubar
    JFrame Frame(String title) {
        
      MenuBarHandler handle = new MenuBarHandler();
        GridLayout grid = new GridLayout(3, 1);
        JFrame frame = new JFrame(title);
        frame.setLayout(grid);

        
        JMenuBar Menubar = new JMenuBar();
        Menubar.setBackground(Color.blue);
        
        JMenu Options = new JMenu("options");
        JMenu Help = new JMenu("Help");
        Menubar.add(Options);
        Menubar.add(Help);
        
        Switch = new JMenuItem("Logout");
           about = new JMenuItem("About Device");
           settings = new JMenuItem("Settings");
           Exit = new JMenuItem("Exit");
           contact = new JMenuItem("contacts");
           
           Switch.addActionListener(handle);
           about.addActionListener(handle);
           settings.addActionListener(handle);
           Exit.addActionListener(handle);
           contact.addActionListener(handle);
           
           Options.add(Switch);
           Options.add(about);
           Options.add(settings);
           Options.add(Exit);
           Help.add(contact);
        
frame.setJMenuBar(Menubar);
        return frame;
    }

    //Frame global properties
    void Frameproperties(JFrame F) {
        F.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        F.setSize(1280, 800);
        F.setResizable(false);
        F.setVisible(true);
    }

    //Gridlayout JPanel 
    JPanel GridPanel(Container parent, int x, int y) {
        GridLayout grid = new GridLayout(x, y);
        JPanel panel = new JPanel();
        // panel.setBackground(parent.getBackground());
        panel.setLayout(grid);
        parent.add(panel);
        return panel;
    }

    //Flowlayout JPanel 
    JPanel FlowPanel(Container parent) {
        JPanel panel = new JPanel();
        //panel.setBackground(parent.getBackground());
        panel.setLayout(flow);
        parent.add(panel);
        return panel;
    }
    
    void ButtonsOnPanel(Container Parent,JButton theButton){
    JPanel parent = FlowPanel(Parent);
    parent.setBackground(Parent.getBackground());
    parent.add(theButton);
    Parent.add(parent);
    }
    
    //ComboBox 
    void Combo(Container p, final JTextField field, String[] elements) {
        final JComboBox Box = new JComboBox(elements);
        Box.addItemListener(
                new ItemListener() {

                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        String X = (String) Box.getSelectedItem();
                        field.setText(X);

                    }
                }
        );
        p.add(Box);
    }

    //JTextField
    JTextField TextField(Container parent, int size, boolean edit) {
        JTextField Field = new JTextField(size);
        Field.setEditable(edit);
        parent.add(Field);
        return Field;
    }

    //RADIOBUTTON
    JRadioButton radioButton(Container parent, final JTextField print, final String A) {
        JRadioButton button = new JRadioButton(A);
        button.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        print.setText(A);
                    }

                }
        );
        parent.add(button);
        return button;
    }

    //JLabel
    JLabel Text(Container parent, String text) {
        JLabel label = new JLabel(text);
         label.setFont(textFont);
        parent.add(label);
        return label;
    }

    //top panel
    Color b;
    JPanel logo(final JFrame P) {
        Date Time = new Date();
        JLabel DateNTime = new JLabel("Today: "+Time);
        
       Icon icon = new ImageIcon(ResourcesURL+"mattApp.png");
       JPanel pane = GridPanel(P,3,1);
       pane.setBackground(Color.white);
      // b = JColorChooser(null,"select color",b);
        JLabel main = new JLabel();
        main.setIcon(icon);
       
       JPanel mainLogo = FlowPanel(pane);
        mainLogo.setBackground(pane.getBackground());
        mainLogo.add(main);
        
        JPanel stripes =GridPanel(pane,4,1);
        for(int i = 1;i<=2;i += 1){
        JPanel red = FlowPanel(stripes);
        red.setBackground(Color.cyan);
        JPanel green = FlowPanel(stripes);
        green.setBackground(Color.blue);
       }
       
        JPanel Home_Time = new JPanel();
                Home_Time.setLayout(new FlowLayout());
                Home_Time.setBackground(Color.white);
                 
        
        DateNTime.setFont(textFont);
        //substring(20,23)
        JButton Home = new JButton(new ImageIcon(ResourcesURL+"home.png"));
        Home.setToolTipText("Home");
        Home.setBackground(Home_Time.getBackground());
        Home.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                P.dispose();
                             HOMEPAGE();  
                            }
                        } );
      JPanel HomePanel = new JPanel();
      HomePanel.setBackground(Home_Time.getBackground());
        HomePanel.add(Home);
     
      JPanel search = new JPanel();
      search.setBackground(Home_Time.getBackground());
      searchPanel(P,search,"");
      Home_Time.add(search);
       Text(Home_Time,"logged in as: "+UserLogin.getText());
       Text(Home_Time,"                                ");
        Home_Time.add(HomePanel);
        Text(Home_Time,"                                ");
       Home_Time.add(DateNTime);
     pane.add(Home_Time);
        
        P.add(pane);
        
        
        return pane;
    }

    //bottom panel
    JPanel bottom(final JFrame P) {
        JPanel down = GridPanel(P,4,1);
        down.setBackground(Color.blue);
        JPanel North = FlowPanel(down);
        North.setBackground(Color.white);
        JPanel center = FlowPanel(down);
        center.setBackground(Color.white);
        JPanel south = FlowPanel(down);
        south.setBackground(down.getBackground());
        JPanel xtreme = FlowPanel(down);
        xtreme.setBackground(down.getBackground());
        JLabel logout1 = Text(center,"");
        logout1.setIcon(new ImageIcon(ResourcesURL+"logout1.jpeg"));
        
        JButton logout2 = new JButton(new ImageIcon(ResourcesURL+"logout2.png"));
        logout2.setBackground(Color.white);
        logout2.setRolloverIcon(new ImageIcon(ResourcesURL+"power.jpeg"));
        center.add(logout2);
        logout2.addActionListener(
        new ActionListener(){
        public void actionPerformed(ActionEvent e){
        Date time = new Date();
        P.dispose();
         JOptionPane.showMessageDialog(null, "logout successful\nlogout time: "+time,"logged out",JOptionPane.INFORMATION_MESSAGE);
         LOGIN(); }
        });
        
        JLabel facebookLike = Text(center,"");
        facebookLike.setIcon(new ImageIcon(ResourcesURL+"facebookLike.jpeg"));
        Text(center,"www.facebook.com/kayode.adedayo1");
        
        JLabel feedback = Text(south,"");
        feedback.setIcon(new ImageIcon(ResourcesURL+"feedback.jpeg"));
        
        JTextField feedbackField = TextField(south,25,true);
        feedbackField.setBackground(Color.pink);
        
       JLabel copyright = Text(xtreme,"all rights reserved c.2015  Kayode Adedayo Matthew");
       copyright.setForeground(Color.white);
        return down;
    }

    void errorModule(Container F, Exception error, String VK_error, String ErrorSummary) {
        String StackString = "";
        int N = 0;
        StackTraceElement[] stack = error.getStackTrace();
        for (StackTraceElement ST : stack) {
            StackString += "%%" + ST + "\n";
            N++;
            if (N == 16) {
                break;
            }
        }


        JOptionPane.showMessageDialog(null, "oops!, something went wrong\n"
                + "error Summary: " + ErrorSummary
                + "\nTechnical Detail %%Exception Type%%: \n" + error.toString()
                +"\nQuick Check:"
                + "\nMake sure the following URL exist in your Hard disk drive\n"
                +clientPath+"\n"+addressURL+"\n"+ListURL+"\n"+ResourcesURL
                + "\n\nif the error persist, please report to the"
                + " software owner >> @matt. for possible solution"
                
                + "\n\t\tBelow is the Stack Trace."
                + "\n\n\tStack Trace:\n\t("
                + N + " of " + stack.length + " lines)\n "
                + StackString
                + "...\npress alt+f4 to exit this error", VK_error,
                JOptionPane.ERROR_MESSAGE);

    }
   int loginTime = 0;
   void LOGIN(){
   final JFrame LoginFrame = new JFrame("Admin login");
    LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    LoginFrame.setLayout(new GridLayout(3,1));
    
    JMenuBar mb = new JMenuBar();
    LoginFrame.setJMenuBar(mb);
    JMenu options = new JMenu("options");
    authorize = new JMenuItem("Authorize user");
    authorize.addActionListener(null);
    mb.add(options);
    options.add(authorize);
//System.out.println("Decryption range: " +encryptionPre.length()+"-"+encryptionPost.length());
            
    final File securityFile = new File(ResourcesURL+"private.txt");
    final Date D = new Date();
    
    JPanel logoPanel = FlowPanel(LoginFrame);
    Text(logoPanel,"").setIcon(new ImageIcon(ResourcesURL+"mattApp.png"));
    logoPanel.setBackground(Color.cyan);
    JPanel main = GridPanel(LoginFrame,4,1);
    main.setBackground(Color.white);
    
    JPanel up = GridPanel(main,2,1);
            up.setBackground(main.getBackground());
     JPanel middle = FlowPanel(main);
     middle.setBackground(main.getBackground());
     JPanel middle2 = FlowPanel(main);
     middle2.setBackground(main.getBackground());
      JPanel down = FlowPanel(main);
      down.setBackground(main.getBackground());
    
    Text(up,"*please login");
    Text(up,"NOTE: This application is not public, only the administrator and"
            + " authorized users are allowed"
            + "\nsee the admin for authorization");
    Text(middle,"user: ");
   UserLogin = TextField(middle,15,true);
   UserLogin.setText("admin");
    
    Text(middle2,"password: ");
    final JPasswordField password = new JPasswordField(20);
    middle2.add(password);
    
   JButton Login = new JButton("login");
    middle2.add(Login);
    
    final JTextField prompt = TextField(down,25,false);
    prompt.setBackground(Color.pink);
    JButton changePass = new JButton("change password");
    down.add(changePass);
    /**
     * Security logic
     * 
     */
   Login.addActionListener(
            new ActionListener(){
@Override

public void actionPerformed(ActionEvent e) {
//    Thread time = new Thread();
//    prompt.setText("verifying password...");
//    try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(EstateManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    time.start();
    try{
    Scanner getPassword = new Scanner(securityFile);
   while(getPassword.hasNext()){
    String EncryptedPassword = getPassword.next();
    //decrypting the password: read from 80th substring and remove the last 80th substring
    String CorrectPassword = EncryptedPassword.substring(80,EncryptedPassword.length()-80);
    
    
        System.out.println("password: "+CorrectPassword);
       
    if(password.getText().equals(CorrectPassword)){
        LoginFrame.dispose();
         loginTime += 1;
         System.out.println("logged in: "+loginTime+" times");
       JOptionPane.showMessageDialog(null, "login successful\n\nlogin time: "+D,"access granted",JOptionPane.INFORMATION_MESSAGE);
     HOMEPAGE();
    }
    else{
   prompt.setText("incorrect password, please try again");
            }
        }
    }
    catch(FileNotFoundException error){
    JOptionPane.showMessageDialog(null, "can't read the security file","access denied",JOptionPane.ERROR_MESSAGE);
                 }
            }
         }
      );
   
   changePass.addActionListener(
   new ActionListener(){
       
   @Override
   public void actionPerformed(ActionEvent e) {
    try{
    Scanner getPassword = new Scanner(securityFile);
    while(getPassword.hasNext()){
        String EncryptedPassword = getPassword.next();
        
 //decrypting the password: read from 80th substring and remove the last 80 substring
    String CorrectPassword = EncryptedPassword.substring(80,EncryptedPassword.length()-80);
//System.out.println("password: "+CorrectPassword);
    String OldOne = JOptionPane.showInputDialog("Enter the old password","old password");
    try{
    if(OldOne.equals(CorrectPassword)){
        String NewOne = JOptionPane.showInputDialog("Enter the new password","new password");
        String ConfirmNewOne =JOptionPane.showInputDialog("Confirm the new password","confirm password");
        
      if(ConfirmNewOne.equals(NewOne)){
        FormatPassword(ConfirmNewOne);
        JOptionPane.showMessageDialog(null, "password changed successful","done",JOptionPane.INFORMATION_MESSAGE);
    LoginFrame.dispose();
        LOGIN();
        }
        else{
        JOptionPane.showMessageDialog(null, "password do not match\npassword change failed","not done",JOptionPane.ERROR_MESSAGE);
    LoginFrame.dispose();
        LOGIN();
        }
    }
    else{
    JOptionPane.showMessageDialog(null, "incorrect password\n\ncan't change password",
            "access denied",JOptionPane.ERROR_MESSAGE);
                }
            }
    catch(NullPointerException Null){
       LoginFrame.dispose();
        LOGIN(); 
                    }
                }
         }       
    catch(FileNotFoundException error){
   JOptionPane.showMessageDialog(null, "can't read the security file","error",JOptionPane.ERROR_MESSAGE);
            }  
         }
      }
   );
 
   JPanel bottom =FlowPanel(LoginFrame);
 bottom.setBackground(logoPanel.getBackground());
 Text(bottom,"All right reserved Adedayo Matt. (c)2016").setFont(textFont);
 
     LoginFrame.setSize(940,550);
        LoginFrame.setLocation(150, 100);
        LoginFrame.setResizable(false);
        LoginFrame.setVisible(true);
       }
    /**
     * This variables encrypt the password for security
     */
    String encryptionPre =   "hgi7o/970/bnsgtrinfurt7ectg/g/egwpqswipdep/ivtfhdedkq'lbvhedcri432it457v95hejo4v";
    String encryptionPost =  "po5ifkkghreg/khbrusnadgirghfjzoej/38ry75459n3fcerwatrnbpeawv/yhkwkjQww8565483-qw";
     
   void FormatPassword(String Newpassword){
       File passwordFile = new File(ResourcesURL+"private.txt");
   try{
   Formatter formatPass = new Formatter(passwordFile);
   formatPass.format(encryptionPre+Newpassword+encryptionPost);
   formatPass.close();
   }
   catch(FileNotFoundException error){
    JOptionPane.showMessageDialog(null, "can't change the password"
            + "\nerror occured while writing to the security file","not done",JOptionPane.ERROR_MESSAGE);
    }
   }
   
    public class MenuBarHandler implements ActionListener{
 @Override
 public void actionPerformed(ActionEvent e){
     
      if(e.getSource()==settings){
      JOptionPane.showMessageDialog(null, "Not available yet","Not available",
              JOptionPane.INFORMATION_MESSAGE);
      }
      else if(e.getSource()==authorize){
     String adminPass = JOptionPane.showInputDialog("provide the Admin password","password");
      }        
     else if(e.getSource() == Switch){
          //LOGIN();
      }
      else if(e.getSource() == about)
         JOptionPane.showMessageDialog(null, "Device information\nDeveloper:"
              + " Adedayo Matt\nversion 1.1.0", "about",
              JOptionPane.INFORMATION_MESSAGE);
             else if(e.getSource()==Exit){
      //JOptionPane.showConfirmDialog(null, "Close this program","Exit", JOptionPane.YES_NO_OPTION,JOptionPane.OK_OPTION);
                 System.exit(0);
             }
             else if(e.getSource()==contact)
              JOptionPane.showMessageDialog(null, "Incase any problem or enquiry, please contact the "
                      + "\nowner through the following channels\n"
                      + "\nphone: +2348139004572"
                      + "\nemail: adedayomatt@gmail.com"
                      + "\nfacebook: www.facebook.com/kayode.adedayo1", "contacts", JOptionPane.INFORMATION_MESSAGE);
    }
        }

                }
