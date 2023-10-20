import java.io.Serializable;

public class obj implements  Serializable {
    private String  Mot_E;
    private String Mot_F ;
    private String Type_E;
    private String  Expl_E;
    private String Expl_F ;
    
    
public obj() {
    }
public obj(String mot_E, String mot_F, String type_E, String expl_E, String expl_F) {
        Mot_E = mot_E;
        Mot_F = mot_F;
        Type_E = type_E;
        Expl_E = expl_E;
        Expl_F = expl_F;
    }
public String getExpl_E() {
    return Expl_E;
}
public String getExpl_F() {
    return Expl_F;
}
public String getMot_E() {
    return Mot_E;
}
public void setExpl_E(String expl_E) {
    Expl_E = expl_E;
}
public void setMot_F(String mot_F) {
    Mot_F = mot_F;
}
public void setMot_E(String mot_E) {
    Mot_E = mot_E;
}
public String getType_E() {
    return Type_E;
}
public String getMot_F() {
    return Mot_F;
}public void setExpl_F(String expl_F) {
    Expl_F = expl_F;
}
public void setType_E(String type_E) {
    Type_E = type_E;
}
  


public String Affichage() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(Mot_E);
    buffer.append("\t");
    buffer.append(Mot_F);
    buffer.append("\t");
    buffer.append(Type_E);
    buffer.append("\t");
    buffer.append(Expl_E);
    buffer.append("\t");
    buffer.append(Expl_F);
    buffer.append("\t");
    buffer.append("\n");
    return buffer.toString();
}
public String AffichageFiltrage() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(Mot_E);
    buffer.append(",");
    buffer.append(Mot_F);
    buffer.append(",");
    buffer.append(Type_E);
    buffer.append(",");
    buffer.append(Expl_E);
    buffer.append(",");
    buffer.append(Expl_F);
    buffer.append(",");

    return buffer.toString();
}
@Override
public String toString() {
    return  Mot_E  + Mot_F + Type_E + Expl_E + Expl_F ;
}
}