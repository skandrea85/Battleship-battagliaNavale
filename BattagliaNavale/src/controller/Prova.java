package controller;

import java.util.Random;
import java.util.Scanner;

public class Prova {
	


	private static boolean  countNavi=false;
	
	static Random ra =new Random();
	
	static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		
		String[][] campoUs=new String[12][12];
		
		String[][] campoCom=new String[12][12];
		
		
		
		for(int i=0;i<campoCom.length;i++) {

			for(int j=0;j<campoCom[i].length;j++) {
				
				


				campoCom[i][j]="0";


				campoCom[0][j]="1";

				campoCom[i][0]="1";

				campoCom[i][11]="1";

				campoCom[11][j]="1";
				

			}

		}
		
		posizionaNaviComp(campoCom);
	
		
		
		for(String[] f:campoCom) {
			for(String s: f) {
				
				System.out.print(s);
			}
			System.out.println();
		}
		

		
		
//
//		campoUs[2][2]="x";
//		campoUs[3][2]="x";
//		campoUs[4][2]="x";
//		
//		
//		campoUs[2][7]="x";
//		campoUs[3][7]="x";
//		campoUs[4][7]="x";
//		
//		
//		campoUs[6][6]="x";
//		campoUs[6][7]="x";
//		campoUs[6][8]="x";
//		campoUs[6][9]="x";
//		
//		campoUs[8][2]="x";
//		campoUs[8][3]="x";
//		
//		campoUs[10][1]="x";
//		campoUs[10][2]="x";
//		campoUs[10][3]="x";
//		campoUs[10][4]="x";
//		campoUs[10][5]="x";
//		

		//trova(campoUs);
		
		
		
		//contColpiti(campoUs);
		
		
		do {
			
			sparaCom(campoUs);
			
			
			
			affNave(campoUs);
			
			finePartita(campoUs);
			
			for(int i=1;i<campoUs.length;i++) {
				for(int j=1;j<campoUs[i].length;j++) {


					System.out.print(campoUs[i][j]);

				}

				System.out.println();

			}
			
			sc.nextLine();
			
			System.out.println("premer esc per uscire ");
			
			
		}while(!countNavi);
		
		sc.close();




	}

	public static void  colpisciCard(String[][] a,int x, int y) {

		if(a[x][y].equals("x")) {

			System.out.println("Colpita!");
			a[x][y]="c";
		}

		else if(a[x][y].equals("0")) {
			a[x][y]="f";

			System.out.println("Mancato!");

		}

		else if(a[x][y].equals("f")) {


			System.out.println("Gi� colpito!");

		}

		else if(a[x][y].equals("1")) {

			System.out.println("out");
		}


	}	


	//se � presente una sola casella colpita di una nave vuol dire che nei punti cardinali della casella si sviluppera la nave 
	//devo controllare i punti cardinali della casella e colpire casualmente ognuna di loro fino a quando trover� lo sviluppo della nave
	public static void trovaCardinale(String[][] a,int i,int j) {

		int x=0;
		int y=0;

					int e=i;
					int e1=j-1;

					int n=i-1;
					int n1=j;

					int o=i;
					int o1=j+1;

					int s=i+1;
					int s1=j;
					//random scelta casuale

					//con un random scelgo di colpire una delle caselle cardinali con un ciclo controllo che non siano caselle vuote gi� colpite oppure fuori dal campo
					//nel caso si verifichi rieseguo la scelta casuale
					int sc=ra.nextInt(4);

					String con=null; 

					do {

						if(sc==0) {
							con=a[e][e1];

							x=e;
							y=e1;
						}

						else if(sc==1) {
							con=a[n][n1];
							x=n;
							y=n1;

						}
						else if(sc==2) {

							con=a[o][o1];
							x=o;
							y=o1;
						}


						else if(sc==3) {
							con=a[s][s1];

							x=s;
							y=s1;
						}

						sc=ra.nextInt(4);

					}
					while(con.equals("1")||con.equals("f"));

		//invio le coordinate della casella da colpire ad un metodo che effettetivamente colpira la casella scelta casulamente e valida

		colpisciCard(a ,x,y);

	}

	// il turno del compute parte da qui all'interno del metodo controllo se non c'� gia una nave coplita per contiunare a colpirla
	// se non � presente sparo un numero random
	public static void sparaCom(String[][] a) {
		
		if(!contColpiti(a)) {

		int x=ra.nextInt(11-1)+1;

		int y=ra.nextInt(11-1)+1;
		
		while(a[x][y].equals("f")) {
			
			 x=ra.nextInt(11-1)+1;

			 y=ra.nextInt(11-1)+1;
		}


			if(a[x][y].equals("x")) {

				System.out.println("Colpita! "+x+" "+y);
				a[x][y]="c";
			}

			else if(a[x][y].equals("0")) {
				a[x][y]="f";

				System.out.println("Mancato! "+x+" "+y);

			}

			//	else if(a[x][y].equals("f")) {
			//		
			//		
			//		System.out.println("Gi� colpito!");
			//		
			//	}



			else {

				System.out.println("valore non valido!");
			}
		}
	}
	
	
	public static boolean contColpiti(String[][] a) {
		int c=0;
		int x=0;
		int y=0;
		int i=0;
		int j=0;
		 boolean check=false;
		//controllo se ci sono gi� state navi colpite nel precedente turno se sono uguali ad uno o maggiori importante metodo dove si sviluppa
		 //la strategia di gioco del computer 
		for( i=1;i<11;i++) {
			for(j=1;j<11;j++) {
				
				if(a[i][j].equals("c")) {
					x=i;
					y=j;
					c++;

					}
				}
			}

		if(c>1) {//nel caso siano maggiori di uno vuol dire che ho gi� scoperto come si sviluppa la nave quindi non mi resta colpire random
			//o un estremo o un altro delle caselle colpite
			estrNave(a);
			check=true;
		}
			
		else if(c==1) {//nel caso sia c uguale ad uno  in uno dei quattro punti cardinali della casella colpita si sviluppa
			//il resto della nave devo trovatlo con un apposito metodo, in questo caso: trova cardinale ,ed invio gi� le coordinate della 
			//casella colpita
		
			trovaCardinale(a,x,y);
			
			check=true;
		}
			// torno un booleano per la verifica di eventuali navi colpite al precedente turno
		return check;

	}
	
	public static int[] conlNave(String[][] a) {
		
		int x=0;
		int y=0;
		int z=0;
		int n=0;
		
		String d="";

		for(int i=1;i<11;i++) {
			for(int j=1;j<11;j++) {
				if(a[i][j].equals("c")) {
					//					x=i;
					//					y=j;
//primi 
					if(a[i][j].equals(a[i][j+1])&&!a[i][j].equals(a[i][j-1])) {
						d="o";
						x=i;
						y=j-1;
					}

					else if(a[i][j].equals(a[i+1][j])&&!a[i][j].equals(a[i-1][j])) {

						d="v";
						x=i-1;
						y=j;
					}
					
					//ultimi 

					else if(!a[i][j].equals(a[i][j+1])&&d.equals("o")) {

						
						z=i;
						n=j+1;
					//	System.out.println("qui "+z+ n);
						
					}
					else if(!a[i][j].equals(a[i+1][j])&&d.equals("v")) {

						
						z=i+1;
						n=j;
					}

				}

			}

		}
		
		 int[] car= {x,y,z,n};
		 
		 return car;
		
		
	}

	public static void estrNave(String[][] a) {
		int[] car=conlNave(a);
		
		int	x=car[0];
		int y=car[1];
		int z=car[2];
		int n=car[3];
		
		int c=0;
		int c1=0;

		int ran=ra.nextInt(2);
		
		String col="";
		
		System.out.println("nb");
		
		do {
			
			if(ran==0) {
				col=a[x][y];
				c=x;
				c1=y;
				
			}else {
				col=a[z][n];
				c=z;
				c1=n;
				
			}
			ran=ra.nextInt(2);
		}
		while(col.equals("f")||col.equals("1"));
		System.out.println("no");
		colpisciCard(a ,c,c1);
	}
	
	public static void affNave(String[][] a) {
		
		int c=0;
	
		for(int i=1;i<11;i++) {
			for(int j=1;j<11;j++) {
				if(a[i][j].equals("c")) {					
					c++;
					

				}
			
			}
		}
		
		
		if(c>1) {
		
	int[] car=conlNave(a);
		
		int	x=car[0];
		int y=car[1];
		int z=car[2];
		int n=car[3];
		
	
		
		//System.out.println(a[x][y]+a[z][n]);
		
		if(!a[x][y].equals("x")&&!a[z][n].equals("x")) {
			System.out.println("nave affondata!!");
			for(int i=1;i<11;i++) {
				for(int j=1;j<11;j++) {
					if(a[i][j].equals("c")) {
						
						
						a[i][j]="f";
					}
				}
			}
		

		}
		
		
	}
	}
	
	public static  void finePartita(String[][] a) {

	
		int x=0;
		for(int i=1;i<11;i++) {
			for(int j=1;j<11;j++) {
				

				
				if(a[i][j].equals("x")) {
					
					x++;
				}
			}
		}
		if(x==0) {
			
			 countNavi =true;
		}
		
		
		

		
	}
	public static String[][] posizionaNaviComp(String[][] oc ) {

		//metodo per il posizionamento random delle navi del computer 
		//

		String orient="";
		int p=ra.nextInt(2);
//		con questo codice genero un random per stabilire la posizione della nave orizz. o verticale
		if(p==1) {

			orient="o";
		}else {

			orient="v";
		}


		int x=0;

		int y=0;

			if(orient.equals("o")) {
			x=	ra.nextInt(11-1)+1;
			y=	ra.nextInt(9-1)+1;//genero un numero da zero ad a 7 altrimenti se maggiore  sforer� sull'asse y visto che la nave � di tre caselle e il campo10x10
			//con questo codice genero le coordinate random della prima casella della nave che dar� seguito al resto
			//nel caso sia gi� presente una nave in quelle caselle ripeto il random finch� trovo spazi liberi
				while(oc[x][y]!="0"||oc[x][y+1]!="0"||oc[x][y+2]!="0") {

					x=ra.nextInt(11-1)+1;

					y=ra.nextInt(9-1)+1;

				}


				oc[x][y]="i";
				oc[x][y+1]="i";
				oc[x][y+2]="i";
			}else {
				x=ra.nextInt(9-1)+1;
				y=ra.nextInt(11-1)+1;

				while(oc[x][y]!="0"||oc[x+1][y]!="0"||oc[x+2][y]!="0") {
					x=ra.nextInt(9-1)+1;

					y=ra.nextInt(11-1)+1;

				}

				oc[x][y]="i";
				oc[x+1][y]="i";
				oc[x+2][y]="i";

			}
		
		// coordinata nave sottomarino
		//orientamneto
		p=ra.nextInt(2);

		if(p==1) {

			orient="o";
		}else {

			orient="v";
		}

			if(orient.equals("o")) {
				
				x=ra.nextInt(11-1)+1;

				y=ra.nextInt(9-1)+1;
				while(oc[x][y]!="0"||oc[x][y+1]!="0"||oc[x][y+2]!="0") {

					x=ra.nextInt(11-1)+1;

					y=ra.nextInt(9-1)+1;

				}


				oc[x][y]="s";
				oc[x][y+1]="s";
				oc[x][y+2]="s";
			}else {
				x=ra.nextInt(9-1)+1;

				y=ra.nextInt(11-1)+1;

				while(oc[x][y]!="0"||oc[x+1][y]!="0"||oc[x+2][y]!="0") {
					x=ra.nextInt(9-1)+1;

					y=ra.nextInt(11-1)+1;

				}

				oc[x][y]="s";
				oc[x+1][y]="s";
				oc[x+2][y]="s";

			}

		//coordinate nave da'assalto 
		//orientamento

		p=ra.nextInt(2);

		if(p==1) {

			orient="o";
		}else {

			orient="v";
		}



		
			if(orient.equals("o")) {
				
				//genero coordinate random

				x=ra.nextInt(11-1)+1;

				y=ra.nextInt(10-1)+1;
				while(oc[x][y]!="0"||oc[x][y+1]!="0") {

					x=ra.nextInt(11-1)+1;

					y=ra.nextInt(10-1)+1;

				}


				oc[x][y]="a";
				oc[x][y+1]="a";

			}else {
				//coordinate

				x=ra.nextInt(10-1)+1;

				y=ra.nextInt(11-1)+1;

				while(oc[x+0][y]!="0"||oc[x+1][y]!="0") {
					x=ra.nextInt(10-1)+1;

					y=ra.nextInt(11-1)+1;

				}

				oc[x][y]="a";
				oc[x+1][y]="a";

			}
		

		//coordinate nave corazzata 4 caselle
		//genero oriantamento nave vertical o orizzontale

		p=ra.nextInt(2);

		if(p==1) {

			orient="o";
		}else {

			orient="v";
		}
	
			if(orient.equals("o")) {
				//genero coordinate random
				x=ra.nextInt(10)+1;

				y=ra.nextInt(7)+1;
				//controllo se sono presnti navi altrimento genero altre coordinate random
				while(oc[x][y]!="0"||oc[x][y+1]!="0"||oc[x][y+2]!="0"||oc[x][y+3]!="0") {
					x=ra.nextInt(10)+1;

					y=ra.nextInt(7)+1;
				}


				oc[x][y]="z";
				oc[x][y+1]="z";
				oc[x][y+2]="z";
				oc[x][y+3]="z";
			}else {
				
				//genero coordinate random
				x=ra.nextInt(7)+1;

				y=ra.nextInt(10)+1;

				while(oc[x+0][y]!="0"||oc[x+1][y]!="0"||oc[x+2][y]!="0"||oc[x+3][y]!="0") {
					x=ra.nextInt(7)+1;

					y=ra.nextInt(10)+1;


				}

				oc[x][y]="z";
				oc[x+1][y]="z";
				oc[x+2][y]="z";
				oc[x+3][y]="z";

			}

		//coordinate nave portaerei
		//orientamento

		p=ra.nextInt(2);

		if(p==1) {

			orient="o";
		}else {

			orient="v";
		}

		//coordinate
			if(orient.equals("o")) {
				
				x=ra.nextInt(10)+1;

				y=ra.nextInt(6)+1;
				while(oc[x][y]!="0"||oc[x][y+1]!="0"||oc[x][y+2]!="0"||oc[x][y+3]!="0"||oc[x][y+4]!="0") {

					x=ra.nextInt(10)+1;

					y=ra.nextInt(6)+1;

				}

				oc[x][y]="p";
				oc[x][y+1]="p";
				oc[x][y+2]="p";
				oc[x][y+3]="p";
				oc[x][y+4]="p";
			}else {
				
				x=ra.nextInt(6)+1;
				y=ra.nextInt(10)+1;

				while(oc[x+0][y]!="0"||oc[x+1][y]!="0"||oc[x+2][y]!="0"||oc[x+3][y]!="0"||oc[x+4][y]!="0") {
					x=ra.nextInt(6)+1;
					y=ra.nextInt(10)+1;

				}

				oc[x][y]="p";
				oc[x+1][y]="p";
				oc[x+2][y]="p";
				oc[x+3][y]="p";
				oc[x+4][y]="p";

			}
						
		return oc;

	}

	
	
}

	