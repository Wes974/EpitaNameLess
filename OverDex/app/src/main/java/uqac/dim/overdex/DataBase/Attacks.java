package uqac.dim.overdex.DataBase;

public class Attacks {

    int Id_heroes;
    byte[] Logo;
    String Name;
    String Descrition;


    public Attacks(int id_heroes, byte[] logo, String name, String description) {
        this.Id_heroes = id_heroes;
        this.Logo = logo;
        this.Name = name;
        this.Descrition = description;
    }

    //-------- Setters --------
    public void setId_heroes(int id_heroes){
        this.Id_heroes = id_heroes;
    }
    public void setLogo(byte[] logo) { this.Logo = logo; }
    public void setName(String name) { this.Name = name; }
    public void setDescrition(String description) { this.Descrition = description; }

    //-------- Getters --------
    public int getId_heroes() {
        return this.Id_heroes;
    }
    public byte[] getLogo() { return this.Logo; }
    public String getName() { return this.Name; }
    public String getDescrition() { return this.Descrition; }
}
