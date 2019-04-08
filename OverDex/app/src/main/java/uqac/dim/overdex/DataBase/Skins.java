package uqac.dim.overdex.DataBase;

public class Skins {

    int Id_heroes;
    String Name;
    String Image;


    public Skins(int id_heroes, String name, String image) {
        this.Id_heroes = id_heroes;
        this.Name = name;
        this.Image = image;
    }

    //-------- Setters --------
    public void setId_heroes(int id_heroes){
        this.Id_heroes = id_heroes;
    }
    public void setName(String name) { this.Name = name; }
    public void setImage(String image) { this.Image = image; }

    //-------- Getters --------
    public int getId_heroes() {
        return this.Id_heroes;
    }
    public String getName() { return this.Name; }
    public String getImage() { return this.Image; }
}
