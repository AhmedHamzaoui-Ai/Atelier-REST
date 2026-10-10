package utilities;

import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

public final class Metier {
    public static final OptionBusiness OPTIONS = new OptionBusiness();
    public static final EtudiantBusiness ETUDIANTS = new EtudiantBusiness();

    private Metier() { }
}
