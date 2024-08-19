import java.util.*;

public class Challenge_3 {
    
    private static Scanner input = new Scanner(System.in);
    private static int invalidKeys = 0;
    private static int validKeys = 0;
    private static String key42 = "";  
    private static String sudo = "";

    public static void main(String[] args) {

        String banner = """
        
            ██████╗ ██╗     ███████╗███████╗██╗    ██╗ █████╗ ██████╗ ███████╗
            ██╔══██╗██║     ██╔════╝██╔════╝██║    ██║██╔══██╗██╔══██╗██╔════╝
            ██████╔╝██║     █████╗  ███████╗██║ █╗ ██║███████║██████╔╝█████╗  
            ██╔══██╗██║     ██╔══╝  ╚════██║██║███╗██║██╔══██║██╔══██╗██╔══╝  
            ██████╔╝███████╗███████╗███████║╚███╔███╔╝██║  ██║██║  ██║███████╗
            ╚═════╝ ╚══════╝╚══════╝╚══════╝ ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝

            Desafio semana 3 de Codember v1.0

            (En Windows presiona Ctrl + Z en una nueva línea para terminar de leer)
            (En Linux Ctrl + D en una nueva línea para terminar de leer)

            Ingresa un texto:""";

        System.out.println(banner);        

        //Se lee el texto línea por línea hasta que se ingrese Ctrl + Z para finalizar
        StringBuilder texto = new StringBuilder();

        while (input.hasNextLine()) {

            texto.append(input.nextLine());
            texto.append("\n"); // Agregar un salto de línea después de cada línea ingresada
        }

        String textKeys = texto.toString().trim();        

        //Dividir el texto por saltos de línea
        String[] lineas = textKeys.split("\n");

        //Convertir el array en un ArrayList
        ArrayList<String> keys = new ArrayList<>(Arrays.asList(lineas));        
        
        boolean passed = false;        

        System.out.println("");
        System.out.println("");

        //Recorriendo el valor ArrayList
        for (int i = 0; i < keys.size(); i++) {

            passed = validKey(keys.get(i));

            if (passed == false) {

                invalidKeys++;

                //Encontrando la contraseña sudo de codember            
                if (invalidKeys == 13) {

                    sudo = keys.get(i).substring(7, keys.get(i).length());                                
                    System.out.println("Contraseña sudo:" + sudo);                                 
                }

                //Obtener el valor 42 incorrecto
                if (invalidKeys == 42) {

                    key42 = keys.get(i + 1).substring(7, keys.get(i + 1).length());                                
                    System.out.println("Key invalida numero 42:" + key42);
                }

            } else {

                validKeys++;
            }        
        }        

        System.out.println("Total de claves analizadas: " + keys.size());
        System.out.println("Claves Invalidas: " + invalidKeys);
        System.out.println("Claves Validas: " + validKeys);
        System.out.println("Finish");

        input.close();
    }      

    //Metodo para validad si una key es correcta o no
    private static boolean validKey(String key)  {

        boolean valid = false;            

        if (key.length() > 7 && 
            String.valueOf(key.charAt(0)).matches("-?\\d+") &&
            String.valueOf(key.charAt(1)).equals("-") &&
            String.valueOf(key.charAt(2)).matches("-?\\d+") &&           
            String.valueOf(key.charAt(3)).equals(" ") &&
            String.valueOf(key.charAt(4)).matches("[a-zA-Z]+") &&
            String.valueOf(key.charAt(5)).equals(":") &&
            String.valueOf(key.charAt(6)).equals(" ")) {

            int repeat = 0;
            int min = Integer.parseInt(String.valueOf(key.charAt(0)));
            int max = Integer.parseInt(String.valueOf(key.charAt(2)));
            String letter = String.valueOf(key.charAt(4));
            String valueKey = key.substring(7, key.length());
            
            for (int i = 0; i < valueKey.length(); i++) {

                if (String.valueOf(valueKey.charAt(i)).equals(letter)) {

                    repeat++;
                }
            }

            if (repeat >= min && repeat <= max) {

                valid = true;
            }            
        }

        return valid;
    }
}
