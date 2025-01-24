import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZadanieD {

    public static boolean czyTrojkat(double a, double b, double c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    public static List<String> wczytajPlik(String plik) throws FileNotFoundException, IOException {
        List<String> linie = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(plik))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                linie.add(linia.trim());
            }
        }
        
        return linie;
    }

    public static int liczbaTrojkatnychTrojek(List<String> linie) {
        int liczbaWierszy = 0;
        
        for (String linia : linie) {
            String[] liczby = linia.split("\\s+");
            
            if (liczby.length == 3) {
                double a = Double.parseDouble(liczby[0]);
                double b = Double.parseDouble(liczby[1]);
                double c = Double.parseDouble(liczby[2]);
                    
                if (czyTrojkat(a, b, c)) {
                    liczbaWierszy++;
                }
            }
        }
        
        return liczbaWierszy;
    }

    public static List<Double> znajdzNajdluzszyCiag(List<String> linie) {
        List<Double> najdluzszyCiag = null;
        List<Double> aktualnyCiag = new ArrayList<>();
        
        for (String linia : linie) {
            String[] liczby = linia.split("\\s+");
            
            if (liczby.length == 3) {
                double a = Double.parseDouble(liczby[0]);
                double b = Double.parseDouble(liczby[1]);
                double c = Double.parseDouble(liczby[2]);
                    
                if (czyTrojkat(a, b, c)) {
                    aktualnyCiag.add(a);
                    aktualnyCiag.add(b);
                    aktualnyCiag.add(c);
                } else {
                    if (najdluzszyCiag == null || aktualnyCiag.size() > najdluzszyCiag.size()) {
                        najdluzszyCiag = new ArrayList<>(aktualnyCiag);
                    }
                    aktualnyCiag.clear();
                }
            }
        }

        if (!aktualnyCiag.isEmpty() && (najdluzszyCiag == null || aktualnyCiag.size() > najdluzszyCiag.size())) {
            najdluzszyCiag = new ArrayList<>(aktualnyCiag);
        }

        return najdluzszyCiag;
    }

    public static void wypiszWyniki(List<String> linie) {
        int liczbaWierszy = liczbaTrojkatnychTrojek(linie);
        System.out.println("Liczba wierszy: " + liczbaWierszy);
        
        List<Double> najdluzszyCiag = znajdzNajdluzszyCiag(linie);
        
        if (najdluzszyCiag != null) {
            System.out.println("Długość najdłuższego ciągu trójkątnego: " + najdluzszyCiag.size() / 3);
        } else {
            System.out.println("Brak trójkątnych ciągów.");
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String plik = "trojki.txt";
        
        List<String> linie = wczytajPlik(plik);
        
        wypiszWyniki(linie);
    }
}
