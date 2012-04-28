package view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import model.Link;
import javax.swing.SwingConstants;

public class GuiUrlNotifier extends JFrame {
	private JTextField txtUrl;
	private JLabel lblTitle;
	private JLabel lblText;
	public static void main(String[] args){
		/*This funcion is for debug*/
		Link link=new Link("schiantaberetti", "www.schiantaberetti.com");
		GuiUrlNotifier notif=new GuiUrlNotifier(link);
		notif.setVisible(true);
		notif.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public GuiUrlNotifier(Link link) {
		this.setTitle("URL Notifier");
		this.setSize(500,150);
		lblTitle = new JLabel(link.getName());
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblText = new JLabel("");
		
		txtUrl = new JTextField();
		txtUrl.setText(link.getUrl());
		txtUrl.setEditable(false);
		txtUrl.selectAll();
		txtUrl.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(166)
					.addComponent(lblText, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
					.addGap(174))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
						.addComponent(txtUrl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
					.addGap(23))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblText)
					.addGap(18)
					.addComponent(txtUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}
