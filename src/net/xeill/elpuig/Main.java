package net.xeill.elpuig;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// Necesari per ordenar els arrays
import java.util.Arrays;
// Necesari per saber quin dia i hora es
import java.time.ZonedDateTime;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Controlador ctrl = new Controlador();

    // Defino la meva carpeta arrel
    private static File carpeta = new File("data");
    // Defino el meu reader, pero aquest Scanner llegirá arxius
    private static Scanner myReader;
    // Defino la ruta: arrel/carpetaMesRecent/horaMesRecent
    // Aquesta ruta la utilitzarem la primera vegada (quan encenem el programa)
    private static String rutaArxiuCarregar = carpeta +"/"+ carpetaMesRecent()+"/"+horaMesRecent();

    // Aquest es el meu arxiu carregat, quan arranquem el codi carrega la ruta mes recent
    private static File arxiuACarregar = new File(rutaArxiuCarregar);

    // Defineixo el meu escriptor
    private static FileWriter escriptor;

    public static void main(String[] args) throws IOException {
        // Truco el metode encarregat de carregar i llegir
        carregarArxiu();
        // Mostro el menu
        menu();
    }

    private static void carregarArxiu() throws IOException {
        //rutaArxiuCarregar = carpeta +"/"+ carpetaMesRecent()+"/"+horaMesRecent();
        // El carregador s'encarrega de definir el valor del File, es a dir, la ruta, quan volem carregar informació truquem al carregador
        carregador();
        // Carregar informació no vols dir llegirla, per aquest motiu he creat un métode lectura
        lectura();
    }

    private static void carregador() throws IOException {
        arxiuACarregar = new File(rutaArxiuCarregar);
    }
    private static void lectura() throws IOException {
        // Primerament, el métode truca al controlador per avisar-li de que esborri totes les dades de l'ArrayList
        // Executar això la primera vegada (quan encenem el programa) pot ser no té sentit, pero quan volem carregar inforamció hem d'eliminar l'anterior ja emmagatzemada
        ctrl.limpiar();
        // Definim que el myReader llegeixi l'arxiuAcarregar, la seva ruta l'hem definit al carregador
        myReader = new Scanner(arxiuACarregar);
        // Fem llegir al programa fins que no quedi cap linia sense llegir
        while (myReader.hasNextLine()) {
            // Agafo la frase completa
            String frase = myReader.nextLine();
            // Utilitzo el métode split per separar les dades
            String[] array = frase.split(",");
            // Afegeixo al controlador les dades emmagatzemades a l'array
            // l'array per defecte es STRING, pero les dades que demana l'add no son tots String
            // array[0] = String
            // array[1] = String
            // array[2] = int (Integer.parseInt)
            // array[3] = int (Integer.parseInt)
            ctrl.add(array[0],array[1],Integer.parseInt(array[2]),Integer.parseInt(array[3]));
        }
    }

    private static void escriptura() throws IOException{
        // Creem una nova instancia de la carpeta del dia actual
        File carpetaDiaDeAvui = new File(carpeta+"/"+diaActual());
        // Si aquesta carpeta no existeix, la creem
        if (!carpetaDiaDeAvui.exists()) {
            carpetaDiaDeAvui.mkdir();
        }
        // Creem un arxiu nou dins de la carpeta del dia actual, aquest arxiu serà l'hora actual.csv
        File arxiuNou = new File(carpetaDiaDeAvui+"/"+horaActual() + ".csv");
        // Definim l'escriptor
        escriptor = new FileWriter(arxiuNou.getPath());
        // Per escriure, truquem a un mètode creat anomenat controlador.getTot (que guarda totes les dades a tipus .csv)
        // Es a dir, es el contrari del que fem quan llegim
        escriptor.write(ctrl.getTot());
        // Tanquem l'escriptor
        escriptor.close();
    }

    private static void menu() throws IOException {
        sc.useLocale(Locale.ENGLISH);

        int opcio = -1;
        do{
            System.out.println();
            opcio = opcions();

            if (opcio == 1) crear();
            else if (opcio == 2) demo();
            else if (opcio == 3) mostrar();
            else if (opcio == 4) mostrarTots();
            else if (opcio == 5) carregar();
            else if (opcio == 6) esborrar();


        }while (opcio != 7);
        // Truquem al metode escriptura despres de l'ordre de sortir de l'usuari.
        escriptura();
    }

    private static int opcions(){
        System.out.println("Escull una opció:");
        System.out.println("  1. Crear una nova assignatura de forma manual.");
        System.out.println("  2. Crear noves assignatures de forma automàtica.");
        System.out.println("  3. Mostrar les dades d'una sola assignatura.");
        System.out.println("  4. Mostrar les dades de totes les assignatures.");
        System.out.println("  5. Carregar un fitxer de dades concret.");
        System.out.println("  6. Esborrar dades.");
        System.out.println("  7. Sortir.");

        int opcio = sc.nextInt();
        sc.nextLine();

        return opcio;
    }

    private static void crear(){
        System.out.println("Escriu el codi de l'assginatura:");
        String codi = sc.nextLine();

        System.out.println("Escriu el nom de l'assginatura:");
        String nom = sc.nextLine();

        System.out.println("Escriu l'aforament (quantitat màxima d'alumnes que hi caben a l'aula):");
        int aforament = sc.nextInt();

        System.out.println("Escriu la quantitat d'ordinadors que hi ha a l'aula:");
        int ordinadors = sc.nextInt();

        ctrl.add(codi, nom, aforament, ordinadors);
    }

    private static void demo(){
        System.out.print("Creant informació de demo... ");
        ctrl.add("0.01", "Stallman", 33, 24);
        ctrl.add("0.19", "Tesla", 25, 19);
        ctrl.add("0.13", "Chomsky", 22, 18);
        ctrl.add("2.10", "Ada", 40, 26);
        ctrl.add("2.09", "Snowden", 40, 26);
        ctrl.add("2.07", "Berners-Lee", 26, 1);
        ctrl.add("3.03", "Turing", 24, 17);
        ctrl.add("3.01", "Escher", 28, 16);

        System.out.println("OK!");
    }

    private static void mostrar(){
        System.out.println("Escriu l'ID de l'assignatura que vols mostrar:");
        int id = sc.nextInt();
        sc.nextLine();

        Aula a = ctrl.get(id);
        if(a == null) System.out.println("No s'ha trobat l'assignatura indicada.");
        else System.out.println(a);
    }

    private static void mostrarTots(){
        List<Aula> all = ctrl.getAll();

        if(all.size() == 0) System.out.println("No hi ha elements que mostrar.");
        else{
            for (Aula a : ctrl.getAll()){
                System.out.println("Dades de l'assignatura amb ID " + a.getId() + ":");
                System.out.println(a);
            }
        }
    }

    private static void carregar() throws IOException {
        // Preguntem a l'usuari quina carpeta vol seleccionar
        System.out.println("Quina carpeta vols seleccionar?");
        // Creem un String[] de les carpetes que es troben dins de la carpeta arrel (data)
        String[] carpetes = carpeta.list();
        // Ordenem
        Arrays.sort(carpetes);
        // Utilitzem un bucle per mostrar totes les carpetes com si fos un menu
        for (int i = 0 ; i < carpetes.length ; i++) {
            System.out.println(i+1 + " )" + carpetes[i]);
        }
        // Esperem la resposta de l'usuari i la guardem en una variable
        int seleccionarCarpeta = sc.nextInt();
        // Com hem posat al menú i+1, a la seleccio hem de restar -1

        // Guardem dins d'un string la seleccionada
        String seleccionada = carpetes[seleccionarCarpeta-1];
        // Creem una nova instancia de File que sigui la carpeta que hem seleccionat
        File carpetaAmbArxius = new File("data/" + seleccionada);
        // Creem un String[] dels arxius de la carpeta seleccionada
        String[] arxius = carpetaAmbArxius.list();
        // Ordenem l'array
        Arrays.sort(arxius);
        // Utilitzem un bucle per mostrar tots els arxius com si fos un menu
        for (int i = 0 ; i < arxius.length ; i++) {
            System.out.println(i+1 + " )" +  arxius[i]);
        }
        // Esperem la resposta de l'usuari i la guardem en una variable
        int seleccionarArxiu = sc.nextInt();
        // Ara, modifiquem la ruta del principi, hem de restar -1 a l'array d'arxius
        rutaArxiuCarregar = carpeta+"/"+seleccionada+"/"+arxius[seleccionarArxiu-1];
        // Truquem al mètode que carrega els arxius
        carregarArxiu();

    }

    private static void esborrar() throws IOException {
        // Preguntem a l'usuari quina carpeta vol esborrar
        System.out.println("Quina carpeta vols seleccionar?");
        // Creem un String[] per emmagatzemar les carpetes de dins
        String[] carpetes = carpeta.list();
        // Ordenem l'array
        Arrays.sort(carpetes);
        // Mostrem l'array com si fos un menú
        for (int i = 0 ; i < carpetes.length ; i++) {
            System.out.println(i+1 + ") " + carpetes[i]);
        }
        // Esperem a la selecció de l'usuari
        int seleccionarCarpeta = sc.nextInt();
        // Guardem l'opció seleccionada a un String
        String seleccionada = carpetes[seleccionarCarpeta-1];
        // Carreguem una nova instancia de la carpeta selececionada
        File carpetaAmbArxius = new File("data/" + seleccionada);
        // Creem un String[] d'arxius per emmagatzemar els arxius de dins
        String[] arxius = carpetaAmbArxius.list();
        // Ordenem l'array
        Arrays.sort(arxius);
        // Mostrem l'array com si fos un menu
        for (int i = 0 ; i < arxius.length ; i++) {
            System.out.println(i+1 + " )" +  arxius[i]);
        }
        // Afegim una opció més, en aquest cas, l'opció d'esborrar tots
        System.out.println((arxius.length+1) + " ) Esborrar tots");
        // Esperem a la resposta de l'usuari
        int seleccionarArxiu = sc.nextInt();
        // Creem un string anomenat arxiu
        String arxiu = "";
        // Si l'usuari selecciona una opció diferent a la d'esborrar tots, haurem d'esborrar nomes un
        if (arxius.length+1 != seleccionarArxiu) {
            // Creem una instancia del fitxer seleccionat
            File eliminar = new File(carpeta+"/"+seleccionada+"/"+arxius[seleccionarArxiu-1]);
            // Guardem la ruta
            arxiu = eliminar.getPath();
            // Si l'arxiu existeix:
            if (eliminar.exists()) {
                // Si l'arxiu es l'arxiu carregat
                if (eliminar.getPath().equals(arxiuACarregar.getPath())) {
                    // tanquem el lector
                    myReader.close();
                }
                // Eliminem el fitxer
                eliminar.delete();
                if (!eliminar.exists()) {
                    // Si entra aquí significa que el fitxer ha deixat d'existir, aixo vol dir que s'ha eliminat correctament
                    System.out.println("L'arxiu " + arxiu + " s'ha eliminat correctament");
                }
            }
            // Si l'usuari demana l'ultima opció
        } else {
            // Definim una nova instancia de File, aquest cas de la carpeta que es vol eliminar
            File carpetaEliminar = new File(carpeta+"/"+seleccionada);
            // Fem una llista
            String[] arxiusEliminar = carpetaEliminar.list();
            // ordenem
            Arrays.sort(arxiusEliminar);
            // Fem un bucle
            for (int i = 0 ; i < arxiusEliminar.length ; i++) {
                // Creem una nova instancia
                File eliminarArxiu = new File(carpeta+"/"+seleccionada+"/"+arxiusEliminar[i]);
                // Si l'arxiu que toca eliminar es el que esta carregat, es tanca el myReader i s'esborra
                if (eliminarArxiu.exists()) {
                    if (eliminarArxiu.getPath().equals(arxiuACarregar.getPath())) {
                        lectura();
                        myReader.close();
                    }
                    // Esborrem
                    eliminarArxiu.delete();
                    // Si l'arxiu ha deixat d'existir vol dir que s'ha elimiant correctament
                    if (!eliminarArxiu.exists()) {
                        System.out.println("L'arxiu " + eliminarArxiu + " s'ha eliminat correctament.");
                    }
                }
            }
            // com hem eliminat tot, podem eliminar la carpeta
            carpetaEliminar.delete();
            // Si la carpeta s'ha eliminat vol dir que ha deixat d'existir.
            if (!carpetaEliminar.exists()) {
                System.out.println("La carpeta " + carpetaEliminar + " s'ha eliminat correctament.");
            }
        }


    }

    // Creem un metode per saber la carpeta mes recent. Mai hi haurà una carpeta buida per aquest motiu puc posar array.length -1
    // El programa mai crearà una carpeta sense un arxiu.
    private static String carpetaMesRecent() {
        String retornar = "";
        String[] array = carpeta.list();
        Arrays.sort(array);
        return array[array.length-1];
    }

    // Aquest metod diu l'horaMesRecent (es a dir, l'arxiu mes recent)
    private static String horaMesRecent() {
        String retornar = "";
        File diaMesRecent = new File("data/" + carpetaMesRecent());
        String[] array = diaMesRecent.list();
        return array[array.length-1];
    }

    // Genera l'hora actual en el format demanat
    private static String horaActual() {
        int hora = ZonedDateTime.now().getHour();
        int minut = ZonedDateTime.now().getMinute();
        int segon = ZonedDateTime.now().getSecond();

        String horaString = String.valueOf(hora);
        String minutString = String.valueOf(minut);
        String segonString = String.valueOf(segon);

        if (hora < 10) {
            horaString=0+horaString;
        }
        if (minut < 10) {
            minutString =0+minutString;
        }
        if (segon < 10) {
            segonString=0+segonString;
        }
        return (horaString+":"+minutString+":"+segonString);
    }

    // Genera el dia actual en el format demanat
    private static String diaActual() {
        int dia = ZonedDateTime.now().getDayOfMonth();
        int mes = ZonedDateTime.now().getMonthValue();
        int any = ZonedDateTime.now().getYear();

        String diaString = String.valueOf(dia);
        String mesString = String.valueOf(mes);

        if (dia < 10) {
            diaString=0+diaString;
        }
        if (mes < 10) {
            mesString =0+mesString;
        }

        return (any+"-"+mesString+"-"+diaString);
    }

}
