package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.Slave;


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
public class Gui extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static Gui istance=null;
	private JTextField searchField;
	private JButton searchButton;
	private JLabel hostsLabel;
	private JList linksPage;
	private JList sitesList;
	private JComboBox hostSelect;
	private JLabel sitesLabel;
	private JLabel statusLabel;
	private JButton infoButton;
	private ArrayList<model.LinkCollector> collectors=null;
	private ArrayList<model.Link> streamingHosts=null;
	private ArrayList<model.Link> showedLinks=null;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane1;
	/**
	* Auto-generated main method to display this JFrame
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Gui inst = new Gui();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	*/
	private Gui() {
		super();
		initGUI();
		this.setTitle("StreamFinder v1.1");
		this.setResizable(false);
		addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {			}
			public void focusGained(FocusEvent arg0) {
				updateData();
				repaint();
				for(Component c : getComponents()){
					c.repaint();
				}
			}
		});
	}
	public static Gui getGui(){
		if(istance==null)
			istance= new Gui();
		return istance;
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			{
				searchField = new JTextField();
				searchField.setText("Type your movie name");
				searchField.selectAll();
				searchField.addKeyListener(new KeyListener() {
					public void keyTyped(KeyEvent arg0) {
					}
					public void keyReleased(KeyEvent arg0) {
					}
					public void keyPressed(KeyEvent e) {
						if(e.getID()!= KeyEvent.KEY_TYPED && e.getKeyCode()==10){
							Slave.getIstance().sendMsg(searchField.getText());
							searchButton.setEnabled(false);
						}
					}
				});
			}
			{
				searchButton = new JButton();
				searchButton.setText("Search");
				searchButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Slave.getIstance().sendMsg(searchField.getText());
						searchButton.setEnabled(false);
					}
				});
			}
			{
				infoButton = new JButton();
				infoButton.setText("Info");
				infoButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,"StreamFinder v1.1\nAuthor: baldo\nwww.schiantaberetti.com");
					}
				});
			}
			{
				statusLabel = new JLabel();
				statusLabel.setText("STATUS: waiting");
				statusLabel.setHorizontalTextPosition(JLabel.CENTER);
				statusLabel.setBackground(Color.CYAN);
				statusLabel.setFont(new Font("arial",Font.BOLD,16));
			}
			{
				sitesLabel = new JLabel();
				sitesLabel.setText("Sites surfed:");
			}
			{
				hostsLabel = new JLabel();
				hostsLabel.setText("Streaming hosts:");
			}
			{
				streamingHosts = core.XMLStuff.parseTrustedLinks();
				String[] choice = new String[streamingHosts.size()+1];
				choice[0]="All";
				int i=1;
				for(model.Link l : streamingHosts){
					choice[i] = l.getName();
					i++;
				}
				
				ComboBoxModel hostSelectModel = 
						new DefaultComboBoxModel(choice);
				hostSelect = new JComboBox();
				hostSelect.setModel(hostSelectModel);
				hostSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateData();
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(searchButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(searchField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
				    .addComponent(infoButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(hostSelect, GroupLayout.Alignment.BASELINE, 0, 24, Short.MAX_VALUE)
				    .addComponent(hostsLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(sitesLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(getJScrollPane2(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getJScrollPane1(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(getJScrollPane1(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(83)
				                .addComponent(sitesLabel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
				                .addGap(73)))
				        .addGap(17)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(thisLayout.createSequentialGroup()
				                .addComponent(getJScrollPane2(), GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 0, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(156)
				                .addComponent(hostsLabel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
				                .addComponent(hostSelect, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 58, Short.MAX_VALUE))))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addComponent(infoButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 689, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 9, Short.MAX_VALUE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED));
			pack();
			this.setSize(717, 450);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public JTextField getSearchField() {
		return searchField;
	}

	private void updateData(){
		sitesList.setSelectedIndex(-1);
		sitesList.setEnabled(false);
		if (!(sitesList.getSelectedIndex() == -1)) {
        	//model.LinkCollector lc;
        	linksPage.removeAll();
        	String streamChoice=(String)hostSelect.getSelectedItem();
        	ArrayList<String> links= new ArrayList<String>();
        	showedLinks =new ArrayList<model.Link>();
        	int i=1;
        	
        	for(model.LinkCollector lc : collectors)
        		if(lc.getName().equalsIgnoreCase((String)sitesList.getSelectedValue()))
        			for(model.Link l : lc.getLinks())
        				if(streamChoice.equals("All") || l.getUrl().contains(getUrlByName(streamChoice, streamingHosts))){
        					showedLinks.add(l);					  
        					links.add(i+" - "+l.getName());
        					i++;
        				}
        	ListModel sitesListModel = 
    				new DefaultComboBoxModel(links.toArray());
    		linksPage.setModel(sitesListModel);
    		sitesList.setSelectedIndex(-1);
    		sitesList.setEnabled(true);
        	//linksPage = new JList(new {"fs","fsafsf"});
        }
	}
	public JLabel getStatusLabel() {
		return statusLabel;
	}
	public void setStatus(String s){
		statusLabel.setText(s);
	}
	public void setCollectors(ArrayList<model.LinkCollector> c){
		collectors=c;
		searchButton.setEnabled(true);
		repaint();
		sitesList.repaint();
	}
	public JList getSitesList() {
		return sitesList;
	}
	public void setSites(String[] s){
		ListModel sitesListModel = 
				new DefaultComboBoxModel(s);
		sitesList.setModel(sitesListModel);
	}
	

	private String getUrlByName(String name,ArrayList<model.Link> lns){
		for(model.Link l:lns)
			if(l.getName().equals(name))
				return l.getUrl();
		return "";
	}
	
	private JScrollPane getJScrollPane1() {
		if(jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			{
				ListModel sitesListModel = 
						new DefaultComboBoxModel(
								new String[] {  });
				sitesList = new JList();
				jScrollPane1.setViewportView(sitesList);
				sitesList.setModel(sitesListModel);
				sitesList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting() == false) {
							updateData();
						}
					}
				}
						);
			}
		}
		return jScrollPane1;
	}
	
	private JScrollPane getJScrollPane2() {
		if(jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			{
				ListModel sitesListModel = 
						new DefaultComboBoxModel(
								new String[] {  });
				linksPage = new JList();
				jScrollPane2.setViewportView(linksPage);
				linksPage.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting() == false) {
							openLink(e.getLastIndex());
						}
					}
				}
						);
			}
		}
		return jScrollPane2;
	}
	private void openLink(int id){
		 if (!(sitesList.getSelectedIndex() == -1) && sitesList.isEnabled()) {
			 if( java.awt.Desktop.isDesktopSupported() && java.awt.Desktop.getDesktop().isSupported( java.awt.Desktop.Action.BROWSE ) )
				try {
					java.awt.Desktop.getDesktop().browse(new URI(showedLinks.get(id).getUrl()));
				} catch (Exception e) {
					System.out.println("Your platform doesn't support browser launching.\nAnyway the URL you are looking for is:\n"+showedLinks.get(id).getUrl());
					GuiUrlNotifier notif= new GuiUrlNotifier(showedLinks.get(id));
					notif.setLocation(200, 200);
					notif.setVisible(true);

				}
			 else
				 JOptionPane.showMessageDialog(null, "Your platform doesn't support browser launching.\nAnyway the URL you are looking for is:\n"+showedLinks.get(id).getUrl());
			 sitesList.setSelectedIndex(-1);
		 }
		 
	}
}
