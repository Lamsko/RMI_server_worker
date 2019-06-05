import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) {
    	int liczba;
	    BufferedReader klawiatura = null;
	    Farmer farmer = null;
	    String handle = null;
	    String s = null;
	    Integer result;

	    if (args.length == 0) {
	    	System.out.println("Musisz podac uchwyt rmi w postaci //host/usluga");
	    	return;
	    }

	    handle = args[0];

	    try {
	    	farmer = (Farmer) java.rmi.Naming.lookup(handle);
	    }
	    catch (Exception e) {
	    	System.out.println("Nie mozna pobrac referencji do" + handle);
	    	return;
	    }

	    System.out.println("Referencja do " + handle + " pobrana.");

	    klawiatura = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Liczba do sprawdzenia: ");
	    try {
	    	s = klawiatura.readLine();
	    	liczba = Integer.parseInt(s);
	    }
	    catch (Exception e) {
	    	System.out.println("Nieprawidlowa liczba.");
	    	return;
	    }

	    try {
	    	result = (Integer)farmer.compute(
	    			new TaskDzielnik(),
			      new Integer(liczba),
			      new DzielnikBO()
	      );
	    }
	    catch (Exception e) {
	    	System.out.println("Nieudane wywolanie RMI:");
	    	e.printStackTrace();
	    	return;
	    }

	    System.out.println("liczba ma " + result.intValue() + " dzielnikow");
    }
}
