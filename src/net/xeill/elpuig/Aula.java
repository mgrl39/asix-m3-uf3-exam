package net.xeill.elpuig;

public class Aula {
    private int id;

    public int getId() {
        return id;
    }

    private String codi;
    public String getCodi() {
        return codi;
    }

    private String nom;
    public String getNom() {
        return nom;
    }

    private int aforament;
    public int getAforament() {
        return aforament;
    }

    private int ordinadors;
    public int getOrdinadors() {
        return ordinadors;
    }

    public Aula(int id, String codi, String nom, int aforament, int ordinadors) {
        this.id = id;
        this.codi = codi;
        this.nom = nom;
        this.aforament = aforament;
        this.ordinadors = ordinadors;
    }

    public String toString(){
        String data = "ID: " + this.id + "\n";
        data += "Codi: " + this.codi + "\n";
        data += "Nom: " + this.nom + "\n";
        data += "Aforament: " + this.aforament + "\n";
        data += "Ordinadors: " + this.ordinadors + "\n";

        return  data;
    }
}




