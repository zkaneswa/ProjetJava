import java.awt.Color;
import java.awt.event.KeyEvent;

public class Menu {
	
	int nbJoueurs=0;
	boolean tryAgain = false;
	

	public void principal(){
		
		boolean choice=false;
		int posArray = 60;
    
	    while(choice!=true){
			//Logo
	    	
			StdDraw.picture(50, 85, "269526.gif",60,35);
			StdDraw.text(50, 70, "Choisissez le nombre de joueurs avec les fleches Haut ou Bas.");
			StdDraw.text(50, 60, "1 joueur");
			StdDraw.text(50, 50, "2 joueurs");
			StdDraw.text(50, 40, "3 joueurs");
			StdDraw.text(50, 30,"Instructions");
			StdDraw.text(50, 20, "Validez avec la touche Entree pour jouer.");
			
			//Fleche de choix
			StdDraw.picture(30, posArray, "Array.jpg.png",10,10);
			StdDraw.show(10);
			
			boolean instruction=false;
			
			//Recup input
			int input=nextArrow();
			if(input==1)				//Si touche Haut
				posArray+=10;
			if(input==2)				//Si touche Bas
				posArray-=10;
			if (input==3){				//Si touche Entree
				choice=true;
				if (posArray==60)
					nbJoueurs=1;
				if (posArray==50)
					nbJoueurs=2;
				if (posArray==40)
					nbJoueurs=3;
				if(posArray==30){
					//Instruction
					StdDraw.clear();
					
					while(!instruction){
						
					getInstruction();
					retourMenu();
					StdDraw.clear();
			    	StdDraw.setPenColor(Color.black);
			    	instruction=true;
			    	principal();
					}
					
					}
			}
		
			//Limites fleche
			if (posArray>60)
				posArray=60;
			
			if (posArray<30)
				posArray=30;
			
			StdDraw.clear(StdDraw.WHITE);
	    }	
	}
	
	public void retourMenu(){
	
		StdDraw.setPenColor(Color.red);
		StdDraw.text(50, 20, "Appuyez sur Entree pour retourner au menu principal.");
		StdDraw.show(100);
		
		int input1=nextArrow();
		if (input1==3){
					tryAgain=true;
			
			}else{
				
			}
		
	}
	
	
	
	 public static int nextArrow(){
			int res=-1;
			while (res == -1){
				if (StdDraw.isKeyPressed(KeyEvent.VK_UP)){
					res=1;
					while (StdDraw.isKeyPressed(KeyEvent.VK_UP));
				}else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
					res=2;
					while (StdDraw.isKeyPressed(KeyEvent.VK_DOWN));
				}else if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){
					res=3;
					while (StdDraw.isKeyPressed(KeyEvent.VK_ENTER));
				}
			}
			return res;
	 }
	 
	 public static void getInstruction(){
		 
		 StdDraw.picture(50, 85, "269526.gif",60,35);
			StdDraw.text(50,70,"Instructions");
			
			StdDraw.text(50, 60, "Commande du joueur 1: flèche haut, gauche, bas, droite.");
			StdDraw.text(50, 50, "Commande du joueur 2: touche Z, Q, S, D.");	
			StdDraw.text(50, 40, "Commande du joueur 3: flèche U, H, J, K.");
			StdDraw.setPenColor(Color.blue );	
			StdDraw.text(50, 30, "Zone bleue: commande inversée.");
			
			for(int i=0; i<3; ++i){
				
			StdDraw.picture(10, 60-(10*i), "vaisseau"+(i+1)+".png", 40,30);
			
			}
			
			
		 
	 }
}
