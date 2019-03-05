import java.util.*;

public class State {

    Crazy_Crossing hlavny=new Crazy_Crossing();
    /**
     *
     * @param nod   nod je uzol z ktoreho bude funkcia generovanie generovat vsetky mozne posuny aut
     * @param hlbka tuto premennu si uzol pamata preto aby vedl ci moze ist dalej
     */
    public void  generovanie( Node nod,int hlbka){
        int [][]auta=nod.getPole();
        //mapka je pomocka na prehladavanie stavoveho priestoru ,a dokaze
        //jednoducho program zistit ktore auto a o kolko sa moze posunut
        // prva suradnica je y druha x
        int[][] mapka = new int[6][6];
        int pomx=0;
        int pomy=0;
        for (int i = 0; i < mapka.length ; i++){
            for (int l = 0; l < mapka.length ; l++){
                mapka[i][l]=0;
            }
        }
        for (int i = 0; i < auta.length ; i++){
            pomx=auta[i][2]-1;
            pomy=auta[i][3]-1;
            if (0==auta[i][4]){
                if(2==auta[i][1]){
                    mapka[pomx][pomy]=auta[i][0];
                    mapka[pomx+1][pomy]=auta[i][0];
                }
                else {
                    mapka[pomx][pomy]=auta[i][0];
                    mapka[pomx+1][pomy]=auta[i][0];
                    mapka[pomx+2][pomy]=auta[i][0];
                }
            }
            if (1==auta[i][4]){
                if(2==auta[i][1]){
                    mapka[pomx][pomy]=auta[i][0];
                    mapka[pomx][pomy+1]=auta[i][0];
                }
                else {
                    mapka[pomx][pomy]=auta[i][0];
                    mapka[pomx][pomy+1]=auta[i][0];
                    mapka[pomx][pomy+2]=auta[i][0];
                }
            }
        }
        //koniec generovania mapky
        //mapka je definovana ako [y][x]

        int pocL=0;
        int pocR=0;
        int pocU=0;
        int pocD=0;

        /**
         * generovanie noveho uzla treba prehladat mapku a zisti o kolko krokov sa moze posunut
         * auticko posunut
         */
        for (int i = 0; i < auta.length ; i++){
            int orienta=auta[i][4];
            pocL=0;
            pocR=0;
            pocU=0;
            pocD=0;
            int ypozicia=auta[i][2];
            int xpozicia=auta[i][3];
            boolean stavp =false;

            /**
             * rozlisuje dva rozne posuny posun horizontalny to je ked 5 suradnica auta ma 1
             * inak je to posun vertikalny
             */
            if (orienta==1){
                //posun vlavo
                stavp=false;
                if(1<auta[i][3]){
                    for(int l = (auta[i][3]-1) ; l>0; l--){
                        if(0>(l-1))stavp=true;
                        if(stavp==false){
                            if(0==(mapka[ypozicia-1][l-1]))	pocL++;
                            else stavp=true;
                        }
                    }
                }
                //posun vpravo tu musim riesit aj dzku auta kedze auto je reprezentovane lavy horny roh
                int dlzka= auta[i][1];
                int posvpr=auta[i][3]+dlzka;
                stavp=false;
                ypozicia=auta[i][2];
                if(7>posvpr){
                    for(int l =posvpr-1  ; l<6; l++){
                        if(6<posvpr)stavp=true;
                        if(stavp==false){
                            if(0==(mapka[ypozicia-1][l])) pocR++;
                            else stavp=true;
                        }
                    }
                }
            }

            //posun  vertikalny cize hore dolu
            if(orienta==0){
                //posun hore
                stavp=false;
                if(1<auta[i][2]){
                    for(int l = (auta[i][2]-1) ; l>0; l--){
                        if(0>(l-1))stavp=true;
                        if(stavp==false){
                            if(0==(mapka[l-1][xpozicia-1])) pocU++;
                            else stavp=true;
                        }
                    }
                }
                //posun dolu tu musim znova riesit dlzku auta aby som nevysiel
                int dlzka= auta[i][1];
                int posdol=auta[i][2]+dlzka;
                stavp=false;
                xpozicia=auta[i][3];
                if(7>posdol){
                    for(int l =posdol-1  ; l<6; l++){
                        if(6<posdol)stavp=true;
                        if(stavp==false){
                            if(0==(mapka[l][xpozicia-1])) pocD++;
                            else stavp=true;
                        }
                    }
                }
            }
            /**
             * v tejto faze uz viem ze dane auto viem posunut do ktorej strany a o kolko napr. pre posun dolava o 3
             * vygeneruje tri nove uzly.....uzol o 1 uzol o 2 uzol o3
             */

            ShiftLeft posv =new ShiftLeft();
            for(int l=1;l<pocL+1;l++)
            {
                hlavny.lifo.push(posv.Stav(prepispola(nod.getPole()), l, i,nod,hlbka));
            }
            ShiftRight posvp =new ShiftRight();
            for(int l=1;l<pocR+1;l++)
            {
                hlavny.lifo.push(posvp.Stav(prepispola(nod.getPole()), l, i,nod,hlbka));
            }
            ShiftUp posh =new ShiftUp();
            for(int l=1;l<pocU+1;l++)
            {
                hlavny.lifo.push(posh.Stav(prepispola(nod.getPole()), l, i,nod,hlbka));
            }
            ShiftDown posd =new ShiftDown();
            for(int l=1;l<pocD+1;l++)
            {
                hlavny.lifo.push(posd.Stav(prepispola(nod.getPole()), l, i,nod,hlbka));

            }
        }
    }
    /**
     *  tato funkcia sluzi na jednoduchy prepis pola tak aby vzniklo nezavisle nove pole
     *  z ktoreho vytvorim nove konfiguracie inak by mohlo nastat genrerovanie novych uzlov do rod. pola
     * @param pole
     * @return
     */
    public int[][]prepispola(int[][] pole){
        int[][] novepole = new int[pole.length][5];
        for (int i = 0; i < pole.length; i++){
            for (int l = 0; l <pole[i].length; l++){
                novepole[i][l]=pole[i][l];
            }
        }
        return novepole;
    }
    /**
     * tato funkcia z daneho uzla ziska pole a zisti ci je cervene auto ktore ma oznacenie 1
     * na pravom okraji mapky
     * @param nod
     * @return
     */
    public Node kontrolauzla(Node nod){
        Node pom=null;
        int[][]kontrlovanepole;
        kontrlovanepole=nod.getPole();
        //konecny stav
        for (int i=0;i< kontrlovanepole.length;i++){
            //x poloha plus dlzka
            if((kontrlovanepole[i][0]==1)&&(kontrlovanepole[i][3]+kontrlovanepole[i][1]==7)) pom=nod;
        }
        return pom;
    }
    /**
     * tato funkcia vytvori novy uzol ""dieta -potomka" ktory bude mat zaznam na predchodcu a sucasne bude mat novo
     * vygenerovane pole aut
     * @param array
     * @param nod
     * @param posun
     * @param hlbka
     * @return
     */
    public Node vytvordieta(int[][] array, Node nod,String posun,int hlbka) {
        Node newNode = new Node();
        newNode.setPole(array);
        newNode.setRod(nod);
        newNode.setposun(posun);
        newNode.setH(hlbka);

        return newNode;
    }
    /**
     * funkcia vypisuje stav bud sa najde riesenie alebo nema dana konfiguracia riesenie
     * @param ciel
     */
    public void vypis(Node ciel){
        for (int i = 0; i <ciel.getPole().length ; i++){
            for (int l = 0; l < ciel.getPole()[i].length ; l++){
                System.out.print(ciel.getPole()[i][l]+" ");
                if (l==4) System.out.print("\n");
            }
        }
        if(ciel==null){
            System.out.println("Dane rozlozenie aut nema riesenie");
        }
        //tato cast je na vypis posunov aut od korena az po konecny tah..vyuzijem stack
        else{
            Stack riesenie = new Stack();
            while (ciel.getRod() != null){
                riesenie.push(ciel.getposun());
                ciel=ciel.getRod();
            }
            while (!riesenie.empty()){
                System.out.println(riesenie.pop());
            }
        }
    }
    /**
     * iddfs
     * algoritmus cyklicky sa prehlbujuci
     * vytvorim si koren kt. funkcia dostane root
     * dalej vygenerujem nove uzly v spravnom poradi tak aby som spravil kontrolu inak by som stratil uzol
     * @param lifo
     */
    public void algoritmus(Stack lifo){
        Node ciel=null;
        int hlbka=1;
        Node root = new Node();
        Node nod = new Node();
        root=(Node) lifo.pop();
        //generovanie pre hlbku 1
        while (ciel==null){
            generovanie(root,1);
            // generujem pokial stack neni prazdny alebo nenastane finalny stav
            while(!lifo.empty()){
                nod=(Node) lifo.pop();
                //kontrolujem az ked hlbka je rovna hbke uzla...
                if(nod.getH()==hlbka)
                    ciel=kontrolauzla(nod);
                    //pre pripad ze sa nerovna finalnej hlbke tak idem pokial nenastane podmienka vyssie
                else generovanie(nod,(nod.getH()+1));
                if(ciel!=null) break;
            }
            hlbka++;
        }
        vypis(ciel);
    }
}