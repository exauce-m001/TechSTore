package techstore;

public class Test {

	public static void main(String[] args) {
		//Création des adresses
		Adresse a1 = new Adresse ("Monastir", 5000);
		Adresse a2 = new Adresse ("Jendouba",  8160);
		Adresse a3 = new Adresse ("Kinshasa", 012);
		//affichage des adresses 
		System.out.println("L'adresse a1:" +a1);
		System.out.println("L'adresse a2:" +a2);
		System.out.println("L'adresse a3:" +a3);
		
		// 3. Création de deux personnes avec les deux constructeurs
        Personne p1 = new Personne("01111111", "Kbayer", "Jasser", "Jendouba", 8160);
        Personne p2 = new Personne("02222222", "Smida", "Ghayth", a1);

        // 4. Affichage des personnes
        System.out.println("La personne p1 : " + p1);
        System.out.println("La personne p2 : " + p2);

        // 5. Modifications
        p1.setNom("Ben Ali");
        p1.setPrenom("Ahmed");
        p1.setAdresse(a2);
        
        //6. Vérification après modification
        System.out.println("\nAprès modification :");
        System.out.println("La personne p1 : " + p1);
        System.out.println("La personne p2 : " + p2);
	}

}
