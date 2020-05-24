import java.util.Scanner;
public class MinMax{
    static Scanner s = new Scanner(System.in);
    static int turno , maquina = 1, jugador = 2;
    static Node n;
    static int[][]tablero = {
        {0,0,0},
        {0,0,0},
        {0,0,0}
    };
    public static void main(String [] args)
    {
        turno = maquina;
        mostrarTablero();
        for(int i=0 ; i<9 ; i++)
        {
            if(verificaGanador() != -1)
            {
                switch(verificaGanador())
                {
                    case(1):System.out.println("La maquina Gana");break;
                    case(2):System.out.println("Usted gana");break;
                    case(0):System.out.println("Empate");break;
                }
                break;
            }
            jugarTurno();
        }
        mostrarTablero();
    }
    public static void jugarTurno()
    {
        if(turno == maquina)
        {
            n = new Node(tablero , maquina);
            n.generar();
            n.hacerJugada();
            turno = jugador;
            System.out.println("La maquina juega..");
        }
        else
        {
            System.out.println("Ingrese la fila y columna");
            int a = s.nextInt();
            int b = s.nextInt();    
            tablero[a-1][b-1] = jugador;
            turno = maquina;
            System.out.println("Usted juega..");
        }
        mostrarTablero();
    }
    public static void mostrarTablero()
    {
        for(int i=0 ; i<3 ; i++)
        {
            for(int j=0 ; j<3 ; j++)
                System.out.print(tablero[i][j]+"\t");
            System.out.println();
        }
    }
    public static int verificaGanador()
    {
        for(int i=0 ; i<3 ; i++)
            if(tablero[i][0]==tablero[i][1] && tablero[i][1]==tablero[i][2])
                switch(tablero[i][0])
                {
                    case(1):return maquina;
                    case(2):return jugador;
                    case(0):return -1;
                }
        for(int i=0 ; i<3 ; i++)
            if(tablero[0][i]==tablero[1][i] && tablero[1][i]==tablero[2][i])
                switch(tablero[0][i]){
                    case(1):return maquina;
                    case(2):return jugador;
                    case(0):return -1;
                }
        if(tablero[0][0]==tablero[1][1] && tablero[1][1]==tablero[2][2])
            switch(tablero[0][0]){
                case(1):return maquina;
                case(2):return jugador;
                case(0):return -1;
            }
        if(tablero[0][2]==tablero[1][1] && tablero[1][1]==tablero[2][0])
            switch(tablero[0][2]){
                case(1):return maquina;
                case(2):return jugador;
                case(0):return -1;
            }
        if(tablero[0][0]!=0 && tablero[0][1]!=0 && tablero[0][2]!=0 && tablero[1][0]!=0 && tablero[1][1]!=0 && tablero[1][2]!=0 && tablero[2][0]!=0 && tablero[2][1]!=0 && tablero[2][2]!=0)   
            return 0;

        return -1;
    }
}