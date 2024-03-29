
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Michael
 */
public final class Sin_accidentes extends javax.swing.JFrame implements Runnable {

    Thread t;
    int dcnt=0,hcnt=0,mcnt=0,scnt=0,mscnt=0,svcnt=0; 
    String str="",nstr="",mstr="",dstr="";
    int cnt=0,cnt2=0;
    PanamaHitek_Arduino ino;

    
    /**
     * Creates new form Sin_accidentes
     */
    public Sin_accidentes() {
        initComponents();
        ino = new PanamaHitek_Arduino();
        try {
            ino.arduinoRXTX("COM4", 9600, listener);
        } catch (ArduinoException ex) {
            Logger.getLogger(Sin_accidentes.class.getName()).log(Level.SEVERE, null, ex);
        }
        setExtendedState(MAXIMIZED_BOTH);
        t=new Thread(this);
        reset();
    }
    
    SerialPortEventListener listener = new SerialPortEventListener(){
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if(ino.isMessageAvailable()==true){
                    t.suspend();
                    accidentes.setText("<html><font color=red>¡ALERTA! ACCIDENTE REPORTADO</font></html>");
                }
            } catch (SerialPortException ex) {
                Logger.getLogger(Sin_accidentes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ArduinoException ex) {
                Logger.getLogger(Sin_accidentes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    };
            
    public void reset()
    {
        dcnt=0;hcnt=0;mcnt=0;scnt=0;mscnt=0;cnt2=0;
        nstr="00:00:00:00";mstr="000";dstr="";
        display();
    }
    public void display()
    {
            lblDisp1.setText(nstr);
    }
    public void setTimeCounter()
    {
        nstr="";
        if(dcnt<10)
        {
            nstr="0"+dcnt;
        }
        else
        {
            nstr=""+dcnt;
        }
        
        if(hcnt<10)
        {
            nstr+=":0"+hcnt;
        }
        else
        {
            nstr+=":"+hcnt;
        }
        
        if(mcnt<10)
        {
            nstr+=":0"+mcnt;
        }
        else
        {
            nstr+=":"+mcnt;
        }
        
        if(scnt<10)
        {
            nstr+=":0"+scnt;
        }
        else
        {
            nstr+=":"+scnt;
        }
    }
    
    public void setMTimeCounter()
    {
        mstr="";
        if(mscnt<10)
        {
            mstr="00"+mscnt;
        }
        else if(mscnt>=10 && mscnt<100)
        {
            mstr="0"+mscnt;
        }
        else
        {
            mstr=""+mscnt;
        }
    }
    
    public void run()
    {
        try
        {
            while(true)
            {
                mscnt++;
                if(mscnt>999)
                {
                    mscnt=0;
                    scnt++;
                }
                if(scnt>59)
                {
                    scnt=0;
                    mcnt++;
                    dias_record.setText(String.valueOf(mcnt));
                }
                if(mcnt>59)
                {
                    mcnt=0;
                    hcnt++;
                }
                if(hcnt>24)
                {
                    hcnt=0;
                    dcnt++;
                }
                setTimeCounter();
                setMTimeCounter();
                display();
                Thread.sleep(1);
            }
        }
        catch(Exception e){}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPopupMenu();
        iniciar = new javax.swing.JMenuItem();
        parar = new javax.swing.JMenuItem();
        reiniciar = new javax.swing.JMenuItem();
        header = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        cumplimos = new javax.swing.JLabel();
        display = new javax.swing.JPanel();
        lblDisp1 = new javax.swing.JLabel();
        tiempo = new javax.swing.JLabel();
        accidentes = new javax.swing.JLabel();
        record = new javax.swing.JLabel();
        dias_record = new javax.swing.JLabel();
        dias = new javax.swing.JLabel();

        iniciar.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\Sin_accidentes\\images\\start.png")); // NOI18N
        iniciar.setLabel("Iniciar");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        menu.add(iniciar);

        parar.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\Sin_accidentes\\images\\stop.png")); // NOI18N
        parar.setLabel("Parar");
        parar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pararActionPerformed(evt);
            }
        });
        menu.add(parar);

        reiniciar.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\Sin_accidentes\\images\\reset.png")); // NOI18N
        reiniciar.setText("Reiniciar");
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });
        menu.add(reiniciar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setMaximumSize(new java.awt.Dimension(1920, 170));
        header.setMinimumSize(new java.awt.Dimension(1920, 170));
        header.setPreferredSize(new java.awt.Dimension(1920, 170));

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        body.setBackground(new java.awt.Color(3, 85, 163));
        body.setForeground(new java.awt.Color(3, 85, 163));
        body.setPreferredSize(new java.awt.Dimension(1920, 768));

        cumplimos.setFont(new java.awt.Font("Tahoma", 0, 100)); // NOI18N
        cumplimos.setForeground(new java.awt.Color(255, 255, 255));
        cumplimos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cumplimos.setText("EN ESTE MOMENTO CUMPLIMOS");
        cumplimos.setMaximumSize(new java.awt.Dimension(1920, 44));
        cumplimos.setMinimumSize(new java.awt.Dimension(1920, 44));
        cumplimos.setPreferredSize(new java.awt.Dimension(1920, 44));

        display.setBackground(new java.awt.Color(0, 0, 0));
        display.setMaximumSize(new java.awt.Dimension(1920, 92));
        display.setMinimumSize(new java.awt.Dimension(1920, 92));
        display.setPreferredSize(new java.awt.Dimension(1920, 92));

        lblDisp1.setFont(new java.awt.Font("DialogInput", 1, 200)); // NOI18N
        lblDisp1.setForeground(new java.awt.Color(255, 0, 0));
        lblDisp1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDisp1.setText("00:00:00:00");
        lblDisp1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblDisp1.setPreferredSize(new java.awt.Dimension(1920, 92));

        javax.swing.GroupLayout displayLayout = new javax.swing.GroupLayout(display);
        display.setLayout(displayLayout);
        displayLayout.setHorizontalGroup(
            displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayLayout.createSequentialGroup()
                .addComponent(lblDisp1, javax.swing.GroupLayout.PREFERRED_SIZE, 1439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        displayLayout.setVerticalGroup(
            displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayLayout.createSequentialGroup()
                .addComponent(lblDisp1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        tiempo.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        tiempo.setForeground(new java.awt.Color(255, 255, 255));
        tiempo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tiempo.setText("                    DÍAS            HRS             MIN             SEG    ");
        tiempo.setMaximumSize(new java.awt.Dimension(1920, 29));

        accidentes.setFont(new java.awt.Font("Tahoma", 1, 100)); // NOI18N
        accidentes.setForeground(new java.awt.Color(255, 255, 255));
        accidentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accidentes.setText("SIN ACCIDENTES");
        accidentes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        accidentes.setMaximumSize(new java.awt.Dimension(1920, 58));

        record.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        record.setForeground(new java.awt.Color(255, 255, 255));
        record.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        record.setText("Nuestro record:");
        record.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        dias_record.setFont(new java.awt.Font("Tahoma", 1, 100)); // NOI18N
        dias_record.setForeground(new java.awt.Color(255, 255, 255));
        dias_record.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dias_record.setText("0");
        dias_record.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        dias.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        dias.setForeground(new java.awt.Color(255, 255, 255));
        dias.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dias.setText("días sin accidentes.");
        dias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accidentes, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cumplimos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bodyLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(record, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dias_record, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(bodyLayout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(display, javax.swing.GroupLayout.PREFERRED_SIZE, 1440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addComponent(cumplimos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accidentes, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(record, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dias_record))
                    .addComponent(dias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        // Empieza el contador:
        accidentes.setText("SIN ACCIDENTES");
        cnt++;
        if(cnt==1)
        {
            t.start();
        }
        else
        {
            t.resume();
        }
    }//GEN-LAST:event_iniciarActionPerformed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        if(evt.isPopupTrigger())
        {
            menu.show(evt.getComponent(),evt.getX(),evt.getY());
        }
    }//GEN-LAST:event_formMouseReleased

    private void pararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pararActionPerformed
        // TODO add your handling code here:
        t.suspend();
        accidentes.setText("<html><font color=red>¡ALERTA! ACCIDENTE REPORTADO</font></html>");
    }//GEN-LAST:event_pararActionPerformed

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
        // Reinicia el contador:
        reset();
        t.suspend();
        accidentes.setText("SIN ACCIDENTES");
    }//GEN-LAST:event_reiniciarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Sin_accidentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sin_accidentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sin_accidentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sin_accidentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sin_accidentes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accidentes;
    private javax.swing.JPanel body;
    private javax.swing.JLabel cumplimos;
    private javax.swing.JLabel dias;
    private javax.swing.JLabel dias_record;
    private javax.swing.JPanel display;
    private javax.swing.JPanel header;
    private javax.swing.JMenuItem iniciar;
    private javax.swing.JLabel lblDisp1;
    private javax.swing.JLabel logo;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JMenuItem parar;
    private javax.swing.JLabel record;
    private javax.swing.JMenuItem reiniciar;
    private javax.swing.JLabel tiempo;
    // End of variables declaration//GEN-END:variables
}
