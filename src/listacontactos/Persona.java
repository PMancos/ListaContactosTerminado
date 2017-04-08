

package listacontactos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;


public abstract class Persona implements Serializable,Comparable <Persona>{
    //ATRIBUTOS 
    private String nombre;
    private String apellidos;
    private int dia,mes,año;
    private GregorianCalendar fechaNac; 

    public Persona(String nombre, String apellidos, int dia, int mes, int año) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.fechaNac=new GregorianCalendar(año, mes-1,dia);
    }

    public GregorianCalendar getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(int año,int mes,int dia) {
        fechaNac.set(año, (mes-1), dia); 
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

   

    @Override
    public String toString() {
       return this.apellidos + "   \t" + this.nombre +"\t"
        + fechaNac.get(Calendar.DAY_OF_MONTH)+"/"
                +(fechaNac.get(Calendar.MONTH)+1)+"/"
                +fechaNac.get(Calendar.YEAR)+"\t"+
                +this.calcularEdad(); 
    }

    @Override
    public int compareTo(Persona p) {
        if(apellidos.equalsIgnoreCase(p.getApellidos())){
            
            if(nombre.equalsIgnoreCase(p.getNombre()))
              return nombre.compareToIgnoreCase(p.getNombre());
            }
        return apellidos.compareToIgnoreCase(p.getApellidos());
    }
    
  public int calcularEdad(){
       GregorianCalendar fecHoy=new GregorianCalendar();
        //FECHA DE HOY
        int dHoy,mHoy,aHoy;
        dHoy=fecHoy.get(Calendar.DAY_OF_MONTH);
        mHoy=fecHoy.get(Calendar.MONTH)+1;
        aHoy=fecHoy.get(Calendar.YEAR);
        //FECHA NACIMIENTO
        int dNac,mNac,aNac;
        dNac=fechaNac.get(Calendar.DAY_OF_MONTH);
        mNac=fechaNac.get(Calendar.MONTH)+1;
        aNac=fechaNac.get(Calendar.YEAR);
        
        int edad;
        edad=aHoy-aNac;
        if(mNac>mHoy || (mNac==mHoy && dNac>dHoy))
            edad--;
        
        return edad;
    }  
    
    
    
    
    
    
    
    
}
