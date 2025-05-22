import java.io.*;
import java.util.*;

public class UsuarioManager {
    private final String archivo = "usuarios.dat";
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioManager() {
        cargarDatos();
    }

    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            usuarios = (List<Usuario>) ois.readObject();
        } catch (Exception e) {
            usuarios = new ArrayList<>();
        }
    }

    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        guardarDatos();
    }

    public Usuario consultarUsuario(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public boolean actualizarUsuario(int id, String nuevoNombre, String nuevoEmail) {
        Usuario usuario = consultarUsuario(id);
        if (usuario != null) {
            usuario.setNombre(nuevoNombre);
            usuario.setEmail(nuevoEmail);
            guardarDatos();
            return true;
        }
        return false;
    }

    public boolean borrarUsuario(int id) {
        boolean eliminado = usuarios.removeIf(u -> u.getId() == id);
        if (eliminado) guardarDatos();
        return eliminado;
    }

    public void listarUsuarios() {
        usuarios.forEach(System.out::println);
    }
}