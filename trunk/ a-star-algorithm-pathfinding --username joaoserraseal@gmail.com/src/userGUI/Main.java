package userGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import mainCore.AStar;
import mainCore.Location;
import mainCore.Path;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Main extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JComboBox jComboBox1;
	private JComboBox jComboBox2;
	private JLabel jLabel3;
	private JButton jButton1;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
	private JComboBox jComboBox3;
	public AStar myAlgorithm = new AStar();
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main inst = new Main();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				
				
			}
		});
	}
	
	public Main() {
		super("\"A Star\" em pesquisa de trajectos");
		
		initGUI();
	
	}
	
	
	private void initGUI() {
		try {
			this.myAlgorithm = new AStar();
			// Add Information  - THIS IS CRITICAL 
			addCities();
			addPaths();
			addRoads();
			
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Origem");
			}
			{
				jButton4 = new JButton();
				jButton4.setText("Info");
				jButton4.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						jButton4MouseClicked(evt);
					}
				});
			}
			{
				jButton3 = new JButton();
				jButton3.setText("Sobre");
				jButton3.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						jButton3MouseClicked(evt);
					}
				});
			}

			{
				jLabel2 = new JLabel();
				jLabel2.setText("Destino");
			}
			{
				jComboBox1 = new JComboBox();
				Set keys = myAlgorithm.cities.keySet();
				jComboBox1.addItem("Selecione uma Origem");
				for (Iterator i = keys.iterator(); i.hasNext();){
					 String key = (String) i.next();
					jComboBox1.addItem(myAlgorithm.cities.get(key).getName());
					
				}
			}
			{
				jComboBox2 = new JComboBox();
				Set keys = myAlgorithm.cities.keySet();
				jComboBox2.addItem("Selecione um Destino");
				for (Iterator i = keys.iterator(); i.hasNext();){
					 String key = (String) i.next();
					jComboBox2.addItem(myAlgorithm.cities.get(key).getName());
					
				}
			}
			{
				jLabel3 = new JLabel();
				jLabel3.setText("Metrica");
			}
			{
				ComboBoxModel jComboBox3Model = 
					new DefaultComboBoxModel(
							new String[] {"Selecione uma Metrica", "Distancia", "Tempo" });
				jComboBox3 = new JComboBox();
				jComboBox3.setModel(jComboBox3Model);
				
			}
			{
				jButton1 = new JButton();
				jButton1.setText("Sair");
				jButton1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						jButton1MouseClicked(evt);
					}
				});
			}
			{
				jButton2 = new JButton();
				jButton2.setText("Pesquisar");
				jButton2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						jButton2MouseClicked(evt);
					}
				});
			}

			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jComboBox1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jComboBox2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jComboBox3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, Short.MAX_VALUE)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jButton4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jButton3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jButton2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jButton1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(jButton4, GroupLayout.Alignment.LEADING, 0, 95, Short.MAX_VALUE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 42, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 42, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 34, Short.MAX_VALUE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
				    .addComponent(jComboBox3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jComboBox2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jComboBox1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(33, 33));
			pack();
			this.setSize(415, 194);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
		
	
	}
	public void addPaths()
	{
		//TODO: Change this array to String with names of the citys
		// Adds the first path
		Location[] road1 = new Location[3];
		road1[0] = this.myAlgorithm.getLocation("Beja");
		road1[1] = this.myAlgorithm.getLocation("Lisboa");
		road1[2] = this.myAlgorithm.getLocation("Aveiro");
		this.myAlgorithm.addPath("bus1",road1);
		
		// Adds the second path
		Location[] road2 = new Location[3];
		road2[0] = this.myAlgorithm.getLocation("Porto");
		road2[1] = this.myAlgorithm.getLocation("Lisboa");
		road2[2] = this.myAlgorithm.getLocation("Beja");
		this.myAlgorithm.addPath("bus2",road2);
		
		// Adds the third path
		Location[] road3 = new Location[3];
		road3[0] = this.myAlgorithm.getLocation("Porto");
		road3[1] = this.myAlgorithm.getLocation("Lisboa");
		road3[2] = this.myAlgorithm.getLocation("Aveiro");
		this.myAlgorithm.addPath("bus3",road3);
		
		// Adds the third path
		Location[] road4 = new Location[2];
		road4[0] = this.myAlgorithm.getLocation("Porto");
		road4[1] = this.myAlgorithm.getLocation("Beja");
		this.myAlgorithm.addPath("bus4",road4);

		// Values of path can be calculated using an already made function to calc the distance between two cities
	}
	
	/**
	 * Add roads with maximum speed between the cities
	 */
	public void addRoads()
	{
		// From , to, max speed
		// Each road has two directions, may have different speeds
		this.myAlgorithm.addRoad("Beja", "Lisboa", 120);
		this.myAlgorithm.addRoad("Lisboa", "Beja", 120);
		
		this.myAlgorithm.addRoad("Beja", "Aveiro", 80);
		this.myAlgorithm.addRoad("Aveiro", "Beja", 80);
		
		this.myAlgorithm.addRoad("Beja", "Porto", 10.0);
		this.myAlgorithm.addRoad("Porto", "Beja", 10.0);
		
		this.myAlgorithm.addRoad("Lisboa", "Aveiro", 80);
		this.myAlgorithm.addRoad("Aveiro", "Lisboa", 80);
		
		this.myAlgorithm.addRoad("Lisboa", "Porto", 120);
		this.myAlgorithm.addRoad("Porto", "Lisboa", 120);
		
		this.myAlgorithm.addRoad("Aveiro", "Porto", 80);
		this.myAlgorithm.addRoad("Porto", "Aveiro", 80);
	}
	
	/**
	*  Add cities
	*/
	public void addCities()
	{
		// Associate the neighbors to create the graph
		String[] neighborsBeja = new String[2];
		neighborsBeja[0] = "Lisboa";
		neighborsBeja[1] = "Porto";
		
		String[] neighborsPorto = new String[2];
		neighborsPorto[0] = "Aveiro";
		neighborsPorto[1] = "Lisboa";
		
		String[] neighborsAveiro = new String[2];
		neighborsAveiro[0] = "Porto";
		neighborsAveiro[1] = "Lisboa";
		
		String[] neighborsLisboa = new String[2];
		neighborsLisboa[0] = "Aveiro";
		neighborsLisboa[1] = "Beja";
		
		String[] neighborsPortalegre = new String[4];
		neighborsPortalegre[0] = "Aveiro";
		neighborsPortalegre[1] = "Beja";
		neighborsPortalegre[2] = "Porto";
		neighborsPortalegre[3] = "Lisboa";
		
		//Coordinates obtained via google earth
		Location beja = new Location("Beja",
				38.0, 0.0, 55.0, //latitude
				7.0, 51.0, 54.0, //longitude
				neighborsBeja);
		
		Location porto = new Location("Porto",
				41.0, 8.0, 59.0, //latitude
				8.0, 36.0, 36.0, //longitude
				neighborsPorto);
		
		Location aveiro = new Location("Aveiro",
				40.0, 38.0, 28.0, //latitude
				8.0, 39.0, 13.0, //longitude
				neighborsPorto);
		
		Location lisboa = new Location("Lisboa",
				38.0, 42.0, 25.0, //latitude
				9.0, 8.0, 7.0, //longitude
				neighborsLisboa);
		
		Location portalegre = new Location("Portalegre",
				39.0,17.0,29.10, // latitude
				7.0,25.0,56.54, // longitude
				neighborsPortalegre);
		
		this.myAlgorithm.addLocation(beja.getName(),beja);
		this.myAlgorithm.addLocation(porto.getName(),porto);
		this.myAlgorithm.addLocation(aveiro.getName(),aveiro);
		this.myAlgorithm.addLocation(lisboa.getName(),lisboa);
		this.myAlgorithm.addLocation(portalegre.getName(),portalegre);
	}
	
/**
 * Find the distance between two cities
 * @author Joao Serra
 *
 */
	class getDistanceButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			String city1 = (String)JOptionPane.showInputDialog("Nome da primeira cidade");
			String city2 = (String)JOptionPane.showInputDialog("Nome da segunda cidade");
			JOptionPane.showMessageDialog(null,"Distancia: " + Math.round(myAlgorithm.calcDistance(city1, city2)));
		}
	}
	
	
	private void jButton1MouseClicked(MouseEvent evt) {
		
		System.exit(0);
	}
	
	private void jButton2MouseClicked(MouseEvent evt) {
		 if(jComboBox1.getSelectedItem().equals("Selecione uma Origem")){
			 JOptionPane.showMessageDialog(null,"Selecione uma Origem ");
			 return;}	 
		 if(jComboBox2.getSelectedItem().equals("Selecione um Destino")){
				 JOptionPane.showMessageDialog(null,"Selecione um Destino ");
				 return;}
		if(!jComboBox1.getSelectedItem().equals(jComboBox2.getSelectedItem())){
			if(jComboBox3.getSelectedItem().equals("Distancia")){
				myAlgorithm.setCalcByDistance(true);
				 String city1 = (String)jComboBox1.getSelectedItem();
					String city2 = (String)jComboBox2.getSelectedItem();
					Path result = myAlgorithm.findPath(city1, city2);
					JOptionPane.showMessageDialog(null,
							"Caminho de " + city1 + " para " + city2 + ": " + result.getCities().toString() +
							"\nCom custo: " + Math.round(result.cost) +
							"\nAutocarros: " + result.getBusList().toString() + 
							"\nTransbordos em: " + result.getChangeBusStops()
					);	 
					return;
			}
			else if(jComboBox3.getSelectedItem().equals("Tempo")){
				myAlgorithm.setCalcByDistance(false);
				 String city1 = (String)jComboBox1.getSelectedItem();
					String city2 = (String)jComboBox2.getSelectedItem();
					Path result = myAlgorithm.findPath(city1, city2);
					JOptionPane.showMessageDialog(null,
							"Caminho de " + city1 + " para " + city2 + ": " + result.getCities().toString() +
							"\nCom custo: " + Math.round(result.cost) +
							"\nAutocarros: " + result.getBusList().toString() + 
							"\nTransbordos em: " + result.getChangeBusStops()
					);	 
					return;
			}
			else
				 JOptionPane.showMessageDialog(null,"Selecione a metrica que pretende");	 
			
	 }else{
		 JOptionPane.showMessageDialog(null,"Origem tem de ser Obrigatoriamente diferente do Destino ");	 
			return;
	 }
	}
	
	private void jButton3MouseClicked(MouseEvent evt) {
		JOptionPane.showMessageDialog(null,"Feup 2010 - Projecto de Inteligencia Artificial \n \n Pesquisa de trajectos em redes de \n               transportes p�blicos");
		}
	
	private void jButton4MouseClicked(MouseEvent evt) {
		 String city1 = (String)jComboBox1.getSelectedItem();
		 String city2 = (String)jComboBox2.getSelectedItem();
		 JOptionPane.showMessageDialog(null,
				"Distancia: " + Math.round(myAlgorithm.calcDistance(city1, city2)) +
				"\nVelocidade m�xima: " + myAlgorithm.getRoadMaxSpeed(city1, city2) +
				"\nTempo: " + Math.round(myAlgorithm.calcTime(city1, city2)),
				city1 + "-->" + city2,
				JOptionPane.INFORMATION_MESSAGE
		);
	}

}
