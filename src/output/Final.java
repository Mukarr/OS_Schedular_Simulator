/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package output;
import java.util.*;
import strategy.*;
import scheduler.*;
import scheduler.Process;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JList;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.Color;

/**
 *
 * @author Ashish
 */


public class Final extends javax.swing.JFrame {

	
    /**
     * Creates new form NewJFrame
     */
    public Final() {
    	getContentPane().setBackground(Color.WHITE);
    	setBackground(Color.WHITE);
    	setTitle("CS307-SP");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        input = new javax.swing.JPanel();
        input.setBackground(Color.WHITE);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        input.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Process Scheduling Simulator", 0, 0, new java.awt.Font("Tahoma", 1, 14)));
//        btnRun.addActionListener(new ActionListener() {
//        
//        
//        });
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        
        JLabel lblWelcome = new JLabel("Thanks !!");
        lblWelcome.setFont(new Font("DejaVu Serif", Font.BOLD, 14));
        
        JTextPane txtpnTheSimulltionApplication = new JTextPane();
        txtpnTheSimulltionApplication.setEditable(false);
        txtpnTheSimulltionApplication.setBackground(Color.WHITE);
        txtpnTheSimulltionApplication.setText("Output files have been generated in the current directory.\nThe Comparision between various policies of scheduling can be seen in the output files.\n");
        
        JTextPane txtpnCreatedBy = new JTextPane();
        txtpnCreatedBy.setBackground(Color.WHITE);
        txtpnCreatedBy.setFont(new Font("DejaVu Serif", Font.ITALIC, 8));
        txtpnCreatedBy.setText("Created By:\nMukarram Tailor\nAShish Kumar Bedi");
        
    
        javax.swing.GroupLayout inputLayout = new javax.swing.GroupLayout(input);
        inputLayout.setHorizontalGroup(
        	inputLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(inputLayout.createSequentialGroup()
        			.addGroup(inputLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(inputLayout.createSequentialGroup()
        					.addGap(24)
        					.addGroup(inputLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(txtpnCreatedBy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtpnTheSimulltionApplication, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(inputLayout.createSequentialGroup()
        					.addGap(37)
        					.addGroup(inputLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblWelcome)
        						.addComponent(btnExit))))
        			.addContainerGap(53, Short.MAX_VALUE))
        );
        inputLayout.setVerticalGroup(
        	inputLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(inputLayout.createSequentialGroup()
        			.addGroup(inputLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(inputLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(txtpnCreatedBy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(inputLayout.createSequentialGroup()
        					.addGap(42)
        					.addComponent(txtpnTheSimulltionApplication, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(lblWelcome)
        					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
        					.addComponent(btnExit)
        					.addGap(97)))
        			.addContainerGap())
        );
        input.setLayout(inputLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(input, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(24))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(input, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void Newscreen2() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Final.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Final.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Final.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Final.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Final().setVisible(true);
            }
        });
    }
    private javax.swing.JPanel input;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final Action action = new SwingAction();
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}