import java.net.*;
import java.io.*;

public class MulticastChatSocket extends MulticastSocket  {
public String nimi = "";
	

	public MulticastChatSocket() throws SocketException, IOException {
	super();
}
public MulticastChatSocket(int portti) throws SocketException, IOException{
super(portti);
}

//yksinkertainen vastaanottofunktio. Ääkköset hiukan sekoittavat tätä, mutta huomasin saman virheen
//esimerkkiohjelmassa. Otetaan talteen [4]:ssa sijaitseva sovelluksen nimen pituus biteissä, plussataan
//siihen 5 niin saadaan käyttäjän nimen pituus, sitten samalla logiikalla talteen käyttäjänimi ja viestin 
//pituus. Lopuksi tarkistetaan onko viesti "leave" tai "join" ja jos on, vaihdetaan teksti sopivaksi.
// alussa toiminnasta poikkeava jäsenviestin handlaus.
public String receive() throws IOException{
   byte[] rec = new byte[1024];
   DatagramPacket p = new DatagramPacket(rec, rec.length);   
   super.receive(p);
   byte[] temp = p.getData();  
   // koska 00100100 = 36 in decimal, eli silloin kyseessä jäsentepäivitys-viesti. Poimitaan tiedot.
   if(temp[0] == 36){
	   String info="USERS:";
	   
	   for(int i =2; i<temp.length; i++){
		   String a = new String(temp, i+1, temp[i]);
		   i += temp[i];
		   if(a.equals(""))break;
		   info = info.concat(a+":");
	   }
	   return info;
   }
   else{
   int clientpituus=temp[4];
   int unamepituus= temp[5+clientpituus];
   int unamealku =  4+clientpituus+2;
   int viestipituus = temp[unamepituus+unamealku];
   int viestialku = unamepituus+unamealku+1;
   String a = new String(temp, viestialku, viestipituus);
   String s = new String(temp, unamealku, unamepituus);
  
   if(a.equals("/join")){
	   s=a.concat(":" +s);
   }
   else if(a.equals("/leave")){
	   s=a.concat(":" +s);
   }
   else{
   s = s.concat(": ");
   s = s.concat(a);
   }
   return s;
   }
}
}


