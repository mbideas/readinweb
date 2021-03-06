package br.unicamp.iel.model;
// Generated Feb 6, 2015 10:24:22 AM by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * ReadInWebUserControl generated by hbm2java
 */
public class ReadInWebUserControl  implements java.io.Serializable {


     private Long id;
     private byte state;
     private Date blockDate;
     private Date evalDate;
     private String user;
     private String site;
     private Integer blocks;

    public ReadInWebUserControl() {
    }

	
    public ReadInWebUserControl(byte state) {
        this.state = state;
    }
    public ReadInWebUserControl(byte state, Date blockDate, Date evalDate, String user, String site, Integer blocks) {
       this.state = state;
       this.blockDate = blockDate;
       this.evalDate = evalDate;
       this.user = user;
       this.site = site;
       this.blocks = blocks;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public byte getState() {
        return this.state;
    }
    
    public void setState(byte state) {
        this.state = state;
    }
    public Date getBlockDate() {
        return this.blockDate;
    }
    
    public void setBlockDate(Date blockDate) {
        this.blockDate = blockDate;
    }
    public Date getEvalDate() {
        return this.evalDate;
    }
    
    public void setEvalDate(Date evalDate) {
        this.evalDate = evalDate;
    }
    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    public String getSite() {
        return this.site;
    }
    
    public void setSite(String site) {
        this.site = site;
    }
    public Integer getBlocks() {
        return this.blocks;
    }
    
    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }




}


