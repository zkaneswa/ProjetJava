import java.awt.Color;
import java.awt.event.KeyEvent;

public class Menu {
	
	int nbJoueurs=0;
	boolean tryAgain = false;
	
	// Carac img logo
	public final static int POS_LOGO = 85;
	public final static int LOGO_SIZE_X = 65;
	public final static int LOGO_SIZE_Y = 35;
	
	// Position texte/image sur axe y
	public final static int POS_TITLE = 70;
	public final static int POS_LVL1 = 60;
	public final static int POS_CENTER = Simu.Y_MAX/2;
	public final static int POS_LVL2 = 40;
	public final static int POS_LVL3 = 30;
	public final static int POS_LVL4 = 20;
	// axe x
	public final static int POS_LVL1_X = 30;
	public final static int POS_LVL2_X = 80;
	
	// Carac img fleche
	public final static int POS_ARRAY_X = 30;
	public final static int ARRAY_SIZE = 10;

	public void principal(){
		
		boolean choice=false;
		boolean instruction=false;
		int posArray = POS_LVL1;
    
	    while(choice!=true){
			//Logo
	    	StdDraw.picture(POS_CENTER, POS_LOGO, "269526.gif",LOGO_SIZE_X,LOGO_SIZE_Y);
	    	
			StdDraw.text(POS_CENTER, POS_TITLE, "Choisissez le nombre de joueurs avec les fleches Haut ou Bas.");
			StdDraw.text(POS_CENTER, POS_LVL1, "1 joueur");
			StdDraw.text(POS_CENTER, POS_CENTER, "2 joueurs");
			StdDraw.text(POS_CENTER, POS_LVL2, "3 joueurs");
			StdDraw.text(POS_CENTER, POS_LVL3,"Instructions");
			StdDraw.text(POS_CENTER, POS_LVL4, "Validez avec la touche Entree.");
			
			//Fleche de choix
			StdDraw.picture(POS_ARRAY_X, posArray, "Array.jpg.png",ARRAY_SIZE,ARRAY_SIZE);
			StdDraw.show(10);
			
			//Recup input
			int input=nextArrow();
			if(input==1)				//Si touche Haut
				posArray+=10;
			if(input==2)				//Si touche Bas
				posArray-=10;
			if (input==3){				//Si touche Entree
				choice=true;
				if (posArray==POS_LVL1)
					nbJoueurs=1;
				if (posArray==POS_CENTER)
					nbJoueurs=2;
				if (posArray==POS_LVL2)
					nbJoueurs=3;
				if(posArray==POS_LVL3){
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
			if (posArray>POS_LVL1)
				posArray=POS_LVL1;
			
			if (posArray<POS_LVL3)
				posArray=POS_LVL3;
			
			StdDraw.clear(StdDraw.WHITE);
	    }	
	}
	
	// Retour au menu
	public void retourMenu(){
		StdDraw.setPenColor(Color.red);
		StdDraw.text(POS_CENTER, POS_LVL4, "Appuyez sur Entree pour retourner au menu principal.");
		StdDraw.show(100);
		
		int input1=nextArrow();
		if (input1==3)
			tryAgain=true;
	}
	
	// Recup la prochaine input
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
	 
	 // Affichage des instructions
	 public static void getInstruction(){
		//Logo
		StdDraw.picture(POS_CENTER, POS_LOGO, "269526.gif",LOGO_SIZE_X,LOGO_SIZE_Y);
		StdDraw.text(POS_CENTER,POS_TITLE,"Instructions");
		
		StdDraw.text(POS_CENTER, POS_LVL1, "Commandes joueur 1 : fleches Haut, Gauche, Bas, Droite");
		StdDraw.text(POS_CENTER, POS_CENTER, "Commandes joueur 2 : touches Z, Q, S, D");	
		StdDraw.text(POS_CENTER, POS_LVL2, "Commandes joueur 3 : touches U, H, J, K");
		StdDraw.setPenColor(Color.blue );	
		StdDraw.text(POS_CENTER, POS_LVL3, "En zone bleue, les commandes sont inversées.");
		
		for(int i=0; i<3; ++i)
			StdDraw.picture(5, 60-(10*i), "vaisseau"+(i+1)+".png", Vaisseau.VAISSEAU_SIZE_X,Vaisseau.VAISSEAU_SIZE_Y);
	 }
}
