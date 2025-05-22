public class Main {
    public static void main(String[] args) {
        UsuarioManager manager = new UsuarioManager();

        manager.crearUsuario(new Usuario(1, "Juan", "juan@mail.com"));
        manager.crearUsuario(new Usuario(2, "Ana", "ana@mail.com"));

        System.out.println("Usuarios actuales:");
        manager.listarUsuarios();

        System.out.println("\nConsultando usuario con ID 1:");
        System.out.println(manager.consultarUsuario(1));

        manager.actualizarUsuario(2, "Ana López", "ana.lopez@mail.com");
        manager.borrarUsuario(1);

        System.out.println("\nDespués de actualizar y borrar:");
        manager.listarUsuarios();
    }
}