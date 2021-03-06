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
import java.awt.Color;

/**
 *
 * @author Ashish
 */


public class Guiframe extends javax.swing.JFrame {
	
	JRadioButton rdbtnSRJF;
	 JRadioButton rdbtnALL;
	 JRadioButton rdbtnRR;
	 JRadioButton rdbtnFCFS;
	int strategy;
	String inputFile = "/home/mukarram/Desktop/input1.txt";
	ArrayList<Process> proc = null;
	Status[] procStatus = null;
	
    /**
     * Creates new form NewJFrame
     */
    public Guiframe() {
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
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        input.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Process Scheduling Simulator", 0, 0, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setText(" Strategies");
        jTextField1.setText("Path of the file");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        
        rdbtnSRJF = new JRadioButton("Shortest Remaining Job First");
        buttonGroup.add(rdbtnSRJF);
        
        rdbtnFCFS = new JRadioButton("First-Come-First-Serve");
        buttonGroup.add(rdbtnFCFS);
        
        rdbtnRR = new JRadioButton("Round Robin");
        buttonGroup.add(rdbtnRR);
        
        rdbtnALL = new JRadioButton("All");
        buttonGroup.add(rdbtnALL);
        
     
        JButton btnRun = new JButton("Run");
        btnRun.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(rdbtnRR.isSelected()){
        			strategy = 1;
        			System.out.println("RR is selected");
        		}
        		else if(rdbtnFCFS.isSelected()){
        			strategy = 0;
        		}
        		else if(rdbtnALL.isSelected()){
        			strategy = -1;
        		}
        		else if(rdbtnSRJF.isSelected()){
        			strategy = 2;
        		}
        		
        		inputFile = jTextField1.getText();
        		
        		proc = Tool.fetch(Tool.read(inputFile));
        		procStatus = new Status[proc.size()];
        		
        		for(int i = 0; i < procStatus.length; i ++) {
        			procStatus[i] = Status.NEW;
        		}
        		//strategy = -1;
        		
        		
        			if(strategy >= 0) {
        				//System.out.println("sdaaf");
        				Scheduler.exec(strategy, proc, procStatus, false);
        			}
        			else if(strategy == -1) {
        				/**
        				 * if perform multi-strategies once, need deep copy
        				 */
        				System.out.println("I am here");
        				Scheduler.exec(0, proc, procStatus, true);
        				Scheduler.exec(1, proc, procStatus,true);
        				Scheduler.exec(2, proc, procStatus,true);
        			}
        		Final nw = new Final();
            	nw.Newscreen2();
            	Guiframe.this.setVisible(false);
        	
        	}
        });
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        
        lbInputFile = new JLabel("Input file");
        lbInputFile.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
        
    
        javax.swing.GroupLayout inputLayout = new javax.swing.GroupLayout(input);
        inputLayout.setHorizontalGroup(
        	inputLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(inputLayout.createSequentialGroup()
        			.addGap(16)
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(440, Short.MAX_VALUE))
        		.addGroup(inputLayout.createSequentialGroup()
        			.addContainerGap(62, Short.MAX_VALUE)
        			.addGroup(inputLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(rdbtnALL, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
        				.addComponent(rdbtnSRJF)
        				.addComponent(rdbtnFCFS)
        				.addComponent(rdbtnRR))
        			.addGap(290))
        		.addGroup(inputLayout.createSequentialGroup()
        			.addGroup(inputLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(inputLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
        				.addGroup(Alignment.LEADING, inputLayout.createSequentialGroup()
        					.addGap(51)
        					.addComponent(btnRun)
        					.addGap(18)
        					.addComponent(btnExit)))
        			.addContainerGap(380, Short.MAX_VALUE))
        		.addGroup(inputLayout.createSequentialGroup()
        			.addGap(30)
        			.addComponent(lbInputFile)
        			.addContainerGap(461, Short.MAX_VALUE))
        );
        inputLayout.setVerticalGroup(
        	inputLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(inputLayout.createSequentialGroup()
        			.addGap(16)
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(rdbtnRR)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(rdbtnFCFS)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(rdbtnSRJF)
        			.addGap(18)
        			.addComponent(rdbtnALL)
        			.addGap(20)
        			.addComponent(lbInputFile)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
        			.addGroup(inputLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnExit)
        				.addComponent(btnRun))
        			.addGap(26))
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
       
    	
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void Newscreen() {
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
            java.util.logging.Logger.getLogger(Guiframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Guiframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Guiframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Guiframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Guiframe().setVisible(true);
            }
        });
    }
    private javax.swing.JPanel input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final Action action = new SwingAction();
    private JLabel lbInputFile;
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
