package interfaz;
import gestion.Sistema;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Interfaz extends JFrame {
	
	//Elementos globales necesarios para el sistema 
	private Sistema sistema = new Sistema();
	
	private String proyectoactual = null;
	private String usuarioactual = null;
	private int prioridadUser;
	
	//Creación propia de JFrame
	private JPanel contentPane;
	private JTextField userid;
	private JPasswordField passwordField;
	private JTextField nombreUser;
	private JTextField userID2;
	private JPasswordField passwordField_1;
	private JTextField nombreProy;
	private JTextField NombreItem;
	private JTextField CampoPrioridad;
	private JTextField fecha;
	private JTextField nombreTI;
	private JTextField nombEstado;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		
		//Listas con datos para cargar listas del frame
		ArrayList<String> proyectos = sistema.getListProyectos();
		ArrayList<String> tipoItems = sistema.getListTipoItems();
		ArrayList<String> usuarios = sistema.getListUsuarios();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 430);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProyecto = new JMenu("Proyecto");
		menuBar.add(mnProyecto);
		
		JMenuItem mntmCrear = new JMenuItem("Crear");
		mntmCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "cpp");
			}
		});
		mnProyecto.add(mntmCrear);
		
		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		mnProyecto.setEnabled(false);
		mnUsuario.setEnabled(false);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel loginPanel = new JPanel();
		contentPane.add(loginPanel, "lp");
		
		JLabel lblUserid = new JLabel("userID:");
		lblUserid.setBounds(215, 139, 49, 14);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(215, 195, 61, 14);
		
		userid = new JTextField();
		userid.setBounds(305, 136, 106, 20);
		userid.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:  ");
		lblUsuario.setBounds(10, 1, 139, 13);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JPanel welcomePanel = new JPanel();
		contentPane.add(welcomePanel, "wp");
		
		JLabel campoUser = new JLabel("");
		campoUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		campoUser.setBounds(10, 11, 250, 14);
		welcomePanel.add(campoUser);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(199, 273, 83, 23);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuarios.contains(userid.getText())) {
					if (sistema.isCorrectPassword(userid.getText(), String.valueOf(passwordField.getPassword()))) {
						usuarioactual = userid.getText();
						campoUser.setText("Usuario:  " + usuarioactual);
						CardLayout cl = (CardLayout)(contentPane.getLayout());
						cl.show(contentPane, "wp");
						mnProyecto.setEnabled(true);
						mnUsuario.setEnabled(true);
						lblUsuario.setText("Usuario:  " + usuarioactual);
						userid.setText("");
						passwordField.setText("");
						//Mostrar notificaciones si es que hay
						for (String notificacion : sistema.getNotificaciones(usuarioactual)) {
							JOptionPane.showMessageDialog(contentPane, notificacion, "Notificación", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Password incorrecto", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "UserID incorrecto", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setBounds(305, 192, 106, 20);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(320, 273, 90, 23);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userid.setText("");
				passwordField.setText("");
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "rp");
			}
		});
		
		JLabel lblLoginDeUsuarios = new JLabel("Login de Usuario");
		lblLoginDeUsuarios.setBounds(234, 54, 149, 25);
		lblLoginDeUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loginPanel.setLayout(null);
		loginPanel.add(btnIngresar);
		loginPanel.add(btnRegistrar);
		loginPanel.add(lblUserid);
		loginPanel.add(lblPassword);
		loginPanel.add(userid);
		loginPanel.add(passwordField);
		loginPanel.add(lblLoginDeUsuarios);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(152, 102, 329, 14);
		loginPanel.add(separator_4);
		
		JLabel lblIngenieraDeSoftware = new JLabel("Ingenier\u00EDa de Software I");
		lblIngenieraDeSoftware.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngenieraDeSoftware.setBounds(446, 323, 203, 14);
		loginPanel.add(lblIngenieraDeSoftware);
		
		JPanel registerPanel = new JPanel();
		contentPane.add(registerPanel, "rp");
		
		JLabel lblRegistroDeUsuario = new JLabel("Registro de Usuario");
		lblRegistroDeUsuario.setBounds(228, 39, 183, 41);
		lblRegistroDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblUserid_1 = new JLabel("userID:");
		lblUserid_1.setBounds(218, 163, 50, 14);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(218, 114, 61, 14);
		
		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(218, 214, 61, 14);
		
		nombreUser = new JTextField();
		nombreUser.setBounds(328, 111, 106, 20);
		nombreUser.setColumns(10);
		
		userID2 = new JTextField();
		userID2.setBounds(328, 160, 106, 20);
		userID2.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(328, 207, 106, 21);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(207, 277, 88, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((!nombreUser.getText().equals("")) && (!userID2.getText().equals("")) && (!String.valueOf(passwordField_1.getPassword()).equals(""))) {
					if (!usuarios.contains(userID2.getText())) {
						usuarios.add(userID2.getText());
						sistema.addUser(nombreUser.getText(), userID2.getText(), String.valueOf(passwordField_1.getPassword()));
						JOptionPane.showMessageDialog(contentPane, "Usuario creado satisfactoriamente", "Aviso", JOptionPane.WARNING_MESSAGE);
						CardLayout cl = (CardLayout)(contentPane.getLayout());
						cl.show(contentPane, "lp");
						nombreUser.setText("");
						userID2.setText("");
						passwordField_1.setText("");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Ese id no es posible. Por favor elija otro.", "Error", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Por favor complete todos los campos", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "lp");
				nombreUser.setText("");
				userID2.setText("");
				passwordField_1.setText("");
			}
		});
		btnCancelar.setBounds(359, 277, 88, 23);
		registerPanel.setLayout(null);
		registerPanel.add(lblNombre);
		registerPanel.add(lblUserid_1);
		registerPanel.add(lblPassword_1);
		registerPanel.add(passwordField_1);
		registerPanel.add(nombreUser);
		registerPanel.add(userID2);
		registerPanel.add(lblRegistroDeUsuario);
		registerPanel.add(btnNewButton);
		registerPanel.add(btnCancelar);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(157, 89, 322, 11);
		registerPanel.add(separator_5);
		
		JPanel experiencePanel = new JPanel();
		contentPane.add(experiencePanel, "ep");
		experiencePanel.setLayout(null);
		
		JLabel lblListaDeUsuarios_1 = new JLabel("Lista de Usuarios");
		lblListaDeUsuarios_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListaDeUsuarios_1.setBounds(60, 99, 125, 14);
		experiencePanel.add(lblListaDeUsuarios_1);
		
		JLabel lblProyectos_1 = new JLabel("Proyectos");
		lblProyectos_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProyectos_1.setBounds(288, 99, 125, 14);
		experiencePanel.add(lblProyectos_1);
		
		JLabel lblTiposDeItem_2 = new JLabel("Tipos de Item");
		lblTiposDeItem_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTiposDeItem_2.setBounds(491, 99, 136, 14);
		experiencePanel.add(lblTiposDeItem_2);
		
		JScrollPane scrollPane_20 = new JScrollPane();
		scrollPane_20.setBounds(26, 143, 165, 167);
		experiencePanel.add(scrollPane_20);
		
		JScrollPane scrollPane_21 = new JScrollPane();
		scrollPane_21.setBounds(239, 143, 165, 167);
		experiencePanel.add(scrollPane_21);
		
		JList<String> listProyExp = new JList<String>();
		listProyExp.setModel(new DefaultListModel<String>());
		scrollPane_21.setViewportView(listProyExp);
		
		JScrollPane scrollPane_22 = new JScrollPane();
		scrollPane_22.setBounds(452, 143, 165, 167);
		experiencePanel.add(scrollPane_22);
		
		JList<String> listTIexp = new JList<String>();
		listTIexp.setModel(new DefaultListModel<String>());
		scrollPane_22.setViewportView(listTIexp);
		
		JList<String> listUsersExp = new JList<String>();
		listUsersExp.setModel(new DefaultListModel<String>());
		listUsersExp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (listUsersExp.getSelectedValue()!=null) {
					DefaultListModel<String> dlm1 = ((DefaultListModel<String>)listTIexp.getModel());
					DefaultListModel<String> dlm2 = ((DefaultListModel<String>)listProyExp.getModel());
					dlm1.removeAllElements();
					dlm2.removeAllElements();
					for (String ti : sistema.getTiposItemUser(listUsersExp.getSelectedValue())) {
						dlm1.addElement(ti);
					}
					for (String proyecto : sistema.getListProyUser(listUsersExp.getSelectedValue())) {
						dlm2.addElement(proyecto);
					}
				}
			}
		});
		scrollPane_20.setViewportView(listUsersExp);
		
		JButton btnVolver_1 = new JButton("Volver");
		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "cp");
			}
		});
		btnVolver_1.setBounds(26, 321, 165, 23);
		experiencePanel.add(btnVolver_1);
		
		JLabel lblConozcaLaExperiencia = new JLabel("Conozca la experiencia de sus potenciales responsables");
		lblConozcaLaExperiencia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConozcaLaExperiencia.setBounds(26, 11, 483, 45);
		experiencePanel.add(lblConozcaLaExperiencia);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setBounds(10, 51, 374, 8);
		experiencePanel.add(separator_15);
		
		JPanel choicePanel = new JPanel();
		contentPane.add(choicePanel, "cp");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(-5, 25, 656, 335);
		
		JLabel lblProyecto = new JLabel("Proyecto: ");
		lblProyecto.setBounds(448, 0, 203, 14);
		lblProyecto.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblRol = new JLabel("Rol: ");
		lblRol.setBounds(246, 1, 118, 13);
		lblRol.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 25, 629, 11);
		
		JPanel nIntPanel = new JPanel();
		tabbedPane.addTab("Nuevo integrante", null, nIntPanel, null);
		
		JLabel lblListaDeUsuarios = new JLabel("Lista de usuarios");
		lblListaDeUsuarios.setBounds(103, 53, 129, 15);
		lblListaDeUsuarios.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblListaDeIntegrantes = new JLabel("Lista de integrantes");
		lblListaDeIntegrantes.setBounds(407, 53, 144, 15);
		lblListaDeIntegrantes.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 86, 172, 166);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(379, 86, 172, 166);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(258, 163, 103, 23);
		
		JLabel label = new JLabel(">>");
		label.setBounds(298, 131, 16, 14);
		
		JLabel lblAadirUsuario = new JLabel("A\u00F1adir usuario");
		lblAadirUsuario.setBounds(250, 11, 153, 25);
		lblAadirUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JList<String> listIntegrantes1 = new JList<String>();
		listIntegrantes1.setModel(new DefaultListModel<String>());
		listIntegrantes1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(listIntegrantes1);
		
		JList<String> listUsers = new JList<String>();
		listUsers.setModel(new DefaultListModel<String>());
		listUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listUsers);
		nIntPanel.setLayout(null);
		nIntPanel.add(lblAadirUsuario);
		nIntPanel.add(scrollPane);
		nIntPanel.add(lblListaDeUsuarios);
		nIntPanel.add(btnAgregar);
		nIntPanel.add(label);
		nIntPanel.add(scrollPane_1);
		nIntPanel.add(lblListaDeIntegrantes);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(174, 36, 284, 15);
		nIntPanel.add(separator_8);
		
		JButton btnNewButton_3 = new JButton("Conocer experiencia");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> dlm1 = ((DefaultListModel<String>)listUsersExp.getModel());
				DefaultListModel<String> dlm2 = ((DefaultListModel<String>)listProyExp.getModel());
				DefaultListModel<String> dlm3 = ((DefaultListModel<String>)listTIexp.getModel());
				dlm1.removeAllElements();
				dlm2.removeAllElements();
				dlm3.removeAllElements();
				for (String user : usuarios) {
					dlm1.addElement(user);
				}
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "ep");
			}
		});
		btnNewButton_3.setBounds(68, 260, 172, 23);
		nIntPanel.add(btnNewButton_3);
		
		JPanel itemsPanel = new JPanel();
		tabbedPane.addTab("Items", null, itemsPanel, null);
		
		JTabbedPane itemsTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_itemsPanel = new GroupLayout(itemsPanel);
		gl_itemsPanel.setHorizontalGroup(
			gl_itemsPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(itemsTabbedPane, GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
		);
		gl_itemsPanel.setVerticalGroup(
			gl_itemsPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(itemsTabbedPane, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
		);
		
		JPanel crearItemPanel = new JPanel();
		itemsTabbedPane.addTab("Crear Item", null, crearItemPanel, null);
		crearItemPanel.setLayout(null);
		
		JLabel lblNombre_2 = new JLabel("Nombre:");
		lblNombre_2.setBounds(97, 83, 60, 14);
		crearItemPanel.add(lblNombre_2);
		
		NombreItem = new JTextField();
		NombreItem.setBounds(163, 80, 153, 20);
		crearItemPanel.add(NombreItem);
		NombreItem.setColumns(10);
		
		JLabel lblPrioridad = new JLabel("Prioridad:");
		lblPrioridad.setBounds(97, 152, 60, 14);
		crearItemPanel.add(lblPrioridad);
		
		CampoPrioridad = new JTextField();
		CampoPrioridad.setBounds(163, 149, 46, 20);
		crearItemPanel.add(CampoPrioridad);
		CampoPrioridad.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(227, 148, 89, 23);
		crearItemPanel.add(btnCrear);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(374, 83, 207, 83);
		crearItemPanel.add(scrollPane_2);
		
		JList<String> listTiposItem1 = new JList<String>();
		listTiposItem1.setModel(new DefaultListModel<String>());
		listTiposItem1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(listTiposItem1);
		
		JLabel lblTiposDeItem = new JLabel("Tipos de Item");
		lblTiposDeItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTiposDeItem.setBounds(434, 58, 107, 14);
		crearItemPanel.add(lblTiposDeItem);
		
		JLabel lblcrearUntem = new JLabel("*Crear un \u00EDtem te hace autom\u00E1ticamente responsable del mismo.");
		lblcrearUntem.setBounds(26, 231, 411, 14);
		crearItemPanel.add(lblcrearUntem);
		
		JLabel lblprioridadNumrica = new JLabel("*Prioridad num\u00E9rica");
		lblprioridadNumrica.setBounds(26, 252, 153, 14);
		crearItemPanel.add(lblprioridadNumrica);
		
		JLabel lblCrearItem = new JLabel("Crear Item");
		lblCrearItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCrearItem.setBounds(103, 19, 158, 25);
		crearItemPanel.add(lblCrearItem);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(42, 49, 237, 5);
		crearItemPanel.add(separator_9);
		
		JPanel asignRespPanel = new JPanel();
		itemsTabbedPane.addTab("Asignar responsable", null, asignRespPanel, null);
		asignRespPanel.setLayout(null);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(109, 64, 46, 14);
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		asignRespPanel.add(lblItems);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(60, 91, 139, 133);
		asignRespPanel.add(scrollPane_3);
		
		JTextArea estadoActual = new JTextArea();
		estadoActual.setBounds(253, 121, 139, 28);
		asignRespPanel.add(estadoActual);
		
		JLabel lblResponsableActual = new JLabel("Responsable actual");
		lblResponsableActual.setBounds(266, 171, 125, 14);
		lblResponsableActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		asignRespPanel.add(lblResponsableActual);
		
		JTextArea respActual = new JTextArea();
		respActual.setBounds(252, 196, 139, 28);
		asignRespPanel.add(respActual);
		
		JTextArea tItem = new JTextArea();
		tItem.setBounds(252, 50, 139, 28);
		asignRespPanel.add(tItem);
		
		JList<String> listItems1 = new JList<String>();
		listItems1.setModel(new DefaultListModel<String>());
		listItems1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_3.setViewportView(listItems1);
		listItems1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (listItems1.getSelectedValue()!=null) {
					estadoActual.setText(sistema.getEstadoActual(proyectoactual, listItems1.getSelectedValue()));
					respActual.setText(sistema.getRespActual(proyectoactual, listItems1.getSelectedValue()));
					tItem.setText(sistema.getTipoItem(proyectoactual, listItems1.getSelectedValue()));
				}
			}
		});
		
		JLabel lblResponsables = new JLabel("Integrantes");
		lblResponsables.setBounds(476, 64, 105, 14);
		lblResponsables.setFont(new Font("Tahoma", Font.BOLD, 11));
		asignRespPanel.add(lblResponsables);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(442, 91, 139, 133);
		asignRespPanel.add(scrollPane_4);
		
		JList<String> listIntegrantes2 = new JList<String>();
		listIntegrantes2.setModel(new DefaultListModel<String>());
		listIntegrantes2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_4.setViewportView(listIntegrantes2);
		
		JLabel lblEstadoActual = new JLabel("Estado actual");
		lblEstadoActual.setBounds(283, 94, 98, 14);
		lblEstadoActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		asignRespPanel.add(lblEstadoActual);
		
		JButton btnAsignar = new JButton("Asignar");
		btnAsignar.setBounds(278, 245, 89, 23);
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((listItems1.getSelectedValue()!=null) && (listIntegrantes2.getSelectedValue()!=null)) {
					if (!listIntegrantes2.getSelectedValue().equals(sistema.getRespActual(proyectoactual, listItems1.getSelectedValue()))) {
						if (!sistema.getEstadoActual(proyectoactual, listItems1.getSelectedValue()).equals("Terminado")) {
							if (!listIntegrantes2.isSelectedIndex(0)) {
								sistema.asignarRespItem(listIntegrantes2.getSelectedValue(), proyectoactual, listItems1.getSelectedValue());
								JOptionPane.showMessageDialog(contentPane, "Asignación concretada exitosamente", "Aviso", JOptionPane.WARNING_MESSAGE);
								estadoActual.setText(sistema.getEstadoActual(proyectoactual, listItems1.getSelectedValue()));
								respActual.setText(sistema.getRespActual(proyectoactual, listItems1.getSelectedValue()));
							} else {
								JOptionPane.showMessageDialog(contentPane, "El líder no necesita ser responsable del item para avanzar estados", "Aviso", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(contentPane, "Item finalizado", "Aviso", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, listIntegrantes2.getSelectedValue() + " ya es el responsable actual del item " + listItems1.getSelectedValue(), "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Primero seleccione un item y al integrante que desea asignar", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAsignar.setFont(new Font("Tahoma", Font.BOLD, 11));
		asignRespPanel.add(btnAsignar);
		
		JLabel lblTipoDeItem = new JLabel("Tipo de item");
		lblTipoDeItem.setBounds(288, 24, 98, 14);
		lblTipoDeItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		asignRespPanel.add(lblTipoDeItem);
		
		JLabel lblNuevoRespDe = new JLabel("Nuevo resp. de item");
		lblNuevoRespDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNuevoRespDe.setBounds(19, 8, 209, 26);
		asignRespPanel.add(lblNuevoRespDe);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBounds(11, 35, 180, 7);
		asignRespPanel.add(separator_11);
		
		
		
		JPanel avEstPanel = new JPanel();
		itemsTabbedPane.addTab("Avanzar estado", null, avEstPanel, null);
		avEstPanel.setLayout(null);
		
		JLabel label_1 = new JLabel("Items");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(113, 64, 46, 14);
		avEstPanel.add(label_1);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(68, 94, 139, 133);
		avEstPanel.add(scrollPane_5);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(424, 94, 139, 133);
		avEstPanel.add(scrollPane_6);
		
		JList<String> listEstadosSig1 = new JList<String>();
		listEstadosSig1.setModel(new DefaultListModel<String>());
		listEstadosSig1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_6.setViewportView(listEstadosSig1);
		
		JLabel lblEstadosSiguientes = new JLabel("Estados siguientes");
		lblEstadosSiguientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadosSiguientes.setBounds(442, 64, 111, 14);
		avEstPanel.add(lblEstadosSiguientes);
		
		JLabel lblEstado = new JLabel("Estado Actual");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBounds(274, 121, 126, 14);
		avEstPanel.add(lblEstado);
		
		JTextArea estadoActual2 = new JTextArea();
		estadoActual2.setBounds(245, 146, 139, 28);
		avEstPanel.add(estadoActual2);
		
		JList<String> listItems2 = new JList<String>();
		listItems2.setModel(new DefaultListModel<String>());
		listItems2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_5.setViewportView(listItems2);
		listItems2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				DefaultListModel<String> dlm = ((DefaultListModel<String>)listEstadosSig1.getModel());
				dlm.removeAllElements(); //limpieza antes de almacenar valores
				if (listItems2.getSelectedValue()!=null) {
					estadoActual2.setText(sistema.getEstadoActual(proyectoactual, listItems2.getSelectedValue()));
					for (String siguiente : sistema.getEstadosSig(sistema.getTipoItem(proyectoactual, listItems2.getSelectedValue()), estadoActual2.getText())) {
						dlm.addElement(siguiente);
					}
				}
			}
		});
		
		JLabel label_2 = new JLabel(">>");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(394, 151, 46, 14);
		avEstPanel.add(label_2);
		
		JButton btnAvanzar = new JButton("Avanzar");
		btnAvanzar.setEnabled(true);
		btnAvanzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((listItems2.getSelectedValue()!=null) && (listEstadosSig1.getSelectedValue()!=null)) {
					sistema.ejecutarTransicion(proyectoactual, listItems2.getSelectedValue(), sistema.getEstadoActual(proyectoactual, listItems2.getSelectedValue()), listEstadosSig1.getSelectedValue(), usuarioactual);
					JOptionPane.showMessageDialog(contentPane, "Transición exitosa", "Aviso", JOptionPane.WARNING_MESSAGE);
					if (prioridadUser==1) { //no es el líder del proyecto, sino un responsable del ítem
						JOptionPane.showMessageDialog(contentPane, "Se ha notificado al creador del ítem de la transición", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
					estadoActual2.setText(sistema.getEstadoActual(proyectoactual, listItems2.getSelectedValue()));
					if (listEstadosSig1.getSelectedValue().equals("Terminado")) {
						sistema.finalizarEstado(proyectoactual, listItems2.getSelectedValue());
						JOptionPane.showMessageDialog(contentPane, "Ha finalizado tu gestíon con el item " + listItems2.getSelectedValue(), "Aviso", JOptionPane.WARNING_MESSAGE);
					}
					if (sistema.getCargo(proyectoactual, usuarioactual) == 0) {
						JOptionPane.showMessageDialog(contentPane, "Como ya no gestionas items del proyecto " + proyectoactual + ", tu rol se ve disminuído", "Aviso", JOptionPane.WARNING_MESSAGE);
						prioridadUser = 0;
						lblRol.setText("Rol: Integrante");
						setVisibilidad(tabbedPane,itemsTabbedPane);
					}
					DefaultListModel<String> dlm = ((DefaultListModel<String>)listEstadosSig1.getModel());
					dlm.removeAllElements();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Seleccione un item y estado al que desea avanzar", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAvanzar.setBounds(270, 204, 89, 23);
		avEstPanel.add(btnAvanzar);
		
		JLabel lblelResponsableActual = new JLabel("*El responsable actual ser\u00E1 inicialmente tambi\u00E9n del nuevo estado.");
		lblelResponsableActual.setBounds(10, 241, 390, 14);
		avEstPanel.add(lblelResponsableActual);
		
		JLabel lblAvanzarEstadoDe = new JLabel("Avanzar estado de un item");
		lblAvanzarEstadoDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAvanzarEstadoDe.setBounds(208, 8, 287, 32);
		avEstPanel.add(lblAvanzarEstadoDe);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBounds(167, 37, 308, 7);
		avEstPanel.add(separator_10);
		
		JPanel historialPanel = new JPanel();
		itemsTabbedPane.addTab("Conocer historial", null, historialPanel, null);
		historialPanel.setLayout(null);
		
		JLabel label_3 = new JLabel("Items");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(172, 56, 46, 14);
		historialPanel.add(label_3);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(96, 81, 195, 138);
		historialPanel.add(scrollPane_7);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(358, 81, 195, 138);
		historialPanel.add(scrollPane_8);
		
		JList<String> listHistorial = new JList<String>();
		listHistorial.setModel(new DefaultListModel<String>());
		listHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listHistorial.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane_8.setViewportView(listHistorial);
		
		JList<String> listItems3 = new JList<String>();
		listItems3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (listItems3.getSelectedValue()!=null) {
					DefaultListModel<String> dlm = ((DefaultListModel<String>)listHistorial.getModel());
					dlm.removeAllElements();
					if (sistema.getSecuenciaEstados(proyectoactual, listItems3.getSelectedValue())!=null) {
						int i = 1;
						for (String lineaHistorial : sistema.getSecuenciaEstados(proyectoactual, listItems3.getSelectedValue())) {
							dlm.addElement(i + "    " + lineaHistorial);
							i += 1;
						}
					}
				}
			}
		});
		listItems3.setModel(new DefaultListModel<String>());
		listItems3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_7.setViewportView(listItems3);
		
		JLabel lblHistorial = new JLabel("Secuencia de estados pasados");
		lblHistorial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHistorial.setBounds(368, 56, 225, 14);
		historialPanel.add(lblHistorial);
		
		JLabel lblseleccioneUntem = new JLabel("*Seleccione un \u00EDtem y a continuaci\u00F3n observe a la derecha su registro hist\u00F3rico");
		lblseleccioneUntem.setBounds(10, 241, 482, 14);
		historialPanel.add(lblseleccioneUntem);
		
		JLabel label_6 = new JLabel(">>");
		label_6.setBounds(313, 137, 46, 14);
		historialPanel.add(label_6);
		
		JLabel lblHistorial_1 = new JLabel("Historial");
		lblHistorial_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHistorial_1.setBounds(285, 6, 133, 35);
		historialPanel.add(lblHistorial_1);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBounds(217, 35, 208, 7);
		historialPanel.add(separator_12);
		
		JPanel fechaPanel = new JPanel();
		itemsTabbedPane.addTab("Ver resp. en fecha det.", null, fechaPanel, null);
		fechaPanel.setLayout(null);
		
		JLabel label_4 = new JLabel("Items");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(79, 28, 46, 14);
		fechaPanel.add(label_4);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(24, 55, 153, 137);
		fechaPanel.add(scrollPane_9);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(225, 55, 153, 137);
		fechaPanel.add(scrollPane_10);
		
		JList<String> listEstadosPasados = new JList<String>();
		listEstadosPasados.setModel(new DefaultListModel<String>());
		listEstadosPasados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_10.setViewportView(listEstadosPasados);
		
		JList<String> ListItems4 = new JList<String>();
		ListItems4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (ListItems4.getSelectedValue()!=null) {
					DefaultListModel<String> dlm = ((DefaultListModel<String>)listEstadosPasados.getModel());
					dlm.removeAllElements();
					if (sistema.getRegistroItems(proyectoactual, ListItems4.getSelectedValue())!=null) {
						for (String item : sistema.getRegistroItems(proyectoactual, ListItems4.getSelectedValue())) {
							dlm.addElement(item);
						}
					}
				}
			}
		});
		ListItems4.setModel(new DefaultListModel<String>());
		ListItems4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_9.setViewportView(ListItems4);
		
		JLabel lblEstados = new JLabel("Estados gestionados");
		lblEstados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstados.setBounds(243, 28, 153, 14);
		fechaPanel.add(lblEstados);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(419, 96, 46, 14);
		fechaPanel.add(lblFecha);
		
		JLabel lblResultado = new JLabel("Resultado: ");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultado.setBounds(10, 229, 77, 14);
		fechaPanel.add(lblResultado);
		
		fecha = new JTextField();
		fecha.setBounds(467, 93, 86, 20);
		fechaPanel.add(fecha);
		fecha.setColumns(10);
		
		JLabel lblFormatoddmmyyyy = new JLabel("Formato: \"dd-mm-yyyy\"");
		lblFormatoddmmyyyy.setBounds(419, 121, 153, 14);
		fechaPanel.add(lblFormatoddmmyyyy);
		itemsPanel.setLayout(gl_itemsPanel);
		
		JScrollPane scrollPane_16 = new JScrollPane();
		scrollPane_16.setBounds(85, 214, 529, 43);
		fechaPanel.add(scrollPane_16);
		
		JList<String> resultadoRespFecha = new JList<String>();
		resultadoRespFecha.setModel(new DefaultListModel<String>());
		resultadoRespFecha.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane_16.setViewportView(resultadoRespFecha);
		
		JButton btnObtener = new JButton("Obtener");
		btnObtener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((ListItems4.getSelectedValue()!=null) && (listEstadosPasados.getSelectedValue()!=null) && (!fecha.getText().equals(""))) {
					if (verificaFecha(fecha.getText())) {
						DefaultListModel<String> dlm = ((DefaultListModel<String>)resultadoRespFecha.getModel());
						dlm.removeAllElements();
						for (String resp : sistema.getRespEnFecha(proyectoactual, ListItems4.getSelectedValue(), listEstadosPasados.getSelectedValue(), fecha.getText())) {
							dlm.addElement(resp);
						}
						if (dlm.isEmpty()) {
							dlm.addElement("No existen registros de actividad para esa fecha");
						}
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Primero seleccione un item, un estado y dé una fecha válida", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnObtener.setBounds(419, 169, 89, 23);
		fechaPanel.add(btnObtener);
		
		JLabel label_7 = new JLabel(">>");
		label_7.setBounds(190, 121, 46, 14);
		fechaPanel.add(label_7);
		
		JLabel lblConocerResponsable = new JLabel("Conocer responsable");
		lblConocerResponsable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConocerResponsable.setBounds(422, 5, 225, 36);
		fechaPanel.add(lblConocerResponsable);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBounds(410, 37, 193, 13);
		fechaPanel.add(separator_13);
		
		JPanel tipoItemsPanel = new JPanel();
		tabbedPane.addTab("Tipos de Item", null, tipoItemsPanel, null);
		
		JTabbedPane tipoItemTabP = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_tipoItemsPanel = new GroupLayout(tipoItemsPanel);
		gl_tipoItemsPanel.setHorizontalGroup(
			gl_tipoItemsPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tipoItemTabP, GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
		);
		gl_tipoItemsPanel.setVerticalGroup(
			gl_tipoItemsPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tipoItemTabP, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
		);
		
		JPanel crearTIPanel = new JPanel();
		tipoItemTabP.addTab("Administrar Tipos de Item", null, crearTIPanel, null);
		crearTIPanel.setLayout(null);
		
		
		
		JLabel lblNombre_3 = new JLabel("Nombre:");
		lblNombre_3.setBounds(10, 211, 55, 14);
		crearTIPanel.add(lblNombre_3);
		
		nombreTI = new JTextField();
		nombreTI.setBounds(65, 208, 86, 20);
		crearTIPanel.add(nombreTI);
		nombreTI.setColumns(10);
		
		JLabel lblCrearNuevoTipo = new JLabel("Crear nuevo Tipo de Item:");
		lblCrearNuevoTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCrearNuevoTipo.setBounds(10, 183, 162, 14);
		crearTIPanel.add(lblCrearNuevoTipo);
		
		JScrollPane scrollPane_12 = new JScrollPane();
		scrollPane_12.setBounds(173, 35, 141, 137);
		crearTIPanel.add(scrollPane_12);
		
		JList<String> listEstados = new JList<String>();
		listEstados.setModel(new DefaultListModel<String>());
		listEstados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_12.setViewportView(listEstados);
		
		JLabel lblEstados_1 = new JLabel("Estados");
		lblEstados_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstados_1.setBounds(222, 10, 60, 14);
		crearTIPanel.add(lblEstados_1);
		
		JLabel lblCrearNuevoEstado = new JLabel("Crear nuevo estado:");
		lblCrearNuevoEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCrearNuevoEstado.setBounds(175, 183, 139, 14);
		crearTIPanel.add(lblCrearNuevoEstado);
		
		JLabel label_5 = new JLabel("Nombre:");
		label_5.setBounds(173, 211, 55, 14);
		crearTIPanel.add(label_5);
		
		nombEstado = new JTextField();
		nombEstado.setColumns(10);
		nombEstado.setBounds(228, 208, 86, 20);
		crearTIPanel.add(nombEstado);
		
		JLabel lblCreacinDeSecuencia = new JLabel("Creaci\u00F3n de secuencia entre estados");
		lblCreacinDeSecuencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCreacinDeSecuencia.setBounds(338, 10, 250, 14);
		crearTIPanel.add(lblCreacinDeSecuencia);
		
		JLabel lblEstado_1 = new JLabel("Inicial (A)");
		lblEstado_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado_1.setBounds(338, 64, 72, 14);
		crearTIPanel.add(lblEstado_1);
		
		JLabel lblSiguiente = new JLabel("Siguiente (B)");
		lblSiguiente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSiguiente.setBounds(427, 64, 92, 14);
		crearTIPanel.add(lblSiguiente);
		
		JLabel lblNewLabel = new JLabel("Secuencias de A");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(542, 64, 104, 14);
		crearTIPanel.add(lblNewLabel);
		
		JScrollPane scrollPane_13 = new JScrollPane();
		scrollPane_13.setBounds(338, 89, 86, 136);
		crearTIPanel.add(scrollPane_13);
		
		JScrollPane scrollPane_14 = new JScrollPane();
		scrollPane_14.setBounds(427, 89, 86, 136);
		crearTIPanel.add(scrollPane_14);
		
		JList<String> listEstadosB = new JList<String>();
		listEstadosB.setModel(new DefaultListModel<String>());
		listEstadosB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_14.setViewportView(listEstadosB);
		
		JScrollPane scrollPane_15 = new JScrollPane();
		scrollPane_15.setBounds(544, 89, 86, 135);
		crearTIPanel.add(scrollPane_15);
		
		JList<String> listSecuenciaA = new JList<String>();
		listSecuenciaA.setModel(new DefaultListModel<String>());
		listSecuenciaA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_15.setViewportView(listSecuenciaA);
		tipoItemsPanel.setLayout(gl_tipoItemsPanel);
		
		JLabel lblTiposDeItem_1 = new JLabel("Tipos de item");
		lblTiposDeItem_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTiposDeItem_1.setBounds(46, 10, 95, 14);
		crearTIPanel.add(lblTiposDeItem_1);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(10, 35, 141, 137);
		crearTIPanel.add(scrollPane_11);
		
		JList<String> listEstadosA = new JList<String>();
		listEstadosA.setModel(new DefaultListModel<String>());
		
		JList<String> listTiposItem2 = new JList<String>();
		listTiposItem2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DefaultListModel<String> dlm1 = ((DefaultListModel<String>)listEstados.getModel());
				DefaultListModel<String> dlm2 = ((DefaultListModel<String>)listEstadosA.getModel());
				DefaultListModel<String> dlm3 = ((DefaultListModel<String>)listEstadosB.getModel());
				DefaultListModel<String> dlm4 = ((DefaultListModel<String>)listSecuenciaA.getModel());
				dlm1.removeAllElements();
				dlm2.removeAllElements();
				dlm3.removeAllElements();
				dlm4.removeAllElements();
				if (listTiposItem2.getSelectedValue()!=null) {
					for (String estado : sistema.getEstadosTipoItem(listTiposItem2.getSelectedValue())) {
						dlm1.addElement(estado);
						dlm2.addElement(estado);
						dlm3.addElement(estado);
					}
				}
			}
		});
		listTiposItem2.setModel(new DefaultListModel<String>());
		listTiposItem2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_11.setViewportView(listTiposItem2);
		
		listEstadosA.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (listEstadosA.getSelectedValue()!=null) {
					DefaultListModel<String> dlm = ((DefaultListModel<String>)listSecuenciaA.getModel());
					dlm.removeAllElements();
					//System.out.println(listTiposItem2.getSelectedValue() + " " + listSecuenciaA.getSelectedValue());
					for (String siguiente : sistema.getEstadosSig(listTiposItem2.getSelectedValue(), listEstadosA.getSelectedValue())) {
						dlm.addElement(siguiente);
					}
				}
			}
		});
		listEstadosA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_13.setViewportView(listEstadosA);
		
		JButton btnCrearSecuencia = new JButton("Crear secuencia");
		btnCrearSecuencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listTiposItem2.getSelectedValue()!=null) { //Debe haber un item seleccionado
					if (listEstadosA.getSelectedValue()!=null) { //Debe haber un estado inicial
						if (listEstadosB.getSelectedValue()!=null) { //Debe haber un estado siguiente
							if (!sistema.existeTransicion(listTiposItem2.getSelectedValue(), listEstadosA.getSelectedValue(), listEstadosB.getSelectedValue())) {
								if (!listEstadosA.getSelectedValue().equals(listEstadosB.getSelectedValue())) { //No existen transiciones A->A
									if (!listEstadosB.getSelectedValue().equals("Iniciado")) { //Ningún estado puede llegar a Iniciado, solo es el primero
										if (!listEstadosA.getSelectedValue().equals("Terminado")) { //Terminado siempre es el estado final
											if (sistema.getCreadorTipoItem(listTiposItem2.getSelectedValue()).equals(usuarioactual)) {
												sistema.agregarTransicion(listTiposItem2.getSelectedValue(), listEstadosA.getSelectedValue(), listEstadosB.getSelectedValue());
												DefaultListModel<String> dlm = ((DefaultListModel<String>)listSecuenciaA.getModel());
												dlm.addElement(listEstadosB.getSelectedValue());
											} else {
												JOptionPane.showMessageDialog(contentPane, "No tiene permiso para realizar esta acción", "Aviso", JOptionPane.WARNING_MESSAGE);
											}
										} else {
											JOptionPane.showMessageDialog(contentPane, "'Terminado' es solo el estado final", "Aviso", JOptionPane.WARNING_MESSAGE);
										}
									} else {
									JOptionPane.showMessageDialog(contentPane, "'Iniciado' es solo el estado inicial", "Aviso", JOptionPane.WARNING_MESSAGE);
									}
							} else {
								JOptionPane.showMessageDialog(contentPane, "No puede existir una transición entre estados iguales", "Aviso", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(contentPane, "Ya existe tal transición", "Aviso", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Antes seleccione el estado final", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Antes seleccione el estado inicial", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(contentPane, "Antes seleccione el tipo de item", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnCrearSecuencia.setBounds(338, 236, 292, 23);
		crearTIPanel.add(btnCrearSecuencia);
		
		JButton btnCrearEstado = new JButton("Crear estado");
		btnCrearEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> dlm1 = ((DefaultListModel<String>)listEstadosA.getModel());
				DefaultListModel<String> dlm2 = ((DefaultListModel<String>)listEstadosB.getModel());
				DefaultListModel<String> dlm3 = ((DefaultListModel<String>)listEstados.getModel());
				if (!nombEstado.getText().equals("")) {
					if (listTiposItem2.getSelectedValue()!=null) {
						if (!sistema.getEstadosTipoItem(listTiposItem2.getSelectedValue()).contains(nombEstado.getText())) {
							if (sistema.getCreadorTipoItem(listTiposItem2.getSelectedValue()).equals(usuarioactual)) {
								sistema.agregarEstado(listTiposItem2.getSelectedValue(), nombEstado.getText());
								dlm1.addElement(nombEstado.getText());
								dlm2.addElement(nombEstado.getText());
								dlm3.addElement(nombEstado.getText());
								nombEstado.setText("");
								JOptionPane.showMessageDialog(contentPane, "Estado creado exitosamente", "Aviso", JOptionPane.WARNING_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(contentPane, "No tiene autoridad para realizar esta acción", "Aviso", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(contentPane, "Ya existe un estado con ese nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Antes seleccione un tipo de item", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Antes dé un nombre para el nuevo estado", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnCrearEstado.setBounds(173, 236, 141, 23);
		crearTIPanel.add(btnCrearEstado);
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (prioridadUser==2) {
					if (!nombreTI.getText().equals("")) {
						if (!tipoItems.contains(nombreTI.getText())) {
							tipoItems.add(nombreTI.getText());
							sistema.crearTipoItem(nombreTI.getText(),usuarioactual);
							JOptionPane.showMessageDialog(contentPane, "Tipo de item creado exitosamente", "Aviso", JOptionPane.WARNING_MESSAGE);
							DefaultListModel<String> dlm = ((DefaultListModel<String>)listTiposItem2.getModel());
							dlm.addElement(nombreTI.getText());
							DefaultListModel<String> dlm1 = ((DefaultListModel<String>)listTiposItem1.getModel());
							dlm1.addElement(nombreTI.getText());
							nombreTI.setText("");
						} else {
							JOptionPane.showMessageDialog(contentPane, "Ya existe un tipo con ese nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Antes ingrese un nombre para el tipo", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Solo los usuarios con rol activo de líder pueden crear tipos de item", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(10, 236, 141, 23);
		crearTIPanel.add(btnNewButton_1);
		
		tabbedPane.setSelectedIndex(0);
		choicePanel.setLayout(null);
		choicePanel.add(separator);
		choicePanel.add(lblUsuario);
		choicePanel.add(lblRol);
		choicePanel.add(lblProyecto);
		choicePanel.add(tabbedPane);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(218, 1, 2, 13);
		choicePanel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(425, 1, 2, 13);
		choicePanel.add(separator_2);
		
		JLabel lblBienvenido = new JLabel("Sistema de seguimiento de \u00EDtems");
		lblBienvenido.setBounds(127, 57, 394, 47);
		lblBienvenido.setForeground(Color.BLUE);
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JLabel lblPunto = new JLabel("- Participe de proyectos disponibles o cree el suyo");
		lblPunto.setBounds(189, 144, 382, 14);
		
		JLabel lblPunto_1 = new JLabel("- Administre los proyectos que integre:");
		lblPunto_1.setBounds(189, 176, 332, 14);
		
		JLabel lblPunto_2 = new JLabel("Cree items, tipos de items y agregue estados");
		lblPunto_2.setBounds(242, 226, 397, 14);
		
		JLabel lblPunto_3 = new JLabel("Genere transiciones entre los estados");
		lblPunto_3.setBounds(242, 251, 343, 14);
		
		JLabel lblDamianLopez = new JLabel("Damian Lopez");
		lblDamianLopez.setBounds(21, 313, 76, 14);
		lblDamianLopez.setFont(new Font("Tahoma", Font.ITALIC, 11));
		welcomePanel.setLayout(null);
		welcomePanel.add(lblPunto_1);
		welcomePanel.add(lblPunto);
		welcomePanel.add(lblBienvenido);
		welcomePanel.add(lblDamianLopez);
		welcomePanel.add(lblPunto_2);
		welcomePanel.add(lblPunto_3);
		
		JLabel lblConozcaElHistorial = new JLabel("Conozca el historial de los \u00EDtems y su responsable dada una fecha");
		lblConozcaElHistorial.setBounds(242, 276, 397, 14);
		welcomePanel.add(lblConozcaElHistorial);
		
		JLabel lblAgregueColaboradoresA = new JLabel("Agregue colaboradores a sus proyectos y asigne responsables");
		lblAgregueColaboradoresA.setBounds(242, 201, 397, 14);
		welcomePanel.add(lblAgregueColaboradoresA);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(96, 102, 425, 14);
		welcomePanel.add(separator_3);
		
		JLabel lblIngenieraDelSoftware = new JLabel("Ingenier\u00EDa de Software I");
		lblIngenieraDelSoftware.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngenieraDelSoftware.setBounds(446, 324, 203, 14);
		welcomePanel.add(lblIngenieraDelSoftware);
		
		JPanel createProPanel = new JPanel();
		contentPane.add(createProPanel, "cpp");
		createProPanel.setLayout(null);
		
		JLabel lblCrearNuevoProyecto = new JLabel("Crear nuevo proyecto");
		lblCrearNuevoProyecto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCrearNuevoProyecto.setBounds(204, 50, 267, 25);
		createProPanel.add(lblCrearNuevoProyecto);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre_1.setBounds(190, 138, 62, 14);
		createProPanel.add(lblNombre_1);
		
		nombreProy = new JTextField();
		nombreProy.setBounds(262, 136, 156, 20);
		createProPanel.add(nombreProy);
		nombreProy.setColumns(10);
		
		JLabel lblCrearUn = new JLabel("* Asignaci\u00F3n de responsabilidad:  Crear un proyecto te convierte autom\u00E1ticamente en su l\u00EDder (admin)");
		lblCrearUn.setBounds(30, 293, 575, 14);
		createProPanel.add(lblCrearUn);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!nombreProy.getText().equals("")) {
					if (!proyectos.contains(nombreProy.getText())) {
						proyectos.add(nombreProy.getText());
						sistema.addProyecto(nombreProy.getText(), usuarioactual);
						JOptionPane.showMessageDialog(contentPane, "Proyecto creado satisfactoriamente", "Aviso", JOptionPane.WARNING_MESSAGE);
						CardLayout cl = (CardLayout)(contentPane.getLayout());
						cl.show(contentPane, "wp");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Ese nombre ya está siendo utilizado", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Campo vacío", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAceptar.setBounds(179, 210, 98, 23);
		createProPanel.add(btnAceptar);
		
		JButton btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "wp");
				nombreProy.setText("");
			}
		});
		btnCancelar_1.setBounds(347, 210, 98, 23);
		createProPanel.add(btnCancelar_1);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(149, 91, 322, 14);
		createProPanel.add(separator_6);
		
		JPanel selectProPanel = new JPanel();
		contentPane.add(selectProPanel, "spp");
		selectProPanel.setLayout(null);
		
		JLabel lblSeleccionarProyecto = new JLabel("Seleccionar Proyecto");
		lblSeleccionarProyecto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSeleccionarProyecto.setBounds(201, 24, 232, 34);
		selectProPanel.add(lblSeleccionarProyecto);
		
		JScrollPane sp1 = new JScrollPane();
		sp1.setBounds(156, 92, 277, 128);
		selectProPanel.add(sp1);
		
		JList<String> listProyectos = new JList<String>();
		listProyectos.setModel(new DefaultListModel<String>());
		listProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sp1.setViewportView(listProyectos);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar sesion");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "lp");
				mnProyecto.setEnabled(false);
				mnUsuario.setEnabled(false);
			}
		});
		mnUsuario.add(mntmCerrarSesin);
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listUsers.getSelectedValue()!=null) {
					if (!sistema.getIntegrantesProyecto(proyectoactual).contains(listUsers.getSelectedValue())) {
						sistema.addUserProyecto(listUsers.getSelectedValue(), proyectoactual);
						JOptionPane.showMessageDialog(contentPane, "Usuario agregado correctamente", "Aviso", JOptionPane.WARNING_MESSAGE);
						DefaultListModel<String> dlm1 = ((DefaultListModel<String>)listIntegrantes1.getModel());
						dlm1.addElement(listUsers.getSelectedValue());
						DefaultListModel<String> dlm2 = ((DefaultListModel<String>)listIntegrantes2.getModel());
						dlm2.addElement(listUsers.getSelectedValue());
					} else {
						JOptionPane.showMessageDialog(contentPane, listUsers.getSelectedValue() + " ya es integrante de " + proyectoactual, "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Primero seleccione un usuario", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton btnAceptar_1 = new JButton("Aceptar");
		btnAceptar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listProyectos.getSelectedValue()!=null) { //para obtener elementos a la lista
					DefaultListModel<String> dlm = ((DefaultListModel<String>)listProyectos.getModel());
					proyectoactual = listProyectos.getSelectedValue();
					lblProyecto.setText("Proyecto:  " + proyectoactual);
					lblRol.setText("Rol:  " + getRol());
					setVisibilidad(tabbedPane,itemsTabbedPane);
					nombreProy.setText("");
					CardLayout cl = (CardLayout)(contentPane.getLayout());
					cl.show(contentPane, "cp");
					dlm.removeAllElements();
					//Limpieza de listas y campos
					ArrayList<JList<String>> listas1 = new ArrayList<JList<String>>();
					listas1.add(listUsers);
					listas1.add(listTiposItem1);
					listas1.add(listTiposItem2);
					listas1.add(listIntegrantes1);
					listas1.add(listIntegrantes2);
					listas1.add(listItems2);
					listas1.add(listItems1);
					listas1.add(listItems3);
					listas1.add(ListItems4);
					listas1.add(listEstadosSig1);
					listas1.add(listHistorial);
					listas1.add(listEstadosPasados);
					listas1.add(listEstados);
					listas1.add(listEstadosA);
					listas1.add(listEstadosB);
					listas1.add(listSecuenciaA);
					listas1.add(resultadoRespFecha);
					vaciarListas(listas1);
					NombreItem.setText("");
					CampoPrioridad.setText("");
					fecha.setText("");
					nombreTI.setText("");
					nombEstado.setText("");
					estadoActual.setText("");
					respActual.setText("");
					estadoActual2.setText("");
					//cargar listas
					ArrayList<JList<String>> listas2 = new ArrayList<JList<String>>();
					listas2.add(listUsers); //Pos 0
					listas2.add(listTiposItem1); //Pos 1
					listas2.add(listTiposItem2); //Pos 2
					listas2.add(listIntegrantes1); //Pos 3
					listas2.add(listIntegrantes2); //Pos 4
					listas2.add(listItems2); //Pos 5 - ItemsBajoResp
					listas2.add(listItems1); //Pos 6
					listas2.add(listItems3); //Pos 7
					listas2.add(ListItems4); //Pos 8
					cargarListas(listas2);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Seleccione un proyecto", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAceptar_1.setBounds(156, 251, 89, 23);
		selectProPanel.add(btnAceptar_1);
		
		JButton btnCancelar_2 = new JButton("Cancelar");
		btnCancelar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> dlm = ((DefaultListModel<String>)listProyectos.getModel());
				dlm.removeAllElements();
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "wp");
			}
		});
		
		btnCancelar_2.setBounds(344, 251, 89, 23);
		selectProPanel.add(btnCancelar_2);
		
		JLabel lblElijaElTtulo = new JLabel("*Seleccione el t\u00EDtulo del proyecto al que desee ingresar y seleccione \"Aceptar\"");
		lblElijaElTtulo.setBounds(32, 324, 472, 14);
		selectProPanel.add(lblElijaElTtulo);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(156, 67, 279, 14);
		selectProPanel.add(separator_7);
		
		JLabel lblaquSoloPodr = new JLabel("*Aqu\u00ED solo podr\u00E1 visualizar aquellos proyectos de los que forme parte");
		lblaquSoloPodr.setBounds(31, 299, 485, 14);
		selectProPanel.add(lblaquSoloPodr);
		
		JPanel verProyPanel = new JPanel();
		contentPane.add(verProyPanel, "vpp");
		verProyPanel.setLayout(null);
		
		JLabel lblProyectosExistentes = new JLabel("Proyectos existentes");
		lblProyectosExistentes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProyectosExistentes.setBounds(227, 25, 232, 34);
		verProyPanel.add(lblProyectosExistentes);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setBounds(180, 70, 279, 14);
		verProyPanel.add(separator_14);
		
		JScrollPane scrollPane_18 = new JScrollPane();
		scrollPane_18.setBounds(232, 121, 181, 178);
		verProyPanel.add(scrollPane_18);
		
		JList<String> listItemsProy = new JList<String>();
		listItemsProy.setModel(new DefaultListModel<String>());
		scrollPane_18.setViewportView(listItemsProy);
		
		JScrollPane scrollPane_19 = new JScrollPane();
		scrollPane_19.setBounds(441, 121, 181, 178);
		verProyPanel.add(scrollPane_19);
		
		JList<String> listIntegrantesProy = new JList<String>();
		listIntegrantesProy.setModel(new DefaultListModel<String>());
		scrollPane_19.setViewportView(listIntegrantesProy);
		
		JScrollPane scrollPane_17 = new JScrollPane();
		scrollPane_17.setBounds(22, 121, 181, 178);
		verProyPanel.add(scrollPane_17);
		
		JList<String> listProyectosExistentes = new JList<String>();
		listProyectosExistentes.setModel(new DefaultListModel<String>());
		listProyectosExistentes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (listProyectosExistentes.getSelectedValue()!=null) {
					DefaultListModel<String> dlm1 = ((DefaultListModel<String>)listItemsProy.getModel());
					DefaultListModel<String> dlm2 = ((DefaultListModel<String>)listIntegrantesProy.getModel());
					dlm1.removeAllElements();
					dlm2.removeAllElements();
					for (String item : sistema.getListItems(listProyectosExistentes.getSelectedValue())) {
						dlm1.addElement(item);
					}
					for (String integrante : sistema.getIntegrantesProyecto(listProyectosExistentes.getSelectedValue())) {
						dlm2.addElement(integrante);
					}
					dlm2.setElementAt(dlm2.getElementAt(0) + "  >  Líder", 0);
				}
			}
		});
		scrollPane_17.setViewportView(listProyectosExistentes);
		
		JLabel lblProyectos = new JLabel("Proyectos");
		lblProyectos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProyectos.setBounds(22, 96, 90, 14);
		verProyPanel.add(lblProyectos);
		
		JLabel lblItems_1 = new JLabel("Items");
		lblItems_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems_1.setBounds(232, 96, 69, 14);
		verProyPanel.add(lblItems_1);
		
		JLabel lblEquipoDeTrabajo = new JLabel("Equipo de trabajo");
		lblEquipoDeTrabajo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEquipoDeTrabajo.setBounds(441, 95, 140, 14);
		verProyPanel.add(lblEquipoDeTrabajo);
		
		JButton btnNewButton_2 = new JButton("Enviar solicitud de participaci\u00F3n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listProyectosExistentes.getSelectedValue()!=null) {
					if (!sistema.getIntegrantesProyecto(listProyectosExistentes.getSelectedValue()).contains(usuarioactual)) {
						sistema.addNotificacionIng(listProyectosExistentes.getSelectedValue(), usuarioactual);
						JOptionPane.showMessageDialog(contentPane, "Se ha enviado una notificación al líder del proyecto " + listProyectosExistentes.getSelectedValue() + " con su solicitud", "Aviso", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(contentPane, "Ya forma parte del proyecto " + listProyectosExistentes.getSelectedValue(), "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Antes seleccione un proyecto", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(22, 310, 238, 23);
		verProyPanel.add(btnNewButton_2);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "wp");
			}
		});
		btnVolver.setBounds(533, 310, 89, 23);
		verProyPanel.add(btnVolver);
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((listTiposItem1.getSelectedValue()!=null) && (!NombreItem.getText().equals("")) && (!CampoPrioridad.getText().equals("")))  {
					if (!sistema.getListItems(proyectoactual).contains(NombreItem.getText())) {
						sistema.crearItem(proyectoactual, NombreItem.getText(), listTiposItem1.getSelectedValue(),Integer.parseInt(CampoPrioridad.getText()));
						DefaultListModel<String> dlm1 = ((DefaultListModel<String>)listItems1.getModel());
						dlm1.addElement(NombreItem.getText());
						DefaultListModel<String> dlm2 = ((DefaultListModel<String>)listItems2.getModel());
						dlm2.addElement(NombreItem.getText());
						DefaultListModel<String> dlm3 = ((DefaultListModel<String>)listItems3.getModel());
						dlm3.addElement(NombreItem.getText());
						DefaultListModel<String> dlm4 = ((DefaultListModel<String>)ListItems4.getModel());
						dlm4.addElement(NombreItem.getText());
						NombreItem.setText("");
						CampoPrioridad.setText("");
						JOptionPane.showMessageDialog(contentPane, "El item " + NombreItem.getText() + " fue creado correctamente", "Aviso", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(contentPane, "Ya existe un item con ese nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Debe completar todos los campos y elegir un tipo de item existente", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JMenuItem mntmSeleccionar = new JMenuItem("Seleccionar");
		mntmSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> dlm = ((DefaultListModel<String>)listProyectos.getModel());
				dlm.removeAllElements();
				for (String proyecto : sistema.getListProyUser(usuarioactual)) {
					dlm.addElement(proyecto);
				}
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "spp");
			}
		});
		
		mnProyecto.add(mntmSeleccionar);
		
		JMenuItem mntmVerProyectos = new JMenuItem("Ver proyectos");
		mntmVerProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> dlm1 = ((DefaultListModel<String>)listProyectosExistentes.getModel());
				dlm1.removeAllElements();
				DefaultListModel<String> dlm2 = ((DefaultListModel<String>)listItemsProy.getModel());
				dlm2.removeAllElements();
				DefaultListModel<String> dlm3 = ((DefaultListModel<String>)listIntegrantesProy.getModel());
				dlm3.removeAllElements();
				for (String proyecto : proyectos) {
					dlm1.addElement(proyecto);;
				}
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "vpp");
			}
		});
		mnProyecto.add(mntmVerProyectos);
	}
	
	public void setVisibilidad(JTabbedPane tabbedPane, JTabbedPane itemsTabbedPane) {
		switch (prioridadUser) {
		case 2: {
			tabbedPane.setEnabledAt(0, true);
			tabbedPane.setEnabledAt(1, true);
			tabbedPane.setEnabledAt(2, true);
			itemsTabbedPane.setEnabledAt(0, true);
			itemsTabbedPane.setEnabledAt(1, true);
			itemsTabbedPane.setEnabledAt(2, true);
			itemsTabbedPane.setEnabledAt(3, true);
			itemsTabbedPane.setEnabledAt(4, true);
			tabbedPane.setSelectedIndex(0);
			itemsTabbedPane.setSelectedIndex(0);
		} break;
		case 1: {
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(1, true);
			tabbedPane.setEnabledAt(2, true);
			itemsTabbedPane.setEnabledAt(0, false);
			itemsTabbedPane.setEnabledAt(1, false);
			itemsTabbedPane.setEnabledAt(2, true);
			itemsTabbedPane.setEnabledAt(3, true);
			itemsTabbedPane.setEnabledAt(4, true);
			tabbedPane.setSelectedIndex(1);
			itemsTabbedPane.setSelectedIndex(2);
		} break;
		case 0: {
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(1, true);
			tabbedPane.setEnabledAt(2, true);
			itemsTabbedPane.setEnabledAt(0, false);
			itemsTabbedPane.setEnabledAt(1, false);
			itemsTabbedPane.setEnabledAt(2, false);
			itemsTabbedPane.setEnabledAt(3, true);
			itemsTabbedPane.setEnabledAt(4, true);
			tabbedPane.setSelectedIndex(1);
			itemsTabbedPane.setSelectedIndex(3);
		} break;
		}
	}
	
	public String getRol() {
		switch (sistema.getCargo(proyectoactual, usuarioactual)) {
		case 2: {
			prioridadUser = 2;
			return "Líder";
		}
		case 1: {
			prioridadUser = 1;
			return "Resp. item";
		}
		default: {
			prioridadUser = 0;
			return "Integrante";
		}
		}
	}
	
	public void cargarListas(ArrayList<JList<String>> listas) {
		ArrayList<String> usuarios = sistema.getListUsuarios();
		ArrayList<String> tipoItems = sistema.getListTipoItems();
		int aux = 0;
		for (JList<String> lista : listas) {
			DefaultListModel<String> dlm = ((DefaultListModel<String>)lista.getModel());
			if (aux==0) {
				for (String user : usuarios) {
					dlm.addElement(user);
				}
			} else {
				if (aux==1 || aux==2) {
					for (String ti : tipoItems) {
						dlm.addElement(ti);
					}
				} else {
					if (aux==3 || aux==4) {
						for (String integrante : sistema.getIntegrantesProyecto(proyectoactual)) {
							dlm.addElement(integrante);
						}
						dlm.setElementAt(dlm.getElementAt(0) + "  >  Líder", 0);
					} else {
						if (aux == 5) {
							if (prioridadUser==1) {
								for (String i : sistema.getItemsBajoResp(proyectoactual, usuarioactual)) {
									dlm.addElement(i);
								}
							} else {
								if (prioridadUser==2) {
									for (String i : sistema.getListItems(proyectoactual)) {
										dlm.addElement(i);
									}
								}
							}
						}
						else {
							for (String i : sistema.getListItems(proyectoactual)) {
								dlm.addElement(i);
							}
						}
					}
				}
			}
			aux += 1;
		}
	}
	
	public void vaciarListas(ArrayList<JList<String>> listas) {
		for (JList<String> lista : listas) {
			DefaultListModel<String> dlm = ((DefaultListModel<String>)lista.getModel());
			dlm.removeAllElements();
		}
	}
	
	public boolean verificaFecha(String fecha) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
			@SuppressWarnings("unused")
			LocalDate date = LocalDate.parse(fecha, formatter);
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(contentPane, "Formato de fecha inválido", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} catch (DateTimeException e) {
			JOptionPane.showMessageDialog(contentPane, "Fecha inválida", "Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
}
