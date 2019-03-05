import java.util.Stack;

public class Crazy_Crossing {
    static Stack lifo = new Stack();
    //c=1
    //empty=0
    //por.cislo,velkost,y,x,orientacia====v=0,h=1

    static int[][]auta =
            {
                    {1,2,3,2,1},
                    {2,3,6,3,1},
                    {3,2,1,1,1},
                    {4,3,2,1,0},
                    {5,3,2,4,0},
                    {6,2,5,5,1},
                    {7,3,1,6,0},
                    {8,2,5,1,0}
            };
    /**
     * vytvorenie korena--hlavny uzol s hlbkou 0 vstupnym polom auta[][]
     * @param args
     */
    public static void main(String[] args) {
        Node uz = new Node();
        uz.setH(0);
        uz.setposun("start");
        uz.setPole(auta);
        uz.setRod(null);
        State stav= new State();
        lifo.push(uz);
        stav.algoritmus(lifo);
    }
}
