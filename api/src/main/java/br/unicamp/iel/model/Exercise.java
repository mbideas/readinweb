package br.unicamp.iel.model;
// Generated Jul 24, 2014 7:05:35 PM by Hibernate Tools 3.2.2.GA



/**
 * Exercise generated by hbm2java
 */
public class Exercise  implements java.io.Serializable {


     private Long id;
     private Activity activity;
     private String title;
     private String description;
     private String exercise_path;
     private String answer;
     private Integer position;

    public Exercise() {
    }

	
    public Exercise(Activity activity) {
        this.activity = activity;
    }
    public Exercise(Activity activity, String title, String description, String exercise_path, String answer, Integer position) {
       this.activity = activity;
       this.title = title;
       this.description = description;
       this.exercise_path = exercise_path;
       this.answer = answer;
       this.position = position;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Activity getActivity() {
        return this.activity;
    }
    
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getExercise_path() {
        return this.exercise_path;
    }
    
    public void setExercise_path(String exercise_path) {
        this.exercise_path = exercise_path;
    }
    public String getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Integer getPosition() {
        return this.position;
    }
    
    public void setPosition(Integer position) {
        this.position = position;
    }




}


