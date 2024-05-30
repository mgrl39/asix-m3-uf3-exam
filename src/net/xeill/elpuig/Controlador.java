package net.xeill.elpuig;
import java.util.ArrayList;

public class Controlador {
    private ArrayList<Aula> aules;
    private int lastID;

    public Controlador() {
        this.aules = new ArrayList<>();
        this.lastID = 0;
    }

    public void add(String codi, String nom, int aforament, int ordinadors){
        Aula a = new Aula(++this.lastID, codi, nom, aforament, ordinadors);
        this.aules.add(a);
    }

    public void remove(int id){
        Aula a = get(id);
        this.aules.remove(a);
    }

    public void removeAll(){
        this.aules.clear();
        this.lastID = 0;
    }

    public Aula get(int id){
        for (Aula a: this.aules) {
            if(a.getId() == id) return  a;
        }

        return null;
    }

    public ArrayList<Aula> getAll(){
        return  this.aules;
    }

    public String getTot() {
        String retornar = "";
        for (int i = 0 ; i < aules.size() ; i++) {
            retornar += aules.get(i).getCodi()+","+aules.get(i).getNom()+","+aules.get(i).getAforament()+","+aules.get(i).getOrdinadors();
            if (i != (aules.size()-1)) {
                retornar+="\n";
            }
        }
        return retornar;
    }

    public void limpiar() {
        aules.clear();
        this.lastID = 0;
    }
}
