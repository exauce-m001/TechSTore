package techstore;

/** Un élément du stock = un Article + sa quantité disponible. */
public class Element {
    private Article article;
    private long quantite;

    public Element(Article article, long quantite) {
        if (article == null) {
            throw new IllegalArgumentException("L'article ne peut pas être null.");
        }
        verifierQuantite(quantite);
        this.article = article;
        this.quantite = quantite;
    }

    public Element(String nom, double prix, long quantite) {
        this(new Article(nom, prix), quantite);
    }

    private static void verifierQuantite(long quantite) {
        if (quantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative.");
        }
    }

    public Article getArticle() {
        return article;
    }

    public long getQuantite() {
        return quantite;
    }

    public String getReferenceArticle() {
        return article.getReference();
    }

    public String getNomArticle() {
        return article.getNom();
    }

    public double getPrixArticle() {
        return article.getPrix();
    }

    public void setArticle(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("L'article ne peut pas être null.");
        }
        this.article = article;
    }

    public void setQuantite(long quantite) {
        verifierQuantite(quantite);
        this.quantite = quantite;
    }

    /** @return true si l'augmentation a été faite (q doit être > 0). */
    public boolean incrementerQuantite(long q) {
        if (q <= 0) {
            return false;
        }
        quantite += q;
        return true;
    }

    /** @return true si la diminution a été faite (0 < q <= quantité disponible). */
    public boolean decrementerQuantite(long q) {
        if (q <= 0 || q > quantite) {
            return false;
        }
        quantite -= q;
        return true;
    }

    /** Remplace l'article par un NOUVEL Article (donc nouvel id et nouvelle référence). */
    public void modifier(String nom, double prix, long quantite) {
        verifierQuantite(quantite);
        Article nouveau = new Article(nom, prix); // peut lever une exception : rien n'est modifié dans ce cas
        this.article = nouveau;
        this.quantite = quantite;
    }

    // SURCHARGE (et non redéfinition) : equals(Object) de Object n'est pas touchée.
    public boolean equals(String reference) {
        return reference != null && article.getReference().equalsIgnoreCase(reference.trim());
    }

    // Règle choisie : deux articles sont identiques s'ils ont la même référence.
    public boolean equals(Article autre) {
        return autre != null && article.getReference().equals(autre.getReference());
    }

    @Override
    public String toString() {
        return "Element [article=" + article + ", quantite=" + quantite + "]";
    }
}
