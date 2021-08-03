import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

public class gui extends JFrame {
        private GridLayout gl = new GridLayout(3,3);
        private ImageIcon iconepdf = new  ImageIcon(this.getClass().getResource("resources/pdf.png"));
        private ImageIcon iconeexcel = new  ImageIcon(this.getClass().getResource("resources/excel.png"));
        private ImageIcon iconeconvert = new  ImageIcon(this.getClass().getResource("resources/convert.png"));
        private ImageIcon iconequit = new  ImageIcon(this.getClass().getResource("resources/quit.png"));
        private ImageIcon icon = new ImageIcon (this.getClass().getResource("resources/icon.png"));
        private JButton selectpdf = new JButton(iconepdf);
        private JButton selectexcel = new JButton(iconeexcel);
        private JButton convert = new JButton(iconeconvert);
        private JButton quit = new JButton(iconequit);
        private JTextField pathpdf=new JTextField(30);
        private JTextField pathexcel=new JTextField(30);
        private JOptionPane dialog = new JOptionPane();

        public gui() throws IOException {
            this.setTitle("PDFtoExcel");
            this.setSize(650, 200);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setIconImage(icon.getImage());
            this.setLayout(gl);
            gl.setHgap(20);
            gl.setVgap(20);
            this.getContentPane().add(selectpdf);
            this.getContentPane().add(selectexcel);
            this.getContentPane().add(pathpdf);
            this.getContentPane().add(pathexcel);
            this.getContentPane().add(convert);
            this.getContentPane().add(quit);

            pathpdf.setText("Selectionnez l'emplacement du fichier PDF à convertir");
            pathexcel.setText("Selectionnez l'emplacement du futur fichier Excel");
            pathpdf.setEnabled(false);
            pathexcel.setEnabled(false);
            this.setVisible(true);

            selectpdf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser choosepdf = new JFileChooser();
                    choosepdf.showOpenDialog(null);
                    File pdf=choosepdf.getSelectedFile();
                    String pdfpath=pdf.getAbsolutePath();
                    pathpdf.setText(pdfpath);
                }
            });

            selectexcel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        JFileChooser chooseexcel = new JFileChooser();
                        chooseexcel.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        chooseexcel.showSaveDialog(null);
                        File excel = chooseexcel.getSelectedFile();
                        String excelpath = excel.getAbsolutePath();
                        pathexcel.setText(excelpath);
                }
            });

            convert.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pathpdf.getText().equals("Selectionnez l'emplacement du fichier PDF à convertir")){
                        dialog.showMessageDialog(null, "Aucun fichier PDF!", "", JOptionPane.WARNING_MESSAGE);
                    }
                    else if (!pathpdf.getText().substring(pathpdf.getText().length()-3).equals("pdf")){
                        dialog.showMessageDialog(null, "Le fichier selectionné n'est pas au format PDF!", "", JOptionPane.WARNING_MESSAGE);
                    }
                    else if (pathexcel.getText().equals("Selectionnez l'emplacement du futur fichier Excel")){
                        dialog.showMessageDialog(null, "Aucun dossier de destination!", "", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        try {
                            extraction text = new extraction((pathpdf.getText()));
                            conversion con = new conversion(pathexcel.getText(),text.getArraynom(),text.getArraytel(),text.getArrayabo(), text.getArrayconso(), text.getArraystotal(), text.getArraystotal2(), text.getArraytotal());
                        }catch(IOException ignored){}
                        dialog.showMessageDialog(null, "Conversion réussie!", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            quit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    dispose();
                }
            });

        }

        public static void main(String[] args)
        {
        }

    }