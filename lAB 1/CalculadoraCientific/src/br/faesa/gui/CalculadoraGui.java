package br.faesa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CalculadoraGui implements ActionListener{

	private JFrame frmCalculadora;
	private JTextField textField;
	private JPanel panel_Excluir;
	private JButton btn_Excluir;
	private JPanel panel_Teclado;
	private JButton btn_7;
	private JButton btn_8;
	private JButton btn_9;
	private JButton btn_div;
	private JButton btn_4;
	private JButton btn_5;
	private JButton btn_6;
	private JButton btn_X;
	private JButton btn_1;
	private JButton btn_2;
	private JButton btn_3;
	private JButton btn_menos;
	private JButton btn_ponto;
	private JButton btn_0;
	private JButton btn_igual;
	private JButton btn_mais;
	private JButton btn_pow;
	private JButton btn_sqr;
	private JButton btn_negate;
	private JButton btn_bLog;
	private JButton btn_P;
	private JButton btn_inv;
	private JButton btn_Primo;
	private JButton btn_iP;
	private JButton btn_Mostra;
	private JButton btnToggle;
	
	private boolean ligado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraGui window = new CalculadoraGui();
					window.frmCalculadora.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculadoraGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalculadora = new JFrame();
		frmCalculadora.setTitle("Calculadora");
		frmCalculadora.setSize(new Dimension(360, 320));
		frmCalculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadora.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frmCalculadora.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 0, 344, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		panel_Excluir = new JPanel();
		panel_Excluir.setBackground(new Color(192, 192, 192));
		panel_Excluir.setBounds(0, 35, 344, 34);
		panel.add(panel_Excluir);
		panel_Excluir.setLayout(null);
		
		btn_Excluir = new JButton("Excluir");
		btn_Excluir.setBounds(115, 0, 114, 34);
		panel_Excluir.add(btn_Excluir);
		
		btn_Mostra = new JButton("Mostra");
		btn_Mostra.setBounds(1, 0, 114, 34);
		panel_Excluir.add(btn_Mostra);
		
		btnToggle = new JButton("Toggle");
		btnToggle.setBounds(229, 0, 114, 34);
		panel_Excluir.add(btnToggle);
		
		panel_Teclado = new JPanel();
		panel_Teclado.setBackground(new Color(0, 128, 128));
		panel_Teclado.setBounds(0, 69, 344, 212);
		panel.add(panel_Teclado);
		panel_Teclado.setLayout(new GridLayout(4, 4, 0, 0));
		
		btn_7 = new JButton("7");
		panel_Teclado.add(btn_7);
		
		btn_8 = new JButton("8");
		panel_Teclado.add(btn_8);
		
		btn_9 = new JButton("9");
		panel_Teclado.add(btn_9);
		
		btn_pow = new JButton("^");
		panel_Teclado.add(btn_pow);
		
		btn_sqr = new JButton("\u221A");
		panel_Teclado.add(btn_sqr);
		
		btn_bLog = new JButton("bLog");
		panel_Teclado.add(btn_bLog);
		
		btn_4 = new JButton("4");
		panel_Teclado.add(btn_4);
		
		btn_5 = new JButton("5");
		panel_Teclado.add(btn_5);
		
		btn_6 = new JButton("6");
		panel_Teclado.add(btn_6);
		
		btn_P = new JButton("(");
		panel_Teclado.add(btn_P);
		
		btn_X = new JButton("X");
		panel_Teclado.add(btn_X);
		
		btn_div = new JButton("/");
		panel_Teclado.add(btn_div);
		
		btn_1 = new JButton("1");
		panel_Teclado.add(btn_1);
		
		btn_2 = new JButton("2");
		panel_Teclado.add(btn_2);
		
		btn_3 = new JButton("3");
		panel_Teclado.add(btn_3);
		
		btn_iP = new JButton(")");
		panel_Teclado.add(btn_iP);
		
		btn_mais = new JButton("+");
		panel_Teclado.add(btn_mais);
		
		btn_menos = new JButton("-");
		panel_Teclado.add(btn_menos);
		
		btn_0 = new JButton("0");
		panel_Teclado.add(btn_0);
		
		btn_ponto = new JButton(".");
		panel_Teclado.add(btn_ponto);
		
		btn_igual = new JButton("=");
		panel_Teclado.add(btn_igual);
		
		btn_Primo = new JButton("Prm");
		panel_Teclado.add(btn_Primo);
		
		btn_negate = new JButton("-1");
		panel_Teclado.add(btn_negate);
		
		btn_inv = new JButton("1/X");
		panel_Teclado.add(btn_inv);
			
		for (Component c : this.panel_Teclado.getComponents()) {
			((JButton)c).addActionListener(this);
			((JButton)c).setBackground(Color.lightGray);
			((JButton)c).setFont(new Font("Tahoma", Font.PLAIN, 11));
		}
		
		for (Component c : this.panel_Excluir.getComponents()) {
			if (c instanceof JButton )
				((JButton)c).addActionListener(this);
		}
	}
	
	private void Toggle(boolean Ligado){
		if(Ligado){
			for (Component c : this.panel_Teclado.getComponents()) {
				((JButton)c).setBackground(Color.BLACK);
				((JButton)c).setFont(new Font("Tahoma", Font.PLAIN, 11));
			}
		
			for (Component c : this.panel_Excluir.getComponents()) {
				if (c instanceof JButton )
					((JButton)c).setBackground(Color.BLACK);
				}
			}else{
				for (Component c : this.panel_Teclado.getComponents()) {
					((JButton)c).setBackground(Color.lightGray);
					((JButton)c).setFont(new Font("Tahoma", Font.PLAIN, 11));
				}
			
				for (Component c : this.panel_Excluir.getComponents()) {
					if (c instanceof JButton )
						((JButton)c).setBackground(Color.white);
				}
			}	
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = ((JButton)e.getSource()).getText().toString();
		switch (key) {
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case ".":
			try{
				System.out.println("-------------------------");
				CalculadoraOp.setDigito(key);
				CalculadoraOp.coletaOperando();
				System.out.println("Digito: "+CalculadoraOp.getDigito());
				System.out.println("Operando: "+CalculadoraOp.getOperando());
				System.out.println("Expressao: "+CalculadoraOp.getExpressao());
				System.out.println("-------------------------");
				textField.setText(CalculadoraOp.getOperando().toString());
				break;
				}catch (Exception e1) {
					System.out.println(e1.getMessage());
					textField.setText(e1.getMessage());
					CalculadoraOp.excluirExpressao();
				}
				break;
		case "-1":
			try{
				System.out.println("-------------------------");
				CalculadoraOp.Negativar();
				System.out.println("Digito: "+CalculadoraOp.getDigito());
				System.out.println("Operando: "+CalculadoraOp.getOperando());
				System.out.println("Expressao: "+CalculadoraOp.getExpressao());
				System.out.println("-------------------------");
				textField.setText(CalculadoraOp.getOperando().toString());
				break;
			}catch (Exception e1) {
				System.out.println(e1.getMessage());
				textField.setText(e1.getMessage());
				CalculadoraOp.excluirExpressao();
			}case "1/X":
				try{
					System.out.println("-------------------------");
					CalculadoraOp.Inverte();
					System.out.println("Digito: "+CalculadoraOp.getDigito());
					System.out.println("Operando: "+CalculadoraOp.getOperando());
					System.out.println("Expressao: "+CalculadoraOp.getExpressao());
					System.out.println("-------------------------");
					textField.setText(CalculadoraOp.getOperando().toString());
					break;
				}catch (Exception e1) {
					System.out.println(e1.getMessage());
					textField.setText(e1.getMessage());
					CalculadoraOp.excluirExpressao();
				}
		case "Prm":
			System.out.println("-------------------------");
			CalculadoraOp.Primo();
			System.out.println("Expressao: "+CalculadoraOp.getExpressao());
			System.out.println("-------------------------");
			textField.setText(CalculadoraOp.getMensage().toString());
			break;
		case "+":
		case "-":
		case "X":
		case "/":
		case "^":
		case "\u221A":
		case "bLog":
		case "(":
		case ")":	
			System.out.println("-------------------------");
			CalculadoraOp.setOperacao(key);
			CalculadoraOp.montaExpressao();
			System.out.println("Expressao: "+CalculadoraOp.getExpressao());
			System.out.println("-------------------------");
			textField.setText(CalculadoraOp.getExpressao().toString());
			break;
		case "=":
			try{
				System.out.println("-------------------------");
				System.out.println("Expressao: "+CalculadoraOp.getExpressao());
				CalculadoraOp.efetuaResultado();
				System.out.println("-------------------------");
				textField.setText(CalculadoraOp.getMensage().toString());
			}catch (Exception e1) {
				System.out.println(e1.getMessage());
				textField.setText("Expressão Inválida!");
				CalculadoraOp.excluirExpressao();
			}
			break;
		case "Excluir":
			System.out.println("-------------------------");
			CalculadoraOp.excluirExpressao();
			System.out.println(CalculadoraOp.getExpressao());
			System.out.println("-------------------------");
			textField.setText(CalculadoraOp.getExpressao().toString());
			break;
		case "Mostra":
			System.out.println("-------------------------");
			System.out.println(CalculadoraOp.getExpressao());
			System.out.println("-------------------------");
			textField.setText(CalculadoraOp.getExpressao().toString());
			break;
		case "Toggle":
			if (this.ligado){
				Toggle(this.ligado);
				this.ligado=false;
			}else{
				Toggle(this.ligado);
				this.ligado=true;
			}
			break;
		default:
			break;
		}
		
	}
}
