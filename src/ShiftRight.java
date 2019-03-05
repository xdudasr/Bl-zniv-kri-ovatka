/**
 * dostane pole rodica a vygeneruje vsetky pohyby dolu ktore pojdu bud o 0 1 2 3 4
 * p pri tomto posune musi program analyzovat aj dlzku auta
 * @author Robert
 *
 */



public  class ShiftRight implements Shift {
    public Node Stav(int[][] auta,int poc,int index,Node nod,int hlbka)
    {	String slovo=("auto "+auta[index][0]+" vpravo o "+poc);
        auta[index][3]=auta[index][3]+poc;
        return  stav.vytvordieta(auta, nod,slovo,hlbka);
    }
}



