package br.unicamp.iel.model;
// Generated Jul 24, 2014 7:05:35 PM by Hibernate Tools 3.2.2.GA



/**
 * Activity generated by hbm2java
 */
public class Activity  implements java.io.Serializable {


     private Long id;
     private Module module;
     private Integer position;
     private String image;
     private String title;
     private String text;
     private String prereading;
     private Integer etaRead;

    public Activity() {
    }

	
    public Activity(Module module) {
        this.module = module;
    }
    public Activity(Module module, Integer position, String image, String title, String text, String prereading, Integer etaRead) {
       this.module = module;
       this.position = position;
       this.image = image;
       this.title = title;
       this.text = text;
       this.prereading = prereading;
       this.etaRead = etaRead;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Module getModule() {
        return this.module;
    }
    
    public void setModule(Module module) {
        this.module = module;
    }
    public Integer getPosition() {
        return this.position;
    }
    
    public void setPosition(Integer position) {
        this.position = position;
    }
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    public String getPrereading() {
        return this.prereading;
    }
    
    public void setPrereading(String prereading) {
        this.prereading = prereading;
    }
    public Integer getEtaRead() {
        return this.etaRead;
    }
    
    public void setEtaRead(Integer etaRead) {
        this.etaRead = etaRead;
    }




}


