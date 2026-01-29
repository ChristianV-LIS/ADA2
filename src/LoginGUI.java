import javax.swing.*;

public class LoginGUI extends JFrame {

    JTextField txtUsuario;
    JPasswordField txtPassword;
    JButton btnLogin, btnRegistro;

    public LoginGUI() {
        setTitle("Login");
        setSize(350, 220);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 30, 160, 25);
        add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 70, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 70, 160, 25);
        add(txtPassword);

        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(40, 120, 120, 30);
        add(btnLogin);

        btnRegistro = new JButton("Registrarse");
        btnRegistro.setBounds(180, 120, 120, 30);
        add(btnRegistro);

        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contraseña = String.valueOf(txtPassword.getPassword());

            Login login = new Login();

            if (login.validar(usuario, contraseña)) {
                dispose();
                new CapturaCalificacionesGUI();
            } else {
                JOptionPane.showMessageDialog(this, "Acceso denegado");
            }
        });

        btnRegistro.addActionListener(e -> {
            new RegistroGUI();
        });

        setVisible(true);
    }
}
