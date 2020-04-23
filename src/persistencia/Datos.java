package persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import dominioproyecto.Proyecto;
import dominioproyecto.TipoItem;
import dominioproyecto.Usuario;

public class Datos {
	
	@SuppressWarnings("unchecked")
	public HashMap<String,Usuario> cargarUsuarios(HashMap<String,Usuario> usuarios) {
		try {
			FileInputStream fi = new FileInputStream(("Usuarios.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			usuarios = (HashMap<String,Usuario>) oi.readObject();
			oi.close();
			fi.close();		
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			//
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String,TipoItem> cargarTipoItems(HashMap<String,TipoItem> tipoItems) {
		try {
			FileInputStream fi = new FileInputStream(("TipoItems.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			tipoItems = (HashMap<String,TipoItem>) oi.readObject();
			oi.close();
			fi.close();		
	
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			
		} catch (IOException e) {
			//
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return tipoItems;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String,Proyecto> cargarProyectos(HashMap<String,Proyecto> proyectos) {
		try {
			FileInputStream fi = new FileInputStream(("Proyectos.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			proyectos = (HashMap<String,Proyecto>) oi.readObject();
			oi.close();
			fi.close();		
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			//
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return proyectos;
	}
	
	public void actualizarProyectos(HashMap<String,Proyecto> proyectos) {
		try {
			FileOutputStream f = new FileOutputStream(("Proyectos.txt"));
			ObjectOutputStream ou = new ObjectOutputStream(f);
			
			ou.writeObject(proyectos);
			ou.flush();
			ou.close();
			f.close();
	
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			//
		} 
	}
	
	public void actualizarUsuarios(HashMap<String,Usuario> usuarios) {
		try {
			FileOutputStream f = new FileOutputStream(("Usuarios.txt"));
			ObjectOutputStream ou = new ObjectOutputStream(f);
			
			ou.writeObject(usuarios);
			ou.flush();
			ou.close();
			f.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			//
		} 
	}
	
	public void actualizarTipoItems(HashMap<String,TipoItem> tipoItems) {
		try {
			FileOutputStream f = new FileOutputStream(("TipoItems.txt"));
			ObjectOutputStream ou = new ObjectOutputStream(f);
			
			ou.writeObject(tipoItems);
			ou.flush();
			ou.close();
			f.close();
	
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			//
		} 
	}
}
