import javax.swing.*;

public class RegistroGUI extends JFrame {

    JTextField txtUsuario;
    JPasswordField txtPassword;
    JButton btnRegistrar;

    public RegistroGUI() {
        setTitle("Registro de Usuario");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 30, 140, 25);
        add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 70, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 70, 140, 25);
        add(txtPassword);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(90, 120, 120, 30);
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contraseña = String.valueOf(txtPassword.getPassword());

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Los campos son obligatorios");
                return;
            }

            Registro registro = new Registro();
            registro.registrar(usuario, contraseña);

            JOptionPane.showMessageDialog(this, "Usuario registrado");
            dispose();
        });

        setVisible(true);
    }
}
