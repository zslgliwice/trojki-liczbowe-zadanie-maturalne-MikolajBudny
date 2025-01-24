import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZadanieA {

    public static int suma(int numer) {
        int suma = 0;

        while (numer > 0) {
            suma += numer % 10;
            numer /= 10;
        }

        return suma;
    }

    public static List<String> znajdzTrojki(String plik) throws FileNotFoundException, IOException {
        List<String> trojki = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(plik))) {
            String linia;

            while ((linia = br.readLine()) != null) {
                String[] liczby = linia.split(" ");
                if (liczby.length == 3) {
                    int a = Integer.parseInt(liczby[0]);
                    int b = Integer.parseInt(liczby[1]);
                    int c = Integer.parseInt(liczby[2]);

                    if (suma(a) + suma(b) == c) {
                        trojki.add(linia);
                    }
                }
            }
        }

        return trojki;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String plik = "trojki.txt";

        List<String> trojki = znajdzTrojki(plik);
        System.out.println("Znaleziono następujące trójki liczb:");
        for (String trojka : trojki) {
            System.out.println(trojka);
        }
    }
}