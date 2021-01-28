package de.MissingNameException.Commands;

import java.util.Scanner;

import de.MissingNameException.ClientHandler;

public class HappyBirthday implements Command{

	private String commandName = "happybirthday";
	private String commandDescription = "Happy Birthday 2 U!";
	private String commandPermission = "nina"; // change if commands needs a permission!
	private String[] commandAlias = {"hb", "birthday", "nina"};
	
	private int counter;
    private char posn[]=new char[10];
    private char player;
	
	
	@Override
	public void crun(ClientHandler client,String...arg) {
		printCake();
		
		System.out.println("Ich wünsche dir alles gute zum geburtstag NINA!!! - 08.01.2021");
		System.out.println("Also befor ich dir verrate was dein genaues Geschenk ist musst du erst mich in einer runde TicTacToe besiegen ^.^");
		System.out.println("Also dann fangen wir mal an :D");
		System.out.println("\n\n\n");
		
		String ch;
        do{
            newBoard();
            play();
            System.out.println ("Willst du nochmal spielen? (Enter'yes')? ");
            Scanner in =new Scanner(System.in);
            ch=in.nextLine();
            //System.out.println("ch value is  "+ch);
        }while (ch.equals("yes"));
		printEnd();
		
		
	}
	
	public  void newBoard()
    {
        
        char posndef[] = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int i;
        counter = 0;
        player = 'N';
        for (i=1; i<10; i++) posn[i]=posndef[i];
        currentBoard();
        
        
    }
    public  String currentBoard()
    {
        System.out.println( "\n\n" );
        System.out.println(  "\n\n" );
        System.out.println(  "\n\n\t\t" + posn [1] + "   | " +posn [2]+ "  | " +posn [3]);
        System.out.println(  " \t\t    |    |   " );
        System.out.println(  " \t\t ___|____|___ " );
        System.out.println(  "\n\n\t\t" +posn [4]+ "   | " +posn [5]+ "  | " +posn [6]);
        System.out.println(  " \t\t    |    |   " );
        System.out.println(  " \t\t ___|____|___ " );
        System.out.println(  "\n\n\t\t" +posn [7]+ "   | " +posn [8]+ "  | " +posn [9]);
        System.out.println(  " \t\t    |    |   " );
        System.out.println(  " \t\t    |    |   " );
        System.out.println(  "\n\n" );
        return "currentBoard";
    }
    
    public  void play()
    {
        int spot;
        char blank = ' ';
        
        System.out.println(  "Spieler " + getPlayer() +" f�ngt an und ist der Buchstabe 'N'" );
        
        do {
            currentBoard();              // display current board
            
            System.out.println(  "\n\n Spieler " + getPlayer() +" w�hle eine Position." );
            
            boolean posTaken = true;
            while (posTaken) {
                // System.out.println( "position is taken, please enter a valid space");
                Scanner in =new Scanner (System.in);
                spot=in.nextInt();
                posTaken = checkPosn(spot);
                if(posTaken==false)
                posn[spot]=getPlayer();
            }
            
            System.out.println(  "Nice move." );
            
            currentBoard();              // display current board
            
            nextPlayer();
        }while ( checkWinner() == blank );
        
    }
    
    public  char checkWinner()
    {
        char Winner = ' ';
        
        // Check if X wins
        if (posn[1] == 'N' && posn[2] == 'N' && posn[3] == 'N') Winner = 'N';
        if (posn[4] == 'N' && posn[5] == 'N' && posn[6] == 'N') Winner = 'N';
        if (posn[7] == 'N' && posn[8] == 'N' && posn[9] == 'N') Winner = 'N';
        if (posn[1] == 'N' && posn[4] == 'N' && posn[7] == 'N') Winner = 'N';
        if (posn[2] == 'N' && posn[5] == 'N' && posn[8] == 'N') Winner = 'N';
        if (posn[3] == 'N' && posn[6] == 'N' && posn[9] == 'N') Winner = 'N';
        if (posn[1] == 'N' && posn[5] == 'N' && posn[9] == 'N') Winner = 'N';
        if (posn[3] == 'N' && posn[5] == 'N' && posn[7] == 'N') Winner = 'N';
        if (Winner == 'N' )
        {System.out.println("NINA DU HAST GEWONNEN!" );
            return Winner;
        }
        
        // Check if O wins
        if (posn[1] == 'M' && posn[2] == 'M' && posn[3] == 'M') Winner = 'M';
        if (posn[4] == 'M' && posn[5] == 'M' && posn[6] == 'M') Winner = 'M';
        if (posn[7] == 'M' && posn[8] == 'M' && posn[9] == 'M') Winner = 'M';
        if (posn[1] == 'M' && posn[4] == 'M' && posn[7] == 'M') Winner = 'M';
        if (posn[2] == 'M' && posn[5] == 'M' && posn[8] == 'M') Winner = 'M';
        if (posn[3] == 'M' && posn[6] == 'M' && posn[9] == 'M') Winner = 'M';
        if (posn[1] == 'M' && posn[5] == 'M' && posn[9] == 'M') Winner = 'M';
        if (posn[3] == 'M' && posn[5] == 'M' && posn[7] == 'M') Winner = 'M';
        if (Winner == 'M' )
        {
            System.out.println( "Schade sieht so aus als w�rden wir noch eine runde spielen." );
        return Winner; }
        
        // check for Tie
        for(int i=1;i<10;i++)
        {
            if(posn[i]=='N' || posn[i]=='M')
            {
                if(i==9)
                {
                    char Draw='D';
                    System.out.println(" Game is stalemate ");
                    return Draw;
                }
                continue;
            }
            else
            break;
            
        }
        
        return Winner;
    }
    
    public  boolean checkPosn(int spot)
    {
        
        if(spot > 10) {
        	System.out.println("Diese Nummer ist zu gro�!, Bitte w�hl eine andere!");
            return true;
        }
        if (posn[spot] == 'N' || posn[spot] == 'M')
        {
            System.out.println("Diese Position ist bereits belegt, Bitte w�hl eine andere!");
            return true;
        }
        else {
            return false;
        }
        
        //  counter++;
        //    return false;
    }
    
    
    
    public  void nextPlayer()
    {
        if (player == 'N')
        player = 'M';
        else player = 'N';
        
    }
    
    public String getTitle()
    {
        return "Tic Tac Toe" ;
    }
    
    public  char getPlayer()
    {
        return player;
    }
	
	private void printEnd() {
		String msg = "Da du es geschafft hast mich in TicTacToe zu schlagen und ich nun mal mein Versprechen halte, verrate ich dir nun was \n"
				+ "dein Geburtstag Geschenk ist ^.^            Also ................... wie du eventuell schon gemerkt hast hat das ganze Mini Game \n"
				+ "in meinem Projekt JADA stattgefunden, des hat auch einen bestimmten Grund wieso, aber dies spreche ich gleich nochmal an. Jedoch wollte \n"
				+ "ich mich vorerst mich bei dir bedanken da du eine gro�artige Person bist, mit der man immer Reden kann, die immer da ist, wenn man sie \n"
				+ "braucht oder einen auch mal daran erinnert sich mal endlich einen Job zu suchen ^^. Wie gesagt wollte ich mich nochmal bei dir bedanken \n"
				+ "da du eine gro�artige Person bist und mir mittlerweile sehr wichtig geworden bist. Naja, ich will dich auch nicht mit meinen Emotionen \n"
				+ "�bersch�tten oder sonstiges. Ok, wie dem auch sei, dann kommen wir doch mal zum eigentlichen. Dein Geburtstags Geschenk ist \n"
				+ "(TROMMEL WIRBEL!!!), dass alles was du hier siehst, beziehungsweise diese Nachrichten sowie als auch das kleine Mini Game :D. Und um die \n"
				+ "Kirche auf der torte nat�rlich nicht zu vergessen, habe ich das Ganze in mein System mit eingebaut (bzw. verewigt) ab sofort bleibt dieses \n"
				+ "Geschenk in meinem System als eine Art EASTER EGG k�nnte man schon sagen. Diesen Befehl, um das ganze wieder aufzurufen, kennen nur du und \n"
				+ "ich! Ansonsten wird dieser Befehl nirgends stehen oder sonstiges! Dazu kommt noch das nur bestimmte Personen zugriff auf diesen Befehl haben \n"
				+ "xunter anderem auch du. Du kriegst das Ganze auch noch separat als ausf�hrbare Datei (Programm) so als ein kleines Erinnerung St�ck.";
		for(int i = 0; i < msg.length(); i++) {
			System.out.print(msg.charAt(i));
			try {
				Thread.sleep(150);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	private static void printCake() {
    	System.out.println("                   ������");
    	System.out.println("             ����  ��  �� ������");
    	System.out.println("     ��  �� ��  �� ������ ��  �� ��    ��");
    	System.out.println("     ��  �� ������ ��     ������  ��  ��");
    	System.out.println("     ������ ��  �� ��     ��       ����");
    	System.out.println("     ��  �� ��  ��        ��        ��");
    	System.out.println("     ��  ��                         ��");
    	System.out.println("");
    	System.out.println("       ������   ��  �����  ������ ��  ��");
    	System.out.println("       ��   ��  ��  ��  ��   ��   ��  ��");
    	System.out.println("       ������   ��  �����    ��   ������");
    	System.out.println("       ��   ��  ��  �� ��    ��   ��  ��");
    	System.out.println("       ������   ��  ��  ��   ��   ��  ��");
    	System.out.println("");
    	System.out.println("          �������          ��    ��");
    	System.out.println("           ��   ��   �����  ��  ��");
    	System.out.println("           ��   ��  ��   ��  ����");
    	System.out.println("           ��   ��  �������   ��");
    	System.out.println("          �������   ��   ��   ��");
    	System.out.println("                    ��   ��");
    	System.out.println("");
    	System.out.println("       ���   ��  ��  ���   ��   �����");
    	System.out.println("       ����  ��  ��  ����  ��  ��   ��");
    	System.out.println("       �� �� ��  ��  �� �� ��  �������");
    	System.out.println("       ��  ����  ��  ��  ����  ��   ��");
    	System.out.println("       ��   ���  ��  ��   ���  ��   ��");
    	System.out.println("");
    	System.out.println("                ��");
    	System.out.println("               ����               ��");
    	System.out.println("              ��S��      ��     ����");
    	System.out.println("             ��SS�     ����    ��SS�");
    	System.out.println("             ��S��    ��SS�   ��SS��");
    	System.out.println("              ��      �SS�     �SS�");
    	System.out.println("             �����     ��       ��");
    	System.out.println("             �  ��    �����    �����");
    	System.out.println("             �  ��    �  ��    �  ��");
    	System.out.println("             �  �     �  ��    �  ��");
    	System.out.println("             �  �     �  ��    �  �");
    	System.out.println("             �  �     �  �     �  �");
    	System.out.println("           ���  �������  �������  �");
    	System.out.println("        ������  �11111�  �11111�  ����");
    	System.out.println("      ���1111�  �11111�  �11111�  �11����");
    	System.out.println("     ��111$11����11111�  �11111����11$11���");
    	System.out.println("    ��111$$$$11111$111����11$1111111$$$$11��");
    	System.out.println("    ����111$11111$$$$111111$$$$1111111$11���");
    	System.out.println("    �  ��������1111$111111111$1111111����� �");
    	System.out.println("   ��      ���������������������������    ���");
    	System.out.println(" ����     ��                        ��  ��11��");
    	System.out.println("��11��  �� ��   ��     ��    ��   �� �� ��111�");
    	System.out.println("�111���   ��   �� ��  �� �� �� ��   �� ���111�");
    	System.out.println("�1111����        ��     ��    ��     ����111��");
    	System.out.println("����111�����������        ��������������11����");
    	System.out.println("  �����11111111�������������������11111111���");
    	System.out.println("    ���11111111111111111111111111111111111��");
    	System.out.println("     ����111����11111111��11111111����1111��");
    	System.out.println("      �����������111�������1111��� �������");
    	System.out.println("               ��������   ��������");
    	System.out.println("");
    	System.out.println("");
    }

	@Override
	public String getCommandName() {return commandName;}

	@Override
	public String getCommandDescription() {return commandDescription;}

	@Override
	public String getCommandPermission() {return commandPermission;}
	
	@Override
	public String[] getCommandAlias() {return commandAlias;}

}
