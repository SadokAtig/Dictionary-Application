import java.io.Serializable;

public class dictionnaire_frensh implements Serializable {
    
    public String idf;
    public String wordf ;
    public String traductionf ;
    public String typef ;
    public String exemplef ;
    public String exemplee ;
 

public String getExemplee() {
    return exemplee;
}
public String getExemplef() {
    return exemplef;
}
public String getIdf() {
    return idf;
}
public String getTraductionf() {
    return traductionf;
}
public String getTypef() {
    return typef;
}
public String getWordf() {
    return wordf;
}
public void setExemplee(String exemplee) {
    this.exemplee = exemplee;
}
public void setExemplef(String exemplef) {
    this.exemplef = exemplef;
}
public void setIdf(String idf) {
    this.idf = idf;
}
public void setTraductionf(String traductionf) {
    this.traductionf = traductionf;
}
public void setTypef(String typef) {
    this.typef = typef;
}
public void setWordf(String wordf) {
    this.wordf = wordf;
}
public String getSourceText() {
    return null;
}
public String getTargetText() {
    return null;
}






}
