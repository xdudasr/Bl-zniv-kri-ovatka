/**
 * dostane pole rodica a vygeneruje vsetky pohyby vlavo ktore pojdu bud o 0 1 2 3 4
 * @author Robert
 *
 */



public class ShiftLeft implements Shift {
    public Node Stav(int[][] auta,int poc,int index,Node nod,int hlbka )
    {   String slovo=("auto "+auta[index][0]+" vlavo o "+poc);
        auta[index][3]=(auta[index][3]-poc);
        return  stav.vytvordieta(auta, nod,slovo,hlbka);
    }
}

