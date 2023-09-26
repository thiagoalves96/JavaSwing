package farmacia;

import java.awt.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

	JLabel lblUsuario, lblSenha, lblMensagem;
	JTextField txtUsuario;
	JPasswordField txtSenha;
	JButton btnLogin, btnCadastro;

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

		btnCadastro = new JButton("Cadastro");
		btnCadastro.setBounds(200, 120, 100, 25);
		btnCadastro.addActionListener(this);

		lblMensagem = new JLabel("");
		lblMensagem.setBounds(50, 160, 200, 25);

		this.add(lblUsuario);
		this.add(txtUsuario);
		this.add(lblSenha);
		this.add(txtSenha);
		this.add(btnLogin);
		this.add(btnCadastro);
		this.add(lblMensagem);
	}

	public void verificaLogin() {
		String usuario = txtUsuario.getText();
		String senha = new String(txtSenha.getPassword());

		if (usuario.equals("admin") && senha.equals("123")) {
			lblMensagem.setText("Seja bem-vindo, Administrador");
			lblMensagem.setForeground(Color.blue);

		} else if (usuario.equals("usuario") && senha.equals("456")) {
			lblMensagem.setText("Seja bem-vindo, Usuário");
			lblMensagem.setForeground(Color.black);
		} else {
			lblMensagem.setText("Usuário ou Senha estão incorretos");
			lblMensagem.setForeground(Color.red);
		}
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			verificaLogin();
		} else if (e.getSource() == btnCadastro) {
			// Fechar Login e Chamar Cadastro
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
