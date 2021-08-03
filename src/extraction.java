import org.apache.pdfbox.text.*;
import org.apache.pdfbox.text.*;
import org.apache.pdfbox.pdmodel.*;
import java.util.regex.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;



public class extraction {

    private Pattern p = Pattern.compile("(M\\.|Mme).*?Total [0-9]+\\.[0-9]+", Pattern.DOTALL);
    private Pattern pnom = Pattern.compile("(M\\.|Mme).*?,",Pattern.DOTALL);
    private Pattern ptel = Pattern.compile("[0-9][0-9][0-9] [0-9][0-9][0-9] [0-9][0-9] [0-9][0-9]",Pattern.DOTALL);
    private Pattern pabo = Pattern.compile("(Me|Multi Surf|Plus|Business Flex|Company Option|Go Europe / Go World|Hello Europe)+?",Pattern.DOTALL);
    private Pattern pconso = Pattern.compile("^(Appels depuis la Suisse & WiFi|SMS/MMS depuis la Suisse/WiFi|Internet mobile en Suisse|Roaming|Ajustements|B2B volume discount B2B)",Pattern.DOTALL|Pattern.MULTILINE);
    private Pattern p2=Pattern.compile("(Me|Multi Surf|Plus|Business Flex|Company Option|Go Europe / Go World|Hello Europe) [0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]-[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9] (-|)[0-9]+\\.[0-9]+",Pattern.DOTALL|Pattern.MULTILINE);
    private Pattern p3=Pattern.compile("Sous-total (-|)[0-9]+\\.[0-9]+",Pattern.DOTALL|Pattern.MULTILINE);
    private Pattern pstotalabo=Pattern.compile("(-|)[0-9]+\\.[0-9]+$",Pattern.DOTALL|Pattern.MULTILINE);
    private Pattern pstotalconso=Pattern.compile("(-|)[0-9]+\\.[0-9]+$",Pattern.DOTALL|Pattern.MULTILINE);
    private Pattern ptotal=Pattern.compile("[0-9]+\\.[0-9]+$",Pattern.DOTALL);
    private List<String> arrayinfo= new ArrayList<String>();
    private List<String> arraynom= new ArrayList<String>();
    private List<String> arraytel= new ArrayList<String>();
    private List<String> arrayabo= new ArrayList<String>();
    private List<String> arrayconso = new ArrayList<String>();
    private List<String> extractstotal = new ArrayList<String>();
    private List<String> extractstotal2 = new ArrayList<String>();
    private List<String> arraystotal= new ArrayList<String>();
    private List<String> arraystotal2= new ArrayList<String>();
    private List<String> arraytotal= new ArrayList<String>();

    static String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }

    public List<String> getArraynom() {
        return arraynom;
    }

    public List<String> getArraytel() {
        return arraytel;
    }

    public List<String> getArrayabo(){
        return arrayabo;
    }

    public List<String> getArrayconso(){
        return arrayconso;
    }

    public List<String> getArraystotal(){
        return arraystotal;
    }

    public List<String> getArraystotal2(){
        return arraystotal2;
    }

    public List<String> getArraytotal(){
        return arraytotal;
    }

    public extraction(String path) throws IOException{
        try{
            String text = getText(new File(path));
            String getinfo = new String();
            String getabo= new String();
            String getconso=new String();
            String getstotal= new String();
            String getstotal2= new String();
            Matcher m = p.matcher(text);


            //EXTRACTION DONNEES CLIENT
            while(m.find()) {
                arrayinfo.add(m.group());
            }

            for(int i=0; i<arrayinfo.size();i++){
                getinfo=arrayinfo.get(i);
                getabo="";
                getconso="";
                getstotal="";
                getstotal2="";
                boolean removefirstmatch=true;

                Matcher mnom=pnom.matcher(getinfo);
                Matcher mtel=ptel.matcher(getinfo);
                Matcher mconso=pconso.matcher(getinfo);
                Matcher m2=p2.matcher(getinfo);
                Matcher m3=p3.matcher(getinfo);
                Matcher mtotal=ptotal.matcher(getinfo);

                //EXTRACTION NOM COLLABORATEUR
                while (mnom.find()){
                    arraynom.add(mnom.group().substring(0,mnom.group().length()-1));
                }

                //EXTRACTION TELEPHONE
                while (mtel.find()){
                    arraytel.add(mtel.group());
                }

                //EXTRACTION ABONNEMENTS
                while (m2.find()){
                    getstotal = (getstotal + m2.group() + "\n");
                }
                extractstotal.add(getstotal);
                Matcher mabo=pabo.matcher(getstotal);
                while (mabo.find()){
                    getabo=(getabo+mabo.group()+"\n");
                }
                arrayabo.add(getabo);


                //EXTRACTION CONSOMMATION
                while (mconso.find()){
                    getconso=(getconso+mconso.group()+"\n");
                }
                arrayconso.add(getconso);

                //EXTRACTION SOUS-TOTAL ABONNEMENTS
                Matcher mstotalabo=pstotalabo.matcher(getstotal);
                while (mstotalabo.find()){
                    getstotal2 = (getstotal2 + mstotalabo.group() + "\n");

                }
                arraystotal.add(getstotal2);

                getstotal="";
                getstotal2="";

                //EXTRACTION SOUS-TOTAL CONSOMMATION
                while (m3.find()){
                    if (removefirstmatch==true){
                        removefirstmatch=false;
                    }
                    else {
                        getstotal = (getstotal + m3.group() + "\n");
                    }
                }
                extractstotal2.add(getstotal);
                Matcher mstotalconso=pstotalconso.matcher(extractstotal2.get(i));
                while (mstotalconso.find()){
                    getstotal2 = (getstotal2 + mstotalconso.group() + "\n");
                }
                arraystotal2.add(getstotal2);



                //EXTRACTION TOTAL
                while (mtotal.find()){
                    arraytotal.add(mtotal.group());
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }}

    public static void main(String args[]) throws Exception {
    }}