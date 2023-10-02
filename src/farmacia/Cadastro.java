package farmacia;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Cadastro extends JFrame {

	private JTextField campoNome, campoUsuario;
	private JPasswordField campoSenha, campoConfirmaSenha;
	private JButton botaoCadastrarUsuario, btnLogin;

	private final String DB_URL = "jdbc:mysql://localhost:3306/farmacia";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "Thiag@38580828";

	public Cadastro() {
		setTitle("Cadastro de Usuario (FARMACIA)");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 250);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
		campoNome = new JTextField();
		campoUsuario = new JTextField();
		campoSenha = new JPasswordField();
		campoConfirmaSenha = new JPasswordField();
		botaoCadastrarUsuario = new JButton("Cadastrar-se");
		btnLogin = new JButton("Login");

		panel.add(new JLabel("Nome: "));
		panel.add(campoNome);
		panel.add(new JLabel("E-mail: "));
		panel.add(campoUsuario);
		panel.add(new JLabel("Senha: "));
		panel.add(campoSenha);
		panel.add(new JLabel("Confirma a senha: "));
		panel.add(campoConfirmaSenha);
		add(panel);

		panel.add(botaoCadastrarUsuario);
		botaoCadastrarUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = campoNome.getText();
				String email = campoUsuario.getText();
				String senha = new String(campoSenha.getPassword());
				String confirmarSenha = new String(campoConfirmaSenha.getPassword());

				// Verifique se os campos obrigatórios estão preenchidos
				if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!senha.equals(confirmarSenha)) {
					JOptionPane.showMessageDialog(null, "As senhas não se coincidem!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					String query = "INSERT INTO usuario(nome,email,senha) VALUES(?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, nome);
					preparedStatement.setString(2, email);
					preparedStatement.setString(3, senha);
					preparedStatement.executeUpdate();

					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
					preparedStatement.close();
					// Após o cadastro bem-sucedido, abrir a tela de login
					dispose(); // Fecha a tela de cadastro
					Login login = new Login(); // Cria uma instância da tela de login
					login.setVisible(true); // Exibe a tela de login
				} catch (Exception e2) {

				}

			}
		});
		panel.add(btnLogin);
		btnLogin.setBounds(200, 160, 100, 25);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Fechar tela de cadastro e abrir tela de login
				dispose(); // Fecha a tela de cadastro
				Login login = new Login(); // Cria uma instância da tela de login
				login.setVisible(true); // Exibe a tela de login
			}
		});

	}
}