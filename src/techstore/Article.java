package techstore;

public class Article {
    private static int compteur = 0;

    private final int id;
    private String nom;
    private String reference;
    private double prix; // en TND

    public Article(String nom, double prix) {
        verifierNom(nom);
        verifierPrix(prix);
        this.id = ++compteur;
        this.nom = nom;
        this.prix = prix;
        this.reference = genererReference();
    }

    private static void verifierNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'article ne peut pas être vide.");
        }
    }

    private static void verifierPrix(double prix) {
        if (prix < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif.");
        }
    }

    private String genererReference() {
        String sansEspaces = nom.replaceAll("\\s+", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sansEspaces.length(); i += 5) {
            sb.append(sansEspaces.charAt(i));
        }
        return sb.toString().toUpperCase() + id;
    }

    public int getId() {
        return id;
    }
    public String getReference() { return reference;
    }
    public String getNom() { return nom;
    }
    public double getPrix() { return prix;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public void setNom(String nom) {
        verifierNom(nom);
        this.nom = nom;
        this.reference = genererReference();
    }

    public void setPrix(double prix) {
        verifierPrix(prix);
        this.prix = prix;
    }

    public void modifier(String nom) {
        setNom(nom);
    }

    // Surcharge : même nom de méthode, signature différente
    public void modifier(String nom, double prix) {
        verifierPrix(prix);
        setNom(nom);
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "id=" + id + ", nom=" + nom + ", reference=" + reference + ", prix=" + prix;
    }
}
