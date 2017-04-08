

package listacontactos;

import java.util.GregorianCalendar;


public class Contacto extends Persona{
  private int grupo;
  private String telefono;
  private String email;
  private String grupos []={"Familia","Amigos","Trabajo"};
  public static final int FAMILIA=1;  
  public static final int AMIGOS=2;    
  public static final int TRABAJO=3;   
   
  
    public Contacto(int grupo, String telefono, String email, String nombre, String apellidos,int dia,int mes,int año) {
        super(nombre, apellidos, dia, mes, año);
        this.grupo = grupo;
        this.telefono = telefono;
        this.email = email;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int g) {
       if(g>=0 && g<=2)
            this.grupo = g;
        else this.grupo=1; //por defecto el grupo es AMIGOS
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
         if(!telefono.isEmpty()) {
             this.telefono = telefono;
         }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.length()>0){
            this.email = email;
        }
    }

   
    @Override
    public String toString() {
        return super.toString()+"\t"+ telefono + "\t"+email + "\t"+grupos[grupo-1];
    }
    
    
    
    
    
    
}
