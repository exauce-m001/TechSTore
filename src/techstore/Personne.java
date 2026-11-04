package techstore;

public class Personne {
	private String cin;
	private String nom;
	private String prenom;
	private Adresse adresse;
	
	//Constructeur qui va recevoir les paramètres de la classe personne
	public Personne(String cin, String nom, String prenom,Adresse adresse) {
		this.cin=cin;
		this.nom=nom;
		this.prenom=prenom;
		this.adresse=adresse;
	}
	
	public Personne(String cin, String nom, String prenom, String gouvernorat, int codePostal) {
		this.cin=cin;
		this.nom=nom;
		this.prenom=prenom;
		this.adresse = new Adresse(gouvernorat,codePostal);
		
	}
	public String getCin() { return cin; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public Adresse getAdresse() { return adresse; }

    public void setCin(String cin) { this.cin = cin; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
    public String toString() {
        return "cin ="+ this.cin+ ", nom = " + this.nom + "prenom = "+this.prenom +", " +this.adresse.toString();
    }

}
