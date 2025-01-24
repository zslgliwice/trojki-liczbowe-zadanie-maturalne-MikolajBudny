import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZadanieC {
    
    public static boolean pitagoras(int[] numery) {
        if (numery.length != 3) return false;

        Arrays.sort(numery);
        int a = numery[0];
        int b = numery[1];
        int c = numery[2];

        return a * a + b * b == c * c;
    }

    public static List<String> znajdzTrojki(String plik) throws IOException {
        List<String> trojki = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(plik))) {
            String poprzedniaLinia = null;
            String aktualnaLinia;

            while ((aktualnaLinia = br.readLine()) != null) {
                if (poprzedniaLinia != null) {
                    int[] poprzednieNumery = Arrays.stream(poprzedniaLinia.split(" ")).mapToInt(Integer::parseInt).toArray();
                    int[] aktualneNumery = Arrays.stream(aktualnaLinia.split(" ")).mapToInt(Integer::parseInt).toArray();

                    if (pitagoras(poprzednieNumery) && pitagoras(aktualneNumery)) {
                        trojki.add(poprzedniaLinia + " | " + aktualnaLinia);
                    }
                }

                poprzedniaLinia = aktualnaLinia;
            }
        }
        
        return trojki;
    }

    public static void main(String[] args) throws IOException {
        String plik = "trojki.txt";

        List<String> trojki = znajdzTrojki(plik);
        System.out.println("Znaleziono następujące trójki liczb:");
        for (String trojka : trojki) {
            System.out.println(trojka);
        }
    }
}