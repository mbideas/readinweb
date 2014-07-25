package br.unicamp.iel.model;
// Generated Jul 24, 2014 7:05:35 PM by Hibernate Tools 3.2.2.GA



/**
 * Question generated by hbm2java
 */
public class Question  implements java.io.Serializable {


     private Long id;
     private Activity activity;
     private Integer position;
     private String question;
     private String suggestedAnswer;

    public Question() {
    }

	
    public Question(Activity activity) {
        this.activity = activity;
    }
    public Question(Activity activity, Integer position, String question, String suggestedAnswer) {
       this.activity = activity;
       this.position = position;
       this.question = question;
       this.suggestedAnswer = suggestedAnswer;
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
    public Integer getPosition() {
        return this.position;
    }
    
    public void setPosition(Integer position) {
        this.position = position;
    }
    public String getQuestion() {
        return this.question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getSuggestedAnswer() {
        return this.suggestedAnswer;
    }
    
    public void setSuggestedAnswer(String suggestedAnswer) {
        this.suggestedAnswer = suggestedAnswer;
    }




}


