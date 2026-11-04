package techstore;

import java.util.ArrayList;
import java.util.List;


public class Stock {
    private ArrayList<Element> articleList;

    public Stock() {
        this.articleList = new ArrayList<>();
    }

    public Stock(ArrayList<Element> articleList) {
        setArticleList(articleList);
    }

    public Stock(List<String> noms, List<Double> prix, List<Long> quantites) {
        this();
        if (noms.size() != prix.size() || noms.size() != quantites.size()) {
            throw new IllegalArgumentException("Les trois listes doivent avoir la même taille.");
        }
        for (int i = 0; i < noms.size(); i++) {
            ajouterArticle(noms.get(i), prix.get(i), quantites.get(i));
        }
    }

    public ArrayList<Element> getArticleList() {
        return articleList;
    }

    public int getArticleNb() {
        return articleList.size();
    }

    public void setArticleList(ArrayList<Element> articleList) {
        this.articleList = (articleList == null) ? new ArrayList<>() : articleList;
    }

    public void ajouterArticle(Article article, long quantite) {
        articleList.add(new Element(article, quantite));
    }

    public void ajouterArticle(String nom, double prix, long quantite) {
        articleList.add(new Element(nom, prix, quantite));
    }

    
    public Element trouverArticle(String reference) {
        for (Element e : articleList) {
            if (e.equals(reference)) { // appelle bien equals(String) : choix fait à la compilation
                return e;
            }
        }
        return null;
    }

    /** @return false si la référence est introuvable. */
    public boolean modifierArticle(String reference, String nom, double prix, long quantite) {
        Element e = trouverArticle(reference);
        if (e == null) {
            return false;
        }
        e.modifier(nom, prix, quantite);
        return true;
    }

    public boolean supprimerArticle(String reference) {
        Element e = trouverArticle(reference);
        if (e == null) {
            return false;
        }
        return articleList.remove(e);
    }

    /** @return false si article introuvable OU quantité invalide. */
    public boolean incrementerQuantiteArticle(String reference, long q) {
        Element e = trouverArticle(reference);
        return e != null && e.incrementerQuantite(q);
    }

    /** @return false si article introuvable OU quantité invalide / supérieure au disponible. */
    public boolean decrementerQuantiteArticle(String reference, long q) {
        Element e = trouverArticle(reference);
        return e != null && e.decrementerQuantite(q);
    }

    @Override
    public String toString() {
        if (articleList.isEmpty()) {
            return "Le stock est vide.";
        }
        StringBuilder sb = new StringBuilder();
        for (Element e : articleList) {
            sb.append(e).append("\n");
        }
        return sb.toString();
    }
}
