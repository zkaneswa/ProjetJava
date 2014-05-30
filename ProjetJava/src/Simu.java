import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.*;

public class Simu {
	
		public final static int X_MAX= 100;
	    public final static int Y_MAX= 100;
	    public final static float WIDTH= 0.8f;
	    
	    public static void main (String [] args){
	    	
	    	boolean move; 
	    	
	    	//Menu ecran titre
    		boolean choice=false;
    		int nbJoueurs=0;
    		int posArray = 60;
		    
	    	// La grille
	        StdDraw.setXscale(-WIDTH, X_MAX+WIDTH);
	        StdDraw.setYscale(-WIDTH, Y_MAX+WIDTH);
	        
	        while(choice!=true){
    			//Logo
    			StdDraw.picture(50, 85, "269526.gif",60,35);
    			
    			StdDraw.text(50, 70, "Choisissez le nombre de joueurs avec les flches Haut ou Bas.");
    			StdDraw.text(50, 60, "1 joueur");
	    		StdDraw.text(50, 50, "2 joueurs");
	    		StdDraw.text(50, 40, "3 joueurs");
	    		StdDraw.text(50, 30, "Validez avec la touche EntrŽe pour jouer.");
	    		
	    		//Fleche de choix
	    		StdDraw.picture(30, posArray, "Array.jpg.png",10,10);
	    		
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
	    		}
	    		
	    		//Limites fleche
	    		if (posArray>60){
	    			posArray=60;
	    		}
	    		if (posArray<40){
	    			posArray=40;
	    		}
	    		
	    		StdDraw.clear(StdDraw.WHITE);	
    		}
	        
	        //nb joueurs pour le timer
	        final int nbJoueursCopie=nbJoueurs;
	        
	        int[] collide=new int[nbJoueurs]; 
		    int rdm=0;

	    	// Les vaisseaux
	    	final Vaisseau[] v = new Vaisseau[nbJoueurs];
	    	for (int i=0;i<nbJoueurs;i++){
	    		v[i] = new Vaisseau(X_MAX/2,Y_MAX/(2+i),WIDTH*4.5,0.7,i);
	    	}
	    	
	    	// Tunnel
	    	Tunnel t = new Tunnel();

	    	// Acceleration (en m/s/s)
	    	double ax = 0;
	    	double ay = -20;
	    	
	    	// Intervalle de temps (en s)
	    	double delta = 0.02;
	    	
	    	//MAJ du score tous les quarts de seconde
            TimerTask task = new TimerTask(){
    			public void run(){
    				for (int i=0;i<nbJoueursCopie;i++)
    					v[i].score+=v[i].px;
    			}	
    		};
    		Timer timer = new Timer();
    		timer.scheduleAtFixedRate(task, 0, 250);
    		
    		
    		
    		//On joue tant qu'il reste un joueur vivant
	    	do{
	    		// RAZ des collisions
    			for(int i=0;i<nbJoueurs;i++)
	            	collide[i]=0; 
            	
    			//Si joueur en vie il se dŽplace
	            for (int i=0;i<nbJoueurs;i++){
	            	if (v[i].exist==1)
	            		v[i].move(ax,ay,delta,X_MAX, Y_MAX,i);
	            }
            	
	            //Fond
            	StdDraw.clear(StdDraw.WHITE);
	            
            	//Affichage tunnel
            	t.tunnel();
            	t.afficheTunnel();
            	t.decale(); 
            	
            	//Vitesse de defilement tunnel
            	rdm=StdRandom.uniform(100);
            	if (rdm > 60){
            		t.decale();
            		t.decale();
            		t.decale();
            		t.decale();
            		t.decale();
            		t.decale();
            	}
            	
            	//Collisions avec tunnel
            	collide=Vaisseau.collisionTunnel (v,nbJoueurs);
            	for (int i=0; i<nbJoueurs; i++)
            		if (collide[i]==1 && v[i].energie > 0)
            			v[i].energie--;
            	
            	//Rebond entre vaisseaux
            	/*double dist = (v[1].px-v[0].px)* (v[1].px-v[0].px) +  (v[1].py-v[0].py)*(v[1].py-v[0].py);
            	if(dist <(v[0].rayon + v[1].rayon)*(v[0].rayon + v[1].rayon)){
            		if (v[0].py>v[1].py){
            			v[0].py+=2;
            			v[1].py-=2;
            		}else{
            			v[0].py-=2;
            			v[1].py+=2;
            		}
            		
            		if (v[0].px>v[1].px){
            			v[0].px+=2;
            			v[1].px-=2;
            		}else{
            			v[0].px-=2;
            			v[1].px+=2;
            		}
            	}*/

	            // Score + energie en noir
	            StdDraw.setPenColor(Color.black);
	            
            	//Energie
	            String [] e = new String [nbJoueurs];
	            for (int i=0;i<nbJoueurs;i++){
	            	e [i] = "Energie joueur "+ (i+1) +" : " +String.valueOf(v[i].energie);
	            	StdDraw.text(30,95-i*5,e[i]);
	            }
            	
			    //Score
	            String [] s = new String [nbJoueurs];
	            for (int i=0;i<nbJoueurs;i++){
	            	s [i] = "Score joueur "+ (i+1) +" : " +String.valueOf(v[i].score);
	            	StdDraw.text(80,95-i*5,s[i]);
	            }
	            
	            //Verif si au moins un joueur est en vie
	            move = false ;
	    		for (int i=0;i<nbJoueurs;i++){
	    			if (v[i].exist == 1)
	    				move = true;
	    		}
	            				    	
	            draw(v, (int)(1000*delta),nbJoueurs);
	    	}while (move);
	    	
	    	//Temps d'attente avant message de fin de partie
	    	try {
	    		Thread.sleep(1500);
	    	}
	    	catch(InterruptedException ex){
	    		Thread.currentThread().interrupt();
	    	}
	    		
	    	//Vainqueur
	    	StdDraw.clear(StdDraw.WHITE);
	    	int winner = 0;
	    	for (int i=0;i<nbJoueurs;i++){
	    		if (v[i].score>v[winner].score)
	    			winner=i;
	    	}
	    	String w = "Le vainqueur est le joueur "+String.valueOf(winner+1)+" avec comme score "+String.valueOf(v[winner].score);
	    	StdDraw.setPenColor(Color.black);
	    	StdDraw.text(50, 50, w);
	    	StdDraw.show(1000);
	    }  
	    
	    public static void draw(Vaisseau[] v, int time,int nbJoueur){
	    	for (int i=0;i<nbJoueur;i++){
	    		if (v[i].energie == 0){					//Verif si joueur vivant
	    			v[i].exist=0;
	    			v[i].px=0;							//Score arrte de s'incrŽmenter
	    		}else{
	    			v[i].draw(i);
	    		}
	    	}	
	        // display and pause
	        StdDraw.show(time);	
	    }   
	    
	    public static int nextArrow(){
			int res=-1;
			while (res == -1){
				if (StdDraw.isKeyPressed(KeyEvent.VK_UP)){
					res=1;
					while (StdDraw.isKeyPressed(KeyEvent.VK_UP)){
					}
				}else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
					res=2;
					while (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
					}
				}else if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){
					res=3;
					while (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){
					}
				}
			}
			return res;
		}
}
