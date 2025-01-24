import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZadanieB {

    public static boolean czyPierwsza(int n) {
        if (n < 2) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }

        return true;
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

                    if (czyPierwsza(a) && czyPierwsza(b) && c == a * b) {
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