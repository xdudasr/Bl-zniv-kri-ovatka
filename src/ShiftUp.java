/**
 * dostane pole rodica a vygeneruje vsetky pohyby hore ktore pojdu bud o 0 1 2 3 4
 * @author Robert
 *
 */



public  class ShiftUp implements Shift {
    public Node Stav(int[][] auta,int poc,int index,Node nod ,int hlbka)
    {   String slovo=("auto "+auta[index][0]+" hore o "+poc);
        auta[index][2]=auta[index][2]-poc;
        return  stav.vytvordieta(auta, nod,slovo,hlbka);
    }
}


