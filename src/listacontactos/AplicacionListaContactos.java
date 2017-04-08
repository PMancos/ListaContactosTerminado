

package listacontactos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AplicacionListaContactos {

    public static Scanner teclado=new Scanner(System.in);
    public static void main(String[] args) {
        //CREACIÓN DEL ARRAY DE CONTACTOS
       try {
          ArrayList <Contacto> listaContacto=null;
        int op;
        boolean modificado=false; //Si se ha modificado se graba.
        File f=new File("Contactos.dat");
        
        if(!f.exists()){
            listaContacto=new ArrayList();
        }
        else{
            
                FileInputStream fis=new FileInputStream(f);
                ObjectInputStream ois=new ObjectInputStream(fis);
                
                listaContacto = (ArrayList <Contacto>) ois.readObject();
                
                fis.close();
                ois.close();
        }    
            
        
        
        do{
         op=menu();
            
          switch(op){
              
              case 1: AñadirContacto(listaContacto);
                      modificado=true;  break;
                  
              case 2: Collections.sort(listaContacto);
                      mostrarListaContactos(listaContacto);  break;
                  
              case 3: felicitarCumpleaños(listaContacto); break;
                  
              case 4: buscarContacto(listaContacto);   break;
              
              case 5: borrarContacto(listaContacto); 
                      modificado=true;   break;
              
              case 6: if(modificado){
                  
                     FileOutputStream fos=new FileOutputStream(f);
                     ObjectOutputStream oos=new ObjectOutputStream(fos);
                  
                     oos.writeObject(listaContacto);
                     oos.close();
                     fos.close();
                       }else{
                          System.out.println("No ha habido cambios, no se ha guardado");
                        }
              
                          System.out.println("Datos guardados correctamente.");
                          break;
              
          
          }  
            
            
            
            
        }while(op!=6);
        
            }catch (EOFException ex){
            System.out.println("_________________");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AplicacionListaContactos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) { //Solo estas dos
                Logger.getLogger(AplicacionListaContactos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AplicacionListaContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    public static int menu(){
        int op=0;
        System.out.println("** 1.Añadir contacto **");
        System.out.println("** 2.Ordenar contactos (Apellido/Nombre) **");
        System.out.println("** 3.Felicitar cumple **");
        System.out.println("** 4.Buscar contacto **");
        System.out.println("** 5.Borrar contacto **");
        System.out.println("** 6.Salir  **");
        do{
        System.out.println("Introduce la opción deseada (1-6):");
        op=pedirNumeroEntero();
        }while(op < 1 || op>6);
        return op;
    }
    public static void AñadirContacto(ArrayList <Contacto> listaContacto){
        int dia,mes,año,grupo=0;
        
        
        
        
        System.out.println("Introduce el nombre: ");
        teclado.nextLine();
        String nombre=teclado.nextLine();
        System.out.println("Introduce los apellidos: ");
        String apellidos=teclado.nextLine();
        System.out.println("Introduce el teléfono: ");
        String telefono=teclado.nextLine();
        System.out.println("Introduce el e-mail: ");
        String email=teclado.nextLine();
        System.out.println("Introduce el día de nacimiento: ");
        dia=pedirNumeroEntero();
       
        System.out.println("Introduce el mes de nacimiento: ");
        mes=pedirNumeroEntero();
        System.out.println("Introduce el año de nacimiento: ");
        año=pedirNumeroEntero();
        do{
            System.out.println("** Grupos **");
            System.out.println("____________");
            System.out.println("1.Familia\n2.Amigos\n3.Trabajo");
        System.out.println("Introduce a qué grupo quieres añadirlo:");
        grupo=pedirNumeroEntero();
        }while(grupo!= 1 && grupo!=2 && grupo!=3);
       
        System.out.println("Contacto añadido correctamente.");
        listaContacto.add(new Contacto(grupo, telefono, email, nombre, apellidos, dia, mes, año));
    }
    public static void mostrarListaContactos(ArrayList <Contacto> listaContactos){
        System.out.println("Apellido\tNombre\t   DNI  \tFecha Nac\tEdad\tTelefono\temail    \tGrupo");       
      System.out.println("________\t______\t   ___  \t_________\t____\t________\t_____    \t_____");
        if(listaContactos.isEmpty()){
            System.out.println("La lista está vacía.");
        }else{
        for(int i=0;i<listaContactos.size();i++){
            System.out.println(listaContactos.get(i).toString());
        }
        }
    }
    public static void buscarContacto(ArrayList <Contacto> listaContacto){
        int i=0;
        boolean encontrado=false;
        String nombre;
        System.out.println("\nA qué contacto deseas buscar? Introduce su nombre:");
        teclado.nextLine();
        nombre=teclado.nextLine();
        
        while(encontrado==false && i<listaContacto.size()){
           
            if(nombre.equalsIgnoreCase(listaContacto.get(i).getNombre())){
               encontrado=true;
                System.out.println("Apellido\tNombre\t   DNI  \tFecha Nac\tEdad\tTelefono\temail    \tGrupo");       
      System.out.println("________\t______\t   ___  \t_________\t____\t________\t_____    \t_____");
               System.out.println(listaContacto.get(i).toString());
           }
            else{
               i++;
            } 
        }
     }
    public static  void borrarContacto(ArrayList <Contacto> listaContacto){
        int i=0;
        boolean encontrado=false;
        String nombre;
        System.out.println("A qué contacto deseas buscar? Introduce su nombre:");
        teclado.nextLine();
        nombre=teclado.nextLine();
        
        while(encontrado==false && i<listaContacto.size()){
           
            if(nombre.equalsIgnoreCase(listaContacto.get(i).getNombre())){
               encontrado=true;
               listaContacto.remove(i);
               System.out.println("Contacto eliminado correctamente.");
           }
            else{
               i++;
            } 
        }
     }
    public static int pedirNumeroEntero(){
       int n=0;
       try{
         n =teclado.nextInt();
       }
       catch(InputMismatchException e){
           System.out.println("Error no has introducido un entero");
           teclado.nextLine();
       }
       return n;
  }
    public static void felicitarCumpleaños(ArrayList <Contacto> listaContacto){
       //crear un objeto con la fecha de hoy llamando al constructor sin parÃ¡metros
      GregorianCalendar hoy=new GregorianCalendar();
      int mHoy,dHoy,mNac,dNac;
      mHoy=hoy.get(Calendar.MONTH)+1;
      dHoy=hoy.get(Calendar.DAY_OF_MONTH);
//recorrer la lista de contactos para saber si alguien cumple hoy
       for(int i=0;i<listaContacto.size();i++){
           mNac=listaContacto.get(i).getFechaNac().get(Calendar.MONTH)+1;
           dNac=listaContacto.get(i).getFechaNac().get(Calendar.DAY_OF_MONTH);      
            if(mHoy==mNac && dHoy==dNac)
            {   //calcular los aÃ±os que cumple
                System.out.println("****************************************");
                System.out.println("\t\tZorionak " +listaContacto.get(i).getNombre()+
                "!!!\n\tHoy cumple "+listaContacto.get(i).calcularEdad()+" años  "
                        + "\n\tTel:"+listaContacto.get(i).getTelefono()+
                 "\n\te-mail: "+listaContacto.get(i).getEmail());
                System.out.println("****************************************");
            } 
     }
       
     
    }

}
