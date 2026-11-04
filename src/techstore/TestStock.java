package techstore;

import java.util.ArrayList;
import java.util.Scanner;

public class TestStock {
    private static final Scanner clavier = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. Listes de départ (noms, prix, quantités)
        ArrayList<String> noms = new ArrayList<>();
        ArrayList<Double> prix = new ArrayList<>();
        ArrayList<Long> quantites = new ArrayList<>();

        noms.add("Micro Casque 588 Filaire");  prix.add(89.0);  quantites.add(15L);
        noms.add("Clavier Logitech K120");     prix.add(45.5);  quantites.add(30L);
        noms.add("Souris HP X1000");           prix.add(29.9);  quantites.add(40L);
        noms.add("Ecran Samsung 24 pouces");   prix.add(549.0); quantites.add(6L);

        // 2. Création du stock
        Stock stock = new Stock(noms, prix, quantites);

        // 3. Menu
        int choix;
        do {
            afficherMenu();
            choix = lireEntier("Votre choix : ");
            switch (choix) {
                case 0: ajouter(stock); break;
                case 1: augmenter(stock); break;
                case 2: modifier(stock); break;
                case 3: supprimer(stock); break;
                case 4: System.out.println("\n" + stock); break;
                case 5: System.out.println("Au revoir !"); break;
                default: System.out.println("Choix invalide, entrez un nombre de 0 à 5.");
            }
        } while (choix != 5);
    }

    private static void afficherMenu() {
        System.out.println("\n========== GESTION DU STOCK TECHSTORE ==========");
        System.out.println("0 - Ajouter un article");
        System.out.println("1 - Augmenter la quantité d’un article");
        System.out.println("2 - Modifier un article");
        System.out.println("3 - Supprimer un article");
        System.out.println("4 - Consulter le stock");
        System.out.println("5 - Quitter");
    }

    private static void ajouter(Stock stock) {
        String nom = lireTexte("Nom de l'article : ");
        double prix = lireDouble("Prix (TND) : ");
        long quantite = lireLong("Quantité : ");
        try {
            stock.ajouterArticle(nom, prix, quantite);
            System.out.println("Article ajouté avec succès.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ajout impossible : " + e.getMessage());
        }
    }

    private static void augmenter(Stock stock) {
        System.out.println("\n" + stock);
        String reference = lireTexte("Référence de l'article : ");
        Element e = stock.trouverArticle(reference);
        if (e == null) {
            System.out.println("Aucun article ne porte la référence " + reference + ".");
            return;
        }
        long q = lireLong("Quantité à ajouter : ");
        if (stock.incrementerQuantiteArticle(reference, q)) {
            System.out.println("Quantité mise à jour : " + e);
        } else {
            System.out.println("Quantité invalide (elle doit être strictement positive).");
        }
    }

    private static void modifier(Stock stock) {
        System.out.println("\n" + stock);
        String reference = lireTexte("Référence de l'article à modifier : ");
        Element e = stock.trouverArticle(reference);
        if (e == null) {
            System.out.println("Aucun article ne porte la référence " + reference + ".");
            return;
        }
        String nom = lireTexte("Nouveau nom : ");
        double prix = lireDouble("Nouveau prix (TND) : ");
        long quantite = lireLong("Nouvelle quantité : ");
        try {
            stock.modifierArticle(reference, nom, prix, quantite);
            System.out.println("Article modifié : " + e);
        } catch (IllegalArgumentException ex) {
            System.out.println("Modification impossible : " + ex.getMessage());
        }
    }

    private static void supprimer(Stock stock) {
        System.out.println("\n" + stock);
        String reference = lireTexte("Référence de l'article à supprimer : ");
        if (stock.supprimerArticle(reference)) {
            System.out.println("Article supprimé.");
        } else {
            System.out.println("Aucun article ne porte la référence " + reference + ".");
        }
    }

    // ---- Lecture sécurisée : toujours nextLine() puis conversion, pour éviter
    // ---- le piège nextInt()/nextLine() et les plantages sur une saisie non numérique.
    private static String lireTexte(String message) {
        System.out.print(message);
        return clavier.nextLine().trim();
    }

    private static int lireEntier(String message) {
        while (true) {
            try {
                return Integer.parseInt(lireTexte(message));
            } catch (NumberFormatException e) {
                System.out.println("Erreur : saisissez un nombre entier.");
            }
        }
    }

    private static long lireLong(String message) {
        while (true) {
            try {
                return Long.parseLong(lireTexte(message));
            } catch (NumberFormatException e) {
                System.out.println("Erreur : saisissez un nombre entier.");
            }
        }
    }

    private static double lireDouble(String message) {
        while (true) {
            try {
                return Double.parseDouble(lireTexte(message).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Erreur : saisissez un nombre (ex. 1269 ou 12.5).");
            }
        }
    }
}

