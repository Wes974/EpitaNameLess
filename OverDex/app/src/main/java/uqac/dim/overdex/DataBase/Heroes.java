package uqac.dim.overdex.DataBase;

public class Heroes {

    int Id;
    String Name;
    String Picture;
    String Classe;
    String Description;
    String Identity;
    String Age;
    String Job;
    String Localisation;
    String Afflilation;
    String Citation;
    String History;
    String Logo;

    public Heroes(int id, String name, String picture, String classe, String description, String identity,
                  String age, String job, String localisation, String afflilation, String citation, String history, String logo) {
        this.Id = id;
        this.Name = name;
        this.Picture = picture;
        this.Classe = classe;
        this.Description = description;
        this.Identity = identity;
        this.Age = age;
        this.Job = job;
        this.Localisation = localisation;
        this.Afflilation = afflilation;
        this.Citation = citation;
        this.History = history;
        this.Logo = logo;
    }

    //-------- Setters --------
    public void setID(int id){
        this.Id = id;
    }
    public void setName(String name){
        this.Name = name;
    }
    public void setPicture(String picture){
        this.Picture = picture;
    }
    public void setClasse(String classe){
        this.Classe = classe;
    }
    public void setDescription(String description){
        this.Description = description;
    }
    public void setIdentity(String identity){
        this.Identity = identity;
    }
    public void setAge(String age){
        this.Age = age;
    }
    public void setJob(String job){
        this.Job = job;
    }
    public void setLocalisation(String localisation){
        this.Localisation = localisation;
    }
    public void setAfflilation(String afflilation){
        this.Afflilation = afflilation;
    }
    public void setCitation(String citation){
        this.Citation = citation;
    }
    public void setHistory(String history){
        this.History = history;
    }
    public void setLogo(String logo) { this. Logo = logo;
    }

    //-------- Getters --------
    public int getId() {
        return this.Id;
    }
    public String getName() {
        return this.Name;
    }
    public String getPicture() {
        return this.Picture;
    }
    public String getClasse() {
        return this.Classe;
    }
    public String getDescription() {
        return this.Description;
    }
    public String getIdentity() {
        return this.Identity;
    }
    public String getAge() {
        return this.Age;
    }
    public String getJob() {
        return this.Job;
    }
    public String getLocalisation() {
        return this.Localisation;
    }
    public String getAfflilation() {
        return this.Afflilation;
    }
    public String getCitation() {
        return this.Citation;
    }
    public String getHistory() {
        return this.History;
    }
    public String getLogo() { return this.Logo;
    }
}
