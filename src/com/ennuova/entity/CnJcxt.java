package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * CnJcxt entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="CN_JCXT")
public class CnJcxt  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String fnumber;
     private String fname;
     private String fdiscribe;


    // Constructors

    /** default constructor */
    public CnJcxt() {
    }

	/** minimal constructor */
    public CnJcxt(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public CnJcxt(Long id, String fnumber, String fname, String fdiscribe) {
        this.id = id;
        this.fnumber = fnumber;
        this.fname = fname;
        this.fdiscribe = fdiscribe;
    }

   
    // Property accessors
    @Id 
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_JCXT",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
    @Column(name="ID", unique=true, nullable=false, precision=16, scale=0)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="FNUMBER", length=30)

    public String getFnumber() {
        return this.fnumber;
    }
    
    public void setFnumber(String fnumber) {
        this.fnumber = fnumber;
    }
    
    @Column(name="FNAME", length=50)

    public String getFname() {
        return this.fname;
    }
    
    public void setFname(String fname) {
        this.fname = fname;
    }
    
    @Column(name="FDISCRIBE", length=256)

    public String getFdiscribe() {
        return this.fdiscribe;
    }
    
    public void setFdiscribe(String fdiscribe) {
        this.fdiscribe = fdiscribe;
    }
   








}