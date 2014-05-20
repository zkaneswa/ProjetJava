import java.awt.Color;
public class Tunnel {
	
	static int tunnelHaut[] = new int [Simu.X_MAX];
	static int tunnelBas[]= new int [Simu.X_MAX];
	
	Tunnel(){
		//Initialisation
		for (int i=0;i<Simu.X_MAX;i++){
			tunnelHaut[i]=70;
			tunnelBas[i]=30;
		}
	}
	
	public static int[] remplirTab(int[]tunnel){
		for (int i=0;i<tunnel.length;i++){
			tunnel[i]=StdRandom.uniform(10);
		}
		return tunnel;
	}
	
	public String toString(){
		String s = "";
		for (int i=0;i<tunnelHaut.length-1;i++){
			s=s+tunnelHaut[i];
		}
		return s;
	}
	
	public void drawTunnel(int tunnel[]){
		remplirTab(tunnelHaut);
		for (int i=0;i<Simu.X_MAX-1;i++){
			int y = tunnelHaut[i];
			int z = tunnelHaut[i+1];
			StdDraw.setPenColor(Color.blue);
			StdDraw.line(i,y,i+1,z);
		}
	}
	
	public static void main (String [] args){
		Tunnel t = new Tunnel();
		t.toString();
		System.out.print(remplirTab(tunnelHaut));
	}
	
}