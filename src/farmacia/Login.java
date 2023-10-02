package farmacia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login extends JFrame implements ActionListener {

	JLabel lblUsuario, lblSenha, lblMensagem;
	JTextField txtUsuario;
	JPasswordField txtSenha;
	JButton btnLogin, btnCadastro, btnSair;

	private final String DB_URL = "jdbc:mysql://localhost:3306/farmacia";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "Thiag@38580828";

	public Login() {

		super(" Tela de Login");
		this.setSize(400, 300);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lblUsuario = new JLabel("Usuário :");
		lblUsuario.setBounds(50, 50, 100, 25);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(110, 50, 200, 25);

		lblSenha = new JLabel("Senha :");
		lblSenha.setBounds(50, 80, 100, 25);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(110, 80, 200, 25);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(50, 120, 100, 25);
		btnLogin.addActionListener(this);

		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(150, 120, 100, 25);
		btnCadastro.addActionListener(this);

		btnSair = new JButton("Sair");
		btnSair.setBounds(250, 120, 100, 25);
		btnSair.addActionListener(this);

		lblMensagem = new JLabel("");
		lblMensagem.setBounds(50, 160, 200, 25);

		this.add(lblUsuario);
		this.add(txtUsuario);
		this.add(lblSenha);
		this.add(txtSenha);
		this.add(btnLogin);
		this.add(btnCadastro);
		this.add(btnSair);

		this.add(lblMensagem);
	}

	public void verificaLogin() {
		String usuario = txtUsuario.getText();
		String senha = new String(txtSenha.getPassword());

		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String query = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, usuario);
			preparedStatement.setString(2, senha);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String nomeUsuario = resultSet.getString("nome");
				lblMensagem.setText("Login realizado com sucesso para " + nomeUsuario);
				lblMensagem.setForeground(Color.blue);
			} else {
				lblMensagem.setText("Usuário ou Senha estão incorretos");
				lblMensagem.setForeground(Color.red);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			lblMensagem.setText("Erro ao conectar ao banco de dados");
			lblMensagem.setForeground(Color.red);
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			verificaLogin();
		} else if (e.getSource() == btnCadastro) {
			// Fechar Login e Chamar Cadastro
			dispose();
			Cadastro cadastro = new Cadastro();
			cadastro.setVisible(true);
		} else if (e.getSource() == btnSair) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame janela = new Login();
			janela.setVisible(true);
		});
	}
}
