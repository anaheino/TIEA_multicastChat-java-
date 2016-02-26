
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



///varsinainen luokka ja toiminnan toteuttava luokka. 

public class multigui extends JFrame {

	/**
	 * aluksi windowbuilderin tekemät automaattiset muuttujat, ja senderin ja receiverin alustus.
	 */
	private Sender sender = null;
	private receiver rece = null;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnLeave;
	private JButton btnJoin;
	private JButton btnSend;
	private JTextField textFieldVuosi;
	private JTextField textFieldKuukausi;
	private JTextField textFieldPaiva;
	private JLabel lblbirthday;
	private JTextField textFieldPort;
	private JLabel lblMulticastp;
	private JTextField textFieldMulticastO;
	private JLabel lblMulticastO;
	private JTextField txtVaihdaOmaksi;
	private JLabel lblNewLabel;
	private JTextArea textAreaChat;
	private JTextArea textAreaUsers;
    private Timer timer = null;
    private boolean needToUpdate = false;
    ActionListener taskPerformer = null;
    private String[] lines;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					multigui frame = new multigui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame. Autogeneroituia koodia.
	 */
	public multigui() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1140, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		btnSend = new JButton("Send");
		btnSend.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		btnSend.setEnabled(false);
		textField = new JTextField();
		textField.setColumns(10);
		btnLeave = new JButton("Leave");
		btnLeave.setEnabled(false);
		btnJoin = new JButton("Join");
		textFieldVuosi = new JTextField();
		textFieldVuosi.setColumns(10);
           int tulos = generoiRandom(1960, 1995);
		textFieldVuosi.setText(Integer.toString(tulos));
		textFieldKuukausi = new JTextField();
		textFieldKuukausi.setColumns(10);
		
		tulos = generoiRandom(1, 12);
		textFieldKuukausi.setText(Integer.toString(tulos));
		textFieldPaiva = new JTextField();
		textFieldPaiva.setColumns(10);
		tulos = generoiRandom(1, 31);
		textFieldPaiva.setText(Integer.toString(tulos));
		lblbirthday = new JLabel("Syntymäaika:");
		
		textFieldPort = new JTextField();
		textFieldPort.setText("6666");
		textFieldPort.setColumns(10);
		
		lblMulticastp = new JLabel("Multicast Portti:");
		
		textFieldMulticastO = new JTextField();
		textFieldMulticastO.setText("239.0.0.1");
		textFieldMulticastO.setColumns(10);
		
		lblMulticastO = new JLabel("Multicast osoite:");
		
		txtVaihdaOmaksi = new JTextField();
		txtVaihdaOmaksi.setText("testi");
		txtVaihdaOmaksi.setColumns(10);
		
		lblNewLabel = new JLabel("Nimi:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMulticastO)
							.addGap(25)
							.addComponent(textFieldMulticastO, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMulticastp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtVaihdaOmaksi, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblbirthday)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPaiva, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldKuukausi, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldVuosi, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnJoin, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLeave))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSend))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 884, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)))
					.addGap(27))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldMulticastO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMulticastO)
						.addComponent(lblMulticastp)
						.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(txtVaihdaOmaksi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblbirthday)
						.addComponent(textFieldPaiva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldKuukausi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldVuosi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnJoin, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLeave))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend)))
		);
		
		textAreaUsers = new JTextArea();
		scrollPane_1.setViewportView(textAreaUsers);
		
		textAreaChat = new JTextArea();
		scrollPane.setViewportView(textAreaChat);
		contentPane.setLayout(gl_contentPane);
		
		//seuraa actionlistenerit. Aluksi keskusteluun liittymisnappula.
		
		btnJoin.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	//uusi receiver-luokka, joka ottaa vastaan viestejä. Sitten sen säie käyntiin.
		    	rece = new receiver();
		    	rece.execute();
		    	try {
		    		//haetaan nimi, portti, multicastosoite ja syntymäaika. Tarkistetaan, etteivät arvot ole liian
		    		//suuria. Jos joo, vaihdetaan muut arvot iäksi.
		    		String uname = txtVaihdaOmaksi.getText();
		    		int p = Integer.parseInt(textFieldPaiva.getText());
		    		int k = Integer.parseInt(textFieldKuukausi.getText());
		    		int v = Integer.parseInt(textFieldVuosi.getText());
		    		if(p>31 || k > 12 || v > 2000){
		    		p=1;
		    		k = 1;
		    		v = 1992;
		    		}
		    		String o = textFieldMulticastO.getText();
		    		int portti = Integer.parseInt(textFieldPort.getText());
		    		// tehdään textfieldeistä "lukittuja" keskustelun ajaksi, alustetaan komponentteja.
		    		txtVaihdaOmaksi.setEditable(false);
		    		textFieldPaiva.setEditable(false);
		    		textFieldKuukausi.setEditable(false);
		    		textFieldVuosi.setEditable(false);
		    		textFieldMulticastO.setEditable(false);
		    		textFieldPort.setEditable(false);
					btnSend.setEnabled(true);
					btnJoin.setEnabled(false);
					btnLeave.setEnabled(true);
					textAreaUsers.setText("");
		    		// laitetaan sender-konstruktorille sen tarvitsemat argumentit.
					sender = new Sender(uname, p, k, v, o, portti);
				} catch (SocketException | UnknownHostException e1) {e1.printStackTrace();}
		    } 
		});
		//lähtemis-funktio. Suljetaan viestien vastaanotto ja vapautetaan muokattavaksi disabloidut kentät.
		
		btnLeave.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		     rece.cancel(true);
		     try {
		    	 //laitetaan lähtöilmoitus asiakkaalle.
				sender.send("/leave", 2);
			} catch (IOException e1) {e1.printStackTrace();}
	    		txtVaihdaOmaksi.setEditable(true);
	    		textFieldPaiva.setEditable(true);
	    		textFieldKuukausi.setEditable(true);
	    		textFieldVuosi.setEditable(true);
	    		textFieldMulticastO.setEditable(true);
	    		textFieldPort.setEditable(true);
				btnSend.setEnabled(false);
				btnJoin.setEnabled(true);
				btnLeave.setEnabled(false);
				textAreaUsers.setText("");
		    } 
		});
		//laitetaan viestiä menemään jos se ei ole tyhjä ja tyhjennetään tekstikenttä.
		btnSend.addActionListener(new ActionListener() { 
		    
			public void actionPerformed(ActionEvent e) { 
		     
		     String viesti = textField.getText();
		     if("".equals(viesti))return;
		     try {
				sender.send(viesti, 3);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		     textField.setText("");
		    } 
		});
		//ajastimen kuuntelija. jos ei tarvitse updatea(joku muu jo laittoi saman), ei tee mitään. muuten laittaa päivitysviestin.
		  taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          if(!needToUpdate){
		        	  timer.stop();
		        	  return;
		          }
		    	  try {
					sender.sendPaivitys();
				} catch (IOException e) {e.printStackTrace();}
		      }
		  };
		
	}
	//arpomiseen.
	private int generoiRandom(int low, int high){
		Random r1 = new Random();;
		return r1.nextInt(high-low)+low;
		
	}
	
	
	//vastaanottaja-luokka. mallia otettu netistä ja muokattu sen pohjalta.
	private class receiver extends SwingWorker<Void, String>{

         // taustalla pyörivä viestien vastaanotto-metodi. Liitytään gruuppiin ja jäädään silmukkaan kunnes
		// funktio cancelloidaan.
	    @Override
		public Void doInBackground() {
			MulticastChatSocket s = null;
			InetAddress osoite = null;
			try {
				s = new MulticastChatSocket(Integer.parseInt(textFieldPort.getText()));
			    osoite = InetAddress.getByName(textFieldMulticastO.getText());
			    s.joinGroup(osoite);
			    while(!isCancelled()){
			    String a = s.receive();
			    publish(a);
			    }
			}
			catch(Exception e){}
			finally {
		        if(s!=null) try{
		        	s.leaveGroup(osoite);
		        	}
		        catch(Exception e){e.printStackTrace();}
		        s.close();
		        s= null;
		      }
			return null;	
	    }
	    //laitetaan viesti tekstikenttään.
	    @Override
		protected void process(List<String> viestit){
	    	for(String viesti: viestit){
	    		//Päätin käyttää : merkkiä splittaamiseen viestissä. itse multicastchatsocketti 
	    		// asettaa viestin alun joko /join: , /leave: tai USERS:, joten sen avulla valikoidaan mitä tehdään.
	    		//jos joku muu joinaa kuin itse, aloitetaan ajastin jäsenviestin lähettämisestä.
	    		String[] parts = viesti.split(":");
	    		if( parts[0].equals("/join")){
	    			textAreaChat.append(parts[1]+" liittyi keskusteluun. \n");
	    			if(parts[1].equals(sender.username))break;	    			
	    			textAreaUsers.append(parts[1]+"\n");
	    			needToUpdate = true;
	    			int aika = generoiRandom(1000, 5000);
	    			timer = new Timer(aika, taskPerformer);
					timer.start();
	    		}
	    		//poistetaan listasta lähtiessä.
	    		else if(parts[0].equals("/leave")){
	    			 lines = textAreaUsers.getText().split("\n");
	    			 textAreaUsers.setText("");
	    			 for(int i = 0 ; i< lines.length; i++)
	    		            if(lines[i].equals(parts[1])){}
	    		            else{
	    		            	textAreaUsers.append(lines[i]+"\n");
	    		            }
	    			 textAreaChat.append(parts[1]+ " lähti keskustelusta. \n");
	    		}
	    		//jäsenviestin luku. Laitetaan jäsenet kenttään ja jos jo tullut toiselta, suljetaan oma ajastin.
	    		// jos on muita kuin oma nimi users-kentässä, ei lisätä muita näkyville.
	    		else if(parts[0].equals("USERS")){
	    			needToUpdate = false;
	    			lines = textAreaUsers.getText().split("\n");
	    			
	    			if(lines.length == 1){
	    			textAreaUsers.setText("");
	    			textAreaUsers.append(sender.username+"\n");
	    			for(int i=1; i<parts.length; i++){
	    				if(!parts[i].equals(sender.username))
	    				textAreaUsers.append(parts[i]+"\n");
	    			}
	    			}
	    			if(timer != null)timer.stop();
	    		}
	    		else{
	    		textAreaChat.append(viesti+"\n");
	    		}
	    	}
	    }
	}
	
	//lähettäjäluokka. 
	  private class Sender {
 // tarvittavat attribuutit. ikä, portti, nimi, multicast-osoite, clientin nimi.
		    private String username = null;
		    private DatagramSocket socket = null;
		    private InetAddress address = null;
            private int day= 18;
            private int month = 12;
            private int year = 1992;
            private String client = "AnttiTIEA";
            private int portti = 1;
            
            //konstruktori. asettaa tarvittavat muuttujat ja laittaa /join viestin toisille asiakkaille. 
		    Sender(String username, int d, int m, int y, String osoite, int po) throws SocketException, UnknownHostException {
		      this.username = username;
		      this.day = d;
		      this.month = m;
		      this.year = y;
		      this.socket = new DatagramSocket();
		      this.address = InetAddress.getByName(osoite);
		      this.portti = po;
		      try {
				send("/join", 1);
			} catch (IOException e) {e.printStackTrace();}
		    }
            //lähetysfunktio. viesti ja tyyppi, eli 1-join, 2-leave, 3-viesti.
		    public void send(String message, int type) throws IOException {
              if(type == 1){
              textAreaUsers.append(username+"\n");
              }
		      int muisti = 0;
		      byte[] cnimi = client.getBytes();
		      byte[] uname = username.getBytes();
              byte[] bytes = message.getBytes();
              //bytetaulukon koon laskeminen, ettei turhaan tule liian pitkää.
              byte[] sendi = new byte[7+ cnimi.length+uname.length+bytes.length];
              
              //pakataan ekaan bittiin shiftauksella protokollaversio ja tyyppi.
		      sendi[0] = (byte)((2<<4) | (type));
		      
		      //tokaan bittiin iän päivä shiftauksella ja 3/4 kuukaudesta.
		      sendi[1] = (byte)((this.day << 3)|(this.month>>>1)); 
		      
		      //kolmanteen viimeinen osa kuukautta ja suurin osa vuodesta. 
		      sendi[2] = (byte)((this.month<<7 ) | (this.year>>>4));
		      
		      //loput vuodesta
		      sendi[3] = (byte)((this.year << 4));
		      
		      //ennen clientin nimen sisällytystä viestiin sen nimen pituus tähän bittiin.
		      sendi[4] = (byte)(client.length());
		      for(int i = 5; i<cnimi.length+5;i++){
            	  sendi[i] = cnimi[i-5];
            	  muisti = i;
              }
		      muisti++;
		      //clientnimen jälkeen usernamen pituus ja sen jälkeen itse username.
              sendi[muisti] = (byte)(username.length());
              muisti++;
              for(int i = muisti; i<uname.length+muisti; i++){
              sendi[i]=uname[i-muisti];
              }
              muisti += uname.length;
              //viestin pituus usernamen jälkeiseen bittiin ja sen jälkeen itse viesti.
              sendi[muisti] = (byte)(message.length());
              muisti++;
              for(int i = muisti; i<bytes.length+muisti; i++){
            	  sendi[i] = bytes[i-muisti]; 	  
              }
              //laitetaan koko paketti menemään.
		      DatagramPacket packet = new DatagramPacket(sendi, sendi.length, this.address, this.portti);
		      this.socket.send(packet);		      
		       }
		      //jäsenviestin laittaminen. lasketaan paketin koko, ja muodostetaan se 
		      //tavalla jasennimipituus, jasennimi, jasennimi2pituus, jasennimi2.
		     public void sendPaivitys() throws IOException {
	 			 
		    	 lines = textAreaUsers.getText().split("\n");
	 			 int koko = 2;
	 			 for(int i = 0; i<lines.length;i++){
	 				 koko++;
	 				 byte[] apu = lines[i].getBytes();
	 				 koko+=apu.length;
	 			 }
	 			 byte[] sendi = new byte[koko];
		         sendi[0] = (byte)((2<<4) | 4);
	 			 sendi[1] = (byte)(koko-2);
	 			 int apu = 2;
	 			 for(int i = 0 ; i< lines.length; i++){
 				   sendi[apu]=(byte)(lines[i].length());
                   apu++;
                   byte[] nimi = lines[i].getBytes();
                   for(int x = 0; x< nimi.length; x++){
                	   sendi[apu]=nimi[x];
                	   apu++;
                   }
 			 }
		      DatagramPacket packet = new DatagramPacket(sendi, sendi.length, this.address, this.portti);
		      this.socket.send(packet);		      
		       }
		    
		  }
}
