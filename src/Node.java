public class Node {
    private int h ;
    private int[][] pole;
    private Node rodic;
    private String slovo;
    /**
     * vytvorenie uzla zo zaznamom na predchodcu,jeho hlbku,posun, a nove rozlozenie aut, dvoj dimenzionalne pole
     * @return
     */
    public Node getRod() {
        return rodic;
    }
    public void setRod(Node rodic) {
        this.rodic = rodic;
    }
    public int[][] getPole() {
        return pole;
    }
    public void setPole(int[][] auta) {
        this.pole = auta;
    }
    public String getposun() {
        return slovo;
    }
    public void setposun(String slovo) {
        this.slovo = slovo;
    }
    public int getH() {
        return h;
    }
    public void setH(int h) {
        this.h = h;
    }
}

