public class Node
{
    int[][]tablero;
    int turno;
    Node[]hijos;
    public Node(int[][]tablero , int turno)
    {
        this.tablero = tablero;
        this.turno = turno;
    }
    public void generar()
    {
        this.hijos = new Node[getVacios(this.tablero)];
        for(int i=0 ; i<hijos.length ; i++)
            for(int j=0 ; j<3 ; j++)
                for(int k=0 ; k<3 ; k++)
                    if(tablero[j][k] == 0)
                    {
                        int[][]tableroPivote = copiarTablero(tablero);
                        tableroPivote[j][k] = this.turno;
                        hijos[i] = new Node(tableroPivote , turno==MinMax.maquina?MinMax.jugador:MinMax.maquina);
                        hijos[i].generar();
                        i++;
                    }
    }
    public void hacerJugada()
    {
        int mejorValor = Integer.MIN_VALUE;
        int mejorIndice = 0;
        for(int i=0 ; i<hijos.length ; i++)
        {
            int valor = hijos[i].calcularValor(false);
            if(mejorValor < valor)
            {
                mejorIndice = i;
                mejorValor = valor;
            }
        }
        MinMax.tablero = this.hijos[mejorIndice].tablero;    
    }
    public int calcularValor(boolean maximizando)
    {
        int ganador = verificaGanador(this.tablero);
        if(ganador != -1 || hijos.length == 0)
            switch (ganador)
            {
                case(1):return 10;
                case(2):return -10;
                case(0):return 0;
            }
            //Bien Hasta aqui
        if(maximizando)
        {
            int mejorValor = Integer.MIN_VALUE;
            for(int i=0 ; i<this.hijos.length ; i++)
            {
                int valorPivote = hijos[i].calcularValor(false);
                mejorValor = Math.max(valorPivote , mejorValor);
            }
            return mejorValor;
        }
        else
        {
            int mejorValor = Integer.MAX_VALUE;
            for(int i=0 ; i<this.hijos.length ; i++)
            {
                int valorPivote = this.hijos[i].calcularValor(true);
                mejorValor = Math.min(valorPivote , mejorValor);
            }
            return mejorValor;
        }
    }   
    public static int getVacios(int[][]tablero)
    {
        int contador = 0;
        for(int i=0 ; i<3 ; i++)
            for(int j=0 ; j<3 ; j++)
                if(tablero[i][j]==0)
                    contador++;
        return contador;
    }
    public static int[][] copiarTablero(int[][]tablero)
    {
        int[][]pivote = new int[3][3];
        for(int i=0 ; i<3 ; i++)
            for(int j=0 ; j<3 ; j++)
                pivote[i][j] = tablero[i][j];
        return pivote;
    }
    public static int verificaGanador(int[][]tablero)
    {
        for(int i=0 ; i<3 ; i++)
            if(tablero[i][0]==tablero[i][1] && tablero[i][1]==tablero[i][2])
                switch(tablero[i][0])
                {
                    case(1):return MinMax.maquina;
                    case(2):return MinMax.jugador;
                    case(0):return -1;
                }
        for(int i=0 ; i<3 ; i++)
            if(tablero[0][i]==tablero[1][i] && tablero[1][i]==tablero[2][i])
                switch(tablero[0][i]){
                    case(1):return MinMax.maquina;
                    case(2):return MinMax.jugador;
                    case(0):return -1;
                }
        if(tablero[0][0]==tablero[1][1] && tablero[1][1]==tablero[2][2])
            switch(tablero[0][0]){
                case(1):return MinMax.maquina;
                case(2):return MinMax.jugador;
                case(0):return -1;
            }
        if(tablero[0][2]==tablero[1][1] && tablero[1][1]==tablero[2][0])
            switch(tablero[0][2]){
                case(1):return MinMax.maquina;
                case(2):return MinMax.jugador;
                case(0):return -1;
            }
        if(tablero[0][0]!=0 && tablero[0][1]!=0 && tablero[0][2]!=0 && tablero[1][0]!=0 && tablero[1][1]!=0 && tablero[1][2]!=0 && tablero[2][0]!=0 && tablero[2][1]!=0 && tablero[2][2]!=0)   
            return 0;
    
        return -1;
    }
}